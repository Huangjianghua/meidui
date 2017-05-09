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
import com.meiduimall.application.usercenter.constant.ResBodyDataShiPei;
import com.meiduimall.application.usercenter.constant.SysParamsConst;
import com.meiduimall.application.usercenter.service.BaseOpService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;

@Service
public class BaseOpServiceImpl implements BaseOpService {
	
	private static Logger logger = LoggerFactory.getLogger(BaseOpService.class);
	
	@Autowired
	private ProfileParamsConfig profile;

	@Override
	public ResBodyData login(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceMemberUrl()+"v1/login";
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("请求账号服务>>登录>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(SysParamsConst.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("请求账号服务>>登录>>RESULT：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("请求账号服务>>登录>>EXCEPTION：{}",e.toString());
			resBodyData.setStatus(ApiStatusConst.REQUEST_GATEWAY_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_GATEWAY_EX));
		}
		return resBodyData;
	}
	
	@Override
	public ResBodyData register(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceMemberUrl()+"v1/register";
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("请求账号服务>>普通会员注册>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(SysParamsConst.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("请求账号服务>>普通会员注册>>RESULT：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("请求账号服务>>普通会员注册>>EXCEPTION：{}",e.toString());
			resBodyData.setStatus(ApiStatusConst.REQUEST_GATEWAY_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_GATEWAY_EX));
		}
		return resBodyData;
	}

	@Override
	public ResBodyDataShiPei getPut(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(null,null);
		ResBodyDataShiPei resBodyDataShiPei=new ResBodyDataShiPei(null,null);
		String url=profile.getServiceMemberUrl()+"v1/getput";
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0){
			resBodyDataShiPei.setStatus_code(resBodyData.getStatus().toString());
			resBodyDataShiPei.setResult_msg(resBodyData.getMsg());
			return resBodyDataShiPei;
		}
		logger.info("请求账号服务，URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.get(url,reqJson);
			logger.info("请求账号服务，结果：{}",result);
			resBodyDataShiPei=JSON.parseObject(result,ResBodyDataShiPei.class);
		} catch (Exception e) {
			logger.error("请求账号服务异常：{}",e.toString());
			resBodyDataShiPei.setStatus_code(ApiStatusConst.REQUEST_GATEWAY_EX.toString());
			resBodyDataShiPei.setResult_msg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_GATEWAY_EX));
		}
		return resBodyDataShiPei;
	}

	@Override
	public ResBodyDataShiPei handleSignOut(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(null,null);
		ResBodyDataShiPei resBodyDataShiPei=new ResBodyDataShiPei(null,null);
		String url=profile.getServiceMemberUrl()+"v1/handlesignout";
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0){
			resBodyDataShiPei.setStatus_code(resBodyData.getStatus().toString());
			resBodyDataShiPei.setResult_msg(resBodyData.getMsg());
			return resBodyDataShiPei;
		}
		logger.info("请求账号服务，URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.get(url,reqJson);
			logger.info("请求账号服务，结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("请求账号服务异常：{}",e.toString());
			resBodyDataShiPei.setStatus_code(ApiStatusConst.REQUEST_GATEWAY_EX.toString());
			resBodyDataShiPei.setResult_msg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_GATEWAY_EX));
		}
		return resBodyDataShiPei;
	}

}
