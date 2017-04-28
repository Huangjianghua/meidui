package com.meiduimall.service.account.dto;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类名:  RequestDTO<br>
 * 描述:  请求数据传输对象 <br>
 * 创建时间: 2016-12-2
 */
public class RequestDTO implements Serializable{

	private static final long serialVersionUID = 6428066573289350974L;

	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private String ip;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
