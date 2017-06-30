package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;

public interface ExternalRechargeService {
	/**
	 * 
	 * @param reqJson
	 * @return
	 */
	public ResBodyData externalMemberRecharge(JSONObject reqJson);

	ResBodyData insertLog(JSONObject reqJson);
}
