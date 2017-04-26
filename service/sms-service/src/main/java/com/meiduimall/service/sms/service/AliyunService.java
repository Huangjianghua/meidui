package com.meiduimall.service.sms.service;

/**
 * 阿里大于短信服务
 * 
 * @author yangchang
 */
public interface AliyunService {

	/**
	 * 发送短信
	 * 
	 * @param mobile
	 *            手机号
	 * @param tid
	 *            阿里大于分配的模板ID
	 * @param context
	 *            发送内容
	 * @return 发送结果，true表示发送成功，false表示发送失败
	 */
	boolean send(String mobile, String tid, String context);
}
