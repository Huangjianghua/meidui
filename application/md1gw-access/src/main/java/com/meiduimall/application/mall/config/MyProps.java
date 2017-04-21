package com.meiduimall.application.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "myProps") // 接收application.yml中的myProps下面的属性
public class MyProps {

	private String routeServiceUrl;
	private String userCenterUrl;
	private String sendSmsUrl;
	private String payUrl;
	private String meiduimallUrl;
	private String finishUrl;
	

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

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getMeiduimallUrl() {
		return meiduimallUrl;
	}

	public void setMeiduimallUrl(String meiduimallUrl) {
		this.meiduimallUrl = meiduimallUrl;
	}

	public String getFinishUrl() {
		return finishUrl;
	}

	public void setFinishUrl(String finishUrl) {
		this.finishUrl = finishUrl;
	}


	 

	


}
