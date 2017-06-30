package com.meiduimall.service.search.entity;

public class ItemPropValue {

	/** 属性ID **/
	private Integer propId;
	
	/** 属性值ID **/
	private Integer propValueId;

	/** 属性值名称 **/
	private String propValueName;

	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public Integer getPropValueId() {
		return propValueId;
	}

	public void setPropValueId(Integer propValueId) {
		this.propValueId = propValueId;
	}

	public String getPropValueName() {
		return propValueName;
	}

	public void setPropValueName(String propValueName) {
		this.propValueName = propValueName;
	}

	@Override
	public String toString() {
		return "ItemPropValue [propId=" + propId + ", propValueId="
				+ propValueId + ", propValueName=" + propValueName + "]";
	}
}
