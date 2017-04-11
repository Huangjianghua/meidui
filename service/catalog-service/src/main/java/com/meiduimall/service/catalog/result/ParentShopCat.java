package com.meiduimall.service.catalog.result;

import java.util.List;

public class ParentShopCat {

	private Integer cat_id; //店铺自定义分类ID
	private String cat_name; //店铺自定义分类名字
	private List<ChildShopCat> childShopCat; //子分类
	
	public Integer getCat_id() {
		return cat_id;
	}
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public List<ChildShopCat> getChildShopCat() {
		return childShopCat;
	}
	public void setChildShopCat(List<ChildShopCat> childShopCat) {
		this.childShopCat = childShopCat;
	}
}
