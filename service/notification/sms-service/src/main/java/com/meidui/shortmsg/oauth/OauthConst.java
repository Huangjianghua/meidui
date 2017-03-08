package com.meidui.shortmsg.oauth;

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
	/** 请求有效时间(分钟) */
	public static final Long REQUEST_EFFECTIVE_TIME = 5 * 60 * 1000L;
	
	/** 时间戳字符串 */
	public static final String TIMESATAMP = "timesatamp";
	
	/** 签名 */
	public static final String SIGN = "sign";
	
	/** 授权Key字符串 */
	public static final String SECRETKEY_NAME = "secretKey=";
	
	/** 授权key模拟值 */
	public static final String SECRETKEY_VALUE = "123";
	
	/** token字符串 */
	public static final String TOKEN = "token";
}
