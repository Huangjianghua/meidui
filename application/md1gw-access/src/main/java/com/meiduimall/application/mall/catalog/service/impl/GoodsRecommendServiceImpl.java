package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.service.GoodsRecommendService;
import com.meiduimall.application.mall.config.MyProps;
import com.meiduimall.application.mall.constant.MallConstant;
import com.meiduimall.application.mall.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;

/**
 * 商品推荐服务
 * 
 * @author yangchang
 *
 */
@Service
public class GoodsRecommendServiceImpl implements GoodsRecommendService {

	@Autowired
	private MyProps myProps;

	@Override
	public ResBodyData getFirstRecommendGoodsHttp(int type, int sourceId) {

		String clientID = myProps.getSignClientID();
		String signKey = myProps.getSignKey();
		String host = myProps.getRouteServiceUrl();
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/goodsRecommend/getFirstRecommend";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("type", String.valueOf(type));
		params.put("sourceId", String.valueOf(sourceId));

		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}
}
