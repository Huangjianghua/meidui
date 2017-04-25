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

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.sms.request.CheckCodeRequest;
import com.meiduimall.service.sms.request.SendCodeRequest;
import com.meiduimall.service.sms.request.SendMessageRequest;

public interface SmsService {

	/**
	 * 发送短信
	 * 
	 * @param model
	 * @return
	 */
	ResBodyData sendSmsMessage(SendMessageRequest model);

	/**
	 * 发送短信验证码
	 * 
	 * @param model
	 * @return
	 */
	ResBodyData sendSmsVerificationCode(SendCodeRequest model);

	/**
	 * 校验短信验证码
	 * 
	 * @param model
	 * @return
	 */
	ResBodyData checkSmsVerificationCode(CheckCodeRequest model);
}
