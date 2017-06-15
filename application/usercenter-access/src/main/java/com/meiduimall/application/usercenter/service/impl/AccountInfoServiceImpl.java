package com.meiduimall.application.usercenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.config.ProfileParamsConfig;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.service.AccountInfoService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * 会员账户信息业务逻辑接口{@link=AccountInfoServiced}实现类
 * @author chencong
 *
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService  {
	
	private static Logger logger = LoggerFactory.getLogger(AccountInfoServiceImpl.class);
	
	@Autowired
	private ProfileParamsConfig profile;

	@Override
	public ResBodyData getAllowWithdrawBalance(JSONObject reqJson) throws MdSysException {
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		String url=profile.getServiceAccountUrl()+"v1/get_allow_withdraw_balance";
		logger.info("调用账户服务>>查询当前会员可提现余额API>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.get(url,reqJson);
			logger.info("调用账户服务>>查询当前会员可提现余额API>>结果：{}",result);
			return JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账户服务>>查询当前会员可提现余额API>>异常:{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		} 
	}
	
}
