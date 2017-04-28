package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;

/**
 * 支付密码操作接口
 * @author chencong
 *
 */
public interface PayPwdService {

	ResBodyData validePaypwd(JSONObject reqJson);
	ResBodyData setPaypwd(JSONObject reqJson);
	ResBodyData setPaypwdStatus(JSONObject reqJson);
	
	/**
	 * 更新支付密码
	 * @param reqJson
	 * @return
	 */
	ResBodyData updatePaypwd(JSONObject reqJson);
}
