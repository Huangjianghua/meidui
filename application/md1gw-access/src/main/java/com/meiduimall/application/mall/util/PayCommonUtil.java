package com.meiduimall.application.mall.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class PayCommonUtil {
	
    private PayCommonUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	//定义签名，微信根据参数字段的ASCII码值进行排序 加密签名,故使用SortMap进行参数排序
    @SuppressWarnings("rawtypes")
	public static String createSign(String characterEncoding,SortedMap<String,String> parameters){
        StringBuilder sb = new StringBuilder();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
//        sb.append("key=" + ConstantUtil.PARTNER_KEY);//最后加密时添加商户密钥，由于key值放在最后，所以不用添加到SortMap里面去，单独处理，编码方式采用UTF-8
        String sign = MD5Util.mD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }
 
//将封装好的参数转换成Xml格式类型的字符串

    @SuppressWarnings("rawtypes")
	public static String getRequestXml(SortedMap<String,String> parameters){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if("sign".equalsIgnoreCase(k)){

            }
            else if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)) {
                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
            }
            else {
                sb.append("<"+k+">"+v+"</"+k+">");
            }
        }
        sb.append("<"+"sign"+">"+"<![CDATA["+parameters.get("sign")+"]]></"+"sign"+">");
        sb.append("</xml>");
        return sb.toString();
    }
    
    
}
