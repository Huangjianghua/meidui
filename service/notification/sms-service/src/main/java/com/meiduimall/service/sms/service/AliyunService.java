package com.meiduimall.service.sms.service;

import com.meiduimall.exception.ApiException;

public interface AliyunService {
	public boolean Send(String mobile, String tid, String context) throws ApiException;

}
