package com.first.pojo;

/**
 * 最新价格
 * @author Administrator
 *
 */
public class NewestPrice {
	
	private int itemId;

	private double price;
	
	private double costPrice;
	
	private double point;

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

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "NewestPrice [itemId=" + itemId + ", price=" + price + ", costPrice=" + costPrice + ", point=" + point
				+ "]";
	}
}
