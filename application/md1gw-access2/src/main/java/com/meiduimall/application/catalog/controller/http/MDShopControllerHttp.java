package com.meiduimall.application.catalog.controller.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;

import com.meiduimall.application.catalog.util.HttpGatewayUtils;

/**
 * MDShopController 网络请求工具类
 * 
 * @author yangchang
 *
 */
public class MDShopControllerHttp {

	/**
	 * 根据店铺shop_id，获取店铺详情
	 * 
	 * @param env
	 * @param shop_id
	 * @param mem_id
	 * @return
	 * @throws Exception
	 */
	public static String getShopDetailHttp(Environment env, int shop_id, String mem_id) throws Exception {

		String uri = "/mall/catalog-service/v1/shopInfo/getShopDetail";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shop_id", "" + shop_id);
		params.put("mem_id", "" + mem_id);
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}

	/**
	 * 收藏或者取消收藏店铺
	 * 
	 * @param env
	 * @param shop_id
	 * @param is_collect
	 * @param mem_id
	 * @return
	 * @throws Exception
	 */
	public static String collectOrCancelShopHttp(Environment env, int shop_id, int is_collect, String mem_id)
			throws Exception {

		String uri = "/mall/catalog-service/v1/shopInfo/collectShop";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shop_id", "" + shop_id);
		params.put("mem_id", "" + mem_id);
		params.put("is_collect", "" + is_collect);
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}

	/**
	 * 请求商家自定义商品分类列表
	 * 
	 * @param env
	 * @param shop_id
	 * @return
	 * @throws Exception
	 */
	public static String getShopProductCatalogHttp(Environment env, int shop_id) throws Exception {
		String uri = "/mall/catalog-service/v1/shopInfo/getShopCatalog";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("shop_id", "" + shop_id);
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}
}
