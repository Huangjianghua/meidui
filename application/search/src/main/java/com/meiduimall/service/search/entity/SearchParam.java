package com.meiduimall.service.search.entity;

import com.meiduimall.service.search.constant.SolrConstant;

/**
 * 
 * @author:   jianhua.huang 
 * @version:  2017年4月26日 下午2:17:13 0.1 
 * Description:搜索参数
 */
public class SearchParam {

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
	
	private String b;
	
	/**
	 * 价格区间
	 */
	private String price;
	
	/**
	 * 属性
	 */
	private String prop;
	
	private String p;
	
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
	private int rows = SolrConstant.PAGE_LIMIT_THIRTY;
	
	/**
	 * 查询方式
	 */
	private String sc_type;
	
	private String opt;
	
	private String ip;
	private String platform;
	private String bid;
	private String pid;
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
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
