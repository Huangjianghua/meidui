<<<<<<< HEAD
package com.meiduimall.application.mall.catalog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.service.CouponService;
import com.meiduimall.application.mall.config.MyProps;
import com.meiduimall.application.mall.constant.MallConstant;
import com.meiduimall.application.mall.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private MyProps myProps;

	@Override
	public ResBodyData getAllCouponRule() {
		String clientID = myProps.getSignClientID();
		String signKey = myProps.getSignKey();
		String host = myProps.getRouteServiceUrl();
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/coupon/getAllRule";
		String url = host + uri;

		return HttpGatewayUtils.sendGet(url, clientID, signKey, null);
	}
}
=======
package com.meiduimall.application.mall.catalog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.service.CouponService;
import com.meiduimall.application.mall.config.ProfileConfig;
import com.meiduimall.application.mall.constant.MallConstant;
import com.meiduimall.application.mall.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private ProfileConfig profileConfig;

	@Override
	public ResBodyData getAllCouponRule() {
		String clientID = profileConfig.getClientId();
		String signKey = profileConfig.getSingKey();
		String host = profileConfig.getHost();
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/coupon/getAllRule";
		String url = host + uri;

		return HttpGatewayUtils.sendGet(url, clientID, signKey, null);
	}
}
>>>>>>> refs/remotes/origin/feature/V4.0.2-Team2
