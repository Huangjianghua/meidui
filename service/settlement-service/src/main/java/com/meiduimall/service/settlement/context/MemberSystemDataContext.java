package com.meiduimall.service.settlement.context;

import java.math.BigDecimal;

public class MemberSystemDataContext {
	
	public static final String KEY_AUTHORIZED_OAUTH_SIGNATURE_METHOD="authorized.oauth_signature_method";
	public static final String KEY_AUTHORIZED_OAUTH_VERSION="authorized.oauth_version";
	public static final String KEY_AUTHORIZED_OAUTH_ACCESS_SECRET="authorized.oauth_accessor_secret";
	public static final String KEY_AUTHORIZED_OAUTH_CONSUMER_KEY="authorized.oauth_consumer_key";
	public static final String KEY_AUTHORIZED_DOMAIN="authorized.url";
	public static final String KEY_AUTHORIZED_API_GET_REFERRER_INFO="authorized.belong";
	public static final String KEY_AUTHORIZED_API_UPD_FIRST_REFERRER_CASH="authorized.api_upd_first_referrer_cash";
	
	public static final String REMARK_FOR_FJJL="附近消费成功，赠送现金奖励";
	
	private String userId;
	private String source;
	private String tradeType;
	private String orderSn;
	private String direction;
	private BigDecimal amount;
	private Integer tradeTime;
	private String remark;
	

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Integer tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


}
