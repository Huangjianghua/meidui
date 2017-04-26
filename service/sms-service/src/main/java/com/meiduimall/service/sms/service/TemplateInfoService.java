package com.meiduimall.service.sms.service;

public interface TemplateInfoService {

	/**
	 * 获取短信模板并转成json字符串
	 * 
	 * @param key
	 * @return
	 */
	String getTemplateList(String key);
}
