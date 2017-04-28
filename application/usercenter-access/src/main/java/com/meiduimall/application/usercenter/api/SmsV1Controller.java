package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.interceptor.ValInterceptor;
import com.meiduimall.application.usercenter.interceptor.ValRequest;	
import com.meiduimall.application.usercenter.service.PayPwdService;
import com.meiduimall.application.usercenter.service.SmsService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.ServiceException;

/**
 * 短信相关接口
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class SmsV1Controller {
	
	private static Logger logger = LoggerFactory.getLogger(SmsV1Controller.class);
	
	@Autowired
	private SmsService smsService;

	/**获取短信验证码*/
	@HasToken
	@RequestMapping(value="/get_validate_code")
	ResBodyData validatePayPwd(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		resBodyData=ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("获取短信验证码API请求：{}",reqJson.toString());
		try {
			resBodyData=smsService.getValidatCode(reqJson);
		} catch (ServiceException e) {
			logger.error("获取短信验证码处理异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.GET_VALIDATE_CODE_EXCEPTION);
		}
		logger.info("获取短信验证码API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
}
