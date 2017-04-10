package com.meiduimall.application.catalog.controller.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;

import com.meiduimall.application.catalog.util.HttpGatewayUtils;

/**
 * GoodsDetailController的网络请求工具类
 * 
 * @author yangchang
 *
 */
public class MDGoodsDetailControllerHttp {

	/**
	 * 根据商品item_id获取商品详情
	 * 
	 * @param type
	 * @param req_id
	 * @return
	 * @throws Exception
	 */
	public static String getItemDetailHttp(Environment env, int item_id, String mem_id) throws Exception {

		String uri = "/mall/catalog-service/v1/goodsDetail/getItem";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("item_id", "" + item_id);
		params.put("mem_id", "" + mem_id);
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}
}
