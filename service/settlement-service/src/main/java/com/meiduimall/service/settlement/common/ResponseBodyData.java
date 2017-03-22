package com.meiduimall.service.settlement.common;

import java.io.Serializable;

public class ResponseBodyData implements Serializable{

	private static final long serialVersionUID = 8882135921855797347L;

	private Object data;
	
	private Integer status;
	
	private String msg;
	

	public ResponseBodyData() {
		super();
	}
	
	public ResponseBodyData(Object data, Integer status, String msg) {
		super();
		this.data = data;
		this.status = status;
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	


}
