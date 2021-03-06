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
	ResBodyData login(JSONObject reqJson) throws MdSysException;
	ResBodyData register(JSONObject reqJson) throws MdSysException;
	ResBodyDataShiPei getPut(JSONObject reqJson) throws MdSysException;
	ResBodyDataShiPei handleSignOut(JSONObject reqJson) throws MdSysException;

}
