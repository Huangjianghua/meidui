package com.meiduimall.service.sms.service;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.sms.request.SmsRequest;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: SmsService.java
 * Author:   
 * Date:     2017年3月14日 下午2:58:31
 * Description: 短信发送相关
 */
public interface SmsService {

	/**
	 * 功能描述:  发送短信
	 * Author: 陈建宇
	 * Date:   2017年3月15日 下午12:19:06   
	 * return  ResBodyData
	 */
	public ResBodyData sendSmsMessage(SmsRequest request) throws Exception;
	
	/**
	 * 功能描述:  发送短信验证码
	 * Author: 陈建宇
	 * Date:   2017年3月15日 下午12:19:16   
	 * return  ResBodyData
	 */
	public ResBodyData sendSmsVerificationCode(SmsRequest request) throws Exception;
	

}
