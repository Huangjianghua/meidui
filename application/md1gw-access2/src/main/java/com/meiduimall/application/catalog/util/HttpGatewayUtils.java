package com.meiduimall.application.catalog.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.password.GatewaySignUtil;

/**
 * Http网关请求工具类
 * 
 * @author yangchangfu
 *
 */
public class HttpGatewayUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpGatewayUtils.class);

	/**
	 * GET请求
	 * 
	 * @param url
	 * @param clientID
	 * @param signKey
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendGet(String url, String clientID, String signKey, Map<String, String> params)
			throws IOException {
		String params_content = getParamsContent(clientID, signKey, params);
		String url_param = url + "?" + params_content;
		logger.info("请求地址：" + url_param);
		String result = HttpUtils.get(url_param);
		logger.info("请求结果：" + result);
		return result;
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 * @param clientID
	 * @param signKey
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPost(String url, String clientID, String signKey, Map<String, String> params)
			throws IOException {
		String params_content = getParamsContent(clientID, signKey, params);
		logger.info("请求地址：" + url);
		logger.info("POST实体内容：" + params_content);
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		String result = HttpUtils.post(url, params_content, headers);
		logger.info("请求结果：" + result);
		return result;
	}

	/**
	 * 将请求参数签名，并组拼成key1=value1&key2=value2的形式
	 * 
	 * @param clientID
	 * @param signKey
	 * @param params
	 * @return
	 */
	private static String getParamsContent(String clientID, String signKey, Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		Map<String, String> req_params = new HashMap<String, String>();

		long timestamp = System.currentTimeMillis();
		req_params.put("timestamp", String.valueOf(timestamp));
		sb.append("timestamp=" + timestamp + "&");

		req_params.put("clientID", clientID);
		sb.append("clientID=" + clientID + "&");

		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (!StringUtils.isBlank(value)) {
					req_params.put(key, value);
					sb.append(key + "=" + value + "&");
				}
			}
		}

		// 获取签名串
		String sign = GatewaySignUtil.sign(signKey, req_params);
		sb.append("sign=" + sign);

		String params_content = sb.toString();
		return params_content;
	}
}
