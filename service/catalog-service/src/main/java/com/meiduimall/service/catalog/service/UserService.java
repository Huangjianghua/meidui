package com.meiduimall.service.catalog.service;

import com.meiduimall.service.catalog.entity.SysuserAccount;

/**
 * 用户相关
 * 
 * @author yangchang
 *
 */
public interface UserService {

	/**
	 * 根据token，获取用户的账户信息
	 * 
	 * @param token
	 * @return
	 */
	SysuserAccount getUserByToken(String token);
}
