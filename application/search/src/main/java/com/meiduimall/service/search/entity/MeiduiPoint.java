package com.meiduimall.service.search.entity;

import java.io.Serializable;

public class MeiduiPoint implements Serializable {

	private static final long serialVersionUID = -7737987061796061378L;

	private String app;
	
	private String key;
	
	private String value;

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
