/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.sms.request.CheckCodeRequest;
import com.meiduimall.service.sms.request.SendCodeRequest;
import com.meiduimall.service.sms.request.SendMessageRequest;
import com.meiduimall.service.sms.service.SmsService;

/**
 * 公共短信发送服务
 *
 * @author lin
 * @date 2017-01-12
 */

@RestController
@RequestMapping("/notify/short_msg_service/v1")
public class SmsController {

	@Autowired
	private SmsService smsService;

	/**
	 * 发送短消息
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/send_common_sms_message")
	public ResBodyData sendSmsMessage(@Validated SendMessageRequest model) {
		return smsService.sendSmsMessage(model);
	}

	/**
	 * 发送短信验证码
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/send_sms_verification_code")
	public ResBodyData sendSmsVerificationCode(@Validated SendCodeRequest model) {
		return smsService.sendSmsVerificationCode(model);
	}

	/**
	 * 校验短信验证码
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/check_sms_verification_code")
	public ResBodyData checkSmsVerificationCode(@Validated CheckCodeRequest model) {
		return smsService.checkSmsVerificationCode(model);
	}
}
