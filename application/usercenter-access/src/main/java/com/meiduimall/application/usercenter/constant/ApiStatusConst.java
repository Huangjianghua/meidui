package com.meiduimall.application.usercenter.constant;

import com.meiduimall.core.BaseApiCode;

/**
 * API返回值
 * @author chencong
 *
 */
public class ApiStatusConst extends BaseApiCode {
	
	public final static Integer SYSTEM_ERROR=9001;
	public final static Integer REQUEST_GATEWAY_EX=9002;
	public final static Integer TOKEN_EMPTY=9003;
	public final static Integer TIMESTAMP_EMPTY=9004;
	public final static Integer CLIENTID_EMPTY=9005;
	public final static Integer SIGN_EMPTY=9006;
	public final static Integer REQUEST_TIMEOUT=9007;
	public final static Integer TIMESTAMP_FORMAT_ERROR=9008;
	public final static Integer MEMID_OF_TOKEN_EMPTY=9009;
	public final static Integer SIGN_FORMAT_ERROR=9010;
	public final static Integer SIGN_ERROR=9011;
	public final static Integer CREATE_SIGN_EXCEPTION=9012;
	
	public final static Integer LOGIN_EXPIRE=9013;

	
	static {
		zhMsgMap.put(SYSTEM_ERROR, "系统错误，请联系客服");
		zhMsgMap.put(REQUEST_GATEWAY_EX, "网关HTTP请求程序异常");
		zhMsgMap.put(TOKEN_EMPTY, "token不能为空");
		zhMsgMap.put(TIMESTAMP_EMPTY, "时间戳不能为空");
		zhMsgMap.put(CLIENTID_EMPTY, "clientID不能为空");
		zhMsgMap.put(SIGN_EMPTY, "签名不能为空");
		zhMsgMap.put(REQUEST_TIMEOUT, "请求超时");
		zhMsgMap.put(TIMESTAMP_FORMAT_ERROR, "时间戳格式错误");
		zhMsgMap.put(MEMID_OF_TOKEN_EMPTY, "token对应的memId为空");
		zhMsgMap.put(SIGN_FORMAT_ERROR, "签名格式错误");
		zhMsgMap.put(SIGN_ERROR, "签名错误");
		zhMsgMap.put(CREATE_SIGN_EXCEPTION, "生成签名失败");
		
		zhMsgMap.put(LOGIN_EXPIRE, "登录已过期，请重新登陆");
		
	}
}
