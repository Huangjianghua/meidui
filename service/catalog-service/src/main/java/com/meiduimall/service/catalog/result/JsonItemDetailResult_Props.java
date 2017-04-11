package com.meiduimall.service.catalog.result;

import java.util.List;

/**
 * 商品规格
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResult_Props {
	
	private String prop_id;//规格ID。比如：4,5
	private String prop_name;//规格名称。比如：颜色、服装尺码
	private List<JsonItemDetailResult_Prop_Values> prop_list;
	
	public String getProp_id() {
		return prop_id;
	}
	public void setProp_id(String prop_id) {
		this.prop_id = prop_id;
	}
	public String getProp_name() {
		return prop_name;
	}
	public void setProp_name(String prop_name) {
		this.prop_name = prop_name;
	}
	public List<JsonItemDetailResult_Prop_Values> getProp_list() {
		return prop_list;
	}
	public void setProp_list(List<JsonItemDetailResult_Prop_Values> prop_list) {
		this.prop_list = prop_list;
	}
}
