package com.meiduimall.application.mall.catalog.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.password.GatewaySignUtil;
import com.meiduimall.password.exception.Md5Exception;

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
	 * @throws IOException
	 * @throws Md5Exception
	 */
	public static String sendGet(String url, String clientID, String signKey, Map<String, String> params)
			throws IOException, Md5Exception {
		String paramsContent = getParamsContent(clientID, signKey, params);
		String urlParam = url + "?" + paramsContent;
		logger.info("请求地址：" + urlParam);
		String result = HttpUtils.get(urlParam);
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
	 * @throws IOException
	 * @throws Md5Exception
	 */
	public static String sendPost(String url, String clientID, String signKey, Map<String, String> params)
			throws IOException, Md5Exception {
		String paramsContent = getParamsContent(clientID, signKey, params);
		logger.info("请求地址：" + url);
		logger.info("POST实体内容：" + paramsContent);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		String result = HttpUtils.post(url, paramsContent, headers);
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
	 * @throws Md5Exception
	 */
	private static String getParamsContent(String clientID, String signKey, Map<String, String> params)
			throws Md5Exception {
		StringBuilder sb = new StringBuilder();
		Map<String, String> reqParams = new HashMap<String, String>();

		long timestamp = System.currentTimeMillis();
		reqParams.put("timestamp", String.valueOf(timestamp));
		sb.append("timestamp=" + timestamp + "&");

		reqParams.put("clientID", clientID);
		sb.append("clientID=" + clientID + "&");

		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (!StringUtils.isBlank(value)) {
					reqParams.put(key, value);
					sb.append(key + "=" + value + "&");
				}
			}
		}

		// 获取签名串
		String sign = GatewaySignUtil.sign(signKey, reqParams);
		sb.append("sign=" + sign);
		return sb.toString();
	}
}
