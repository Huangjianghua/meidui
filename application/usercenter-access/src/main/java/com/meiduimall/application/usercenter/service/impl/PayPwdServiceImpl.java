package com.meiduimall.application.usercenter.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.meiduimall.exception.ServiceException;
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
	public ResBodyData validePaypwd(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceAccountUrl()+"v1/valide_pay_pwd";
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("请求账户服务>>验证支付密码   URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.form(url,reqJson);
			logger.info("请求账户服务>>验证支付密码，结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("请求账户服务异常:{}",e.toString());
			resBodyData.setStatus(ApiStatusConst.REQUEST_GATEWAY_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_GATEWAY_EX));
		}
		return resBodyData;
	}

	@Override
	public ResBodyData setPaypwd(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceAccountUrl()+"v1/set_pay_pwd";
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("请求账户服务>>设置支付密码   URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.form(url,reqJson);
			logger.info("请求账户服务>>设置支付密码，结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("请求账户服务异常:{}",e.toString());
			resBodyData.setStatus(ApiStatusConst.REQUEST_GATEWAY_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_GATEWAY_EX));
		}
		return resBodyData;
	}

	@Override
	public ResBodyData setPaypwdStatus(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceMemberUrl()+"v1/set_paypwd_status";
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("请求账户服务>>设置支付密码开关状态   URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>	 ();
			headers.put(SysParamsConst.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("请求账户服务>>设置支付密码开关状态，结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("请求账户服务异常:{}",e.toString());
			resBodyData.setStatus(ApiStatusConst.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}

	@Override
	public ResBodyData updatePaypwd(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		
		String url=profile.getServiceAccountUrl()+"v1/update_pay_pwd";//获取修改支付密码服务地址
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());//重新生成签名
		if(resBodyData.getStatus()!=0){
			throw new ServiceException(ApiStatusConst.GET_SIGN_EX);
		}
		
		logger.info("请求账户服务>>修改支付密码    URL:{}  DATA:{}",url,reqJson.toString());
		Map<String, String> headers=new HashMap<>();
		headers.put(SysParamsConst.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
		try {
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("请求账户服务>>修改支付密码，结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("请求账户服务>>修改支付密码 异常：{}",e.toString());
			throw new ServiceException(ApiStatusConst.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}
	
}
