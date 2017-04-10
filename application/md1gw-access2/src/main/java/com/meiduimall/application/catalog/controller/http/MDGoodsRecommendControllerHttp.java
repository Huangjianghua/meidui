package com.meiduimall.application.catalog.controller.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;

import com.meiduimall.application.catalog.util.HttpGatewayUtils;

/**
 * GoodsRecommendController的网络请求工具类
 * 
 * @author yangchang
 *
 */
public class MDGoodsRecommendControllerHttp {

	/**
	 * 根据推荐类型，获取推荐商品
	 * 
	 * @param type
	 * @param req_id
	 * @return
	 * @throws Exception
	 */
	public static String getFirstRecommendGoodsHttp(Environment env, int type, int req_id) throws Exception {

		String uri = "/mall/catalog-service/v1/goodsRecommend/getFirstRecommend";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("type", "" + type);
		params.put("req_id", "" + req_id);
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}
}
