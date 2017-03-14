package com.meiduimall.service.sms.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.service.sms.model.message.CommonShortMessageModel;
import com.meiduimall.service.sms.service.SmsService;
import com.meiduimall.support.core.util.StringUtil;


/**
 * 公共短信发送服务
 * @author lin
 * @date  2017-01-12
 */

@RestController
@RequestMapping("notify/short_msg_service/v1")
public class SmsController {
	@Autowired
	private SmsService smsService;

	/**
	 * 发送短信
	 * @param model
	 * @return
	 */
	@RequestMapping("/send_common_sms_message")
	public ResultBody sendSmsMessage(CommonShortMessageModel model) {
		
		ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
		
		if(null == model){
			result = new ResultBody(ResultBody.FAILED, "参数不能为空");
			return result;
		}
		ResultBody rb = smsService.validSmsMessageParams(model);
		if(rb.getStatus_code().equals(ResultBody.FAILED)){
			result = new ResultBody(ResultBody.FAILED, rb.getResult_msg());
			return result;
		}else{
			if(null == rb.getData()){
				result = new ResultBody(ResultBody.FAILED, "参数格式错误");
				return result;
			}
			try {
				result = smsService.sendSmsMessage((CommonShortMessageModel) rb.getData());
			} catch (Exception e) {
				result = new ResultBody(ResultBody.FAILED, e.getMessage());
			}
		}
		
		
		return result;
	}
	
	/**
	 * 发送短信验证码
	 * @param model
	 * @return
	 */
	@RequestMapping("/send_sms_verification_code")
	public ResultBody sendSmsVerificationCode(String clientID ,CommonShortMessageModel model) {
		
		
		ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
		if(StringUtil.isEmptyByString(clientID)){
			result = new ResultBody(ResultBody.FAILED, "客户端来源参数不能为空");
			return result;
		}
		if(null == model){
			result = new ResultBody(ResultBody.FAILED, "参数不能为空");
			return result;
		}
		ResultBody rb = smsService.validVerificationCodeParams(model);
		if(rb.getStatus_code().equals(ResultBody.FAILED)){
			result = new ResultBody(ResultBody.FAILED, rb.getResult_msg());
			return result;
		}else{
			if(null == rb.getData()){
				result = new ResultBody(ResultBody.FAILED, "参数格式错误");
				return result;
			}
			try {
				result = smsService.sendSmsVerificationCode((CommonShortMessageModel) rb.getData());
			} catch (Exception e) {
				result = new ResultBody(ResultBody.FAILED, e.getMessage());
			}
		}
		

		return result;
	}
	
	/**
	 * 校验短信验证码
	 * @param model
	 * @return
	 */
	@RequestMapping("/check_sms_verification_code")
	public ResultBody checkSmsVerificationCode(String clientID ,CommonShortMessageModel model) {
		

		ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
		
		if(StringUtil.isEmptyByString(clientID)){
			result = new ResultBody(ResultBody.FAILED, "客户端来源参数不能为空");
			return result;
		}
		if(null == model){
			result = new ResultBody(ResultBody.FAILED, "参数不能为空");
			return result;
		}
		ResultBody rb = smsService.validCheckVerificationCodeParams(model);
		if(rb.getStatus_code().equals(ResultBody.FAILED)){
			result = new ResultBody(ResultBody.FAILED, rb.getResult_msg());
			return result;
		}else{
			if(null == rb.getData()){
				result = new ResultBody(ResultBody.FAILED, "参数格式错误");
				return result;
			}
			try {
				result = smsService.checkSmsVerificationCode((CommonShortMessageModel) rb.getData());
			} catch (Exception e) {
				result = new ResultBody(ResultBody.FAILED, e.getMessage());
			}
		}
	
		return result;
	}

}
