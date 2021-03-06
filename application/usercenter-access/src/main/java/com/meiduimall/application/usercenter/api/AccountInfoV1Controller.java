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
import com.meiduimall.application.usercenter.service.AccountInfoService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;


/**
 * 账户信息相关API
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class AccountInfoV1Controller {
	
	private static Logger logger = LoggerFactory.getLogger(AccountInfoV1Controller.class);
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	/**查询当前会员可提现余额*/
	@HasToken
	@GetMapping(value = "/get_allow_withdraw_balance")
	ResBodyData getAllowWithdrawBalance(){	
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到查询当前会员可提现余额API请求：{}",reqJson.toString());
		try {
			return accountInfoService.getAllowWithdrawBalance(reqJson);
		} catch (MdSysException e) {
			logger.error("查询当前会员可提现余额API异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
	}
	
}
