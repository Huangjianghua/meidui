package com.meiduimall.service.sms.service;

/**
 * 漫道短信服务
 * 
 * @author yangchang
 *
 */
public interface ZucpService {

	/**
	 * 发送短信
	 * 
	 * @param mobile
	 * @param content
	 * @param ext
	 * @param stime
	 * @param rrid
	 * @return
	 */
	String send(String mobile, String content, String ext, String stime, String rrid);

	/**
	 * 发送短信
	 * 
	 * @param mobile
	 * @param content
	 * @return
	 */
	String send(String mobile, String content);
}
