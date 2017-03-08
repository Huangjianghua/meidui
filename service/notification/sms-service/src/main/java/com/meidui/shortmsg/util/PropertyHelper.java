package com.meidui.shortmsg.util;

import java.io.UnsupportedEncodingException;

/**
 * 类名: PropertyHelper
 * 描述: 编码转换
 * 编写者:   
 * 版权: Copyright (C) 2016 first版权所有
 * 创建时间: 2016年7月19日
 */
public class PropertyHelper {

	public static String isoToUtf8(String value){
		try {
			return new String(value.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
