package com.meiduimall.password;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.meiduimall.password.util.MD5;
import com.meiduimall.password.util.PassworkEncryptUtils;
import com.meiduimall.password.util.StringUtils;

public class SecuForNewInterf {
	private static String encKey = "6e63627a2437346c";
	
	public static String getAppKeyDecrypt(String appKeyEncrypt){
		try{
			return new String(PassworkEncryptUtils.decrypt(StringUtils.hexToBytes(appKeyEncrypt), StringUtils.hexToBytes(encKey)));
		}catch(Exception e){
			return null;
		}
	}
	
	public static String generateRequest(HashMap<String,String> param,String appKey) {
			Map<String, String> map = new TreeMap<String, String>();
			map.putAll(param);
			Set<String> keySet = map.keySet();
	    	
			StringBuffer buffer = new StringBuffer();
	        Iterator<String> iter = keySet.iterator();
	        while (iter.hasNext()) {
	            String key = iter.next();
	            String value = map.get(key);
	            if (key.equals("sign")){
	            	continue;
	            }
	            if ( value == null 
	            		|| value.length() < 1 ) {
	            	continue;
	            }
	            buffer.append(key);
	            buffer.append('=');
	            buffer.append(value);
	            buffer.append('&');
	        }
	        buffer.append("key=");
	        buffer.append(appKey);
	        String encodeSign = MD5.MD5Encode(buffer.toString());
			
	        StringBuffer buffer1 = new StringBuffer();
	        Iterator<String> iter1 = keySet.iterator();
	        while (iter1.hasNext()) {
	            String key = iter1.next();
	            String value = map.get(key);
	            if ( value == null 
	            		|| value.length() < 1 ) {
	            	value="";
	            }
	            buffer1.append(key);
	            buffer1.append('=');
	            buffer1.append(value);
	            buffer1.append('&');
	        }
	        buffer1.append("sign=");
	        buffer1.append(encodeSign);
	        return buffer1.toString();		
	}
	
	public static int validateRequest(String request,String appKey,Map<String,String> param){
		
		if ( request == null || request.trim().length() < 1){
			return -1;
		}
		String sign = null;
		String[] req_seq = request.split("&");
		if ( req_seq == null ){
			return -2;
		}
		
		for ( int i = 0; i < req_seq.length; i++ ){
			String[] param_one = req_seq[i].split("=");
			if ( param_one == null || param_one.length == 0){
				continue;
			}
			if (param_one[0] != null 
					&& param_one[0].equals("sign")){
				if ( param_one.length >= 2){
					sign=param_one[1];
				}				
			}else{
				if ( param_one.length >= 2){
					param.put(param_one[0],param_one[1]);
				}else{
					param.put(param_one[0],"");
				}
			}
		}
		
		if ( sign == null || sign.trim().length() < 1){
			return -3;
		}
		
		Map<String, String> map = new TreeMap<String, String>();
		map.putAll(param);
		Set<String> keySet = map.keySet();
    	
		StringBuffer buffer = new StringBuffer();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String value = map.get(key);
            if ( value == null 
            		|| value.length() < 1 ) {
            	continue;
            }
            buffer.append(key);
            buffer.append('=');
            buffer.append(value);
            buffer.append('&');
        }
        buffer.append("key=");
        buffer.append(appKey);
        String encodeSign = MD5.MD5Encode(buffer.toString());
		if(!encodeSign.equals(sign)){
			return -4;
		}
		return 0;	
	}
	
	public static int parseRequest(String request,Map<String,String> param){
		if ( request == null || request.trim().length() < 1){
			return -1;
		}
		
		String[] req_seq = request.split("&");
		if ( req_seq == null ){
			return -2;
		}
		
		for ( int i = 0; i < req_seq.length; i++ ){
			String[] param_one = req_seq[i].split("=");
			if ( param_one == null || param_one.length == 0){
				continue;
			}
			
			if ( param_one.length >= 2){
				param.put(param_one[0],param_one[1]);
			}else{
				param.put(param_one[0],"");
			}			

		}
		return 0;
	}
	
	public static int validateRequestEx(String appKey,Map<String,String> param) {
		String sign = param.get("sign");
		if ( sign == null || sign.trim().length() < 1){
			return -3;
		}		
		Map<String, String> map = new TreeMap<String, String>();
		map.putAll(param);
		Set<String> keySet = map.keySet();
    	
		StringBuffer buffer = new StringBuffer();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (key.equals("sign")){
            	continue;
            }
            String value = map.get(key);
            if ( value == null 
            		|| value.length() < 1 ) {
            	continue;
            }
            buffer.append(key);
            buffer.append('=');
            buffer.append(value);
            buffer.append('&');
        }
        buffer.append("key=");
        buffer.append(appKey);
        String encodeSign = MD5.MD5Encode(buffer.toString());
		if(!encodeSign.equals(sign)){
			return -4;
		}		
		return 0;
	}
}
