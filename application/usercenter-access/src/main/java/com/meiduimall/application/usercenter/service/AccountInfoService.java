package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * 会员账户信息业务逻辑接口
 * @author chencong
 *
 */
public interface AccountInfoService {

	/**
	 * 查询当前会员可提现余额
	 * @param reqJson 请求的数据
	 * @return 统一数据返回格式
	 * @throws MdSysException 系统异常
	 */
	 ResBodyData getAllowWithdrawBalance(JSONObject reqJson) throws MdSysException;
	
}
