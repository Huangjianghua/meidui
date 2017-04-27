package com.meiduimall.service.account.model.request;

import java.io.Serializable;

/**
 * 解冻扣减积分和余额API请求映射model
 * @author chencong
 *
 */
public class RequestUnfreezeDecut implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**会员ID*/
	private String memId;
	
	/**交易订单号*/
	private String orderID;
	
	/**交易产品名称*/
	private String product_name;
	
	/**订单来源*/
	private String order_source;
	
	/**支付方式（积分、其他支付方式（比如支付宝，网银支付等等）） 1：表示单独使用积分支付 2：混合支付 3:其他第三方支付*/
	private String pay_type;
	
	/**余额支付金额*/
	private Double consume_money;
	
	/**积分支付金额*/
	private Double consume_points;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getOrder_source() {
		return order_source;
	}
	public void setOrder_source(String order_source) {
		this.order_source = order_source;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public Double getConsume_money() {
		return consume_money;
	}
	public void setConsume_money(Double consume_money) {
		this.consume_money = consume_money;
	}
	public Double getConsume_points() {
		return consume_points;
	}
	public void setConsume_points(Double consume_points) {
		this.consume_points = consume_points;
	}
	
	@Override
	public String toString() {
		return "UnfreezeDecutRequest [memId=" + memId + ", orderID=" + orderID + ", product_name=" + product_name
				+ ", order_source=" + order_source + ", pay_type=" + pay_type + ", consume_money=" + consume_money
				+ ", consume_points=" + consume_points + "]";
	}
	
}
