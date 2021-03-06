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
import com.meiduimall.application.usercenter.constant.ResBodyDataShiPei;
import com.meiduimall.application.usercenter.constant.ConstSysParamsDefination;
import com.meiduimall.application.usercenter.service.BaseOpService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

@Service
public class BaseOpServiceImpl implements BaseOpService {
	
	private static Logger logger = LoggerFactory.getLogger(BaseOpService.class);
	
	@Autowired
	private ProfileParamsConfig profile;

	@Override
	public ResBodyData login(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceMemberUrl()+"v1/login";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账号服务>>登录API>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(ConstSysParamsDefination.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("调用账号服务>>登录API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
			//服务层0表示登录成功，但是旧APP是用1来表示成功的，故做此适配
			if(resBodyData.getStatus()==Constants.CONSTANT_INT_ZERO){
				resBodyData.setStatus(Constants.CONSTANT_INT_ONE);
			}
		} catch (Exception e) {
			logger.error("请求账号服务>>登录API>>异常：{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}
	
	@Override
	public ResBodyData register(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceMemberUrl()+"v1/register";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账号服务>>普通会员注册>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(ConstSysParamsDefination.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("调用账号服务>>普通会员注册>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账号服务>>普通会员注册>>异常：{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}

	@Override
	public ResBodyDataShiPei getPut(JSONObject reqJson) throws MdSysException {
		ResBodyDataShiPei resBodyDataShiPei=new ResBodyDataShiPei(null,null);
		String url=profile.getServiceMemberUrl()+"v1/getput";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账号服务>>getput>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.get(url,reqJson);
			logger.info("调用账号服务>>getput>>结果：{}",result);
			resBodyDataShiPei=JSON.parseObject(result,ResBodyDataShiPei.class);
		} catch (Exception e) {
			logger.error("调用账号服务>>getput>>异常：{}",e.toString());
			resBodyDataShiPei.setStatus_code(ConstApiStatus.REQUEST_GATEWAY_EX.toString());
			resBodyDataShiPei.setResult_msg(ConstApiStatus.getZhMsg(ConstApiStatus.REQUEST_GATEWAY_EX));
		}
		return resBodyDataShiPei;
	}

	@Override
	public ResBodyDataShiPei handleSignOut(JSONObject reqJson) throws MdSysException {
		ResBodyDataShiPei resBodyDataShiPei=new ResBodyDataShiPei(null,null);
		String url=profile.getServiceMemberUrl()+"v1/handlesignout";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账号服务>>handlesignout>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.get(url,reqJson);
			logger.info("调用账号服务>>handlesignout>>结果：{}",result);
			resBodyDataShiPei=JSON.parseObject(result,ResBodyDataShiPei.class);
		} catch (Exception e) {
			logger.error("调用账号服务>>handlesignout>>异常：{}",e.toString());
			resBodyDataShiPei.setStatus_code(ConstApiStatus.REQUEST_GATEWAY_EX.toString());
			resBodyDataShiPei.setResult_msg(ConstApiStatus.getZhMsg(ConstApiStatus.REQUEST_GATEWAY_EX));
		}
		return resBodyDataShiPei;
	}

}
