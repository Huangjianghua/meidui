package com.meiduimall.application.search.manage.domain;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8434261263875307517L;

	/**
	 * ID主键
	 */
	@Field
	private String id;
	
	/**
	 * 商品编号
	 */
	@Field
	private String itemId;
	
	/**
	 * 商品名称
	 */
	@Field
	private String title;
	
	/**
	 * 价格
	 */
	@Field
	private double price;
	
	/**
	 * 图片地址
	 */
	@Field
	private String image;
	
	/**
	 * 图片title
	 */
	private String imgTitle;
	
	/**
	 * 店铺名称
	 */
	@Field
	private String shopName;
	
	/**
	 * xfc
	 */
	@Field
	private double xfc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public double getXfc() {
		return xfc;
	}

	public void setXfc(double xfc) {
		this.xfc = xfc;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemId=" + itemId + ", title=" + title
				+ ", price=" + price + ", image=" + image + ", imgTitle="
				+ imgTitle + ", shopName=" + shopName + ", xfc=" + xfc + "]";
	}

}
