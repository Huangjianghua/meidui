package com.meidui.shortmsg.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ToolsUtil {

	/**
	 * Description : 加载配置文件 Created By : Kaixuan.Feng Creation Time :
	 * 2016年12月14日 下午3:02:49
	 * 
	 * @param config
	 * @return
	 */
	public static Map<String, String> loadProperty(String config) {
		InputStream is = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
			Properties pro = new Properties();
			pro.load(is);
			Iterator<Object> localIterator = pro.keySet().iterator();
			while (localIterator.hasNext()) {
				Object key = localIterator.next();
				map.put(key.toString(), pro.get(key).toString());
			}
		} catch (Exception ex) {
			System.out.println("配置文件:" + config + "加载出错!");
			ex.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * Description : 十六进制转二进制
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月14日 下午3:05:46 
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
	 * Description : 二进制转16进制
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月14日 下午3:05:59 
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
}