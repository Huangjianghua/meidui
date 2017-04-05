package com.meiduimall.application.catalog.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Http网关请求工具类
 * 
 * @author yangchang
 *
 */
public class HttpGatewayUtils {

	/**
	 * GET请求，自动签名
	 * 
	 * @param url
	 *            请求地址
	 * @param clientID
	 * @param signKey
	 * @param params
	 *            请求参数Map集合
	 * @return Http请求结果
	 * @throws Exception
	 */
	public static String sendGet(String url, String clientID, String signKey, Map<String, String> params)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		Map<String, String> req_params = new HashMap<String, String>();

		long timestamp = System.currentTimeMillis();
		req_params.put("timestamp", timestamp + "");
		sb.append("timestamp=" + timestamp + "&");

		req_params.put("clientID", clientID);
		sb.append("clientID=" + clientID + "&");

		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (!isEmptyByString(value)) {
					req_params.put(key, value);
					sb.append(key + "=" + value + "&");
				}
			}
		}

		// 获取签名串
		String sign = getSign(req_params, signKey);
		sb.append("sign=" + sign);

		String params_content = sb.toString();

		url = url + "?" + params_content;

		Logger.info("请求地址：%s", url);
		String result = HttpTooUtils.sendGet(url);

		Logger.info("请求结果：%s", result);
		return result;
	}

	/**
	 * POST请求，自动签名
	 * 
	 * @param url
	 *            请求地址
	 * @param clientID
	 * @param signKey
	 * @param params
	 *            请求参数Map集合
	 * @return Http请求结果
	 * @throws Exception
	 */
	public static String sendPost(String url, String clientID, String signKey, Map<String, String> params)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		Map<String, String> req_params = new HashMap<String, String>();

		long timestamp = System.currentTimeMillis();
		req_params.put("timestamp", timestamp + "");
		sb.append("timestamp=" + timestamp + "&");

		req_params.put("clientID", clientID);
		sb.append("clientID=" + clientID + "&");

		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (!isEmptyByString(value)) {
					req_params.put(key, value);
					sb.append(key + "=" + value + "&");
				}
			}
		}

		// 获取签名串
		String sign = getSign(req_params, signKey);
		sb.append("sign=" + sign);

		String params_content = sb.toString();

		Logger.info("请求地址：%s", url);
		Logger.info("POST实体内容：%s", params_content);
		String result = HttpTooUtils.sendPost(url, params_content);
		Logger.info("请求结果：%s", result);
		return result;
	}

	/**
	 * 签名
	 * 
	 * @param params
	 *            请求参数
	 * @param signKey
	 *            签名密钥
	 * @return 签名串
	 * @throws Exception
	 */
	private static String getSign(Map<String, String> params, String signKey) throws Exception {
		String signResult = null;
		try {
			// 遍历请求参数
			String[] arr = new String[params.size()];
			int i = 0;
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				arr[i] = key + "=" + value;
				i++;
			}

			// 对参数进行排序
			Arrays.sort(arr);

			// 拼接参数
			StringBuffer buffer = new StringBuffer();
			for (int k = 0; k < arr.length; k++) {
				buffer.append(arr[k]);
				buffer.append("&");
			}
			buffer.append("key=");
			buffer.append(signKey);
			String tempStr = buffer.toString();

			// MD5签名
			signResult = MD5Util.MD5EncryptBy32(tempStr).toUpperCase();
		} catch (Exception e) {
			throw e;
		}
		return signResult;
	}

	/**
	 * 检查字符串是否为空
	 * 
	 * @param value
	 * @return true 为空
	 */
	private static boolean isEmptyByString(String value) {
		return ((value == null) || "null".equals(value) || "".equals(value) || (value.trim().length() == 0));
	}

}
