package com.meiduimall.core;
import java.util.HashMap;

import java.util.Map;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: BaseApiCode.java
 * Author:   陈建宇
 * Date:     2017年3月14日 上午10:16:57
 * Description: //模块目的、功能描述
 */
public class BaseApiCode {
	
	public static final Map<Integer, String> zhMsgMap = new HashMap<Integer, String>(300);
	/** 系统正常**/
	public static final Integer SUCCESS = 1000;
	/** 系统异常 **/
	public static final Integer OPERATE_SYSTEM_FAIL = 1001;
	/** 参数无效 **/
	public static final Integer CLIENTID_PARAM_INVALID = 2000;
	/** 参数无效 **/
	public static final Integer TIMESTAMP_PARAM_INVALID = 2001;
	/** 参数无效 **/
	public static final Integer SIGN_PARAM_INVALID = 2002;
	/** 用户未登陆 **/
	public static final Integer USER_NOT_LOGGED_IN = 2003;
	/** 调用超出五分钟时间范围 **/
	public static final Integer ILLEGAL_CALL = 2004;
	/** 签名验证失败 **/
	public static final Integer SIGN_INVALID = 2005;
	/** 未发现秘钥 **/
	public static final Integer NOT_FOUND_SECRET = 2006;
	/** 不是json类型 **/
	public static final Integer HTTP_CONTENTTYPE_INVALID = 2007;
	/** json解析报错 **/
	public static final Integer JSON_INVALID = 2008;
	/** get请求参数解析报错 **/
	public static final Integer GET_PARAM_INVALID = 2009;
	/** 时间戳超时验证异常 **/
	public static final Integer TIMESTAMP_VALIDATE_EXCEPTION = 2010;
	/** 用户登陆验证异常 **/
	public static final Integer USERLOGIN_VALIDATE_EXCEPTION = 2011;
	/** 签名验证异常 **/
	public static final Integer SIGN_VALIDATE_EXCEPTION = 2012;
	/** 网关层过滤器异常 **/
	public static final Integer GATEWAY_EXCEPTION = 2013;
	/** token参数不存在 **/
	public static final Integer TOKEN_NOT_EXISTS = 2014;
	/** token验证异常 **/
	public static final Integer TOKEN_VALIDATE_EXCEPTION = 2015;
	/** 黑名单验证不通过 **/
	public static final Integer BLACKLIST_VALIDATE = 2016;
	/** 黑名单验证异常 **/
	public static final Integer BLACKLIST_VALIDATE_EXCEPTION = 2017;
	
	/** 短信服务请勿频繁重复发送短信**/
	public static final Integer REPEAT_FAIL = 3000;
	/** 短信服务获取短信模板列表失败**/
	public static final Integer TEMPLATE_FAIL = 3001;
	/** 短信服务获取不到模板id对应短信模板记录**/
	public static final Integer SMSTEMPLATE_NOT_EXISTS = 3002;
	/** 替换内容与替换参数不匹配**/
	public static final Integer SMSTEMPLATE_PRASE_FAIL = 3003;
	/** 短信发送失败**/
	public static final Integer SMS_SEND_FAIL = 3004;
	/** 短信渠道错误**/
	public static final Integer SMS_CHANNEL_FAIL = 3005;
	/** 发送短信异常**/
	public static final Integer SMS_SEND_EXCEPTION = 3006;
	
	public static String getZhMsg(Integer errorCode) {
		return zhMsgMap.get(errorCode);
	}

	static {
		zhMsgMap.put(SUCCESS, "系统正常");
		zhMsgMap.put(OPERATE_SYSTEM_FAIL, "系统异常");
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
		
		zhMsgMap.put(REPEAT_FAIL, "请勿频繁重复发送短信");
		zhMsgMap.put(TEMPLATE_FAIL, "获取短信模板列表失败");
		zhMsgMap.put(SMSTEMPLATE_NOT_EXISTS, "获取不到模板id对应短信模板记录");
		zhMsgMap.put(SMSTEMPLATE_PRASE_FAIL, "替换内容与替换参数不匹配");
		zhMsgMap.put(SMS_SEND_FAIL, "短信发送失败");
		zhMsgMap.put(SMS_CHANNEL_FAIL, "短信渠道失败");
		zhMsgMap.put(SMS_SEND_EXCEPTION, "发送短信异常");
	}

}
