package com.meiduimall.application.mall.util;

import java.security.MessageDigest;

public class MD5 {
	

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


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

	public static String mD5Encode(String origin) {
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
	
	public static String getMD5EncodeUTF8(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin.getBytes("UTF-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
           Logger.error("系统错误!: %s", ex);
		}
		return resultString;
	}
	
	public static String getASPMD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin.getBytes("UTF-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			Logger.error("系统错误!: %s", ex);
		}
		return resultString;
	}
	
	public static String getMD5EncodeGBK(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin.getBytes("GBK"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			Logger.error("系统错误!: %s", ex);
		}
		return resultString;
	}
}