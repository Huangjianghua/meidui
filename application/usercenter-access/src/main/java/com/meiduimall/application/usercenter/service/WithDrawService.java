package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

public interface WithDrawService {

	/**
	 * 提现明细
	 * @param reqJson
	 * @return ResBodyData
	 */
	ResBodyData queryWithDrawDetail(JSONObject reqJson)throws MdSysException ;
	
	/**
	 * 查询提现手续费
	 * @param reqJson
	 * @return
	 * @throws MdSysException
	 * @author: jianhua.huang  2017年6月15日 下午12:22:05
	 */
	ResBodyData getWithDrawPoundage(JSONObject reqJson)throws MdSysException ;
}
