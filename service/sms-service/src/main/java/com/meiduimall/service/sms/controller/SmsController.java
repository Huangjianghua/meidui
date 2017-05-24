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
 * 公共短信发送和校验
 *
 * @author yangchangfu
 */

@RestController
@RequestMapping("/notify/short_msg_service/v1")
public class SmsController {

	@Autowired
	private SmsService smsService;

	/**
	 * 发送普通短信
	 *
	 * @param model
	 *            请求参数封装的SendMessageRequest对象
	 * @return 发送结果
	 */
	@RequestMapping("/new/send_common_sms_message")
	public ResBodyData sendSmsMessage(@Validated SendMessageRequest model) {
		model.setPhones(formatParamsPhones(model.getPhones()));
		return smsService.sendSmsMessage(model);
	}

	/**
	 * 发送短信验证码
	 *
	 * @param model
	 *            请求参数封装的SendCodeRequest对象
	 * @return 发送结果和验证码
	 */
	@RequestMapping("/new/send_sms_verification_code")
	public ResBodyData sendSmsVerificationCode(@Validated SendCodeRequest model) {
		model.setPhones(formatParamsPhones(model.getPhones()));
		return smsService.sendSmsVerificationCode(model);
	}

	/**
	 * 校验短信验证码
	 *
	 * @param model
	 *            请求参数封装的CheckCodeRequest对象
	 * @return 校验结果
	 */
	@RequestMapping("/new/check_sms_verification_code")
	public ResBodyData checkSmsVerificationCode(@Validated CheckCodeRequest model) {
		model.setPhones(formatParamsPhones(model.getPhones()));
		return smsService.checkSmsVerificationCode(model);
	}

	/**
	 * 手机号过滤 +86
	 * 
	 * @param phones
	 *            一个或者多个手机号
	 */
	private String formatParamsPhones(String phones) {
		StringBuilder sb = new StringBuilder();
		if (phones.contains(",")) {
			String[] split = phones.split(",");
			for (String phone : split) {
				phone = formartPhone(phone);
				sb.append(phone + ",");
			}
			return sb.substring(0, sb.length() - 1);
		} else {
			return formartPhone(phones);
		}
	}

	/**
	 * 手机号过滤 +86
	 * 
	 * @param phone
	 *            单个手机号
	 * @return 过滤后的手机号
	 */
	private String formartPhone(String phone) {
		phone = phone.trim();
		if (phone.startsWith("+")) {
			phone = phone.substring(1);
		}
		if (phone.startsWith("86")) {
			phone = phone.substring(2);
		}
		return phone;
	}
}
