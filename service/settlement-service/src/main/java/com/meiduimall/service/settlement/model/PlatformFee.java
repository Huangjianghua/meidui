package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PlatformFee implements Serializable {

	private static final long serialVersionUID = 7866207354040755894L;
	
	//订单编号
	private String orderSn;
	//商家编号
	private String sellerName;
	//商家账号
	private String sellerPhone;
	//每笔订单平台所获服务费
	private BigDecimal money;
	//平台获取服务费比例
	private String serviceFee;
	
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerPhone() {
		return sellerPhone;
	}
	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	
}
