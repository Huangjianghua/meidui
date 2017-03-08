package com.meiduimall.service;

/**
 * 短信服务接口
 * @author chencong
 *
 */
public interface ShortMessageService {

	/**
	 * 短信服务接口
	 * @param url 短信服务地址
	 * @param json 需要发送的json字符串
	 * @return
	 */
	public String getValidateCode(String url,String json);
}
