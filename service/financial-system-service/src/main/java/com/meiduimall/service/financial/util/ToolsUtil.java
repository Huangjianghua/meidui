package com.meiduimall.service.financial.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;

public class ToolsUtil {

	/**
	 * Description : 十六进制转二进制 Created By : Kaixuan.Feng Creation Time :
	 * 2016年12月14日 下午3:05:46
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * Description : 二进制转16进制 Created By : Kaixuan.Feng Creation Time :
	 * 2016年12月14日 下午3:05:59
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte bytes[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 获取六位验证码
	 * 
	 * @return
	 */
	public static String getValidateCode() {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return String.valueOf(tmp % (999999 - 100000 + 1) + 100000);
	}

	public static String getSign(JSONObject jsonObject) throws Exception {
		String syssign = null;
		String[] arr = new String[jsonObject.size() - 1];
		int i = 0;
		try {
			for (String key : jsonObject.keySet()) {
				if ("sign".equals(key))
					continue;
				else
					arr[i] = key + "=" + jsonObject.getString(key);
				i++;
			}
			Arrays.sort(arr);
			StringBuffer buffer = new StringBuffer();
			for (int k = 0; k < arr.length; k++) {
				buffer.append(arr[k]);
				buffer.append("&");
			}
			buffer.append("key=");
			buffer.append("test123");
			syssign = MD5Util.MD5EncryptBy32(buffer.toString()).toUpperCase();
		} catch (Exception e) {
			throw e;
		}
		return syssign;
	}

}