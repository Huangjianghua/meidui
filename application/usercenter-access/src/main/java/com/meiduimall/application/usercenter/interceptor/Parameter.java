package com.meiduimall.application.usercenter.interceptor;

import java.util.Map;


public class Parameter implements Map.Entry<String, String>{
	
	private String key;
	
	private String value;
	
	public Parameter(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String setValue(String value) {
		try {
            return this.value;
        } finally {
            this.value = value;
        }
	}
}