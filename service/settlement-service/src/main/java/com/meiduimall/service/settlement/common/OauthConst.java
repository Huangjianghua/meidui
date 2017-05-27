package com.meiduimall.service.settlement.common;

/**
 * Title : OauthConst 
 * Description : Oauth常量
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月16日 下午2:47:30 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
public class OauthConst {
	/** 请求有效时间(5分钟) */
	public static final Long REQUEST_EFFECTIVE_TIME = 5 * 60 * 1000L;
	
	
	/** 时间戳字符串 */
	public static final String TIMESATAMP = "timestamp";
	
	/** 签名 */
	public static final String SIGN = "sign";
	
	/** 授权Key字符串 */
	public static final String SECRETKEY_NAME = "key=";
	
	/**前端用户标识 */
	public static final String CLIENT_ID = "clientID";
	
	/** token字符串 */
	public static final String TOKEN = "token";
	
	/** &符号 */
	public static final String CONNECTION_SYMBOL = "&";
	
	/** =符号 */
	public static final String EQUALS_SYMBOL = "=";
	
	/** ? 符号 */
	public static final String QUESTION_SYMBOL = "?";
	
	/** &&& 符号 */
	public static final String AND_SYMBOL = "&&&";
	
	/** token通过标志 */
	public static final String TOKEN_PASS="tokenpass";
	
	public static final String CLIENT_ID_VALUE="payment_java";
	
	public static final String SECRETKEY_VALUE="Test123";
}
