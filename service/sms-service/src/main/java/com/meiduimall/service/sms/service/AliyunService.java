package com.meiduimall.service.sms.service;

/**
 * 阿里大于短信服务
 * 
 * @author yangchang
 *
 */
public interface AliyunService {

	/**
	 * 发送短信
	 * 
	 * @param mobile
	 * @param tid
	 * @param context
	 * @return
	 */
	boolean send(String mobile, String tid, String context);
}
