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
import com.meiduimall.application.usercenter.service.ExternalRechargeService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;
@Service
public class ExternalRechargeServiceImpl implements ExternalRechargeService{
	
	private static Logger logger = LoggerFactory.getLogger(ExternalRechargeServiceImpl.class);
	
	@Autowired
	private ProfileParamsConfig profile;

	@Override
	public ResBodyData externalMemberRecharge(JSONObject reqJson) {
		ResBodyData resBodyData = new ResBodyData(null,null);
		String url = profile.getServiceMemberUrl()+"v1/thereexist";
		String urls = profile.getServiceAccountUrl()+"v1/rechargeApply";
		resBodyData = MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0){
			return resBodyData;
		}
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(SysParamsConst.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result = HttpUtils.post(url,reqJson.toString(),headers);
			resBodyData = JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			resBodyData.setStatus(ApiStatusConst.REQUEST_GATEWAY_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_GATEWAY_EX));
		}
		JSONObject s = (JSONObject) resBodyData.getData();
		reqJson.put("memId", s.get("memId"));
		
		resBodyData = MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0){
			return resBodyData;
		}
		
		if("0".equals(resBodyData.getStatus().toString())){
			try {
				Map<String, String> headers=new HashMap<>();
				headers.put(SysParamsConst.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
				String result = HttpUtils.post(urls,reqJson.toString(),headers);
				resBodyData = JSON.parseObject(result,ResBodyData.class);
			} catch (Exception e) {
				resBodyData.setStatus(ApiStatusConst.REQUEST_GATEWAY_EX);
				resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_GATEWAY_EX));
			}
		}

		logger.info("----resBodyData");
		return resBodyData;
	}
}
