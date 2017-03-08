package com.meiduimall.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;

public class SortUtil {

	private static final Log log = LogFactory.getLog(SortUtil.class);
	
	/**
	 * 对List对象按照某个成员变量进行排序
	 * 
	 * @param list
	 *            List对象
	 * @param sortField
	 *            排序的属性名称
	 * @param sortMode
	 *            排序方式：ASC，DESC 任选其一
	 */
	public static <T> void sortList(List<T> list, final String sortField,
			final String sortMode, final boolean toDouble) {
		Collections.sort(list, new Comparator<T>() {
			@SuppressWarnings("rawtypes")
			@Override
			public int compare(T o1, T o2) {
				try {
					Class clazz = o1.getClass();
					Field field = clazz.getDeclaredField(sortField); // 获取成员变量
					field.setAccessible(true); // 设置成可访问状态
					String typeName = field.getType().getName().toLowerCase(); // 转换成小写

					Object v1 = field.get(o1); // 获取field的值
					Object v2 = field.get(o2); // 获取field的值
					// 强制以double类型排序
					if (toDouble) {
						typeName = "double";
					}
					boolean ASC_order = (sortMode == null || "ASC"
							.equalsIgnoreCase(sortMode));

					// 判断字段数据类型，并比较大小
					if (typeName.endsWith("string")) {
						String value1 = v1.toString();
						String value2 = v2.toString();
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else if (typeName.endsWith("short")) {
						Short value1 = Short.parseShort(v1.toString());
						Short value2 = Short.parseShort(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else if (typeName.endsWith("byte")) {
						Byte value1 = Byte.parseByte(v1.toString());
						Byte value2 = Byte.parseByte(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else if (typeName.endsWith("char")) {
						Integer value1 = (int) (v1.toString().charAt(0));
						Integer value2 = (int) (v2.toString().charAt(0));
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else if (typeName.endsWith("int")
							|| typeName.endsWith("integer")) {
						Integer value1 = Integer.parseInt(v1.toString());
						Integer value2 = Integer.parseInt(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else if (typeName.endsWith("long")) {
						Long value1 = Long.parseLong(v1.toString());
						Long value2 = Long.parseLong(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else if (typeName.endsWith("float")) {
						Float value1 = Float.parseFloat(v1.toString());
						Float value2 = Float.parseFloat(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else if (typeName.endsWith("double")) {
						Double value1 = Double.parseDouble(v1.toString());
						Double value2 = Double.parseDouble(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else if (typeName.endsWith("boolean")) {
						Boolean value1 = Boolean.parseBoolean(v1.toString());
						Boolean value2 = Boolean.parseBoolean(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else if (typeName.endsWith("date")) {
						Date value1 = (Date) (v1);
						Date value2 = (Date) (v2);
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else if (typeName.endsWith("timestamp")) {
						Timestamp value1 = (Timestamp) (v1);
						Timestamp value2 = (Timestamp) (v2);
						return ASC_order ? value1.compareTo(value2) : value2
								.compareTo(value1);
					} else {
						// 调用对象的compareTo()方法比较大小
						Method method = field.getType().getDeclaredMethod(
								"compareTo", new Class[] { field.getType() });
						method.setAccessible(true); // 设置可访问权限
						int result = (Integer) method.invoke(v1,
								new Object[] { v2 });
						return ASC_order ? result : result * (-1);
					}
				} catch (Exception e) {
					String err = e.getLocalizedMessage();
					e.printStackTrace();
				}

				return 0; // 未知类型，无法比较大小
			}
		});
	}
	
	/**
	 * 方法名: ascSortMD5<br>
	 * 描述:  升序/正序排序并进行MD5加密<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-19
	 * @param jsonObj
	 * @return
	 */
	public static String ascSortMD5(JSONObject jsonObj,String beginStr,String endStr){
		StringBuffer sign = new StringBuffer();
		//排序
		List<String> setList = new ArrayList<String>(jsonObj.keySet());
		Collections.sort(setList);
		int listSize = setList.size(); 
		//拼接参数
		sign.append(beginStr);
		for (int i = 0; i < setList.size(); i++) {
			sign.append(setList.get(i)).append("=")
					.append(jsonObj.getString(setList.get(i)));
			if(listSize > (i+1)){
				sign.append("&");
			}
		}
		//附加参数
		sign.append(endStr);
		log.info("MD5加密字符串："+sign.toString());
		return MD5Tool.MD5Encrypt(sign.toString());
	}

}
