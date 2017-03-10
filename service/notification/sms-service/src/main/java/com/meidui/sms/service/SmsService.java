package com.meidui.sms.service;


import com.meidui.sms.model.message.CommonShortMessageModel;
import com.meiduimall.ResultBody;

public interface SmsService {
	/**
	 * 校验发送短信的参数
	 * @param CommonShortMessageModel model
	 * @return
	 */
	public ResultBody validSmsMessageParams(CommonShortMessageModel model);
	
	/**
	 * 校验发送短信验证码的参数
	 * @param CommonShortMessageModel model
	 * @return
	 */
	public ResultBody validVerificationCodeParams(CommonShortMessageModel model);
	
	/**
	 * 校验验证短信验证码的参数
	 * @param CommonShortMessageModel model
	 * @return
	 */
	public ResultBody validCheckVerificationCodeParams(CommonShortMessageModel model);
	
	/**
	 * 发送短信
	 * 
	 * @param CommonShortMessageModel
	 * @return
	 * @throws Exception
	 */
	public ResultBody sendSmsMessage(CommonShortMessageModel model) throws Exception;
	
	/**
	 * 发送短信验证码
	 * 
	 * @param CommonShortMessageModel
	 * @return
	 * @throws Exception
	 */
	public ResultBody sendSmsVerificationCode(CommonShortMessageModel model) throws Exception;
	
	/**
	 * 校验短信验证码
	 * 
	 * @param CommonShortMessageModel
	 * @return
	 * @throws Exception
	 */
	public ResultBody checkSmsVerificationCode(CommonShortMessageModel model) throws Exception;
}
