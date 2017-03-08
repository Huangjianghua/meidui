package com.meiduimall.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信相关常量设置
 * @author chencong
 *
 */
public class ShortMessageConst {

	/** 短信验证码操作类型 */
	public static Map<String, String> OpType = new HashMap<String, String>();
	static {
		OpType.put("1", "SIGNIN");//表示手机注册校验码下发
		OpType.put("2", "");//表示手机找回登录密码校验码下发
		OpType.put("3", "");//手机绑定校验码下发,需要带token
		OpType.put("4", "");//表示转账校验码下发,需要带token
		OpType.put("5", "");//找回支付密码
		OpType.put("7", "");//购物券余额兑换校验码下发
	}
}
