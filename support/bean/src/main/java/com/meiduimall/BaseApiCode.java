package com.meiduimall;
import java.util.HashMap;

import java.util.Map;

/**
 * test
 * @author chencong
 *
 */
public class BaseApiCode {
	
	public static final Map<Integer, String> zhMsgMap = new HashMap<Integer, String>(300);
	/** 参数无效 **/
	public static final Integer CLIENTID_PARAM_INVALID = 91;
	/** 参数无效 **/
	public static final Integer TIMESTAMP_PARAM_INVALID = 92;
	/** 参数无效 **/
	public static final Integer SIGN_PARAM_INVALID = 93;
	/** 用户未登陆 **/
	public static final Integer USER_NOT_LOGGED_IN = 94;
	/** 调用超出五分钟时间范围 **/
	public static final Integer ILLEGAL_CALL = 95;
	/** 签名验证失败 **/
	public static final Integer SIGN_INVALID = 96;
	/** 未发现秘钥 **/
	public static final Integer NOT_FOUND_SECRET = 97;
	/** 不是json类型 **/
	public static final Integer HTTP_CONTENTTYPE_INVALID = 98;
	/** json解析报错 **/
	public static final Integer JSON_INVALID = 99;
	/** get请求参数解析报错 **/
	public static final Integer GET_PARAM_INVALID = 100;
	/** 时间戳超时验证异常 **/
	public static final Integer TIMESTAMP_VALIDATE_EXCEPTION = 101;
	/** 用户登陆验证异常 **/
	public static final Integer USERLOGIN_VALIDATE_EXCEPTION = 102;
	/** 签名验证异常 **/
	public static final Integer SIGN_VALIDATE_EXCEPTION = 103;
	/** 网关层过滤器异常 **/
	public static final Integer GATEWAY_EXCEPTION = 104;
	/** token参数不存在 **/
	public static final Integer TOKEN_NOT_EXISTS = 105;
	/** token验证异常 **/
	public static final Integer TOKEN_VALIDATE_EXCEPTION = 106;
	/** 黑名单验证不通过 **/
	public static final Integer BLACKLIST_VALIDATE = 107;
	/** 黑名单验证异常 **/
	public static final Integer BLACKLIST_VALIDATE_EXCEPTION = 108;

	public static String getZhMsg(Integer errorCode) {
		return zhMsgMap.get(errorCode);
	}

	static {
		zhMsgMap.put(CLIENTID_PARAM_INVALID, "clientID请求参数不存在");
		zhMsgMap.put(TIMESTAMP_PARAM_INVALID, "timestamp请求参数不存在或者非数字");
		zhMsgMap.put(SIGN_PARAM_INVALID, "sign请求参数不存在");
		zhMsgMap.put(TOKEN_NOT_EXISTS, "token请求参数不存在");
		zhMsgMap.put(USER_NOT_LOGGED_IN, "用户未登陆");
		zhMsgMap.put(ILLEGAL_CALL, "调用时间戳超出五分钟时间范围");
		zhMsgMap.put(SIGN_INVALID, "签名验证失败");
		zhMsgMap.put(NOT_FOUND_SECRET, "未发现秘钥");
		zhMsgMap.put(HTTP_CONTENTTYPE_INVALID, "HTTP的ContentType不是Json类型");
		zhMsgMap.put(JSON_INVALID, "解析Json失败");
		zhMsgMap.put(GET_PARAM_INVALID, "get请求参数解析失败");
		zhMsgMap.put(BLACKLIST_VALIDATE, "黑名单验证不通过");
		zhMsgMap.put(TIMESTAMP_VALIDATE_EXCEPTION, "时间戳超时验证异常");
		zhMsgMap.put(USERLOGIN_VALIDATE_EXCEPTION, "用户登陆验证异常");
		zhMsgMap.put(SIGN_VALIDATE_EXCEPTION, "签名验证异常");
		zhMsgMap.put(GATEWAY_EXCEPTION, "网关层过滤器验证异常");
		zhMsgMap.put(TOKEN_VALIDATE_EXCEPTION, "token验证异常");
		zhMsgMap.put(BLACKLIST_VALIDATE_EXCEPTION, "黑名单验证异常");
	}

}
