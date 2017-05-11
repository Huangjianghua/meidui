package com.meiduimall.service.account.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信操作类型
 * @author chencong
 *
 */
public abstract class SmsTemplateIDConst {
	
	public static final Map<Integer, String> smsTemplateID = new HashMap<>(300);
	
	public final static Integer REGIST_SUCCESS=1;//注册成功
	public final static Integer SEND_VALIDATE_CODE=2;//发送验证码
	public final static Integer GIVE_POINT=3;//赠送积分
	public final static Integer REGIST_SCAN_CODE_SUCCESS=4;//扫码注册成功
	
	public static String getSmsTemplate(Integer type) {
		return smsTemplateID.get(type);
	}
	
	static {
		smsTemplateID.put(REGIST_SUCCESS, "MEM_1001");
		smsTemplateID.put(SEND_VALIDATE_CODE, "MEM_1002");
		smsTemplateID.put(GIVE_POINT, "MEM_1004");
		smsTemplateID.put(REGIST_SCAN_CODE_SUCCESS, "MEM_1005");
	}

}
