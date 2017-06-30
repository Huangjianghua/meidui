package com.meiduimall.service.search.entity;

public class PropValue {

	private String propValueId;
	
	private String propValue;
	
	private long count;

	public String getPropValueId() {
		return propValueId;
	}

	public void setPropValueId(String propValueId) {
		this.propValueId = propValueId;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "PropValue [propValueId=" + propValueId + ", propValue="
				+ propValue + ", count=" + count + "]";
	}
}
