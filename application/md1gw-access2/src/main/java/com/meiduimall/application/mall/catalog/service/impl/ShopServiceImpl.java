package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.request.ShopProductRequest;
import com.meiduimall.application.mall.catalog.service.ShopService;
import com.meiduimall.application.mall.catalog.util.HttpGatewayUtils;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.exception.ApiException;

/**
 * MDShopController 网络请求工具类
 * 
 * @author yangchang
 *
 */
@Service
public class ShopServiceImpl implements ShopService {

	private static Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	private Environment env;

	@Override
	public String getShopDetailHttp(int shop_id, String mem_id) {

		String uri = "/mall/catalog-service/v1/shopInfo/getShopDetail";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shop_id", String.valueOf(shop_id));
		params.put("mem_id", "" + mem_id);
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ApiException(BaseApiCode.REQUEST_SERVICE_ERROR);
		}
	}

	@Override
	public String collectOrCancelShopHttp(int shop_id, int is_collect, String mem_id) {

		String uri = "/mall/catalog-service/v1/shopInfo/collectShop";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shop_id", String.valueOf(shop_id));
		params.put("mem_id", "" + mem_id);
		params.put("is_collect", String.valueOf(is_collect));
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ApiException(BaseApiCode.REQUEST_SERVICE_ERROR);
		}
	}

	@Override
	public String getShopProductCatalogHttp(int shop_id) {
		String uri = "/mall/catalog-service/v1/shopInfo/getShopCatalog";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shop_id", String.valueOf(shop_id));
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ApiException(BaseApiCode.REQUEST_SERVICE_ERROR);
		}
	}

	@Override
	public String getShopProductList(ShopProductRequest param) {
		String uri = "/mall/catalog-service/v1/shopInfo/getProductList";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shop_id", String.valueOf(param.getShop_id()));
		if (param.getShop_cat_id() != null) {
			params.put("shop_cat_id", String.valueOf(param.getShop_cat_id()));
		}
		if (param.getPageNo() != null) {
			params.put("pageNo", String.valueOf(param.getPageNo()));
		}
		if (param.getPageSize() != null) {
			params.put("pageSize", String.valueOf(param.getPageSize()));
		}
		if (!StringUtils.isBlank(param.getOrder_by())) {
			params.put("order_by", param.getOrder_by());
		}
		if (!StringUtils.isBlank(param.getColumn())) {
			params.put("column", param.getColumn());
		}

		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ApiException(BaseApiCode.REQUEST_SERVICE_ERROR);
		}
	}
}
