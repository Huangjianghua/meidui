package com.meiduimall.service.member.service;

import com.meiduimall.service.member.model.request.RequestGetValidateCode;

/**
 * 短信相关接口
 * @author chencong
 *
 */
public interface SmsService {
	
	/**
	 * 获取短信验证码
	 * @param model 获取短信验证码请求映射实体
	 * @return true:请求短信服务返回成功  false:请求短信服务返回失败
	 */
	boolean getValidateCode(RequestGetValidateCode model);
	
}
