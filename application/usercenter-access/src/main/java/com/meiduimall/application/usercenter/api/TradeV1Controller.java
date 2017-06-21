package com.meiduimall.application.usercenter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.interceptor.ValRequest;
import com.meiduimall.application.usercenter.service.TradeService;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;

import io.swagger.annotations.ApiOperation;

/**
 * 会员订单相关
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class TradeV1Controller {

	private static Logger logger = LoggerFactory.getLogger(TradeV1Controller.class);
	
	@Autowired
	private TradeService memberOrderService;
	
	/**
	 * 当前商家退会员订单信息接口  http://IP:PORT/Authorized/BusinessRecedeOrder
	 * @return ResBodyData
	 */
	@ApiOperation(value="当前商家退会员订单信息接口", notes="")
	@RequestMapping(value="/business_recede_order")
	ResBodyData businessRecedeOrder(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到 当前商家退会员订单信息接口  API请求：{}",reqJson.toString());
		try {
			resBodyData=memberOrderService.businessRecedeOrder(reqJson);
		} catch (Exception e) {
			logger.info("当前商家退会员订单信息接口 API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("当前商家退会员订单信息接口 API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**
	 * 保存当前会员订单信息接口(免token校验) http://IP:PORT/Authorized/saveOrderNotoken
	 * @return ResBodyData
	 */
	@ApiOperation(value="保存当前会员订单信息接口(免token校验)", notes="")
	@RequestMapping(value="/save_order_notoken")
	ResBodyData saveOrderNotoken(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到  保存当前会员订单信息接口(免token校验) API请求：{}",reqJson.toString());
		try {
			resBodyData=memberOrderService.saveOrderNotoken(reqJson);
		} catch (Exception e) {
			logger.info("保存当前会员订单信息接口(免token校验) API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("保存当前会员订单信息接口(免token校验) API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**
	 * 当前会员退单信息接口  http://IP:PORT/Authorized/RecedeOrder
	 * @return ResBodyData
	 */
	@ApiOperation(value="当前会员退单信息接口", notes="")
	@RequestMapping(value="/recede_order")
	ResBodyData recedeOrder(){
		ResBodyData resBodyData=null;
		JSONObject reqJson=ValRequest.apiReqData.get();
		logger.info("收到 当前会员退单信息接口  API请求：{}",reqJson.toString());
		try {
			resBodyData=memberOrderService.recedeOrder(reqJson);
		} catch (Exception e) {
			logger.info("当前会员退单信息接口 API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("当前会员退单信息接口 API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
}
