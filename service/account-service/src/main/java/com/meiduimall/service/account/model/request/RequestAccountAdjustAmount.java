package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 账户余额调整API请求映射实体
 * @author chencong
 *
 */
public class RequestAccountAdjustAmount extends RequestBaseModel implements Serializable {

	private static final long serialVersionUID = -4128146492405140400L;
	
	@NotEmpty(message="来源不能为空")
	private String source;
	
	@NotEmpty(message="交易类型不能为空")
	@JsonProperty("trade_type")
	private String tradeType;
	
	@NotEmpty(message="订单号不能为空")
	@JsonProperty("order_id")
	private String orderId;
	
	@NotEmpty(message="调账方向不能为空")
	private String direction;
	
	@NotNull(message="交易金额不能为空")
	@JsonProperty("trade_amount")
	private Double trade_amount;
	
	@NotEmpty(message="交易时间不能为空")
	@JsonProperty("trade_time")
	private String tradeTime;
	
	private String remark;

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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Double getTrade_amount() {
		return trade_amount;
	}

	public void setTrade_amount(Double trade_amount) {
		this.trade_amount = trade_amount;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "RequestAccountAdjustAmount [source=" + source + ", tradeType=" + tradeType + ", orderId=" + orderId
				+ ", direction=" + direction + ", trade_amount=" + trade_amount + ", tradeTime=" + tradeTime
				+ ", remark=" + remark +" memId="+getMemId() +"]";
	}


}
