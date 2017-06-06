package com.meiduimall.service.sms.constant;

import com.meiduimall.core.BaseApiCode;

public class SmsApiCode extends BaseApiCode {

	/******************* 业务异常 start *******************/

	/** 短信发送成功 **/
	public static final Integer SMS_SEND_SUCCESS = 7001;
	/** 短信发送失败 **/
	public static final Integer SMS_SEND_FAILUER = 7002;

	/** 验证码发送成功 **/
	public static final Integer SEND_CODE_SUCCESS = 7003;
	/** 验证码发送失败 **/
	public static final Integer SEND_CODE_FAILUER = 7004;

	/** 验证码已过期 **/
	public static final Integer SMS_VALID_CODE_EXPIRED = 7005;
	/** 验证码不匹配 **/
	public static final Integer SMS_VALID_CODE_UNMATCHED = 7006;
	/** 验证码校验成功 **/
	public static final Integer CHECK_CODE_SUCCESS = 7007;

	/** 请勿重复发送短信 **/
	public static final Integer REPEATING = 7008;

	/******************* 业务异常 end *******************/

	/******************* 系统异常 start *******************/

	/** 无法获取短信模板列表 **/
	public static final Integer NOT_FOUND_TEMPLATE_LIST = 7101;
	/** 获取不到模板id对应短信模板记录 **/
	public static final Integer NOT_FOUND_TEMPLATE = 7102;

	/** 获取短信渠道列表异常 **/
	public static final Integer EXCEPTION_ACCESS_CHANNEL = 7103;
	/** 获取短信模板列表异常 **/
	public static final Integer EXCEPTION_ACCESS_TEMPLATE = 7104;

	/** 请求参数错误 */
	public static final Integer REQUEST_PARAMS_ERROR = 7105;

	/** 替换内容与替换参数不匹配 **/
	public static final Integer PARAM_ERROR = 7106;

	/** 请求微信AccessToken异常 **/
	public static final Integer REQUEST_ACCESS_TOKEN_EXCEPTION = 7015;

	/** 请求不到用户数据 **/
	public static final Integer CAN_NOT_REQUEST_MEMBER_INFO = 7016;

	/** 用户数据异常 **/
	public static final Integer MEMBER_INFO_EXCEPTION = 7017;

	/** 发送微信模板消息失败 **/
	public static final Integer SEND_WEIXIN_TEMPLATE_MSG_FAIL = 7018;

	/** 发送微信模板消息成功 **/
	public static final Integer SEND_WEIXIN_TEMPLATE_MSG_SUCCESS = 7019;

	/** 数据库异常 **/
	public static final Integer DB_EXCEPTION = 7107;

	/** 未知错误 **/
	public static final Integer UNKNOW_ERROR = 7777;

	/******************* 系统异常 end *******************/
	static {
		// 业务异常
		zhMsgMap.put(SMS_SEND_SUCCESS, "短信发送成功");
		zhMsgMap.put(SMS_SEND_FAILUER, "短信发送失败");
		zhMsgMap.put(SEND_CODE_SUCCESS, "验证码发送成功");
		zhMsgMap.put(SEND_CODE_FAILUER, "验证码发送失败");
		zhMsgMap.put(SMS_VALID_CODE_EXPIRED, "验证码已过期");
		zhMsgMap.put(SMS_VALID_CODE_UNMATCHED, "验证码不匹配");
		zhMsgMap.put(CHECK_CODE_SUCCESS, "验证码校验成功");

		zhMsgMap.put(PARAM_ERROR, "替换内容与替换参数不匹配");
		zhMsgMap.put(NOT_FOUND_TEMPLATE_LIST, "无法获取短信模板列表");
		zhMsgMap.put(NOT_FOUND_TEMPLATE, "获取不到模板id对应短信模板记录");
		zhMsgMap.put(EXCEPTION_ACCESS_CHANNEL, "获取短信渠道列表异常");
		zhMsgMap.put(EXCEPTION_ACCESS_TEMPLATE, "获取短信模板列表异常");
		zhMsgMap.put(REQUEST_PARAMS_ERROR, "请求参数错误");
		zhMsgMap.put(REPEATING, "请勿频繁重复发送短信");
		zhMsgMap.put(REQUEST_ACCESS_TOKEN_EXCEPTION, "请求微信AccessToken异常");
		zhMsgMap.put(CAN_NOT_REQUEST_MEMBER_INFO, "请求不到用户数据");
		zhMsgMap.put(MEMBER_INFO_EXCEPTION, "用户数据异常");
		zhMsgMap.put(SEND_WEIXIN_TEMPLATE_MSG_FAIL, "发送微信模板消息失败");
		zhMsgMap.put(SEND_WEIXIN_TEMPLATE_MSG_SUCCESS, "发送微信模板消息成功");
		zhMsgMap.put(DB_EXCEPTION, "数据库异常");
		zhMsgMap.put(UNKNOW_ERROR, "未知错误");

		zhMsgMap.put(REPEATING, "请勿重复发送短信");

		// 系统异常
		zhMsgMap.put(NOT_FOUND_TEMPLATE_LIST, "系统异常：" + NOT_FOUND_TEMPLATE_LIST + "，请联系客服。");
		zhMsgMap.put(NOT_FOUND_TEMPLATE, "系统异常：" + NOT_FOUND_TEMPLATE + "，请联系客服。");
		zhMsgMap.put(EXCEPTION_ACCESS_CHANNEL, "系统异常：" + EXCEPTION_ACCESS_CHANNEL + "，请联系客服。");
		zhMsgMap.put(EXCEPTION_ACCESS_TEMPLATE, "系统异常：" + EXCEPTION_ACCESS_TEMPLATE + "，请联系客服。");
		zhMsgMap.put(REQUEST_PARAMS_ERROR, "系统异常：" + REQUEST_PARAMS_ERROR + "，请联系客服。");
		zhMsgMap.put(PARAM_ERROR, "系统异常：" + PARAM_ERROR + "，请联系客服。");
		zhMsgMap.put(DB_EXCEPTION, "系统异常：" + DB_EXCEPTION + "，请联系客服。");
		zhMsgMap.put(UNKNOW_ERROR, "系统异常：" + UNKNOW_ERROR + "，请联系客服。");

	}

	private SmsApiCode() {
	}
}
