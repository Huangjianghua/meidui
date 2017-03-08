package com.meiduimall.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.constant.SysConstant;
import com.meiduimall.dao.BaseDao;
import com.meiduimall.model.MemberGet;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.service.AuthorizationService;
import com.meiduimall.util.DESC;
import com.meiduimall.util.Logger;
@Service
public class AuthorizationServiceImpl implements AuthorizationService 
{
	@Autowired
	private  BaseDao  baseDao;
	@Override
	public String updateLoginPwd(JSONObject obj) throws Exception {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		String one_pass_word = obj.getString("one_pass_word");
		String old_pass_word = obj.getString("old_pass_word");
		String ip = obj.getString("ip");
		String memberId = obj.getString("memberId");
		boolean blc = validateOldPwd_wai(memberId, old_pass_word);
		if(!blc){
			result.put(SysConstant.STATUS_CODE, "1023");
			result.put(SysConstant.RESULT_MSG, "原始密码输入错误");
			Logger.info("外部当前请求找回登录密码IP地址=" + ip + "结束,原始密码输入错误");
			return result.toString();
		}
		Map<String,Object> param  = new HashMap<String,Object>();
		param.put("memberId",memberId);
		param.put("one_pass_word", one_pass_word);
		
		int blean = baseDao.update(param,"MemberMapper.updateMemberLoginPwdByMemId");
		if (blean>0) {
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, "修改成功");
			Logger.info("外部当前请求邮箱找回登录密码IP地址=" + ip + "结束,修改成功");
			return result.toString();
		}else {
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, "修改成功");
			Logger.info("外部当前请求邮箱找回登录密码IP地址=" + ip + "结束,修改登录密码 失败");
			return result.toString();
		}
	}
	@Override
	public boolean validateOldPwd_wai(String id, String pwd) throws Exception {
		//增加美兑会员密码规则
		String[] pwds = pwd.split(SysConstant.MD_PASSWORD_SPLIT_STR);
		int  recordRows =0;
		Map<String,Object> param = new HashMap<String,Object>();
		if(pwds.length == 2){
			
			
			param.put("memIdOne",id);
			
			param.put("memLoginPwdOne",pwds[0]);
			
			param.put("memLoginPwdTwo", pwds[1]);
			
			recordRows = baseDao.selectOne(param,"MemberMapper.getMemberRecordByTarget");
		}else{
			param.put("memIdTwo",id);
			param.put("memLoginPwdThree", pwd);
			recordRows = baseDao.selectOne(param,"MemberMapper.getMemberRecordByTarget");
		}
		if (recordRows > 0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	@Transactional
	public String updateLoginPwdByPhone(JSONObject obj) throws Exception {
		// TODO Auto-generated method stub
		JedisUtil  jedisUtil  = JedisUtil.getJedisInstance();
		JSONObject result = new JSONObject();
		String phone = obj.getString("phone");
		String two_pass_word = obj.getString("two_pass_word");
		String ip = obj.getString("ip");
		MemberGet m = baseDao.selectOne(DESC.encryption(phone),"MemberMapper.getMemberIdByPLName");
		if (null == m) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误。缺少认证参数或服务器错误统一返回此参数");
			Logger.info("外部当前请求手机找回登录密码IP地址=" + ip + "结束,当前手机号码不存在");
			return result.toString();
		}
		jedisUtil.execDelToCache(phone + "app_code_zhaohui");
		Map<String,Object> param  = new HashMap<String,Object>();
		param.put("memberId", m.getMemId());
		param.put("two_pass_word",two_pass_word);
		int blean = baseDao.update(param, "MemberMapper.updateMemberLoginPwdByMemId");
		if (blean>0) {
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, "修改成功");
			//找回密码，清理24小时密码错误缓存
			jedisUtil.execDelToCache(m.getMemId());
			Logger.info("外部当前请求手机找回登录密码IP地址=" + ip + "结束,修改成功");
			return result.toString();
		} else {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "修改失败");
			Logger.info("外部当前请求手机找回登录密码IP地址=" + ip + "结束,修改登录密码失败");
			return result.toString();
		}
	}

}
