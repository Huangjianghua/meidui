package com.meiduimall.application.usercenter.constant;

/**
 * 其他系统参数名称定义
 * @author chencong
 *
 */
public class ConstSysParamsDefination {
	
	/**请求有效时间(5分钟)*/
	public static final Long REQUEST_EFFECTIVE_TIME = 5 * 60 * 1000L;
	
	/**时间戳字符串*/
	public static final String TIMESATAMP = "timestamp";
	
	/**签名*/
	public static final String SIGN = "sign";
	
	/**key后缀*/
	public static final String KEY_LAST= "key=";
	
	/**应用标识*/
	public static final String CLIENTID = "clientID";
	
	/**会员ID*/
	public static final String MEMID = "memId";
	
	/**storage clientID and key*/
	public static final String memberSecretJson="memberSecretJson";
	
	/**token*/
	public static final String TOKEN = "token";
	
	/**符号*/
	public static final String CONNECTION_SYMBOL = "&";
	
	/**=符号 */
	public static final String EQUALS_SYMBOL = "=";
	
	/**=符号 */
	public static final String CONTENT_TYPE = "Content-Type";
	
	/**HTTP请求方式*/
	public static final String HTTP_GET="GET";
	public static final String HTTP_POST="POST";
	
}
