package com.meiduimall.application.mall.util;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.meiduimall.application.mall.exception.MallApiCode;
import com.meiduimall.exception.ServiceException;


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
        	Logger.info("日期转换错误!: %s", e);
        	throw new ServiceException(MallApiCode.DATE_ERROR, MallApiCode.getZhMsg(MallApiCode.DATE_ERROR));
        }  
    }  
    
    
    public static String timeStamp(Long date_str,String format){  
    	try {  
    		SimpleDateFormat sdf = new SimpleDateFormat(format);  
    		return sdf.format(date_str);
    	} catch (Exception e) {  
    		Logger.error("日期转换错误!: %s", e);
    		throw new ServiceException(MallApiCode.DATE_ERROR, MallApiCode.getZhMsg(MallApiCode.DATE_ERROR));
    	}  
    }  
    
    public static String formatTiemStr(String date_str){  
    	try {  
        	String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
        	return date_str.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
    	} catch (Exception e) {  
    		Logger.info("日期转换错误:%s", e);
    		throw new ServiceException(MallApiCode.DATE_ERROR, MallApiCode.getZhMsg(MallApiCode.DATE_ERROR));
    	}  
    	 
    }  
    
}
