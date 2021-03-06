package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.request.ShopProductRequest;
import com.meiduimall.application.mall.catalog.service.ShopService;
import com.meiduimall.application.mall.config.MyProps;
import com.meiduimall.application.mall.constant.MallConstant;
import com.meiduimall.application.mall.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;

/**
 * 店铺相关服务
 * 
 * @author yangchang
 *
 */
@Service
public class ShopServiceImpl implements ShopService {

	private static final String SHOP_ID = "shopId";

	@Autowired
	private MyProps myProps;

	@Override
	public ResBodyData getShopDetailHttp(int shopId, String memId) {

		String clientID = myProps.getSignClientID();
		String signKey = myProps.getSignKey();
		String host = myProps.getRouteServiceUrl();
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/getShopDetail";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put(SHOP_ID, String.valueOf(shopId));
		if (!StringUtils.isBlank(memId)) {
			params.put("memId", memId);
		}
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}

	@Override
	public ResBodyData collectOrCancelShopHttp(int shopId, int isCollect, String memId) {

		String clientID = myProps.getSignClientID();
		String signKey = myProps.getSignKey();
		String host = myProps.getRouteServiceUrl();
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/collectShop";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put(SHOP_ID, String.valueOf(shopId));
		params.put("isCollect", String.valueOf(isCollect));
		if (!StringUtils.isBlank(memId)) {
			params.put("memId", memId);
		}
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}

	@Override
	public ResBodyData getShopProductCatalogHttp(int shopId) {

		String clientID = myProps.getSignClientID();
		String signKey = myProps.getSignKey();
		String host = myProps.getRouteServiceUrl();
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/getShopCatalog";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put(SHOP_ID, String.valueOf(shopId));
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}

	@Override
	public ResBodyData getShopProductList(ShopProductRequest param) {

		String clientID = myProps.getSignClientID();
		String signKey = myProps.getSignKey();
		String host = myProps.getRouteServiceUrl();
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/getProductList";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put(SHOP_ID, String.valueOf(param.getShopId()));
		if (param.getShopCatId() != null) {
			params.put("shopCatId", String.valueOf(param.getShopCatId()));
		}
		if (param.getPageNo() != null) {
			params.put("pageNo", String.valueOf(param.getPageNo()));
		}
		if (param.getPageSize() != null) {
			params.put("pageSize", String.valueOf(param.getPageSize()));
		}
		if (!StringUtils.isBlank(param.getSortBy())) {
			params.put("sortBy", param.getSortBy());
		}
		if (!StringUtils.isBlank(param.getColumn())) {
			params.put("column", param.getColumn());
		}
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}
}
