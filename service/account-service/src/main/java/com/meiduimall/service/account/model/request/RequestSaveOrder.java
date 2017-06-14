package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 保存订单API请求映射实体
 * @author chencong
 *
 */
public class RequestSaveOrder extends RequestBaseModel implements Serializable {

	private static final long serialVersionUID = -6815942537434761600L;

	/**交易订单号*/
	@JsonProperty("order_id")
	@NotEmpty(message="订单号不能为空")
	@Length(max=40,message="订单号长度不合法")
	private String orderId;
	
	/**消费总金额*/
	@JsonProperty("consume_amount")
	@NotNull(message="消费总金额不能为空")
	@Min(0)
	private Double consumeAmount;
	
	/**交易产品名称*/
	@JsonProperty("product_name")
	@NotEmpty(message="交易产品名称不能为空")
	@Length(max=40,message="交易产品名称长度不合法")
	private String productName;

	/**订单来源*/
	@JsonProperty("order_source")
	@NotEmpty(message="订单来源不能为空")
	private String orderSource;
	
	/**订单状态 1未支付 2已支付*/
	@JsonProperty("order_status")
	@NotNull(message="订单状态不能为空")
	@Min(1)
	@Max(2)
	private Integer orderStatus;
	
	/**余额支付金额*/
	@JsonProperty("consume_money")
	@NotNull(message="余额支付金额不能为空")
	@Min(0)
	private Double consumeMoney;
	
	/**支付方式（积分、其他支付方式（比如支付宝，网银支付等等）） 1：表示单独使用积分支付 2：混合支付 3:其他第三方支付*/
	@JsonProperty("pay_type")
	private Integer payType;

	/**积分支付金额*/
	@JsonProperty("consume_points")
	@NotNull(message="积分支付金额不能为空")
	@Min(0)
	private Double consumePoints;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(Double consumeAmount) {
		this.consumeAmount = consumeAmount;
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

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Double getConsumeMoney() {
		return consumeMoney;
	}

	public void setConsumeMoney(Double consumeMoney) {
		this.consumeMoney = consumeMoney;
	}

	public Double getConsumePoints() {
		return consumePoints;
	}

	public void setConsumePoints(Double consumePoints) {
		this.consumePoints = consumePoints;
	}
	

	@Override
	public String toString() {
		return "RequestSaveOrder [orderId=" + orderId + ", consumeAmount=" + consumeAmount + ", productName="
				+ productName + ", orderSource=" + orderSource +  ", orderStatus=" +orderStatus
				+ ", consumeMoney=" + consumeMoney + ", consumePoints=" + consumePoints + ", memId=" + getMemId() +"]";
	}
	
}
