package com.meiduimall.service.member.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * 账号校验相关接口
 * @author chencong
 *
 */
public interface ValidateService {
	
	/**
	 * 校验userId在库中是否存在
	 * @param userId 手机号或邮箱或登录名 
	 * @return 统一数据返回格式
	 * @throws MdSysException 系统异常
<<<<<<< HEAD
	 */
=======
	 */
>>>>>>> refs/remotes/origin/feature/V4.0.2-Team2
	ResBodyData checkUserIdExists(String userId) throws MdSysException;

	void checkUserIdExistsThrowable(String userId) throws MdSysException;
	
	/**
	 * 校验注册来源
	 * @param source 注册来源
	 */
	void checkRegisterSource(Integer source);
}
