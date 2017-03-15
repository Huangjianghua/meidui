package com.meiduimall.core.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class FastJsonUtil {
	
	
	/**
	 * 功能描述: 将java类型的对象转换为JSON格式的字符串
	 * Author: 陈建宇
	 * Date:   2016年12月19日 上午11:01:40
	 * @param object
	 * @return    
	 * return String   
	 * @throws
	 */
    public static <T> String serialize(T object) {
        return JSON.toJSONString(object);
    }

    /**
     * 功能描述:将JSON格式的字符串转换为java类型的对象或者java数组类型的对象，不包括java集合类型
     * Author: 陈建宇
     * Date:   2016年12月19日 上午11:01:53
     * @param json
     * @param clz
     * @return    
     * return T   
     * @throws
     */
    public static <T> T deserialize(String json, Class<T> clz) {
        return JSON.parseObject(json, clz);
    }

    
    
    /**
     * 功能描述: 将JSON格式的字符串转换为List<T>类型的对象
     * Author: 陈建宇
     * Date:   2016年12月19日 上午11:02:07
     * @param json
     * @param clz
     * @return    
     * return List<T>   
     * @throws
     */
    public static <T> List<T> deserializeList(String json, Class<T> clz) {
        String jsonStr = "";
		if (!(null == json || json.trim().equals(""))) {
			if (!json.startsWith("[")) {
				jsonStr = "[" + json + "]";
			} else {
				jsonStr = json;
			}
			return JSON.parseArray(jsonStr, clz);
		} else {
			return new ArrayList<T>();
		}
    }

    /**
     * 功能描述: 将JSON格式的字符串转换成任意Java类型的对象
     * Author: 陈建宇
     * Date:   2016年12月19日 上午11:02:20
     * @param json
     * @param type
     * @return    
     * return T   
     * @throws
     */
    public static <T> T deserializeAny(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type);
    }

    
    /**
     * 功能描述: 将json格式转换成map
     * Author: 陈建宇
     * Date:   2016年12月19日 上午11:02:49
     * @param json
     * @param key
     * @return    
     * return String   
     * @throws
     */
    public static String getJsonValue(String json, String key){
		HashMap map = FastJsonUtil.deserialize(json, HashMap.class);
		return (String)map.get(key); 
	} 
    
    

}
