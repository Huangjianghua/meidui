package com.meiduimall.service.account.model.request;

import java.io.Serializable;

public class MemberConsumeMessageReq implements Serializable{

 
	private static final long serialVersionUID = -4100412753193648648L;
	 
	
	/** mem_ID **/
	private String memId;
	
	/** 订单ID **/
	private String orderId;
	
	/** 消费金额 **/
	private String consumerMoney;
	
	/** 商品名称 **/
	private String productName;
	
	/** 消费来源 **/
	private String orderSource;
	
	/** O2O会员ID **/
	private String orginalUserId;
	
	/** 商家赠送积分 **/
	private String backIntegral;
	
	/** 支付类型 1：表示单独使用消费劵支付2：混合支付3: 其他第三方支付*/
	private String payType;
	
	/**订单状态1表示已经完，2表示已退单*/
	private String mchStatus;
	
	/**消费劵支付金额*/
	private String consumeCouponCount;
	
	/**
	 * 冻结解冻 1.冻结 2，解冻
	 */
	private String freeType;
	
	/** 购物券支付金额**/
	private String shoppingCouponCount;
	
	/** 美兑积分 **/
	private String consumePointsCount;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getConsumerMoney() {
		return consumerMoney;
	}

	public void setConsumerMoney(String consumerMoney) {
		this.consumerMoney = consumerMoney;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getOrginalUserId() {
		return orginalUserId;
	}

	public void setOrginalUserId(String orginalUserId) {
		this.orginalUserId = orginalUserId;
	}

	public String getBackIntegral() {
		return backIntegral;
	}

	public void setBackIntegral(String backIntegral) {
		this.backIntegral = backIntegral;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getMchStatus() {
		return mchStatus;
	}

	public void setMchStatus(String mchStatus) {
		this.mchStatus = mchStatus;
	}

	public String getConsumeCouponCount() {
		return consumeCouponCount;
	}

	public void setConsumeCouponCount(String consumeCouponCount) {
		this.consumeCouponCount = consumeCouponCount;
	}

	public String getFreeType() {
		return freeType;
	}

	public void setFreeType(String freeType) {
		this.freeType = freeType;
	}

	public String getShoppingCouponCount() {
		return shoppingCouponCount;
	}

	public void setShoppingCouponCount(String shoppingCouponCount) {
		this.shoppingCouponCount = shoppingCouponCount;
	}

	public String getConsumePointsCount() {
		return consumePointsCount;
	}

	public void setConsumePointsCount(String consumePointsCount) {
		this.consumePointsCount = consumePointsCount;
	}
	
	
	
}
