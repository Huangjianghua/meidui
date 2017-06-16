package com.meiduimall.service.search.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.phprpc.util.AssocArray;
import org.phprpc.util.PHPSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PHPUnSerializerUtil {

	private static Logger logger = LoggerFactory.getLogger(PHPUnSerializerUtil.class);

	/**
	 * 解析表site_widgets_instance的params字段
	 * 
	 * @param content
	 *            解析内容
	 * @return
	 */
	public static Map<String, Object> siteWidgetsInstanceParams(String content) {
		Map<String, Object> map = new HashMap<>();
		PHPSerializer p = new PHPSerializer();
		AssocArray array = null;
		try {
			array = (AssocArray) p.unserialize(content.getBytes("utf-8"));
		} catch (Exception e) {
			logger.error("change to AssocArray error!");
			return map;
		}
		if (array == null || array.isEmpty()) {
			logger.error("AssocArray isEmpty");
			return map;
		}
		@SuppressWarnings("unchecked")
		HashMap<String, Object> tempMap = array.toHashMap();
		if (tempMap == null || tempMap.size() == 0) {
			logger.error("AssocArray to HashMap, HashMap is empty!");
			return map;
		}
		for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof byte[]) {
				byte[] bs = (byte[]) value;
				if (bs != null && bs.length > 0) {
					try {
						tempMap.put(key, new String(bs, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						logger.error("byte[] change to String error! : ", e);
						continue;
					}
				}
			} else if (value instanceof AssocArray) {
				AssocArray arrayValue = (AssocArray) value;
				if (arrayValue != null && !arrayValue.isEmpty()) {
					List<String> subResultList = new ArrayList<>();
					@SuppressWarnings("rawtypes")
					ArrayList list = arrayValue.toArrayList();
					for (Object object : list) {
						if (object instanceof byte[]) {
							byte[] bs = (byte[]) object;
							if (bs != null && bs.length > 0) {
								try {
									String subResult = new String(bs, "utf-8");
									subResultList.add(subResult);
								} catch (UnsupportedEncodingException e) {
									logger.error("byte[] change to String error! : ", e);
								}
							}
						}
					}
					map.put(key, subResultList);
				}
			}
		}
		return map;
	}
}
