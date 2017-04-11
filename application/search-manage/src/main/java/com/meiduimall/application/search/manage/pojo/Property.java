package com.meiduimall.application.search.manage.pojo;

import java.util.List;

public class Property {

	private String propId;
	
	private String propName;
	
	private List<PropValue> properties;

	public String getPropId() {
		return propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public List<PropValue> getProperties() {
		return properties;
	}

	public void setProperties(List<PropValue> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Property [propId=" + propId + ", propName=" + propName
				+ ", properties=" + properties + "]";
	}
}
