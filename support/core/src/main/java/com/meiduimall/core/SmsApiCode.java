package com.meiduimall.core;

import java.util.Map;

import com.meiduimall.core.BaseApiCode;

public class SmsApiCode {
	public static final Integer NOT_FOUND=-1;
	public static final Integer UNKNOWN=-9;
	public static final Integer REPEATING=-2;
	public static final Integer NOT_FOUND_TEMPLATES=-3;
	public static final Integer PARAM_ERROR=-4;
	public static final Integer SMS_VALID_CODE_EXPIRED=-7;
	public static final Integer SMS_VALID_CODE_UNMATCHED=-8;
	public static final Integer NOT_FOUND_TEMPLATE=-5;
	public static final Integer SMS_SEND_FAILUER=-6;
	
	public static void initResponseCode(){
		Map<Integer,String> zhMsgMap = BaseApiCode.zhMsgMap;
		zhMsgMap.put(NOT_FOUND, "service not found");
		zhMsgMap.put(UNKNOWN, "service not found");
		zhMsgMap.put(REPEATING, "请勿频繁重复发送短信");
		zhMsgMap.put(NOT_FOUND_TEMPLATES, "获取短信模板列表失败");
		zhMsgMap.put(PARAM_ERROR, "替换内容与替换参数不匹配");
		zhMsgMap.put(SMS_VALID_CODE_EXPIRED, "验证码已过期");
		zhMsgMap.put(SMS_VALID_CODE_UNMATCHED, "验证码不匹配");
		zhMsgMap.put(NOT_FOUND_TEMPLATE, "获取不到模板id对应短信模板记录");
		zhMsgMap.put(SMS_SEND_FAILUER, "发送短信失败！");
	}
}
