package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.SmsService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;

/**
 * 短信相关API
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class SmsV1Controller {
	
	private static Logger logger = LoggerFactory.getLogger(SmsV1Controller.class);
	
	@Autowired
	private SmsService smsService;

	/**获取短信验证码，需要token*/
	@HasToken
	@GetMapping(value="/get_validate_code")
	ResBodyData getValidateCode(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到获取短信验证码API请求：{}",reqJson.toString());
		try {
			resBodyData=smsService.getValidatCode(reqJson);
		} catch (MdSysException e) {
			logger.error("获取短信验证码API处理异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("获取短信验证码API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**获取短信验证码，不需要token*/
	@GetMapping(value="/get_validate_code_notoken")
	ResBodyData getValidateCodeNoToken(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到获取短信验证码(不需要token)API请求：{}",reqJson.toString());
		try {
			resBodyData=smsService.getValidatCode(reqJson);
		} catch (MdSysException e) {
			logger.error("获取短信验证码(不需要token)API处理异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("获取短信验证码(不需要token)API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
}
