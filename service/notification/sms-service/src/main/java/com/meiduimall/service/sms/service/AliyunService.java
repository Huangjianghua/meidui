package com.meiduimall.service.sms.service;

import com.meiduimall.exception.ApiException;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: AliyunService.java
 * Author:   Administrator
 * Date:     2017年3月15日 下午12:17:27
 * Description:阿里云短信平台 
 */
public interface AliyunService {
	/**
	 * 功能描述:  调用阿里云短信接口
	 * Author: 陈建宇
	 * Date:   2017年3月15日 下午12:17:56   
	 * return  boolean
	 */
	public boolean Send(String mobile, String tid, String context) throws ApiException;

}
