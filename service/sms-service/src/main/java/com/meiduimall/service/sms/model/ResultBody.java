package com.meiduimall.service.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultBody {

	public static final String SUCCESS = "0";
	public static final String FAILED = "1";
	public static final String SUCCESS_MSG = "success";

	@JsonProperty("status_code")
	private String statusCode;

	@JsonProperty("result_msg")
	private String resultMsg;
	private Object data;

	public ResultBody() {
	}

	public ResultBody(String statusCode, String resultMsg) {
		super();
		this.statusCode = statusCode;
		this.resultMsg = resultMsg;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
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
