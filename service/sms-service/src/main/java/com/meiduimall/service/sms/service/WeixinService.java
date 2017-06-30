package com.meiduimall.service.sms.service;

import com.meiduimall.core.ResBodyData;
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
	 * @param model
	 *            请求参数
	 * @return 发送结果
	 */
	ResBodyData sendTemplateMessageOnPaySuccess(WXMsgOnPaySuccessRequest model);

	/**
	 * 请求会员系统，获取用户信息
	 * @param model
	 */
	void getMemberInfo(WXMsgOnPaySuccessRequest model);
}
