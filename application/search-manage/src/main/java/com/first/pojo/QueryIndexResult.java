package com.first.pojo;

import java.util.List;

import com.first.domain.Item;

/**
 * 索引查询结果
 * @date 2016年4月27日
 */
public class QueryIndexResult {

	private Long totalCount;
	
	private List<ShopInfo> shopInfos;
	
	private List<Brand> brands;
	
	private List<Cat> cats;
	
	private List<String> prices;
	
	private List<Property> properties;
	
	private List<Item> dataList;
	
	private CatModel catModel;
	
	private String status;

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<ShopInfo> getShopInfos() {
		return shopInfos;
	}

	public void setShopInfos(List<ShopInfo> shopInfos) {
		this.shopInfos = shopInfos;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public List<Cat> getCats() {
		return cats;
	}

	public void setCats(List<Cat> cats) {
		this.cats = cats;
	}

	public List<String> getPrices() {
		return prices;
	}

	public void setPrices(List<String> prices) {
		this.prices = prices;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public List<Item> getDataList() {
		return dataList;
	}

	public void setDataList(List<Item> dataList) {
		this.dataList = dataList;
	}

	public CatModel getCatModel() {
		return catModel;
	}

	public void setCatModel(CatModel catModel) {
		this.catModel = catModel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
