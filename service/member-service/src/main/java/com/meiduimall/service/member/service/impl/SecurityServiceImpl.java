package com.meiduimall.service.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.redis.util.RedisTemplate;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.member.constant.ConstApiStatus;
import com.meiduimall.service.member.constant.ConstSysEncrypParams;
import com.meiduimall.service.member.constant.ConstSysParams;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.MSMembersSet;
import com.meiduimall.service.member.model.request.RequestLoginUnlock;
import com.meiduimall.service.member.model.response.MemberLockDTO;
import com.meiduimall.service.member.service.SecurityService;
import com.meiduimall.service.member.util.DESC;


@Service
public class SecurityServiceImpl implements SecurityService 
{
	private final static Logger logger=LoggerFactory.getLogger(SecurityServiceImpl.class);
	
	@Override
	public ResBodyData setPaypwdStatus(MSMembersGet msMembersGet){
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		msMembersGet.setEnable("1".equals(msMembersGet.getEnable())
				?"Y":"N");
		baseDao.update(msMembersGet,"MSMembersMapper.updateMemberBasicInfoByCondition");
		return resBodyData;
	}
	
	@Autowired
	private  BaseDao  baseDao;
	@Override
	public String updateLoginPwd(JSONObject obj) throws Exception {
		JSONObject result = new JSONObject();
		String one_pass_word = obj.getString("one_pass_word");
		String old_pass_word = obj.getString("old_pass_word");
		String ip = obj.getString("ip");
		String memberId = obj.getString("memberId");
		boolean blc = validateOldPwd_wai(memberId, old_pass_word);
		if(!blc){
			result.put(ConstSysParams.STATUS, "1023");
			result.put(ConstSysParams.MSG, "原始密码输入错误");
			logger.info("外部当前请求找回登录密码IP地址=" + ip + "结束,原始密码输入错误");
			return result.toString();
		}
		Map<String,Object> param  = new HashMap<String,Object>();
		param.put("memberId",memberId);
		param.put("one_pass_word", one_pass_word);
		
		int blean = baseDao.update(param,"MSMembersMapper.updateMemberBasicInfoByCondition");
		if (blean>0) {
			result.put(ConstSysParams.STATUS, "0");
			result.put(ConstSysParams.MSG, "修改成功");
			logger.info("外部当前请求邮箱找回登录密码IP地址=" + ip + "结束,修改成功");
			return result.toString();
		}else {
			result.put(ConstSysParams.STATUS, "0");
			result.put(ConstSysParams.MSG, "修改成功");
			logger.info("外部当前请求邮箱找回登录密码IP地址=" + ip + "结束,修改登录密码 失败");
			return result.toString();
		}
	}
	@Override
	public boolean validateOldPwd_wai(String id, String pwd) throws Exception {
		//增加美兑会员密码规则
		String[] pwds = pwd.split(ConstSysParams.MD_PASSWORD_SPLIT_STR);
		int  recordRows =0;
		Map<String,Object> param = new HashMap<String,Object>();
		if(pwds.length == 2){
			
			
			param.put("memIdOne",id);
			
			param.put("memLoginPwdOne",pwds[0]);
			
			param.put("memLoginPwdTwo", pwds[1]);
			
			recordRows = baseDao.selectOne(param,"MSMembersMapper.getMemberRecordByTarget");
		}else{
			param.put("memIdTwo",id);
			param.put("memLoginPwdThree", pwd);
			recordRows = baseDao.selectOne(param,"MSMembersMapper.getMemberRecordByTarget");
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
		RedisTemplate  jedisUtil  = RedisTemplate.getJedisInstance();
		JSONObject result = new JSONObject();
		String phone = obj.getString("phone");
		String two_pass_word = obj.getString("two_pass_word");
		String ip = obj.getString("ip");
		MSMembersGet m = baseDao.selectOne(DESC.encryption(phone),"MSMembersMapper.getMemberIdByPLName");
		if (null == m) {
			result.put(ConstSysParams.STATUS, "9999");
			result.put(ConstSysParams.MSG, "服务器错误。缺少认证参数或服务器错误统一返回此参数");
			logger.info("外部当前请求手机找回登录密码IP地址=" + ip + "结束,当前手机号码不存在");
			return result.toString();
		}
		jedisUtil.execDelToCache(phone + "app_code_zhaohui");
		Map<String,Object> param  = new HashMap<String,Object>();
		param.put("memberId", m.getMemId());
		param.put("two_pass_word",two_pass_word);
		int blean = baseDao.update(param, "MSMembersMapper.updateMemberBasicInfoByCondition");
		if (blean>0) {
			result.put(ConstSysParams.STATUS, "0");
			result.put(ConstSysParams.MSG, "修改成功");
			//找回密码，清理24小时密码错误缓存
			jedisUtil.execDelToCache(m.getMemId());
			logger.info("外部当前请求手机找回登录密码IP地址=" + ip + "结束,修改成功");
			return result.toString();
		} else {
			result.put(ConstSysParams.STATUS, "9999");
			result.put(ConstSysParams.MSG, "修改失败");
			logger.info("外部当前请求手机找回登录密码IP地址=" + ip + "结束,修改登录密码失败");
			return result.toString();
		}
	}
	

	@Override
	public JSONObject updateMemberPhone(String token, String newPhone, String code, String password) throws Exception {
		/*JSONObject json = new JSONObject();
		Map<String, String> param = new HashMap<String, String>();
		String memId = JedisUtil.getJedisInstance().execGetFromCache(token);
		if (StringUtil.checkStr(memId)) {
			MemberGet lv1 = baseDao.selectOne(memId, "MSMembersMapper.getMembersInfoByMemId");
			String exist_code=JedisUtil.getJedisInstance().execGetFromCache(SysParamsConst.UPDATE_PHONE_VALIDATE_CODE+newPhone);
			if (exist_code.equals(code)) {
				if (lv1.getMemLoginPwd().equals(password)) {
					if (StringUtil.isPhoneToRegex(newPhone)) {
						String valMemId = baseDao.selectOne(newPhone, "MSMembersMapper.getMemberInfoByCondition");
						if (!StringUtil.checkStr(valMemId)) {
							param.put("oldPhone", DESC.encryption(lv1.getMemPhone()));
							param.put("newPhone", DESC.encryption(newPhone));
							param.put("updateDate", DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS));
							param.put("memId", memId);
							baseDao.selectOne(param, "MSMembersMapper.updateMemberPhoneByMemId");
							json.put(SysParamsConst.STATUS, "0");
							json.put(SysParamsConst.MSG, "手机号码修改成功!");
							logger.info("修改手机号码成功!");
						} else {
							json.put(SysParamsConst.STATUS, ApiStatusConst.UPDATE_NEWPHONE_ERROR);
							json.put(SysParamsConst.MSG, ApiStatusConst.UPDATE_NEWPHONE_ERROR_C);
							logger.info("新手机号码已被注册,注册人ID:%s!" + valMemId);
						}
					} else {
						json.put(SysParamsConst.STATUS, ApiStatusConst.PHONE_ERROR);
						json.put(SysParamsConst.MSG, ApiStatusConst.PHONE_ERROR_C);
						logger.info("手机号码:%s错误!", newPhone);
					}
				} else {
					json.put(SysParamsConst.STATUS, ApiStatusConst.PASSWORD_ERROR);
					json.put(SysParamsConst.MSG, ApiStatusConst.PASSWORD_ERROR_C);
					logger.info("修改手机号码:密码验证失败!");
				}
			} else {
				json.put(SysParamsConst.STATUS, ApiStatusConst.VAL_SMSMSG_ERROR);
				json.put(SysParamsConst.MSG, ApiStatusConst.VAL_SMSMSG_ERROR_C);
				logger.info("修改手机号码:短信校验码校验不匹配!");
			}
		} else {
			json.put(SysParamsConst.STATUS, "1020");
			json.put(SysParamsConst.MSG, "当前会员不存在!");
			logger.info("当前会员ID:%s不存在!", memId);
		}
		return json;*/
		return null;
	}
	/**
	 * @param id
	 * @throws MdBizException
	 */
	@Override
	public void disabledAccount(String id) throws MdBizException {
		try {
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("memId", id);
			param.put("status",ConstSysEncrypParams.MEMBER_FORBIDDEN_EN);
			baseDao.update(param, "MSAccountMapper.updateAccountById");
		} catch (Exception e) {
			logger.error("调用账号禁用API接口SecurityServiceImpl.disabledAccount异常:{}", e);
			throw new MdBizException(ConstApiStatus.DISABLED_ACCOUNT_EXCEPTION);
		}
	}
	/**
	 * @param id
	 * @throws MdBizException
	 */
	@Override
	public void unDisabledAccount(String id) throws MdBizException {
		try {
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("memId", id);
			param.put("status",ConstSysEncrypParams.MEMBER_STATUS_OK);
			baseDao.update(param, "MSAccountMapper.updateAccountById");
		} catch (Exception e) {
			logger.error("调用账号解禁API接口SecurityServiceImpl.unDisabledAccount异常:{}", e.getMessage());
			throw new MdBizException(ConstApiStatus.UNDISABLED_ACCOUNT_EXCEPTION);
		}
	}
	/**
	 * @param param
	 * @throws MdBizException
	 */
	@Override
	public void resetAccountPwd(Map<String, Object> param) throws MdBizException {
		try {
			baseDao.update(param, "MSAccountMapper.updateAccountById");
		} catch (Exception e) {
			logger.error("调用重置密码API接口SecurityServiceImpl.resetAccountPwd异常:{}", e.getMessage());
			throw new MdBizException(ConstApiStatus.RESET_ACCOUNT_PWD_EXCEPTION);
		}
	}
	/**
	 * @param loginUnlock
	 * @return
	 * @throws MdBizException
	 */
	@Override
	public List<MemberLockDTO> loginUnlockList(RequestLoginUnlock loginUnlock) throws MdBizException {
		List<MemberLockDTO> list=null;
		try {
			list=baseDao.selectList(loginUnlock, "MSMembersMapper.queryMemberLockList");
		} catch (Exception e) {
			logger.error("调用登陆解锁API接口SecurityServiceImpl.loginUnlockList异常:{}", e.getMessage());
			throw new MdBizException(ConstApiStatus.LOGIN_UNLOCK_LIST_EXCEPTION);
		}
		return list;
	}
	/**
	 * @param param
	 * @throws MdBizException
	 */
	@Override
	public void unlockMember(Map<String, Object> param) throws MdBizException {
		try {
			//step1 根据memId查询会员
			MSMembersGet membersGet=baseDao.selectOne(param, "MSMembersMapper.getMemberBasicInfoByCondition");
			if(membersGet==null) throw new MdBizException(ConstApiStatus.MEMBER_NOT_EXIST);
			//step2修改锁定次数为0
			MSMembersSet getMember=new MSMembersSet();
			getMember.setMemId(membersGet.getMemId());
			getMember.setMemLockCountPlained(ConstSysParams.INIT_LOGIN_LOCK_COUNT);
			getMember.setMemLockCount(ConstSysParams.INIT_LOGIN_LOCK_COUNT);
			baseDao.update(getMember, "MSMembersMapper.updateMemberBasicInfoByCondition");
			//step3删除redis里面的key
			RedisUtils.del(ConstSysParams.REDIS_LOGIN_LOCK+ConstSysParams.CONNECTION+membersGet.getMemId());
		} catch (Exception e) {
			logger.error("调用解锁API接口SecurityServiceImpl.unlockMember异常:{}", e.getMessage());
			throw new MdBizException(ConstApiStatus.LOGIN_UNLOCK_EXCEPTION);
		}
	}

}
