package com.meiduimall.service.catalog.entity;

/**
 * json数据构造对象--返回商品详情页访问地址
 * 
 * @author yangchang
 *
 */
public class CheckGoodsResult {

	// 商品的访问地址
	private String url;

	// 商品ID
	private String item_id;

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
}
