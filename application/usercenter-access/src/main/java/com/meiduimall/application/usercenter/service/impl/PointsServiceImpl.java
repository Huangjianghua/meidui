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
import com.meiduimall.application.usercenter.constant.ConstSysParamsDefination;
import com.meiduimall.application.usercenter.service.PointsService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

@Service
public class PointsServiceImpl implements PointsService  {
	
	private static Logger logger = LoggerFactory.getLogger(PointsServiceImpl.class);
	
	@Autowired
	private ProfileParamsConfig profile;
	

	@Override
	public ResBodyData listConsumePointsDetail(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceAccountUrl()+"v1/list_consume_points_detail";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("调用账户服务>>积分流水（分页）API>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(ConstSysParamsDefination.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("调用账户服务>>积分流水（分页）API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("调用账户服务>>积分流水（分页）API>>异常：{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}
	
}
