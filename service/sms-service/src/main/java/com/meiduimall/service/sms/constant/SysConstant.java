package com.meiduimall.service.sms.constant;

import com.meiduimall.core.Constants;

/**
 * system constant
 *
 * @author yangchangfu
 *
 */
public class SysConstant extends Constants {
	
	private SysConstant() {
	}

	/** 短信渠道key */
	public static final String MESSAGE_CHANNEL_KEY = "message_channel_key";

	/** 支付宝渠道key */
	public static final String MESSAGE_CHANNEL_ALI_KEY = "message_channel_ali_key";

	/** 漫道短信渠道key */
	public static final String MESSAGE_CHANNEL_MANDAO_KEY = "message_channel_mandao_key";

	/** 短信模板key */
	public static final String MESSAGE_TEMPLATE_KEY = "message_template_key";

	/** 短信验证码缓存其中的一个标识 */
	public static final String MESSAGE_CODE_KEY = "code_key";
	
	public static final String CODE_SPLIT_KEY = "##";
	
	/** 微信access_token_key */
	public static final String WEIXIN_ACCESS_TOKEN_KEY = "weixin_access_token_key";
	
	public static final String SYSTEM_EXCEPTION_MSG="，系统异常，请联系客服。";
	

}