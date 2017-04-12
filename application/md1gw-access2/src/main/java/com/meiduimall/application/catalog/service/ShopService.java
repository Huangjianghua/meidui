package com.meiduimall.application.catalog.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.catalog.util.HttpGatewayUtils;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.exception.ServiceException;

/**
 * MDShopController 网络请求工具类
 * 
 * @author yangchang
 *
 */
@Service
public class ShopService {

	private static Logger logger = LoggerFactory.getLogger(ShopService.class);

	@Autowired
	private Environment env;

	/**
	 * 根据店铺shop_id，获取店铺详情
	 * 
	 * @param shop_id
	 *            店铺ID
	 * @param mem_id
	 *            会员系统ID
	 * @return
	 */
	public String getShopDetailHttp(int shop_id, String mem_id) {

		String uri = "/mall/catalog-service/v1/shopInfo/getShopDetail";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shop_id", "" + shop_id);
		params.put("mem_id", "" + mem_id);
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ServiceException(BaseApiCode.REQUEST_SERVICE_ERROR);
		}
	}

	/**
	 * 收藏店铺或者取消收藏
	 * 
	 * @param shop_id
	 *            店铺ID
	 * @param is_collect
	 *            收藏1，取消收藏0
	 * @param mem_id
	 *            会员系统ID
	 * @return
	 */
	public String collectOrCancelShopHttp(int shop_id, int is_collect, String mem_id) {

		String uri = "/mall/catalog-service/v1/shopInfo/collectShop";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shop_id", "" + shop_id);
		params.put("mem_id", "" + mem_id);
		params.put("is_collect", "" + is_collect);
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ServiceException(BaseApiCode.REQUEST_SERVICE_ERROR);
		}
	}

	/**
	 * 获取商家自定义商品分类列表
	 * 
	 * @param shop_id
	 *            店铺ID
	 * @return
	 */
	public String getShopProductCatalogHttp(int shop_id) {
		String uri = "/mall/catalog-service/v1/shopInfo/getShopCatalog";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shop_id", "" + shop_id);
		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ServiceException(BaseApiCode.REQUEST_SERVICE_ERROR);
		}
	}
}
