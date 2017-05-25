<<<<<<< HEAD
package com.meiduimall.service.sms.request;

import javax.validation.constraints.NotNull;

public class WXMsgOnPaySuccessRequest {

	@NotNull
	private String phone;
	@NotNull
	private Long orderTime;
	@NotNull
	private String storeName;
	@NotNull
	private String addPoint;
	@NotNull
	private String sysKey;
	
	private String coupon;
	
	private String openID;
	private String userName;
	private String totalPoint;
	private String memId;
	
	public String getSysKey() {
		return sysKey;
	}
	public void setSysKey(String sysKey) {
		this.sysKey = sysKey;
	}
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
=======
package com.meiduimall.service.sms.request;

public class WXMsgOnPaySuccessRequest {

	private String phone;
	private String openID;
	private String storeName;
	private String addPoint;
	private String coupon;
	private String userName;
	private String orderTime;
	private String totalPoint;
	
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
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}
}
>>>>>>> refs/remotes/origin/feature/V4.0.2-Team2
