package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.service.GoodsDetailService;
import com.meiduimall.application.mall.config.MyProps;
import com.meiduimall.application.mall.constant.MallConstant;
import com.meiduimall.application.mall.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;

/**
 * 商品详情服务
 * 
 * @author yangchang
 *
 */
@Service
public class GoodsDetailServiceImpl implements GoodsDetailService {

	@Autowired
	private MyProps myProps;

	@Override
	public ResBodyData getItemDetailHttp(int itemId, String memId) {

		String clientID = myProps.getSignClientID();
		String signKey = myProps.getSignKey();
		String host = myProps.getRouteServiceUrl();
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/goodsDetail/getItem";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("itemId", String.valueOf(itemId));
		if (!StringUtils.isBlank(memId)) {
			params.put("memId", memId);
		}
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}
}
