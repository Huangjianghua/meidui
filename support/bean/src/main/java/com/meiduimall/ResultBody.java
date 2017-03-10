package com.meiduimall;

import java.io.Serializable;



public class ResultBody implements Serializable {

	private static final long serialVersionUID = -586264308558755306L;

	public static final String SUCCESS = "0";
	public static final String FAILED = "1";

	private String status_code;
	private String result_msg;
	private Object data;

	public ResultBody() {

	}

	public ResultBody(String status_code, String result_msg) {
		this.status_code = status_code;
		this.result_msg = result_msg;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}

	public Object getData() {
		if (data == null) {
			return "";
		}
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	

}
