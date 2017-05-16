package com.meiduimall.service.sms.request;

import javax.validation.constraints.NotNull;

public class WXMsgOnPaySuccessRequest {

	@NotNull
	private Long orderTime;
	@NotNull
	private String phone;
	private String openID;
	private String storeName;
	private String addPoint;
	private String coupon;
	private String userName;
	private String totalPoint;
	private String memId;
	
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
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getAddPoint() {
		return addPoint;
	}
	public void setAddPoint(String addPoint) {
		this.addPoint = addPoint;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Long orderTime) {
		this.orderTime = orderTime;
	}
	public String getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
}
