package com.meiduimall.service.sms.service;

/**
 * 漫道短信服务
 * 
 * @author yangchang
 *
 */
public interface ZucpService {

	String send(String mobile, String content, String ext, String stime, String rrid);

	/**
	 * 发送短信
	 * 
	 * @param mobile
	 *            手机号
	 * @param content
	 *            发送内容
	 * @return 发送结果
	 */
	String send(String mobile, String content);
}
