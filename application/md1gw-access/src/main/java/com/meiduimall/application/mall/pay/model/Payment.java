package com.meiduimall.application.mall.pay.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Payment implements Serializable{

	
	private static final long serialVersionUID = -2458266297035033280L;

	private String memId;//用户ID 				
	
	private String clientId;//客户度ID			
						
	private String orderId;//订单号					
						
	private BigDecimal account;//支付金额					
						
	private OrderInfo orderInfo = new OrderInfo();//订单信息					
						
	private Integer paytType;//支付类型 0微信1是支付宝					
						
	private Long timestamp;//时间戳					
						
	private BigDecimal payAmount;//支付金额					
						
	private BigDecimal orderAmount;//订单金额					
						
	private BigDecimal cashAmount;//余额金额					
						
	private BigDecimal integral;//积分					
						
	private String merchantId;//商户标识可以多个;分隔					
						
	private Integer notifyType;//回调地址类型，1：完整的HTTP地址（http://ip:port/xxxxxx/)					
						
	private String orderTime;//订单时间，格式：YYYYMMDDhhmmss					
						
	private String notiftUrl;//回调地址

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	 

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Integer getPaytType() {
		return paytType;
	}

	public void setPaytType(Integer paytType) {
		this.paytType = paytType;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(Integer notifyType) {
		this.notifyType = notifyType;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getNotiftUrl() {
		return notiftUrl;
	}

	public void setNotiftUrl(String notiftUrl) {
		this.notiftUrl = notiftUrl;
	}
	
	 				
		
	
	
	
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					
	 					

						

}
