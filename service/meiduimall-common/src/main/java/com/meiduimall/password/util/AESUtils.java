package com.meiduimall.password.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.sf.json.JSONObject;

public class AESUtils {
     
    private static final String AES = "AES";  
    
    public static String ENCODE_KEY = "E98Q43286B0351C7";
    
    public static void main(String[] args) {
 
    	try{
    	
    	/**
		  * 充值参数 
		  */
		 String appKey = "1298e0759f2bdbb745eb5c07768d0d62";
		 String tradeNumber = UUID.randomUUID().toString();
		 String address = "2Wdkf89132ksdf01230dsidf1201";
		 String appId = "Firstkg_JIFENG_Platform";
		 
		 DateFormat dateformet = new SimpleDateFormat("yyyyMMddHHmmss");
		 String timestamp =dateformet.format(new Date());
		 String amount = "0.582";
		 
		 String sign = appId + amount + timestamp + tradeNumber + address + appKey;
		 String encodeSign = MD5.MD5Encode(sign);
		 
		 
		 HashMap<String,String> codeJSONObject = new HashMap<String,String>();
		 //codeJSONObject.put("appKey", appKey);
		 codeJSONObject.put("tradeNumber", tradeNumber);
		 codeJSONObject.put("address", address);
		 codeJSONObject.put("appid", appId);
		 codeJSONObject.put("amount", "");
		 codeJSONObject.put("sign", encodeSign);
		 
		 Map<String, String> map = new TreeMap<String, String>();
		 map.putAll(codeJSONObject);
		 StringBuffer buffer = new StringBuffer();
		 Set<String> keySet = map.keySet();
		 Iterator<String> iter = keySet.iterator();
		 
		 while (iter.hasNext()) {
			 String key = iter.next();
	         String value = map.get(key);
	        
	         buffer.append(key);
	         buffer.append('=');
	         buffer.append(value);
	         buffer.append('&');  
		 }
		 buffer.append("key=");
	     buffer.append(appKey);
		String encodKey = AESUtils.encrypt(codeJSONObject.toString(),ENCODE_KEY);
		 
        System.out.println("encodKey:"+encodKey);  
        String idDecrypt = decrypt(encodKey,ENCODE_KEY);  
        System.out.println(idDecrypt);  
        
        
        /******校验地址参数*****/
		 String address2 = "钱包地址参数，测试专用。符号.";		 		 
		 String source = address2 + appKey;
		 String sign2 = MD5.MD5Encode(source);
		 System.out.println("source:["+source+"]");
		 System.out.println("sign2:["+sign2+"]");
		
		}catch(Exception e){
			e.printStackTrace();
		}
    }  
  
    public static byte[] encrypt(byte[] src, String key) throws Exception {  
        Cipher cipher = Cipher.getInstance(AES);  
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);  
        cipher.init(Cipher.ENCRYPT_MODE, securekey);//设置密钥和加密形式  
        return cipher.doFinal(src);  
    }  
  
    public static byte[] decrypt(byte[] src, String key)  throws Exception  {  
        Cipher cipher = Cipher.getInstance(AES);  
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);//设置加密Key  
        cipher.init(Cipher.DECRYPT_MODE, securekey);//设置密钥和解密形式  
        return cipher.doFinal(src);  
    }  
      
    public static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1)  
                hs = hs + "0" + stmp;  
            else  
                hs = hs + stmp;  
        }  
        return hs.toUpperCase();  
    }  
  
    public static byte[] hex2byte(byte[] b) {  
        if ((b.length % 2) != 0)  
            throw new IllegalArgumentException("长度不是偶数");  
        byte[] b2 = new byte[b.length / 2];  
        for (int n = 0; n < b.length; n += 2) {  
            String item = new String(b, n, 2);  
            b2[n / 2] = (byte) Integer.parseInt(item, 16);  
        }  
        return b2;  
    }  
      
    public final static String decrypt(String data,String key) throws Exception{  
        return new String(decrypt(hex2byte(data.getBytes()),key));  

    }  
  
    public final static String encrypt(String data,String key) throws Exception{
        return byte2hex(encrypt(data.getBytes(), key));          
    }  
      
}
