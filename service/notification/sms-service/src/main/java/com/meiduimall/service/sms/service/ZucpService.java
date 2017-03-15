package com.meiduimall.service.sms.service;

import com.meiduimall.core.exception.ApiException;
/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: ZucpService.java
 * Author:   Administrator
 * Date:     2017年3月15日 下午12:19:39
 * Description: 漫道短信服務
 */
public interface ZucpService {
	
	/**
	 * 功能描述:  漫道短信服務发送接口重载
	 * Author: 陈建宇
	 * Date:   2017年3月15日 下午12:19:49   
	 * return  String
	 */
	public String Send(String mobile, String content, String ext, String stime, String rrid) throws ApiException;
	
	/**
	 * 功能描述:  漫道短信服務发送接口
	 * Author: 陈建宇
	 * Date:   2017年3月15日 下午12:20:04   
	 * return  String
	 */
	public String Send(String mobile, String content) throws ApiException;
}
