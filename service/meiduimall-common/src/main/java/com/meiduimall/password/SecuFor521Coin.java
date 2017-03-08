package com.meiduimall.password;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.meiduimall.password.util.AESUtils;
import com.meiduimall.password.util.MD5;

import net.sf.json.JSONObject;

public class SecuFor521Coin {
	
	public static int  checkParamOfIncharge(String request,HashMap<String,String> param,Map<String,String> configMap){
		try{	
			String aesKey = configMap.get("AESKEY");
			String appId = configMap.get("APPID");
			String appKey = configMap.get("APIKEY");
			String appPwd = configMap.get("APPPWD");
			String decodeRequest = AESUtils.decrypt(request, aesKey);
			if (decodeRequest == null || decodeRequest.equals("") ){
				return -1;
			}
			JSONObject jsonbean = JSONObject.fromObject(decodeRequest);
			String tradeNumber = jsonbean.getString("tradeNumber");
			String timestamp = jsonbean.getString("timestamp");
			String amount = jsonbean.getString("amount");
			String password = jsonbean.getString("password");
			String address = jsonbean.getString("address");
			String appId1 = jsonbean.getString("appId");
			String source = jsonbean.getString("source");
			String sign = jsonbean.getString("sign");
			param.put("tradeNumber", tradeNumber);
			param.put("amount", amount);
			param.put("password", password);
			param.put("address", address);
			param.put("source", source);
			String signToCheck = appId + amount + timestamp + tradeNumber + address+source+password +appKey;
			String encodeSign = MD5.MD5Encode(signToCheck);
			if (!encodeSign.equals(sign)){
				return -2;
			}
		}catch(Exception e){
			return -2;
		}
		return 0;
	}
	
