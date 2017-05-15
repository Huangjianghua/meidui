package com.meiduimall.service.member.model.request;

import javax.validation.constraints.NotNull;

public class RequestBindingWeixin {

	@NotNull
	private String phone;

	@NotNull
	private String openID;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}
}
