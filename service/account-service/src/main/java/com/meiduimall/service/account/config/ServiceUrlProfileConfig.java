package com.meiduimall.service.account.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
/**
 * 获取当前环境其他service的地址
 * @author chencong
 *
 */
@Configuration
public class ServiceUrlProfileConfig {
	
	
	@Autowired
	private Environment env;

	public String getSmsServiceUrl() {
		return env.getProperty("service.sms");
	}

	public String getMemberServiceUrl() {
		return env.getProperty("service.member");
	}

}
