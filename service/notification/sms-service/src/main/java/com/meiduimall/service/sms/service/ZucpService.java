package com.meiduimall.service.sms.service;

import com.meiduimall.support.core.exception.ApiException;

public interface ZucpService {

	public String Send(String mobile, String content, String ext, String stime, String rrid) throws ApiException;
	
	public String Send(String mobile, String content) throws ApiException;
}
