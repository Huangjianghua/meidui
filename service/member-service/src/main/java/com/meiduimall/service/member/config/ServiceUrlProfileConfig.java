package com.meiduimall.service.member.config;

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

	public String getAccountServiceUrl() {
		return env.getProperty("service.account");
	}
	
}
