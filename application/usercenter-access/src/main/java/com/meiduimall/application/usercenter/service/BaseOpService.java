package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.constant.ResBodyDataShiPei;
import com.meiduimall.core.ResBodyData;

/**
 * 会员基本操作
 * @author chencong
 *
 */
public interface BaseOpService {

	/**
	 * 登录
	 * @param reqJson 请求的数据
	 * @return 统一数据返回格式
	 */
	ResBodyData login(JSONObject reqJson);
	ResBodyDataShiPei getPut(JSONObject reqJson);
	ResBodyDataShiPei handleSignOut(JSONObject reqJson);

}
