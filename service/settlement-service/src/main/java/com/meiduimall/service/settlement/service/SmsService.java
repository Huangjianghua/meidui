package com.meiduimall.service.settlement.service;

import com.meiduimall.service.settlement.model.SmsReqDTO;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: SmsService.java
 * Author:   guidl
 * Date:     2017年3月24日 下午14:14:28
 * Description: 短信相关
 */
public interface SmsService {
	
	/**
	 * 发送短信 采用RestTemplate请求http
	 * @param  smsReqDTO 短信相关信息
	 * @return boolean
	 */
	public boolean sendMessage(SmsReqDTO smsReqDTO);
	
}
