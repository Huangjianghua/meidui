package com.meiduimall.service.sms.constant;

import com.meiduimall.core.BaseApiCode;

public class SmsApiCode extends BaseApiCode {
	
	/** 发送短信成功 **/
	public static final Integer SMS_SEND_SUCCESS = 7001;
	/** 发送短信失败 **/
	public static final Integer SMS_SEND_FAILUER = 7002;
	
	/** 请求参数错误 */
	public static final Integer REQUEST_PARAMS_ERROR = 7003;
	/** 请勿频繁重复发送短信 **/
	public static final Integer REPEATING = 7004;

	/** 验证码已过期 **/
	public static final Integer SMS_VALID_CODE_EXPIRED = 7005;
	/** 验证码不匹配 **/
	public static final Integer SMS_VALID_CODE_UNMATCHED = 7006;
	/** 验证码校验成功 **/
	public static final Integer CHECK_CODE_SUCCESS = 7007;
	
	/** 替换内容与替换参数不匹配 **/
	public static final Integer PARAM_ERROR = 7008;
	
	/** 无法获取短信模板列表 **/
	public static final Integer NOT_FOUND_TEMPLATE_LIST = 7009;
	/** 获取不到模板id对应短信模板记录 **/
	public static final Integer NOT_FOUND_TEMPLATE = 7010;

	/** 获取短信渠道列表异常 **/
	public static final Integer EXCEPTION_ACCESS_CHANNEL = 7011;
	/** 获取短信模板列表异常 **/
	public static final Integer EXCEPTION_ACCESS_TEMPLATE = 7012;

	/** 数据库异常 **/
	public static final Integer DB_EXCEPTION = 7777;

	static {
		zhMsgMap.put(SMS_SEND_SUCCESS, "发送短信成功");
		zhMsgMap.put(SMS_SEND_FAILUER, "发送短信失败");
		zhMsgMap.put(REQUEST_PARAMS_ERROR, "请求参数错误");
		zhMsgMap.put(REPEATING, "请勿频繁重复发送短信");
		zhMsgMap.put(SMS_VALID_CODE_EXPIRED, "验证码已过期");
		zhMsgMap.put(SMS_VALID_CODE_UNMATCHED, "验证码不匹配");
		zhMsgMap.put(CHECK_CODE_SUCCESS, "验证码校验成功");
		zhMsgMap.put(PARAM_ERROR, "替换内容与替换参数不匹配");
		zhMsgMap.put(NOT_FOUND_TEMPLATE_LIST, "无法获取短信模板列表");
		zhMsgMap.put(NOT_FOUND_TEMPLATE, "获取不到模板id对应短信模板记录");
		zhMsgMap.put(EXCEPTION_ACCESS_CHANNEL, "获取短信渠道列表异常");
		zhMsgMap.put(EXCEPTION_ACCESS_TEMPLATE, "获取短信模板列表异常");
		zhMsgMap.put(DB_EXCEPTION, "数据库异常");
	}
}
