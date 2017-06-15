package com.meiduimall.service.catalog.result;

public class OrderTypeCountResult {

	private int cartNum;
	private int waitPay;
	private int waitDelivery;
	private int waitEnter;
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
