package com.meiduimall.service.account.model.request;

import java.io.Serializable;
import java.util.Date;

public class MSMemberIntegralReq implements Serializable{

 
	private static final long serialVersionUID = -112408970384757542L;
	
	 private String memId;
	 
	 private Date sahBegin;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public Date getSahBegin() {
		return sahBegin;
	}

	public void setSahBegin(Date sahBegin) {
		this.sahBegin = sahBegin;
	}

 
	 

	
}
