package com.meiduimall.application.search.pojo;

public class XFCPrice {
	
	private int itemId;

	private double price;
	
	private double costPrice;
	
	private double xfc;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getXfc() {
		return xfc;
	}

	public void setXfc(double xfc) {
		this.xfc = xfc;
	}

	@Override
	public String toString() {
		return "XFCPrice [itemId=" + itemId + ", price=" + price
				+ ", costPrice=" + costPrice + ", xfc=" + xfc + "]";
	}
}
