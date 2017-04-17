package com.meiduimall.service.settlement.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ResultData implements Serializable {
	
	private static final long serialVersionUID = -7413497688928725916L;

	private String status_code;
	
	private String result_msg;
	
	private Object data;
	
	private List<Map<String,String>> RESULT;
	
	public ResultData(){
		
	}
	
	public ResultData(String status_code, String result_msg){
		this.status_code = status_code;
		this.result_msg = result_msg;
	}
	
	public ResultData(String status_code, String result_msg, Object data){
		this.status_code = status_code;
		this.result_msg = result_msg;
		this.data = data;
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
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public List<Map<String, String>> getRESULT() {
		return RESULT;
	}

	public void setRESULT(List<Map<String, String>> rESULT) {
		RESULT = rESULT;
	}
	
}
