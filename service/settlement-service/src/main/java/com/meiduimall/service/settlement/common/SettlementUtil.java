package com.meiduimall.service.settlement.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.pagehelper.StringUtil;
import com.meiduimall.core.ResBodyData;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: SettlementUtil.java
 * Author:   许彦雄
 * Date:     2017年3月15日 下午6:15:47
 * Description: 结算工具类
 */
public class SettlementUtil {
	
	private static final Log log = LogFactory.getLog(SettlementUtil.class);
	
	public static <K,V> Map<K,V> convert2Map(List<V> list,String propName){
		
		Map<K,V> map=new HashMap<K,V>();
		if(Strings.isNullOrEmpty(propName)){
			return map;
		}
		if(list!=null && !list.isEmpty()){
			for(V v:list){
				try{
					K k=(K) PropertyUtils.getProperty(v, propName);
					map.put(k, v);
				}catch(Exception e){
					log.error(e.getMessage());
				}
			}
		}
		
		return map;
	}
	
	public static <K,V> List<K> convert2ListByProperty(List<V> list,String propName){
		
		List<K> retList=new ArrayList<K>();
		if(Strings.isNullOrEmpty(propName)){
			return retList;
		}
		if(list!=null && !list.isEmpty()){
			for(V v:list){
				try{
					K k=(K) PropertyUtils.getProperty(v, propName);
					retList.add(k);
				}catch(Exception e){
					log.error(e.getMessage());
				}
			}
		}
		
		return retList;
	}
	
	public static boolean isZero(double number){
		
		BigDecimal num=new BigDecimal(number);
		if(num.compareTo(BigDecimal.ZERO)==0){
			return true;
		}
		return false;
	}
	
	public static ResBodyData success(Object data, String resultMsg) {
		return buildReponseData(data, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, resultMsg);
	}
	
	public static ResBodyData failure(Object data, String resultMsg) {
		return buildReponseData(data, ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE, resultMsg);
	}
	
	public static ResBodyData buildReponseData(Object data,Integer statusCode,String resultMsg){
		
		return new ResBodyData(statusCode,resultMsg,data);

	}
	
	public static boolean contains(String[] src,String ele){
		boolean result=false;
		if(src!=null && src.length>0){
			if(!Strings.isNullOrEmpty(ele)){
				result=Arrays.asList(src).contains(ele);
			}	
		}
		
		return result;
	}
	
	public static String getValues(String[] src,String separator){
		String result="";
		separator=StringUtil.isEmpty(separator)?",":separator;
		if(src!=null && src.length>0){
			result=Joiner.on(separator).skipNulls().join(src);
		}
		return result;
	}
	

}
