package com.meiduimall.application.mall.catalog.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商城数据
 * @author yangchang
 */
public class MallInfoResult {

	/** 购物车商品数量(比如商品A有1个，商品B有3个，返回4) */
	@JsonProperty("cart_num")
	private int cartNum;

	/** 待付款 */
	@JsonProperty("wait_pay")
	private int waitPay;

	/** 待发货 */
	@JsonProperty("wait_delivery")
	private int waitDelivery;

	/** 待收货 */
	@JsonProperty("wait_enter")
	private int waitEnter;

	/** 待评价 */
	@JsonProperty("wait_rate")
	private int waitRate;

	public int getCartNum() {
		return cartNum;
	}

	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	public int getWaitPay() {
		return waitPay;
	}

	public void setWaitPay(int waitPay) {
		this.waitPay = waitPay;
	}

	public int getWaitDelivery() {
		return waitDelivery;
	}

	public void setWaitDelivery(int waitDelivery) {
		this.waitDelivery = waitDelivery;
	}

	public int getWaitEnter() {
		return waitEnter;
	}

	public void setWaitEnter(int waitEnter) {
		this.waitEnter = waitEnter;
	}

	public int getWaitRate() {
		return waitRate;
	}

	public void setWaitRate(int waitRate) {
		this.waitRate = waitRate;
	}
}
