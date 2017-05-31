package com.meiduimall.service.sms.service;

import com.meiduimall.service.sms.request.WXMsgOnPaySuccessRequest;

public interface WeixinService {

	/**
	 * 获取微信access_token
	 * 
	 * @return access_token
	 */
	String getAccessToken();

	String sendTemplateMessageOnPaySuccess(WXMsgOnPaySuccessRequest model);
}
