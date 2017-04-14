package com.meiduimall.application.md1gwaccess.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "myProps") // 接收application.yml中的myProps下面的属性
public class MyProps {

	private String routeServiceUrl;
	private String userCenterUrl;
	private String sendSmsUrl;
	private String paydUrl;
	private String payUrl;
	
	
	
	

	public String getRouteServiceUrl() {
		return routeServiceUrl;
	}

	public void setRouteServiceUrl(String routeServiceUrl) {
		this.routeServiceUrl = routeServiceUrl;
	}

	public String getUserCenterUrl() {
		return userCenterUrl;
	}

	public void setUserCenterUrl(String userCenterUrl) {
		this.userCenterUrl = userCenterUrl;
	}

	public String getSendSmsUrl() {
		return sendSmsUrl;
	}

	public void setSendSmsUrl(String sendSmsUrl) {
		this.sendSmsUrl = sendSmsUrl;
	}

	public String getPaydUrl() {
		return paydUrl;
	}

	public void setPaydUrl(String paydUrl) {
		this.paydUrl = paydUrl;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}


}
