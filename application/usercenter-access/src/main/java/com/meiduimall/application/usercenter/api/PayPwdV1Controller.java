package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.interceptor.ValRequest;	
import com.meiduimall.application.usercenter.service.PayPwdService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;

/**
 * 支付密码相关API
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class PayPwdV1Controller {
	
	private static Logger logger = LoggerFactory.getLogger(PayPwdV1Controller.class);
	
	@Autowired
	private PayPwdService payPwdService;

	/**验证支付密码*/
	@HasToken
	@RequestMapping(value="/valide_pay_pwd")
	ResBodyData validatePayPwd(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到验证支付密码API请求：{}",reqJson.toString());
		try {
			resBodyData=payPwdService.validePaypwd(reqJson);
		} catch (MdSysException e) {
			logger.info("验证支付密码API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.SYSTEM_ERROR);
		}
		logger.info("验证支付密码API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}

	/**设置支付密码*/
	@HasToken
	@RequestMapping(value="/set_pay_pwd")
	ResBodyData setPayPwd(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到设置支付密码API请求：{}",reqJson.toString());
		try {
			resBodyData=payPwdService.setPaypwd(reqJson);
		} catch (MdSysException e) {
			logger.info("设置支付密码API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.SYSTEM_ERROR);
		}
		logger.info("验证设置密码API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**设置支付密码开关*/
	@HasToken
	@RequestMapping(value="/set_paypwd_status")
	ResBodyData setPaypwdStatus(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到设置支付密码开关API请求：{}",reqJson.toString());
		try {
			resBodyData=payPwdService.setPaypwdStatus(reqJson);
		} catch (MdSysException e) {
			logger.info("设置支付密码开关API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.SYSTEM_ERROR);
		}
		logger.info("设置支付密码开关API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**修改支付密码*/
	@HasToken
	@RequestMapping(value="/update_pay_pwd")
	ResBodyData updatePaypwd(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到修改支付密码API请求：{}",reqJson.toString());
		try {
			resBodyData=payPwdService.updatePaypwd(reqJson);
		} catch (MdSysException e) {
			logger.error("修改支付密码API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.SYSTEM_ERROR);
		}
		logger.info("修改支付密码API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**找回支付密码*/
	@HasToken
	@RequestMapping(value="/retrieve_pay_pwd")
	ResBodyData retrievePaypwd(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到找回支付密码API请求：{}",reqJson.toString());
		try {
			resBodyData=payPwdService.retrievePaypwd(reqJson);
		} catch (MdSysException e) {
			logger.error("找回支付密码API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.SYSTEM_ERROR);
		}
		logger.info("找回支付密码API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}

	
}
