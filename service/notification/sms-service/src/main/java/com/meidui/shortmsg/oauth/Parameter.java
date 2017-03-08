package com.meidui.shortmsg.oauth;

import java.util.Map;

/**
 * Title : Parameter 
 * Description : 参数
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月16日 下午2:48:05 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
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