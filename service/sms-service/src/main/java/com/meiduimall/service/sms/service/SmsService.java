/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.service;

import com.meiduimall.service.sms.model.message.CommonShortMessageModel;

public interface SmsService {
//	/**
//	 * 校验发送短信的参数
//	 * @param  model
//	 * @return
//	 */
//	ResultBody validSmsMessageParams(CommonShortMessageModel model);
//
//	/**
//	 * 校验发送短信验证码的参数
//	 * @param  model
//	 * @return
//	 */
//	ResultBody validVerificationCodeParams(CommonShortMessageModel model);
//
//	/**
//	 * 校验验证短信验证码的参数
//	 * @param  model
//	 * @return
//	 */
//	ResultBody validCheckVerificationCodeParams(CommonShortMessageModel model);
	
	/**
	 * 发送短信
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	void sendSmsMessage(CommonShortMessageModel model) ;
	
	/**
	 * 发送短信验证码
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	String sendSmsVerificationCode(CommonShortMessageModel model) ;
	
	/**
	 * 校验短信验证码
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	int checkSmsVerificationCode(CommonShortMessageModel model) ;
}
