package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * 支付密码操作接口
 * @author chencong
 *
 */
public interface PayPwdService {

	ResBodyData validePaypwd(JSONObject reqJson) throws MdSysException;
	ResBodyData setPaypwd(JSONObject reqJson) throws MdSysException;
	ResBodyData setPaypwdStatus(JSONObject reqJson) throws MdSysException;
	ResBodyData updatePaypwd(JSONObject reqJson) throws MdSysException;
	ResBodyData retrievePaypwd(JSONObject reqJson) throws MdSysException;
}
