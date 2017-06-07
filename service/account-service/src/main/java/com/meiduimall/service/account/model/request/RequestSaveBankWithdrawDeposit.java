package com.meiduimall.service.account.model.request;

import org.hibernate.validator.constraints.NotEmpty;

public class RequestSaveBankWithdrawDeposit {

	@NotEmpty(message="用户标识不能为空")
	private String memId;//用户标识
	
	@NotEmpty(message="银行卡号不能为空")
	private String accountNo;//银行卡号
	
	@NotEmpty(message="申请提现金额不能为空")
	private String applyCarryCash;//申请提现金额

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getApplyCarryCash() {
		return applyCarryCash;
	}

	public void setApplyCarryCash(String applyCarryCash) {
		this.applyCarryCash = applyCarryCash;
	}
}
