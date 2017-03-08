package com.meidui.shortmsg.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meidui.shortmsg.model.ResultBody;
import com.meidui.shortmsg.model.message.CommonShortMessageModel;
import com.meidui.shortmsg.service.ISmsService;
import com.meidui.shortmsg.util.Logger;
import com.meidui.shortmsg.util.StringUtil;

/**
 * 公共短信发送服务
 * @author lin
 * @date  2017-01-12
 */

@RestController
@RequestMapping("notify/short_msg_service")
public class SmsController {
	@Autowired
	private ISmsService smsService;

	/**
	 * 发送短信
	 * @param model
	 * @return
	 */
	@RequestMapping("v1/send_common_sms_message")
	public ResultBody sendSmsMessage(CommonShortMessageModel model) {
		
		Logger.info("进入发短信程序,参数为：%s" + ToStringBuilder.reflectionToString(model));
		
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
		Logger.info("结束发普通短信程序");
		
		return result;
	}
	
	/**
	 * 发送短信验证码
	 * @param model
	 * @return
	 */
	@RequestMapping("v1/send_sms_verification_code")
	public ResultBody sendSmsVerificationCode(String clientID ,CommonShortMessageModel model) {
		Logger.info("开始发短信验证码程序  %s" ,clientID + ToStringBuilder.reflectionToString(model));
		
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
		Logger.info("结束发短信验证码程序");

		return result;
	}
	
	/**
	 * 校验短信验证码
	 * @param model
	 * @return
	 */
	@RequestMapping("v1/check_sms_verification_code")
	public ResultBody checkSmsVerificationCode(String clientID ,CommonShortMessageModel model) {
		Logger.info("开始校验短信验证码程序  %s" ,clientID + ToStringBuilder.reflectionToString(model));

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
		Logger.info("结束校验短信验证码程序");
		return result;
	}

}
