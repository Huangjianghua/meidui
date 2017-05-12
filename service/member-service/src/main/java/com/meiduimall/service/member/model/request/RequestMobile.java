package com.meiduimall.service.member.model.request;

import java.io.Serializable;
import java.util.List;


public class RequestMobile implements Serializable{

	private static final long serialVersionUID = -5856003931805288902L;

	
	private List<String> mobileList;
	
	private String cityName;


	


	public RequestMobile() {
		super();
	}





	public RequestMobile(List<String> mobileList, String cityName) {
		super();
		this.mobileList = mobileList;
		this.cityName = cityName;
	}





	public List<String> getMobileList() {
		return mobileList;
	}





	public void setMobileList(List<String> mobileList) {
		this.mobileList = mobileList;
	}





	public String getCityName() {
		return cityName;
	}





	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	 
	
	
	
	
}
