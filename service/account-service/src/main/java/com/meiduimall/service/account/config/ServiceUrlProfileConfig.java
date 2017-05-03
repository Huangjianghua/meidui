package com.meiduimall.service.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
/**
 * 获取当前环境其他service的地址
 * @author chencong
 *
 */
@Configuration
public class ServiceUrlProfileConfig {
	
	
	@Value("${service.member}")
	private String memberServiceUrl;
	
	@Value("${service.sms}")
	private String smsServiceUrl;

	/**
	 * 获取账号服务地址
	 * @return url
	 */
	public String getMemberServiceUrl() {
		return memberServiceUrl;
	}

	/**
	 * 获取短信服务地址
	 * @return url
	 */
	public String getSmsServiceUrl() {
		return smsServiceUrl;
	}
	
}
