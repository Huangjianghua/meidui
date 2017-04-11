package com.meiduimall.application.search.manage.request;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;

import com.meiduimall.application.search.manage.constant.SysConstant;

public class IndexRequest {
	
	@NotBlank(message="操作类型不能为空")
	private String opt;
	
	
	@NotBlank(message="操作条件不能为空")
	private String q;
	
	@Digits(integer=100000,fraction=0,message="每页数量必须是数字")  
	private Integer number=SysConstant.PAGE_LIMIT_INDEX;
	
	
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
