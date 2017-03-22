package com.meiduimall.service.settlement.service;

import com.meiduimall.service.settlement.model.SmsReqDTO;

public interface SmsService {
	
	/**
	 * 发送短信 采用RestTemplate请求http
	 * @param smsReqDTO
	 * @return
	 * @throws Exception
	 */
	public boolean sendMsm(SmsReqDTO smsReqDTO) throws Exception;
	
	/**
	 * 发送短信 采用httpClient请求http
	 * @return
	 * @throws Exception
	 */
	public boolean sendMessage(SmsReqDTO smsReqDTO) throws Exception;

}
