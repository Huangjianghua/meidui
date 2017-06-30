package com.meiduimall.service.sms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.sms.constant.SmsApiCode;
import com.meiduimall.service.sms.model.ResultBody;
import com.meiduimall.service.sms.request.CheckCodeRequest;
import com.meiduimall.service.sms.request.SendCodeRequest;
import com.meiduimall.service.sms.request.SendMessageRequest;
import com.meiduimall.service.sms.service.SmsService;
import com.meiduimall.service.sms.util.PhoneUtil;

/**
 * 公共短信发送和校验
 *
 * @author yangchangfu
 */

@RestController
@RequestMapping("/notify/short_msg_service/v1")
public class OldSmsController {

	private static Logger logger = LoggerFactory.getLogger(OldSmsController.class);

	@Autowired
	private SmsService smsService;

	/**
	 * 发送普通短信
	 *
	 * @param model
	 *            请求参数封装的SendMessageRequest对象
	 * @return 发送结果
	 */
	@RequestMapping("/send_common_sms_message")
	public ResultBody oldSendSmsMessage(SendMessageRequest model) {
		try {
			// 参数校验
			if (model == null) {
				return new ResultBody(ResultBody.FAILED, SmsApiCode.getZhMsg(SmsApiCode.REQUEST_PARAMS_ERROR));
			}
			if (Strings.isNullOrEmpty(model.getPhones()) || Strings.isNullOrEmpty(model.getTemplateId())) {
				return new ResultBody(ResultBody.FAILED, SmsApiCode.getZhMsg(SmsApiCode.REQUEST_PARAMS_ERROR));
			}


			// 过滤手机号+86字符
			model.setPhones(PhoneUtil.formatParamsPhones(model.getPhones()));

			// 发送普通短信
			ResBodyData data = smsService.sendSmsMessage(model);
			if (data.getStatus() != 0) {
				// can not reach
				return new ResultBody(ResultBody.FAILED, data.getMsg());
			} else {
				return new ResultBody(ResultBody.SUCCESS, ResultBody.SUCCESS_MSG);
			}
		} catch (ServiceException e) {
			// 业务异常
			logger.error("发送普通短信,业务异常: " + e);
			return new ResultBody(ResultBody.FAILED, e.getMessage());
		} catch (Exception e) {
			// 未捕获的异常
			logger.error("发送普通短信,未捕获的异常: " + e);
			return new ResultBody(ResultBody.FAILED, SmsApiCode.getZhMsg(SmsApiCode.UNKNOW_ERROR));
		}
	}

	/**
	 * 发送短信验证码
	 *
	 * @param model
	 *            请求参数封装的SendCodeRequest对象
	 * @return 发送结果和验证码
	 */
	@RequestMapping("/send_sms_verification_code")
	public ResultBody oldSendSmsVerificationCode(SendCodeRequest model) {
		try {
			// 参数校验
			if (model == null) {
				return new ResultBody(ResultBody.FAILED, SmsApiCode.getZhMsg(SmsApiCode.REQUEST_PARAMS_ERROR));
			}
			if (Strings.isNullOrEmpty(model.getPhones()) || Strings.isNullOrEmpty(model.getTemplateId())) {
				return new ResultBody(ResultBody.FAILED, SmsApiCode.getZhMsg(SmsApiCode.REQUEST_PARAMS_ERROR));
			}

			// 过滤手机号+86字符
			model.setPhones(PhoneUtil.formatParamsPhones(model.getPhones()));

			// 发送短信验证码
			ResBodyData data = smsService.sendSmsVerificationCode(model);
			if (data.getStatus() != 0) {
				// can not reach
				return new ResultBody(ResultBody.FAILED, data.getMsg());
			} else {
				return new ResultBody(ResultBody.SUCCESS, ResultBody.SUCCESS_MSG);
			}
		} catch (ServiceException e) {
			// 业务异常
			logger.error("发送短信验证码,业务异常: " + e);
			return new ResultBody(ResultBody.FAILED, e.getMessage());
		} catch (Exception e) {
			// 未捕获的异常
			logger.error("发送短信验证码,未捕获的异常: " + e);
			return new ResultBody(ResultBody.FAILED, SmsApiCode.getZhMsg(SmsApiCode.UNKNOW_ERROR));
		}
	}

	/**
	 * 校验短信验证码
	 *
	 * @param model
	 *            请求参数封装的CheckCodeRequest对象
	 * @return 校验结果
	 */
	@RequestMapping("/check_sms_verification_code")
	public ResultBody oldCheckSmsVerificationCode(CheckCodeRequest model) {
		try {
			// 参数校验
			if (model == null) {
				return new ResultBody(ResultBody.FAILED, SmsApiCode.getZhMsg(SmsApiCode.REQUEST_PARAMS_ERROR));
			}
			if (Strings.isNullOrEmpty(model.getPhones()) || Strings.isNullOrEmpty(model.getTemplateId())
					|| Strings.isNullOrEmpty(model.getVerificationCode())) {
				return new ResultBody(ResultBody.FAILED, SmsApiCode.getZhMsg(SmsApiCode.REQUEST_PARAMS_ERROR));
			}

			// 过滤手机号+86字符
			model.setPhones(PhoneUtil.formatParamsPhones(model.getPhones()));

			// 结果校验
			ResBodyData data = smsService.checkSmsVerificationCode(model);
			if (data.getStatus() != 0) {
				// can not reach
				return new ResultBody(ResultBody.FAILED, data.getMsg());
			} else {
				return new ResultBody(ResultBody.SUCCESS, ResultBody.SUCCESS_MSG);
			}
		} catch (ServiceException e) {
			// 业务异常
			logger.error("校验短信验证码,业务异常: " + e);
			return new ResultBody(ResultBody.FAILED, e.getMessage());
		} catch (Exception e) {
			// 未捕获的异常
			logger.error("校验短信验证码,未捕获的异常: " + e);
			return new ResultBody(ResultBody.FAILED, SmsApiCode.getZhMsg(SmsApiCode.UNKNOW_ERROR));
		}
	}
}
