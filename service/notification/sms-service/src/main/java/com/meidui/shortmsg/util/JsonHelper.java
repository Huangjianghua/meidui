package com.meidui.shortmsg.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

/**
 * 类名: JsonHelper
 * 描述: json操作类
 * 编写者:   
 * 版权: Copyright (C) 2016 first版权所有
 * 创建时间: 2016年7月19日
 */
public class JsonHelper {

	/**
	 * 方法名: jsonToBean
	 * 描述: json转bean
	 * @param content
	 * @param valueType
	 * @return
	 * 作者:  
	 * 创建时间: 2016年7月19日
	 */
	public static <T> T jsonToBean(String content, Class<T> valueType){
		if(StringUtils.isBlank(content)){
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(content,valueType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 方法名: beanToJson
	 * 描述: bean转json
	 * @param value
	 * @return
	 * 作者:  
	 * 创建时间: 2016年7月19日
	 */
	public static String beanToJson(Object value){
		ObjectMapper objectMapper = new ObjectMapper();
		StringWriter str = new StringWriter();
		
		try {
			objectMapper.writeValue(str, value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str.toString();
	}
 
	public static String beanToJsonAndFmDate(Object value){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		JSONArray ja = JSONArray.fromObject(value, jsonConfig);
		return ja.toString();
	}
	
	/**
	 * 函数功能说明: 设定日期的转换格式
	 *  
	 * @param object 进行转换的对象
     * @return
	 */
    private static void setDataFormat2JAVA(){ 
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{
                "yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"}));
         
    }
    
 
    /**
     * 函数功能说明: 把一个指定的jsonString字符串，转换为一个clazz对象
     * 形如{"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}} 
     * 编写者:   
     * 创建时间: 2016年7月25日
     * @param jsonString 进行转换的字符串
     * @param clazz  字符串按照类clazz进行转换
     * @return
     */
    public static Object getObjectFromJsonString(String jsonString,Class<?> clazz){
        JSONObject jsonObject = null;
        try {
            setDataFormat2JAVA();
            jsonObject = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toBean(jsonObject, clazz);
    }
     
    /**
     * 函数功能说明: 从一个JSON 对象字符格式中得到一个java对象，其中dogs,cats表示的各是一类的集合，形如：
     * {"id" : idValue, "name" : nameValue, "cats" : {"catId" : catIdValue, ...},dogs:[{}, {}, ...]} 
     * 编写者:   
     * 创建时间: 2016年7月25日
     * @param jsonString
     * @param clazz
     * @param classMap 集合属性的类型 (key : 集合属性名, value : 集合属性类型class) 
     *          如：Map map = new HashMap();
     *          map.put("dogs",Dog.class);
     *          map.put("cats",Cat.class);
     * @return
     */
    public static Object getObjectFromJsonString(String jsonString,Class<?> clazz,Map<String, Object> classMap){
        JSONObject jsonObject = null;
        try {
            setDataFormat2JAVA();
            jsonObject = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toBean(jsonObject, clazz, classMap);
    }
     
    /**
     * 函数功能说明: 从一个JSON数组得到一个对象数组
     *      形如格式：[{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...]   
     * 编写者:   
     * 创建时间: 2016年7月25日
     * @param jsonString 要进行转换的对象字符串
     * @param clazz 字符串要转换为的对象
     * @return
     */
    public static Object[] getObjectArray(String jsonString,Class<?> clazz){
        setDataFormat2JAVA();     
        JSONArray array = JSONArray.fromObject(jsonString);     
        Object[] obj = new Object[array.size()];     
        for(int i = 0; i < array.size(); i++){     
            JSONObject jsonObject = array.getJSONObject(i);     
            obj[i] = JSONObject.toBean(jsonObject, clazz);     
        }     
        return obj;     
    }
    /**
     * 函数功能说明: 从一个JSON数组得到一个java的对象数组
     * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...] 
     * 编写者:   
     * 创建时间: 2016年7月25日
     * @param jsonString
     * @param clazz
     * @param map 参见方法getObjectFromJsonString(String jsonString,Class<?> clazz,Map<String, Object> map)的map
     * @return
     */
    public static Object[] getObjectArray(String jsonString,Class<?> clazz,Map<String,Object> map){
        setDataFormat2JAVA();     
        JSONArray array = JSONArray.fromObject(jsonString);     
        Object[] obj = new Object[array.size()];     
        for(int i = 0; i < array.size(); i++){     
            JSONObject jsonObject = array.getJSONObject(i);     
            obj[i] = JSONObject.toBean(jsonObject, clazz,map);     
        }     
        return obj;     
    }
     
    /**
     * 函数功能说明:从一个JSON数组得到一个java对象集合 
     * 编写者:   
     * 创建时间: 2016年7月25日
     * @param jsonString
     * @param clazz
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<?> getObjectList(String jsonString,Class<?> clazz){
        setDataFormat2JAVA();
        JSONArray array = JSONArray.fromObject(jsonString);
        List list = new ArrayList();
        for (Iterator iterator = array.iterator(); iterator.hasNext();) {
            JSONObject jsonObject = (JSONObject) iterator.next();
            list.add(JSONObject.toBean(jsonObject, clazz));
        }
        return list;
    }
    /**
     * 函数功能说明: 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性
      * 编写者:   
     * 创建时间: 2016年7月25日
     * @param jsonString
     * @param clazz
     * @param map 参见方法getObjectFromJsonString(String jsonString,Class<?> clazz,Map<String, Object> map)的map
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<?> getObjectList(String jsonString,Class<?> clazz,Map<String,Object> map){
        setDataFormat2JAVA();
        JSONArray array = JSONArray.fromObject(jsonString);
        List list = new ArrayList();
        for (Iterator iterator = array.iterator(); iterator.hasNext();) {
            JSONObject jsonObject = (JSONObject) iterator.next();
            list.add(JSONObject.toBean(jsonObject, clazz,map));
        }
        return list;
    }
     
     
    /**
     * 函数功能说明: 从json hash表达式中获取一个map，该map支持嵌套功能
     * 形如：{"id" : "johncon", "name" : "小强"}
     * 注意commons-collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap
     * 编写者:   
     * 创建时间: 2016年7月25日
     * @param jsonString
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map getMapFromJson(String jsonString){
        setDataFormat2JAVA();
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Map map = new HashMap();
        for (Iterator iterator = jsonObject.keys(); iterator.hasNext();) {
            String key = (String) iterator.next();
            map.put(key, jsonObject.get(key));
        }
        return map;
    }
     
    /**
     * 函数功能说明: 从json数组中得到相应的java数组
     * json形如：["123", "456"] 
     * 编写者:   
     * 创建时间: 2016年7月25日
     * @param jsonString
     * @return
     */
    public static Object[] getObjectArrayFromJson(String jsonString){
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
    }
}
