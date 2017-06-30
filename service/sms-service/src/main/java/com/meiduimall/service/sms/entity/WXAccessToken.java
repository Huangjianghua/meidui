package com.meiduimall.service.sms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WXAccessToken {

	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("expires_in")
	private int expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
