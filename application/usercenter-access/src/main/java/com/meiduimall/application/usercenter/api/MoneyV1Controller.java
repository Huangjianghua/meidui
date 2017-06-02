package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meiduimall.application.usercenter.annotation.HasToken;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.MoneyService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;

/**
 * 余额相关API
 * @author chencong
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
	
	/**
	 * 获取会员账户余额和积分余额---给旧会员系统调用，要注意返回参数的兼容问题
	 * 
	 * @return
	 */
	@HasToken
	@RequestMapping(value = "/getAccountBalanceForApp")
	public String getAccountBalanceForApp() {
		try {
			JSONObject reqJson = ValRequest.apiReqData.get();
			logger.info("收到提现申请API请求：{}", reqJson.toString());
			String result = moneyService.getAccountBalanceForApp(reqJson);
			logger.info("提现申请API请求结果：{}", result);
			return result;
		} catch (MdSysException e) {
			logger.info("提现申请API请求异常：{}", e.toString());
			ObjectNode rootNode = JsonUtils.getInstance().createObjectNode();
			rootNode.put("status_code", String.valueOf(ConstApiStatus.SYSTEM_ERROR));
			rootNode.put("result_msg", ConstApiStatus.getZhMsg(ConstApiStatus.SYSTEM_ERROR));
			return rootNode.toString();
		}
	}
}
