package com.meiduimall.service.catalog.entity;

/**
 * 商品SKU
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResult_Sku {
	
	private String sku_id;
	private String price;// 该规格商品价格
	private String weight;// 该规格商品重量
	//private String spec_info;// 该规格描述信息
	private String status;// sku状态: normal 代表正常， delete 代表删除
	private String point;// 该规格商品可用美兑积分
	
	// 由syscategory_prop_values表中的prop_value_id组成的字符串，格式如下： 51_4_76
	private String prop_value_ids;
	
	private String sku_store;//每一个SKU对应的库存
	
	public String getSku_store() {
		return sku_store;
	}
	public void setSku_store(String sku_store) {
		this.sku_store = sku_store;
	}
	public String getSku_id() {
		return sku_id;
	}
	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
//	public String getSpec_info() {
//		return spec_info;
//	}
//	public void setSpec_info(String spec_info) {
//		this.spec_info = spec_info;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getProp_value_ids() {
		return prop_value_ids;
	}
	public void setProp_value_ids(String prop_value_ids) {
		this.prop_value_ids = prop_value_ids;
	}
}