	public static int  checkParamOfAddr(String request,HashMap<String,String> param,Map<String,String> configMap){
		try{
			String aesKey = configMap.get("AESKEY");
			String appId = configMap.get("APPID");
			String appKey = configMap.get("APIKEY");
			String decodeRequest = AESUtils.decrypt(request, aesKey);
			if (decodeRequest == null || decodeRequest.equals("") ){
				return -1;
			}
			JSONObject jsonbean = JSONObject.fromObject(decodeRequest);
			String timestamp = jsonbean.getString("timestamp");
			String address = jsonbean.getString("address");
			String appId1 = jsonbean.getString("appId");
			String sign = jsonbean.getString("sign");
			param.put("address", address);		
			String signToCheck = appId +address+timestamp+appKey;
			String encodeSign = MD5.MD5Encode(signToCheck);
			if (!encodeSign.equals(sign)){
				return -2;
			}
		}catch(Exception e){
			return -2;
		}
		return 0;
	}
	public static String generateReqParam(JSONObject jsonbean,Map<String,String> configMap){
	try{	
		String appId = configMap.get("APPIDQB"); 
		String appKey = configMap.get("APIKEYQB");
		String aesKey = configMap.get("AESKEYQB");
		 
		String tradeNumber = jsonbean.getString("tradeNumber");
		String password = jsonbean.getString("password");
		String amount = jsonbean.getString("amount");
		String address = jsonbean.getString("address");		 
		DateFormat dateformet = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp =dateformet.format(new Date());		 

    	Map<String, String> map = new TreeMap<String, String>();
    	map.put("tradeNumber", tradeNumber);
    	map.put("address", address);
    	map.put("cointype", "withdraw");
    	map.put("uid", appId);
    	map.put("amount", amount);
    	map.put("coinname", "fec");
    	map.put("password", password);
    	
    	Set<String> keySet = map.keySet();
    	StringBuffer buffer = new StringBuffer();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            buffer.append(key);
            buffer.append('=');
            buffer.append(map.get(key));
            buffer.append('&');
        }
        buffer.append("key=");
        buffer.append(appKey);
        String encodeSign = MD5.MD5Encode(buffer.toString());
        map.put("sign", encodeSign);
        
        JSONObject codeJSONObject1 = JSONObject.fromObject(map);
		String encodKey = AESUtils.encrypt(codeJSONObject1.toString(),aesKey);
		return "request="+encodKey;
	}catch(Exception e){
		return null;
	}
	}

	public static String generateCheckAddrReqOfQB(JSONObject jsonbean,Map<String,String> configMap){
		try{	
			String appId = configMap.get("APPIDQB"); 
			String appKey = configMap.get("APIKEYQB");
			String aesKey = configMap.get("AESKEYQB");	
			String address = jsonbean.getString("address");
	    	Map<String, String> map = new TreeMap<String, String>();
	    	map.put("address", address);
	    	map.put("cointype", "validateaddress");
	    	map.put("coinname", "fec");
	    	
	    	Set<String> keySet = map.keySet();
	    	StringBuffer buffer = new StringBuffer();
	        Iterator<String> iter = keySet.iterator();
	        while (iter.hasNext()) {
	            String key = iter.next();
	            buffer.append(key);
	            buffer.append('=');
	            buffer.append(map.get(key));
	            buffer.append('&');
	        }
	        buffer.append("key=");
	        buffer.append(appKey);
	        String encodeSign = MD5.MD5Encode(buffer.toString());
	        map.put("sign", encodeSign);        
	        JSONObject codeJSONObject1 = JSONObject.fromObject(map);
			String encodKey = AESUtils.encrypt(codeJSONObject1.toString(),aesKey);
			return "request="+encodKey;
		}catch(Exception e){
			return null;
		}
	}

	public static String generateReqParamOfCheck(JSONObject jsonbean,Map<String,String> configMap){
		try{	
			String appId = configMap.get("APPID521"); 
			String appKey = configMap.get("APIKEY521");
			String aesKey = configMap.get("AESKEY521");
			 
			
			String address = jsonbean.getString("address");		 
			DateFormat dateformet = new SimpleDateFormat("yyyyMMddHHmmss");
			String timestamp =dateformet.format(new Date());		 
			String sign =appId+address+ timestamp +appKey;
			String encodeSign = MD5.MD5Encode(sign);
	
			JSONObject codeJSONObject = new JSONObject();
			codeJSONObject.put("address", address);
			codeJSONObject.put("timestamp",timestamp);
			codeJSONObject.put("appId", appId);
			codeJSONObject.put("sign", encodeSign);		 
			String encodKey = AESUtils.encrypt(codeJSONObject.toString(),aesKey);
			return "request="+encodKey;
		}catch(Exception e){
			return null;
		}
	}

	public static String generateReqOfCreateAddr(JSONObject jsonbean,Map<String,String> configMap){
		try{	
			String appId = configMap.get("APPIDQB"); 
			String appKey = configMap.get("APIKEYQB");
			String aesKey = configMap.get("AESKEYQB");
			
					 
	    	Map<String, String> map = new TreeMap<String, String>();
	    	map.put("cointype", "createAddress");
	    	map.put("uid", appId);
	    	map.put("coinname", "fec");
	    	
	    	    	
	    	Set<String> keySet = map.keySet();
	    	StringBuffer buffer = new StringBuffer();
	        Iterator<String> iter = keySet.iterator();
	        while (iter.hasNext()) {
	            String key = iter.next();
	            buffer.append(key);
	            buffer.append('=');
	            buffer.append(map.get(key));
	            buffer.append('&');
	        }
	        buffer.append("key=");
	        buffer.append(appKey);
	        String encodeSign = MD5.MD5Encode(buffer.toString());
	        map.put("sign", encodeSign);
	        
	        JSONObject codeJSONObject1 = JSONObject.fromObject(map);
			String encodKey = AESUtils.encrypt(codeJSONObject1.toString(),aesKey);
			return "request="+encodKey;
		}catch(Exception e){
			return null;
		}
	}

	public static String generateReqOfQryIncharge(JSONObject jsonbean,Map<String,String> configMap){
		try{	
			String appId = configMap.get("APPIDQB"); 
			String appKey = configMap.get("APIKEYQB");
			String aesKey = configMap.get("AESKEYQB");
							 
	    	Map<String, String> map = new TreeMap<String, String>();
	    	map.put("cointype", "transactionProcess");
	    	map.put("offset","0");
	    	//map.put("uid", appId);
	    	map.put("coinname", "fec");
	    	
	    	    	
	    	Set<String> keySet = map.keySet();
	    	StringBuffer buffer = new StringBuffer();
	        Iterator<String> iter = keySet.iterator();
	        while (iter.hasNext()) {
	            String key = iter.next();
	            buffer.append(key);
	            buffer.append('=');
	            buffer.append(map.get(key));
	            buffer.append('&');
	        }
	        buffer.append("key=");
	        buffer.append(appKey);
	        String encodeSign = MD5.MD5Encode(buffer.toString());
	        map.put("sign", encodeSign);
	        
	        JSONObject codeJSONObject1 = JSONObject.fromObject(map);
			String encodKey = AESUtils.encrypt(codeJSONObject1.toString(),aesKey);
			return "request="+encodKey;
		}catch(Exception e){
			return null;
		}
	}

}
