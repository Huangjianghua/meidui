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
import com.meiduimall.application.usercenter.service.WithDrawService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;

/**
 * 提现相关API
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class WithDrawV1Controller {

	private static Logger logger = LoggerFactory.getLogger(WithDrawV1Controller.class);
	
	@Autowired
	private WithDrawService withdrawService;
	
	/**
	 * 提现明细
	 * @return ResBodyData
	 */
	@HasToken
	@RequestMapping(value="/query_withdraw_detail")
	ResBodyData queryWithDrawDetail(){
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到提现明细API请求：{}",reqJson.toString());
		try {
			return withdrawService.queryWithDrawDetail(reqJson);
		} catch (MdSysException e) {
			logger.info("提现明细API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 查询提现手续费
	 * @return ResBodyData
	 */
	@HasToken
	@GetMapping(value="/get_withdraw_poundage")
	ResBodyData getWithDrawPoundage(){
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到查询提现手续费API请求：{}",reqJson.toString());
		try {
			return withdrawService.getWithDrawPoundage(reqJson);
		} catch (Exception e) {
			logger.info("查询提现手续费API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
	}
}
