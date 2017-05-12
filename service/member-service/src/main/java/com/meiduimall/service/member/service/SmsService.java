package com.meiduimall.service.member.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.meiduimall.service.member.model.request.RequestCheckValidateCode;
import com.meiduimall.service.member.model.request.RequestGetValidateCode;
import com.meiduimall.service.member.model.request.RequestSendSms;

/**
 * 短信相关接口
 * @author chencong
 *
 */
public interface SmsService {
	
	/**
	 * 获取短信验证码
	 * @param model 获取短信验证码请求映射实体
	 * @return true:请求短信服务成功  false:请求短信服务失败
	 */
	boolean getValidateCode(RequestGetValidateCode model);
	
	/**
	 * 校验短信验证码
	 * @param model 校验短信验证码请求映射实体
	 * @return true:校验成功  false:校验失败
	 */
	boolean checkValidateCode(RequestCheckValidateCode model);
	
	/**
	 * 发送普通短信
	 * @param model 发送短信请求映射实体
	 */
	void sendSms(RequestSendSms model);
	
}
