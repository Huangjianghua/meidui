package com.meiduimall.service.sms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.sms.request.SmsRequest;
import com.meiduimall.service.sms.service.SmsService;


/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: SmsController.java
 * Author:   lin
 * Date:     2017年3月14日 下午3:37:58
 * Description: 公共短信发送服务
 */
@RestController
@RequestMapping("notify/short_msg_service/v1")
public class SmsController {
	
	
	@Autowired
	private SmsService smsService;
	
	
	/**
	 * 功能描述:  发送短信
	 * Author: 陈建宇
	 * Date:   2017年3月14日 下午3:38:26   
	 * return  ResBodyData
	 */
	@RequestMapping("/send_common_sms_message")
	public ResBodyData sendSmsMessage(@Validated SmsRequest request) {
		ResBodyData result =null;
		try {
			result = smsService.sendSmsMessage(request);
		} catch (Exception e) {
			result = new ResBodyData(BaseApiCode.EXCEPTION_SMS_SEND, BaseApiCode.getZhMsg(BaseApiCode.EXCEPTION_SMS_SEND));
		}
		return result;
	}
	
	
	
	/**
	 * 功能描述: 发送短信验证码
	 * Author: 陈建宇
	 * Date:   2017年3月14日 下午3:38:38   
	 * return  ResBodyData
	 */
	@RequestMapping("/send_sms_verification_code")
	public ResBodyData sendSmsVerificationCode(@Validated SmsRequest request) {
		ResBodyData result = null;
		try {
			result = smsService.sendSmsVerificationCode(request);
		} catch (Exception e) {
			result = new ResBodyData(BaseApiCode.EXCEPTION_SMS_SEND, BaseApiCode.getZhMsg(BaseApiCode.EXCEPTION_SMS_SEND));
		}
		return result;
	}
	
}
