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
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.constant.SysParamsConst;
import com.meiduimall.application.usercenter.service.MoneyService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;

@Service
public class MoneyServiceImpl implements MoneyService  {
	
	private static Logger logger = LoggerFactory.getLogger(MoneyServiceImpl.class);
	
	@Autowired
	private ProfileParamsConfig profile;

	@Override
	public ResBodyData listAccountDetail(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceAccountUrl()+"v1/list_account_detail";
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("请求账户服务，URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(SysParamsConst.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("请求账户服务，结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("请求账户服务异常:{}",e.toString());
			resBodyData.setStatus(ApiStatusConst.REQUEST_GATEWAY_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_GATEWAY_EX));
		}
		return resBodyData;
	}


}
