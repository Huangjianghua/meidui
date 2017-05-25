package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.constant.ResBodyDataShiPei;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

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
	 * @throws MdSysException 系统异常
	 */
<<<<<<< HEAD
	ResBodyData login(JSONObject reqJson);
	ResBodyData register(JSONObject reqJson);
	String getPut(JSONObject reqJson);
	ResBodyDataShiPei handleSignOut(JSONObject reqJson);
=======
	ResBodyData login(JSONObject reqJson) throws MdSysException;
	ResBodyData register(JSONObject reqJson) throws MdSysException;
	ResBodyDataShiPei getPut(JSONObject reqJson) throws MdSysException;
	ResBodyDataShiPei handleSignOut(JSONObject reqJson) throws MdSysException;
>>>>>>> refs/remotes/origin/feature/V4.0.2-Team2

}
