package com.first.pojo;

import com.first.constant.SolrConstant;

/**
 * 搜索参数
 * @date 2016年4月25日
 */
public class AppSearchParam {

	/**
	 * 搜索词
	 */
	private String keyword;
	
	/**
	 * 类目
	 */
	private String cat;
	
	// 类目ID
	private String cid;
	
	// 一级类目ID
	private String cid1;
	
	// 二级类目ID
	private String cid2;
	
	// 三级类目ID
	private String cid3;
	
	private Integer cat_id;
	
	/**
	 * 品牌
	 */
	private String brand;
	
	/**
	 * 价格区间
	 */
	private String price;
	
	/**
	 * 积分区间
	 */
	private String point;
	
	/**
	 * 属性
	 */
	private String prop;
	
	/**
	 * 排序
	 */
	private String r_sort;

	/**
	 * 当前页
	 */
	private String page = "1";
	
	/**
	 * 每页显示记录
	 */
	private int rows;
	
	/**
	 * 查询方式
	 */
	private String sc_type;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCid1() {
		return cid1;
	}

	public void setCid1(String cid1) {
		this.cid1 = cid1;
	}

	public String getCid2() {
		return cid2;
	}

	public void setCid2(String cid2) {
		this.cid2 = cid2;
	}

	public String getCid3() {
		return cid3;
	}

	public void setCid3(String cid3) {
		this.cid3 = cid3;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public String getR_sort() {
		return r_sort;
	}

	public void setR_sort(String r_sort) {
		this.r_sort = r_sort;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSc_type() {
		return sc_type;
	}

	public void setSc_type(String sc_type) {
		this.sc_type = sc_type;
	}

	public Integer getCat_id() {
		return cat_id;
	}

	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
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

	@Override
	public String toString() {
		return "SearchParam [keyword=" + keyword + ", cat=" + cat + ", cid="
				+ cid + ", cid1=" + cid1 + ", cid2=" + cid2 + ", cid3=" + cid3
				+ ", brand=" + brand + ", price=" + price + ", prop=" + prop
				+ ", r_sort=" + r_sort + ", page=" + page + ", rows=" + rows
				+ ", sc_type=" + sc_type + "]";
	}

}
