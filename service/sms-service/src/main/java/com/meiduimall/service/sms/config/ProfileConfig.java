package com.meiduimall.service.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 获取当前环境其他service的地址
 * 
 * @author yangchangfu
 *
 */
@Configuration
public class ProfileConfig {

	@Autowired
	private Environment env;

	public String getWeixinAppId() {
		return env.getProperty("weixin.appId");
	}

	public String getWeixinAppSecret() {
		return env.getProperty("weixin.appSecret");
	}

	public String getWeixinUrl() {
		return env.getProperty("weixin.url");
	}

	public String getWeixinTemplateId1() {
		return env.getProperty("weixin.templateId1");
	}
	
	public String getAppDownloadUrl(){
		return env.getProperty("app-download-url");
	}
}
