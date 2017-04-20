package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.constant.ApplMallApiCode;
import com.meiduimall.application.mall.catalog.constant.ApplMallConstant;
import com.meiduimall.application.mall.catalog.request.ShopProductRequest;
import com.meiduimall.application.mall.catalog.service.ShopService;
import com.meiduimall.application.mall.catalog.util.HttpGatewayUtils;
import com.meiduimall.exception.ServiceException;

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
	public String getShopDetailHttp(int shopId, String memId) {

		String clientID = env.getProperty(ApplMallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(ApplMallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(ApplMallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = ApplMallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/getShopDetail";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", String.valueOf(shopId));
		if (!StringUtils.isBlank(memId)) {
			params.put("memId", memId);
		}
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ServiceException(ApplMallApiCode.REQUEST_SERVICE_ERROR,
					ApplMallApiCode.getZhMsg(ApplMallApiCode.REQUEST_SERVICE_ERROR));
		}
	}

	@Override
	public String collectOrCancelShopHttp(int shopId, int isCollect, String memId) {

		String clientID = env.getProperty(ApplMallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(ApplMallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(ApplMallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = ApplMallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/collectShop";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", String.valueOf(shopId));
		params.put("isCollect", String.valueOf(isCollect));
		if (!StringUtils.isBlank(memId)) {
			params.put("memId", memId);
		}
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ServiceException(ApplMallApiCode.REQUEST_SERVICE_ERROR,
					ApplMallApiCode.getZhMsg(ApplMallApiCode.REQUEST_SERVICE_ERROR));
		}
	}

	@Override
	public String getShopProductCatalogHttp(int shopId) {

		String clientID = env.getProperty(ApplMallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(ApplMallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(ApplMallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = ApplMallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/getShopCatalog";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", String.valueOf(shopId));
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ServiceException(ApplMallApiCode.REQUEST_SERVICE_ERROR,
					ApplMallApiCode.getZhMsg(ApplMallApiCode.REQUEST_SERVICE_ERROR));
		}
	}

	@Override
	public String getShopProductList(ShopProductRequest param) {

		String clientID = env.getProperty(ApplMallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(ApplMallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(ApplMallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = ApplMallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/getProductList";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", String.valueOf(param.getShopId()));
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

		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ServiceException(ApplMallApiCode.REQUEST_SERVICE_ERROR,
					ApplMallApiCode.getZhMsg(ApplMallApiCode.REQUEST_SERVICE_ERROR));
		}
	}
}
