package com.meiduimall.application.md1gwaccess.constant;

/**
 * HTTP请求相关常量
 * @author chencong
 *
 */
public abstract class HttpRConst {
	
	/*请求类型*/
	public final static String GET = "GET";
	public final static String POST = "POST";
	public final static String DELETE = "DELETE";
	public final static String PUT = "PUT";
	
	/*头部*/
	public final static String CONTENT_TYPE="Content-Type";
	
	/*请求的数据格式类型*/
	public final static String MEDIATYPE_JSON_FOR_APP = "application/json; charset=utf-8";//json
	public final static String MEDIATYPE_JSON = "application/json";//json
	public final static String MEDIATYPE_XML = "application/xml ";//xml
	public final static String MEDIATYPE_KEYVALUE = "application/x-www-form-urlencoded";//get字符串
	
}
