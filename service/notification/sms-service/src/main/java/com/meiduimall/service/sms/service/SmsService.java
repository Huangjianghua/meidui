package com.meiduimall.service.sms.service;
import com.meiduimall.service.sms.request.SmsRequest;
import com.meiduimall.support.core.ResBodyData;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: SmsService.java
 * Author:   
 * Date:     2017年3月14日 下午2:58:31
 * Description: 短信发送相关
 */
public interface SmsService {

	/**
	 * 发送短信
	 * 
	 * @param CommonShortMessageModel
	 * @return
	 * @throws Exception
	 */
	public ResBodyData sendSmsMessage(SmsRequest request) throws Exception;
	
	/**
	 * 发送短信验证码
	 * 
	 * @param CommonShortMessageModel
	 * @return
	 * @throws Exception
	 */
	public ResBodyData sendSmsVerificationCode(SmsRequest request) throws Exception;
	

}
