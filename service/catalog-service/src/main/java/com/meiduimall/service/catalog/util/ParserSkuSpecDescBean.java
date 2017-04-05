package com.meiduimall.service.catalog.util;


public class ParserSkuSpecDescBean {

	private Integer prop_id;// 规格ID -- 对应syscategory_props表的prop_id字段
	private Integer prop_value_id;// 规格属性ID -- 对应syscategory_prop_values表的prop_value_id字段
	private String spec_value; //规格属性ID对应的值 -- 对应syscategory_prop_values表的prop_value字段
	
	public Integer getProp_id() {
		return prop_id;
	}
	public void setProp_id(Integer prop_id) {
		this.prop_id = prop_id;
	}
	public Integer getProp_value_id() {
		return prop_value_id;
	}
	public void setProp_value_id(Integer prop_value_id) {
		this.prop_value_id = prop_value_id;
	}
	public String getSpec_value() {
		return spec_value;
	}
	public void setSpec_value(String spec_value) {
		this.spec_value = spec_value;
	}
}
