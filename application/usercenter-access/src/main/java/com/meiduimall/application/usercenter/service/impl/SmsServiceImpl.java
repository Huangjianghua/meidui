package com.meiduimall.application.usercenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.config.ProfileParamsConfig;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.service.SmsService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

@Service
public class SmsServiceImpl implements SmsService  {
	
	private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	private ProfileParamsConfig profile;


	@Override
	public ResBodyData getValidatCode(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		String url=profile.getServiceMemberUrl()+"v1/get_validate_code";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账号服务>>发送短信验证码API>>URL:{}  DATA:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.get(url,reqJson);
			logger.info("调用账号服务>>发送短信验证码API>>RESULT:{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用短信服务>>发送短信验证码 >>EXCEPTION：{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}
	
}
