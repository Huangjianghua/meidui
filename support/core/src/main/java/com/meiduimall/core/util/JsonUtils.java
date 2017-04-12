package com.meiduimall.core.util;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: JsonUtils.java
 * Author:   Administrator
 * Date:     2017年3月15日 下午6:15:47
 * Description: json工具类
 */
public class JsonUtils {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	private static JsonUtils jacksonUtil = new JsonUtils();

    private ObjectMapper mapper;

    private JsonUtils() {
    	mapper = new ObjectMapper();
    }


    public static ObjectMapper getInstance() {
        return jacksonUtil.mapper;
    }


    /**
     * 功能描述:  json转化为java bean
     * Author: 陈建宇
     * Date:   2017年3月15日 下午6:15:32
     * return  T
     */
    public static <T> T jsonToBean(String json,  Class<T> valueType){
    	if(!Strings.isNullOrEmpty(json)){
    		try {
    			return getInstance().readValue(json, valueType);
    		} catch (JsonParseException e) {
    			logger.error(e.getMessage(),e);
    		} catch (JsonMappingException e) {
    			logger.error(e.getMessage(),e);
    		} catch (IOException e) {
    			logger.error(e.getMessage(),e);
    		}
    	}
    	return null;
    }


   /**
    *
    * 功能描述:  java bean转化为json
    * Author: 陈建宇
    * Date:   2017年3月15日 下午6:15:16
    * return  String
    */
    public static String beanToJson(Object bean){
		try {
			return getInstance().writeValueAsString(bean);
		} catch (JsonGenerationException e) {
			 logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			 logger.error(e.getMessage());
		} catch (IOException e) {
			 logger.error(e.getMessage());
		}
    	return null;
    }



    /**
     * 功能描述:  json转list
     * Author: 陈建宇
     * Date:   2017年3月15日 下午6:10:54
     * return  List<T>
     */
    public static <T> List<T> jsonToList(String json,Class<T> clazz) {
    	if(!Strings.isNullOrEmpty(json)){
    		T[] t =  (T[]) Array.newInstance(clazz, 1024);
            try {
                t =  (T[]) getInstance().readValue(json, t.getClass());
                List<T> list = (List<T>) Arrays.asList(t);
                return list;
            } catch (JsonGenerationException e) {
                logger.error(e.getMessage());
            } catch (JsonMappingException e) {
                logger.error(e.getMessage());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
    	}
        return null;
    }


/*
    *//**
     * 功能描述:  list转string
     * Author: 陈建宇
     * Date:   2017年3月15日 下午6:10:14
     * return  String
     *//*
    public static String listToJson(List<?> list){
        try {
            return getInstance().writeValueAsString(list);
        } catch (JsonGenerationException e) {
            logger.error(e.getMessage());
        } catch (JsonMappingException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    *//**
     * 功能描述:  map转json
     * Author: 陈建宇
     * Date:   2017年3月16日 上午9:44:44
     * return  String
     *//*
    public static String mapToJson(Map<String,?> map){
        try {
            return getInstance().writeValueAsString(map);
        } catch (JsonGenerationException e) {
            logger.error(e.getMessage());
        } catch (JsonMappingException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    *//**
     * 功能描述:  json转map
     * Author: 陈建宇
     * Date:   2017年3月16日 上午9:44:49
     * return  Map<String,T>
     *//*
    public static <T> Map<String,T> jsonToMap(String json,Class<T> valueType){
    	if(!Strings.isNullOrEmpty(json)){
            try {
            	JavaType javaType = getInstance().getTypeFactory().constructParametricType(Map.class,String.class, valueType);
    			return getInstance().readValue(json, javaType);
    		} catch (JsonParseException e) {
    			 logger.error(e.getMessage());
    		} catch (JsonMappingException e) {
    			 logger.error(e.getMessage());
    		} catch (IOException e) {
    			 logger.error(e.getMessage());
    		}
    	}
        return null;
    }
   **/

  public static String beanToJsonAndFmDate(Object channelList) {
    return beanToJson(channelList);
  }
}
