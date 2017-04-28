package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;

/**
 * 短信相关接口
 * @author chencong
 *
 */
public interface SmsService {
	
	/**
	 * 获取短信验证码
	 * @param reqJson
	 * @return
	 */
	ResBodyData getValidatCode(JSONObject reqJson);
}
