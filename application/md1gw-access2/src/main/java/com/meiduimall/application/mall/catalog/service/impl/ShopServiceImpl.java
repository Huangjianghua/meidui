<<<<<<< HEAD
package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.config.ProfileConfig;
import com.meiduimall.application.mall.catalog.constant.MallConstant;
import com.meiduimall.application.mall.catalog.request.ShopProductRequest;
import com.meiduimall.application.mall.catalog.service.ShopService;
import com.meiduimall.application.mall.catalog.util.HttpGatewayUtils;
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
	private ProfileConfig profileConfig;

	@Override
	public ResBodyData getShopDetailHttp(int shopId, String memId) {

		String clientID = profileConfig.getClientId();
		String signKey = profileConfig.getSingKey();
		String host = profileConfig.getHost();
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

		String clientID = profileConfig.getClientId();
		String signKey = profileConfig.getSingKey();
		String host = profileConfig.getHost();
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

		String clientID = profileConfig.getClientId();
		String signKey = profileConfig.getSingKey();
		String host = profileConfig.getHost();
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/getShopCatalog";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put(SHOP_ID, String.valueOf(shopId));
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}

	@Override
	public ResBodyData getShopProductList(ShopProductRequest param) {

		String clientID = profileConfig.getClientId();
		String signKey = profileConfig.getSingKey();
		String host = profileConfig.getHost();
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
=======
package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.constant.ApplMallConstant;
import com.meiduimall.application.mall.catalog.request.ShopProductRequest;
import com.meiduimall.application.mall.catalog.service.ShopService;
import com.meiduimall.application.mall.catalog.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;

/**
 * MDShopController 网络请求工具类
 * 
 * @author yangchang
 *
 */
@Service
public class ShopServiceImpl implements ShopService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	private static final String SHOP_ID = "shopId";

	@Autowired
	private Environment env;

	@Override
	public ResBodyData getShopDetailHttp(int shopId, String memId) {

		String clientID = env.getProperty(ApplMallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(ApplMallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(ApplMallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = ApplMallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/getShopDetail";
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

		String clientID = env.getProperty(ApplMallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(ApplMallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(ApplMallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = ApplMallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/collectShop";
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

		String clientID = env.getProperty(ApplMallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(ApplMallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(ApplMallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = ApplMallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/getShopCatalog";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put(SHOP_ID, String.valueOf(shopId));
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}

	@Override
	public ResBodyData getShopProductList(ShopProductRequest param) {

		String clientID = env.getProperty(ApplMallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(ApplMallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(ApplMallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = ApplMallConstant.SERVICE_CATALOG_BASE_URL + "/shopInfo/getProductList";
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
>>>>>>> refs/remotes/origin/feature/V4.0.2-Team2
