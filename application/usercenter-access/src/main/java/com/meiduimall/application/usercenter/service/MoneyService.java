package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;


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
	 * @throws MdSysException 系统异常
	 */
	ResBodyData listAccountDetail(JSONObject reqJson) throws MdSysException;
	
	/**
	 * 提现申请
	 * @param reqJson
	 * @return
	 * @author: jianhua.huang  2017年5月4日 上午10:40:36
	 * @throws MdSysException 系统异常
	 */
	ResBodyData saveWithDrawApply(JSONObject reqJson) throws MdSysException;
}
