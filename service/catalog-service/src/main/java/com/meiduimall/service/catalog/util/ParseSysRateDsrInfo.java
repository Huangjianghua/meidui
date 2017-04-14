package com.meiduimall.service.catalog.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author yangchang
 *
 */
public class ParseSysRateDsrInfo {

	public static float getValue(String content) throws Exception {

		if (content == null || "null".equals(content) || content.trim().length() == 0) {
			return 5.0f;
		}

		Map<Integer, Integer> map = parse(content);

		if (map == null || map.size() == 0) {
			return 5.0f;
		}

		int sum = 0;
		int count = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			int tempSum = key * value;
			count += value;
			sum += tempSum;
		}

		if (count == 0) {
			return 5.0f;
		} else {
			return (sum * 1.0f) / count;
		}
	}

	/**
	 * 解析如下格式数据
	 * 
	 * {1=0, 2=0, 3=0, 4=1, 5=3}
	 * 
	 * @param content
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IllegalAccessException
	 */
	private static Map<Integer, Integer> parse(String content) throws Exception {
		if (content == null || content.length() < 1) {
			return null;
		}

		Object obj = PHPSerializer.unserialize(content.getBytes("utf-8"), "utf-8");
		String result = obj.toString();

		if (result == null || result.length() < 1) {
			return null;
		}
		result = result.replace(" ", "");
		if (result == null || result.length() < 1) {
			return null;
		}

		if (result.startsWith("{") && result.endsWith("}")) {
			result = result.substring(1, result.length() - 1);
		} else {
			return null;
		}

		if (result == null || result.length() < 1) {
			return null;
		}

		String[] split = result.split(",");
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		if (split != null && split.length > 0) {
			for (String str : split) {
				String[] split2 = str.split("=");
				if (split2.length > 1) {
					String key = split2[0];
					String value = split2[1];
					try {
						map.put(Integer.parseInt(key), Integer.parseInt(value));
					} catch (Exception e) {
						continue;
					}
				} else {
					continue;
				}
			}
		} else {
			return null;
		}
		return map;
	}
}
