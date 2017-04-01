package com.meiduimall.service.catalog.entity;

/**
 * 每一个商品规格分别对应的值
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResult_Prop_Values {
	
	private String prop_value_id;//规格属性ID
	private String prop_value;//规格属性名称，比如：颜色规格--红色、蓝色，服装尺码--XXL、XXXL
	
	public String getProp_value_id() {
		return prop_value_id;
	}
	public void setProp_value_id(String prop_value_id) {
		this.prop_value_id = prop_value_id;
	}
	public String getProp_value() {
		return prop_value;
	}
	public void setProp_value(String prop_value) {
		this.prop_value = prop_value;
	}
}
