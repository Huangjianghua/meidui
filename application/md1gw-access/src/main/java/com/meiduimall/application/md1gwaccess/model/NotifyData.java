package com.meiduimall.application.md1gwaccess.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class NotifyData implements Serializable{

	
 
	private static final long serialVersionUID = 1559857383495443624L;

	private String trade_status;//订单状态					
	
	private BigDecimal total_fee;//订单金额					
												
	private String out_trade_no;//商城订单号					
												
	private String trade_no;//流水号					
												
	private String seller_id;//卖家账号					
												
	private String seller_email;											
												
	private String buyer_id;//买家账号					

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		this.total_fee = total_fee;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
												

	
	
	
	
}
