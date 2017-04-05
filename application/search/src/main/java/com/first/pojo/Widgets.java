package com.first.pojo;

public class Widgets {

	private String widgetsId;
	
	private String widgetsType;
	
	private String params;
	
	private int modified;

	public String getWidgetsId() {
		return widgetsId;
	}

	public void setWidgetsId(String widgetsId) {
		this.widgetsId = widgetsId;
	}

	public String getWidgetsType() {
		return widgetsType;
	}

	public void setWidgetsType(String widgetsType) {
		this.widgetsType = widgetsType;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public int getModified() {
		return modified;
	}

	public void setModified(int modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "Widgets [widgetsId=" + widgetsId + ", widgetsType="
				+ widgetsType + ", params=" + params + ", modified=" + modified
				+ "]";
	}
}
