package com.meiduimall.service.sms.service;

import com.meiduimall.exception.MdSysException;

/**
 * 漫道短信服务
 * 
 * @author yangchang
 *
 */
public interface ZucpService {

	String send(String mobile, String content, String ext, String stime, String rrid) throws MdSysException;

	/**
	 * 发送短信
	 * 
	 * @param mobile
	 *            手机号
	 * @param content
	 *            发送内容
	 * @return 发送结果
	 * @throws MdSysException
	 */
	String send(String mobile, String content) throws MdSysException;
}
