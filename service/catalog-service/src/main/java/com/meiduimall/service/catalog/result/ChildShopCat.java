package com.meiduimall.service.catalog.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChildShopCat {

	@JsonProperty("cat_id")
	private Integer catId; //店铺自定义分类ID
	
	@JsonProperty("cat_name")
	private String catName; //店铺自定义分类名字

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
}
