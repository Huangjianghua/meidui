package com.meiduimall.application.mall.pay.model;

import java.io.Serializable;

public class PaymentTrade implements Serializable{
 
	private static final long serialVersionUID = -4690630299637789177L;

	private String token;               //token
	
	private String user_id;             //用户id
	
	private String payment_id;          //支付单号
	
	private String use_point;           //积分
	
	private String use_money;           //余额

	private String money;           //支付货币金额
	
	private String pay_type;            //支付类型  wechatpay:微信  其他:支付宝
	
	private String pay_password;         //支付密码  (明文)
	
	private String wechat_pay_account_type;  //1:壹购物的签名  2:美兑的签名
	
	private String memId;
	
	private String payway;
	
	private String is_new;
	
	

	public String getIs_new() {
		return is_new;
	}

	public void setIs_iOS_new(String is_new) {
		this.is_new = is_new;
	}

	 

	@Override
	public String toString() {
		return "PaymentTrade [token=" + token + ", user_id=" + user_id + ", payment_id=" + payment_id + ", use_point="
				+ use_point + ", use_money=" + use_money + ", money=" + money + ", pay_type=" + pay_type
				+ ", pay_password=" + pay_password + ", wechat_pay_account_type=" + wechat_pay_account_type + ", memId="
				+ memId + ", payway=" + payway + ", is_new=" + is_new + "]";
	}

	public String getPayway() {
		return payway;
	}

	public void setPayway(String payway) {
		this.payway = payway;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getUse_point() {
		return use_point;
	}

	public void setUse_point(String use_point) {
		this.use_point = use_point;
	}

	public String getUse_money() {
		return use_money;
	}

	public void setUse_money(String use_money) {
		this.use_money = use_money;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getPay_password() {
		return pay_password;
	}

	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}

	public String getWechat_pay_account_type() {
		return wechat_pay_account_type;
	}

	public void setWechat_pay_account_type(String wechat_pay_account_type) {
		this.wechat_pay_account_type = wechat_pay_account_type;
	}
	
	
	
	
}
