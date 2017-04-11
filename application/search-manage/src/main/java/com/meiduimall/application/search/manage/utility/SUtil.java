package com.meiduimall.application.search.manage.utility;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 系统公用方法集
 * 
 * @author Administrator
 * 
 */
public class SUtil {

	/**
	 * 判断某字符串是否为null，如果长度大于256，则返回256长度的子字符串，反之返回原串
	 * 
	 * @param str
	 * @return
	 */
	public static String checkStr(String str) {
		if (str == null) {
			return "";
		} else if (str.length() > 256) {
			return str.substring(256);
		} else {
			return str;
		}
	}

	/**
	 * 验证是不是Int validate a int string
	 * 
	 * @param str
	 *            the Integer string.
	 * @return true if the str is invalid otherwise false.
	 */
	public static boolean validateInt(String str) {
		if (str == null || str.trim().equals(""))
			return false;

		char c;
		for (int i = 0, l = str.length(); i < l; i++) {
			c = str.charAt(i);
			if (!((c >= '0') && (c <= '9')))
				return false;
		}

		return true;
	}

	/**
	 * 是否为空
	 * 
	 * @param str
	 *            待校验字符串
	 * @return 是否为空 true 为空，false不为空
	 */
	public static boolean isEmpty(String str) {
		if(str ==null){
			return true;
		}
		if("null".equalsIgnoreCase(str)){
			return true;
		}
		return org.apache.commons.lang.StringUtils.isEmpty(str);
	}

