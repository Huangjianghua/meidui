package com.meiduimall.application.usercenter.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.config.ProfileParamsConfig;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.constant.ConstSysParamsDefination;
import com.meiduimall.application.usercenter.service.MoneyService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;

@Service
public class MoneyServiceImpl implements MoneyService  {
	
	private static Logger logger = LoggerFactory.getLogger(MoneyServiceImpl.class);
	
	@Autowired
	private ProfileParamsConfig profile;

	@Override
	public ResBodyData listAccountDetail(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceAccountUrl()+"v1/list_account_detail";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账户服务>>余额流水（分页）API>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(ConstSysParamsDefination.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("调用账户服务>>余额流水（分页）API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账户服务>>余额流水（分页）API>>异常:{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}

	@Override
	public ResBodyData saveWithDrawApply(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceAccountUrl()+"v1/save_withdraw";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账户服务>>提现申请API>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(ConstSysParamsDefination.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("调用账户服务>>提现申请API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账户服务>>提现申请API>>异常:{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}

	@Override
	public String getAccountBalanceForApp(JSONObject reqJson) throws MdSysException {
		try {
			String url = profile.getServiceAccountUrl() + "v1/getAccountBalanceForApp_old";
			MD5Utils.updateSign(reqJson, profile.getRouteClientID(), profile.getRouteKey());
			Map<String, String> headers = new HashMap<>();
			headers.put(ConstSysParams.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			return HttpUtils.post(url, reqJson.toString(), headers);
		} catch (Exception e) {
			logger.error("调用账户服务>>提现申请API>>异常:{}", e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
	}

}
