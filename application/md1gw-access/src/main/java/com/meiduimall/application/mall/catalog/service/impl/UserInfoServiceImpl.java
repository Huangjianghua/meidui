package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.service.UserInfoService;
import com.meiduimall.application.mall.config.MyProps;
import com.meiduimall.application.mall.constant.MallConstant;
import com.meiduimall.application.mall.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private MyProps myProps;

	@Override
	public ResBodyData getUserInfoForApp(String memId, String token) {
		// 请求member-service获取会员基本信息
		String clientID = myProps.getSignClientID();
		String signKey = myProps.getSignKey();
		String host = myProps.getRouteServiceUrl();
		String uri = MallConstant.MEMBER_SERVCIE + "/get_member_simple_info";
		String url = host + uri;
		Map<String, String> params = new HashMap<String, String>();
		params.put("memId", memId);
		ResBodyData bodyData = HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		return null;
	}

}
