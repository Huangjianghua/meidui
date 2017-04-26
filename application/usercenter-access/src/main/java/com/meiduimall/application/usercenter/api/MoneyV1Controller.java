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
import com.meiduimall.application.usercenter.service.MoneyService;
import com.meiduimall.core.ResBodyData;


/**
 * 余额相关
 * @author jun.wu@meiduimall.com
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class MoneyV1Controller {
	
	private static Logger logger = LoggerFactory.getLogger(MoneyV1Controller.class);
	
	@Autowired
	private MoneyService moneyService;
	
	/**余额流水（分页）*/
	@HasToken
	@RequestMapping(value="/list_account_detail")
	ResBodyData listAccountDetail(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		resBodyData=ValInterceptor.apiValResult.get();
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("收到余额流水分页API请求：{}",reqJson.toString());
		resBodyData=moneyService.listAccountDetail(reqJson);
		logger.info("余额流水分页API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
}
