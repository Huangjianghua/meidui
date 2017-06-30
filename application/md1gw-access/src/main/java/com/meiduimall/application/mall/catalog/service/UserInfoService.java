package com.meiduimall.application.mall.catalog.service;

import com.meiduimall.core.ResBodyData;

public interface UserInfoService {

	/**
	 * 获取用户基本信息
	 * @param memId 会员ID
	 * @param token 会员token
	 * @return 数据对象
	 */
	ResBodyData getUserInfoForApp(String memId, String token);

}
