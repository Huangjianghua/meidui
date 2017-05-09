package com.meiduimall.application.usercenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.config.ProfileParamsConfig;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.service.SmsService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;

@Service
public class SmsServiceImpl implements SmsService  {
	
	private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	private ProfileParamsConfig profile;


	@Override
	public ResBodyData getValidatCode(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		
		String url=profile.getServiceMemberUrl()+"v1/get_validate_code";
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0){
			throw new ServiceException(ApiStatusConst.GET_SIGN_EX);
		}
		logger.info("调用账号服务>>发送短信验证码API>>URL:{}  DATA:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.get(url,reqJson);
			logger.info("调用账号服务>>发送短信验证码API>>RESULT:{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用短信服务>>发送短信验证码 >>EXCEPTION：{}",e.toString());
			throw new ServiceException(ApiStatusConst.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}
	
}
