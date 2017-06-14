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
import com.meiduimall.exception.MdSysException;

@Service
public class UserInfoServiceImpl implements UserInfoService  {
	
	private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	@Autowired
	private ProfileParamsConfig profile;

	@Override
	public ResBodyData getmemberbasicinfo(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceMemberUrl()+"v1/get_member_basic_info";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账号服务>>获取会员基本信息API>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.get(url,reqJson);
			logger.info("调用账号服务>>获取会员基本信息API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账号服务>>获取会员基本信息API>>异常:{}",e.toString());
			throw new MdSysException(ApiStatusConst.REQUEST_GATEWAY_EX);
		} 
		return resBodyData;
	}
	
}
