package com.meiduimall.service;

import org.springframework.dao.DataAccessException;

import com.alibaba.fastjson.JSONObject;
public interface AuthorizationService 
{
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
}
