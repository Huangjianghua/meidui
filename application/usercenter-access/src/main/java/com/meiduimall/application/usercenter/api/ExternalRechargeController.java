package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.interceptor.ValInterceptor;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.ExternalRechargeService;
import com.meiduimall.core.ResBodyData;
/**
 * 外部会员充值接口
 * @author liuhailang
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class ExternalRechargeController {
	
	private static Logger logger = LoggerFactory.getLogger(ExternalRechargeController.class);
	@Autowired
	private ExternalRechargeService externalRechargeService;
	
	/**外部充值*/
	@RequestMapping(value = "/externalRecharge")
	public ResBodyData externalMemberRecharge(){
		ResBodyData resBodyData=null;
		JSONObject reqJson = ValRequest.apiReqData.get();
		resBodyData = ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0){
			return resBodyData;
		}
		
		logger.info("收到外部充值API请求：{}",reqJson.toString());
		resBodyData=externalRechargeService.externalMemberRecharge(reqJson);
		logger.info("外部充值API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
}
