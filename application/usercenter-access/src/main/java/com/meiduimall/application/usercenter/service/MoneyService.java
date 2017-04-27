package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;


/**
 * 余额操作接口
 * @author chencong
 *
 */
public interface MoneyService {

	/**
	 * 余额流水
	 * @param reqJson 请求的数据
	 * @return 统一数据返回格式
	 */
	ResBodyData listAccountDetail(JSONObject reqJson);
}