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
import com.meiduimall.service.sms.util.PhoneUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
	@ApiOperation(value="发送普通短信", notes="发送普通短信")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "model", value = "短信实体", required = true, dataType = "SendMessageRequest")
	})
	@RequestMapping("/new/send_common_sms_message")
	public ResBodyData sendSmsMessage(@Validated SendMessageRequest model) {
		model.setPhones(PhoneUtil.formatParamsPhones(model.getPhones()));
		return smsService.sendSmsMessage(model);
	}

	/**
	 * 发送短信验证码
	 *
	 * @param model
	 *            请求参数封装的SendCodeRequest对象
	 * @return 发送结果和验证码
	 */
	@ApiOperation(value="发送短信验证码", notes="发送短信验证码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "model", value = "短信实体", required = true, dataType = "SendCodeRequest")
	})
	@RequestMapping("/new/send_sms_verification_code")
	public ResBodyData sendSmsVerificationCode(@Validated SendCodeRequest model) {
		model.setPhones(PhoneUtil.formatParamsPhones(model.getPhones()));
		return smsService.sendSmsVerificationCode(model);
	}

	/**
	 * 校验短信验证码
	 *
	 * @param model
	 *            请求参数封装的CheckCodeRequest对象
	 * @return 校验结果
	 */
	@ApiOperation(value="校验短信验证码", notes="校验短信验证码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "model", value = "验证码实体", required = true, dataType = "CheckCodeRequest")
	})
	@RequestMapping("/new/check_sms_verification_code")
	public ResBodyData checkSmsVerificationCode(@Validated CheckCodeRequest model) {
		model.setPhones(PhoneUtil.formatParamsPhones(model.getPhones()));
		return smsService.checkSmsVerificationCode(model);
	}
}
