package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 取消订单API请求映射实体
 * @author chencong
 *
 */
public class RequestCancelOrder extends RequestBaseModel implements Serializable {
	
	private static final long serialVersionUID = 5926067839910159028L;

	/**交易订单号*/
	@JsonProperty("order_id")
	@NotEmpty(message="订单号不能为空")
	private String orderId;
	
	/**订单状态 1未支付 2已支付*/
	@JsonProperty("order_status")
	@NotEmpty(message="订单状态不能为空")
	private Integer orderStatus;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "RequestCancelOrder [orderId=" + orderId + ", orderStatus=" + orderStatus + ", memId=" + getMemId()+"]";
	}
}
