package com.meiduimall.service.member.model.request;

import java.io.Serializable;

public class RequestMobile implements Serializable{

 
	private static final long serialVersionUID = -5856003931805288902L;

    private String Mobile;
	
	private String cityName;
	
	
	
	
	public RequestMobile() {
		super();
	}

	public RequestMobile(String mobile) {
		super();
		Mobile = mobile;
	}

	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	 
	
	
}
