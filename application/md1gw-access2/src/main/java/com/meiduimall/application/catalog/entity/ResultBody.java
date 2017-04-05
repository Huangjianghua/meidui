package com.meiduimall.application.catalog.entity;

/**
 * 统一返回数据格式
 * 
 * @author chencong
 *
 */
public class ResultBody {

	private String msg;
	private String status;
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
