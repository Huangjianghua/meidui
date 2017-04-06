package com.meiduimall.service.catalog.entity;

import java.util.List;

/**
 * APP商品详情页，请求接口返回json数据，对应的实体类
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResult {

	private JsonItemDetailResult_ShopData shopData;//店铺相关
	private JsonItemDetailResult_ItemData itemData;//商品相关
	private List<JsonItemDetailResult_Sku> skuList;//SKU相关
	private List<JsonItemDetailResult_Props> itemPropsList;//规格相关
	
	public JsonItemDetailResult_ShopData getShopData() {
		return shopData;
	}
	public void setShopData(JsonItemDetailResult_ShopData shopData) {
		this.shopData = shopData;
	}
	public JsonItemDetailResult_ItemData getItemData() {
		return itemData;
	}
	public void setItemData(JsonItemDetailResult_ItemData itemData) {
		this.itemData = itemData;
	}
	public List<JsonItemDetailResult_Sku> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<JsonItemDetailResult_Sku> skuList) {
		this.skuList = skuList;
	}
	public List<JsonItemDetailResult_Props> getItemPropsList() {
		return itemPropsList;
	}
	public void setItemPropsList(List<JsonItemDetailResult_Props> itemPropsList) {
		this.itemPropsList = itemPropsList;
	}
}

