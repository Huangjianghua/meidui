package com.meiduimall.application.usercenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.config.ProfileParamsConfig;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.service.UserInfoService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;

@Service
public class UserInfoServiceImpl implements UserInfoService  {
	
	private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	@Autowired
	private ProfileParamsConfig profile;

	@Override
	public ResBodyData getmemberbasicinfo(JSONObject reqJson) {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceMemberUrl()+"v1/get_member_basic_info";
		resBodyData=MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		logger.info("请求账号服务，URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.get(url,reqJson);
			logger.info("请求账号服务，结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("请求账号服务异常:{}",e.toString());
			resBodyData.setStatus(ApiStatusConst.REQUEST_GATEWAY_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_GATEWAY_EX));
		} 
		return resBodyData;
	}
	
}
