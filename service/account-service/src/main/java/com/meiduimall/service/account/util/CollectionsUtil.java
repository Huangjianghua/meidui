package com.meiduimall.service.account.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;


/**
 * 类名:  CollectionsUtil<br>
 * 描述: 集合工具类 <br>
 * 创建时间: 2016-12-18
 */
public class CollectionsUtil {

	/**
	 * 方法名: convertJSONToMap<br>
	 * 描述: 指定属性的json转换成map <br>
	 * 创建时间: 2016-12-18
	 * @param keys
	 * @param param
	 * @return
	 */
	public static Map<String, String> convertJSONToMap(String[] keys,JSONObject param){
		Map<String, String> map = new HashMap<String, String>();
		for (String key : keys) {
			if(param.containsKey(key)){
				map.put(key, param.getString(key));
			}
		}
		return map;
	}
}
