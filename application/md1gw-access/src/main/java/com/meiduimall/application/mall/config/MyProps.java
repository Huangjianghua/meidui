package com.meiduimall.application.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "myProps") // 接收application.yml中的myProps下面的属性
public class MyProps {

	private String routeServiceUrl; // 网关地址
	private String userCenterUrl; // 会员中心接入层
	private String sendSmsUrl;
	private String payUrl;
	private String meiduimallUrl;
	private String finishUrl;

	private String signClientID;// 网关签名clientID
	private String signKey;// 网关签名key
	
	private String otoUrl;

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

	public String getSignClientID() {
		return signClientID;
	}

	public void setSignClientID(String signClientID) {
		this.signClientID = signClientID;
	}

	public String getSignKey() {
		return signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	public String getOtoUrl() {
		return otoUrl;
	}

	public void setOtoUrl(String otoUrl) {
		this.otoUrl = otoUrl;
	}

}
