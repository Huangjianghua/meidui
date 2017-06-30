package com.meiduimall.service.account.model.request;

import org.hibernate.validator.constraints.NotEmpty;

public class RequestBankWithdrawDepositsList {

	@NotEmpty(message="用户标识不能为空")
	private String memId;
	private int pageNo;
	private int pageSize;
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
