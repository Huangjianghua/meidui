package com.meiduimall.service.search.entity;

public class Props {

	private Integer propId;
	
	private String propName;

	private Integer propValueId;
	
	private String propValue;

	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public Integer getPropValueId() {
		return propValueId;
	}

	public void setPropValueId(Integer propValueId) {
		this.propValueId = propValueId;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	@Override
	public String toString() {
		return "Props [propId=" + propId + ", propName=" + propName
				+ ", propValueId=" + propValueId + ", propValue=" + propValue
				+ "]";
	}
}
