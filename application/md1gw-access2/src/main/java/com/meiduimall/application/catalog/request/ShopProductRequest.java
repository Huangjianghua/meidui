package com.meiduimall.application.catalog.request;

import javax.validation.constraints.NotNull;

public class ShopProductRequest {

	// 店铺ID
	@NotNull
	private Integer shop_id;

	// 店铺自定义分类ID，可以为空
	private Integer shop_cat_id;

	// 排序字段：store 按销量，updateTime 按修改时间，price 按价格，point 按积分；默认 store 按销量
	private String order_by;

	// 排序规则：desc 降序，asc 升序；默认 desc 降序
	private String column;

	// 页数，从1开始，默认为1
	private Integer pageNo;
	
	// 每页显示数量，默认20
	private Integer pageSize;
	
	// 开始位置
	private Integer limitBegin;
	
	public Integer getLimitBegin() {
		return limitBegin;
	}

	public void setLimitBegin(Integer limitBegin) {
		this.limitBegin = limitBegin;
	}

	public Integer getShop_cat_id() {
		return shop_cat_id;
	}

	public void setShop_cat_id(Integer shop_cat_id) {
		this.shop_cat_id = shop_cat_id;
	}

	public Integer getShop_id() {
		return shop_id;
	}

	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
