package com.meiduimall.application.usercenter.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.config.ProfileParamsConfig;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.constant.ConstSysParamsDefination;
import com.meiduimall.application.usercenter.service.WithDrawService;
import com.meiduimall.application.usercenter.util.HttpUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

@Component
public class WithDrawServiceImpl implements WithDrawService{

	private static Logger logger = LoggerFactory.getLogger(WithDrawServiceImpl.class);	 
	
	@Autowired
	private ProfileParamsConfig profile;

	
	@Override
	public ResBodyData queryWithDrawDetail(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceAccountUrl()+"v1/query_withdraw_detail";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("提现明细API>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(ConstSysParamsDefination.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result=HttpUtils.post(url,reqJson.toString(),headers);
			logger.info("提现明细API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("提现明细API>>异常:{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}


	/**
	 * @param reqJson
	 * @return
	 * @throws MdSysException
	 */
	@Override
	public ResBodyData getWithDrawPoundage(JSONObject reqJson) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String url=profile.getServiceAccountUrl()+"v1/get_withdraw_poundage";
		MD5Utils.updateSign(reqJson,profile.getRouteClientID(),profile.getRouteKey());
		logger.info("提现手续费API>>URL:{}  Data:{}",url,reqJson.toString());
		try {
			String result=HttpUtils.get(url, reqJson);
			logger.info("提现手续费API>>结果：{}",result);
			resBodyData=JSON.parseObject(result,ResBodyData.class);
		} catch (Exception e) {
			logger.error("提现手续费API>>异常:{}",e.toString());
			throw new MdSysException(ConstApiStatus.REQUEST_GATEWAY_EX);
		}
		return resBodyData;
	}

}
