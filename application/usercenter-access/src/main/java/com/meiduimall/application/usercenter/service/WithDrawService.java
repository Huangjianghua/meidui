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
	
}
