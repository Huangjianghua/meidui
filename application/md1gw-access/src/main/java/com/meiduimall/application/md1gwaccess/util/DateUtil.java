package com.meiduimall.application.md1gwaccess.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {

	public static String DataUtils(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
		
	}
	
	public static String orderTime(String unix) {
		Long timestamp = Long.parseLong(unix) * 1000;
	    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));
	    return date;
	}
	
	 
	/** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
    
    
    public static String timeStamp(Long date_str,String format){  
    	try {  
    		SimpleDateFormat sdf = new SimpleDateFormat(format);  
    		return sdf.format(date_str);
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	}  
    	return "";  
    }  
    
}
