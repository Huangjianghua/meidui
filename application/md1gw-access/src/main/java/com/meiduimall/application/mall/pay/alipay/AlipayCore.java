package com.meiduimall.application.mall.pay.alipay;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meiduimall.application.mall.util.Logger;

public class AlipayCore {


	/** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {


        Map<String, String> result = new HashMap<>();


        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (Map.Entry<String, String> entry : sArray.entrySet()) {
        	String key = entry.getKey();
        	String value = entry.getValue();
            if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key)
                || "sign_type".equalsIgnoreCase(key)) {
                continue;
            }
            result.put(key, value);
        }


        return result;
    }


    /** 
     * 把数组所有元素，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要参与字符拼接的参数组
     * @param sorts   是否需要排序 true 或者 false
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {


        List<String> keys = new ArrayList<>(params.keySet());


        Collections.sort(keys);


        
        StringBuilder prestr = new StringBuilder();


        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);


            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
            	prestr.append(key);
            	prestr.append("=");
            	prestr.append(value);
            } else {
            	prestr.append(key);
            	prestr.append("=");
            	prestr.append(value);
            	prestr.append("&");
            }
        }


        return prestr.toString();
    }


    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord,String filename) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(AlipayConfig.LOG_PATH + "alipay_log_" + System.currentTimeMillis()+filename+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            Logger.error("system error: %s",e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                	Logger.error("system error: %s",e);
                }
            }
        }
    }
}
