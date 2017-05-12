package com.meiduimall.application.usercenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
/**
 * 配置文件当前环境相关参数
 * @author chencong
 *
 */
@Configuration
public class ProfileParamsConfig {
	
	@Autowired
	private Environment env;

	public String getServiceMemberUrl() {
		return env.getProperty("service.member.url");
	}

	public String getServiceAccountUrl() {
		return env.getProperty("service.account.url");
	}

	public String getRouteClientID() {
		return env.getProperty("route.clientID");
	}

	public String getRouteKey() {
		return env.getProperty("route.key");
	}

}
