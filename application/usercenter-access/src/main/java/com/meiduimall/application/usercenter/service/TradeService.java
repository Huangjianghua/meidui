package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

public interface TradeService {

	/**
	 * 当前商家退会员订单信息接口
	 * @param reqJson
	 * @return ResBodyData
	 */
	ResBodyData businessRecedeOrder(JSONObject reqJson)throws MdSysException ;
	
	/**
	 * 保存当前会员订单信息接口(免token校验)
	 * @param reqJson
	 * @return ResBodyData
	 */
	ResBodyData saveOrderNotoken(JSONObject reqJson)throws MdSysException ;
	
	/**
	 * 当前会员退单信息接口
	 * @param reqJson
	 * @return ResBodyData
	 */
	ResBodyData recedeOrder(JSONObject reqJson)throws MdSysException ;

	
	
}
