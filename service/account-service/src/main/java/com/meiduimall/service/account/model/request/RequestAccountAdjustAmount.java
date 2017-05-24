package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 账户余额调整API请求映射实体
 * @author chencong
 *
 */
public class RequestAccountAdjustAmount extends RequestBaseModel implements Serializable {

	private static final long serialVersionUID = -4128146492405140400L;
	
	@NotEmpty(message="来源不能为空")
	private String source ;
	
	@NotEmpty(message="交易类型不能为空")
	private String trade_type;
	
	@NotEmpty(message="订单号不能为空")
	private String order_id;
	
	@NotEmpty(message="调账方向不能为空")
	private String direction;
	
	@NotNull(message="交易金额不能为空")
	private Double trade_amount;
	
	@NotEmpty(message="交易时间不能为空")
	private String trade_time;
	
	private String remark;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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

	public String getTrade_time() {
		return trade_time;
	}

	public void setTrade_time(String trade_time) {
		this.trade_time = trade_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "RequestAccountAdjustAmount [source=" + source + ", trade_type=" + trade_type + ", order_id=" + order_id
				+ ", direction=" + direction + ", trade_amount=" + trade_amount + ", trade_time=" + trade_time
				+ ", remark=" + remark + "]";
	}

}
