package com.meiduimall.service.catalog.entity;

/**
 * 查询结果--商品详情对象
 * @author yangchang
 *
 */
public class GoodsDetailResult {
	private String item_id;
	private String title;
	private String sub_title;
	private String price;
	private String point;
	private String image_default_id;
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getImage_default_id() {
		return image_default_id;
	}
	public void setImage_default_id(String image_default_id) {
		this.image_default_id = image_default_id;
	}
}
