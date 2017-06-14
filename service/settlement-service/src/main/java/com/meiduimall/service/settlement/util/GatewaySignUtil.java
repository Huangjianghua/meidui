package com.meiduimall.service.settlement.util;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class GatewaySignUtil {
	
	private static final Logger Logger = LoggerFactory.getLogger(GatewaySignUtil.class);
	
	/** &符号**/
	public final static String URLCONNCTION = "&";
	/** =符号**/
	public final static String URLEQUALS = "=";
	/**
	 * 编码类型
	 */
	public final static String INPUT_CHARSET_DEFAULT = "utf-8"; // 默认utf-8
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	
	@SuppressWarnings("rawtypes")
	public static String buildsign(String appKey,JSONObject param) {
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
        return MD5Encode(concat).toUpperCase();
	}
	

	public static String MD5Encode(String origin) {
		String resultString = null;

		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			Logger.error("系统错误!: %s", ex);
		}
		return resultString;
	}
	
	public static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

}
