package com.meiduimall.service.member.service;

/**
 * 账号校验相关接口
 * @author chencong
 *
 */
public interface ValidateService {
	
	/**
	 * 校验userId在库中是否存在
	 * @param userId 手机号或邮箱或登录名 
	 * @return true:存在  false:不存在
	 */
	boolean checkUserIdExists(String userId);
}
