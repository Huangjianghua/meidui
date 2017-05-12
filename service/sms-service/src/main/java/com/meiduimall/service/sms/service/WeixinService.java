package com.meiduimall.service.sms.service;

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
	 * @return
	 */
	String sendTemplateMessageOnPaySuccess();
}
