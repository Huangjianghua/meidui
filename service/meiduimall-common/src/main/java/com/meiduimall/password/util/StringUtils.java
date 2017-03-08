package com.meiduimall.password.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 字符串操作工具类。
 * 
 * @author 
 * @version 
 * @since 
 */
public final class StringUtils
{
	private static final char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 将16进制字符串转换为字节数组。
	 * 
	 * @param hexStr
	 *            16进制字符串
	 * @return 字节数组
	 */
	public static byte[] hexToBytes(String hexStr)
	{
		int fromLen = hexStr.length();
		int toLen = (fromLen + 1) / 2;
		
		final byte[] b = new byte[toLen];
		
		for (int i = 0; i < toLen; i++)
		{
			b[i] = (byte) hexPairToInt(hexStr.substring(i * 2, (i + 1) * 2));
		}
		
		return b;
	}

	/**
	 * 将字节数组转换为16进制字符串。
	 * 
	 * @param bytes
	 *            字节数组
	 * @return 16进制字符串
	 */
	public static String toHex(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		
		for (byte b : bytes)
		{
			sb.append(hex[((b >> 4) & 0xF)]).append(hex[((b >> 0) & 0xF)]);
		}
		
		return sb.toString();
	}

	// 将16进制字符串转换为整型
	private static int hexPairToInt(String hexStr)
	{
		String digits = String.valueOf(hex);
		
		hexStr = hexStr.toLowerCase();
		
		int n = 0;
		int thisDigit = 0;
		int len = hexStr.length();
		
		if (len > 2)
			len = 2;
		
		for (int i = 0; i < len; i++)
		{
			thisDigit = digits.indexOf(hexStr.substring(i, i + 1));
			
			if (thisDigit < 0)
				throw new NumberFormatException();
			
			if (i == 0)
				thisDigit *= 0x10;
			
			n += thisDigit;
		}
		
		return n;
	}

}
