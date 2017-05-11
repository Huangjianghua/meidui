package com.meiduimall.application.mall.util;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.meiduimall.application.mall.constant.MallApiCode;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.password.exception.Md5Exception;
import com.meiduimall.password.util.MD5;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

public class GatewaySignUtil {
	
	

	/* &符号 */
	public static final String URLCONNCTION = "&";
	/* =符号 */
	public static final String URLEQUALS = "=";
	/**
	 * 编码类型
	 */
	public static final String INPUT_CHARSET_DEFAULT = "utf-8"; // 默认utf-8
	
	
	/**
	 * @throws Md5Exception 
	 * 功能描述:  产生签名
	 * Author: 陈建宇
	 * Date:   2016年12月19日 上午10:28:34
	 * @param appKey
	 * @param param
	 * @return String   
	 */
	public static String  sign(String appKey,Map<String,String> param) throws Md5Exception {
		Map<String, String> map = new TreeMap<>();
		map.putAll(param);
		Set<String> keySet = map.keySet();
		StringBuilder buffer = new StringBuilder();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if ("sign".equals(key)){
            	continue;
            }
            String value = map.get(key);
            if ( value == null || value.length() < 1 ) {
            	continue;
            }
            buffer.append(key);
            buffer.append(URLEQUALS);
            buffer.append(value);
            buffer.append(URLCONNCTION);
        }
        buffer.append("key=");
        buffer.append(appKey);
        return MD5.encode(buffer.toString().toUpperCase());
	}
	
	
	@SuppressWarnings("rawtypes")
	public static String  buildsign(String appKey,JSONObject param) throws Md5Exception {
		StringBuilder buffer = new StringBuilder();
		String key;
		String value;
		Iterator iterator = param.keys();
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			value = param.getString(key);
            if ( value == null || value.length() < 1 || "sign".equals(key) || StringUtils.isEmpty(value)) {
            	continue;
            }
			buffer.append(key);
			buffer.append(URLEQUALS);
			buffer.append(value);
			buffer.append(URLCONNCTION);
		}
		String[] split = buffer.toString().split(URLCONNCTION);
		Arrays.sort(split);
		String concat = StringUtils.join(split, URLCONNCTION).concat(URLCONNCTION).concat("key=").concat(appKey);
        return MD5.encode(concat.toUpperCase());
	}
	
	/**
	 * 功能描述: 产生签名的字符串
	 * Author: 陈建宇
	 * Date:   2016年12月19日 上午10:28:55
	 * @param appKey
	 * @param parameters
	 * @return String   
	 */
	public static String buildEncodeSortParam(String appKey,Map<String, String> parameters) {
		StringBuilder getSB = new StringBuilder();
		String key;
		String value;
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
			if ("sign".equals(key)||StringUtils.isEmpty(value)) {
				continue;
			}
			try {
				getSB.append(key).append(URLEQUALS)
						.append(URLEncoder.encode(value, INPUT_CHARSET_DEFAULT))
						.append(URLCONNCTION);
			} catch (Exception e) {
				Logger.error("产生签名的字符串错误: %s", e);
				throw new ServiceException(MallApiCode.SIGN_ERROR, MallApiCode.getZhMsg(MallApiCode.SIGN_ERROR));
			}
		}
		String[] arrs = getSB.toString().split(URLCONNCTION);
		Arrays.sort(arrs);
		return StringUtils.join(arrs, URLCONNCTION).concat("key=").concat(appKey);
	}
	


}
