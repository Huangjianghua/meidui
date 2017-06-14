package com.meiduimall.application.mall.constant;

import net.sf.json.JSONObject;

public class ResponseBodyData{


	private Object data = new JSONObject();
	
	private Integer status = 0;
	
	private String msg;
	

	public ResponseBodyData() {
		super();
	}


 


	
	
	public ResponseBodyData(Object data) {
		this.data = data;
	}







	public ResponseBodyData(Object data, String msg) {
		this.data = data;
		this.msg = msg;
	}


	public ResponseBodyData(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}


	public ResponseBodyData(Object data, Integer status, String msg) {
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







	@Override
	public String toString() {
		return "ResponseBodyData [data=" + data + ", status=" + status + ", msg=" + msg + "]";
	}


	
	

}
