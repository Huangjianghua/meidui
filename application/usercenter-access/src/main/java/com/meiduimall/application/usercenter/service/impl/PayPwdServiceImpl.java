package com.meiduimall.application.usercenter.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.meiduimall.exception.MdSysException;
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
import com.meiduimall.application.usercenter.service.PayPwdService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;

@Service
public class PayPwdServiceImpl implements PayPwdService  {
	
	private static Logger logger = LoggerFactory.getLogger(PayPwdServiceImpl.class);

	@Autowired
	private ProfileParamsConfig profile;

	@Override
	public ResBodyData validePaypwd(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceAccountUrl()+"v1/valide_pay_pwd";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账户服务>>验证支付密码API>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.form(url,reqJson);
			logger.info("调用账户服务>>验证支付密码API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账户服务>>验证支付密码API>>异常:{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}

	@Override
	public ResBodyData setPaypwd(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceAccountUrl()+"v1/set_pay_pwd";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账户服务>>设置支付密码 API URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.form(url,reqJson);
			logger.info("调用账户服务>>设置支付密码API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账户服务>>设置支付密码API>>异常:{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}

	@Override
	public ResBodyData setPaypwdStatus(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceMemberUrl()+"v1/set_paypwd_status";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账户服务>>设置支付密码开关状态 API>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>	 ();
			headers.put(ConstSysParamsDefination.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("调用账户服务>>设置支付密码开关状态API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账户服务>>设置支付密码开关状态API>>异常:{}",e.toString());
			resBodyData.setStatus(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}

	@Override
	public ResBodyData updatePaypwd(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		String url=profile.getServiceAccountUrl()+"v1/update_pay_pwd";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账户服务>>修改支付密码API>>URL:{}  DATA:{}",url,reqJson.toString());
		Map<String, String> headers=new HashMap<>();
		headers.put(ConstSysParamsDefination.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
		try {
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("调用账户服务>>修改支付密码API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账户服务>>修改支付密码API>>异常：{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}
	
	@Override
	public ResBodyData retrievePaypwd(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		String url=profile.getServiceAccountUrl()+"v1/retrieve_pay_pwd";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账户服务>>找回支付密码API>>URL:{}  DATA:{}",url,reqJson.toString());
		Map<String, String> headers=new HashMap<>();
		headers.put(ConstSysParamsDefination.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
		try {
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("调用账户服务>>找回支付密码API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账户服务>>找回支付密码API>>异常：{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}
	
}
