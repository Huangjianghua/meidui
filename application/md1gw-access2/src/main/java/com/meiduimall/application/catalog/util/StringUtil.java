package com.meiduimall.application.catalog.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * 字符串帮助类，提供各种字符串处理函数
 * @author madongdong
 *
 */
public class StringUtil{

	/**
	 * 首字母大写处理
	 * @param name
	 * @return
	 */
	public static String capitalize(String name) {
		if ((name == null) || (name.length() <= 0)) {
			return name;
		}

		char first = name.charAt(0);
		if (Character.isUpperCase(first)) {
			return name;
		}
		char[] chars = name.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return new String(chars);
	}

	/**
	 * 首字母小写处理
	 * @param name
	 * @return
	 */
	public static String decapitalize(String name) {
		if ((name == null) || (name.length() <= 0)) {
			return name;
		}

		char first = name.charAt(0);
		if (Character.isLowerCase(first)) {
			return name;
		}
		char[] chars = name.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		return new String(chars);
	}
	
	/**
	 * 数字格式化处理	pattern: ##.##
	 * @param o
	 * @param pattern
	 * @return
	 */
	public static String formatNumber(Object o, String pattern) {
		Double d = Double.parseDouble(String.valueOf(o));
		if(d == null)
			return null;
        return new DecimalFormat(pattern).format(d);
    }

	/**
	 * 日期格式化处理
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern){
		if(date == null)
			return null;
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static String transformToDate(long ms){
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = ms / dd;
		long hour = (ms - day * dd) / hh;
		long minute = (ms - day * dd - hour * hh) / mi;
		long second = (ms - day * dd - hour * hh - minute * mi) / ss;

		String strDay = day < 10 ? "0" + day : "" + day;
		String strHour = hour < 10 ? "0" + hour : "" + hour;
		String strMinute = minute < 10 ? "0" + minute : "" + minute;
		String strSecond = second < 10 ? "0" + second : "" + second;
		
		return strDay + "天" + strHour + "时" + strMinute + "分" + strSecond + "秒";
	}
	
	/**
	 * 字符串拼接
	 * @param o
	 * @param separator
	 * @return
	 */
	public static String join(Object o, String separator) {
        if (o == null)
            return null;
        
        Iterator<?> iterator = null;
        if (o instanceof Collection) {
            iterator = ((Collection<?>) o).iterator();
        } else if (o instanceof Iterator) {
            iterator = (Iterator<?>) o;
        } else if (o instanceof Object[]) {
            iterator = Arrays.asList((Object[]) o).iterator();
        } else {
            return null;
        }
        
        StringBuilder buf = new StringBuilder();
        while (iterator.hasNext()) {
            buf.append(iterator.next());
            if (iterator.hasNext()) {
                buf.append(separator);
            }
        }
        return buf.toString();
    }
	
	public static String join(Object o){
		return join(o, ",");
	}
	
	/**
	 * 安全截取字符串长度
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String substring(String str, int start, int end) {
        if (str == null)
            return null;
        
        if(start < 0)
        	start = 0;
        
        if(end > str.length())
        	end = str.length();
        
        return str.substring(start, end);
    }
	
	/**
	 * 截取字符串长度，如果超出指定长度，则用...省略
	 * @param str
	 * @param n
	 * @return
	 */
	public static String fixLength(String str, int n){
		if(str == null || str.length() <= n)
			return str;
		
		return StringUtil.substring(str, 0, n) + "...";
	}
	
	/**
	 * 生成随机数字组成的字符串，可以指定长度
	 * @param length
	 * @return
	 */
	public static String genRandomNumber(int length){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++){
			sb.append(new Random().nextInt(10));
		};
		
		return sb.toString();
	}
	
	
	/**
	 * 将map对象转化为xml格式
	 * @param params
	 * @return
	 */
	public static String toXml(Map<String,Object> params){
		if(params == null)
			return null;
		
		StringBuffer sb = new StringBuffer("<xml>");
		for(Entry<String,Object> entry : params.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue() != null ? entry.getValue().toString() : null;
			if(value == null)
				continue;
			
			sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
		}
		sb.append("</xml>");
		
		return sb.toString();
	}
	
	/**
	 * 将map对象转化为url字符串格式
	 * @param params
	 * @return
	 */
	public static String toUrl(Map<String,Object> params){
		if(params == null)
			return null;
		
		StringBuffer sb = new StringBuffer();
		
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			Object value = params.get(key);
			if(value == null)
				continue;
			
			if(sb.length() > 0)
				sb.append("&");
			sb.append(key).append("=").append(value.toString());
		}
		
		return sb.toString();
	}
	
	public static boolean isEmpty(String str){
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * 检查字符串是否为空
	 * 
	 * @param value
	 * @return true 为空
	 */
	public static boolean isEmptyByString(String value) {
		return ((value == null)  || "null".equals(value) || "".equals(value) || (value.trim().length() == 0));
	}
}