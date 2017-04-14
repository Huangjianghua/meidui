package com.meiduimall.service.catalog.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ParseItemSpecDesUtil {

	/**
	 * 解析如下格式字符串
	 * 
	 * {4={51={spec_value_id=51, spec_value=军绿色, private_spec_value_id=},
	 * 43={spec_value_id=43, spec_value=黑色, private_spec_value_id=},
	 * 44={spec_value_id=44, spec_value=咖啡色, private_spec_value_id=}},
	 * 5={4={spec_value_id=4, spec_value=F, private_spec_value_id=}},
	 * 6={76={spec_value_id=76, spec_value=默认, private_spec_value_id=}}}
	 * 
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public static List<ParseItemSpecDescBean> parse(String content) throws Exception {
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

		// 第一次切割---取出最外层 ---切分规格个数，存入TreeMap，自动排序
		Map<Integer, String> map1 = new TreeMap<Integer, String>();
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
					String sub = result.substring(ee, i + 1);
					if (sub == null || sub.length() < 1) {
						continue;
					}
					int index = sub.indexOf('=');
					if (index < 1) {
						continue;
					}
					String key1 = sub.substring(0, index);
					String value1 = sub.substring(index + 1, sub.length());
					map1.put(Integer.parseInt(key1), value1);

					ee = i + 2;
				}
			}
		}

		if (map1.size() == 0) {
			return null;
		}

		List<ParseItemSpecDescBean> list = new ArrayList<ParseItemSpecDescBean>();

		// 遍历map1,并对map1的value进行第二次切割
		for (Map.Entry<Integer, String> entry : map1.entrySet()) {
			Integer key2 = entry.getKey();
			String value2 = entry.getValue();

			if (value2 != null && value2.length() > 0) {
				if (value2.startsWith("{") && value2.endsWith("}")) {
					value2 = value2.substring(1, value2.length() - 1);
					if (value2 == null || value2.length() < 1) {
						continue;
					} else {

						// 创建ParseItemSpecDescBean对象
						ParseItemSpecDescBean itemSpecDescBean = new ParseItemSpecDescBean();
						itemSpecDescBean.setProp_id(key2);

						// 创建集合，用于存储PropBean
						List<ParseItemSpecDescBean.PropBean> propBeanList = new ArrayList<ParseItemSpecDescBean.PropBean>();
						// 第二次切割
						Map<Integer, String> map2 = new TreeMap<Integer, String>();
						int aa = 0;
						int bb = 0;
						for (int i = 0; i < value2.length(); i++) {
							char ch = value2.charAt(i);
							if ('{' == ch) {
								aa++;
							}
							if ('}' == ch) {
								aa--;
								if (aa == 0) {
									String value3 = value2.substring(bb, i + 1);
									if (value3 == null || value3.length() < 1) {
										continue;
									}
									int index = value3.indexOf('=');
									if (index < 1) {
										continue;
									}
									String key4 = value3.substring(0, index);
									String value4 = value3.substring(index + 1, value3.length());
									map2.put(Integer.parseInt(key4), value4);
									bb = i + 2;
								}
							}
						}

						// 第三次切割
						for (Map.Entry<Integer, String> entry2 : map2.entrySet()) {
							Integer key5 = entry2.getKey();
							String value5 = entry2.getValue();

							// 创建二级对象
							ParseItemSpecDescBean.PropBean propBean = new ParseItemSpecDescBean().new PropBean();
							propBean.setProp_value_id(key5);

							if (value5 != null && value5.length() > 0) {
								if (value5.startsWith("{") && value5.endsWith("}")) {
									value5 = value5.substring(1, value5.length() - 1);
									if (value5 == null || value5.length() < 1) {
										continue;
									} else {
										// 第四次切割--获取最里面的规格属性值
										String[] propValues = value5.split(",");
										if (propValues != null && propValues.length > 0) {

											// 创建三级对象
											ParseItemSpecDescBean.PropValueBean bean = new ParseItemSpecDescBean().new PropValueBean();
											for (int j = 0; j < propValues.length; j++) {
												String propValue = propValues[j];
												if (propValue != null && propValue.length() > 0) {
													String[] props = propValue.split("=");
													if (props.length > 1) {
														String propsKey = props[0];
														String propsValue = props[1];
														switch (propsKey) {
														case "spec_value_id":
															try {
																bean.setSpec_value_id(Integer.parseInt(propsValue));
															} catch (Exception e) {
																bean.setSpec_value_id(0);
															}
															break;
														case "spec_value":
															bean.setSpec_value(propsValue);
															break;
														case "private_spec_value_id":
															// can not reach
															// 因为private_spec_value_id的值一般为空
															try {
																bean.setPrivate_spec_value_id(Integer
																		.parseInt(propsValue));
															} catch (Exception e) {
																bean.setPrivate_spec_value_id(null);
															}
															break;
														}
													}
												} else {
													continue;
												}
											}
											// 添加 PropValueBean
											propBean.setPropValueBean(bean);
										} else {
											continue;
										}
									}
								} else {
									continue;
								}
							} else {
								continue;
							}
							// 添加 PropBean
							propBeanList.add(propBean);
						}
						itemSpecDescBean.setPropBeanList(propBeanList);
						list.add(itemSpecDescBean);
					}
				} else {
					continue;
				}
			} else {
				continue;
			}
		}
		return list;
	}
}
