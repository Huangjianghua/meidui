package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * 短信相关接口
 * @author chencong
 *
 */
public interface SmsService {
	
	ResBodyData getValidatCode(JSONObject reqJson) throws MdSysException;
}
