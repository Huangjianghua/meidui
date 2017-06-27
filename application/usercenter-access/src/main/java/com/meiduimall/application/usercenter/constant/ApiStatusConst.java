package com.meiduimall.application.usercenter.constant;

import com.meiduimall.core.BaseApiCode;

/**
 * API返回值
 * @author chencong
 *
 */
public class ApiStatusConst extends BaseApiCode {
	
	public final static Integer REQUEST_GATEWAY_EX=9001;
	public final static Integer VAL_TOKEN_ANNOTATION_EX=9002;
	public final static Integer TOKEN_NOT_EXIST=9003;
	public final static Integer VAL_REDIS_TOKEN_EX=9004;
	public final static Integer RESOLVE_REQUEST_EX=9005;
	public final static Integer TIMESTAMP_EMPTY=9006;
	public final static Integer CLIENTID_EMPTY=9007;
	public final static Integer SIGN_EMPTY=9008;
	public final static Integer REQUEST_TIMEOUT=9009;
	public final static Integer TIMESTAMP_FORMAT_ERROR=9010;
	public final static Integer MEMID_OF_TOKEN_EMPTY=9011;
	public final static Integer SIGN_FORMAT_ERROR=9012;
	public final static Integer SIGN_ERROR=9013;
	public final static Integer GET_SIGN_EX=9014;
	
	public final static Integer EXIT_EXCEPTION=9015;
	public final static Integer UPDATE_PAYPWD_EXCEPTION=9016;
	public final static Integer GET_VALIDATE_CODE_EXCEPTION=9017;
	public final static Integer REGISTER_EXCEPTION=9018;
	public final static Integer BIZID_EMPTY=9019;
	public final static Integer MDUSER_EMPTY=9020;
	public final static Integer RECHARGE_AMOUT_EMPTY=9021;
	public final static Integer RECHARGE_TYPE_EMPTY=9022;
	public final static Integer CALLBACK_URL_EMPTY=9023;
	public final static Integer CALLBACK_URL_NEGATIVE=9024;
	public final static Integer CALLBACK_URL_ZERO=9025;
	public final static Integer CALLBACK_URL_ISNUM=9026;
	public final static Integer BIZID_REPEAT=9027;
	public final static Integer RECHARGE_AMOUT_DECIMAL=9028;
	public final static Integer ACCOUNT_TYPE_NON_EXISTENT=9029;
	
	static {
		zhMsgMap.put(ACCOUNT_TYPE_NON_EXISTENT, "个人帐户类型不存在");
		zhMsgMap.put(RECHARGE_AMOUT_DECIMAL, "充值金额小数位大于2位");
		zhMsgMap.put(BIZID_REPEAT, "充值单号重复");
		zhMsgMap.put(CALLBACK_URL_ISNUM, "充值金额只能为纯数字");
		zhMsgMap.put(CALLBACK_URL_ZERO, "充值金额不能为0");
		zhMsgMap.put(CALLBACK_URL_NEGATIVE, "充值金额不能为负数");
		zhMsgMap.put(REQUEST_GATEWAY_EX, "网关HTTP请求程序异常");
		zhMsgMap.put(VAL_TOKEN_ANNOTATION_EX, "判断API接口是否有token注解程序异常");
		zhMsgMap.put(TOKEN_NOT_EXIST, "token不存在");
		zhMsgMap.put(VAL_REDIS_TOKEN_EX, "校验token程序异常");
		zhMsgMap.put(RESOLVE_REQUEST_EX, "解析request程序异常");
		zhMsgMap.put(TIMESTAMP_EMPTY, "时间戳不能为空");
		zhMsgMap.put(CLIENTID_EMPTY, "clientID不能为空");
		zhMsgMap.put(SIGN_EMPTY, "签名不能为空");
		zhMsgMap.put(REQUEST_TIMEOUT, "请求超时");
		zhMsgMap.put(TIMESTAMP_FORMAT_ERROR, "时间戳格式错误");
		zhMsgMap.put(MEMID_OF_TOKEN_EMPTY, "token对应的memId为空");
		zhMsgMap.put(SIGN_FORMAT_ERROR, "签名格式错误");
		zhMsgMap.put(SIGN_ERROR, "签名错误");
		zhMsgMap.put(GET_SIGN_EX, "生成签名程序异常");
		
		zhMsgMap.put(EXIT_EXCEPTION, "退出登录失败，请联系客服");
		zhMsgMap.put(UPDATE_PAYPWD_EXCEPTION, "修改支付密码失败，请联系客服");
		zhMsgMap.put(REGISTER_EXCEPTION, "注册失败，请联系客服");
		zhMsgMap.put(BIZID_EMPTY, "外部系统充值订单号不能为空");
		zhMsgMap.put(MDUSER_EMPTY, "美兑用户名不能为空");
		zhMsgMap.put(RECHARGE_AMOUT_EMPTY, "充值金额不能为空");
		zhMsgMap.put(RECHARGE_TYPE_EMPTY, "充值类型不能为空");
		zhMsgMap.put(CALLBACK_URL_EMPTY, "回调地址不能为空");
	}
}
