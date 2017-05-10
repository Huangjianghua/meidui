package com.meiduimall.service.member.service;


import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.request.RequestLoginUnlock;
import com.meiduimall.service.member.model.response.MemberLockDTO;


public interface SecurityService {
	
	/***
	 * 设置支付密码开关状态
	 * @param msMembers
	 * @return 1：开   0：关
	 */
	ResBodyData setPaypwdStatus(MSMembersGet msMembers);
	
	/**
	 * 会员修改登录密码
	 * @param obj
	 * @return
	 * @throws DataAccessException
	 */
	public String updateLoginPwd(JSONObject obj)throws DataAccessException,Exception;
	
	
	/**
	 * * 外部接口校验原始登录密码
	 * 
	 * @param id
	 *            会员编号
	 * @param pwd
	 *            原始登录密码
	 * @return true原始登录密码存在 false原始登录密码不存在
	 */
	public boolean validateOldPwd_wai(String id, String pwd)throws Exception;
	
	/**
	 * 手机找回登录密码
	 * @param obj
	 * @return
	 * @throws DataAccessException
	 */
	public String updateLoginPwdByPhone(JSONObject obj)throws Exception;
	
	public JSONObject updateMemberPhone(String token, String newPhone, String code, String password) throws Exception;
	
	/**
	 * 账号禁用
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月2日 下午3:37:13
	 */
	public void  disabledAccount(String id)throws MdBizException;
	
	/**
	 * 账号解禁
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月2日 下午3:37:13
	 */
	public void  unDisabledAccount(String id)throws MdBizException;
	
	/**
	 * 重置账号密码
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月3日 上午9:52:04
	 */
	public void resetAccountPwd(Map<String, Object> param)throws MdBizException;
	
	/**
	 * 登陆解锁列表
	 * @param loginUnlock
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月3日 下午12:10:48
	 */
	public List<MemberLockDTO>  loginUnlockList(RequestLoginUnlock loginUnlock)throws MdBizException;
	
	/**
	 * 解锁账号
	 * @param param
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月3日 下午12:12:18
	 */
	public void unlockMember(Map<String, Object> param)throws MdBizException;
}
