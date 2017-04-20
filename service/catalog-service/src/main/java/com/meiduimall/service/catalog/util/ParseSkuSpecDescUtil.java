package com.meiduimall.service.catalog.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ParseSkuSpecDescUtil {

	/**
	 * 解析如下格式字符串
	 * 
	 * {spec_value_id={77=706, 16=588, 67=593}, spec_value={77=全网通, 16=wifi128G,
	 * 67=金色}, spec_private_value_id={77=, 16=, 67=}}
	 * 
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public static List<ParseSkuSpecDescBean> parse(String content) throws Exception {

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

		if (!result.contains("{")) {
			return null;
		}

		Map<String, String> map1 = new TreeMap<String, String>();
		int cc = 0;
		int ee = 0;
		for (int i = 0; i < result.length(); i++) {
			char ch = result.charAt(i);
			if ('{' == ch) {
				cc++;
			}
			if ('}' == ch) {
				cc--;
				if (cc == 0) {

					// 第一次切割---取出最外层，切分规格个数
					String sub = result.substring(ee, i + 1);
					if (sub == null || sub.length() < 1) {
						continue;
					}

					// 第二次切割 ---存入TreeMap，自动排序
					int index = sub.indexOf('=');
					if (index < 1) {
						continue;
					}
					String key1 = sub.substring(0, index);
					String value1 = sub.substring(index + 1, sub.length());
					map1.put(key1, value1);

					ee = i + 2;
				}
			}
		}

		if (map1.size() == 0) {
			return null;
		}

		// 用TreeMap存放数据是为了自动排序
		Map<Integer, Integer> map2 = new TreeMap<Integer, Integer>();
		Map<Integer, String> map3 = new TreeMap<Integer, String>();

		// 遍历map1,并对map1的value进行第三次切割
		for (Map.Entry<String, String> entry : map1.entrySet()) {
			String key2 = entry.getKey();
			String value2 = entry.getValue();
			if (value2 != null && value2.length() > 0) {
				if (value2.startsWith("{") && value2.endsWith("}")) {
					value2 = value2.substring(1, value2.length() - 1);
					if (value2 == null || value2.length() < 1) {
						continue;
					} else {

						// 第三次切割
						String[] split = value2.split(",");
						for (int i = 0; i < split.length; i++) {
							String splitValue = split[i];
							if (splitValue != null && splitValue.length() > 0) {
								if ("spec_value_id".equals(key2)) {

									// 第四次切割
									// 取出每一个 规格ID 对应 的
									// 规格属性ID，value值形式：{4=51, 5=4,6=76}
									String prop_id = splitValue.split("=")[0];
									String prop_value_id = splitValue.split("=")[1];
									try {
										map2.put(Integer.parseInt(prop_id), Integer.parseInt(prop_value_id));
									} catch (NumberFormatException e) {
										continue;
									}
								} else if ("spec_value".equals(key2)) {

									// 第四次切割
									// 取出每一个 规格ID 对应 的
									// 规格属性内容，value值形式：{4=军绿色, 5=F,6=默认}
									String prop_id = splitValue.split("=")[0];
									String spec_value = splitValue.split("=")[1];

									try {
										map3.put(Integer.parseInt(prop_id), spec_value);
									} catch (NumberFormatException e) {
										continue;
									}

								} else {// key2=spec_private_value_id 不需要处理
									continue;
								}
							} else {
								continue;
							}
						}
					}
				} else {
					continue;
				}
			} else {
				continue;
			}
		}

		List<ParseSkuSpecDescBean> list = new ArrayList<ParseSkuSpecDescBean>();
		// 遍历map2 -- 给对象赋值
		for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
			Integer prop_id = entry.getKey();
			Integer prop_value_id = entry.getValue();
			ParseSkuSpecDescBean bean = new ParseSkuSpecDescBean();
			bean.setPropId(prop_id);
			bean.setPropValueId(prop_value_id);
			list.add(bean);
		}

		// 遍历map3 -- 给对象赋值
		int index = 0;
		for (Map.Entry<Integer, String> entry : map3.entrySet()) {
			String spec_value = entry.getValue();
			if (list.size() > index) {
				list.get(index).setSpecValue(spec_value);
			}
			index++;
		}

		return list;
	}
}
