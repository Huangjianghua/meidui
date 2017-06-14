package com.meiduimall.service.account.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信验证码类型
 * @author chencong
 *
 */
public abstract class ConstSmsValidateCodeType {
	
	public static final Map<Integer, String> smsTypeMap = new HashMap<>(300);
	
	public final static Integer RETRIEVE=1;//找回支付密码
	public final static Integer REGISTER=2;//会员注册
	
	public static String getSmsType(Integer type) {
		return smsTypeMap.get(type);
	}
	
	static {
		smsTypeMap.put(RETRIEVE, "findPayPwd");
		smsTypeMap.put(REGISTER, "regist");
	}

}
