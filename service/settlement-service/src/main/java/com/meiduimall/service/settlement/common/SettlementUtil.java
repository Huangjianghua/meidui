package com.meiduimall.service.settlement.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.pagehelper.StringUtil;


/**
 * @author alex.xu
 * @created at 2016/12/23
 *
 */
public class SettlementUtil {
	
	private static final Log log = LogFactory.getLog(SettlementUtil.class);
	
	public static <K,V> Map<K,V> convert2Map(List<V> list,String propName){
		
		Map<K,V> map=new HashMap<K,V>();
		if(StringUtils.isEmpty(propName)){
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
		if(StringUtils.isEmpty(propName)){
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
	
	public static ResponseBodyData success(Object data, String resultMsg) {
		return buildReponseData(data, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, resultMsg);
	}
	
	public static ResponseBodyData failure(Object data, String resultMsg) {
		return buildReponseData(data, ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE, resultMsg);
	}
	
	public static ResponseBodyData buildReponseData(Object data,Integer statusCode,String resultMsg){

		return new ResponseBodyData(data,statusCode,resultMsg);

	}
	
	public static boolean contains(String[] src,String ele){
		boolean result=false;
		if(src!=null && src.length>0){
			if(!StringUtils.isEmpty(ele)){
				result=Arrays.asList(src).contains(ele);
			}	
		}
		
		return result;
	}
	
	public static String getValues(String[] src,String seperator){
		String result="";
		seperator=StringUtil.isEmpty(seperator)?",":seperator;
		if(src!=null && src.length>0){
			result=StringUtils.join(Arrays.asList(src),seperator);
		}
		return result;
	}
	

}
