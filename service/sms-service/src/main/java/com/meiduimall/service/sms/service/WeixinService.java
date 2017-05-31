package com.meiduimall.service.sms.service;

import com.meiduimall.service.sms.request.WXMsgOnPaySuccessRequest;

public interface WeixinService {

	/**
	 * 获取微信access_token
	 * 
	 * @return access_token
	 */
	String getAccessToken();

	/**
	 * 发送模板消息
	 * 
	 * @param phone
	 *            用户手机号
	 * @param model
	 *            请求参数
	 * @return 发送结果
	 */
	String sendTemplateMessageOnPaySuccess(String phone, WXMsgOnPaySuccessRequest model);
}
