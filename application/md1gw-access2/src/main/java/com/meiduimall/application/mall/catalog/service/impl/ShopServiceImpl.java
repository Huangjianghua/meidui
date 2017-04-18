package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.constant.ApplicationMallApiCode;
import com.meiduimall.application.mall.catalog.request.ShopProductRequest;
import com.meiduimall.application.mall.catalog.service.ShopService;
import com.meiduimall.application.mall.catalog.util.HttpGatewayUtils;
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

	/**
	 * 请求微服务，根据店铺shopId，获取店铺详情
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param memId
	 *            会员系统ID
	 * @return
	 */
	@Override
	public String getShopDetailHttp(int shopId, String memId) {

		String uri = "/mall/catalog-service/v1/shopInfo/getShopDetail";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", String.valueOf(shopId));
		if(!StringUtils.isBlank(memId)){
			params.put("memId", memId);
		}
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ApiException(ApplicationMallApiCode.REQUEST_SERVICE_ERROR);
		}
	}

	/**
	 * 请求微服务，收藏店铺或者取消收藏
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param isCollect
	 *            收藏1，取消收藏0
	 * @param memId 会员系统ID
	 * @return
	 */
	@Override
	public String collectOrCancelShopHttp(int shopId, int isCollect, String memId) {

		String uri = "/mall/catalog-service/v1/shopInfo/collectShop";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", String.valueOf(shopId));
		params.put("isCollect", String.valueOf(isCollect));
		if(!StringUtils.isBlank(memId)){
			params.put("memId", memId);
		}
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ApiException(ApplicationMallApiCode.REQUEST_SERVICE_ERROR);
		}
	}

	/**
	 * 请求微服务，获取商家自定义商品分类列表
	 * 
	 * @param shopId
	 *            店铺ID
	 * @return
	 */
	@Override
	public String getShopProductCatalogHttp(int shopId) {
		String uri = "/mall/catalog-service/v1/shopInfo/getShopCatalog";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", String.valueOf(shopId));
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ApiException(ApplicationMallApiCode.REQUEST_SERVICE_ERROR);
		}
	}

	/**
	 * 请求微服务，获取店铺的商品列表
	 * 
	 * @param param
	 * @return
	 */
	@Override
	public String getShopProductList(ShopProductRequest param) {
		String uri = "/mall/catalog-service/v1/shopInfo/getProductList";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
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
			throw new ApiException(ApplicationMallApiCode.REQUEST_SERVICE_ERROR);
		}
	}
}
