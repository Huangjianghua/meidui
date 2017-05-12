package com.meiduimall.application.mall.constant;

/**
 * HTTP请求相关常量
 * @author chencong
 *
 */
public abstract class HttpRConst {
	
	/*请求类型*/
	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String DELETE = "DELETE";
	public static final String PUT = "PUT";
	
	/*头部*/
	public static final String CONTENT_TYPE="Content-Type";
	
	/*请求的数据格式类型*/
	public static final String MEDIATYPE_JSON_FOR_APP = "application/json; charset=utf-8";//json
	public static final String MEDIATYPE_JSON = "application/json";//json
	public static final String MEDIATYPE_XML = "application/xml ";//xml
	public static final String MEDIATYPE_KEYVALUE = "application/x-www-form-urlencoded";//get字符串
	
}
