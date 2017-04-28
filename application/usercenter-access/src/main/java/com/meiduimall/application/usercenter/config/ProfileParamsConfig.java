package com.meiduimall.application.usercenter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
/**
 * 配置文件当前环境相关参数
 * @author chencong
 *
 */
@Configuration
public class ProfileParamsConfig {

	@Value("${service.account.url}")
	private String serviceAccountUrl;

	@Value("${service.member.url}")
	private String serviceMemberUrl;
	
	@Value("${service.sms.url}")
	private String serviceSmsUrl;

	@Value("${route.clientID}")
	private String routeClientID;
	
	@Value("${route.key}")
	private String routeKey;
	
	public String getServiceAccountUrl() {
		return serviceAccountUrl;
	}

	public String getServiceMemberUrl() {
		return serviceMemberUrl;
	}
	
	public String getServiceSmsUrl() {
		return serviceSmsUrl;
	}

	public String getRouteClientID() {
		return routeClientID;
	}

	public String getRouteKey() {
		return routeKey;
	}


}