	/**
	 * 是否非空
	 * 
	 * 任何一个字符串为空,将返回false
	 * 
	 * @param strs
	 *            待校验字符串
	 * @return 是否非空
	 */
	public static boolean isNotBlank(String... strs) {
		if (null == strs) {
			return false;
		}

		for (String str : strs) {
			if (!org.apache.commons.lang.StringUtils.isNotBlank(str)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否是数字
	 * 
	 * @param str
	 *            目标字符串
	 * @return 是否是数字
	 */
	public static boolean isNumeric(String str) {
		return org.apache.commons.lang.StringUtils.isNumeric(str);
	}

	/**
	 * 是否为数字 任何一个字符串不是数字，将返回false
	 * 
	 * @param strs
	 *            待校验字符串
	 * @return 是否非空
	 */
	public static boolean isAllNumeric(String... strs) {
		if (null == strs) {
			return false;
		}
		for (String str : strs) {
			if (!org.apache.commons.lang.StringUtils.isNumeric(str)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断一个Interger是否能转为数字,只有当interger为null才不能转换
	 */
	public static boolean isNumeric(Integer str) {
		if (str == null) {
			return false;
		}
		return true;
	}

	public static Integer toInt(Integer str) {
		if (isNumeric(str)) {
			return str;
		}
		return 0;
	}
	
	public static Long toLong(String str){
		if("".equals(str)||str==null){
			return 0L;
		}
		if(isNumeric(str)){
			return Long.valueOf(str);
		}
		return 0L;
	}
	/**
	 * 返回yyyy-mm-dd
	 * 
	 * @param aDate
	 * @return
	 */
	public static final String formatdate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat("yyyy-MM-dd");
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final String formatdate(Date aDate,String format) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(format);//"yyyy-MM-dd HH:mm:ss"
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
	
	/**
	 * 将格式为yyyy-MM-dd的日期字符串转为java.util.Date
	 * @param dateStr
	 * @return
	 */
	public static Date converseToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date theDate = null;
		try {
			theDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theDate;
	}
	
	/**
	 * Interger 转换为String
	 */
	public static String formatInt(Integer i) {

		if (isNumeric(i))
			return String.valueOf(i);
		return "0";
	}
	
	/**
	 * String 转换为Integer
	 */
	public static int formatStr(String str){
		if("".equals(str)||str==null){
			return 0;
		}
		if(isNumeric(str)){
			return Integer.valueOf(str);
		}
		return 0;
	}
	
	public static int formatStr(Object str){
		if("".equals(str)||str==null){
			return 0;
		}
		if(isNumeric(str.toString())){
			return Integer.valueOf(str.toString());
		}
		return 0;
	}
	
	/**
	   * 判断字符串数组中是否包含某字符串元素
	   *
	   * @param substring 某字符串
	   * @param source    源字符串数组
	   * @return 包含则返回true，否则返回false
	   */
	  public static boolean isIn(String substring, String[] source) {
	    if (source == null || source.length == 0) {
	      return false;
	    }
	    for (int i = 0; i < source.length; i++) {
	      String aSource = source[i];
	      if (aSource.equals(substring)) {
	        return true;
	      }
	    }
	    return false;
	  }
  
	  
  	/*
	 * 基础数据类型转换为string
	 */
	public static String converString(Object o) {
		if (o == null) {
			return "";
		}
		return o.toString();
	}
	
	public static int convertInt(Object str){
		if("".equals(str)||str==null){
			return 0;
		}
		if(isNumeric(str.toString())){
			return Integer.valueOf(str.toString());
		}
		return 0;
	}
	
	//获取当前日期
	public static String getNowDate(String formatDate){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
			String dtstr = sdf.format(new Date());
			return dtstr;
		} catch (Exception e) {
			return new Date().toLocaleString();
		}
	}
	
	/**
	 * 将时间戳转换为日期
	 * @param timeStampStr
	 * @return 返回日期
	 * @author Liujun
	 * @date 2015-08-06
	 */
	public static Date getDateFromTimeStamp(String timeStampStr) {
		Date convertTime = null;
		if(timeStampStr != null && !"".equalsIgnoreCase(timeStampStr)) {
			convertTime = new Date(Long.parseLong(timeStampStr));
		}
		return convertTime;
	}
	
	/**
	 * 根据给定的路径新建文件夹
	 * @param dir   文件路径
	 * @return
	 */
	public static boolean createFolder(String dir){
		boolean flag = false;
		String path = new File(dir).getAbsolutePath();
		String separator = System.getProperty("file.separator");//系统通用分隔符
		path = path.substring(0, path.lastIndexOf(separator));
		System.out.println("=="+path);
		File f = new File(path);
		if(!f.exists()){
			flag = f.mkdirs();
		}
		return flag;
	}
	
	public static String formatFileSize(long size){  
        DecimalFormat formater = new DecimalFormat("####0.00");         
        if(size<1000L){  
            return size+"bytes";  
        }else if(size<1000L*1000L){  
            float kbsize = size/1024f;    
            System.out.println(kbsize);
            return formater.format(kbsize)+"KB";  
        }else if(size<1000L*1000L*1000L){  
            float mbsize = size/1024f/1024f; 
            System.out.println(mbsize);
            return formater.format(mbsize)+"MB";  
        }else if(size<1000L*1000L*1000L*1000L){  
            float gbsize = size/1024f/1024f/1024f; 
            System.out.println(gbsize);
            return formater.format(gbsize)+"GB";  
        }else{  
            return "0";  
        } 
	}
	

	//解析介绍图片的json字符串
	public static List<String> decodeJson(String json){
		List<String> images = new ArrayList<String>();
		if(json!= null && json.length()>5){
			try {
				JSONArray jsonArray = JSONArray.parseArray(json);
				Object[] array= jsonArray.toArray();
				for(int i=0;i<array.length;i++){
					JSONObject jsonObject = JSONObject.parseObject(array[i].toString());
					images.add(jsonObject.get("url").toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		return images;
	} 
	
	//根据固件的下载路径获取文件名
	public static String getDownName(String name){
		String downName ="";
		try {
			int idnex1 = name.lastIndexOf("/");
			if(idnex1>10){
				name = name.substring(name.lastIndexOf("/")+1,name.length());
			}
			
			int index = name.lastIndexOf("_");
			
			if(index>10){
				downName = name.substring(0, index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downName;
	}
	
	public static void main(String[] args) {
//		System.out.println(getDownName("roms/2015/11/16/10029/vivo_Y系列_移动4G(Y29L,Android 4.4.4,PD1420L_A_1.12.3,Funtouch OS_2.0)_20151031115651.zip"));
		System.out.println(formatFileSize(1057860330L));
		 DecimalFormat formater = new DecimalFormat("####0.00");
		System.out.println(formater.format(0.9852092));
		 
	}
}
