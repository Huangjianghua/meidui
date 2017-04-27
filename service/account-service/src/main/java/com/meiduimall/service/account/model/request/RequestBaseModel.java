package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class RequestBaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="会员ID不能为空")
	@Length(min=36,max=36,message="会员ID长度不正确")
	private String memId;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
}
