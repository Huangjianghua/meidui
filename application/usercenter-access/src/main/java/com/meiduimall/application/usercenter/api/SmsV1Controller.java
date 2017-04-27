package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.interceptor.ValInterceptor;
import com.meiduimall.application.usercenter.interceptor.ValRequest;	
import com.meiduimall.application.usercenter.service.PayPwdService;
import com.meiduimall.core.ResBodyData;

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
	private PayPwdService payPwdService;

	/**验证支付密码*/
	@HasToken
	@RequestMapping(value="/valide_pay_pwd")
	ResBodyData validatePayPwd(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		resBodyData=ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("收到验证支付密码API请求：{}",reqJson.toString());
		resBodyData=payPwdService.validePaypwd(reqJson);
		logger.info("验证支付密码API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	
}
