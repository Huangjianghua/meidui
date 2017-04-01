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
	/** 正常**/
	public static final Integer SUCCESS = 0;
	/** 失败 **/
//	public static final Integer FAIL = 1;
	
	/** clientID参数无效 **/
	public static final Integer NOT_EXISTS_CLIENTID = 1000;
	/** 时间戳参数无效 **/
	public static final Integer NOT_EXISTS_TIMESTAMP = 1001;
	/** 签名参数无效 **/
	public static final Integer NOT_EXISTS_SIGN = 1002;
	/** 短信服务获取不到模板id对应短信模板记录**/
	public static final Integer NOT_EXISTS_SMSTEMPLATE = 1003;
	/** 未发现秘钥 **/
	public static final Integer NOT_EXISTS_SECRET = 1004;
	
	
	/** 签名验证失败 **/
	public static final Integer FAIL_SIGN = 2000;
	/** 调用超出五分钟时间范围 **/
	public static final Integer FAIL_TIMESTAMP = 2001;
	/** 短信服务请勿频繁重复发送短信**/
	public static final Integer FAIL_REPEAT = 2002;
	/** 短信服务获取短信模板列表失败**/
	public static final Integer FAIL_TEMPLATE = 2003;
	/** 替换内容与替换参数不匹配**/
	public static final Integer FAIL_SMSTEMPLATE_PRASE = 2004;
	/** 短信发送失败**/
	public static final Integer FAIL_SMS_SEND = 2005;
	/** 短信渠道错误**/
	public static final Integer FAIL_SMS_CHANNEL = 2006;
	/** 黑名单验证不通过 **/
	public static final Integer FAIL_BLACKLIST_VALIDATE = 2007;
	
	/** 时间戳超时验证异常 **/
	public static final Integer EXCEPTION_TIMESTAMP = 3000;
	/** 签名验证异常 **/
	public static final Integer EXCEPTION_SIGN = 3001;
	/** token验证异常 **/
	public static final Integer EXCEPTION_TOKEN = 3002;
	/** 黑名单验证异常 **/
	public static final Integer EXCEPTION_BLACKLIST = 3003;
	/** 网关层过滤器异常 **/
	public static final Integer EXCEPTION_GATEWAY = 3004;
	/** 发送短信异常**/
	public static final Integer EXCEPTION_SMS_SEND = 3005;
	/** json解析报错 **/
	public static final Integer EXCEPTION_PRASE_JSON = 3006;
	/** get请求参数解析报错 **/
	public static final Integer EXCEPTION_PRASE_GET = 3007;
	/** form解析异常 **/
	public static final Integer EXCEPTION_PRASE_FORM = 3008;

	/** 操作失败 */
	public static final Integer OPERAT_FAIL = 4001;
	/** 请求参数错误 */
	public static final Integer REQUEST_PARAMS_ERROR = 4002;
	/** 暂无数据 */
	public static final Integer NONE_DATA = 4003;
	
	
	
	
	public static String getZhMsg(Integer errorCode) {
		return zhMsgMap.get(errorCode);
	}

	static {
		zhMsgMap.put(SUCCESS, "系统正常");
//		zhMsgMap.put(FAIL, "系统异常");
		zhMsgMap.put(NOT_EXISTS_CLIENTID, "clientID请求参数不存在");
		zhMsgMap.put(NOT_EXISTS_TIMESTAMP, "timestamp请求参数不存在或者非数字");
		zhMsgMap.put(NOT_EXISTS_SIGN, "sign请求参数不存在");
		zhMsgMap.put(FAIL_TIMESTAMP, "调用时间戳超出五分钟时间范围");
		zhMsgMap.put(FAIL_SIGN, "签名验证失败");
		zhMsgMap.put(NOT_EXISTS_SECRET, "未发现秘钥");
		zhMsgMap.put(EXCEPTION_PRASE_JSON, "解析Json失败");
		zhMsgMap.put(EXCEPTION_PRASE_GET, "get请求参数解析失败");
		zhMsgMap.put(FAIL_BLACKLIST_VALIDATE, "黑名单验证不通过");
		zhMsgMap.put(EXCEPTION_TIMESTAMP, "时间戳超时验证异常");
		zhMsgMap.put(EXCEPTION_SIGN, "签名验证异常");
		zhMsgMap.put(EXCEPTION_GATEWAY, "网关层过滤器验证异常");
		zhMsgMap.put(EXCEPTION_BLACKLIST, "黑名单验证异常");
		zhMsgMap.put(FAIL_REPEAT, "请勿频繁重复发送短信");
		zhMsgMap.put(FAIL_TEMPLATE, "获取短信模板列表失败");
		zhMsgMap.put(NOT_EXISTS_SMSTEMPLATE, "获取不到模板id对应短信模板记录");
		zhMsgMap.put(FAIL_SMSTEMPLATE_PRASE, "替换内容与替换参数不匹配");
		zhMsgMap.put(FAIL_SMS_SEND, "短信发送失败");
		zhMsgMap.put(FAIL_SMS_CHANNEL, "短信渠道失败");
		zhMsgMap.put(EXCEPTION_SMS_SEND, "发送短信异常");
		zhMsgMap.put(EXCEPTION_PRASE_FORM, "form解析异常");
		zhMsgMap.put(OPERAT_FAIL, "操作失败");
		zhMsgMap.put(REQUEST_PARAMS_ERROR, "请求参数错误");
		zhMsgMap.put(NONE_DATA, "暂无数据");
	}

}
