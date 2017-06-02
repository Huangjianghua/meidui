package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.MoneyService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;


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
		logger.info("收到余额流水分页API请求：{}",reqJson.toString());
		try {
			resBodyData=moneyService.listAccountDetail(reqJson);
		} catch (MdSysException e) {
			logger.info("余额流水分页API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("余额流水分页API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**提现申请*/
	@HasToken
	@RequestMapping(value="/save_withdraw")
	ResBodyData saveWithDrawApply(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到提现申请API请求：{}",reqJson.toString());
		try {
			resBodyData=moneyService.saveWithDrawApply(reqJson);
		} catch (Exception e) {
			logger.info("提现申请API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("提现申请API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
}
