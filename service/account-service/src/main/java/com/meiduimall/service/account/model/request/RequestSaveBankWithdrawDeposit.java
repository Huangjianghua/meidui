package com.meiduimall.service.account.model.request;

import org.hibernate.validator.constraints.NotEmpty;

public class RequestSaveBankWithdrawDeposit {

	@NotEmpty(message="用户标识不能为空")
	private String memId;//用户标识
	
//	@NotEmpty(message="身份证不能为空")
	private String accountIdcard;//身份证
	
	@NotEmpty(message="银行卡号不能为空")
	private String accountNo;//银行卡号
	
//	@NotEmpty(message="银行卡户名不能为空")
	private String accountName;//银行卡户名
	
//	@NotEmpty(message="银行名称不能为空")
	private String accountBank;//银行名称
	
//	@NotEmpty(message="银行所属省份不能为空")
	private String accountProvince;//银行所属省份
	
//	@NotEmpty(message="银行所属城市不能为空")
	private String accountCity;//银行所属城市
	
//	@NotEmpty(message="银行所属地区不能为空")
	private String accountArea;//银行所属地区
	
//	@NotEmpty(message="支行名称不能为空")
	private String accountSubBank;//支行名称
	
	@NotEmpty(message="申请提现金额不能为空")
	private String applyCarryCash;//申请提现金额
	
//	@NotEmpty(message="申请提现手续费不能为空")
	private String counterFee;//申请提现手续费
	
//	@NotEmpty(message="申请提现时间不能为空")
	private String applyDate;//申请提现时间
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getAccountIdcard() {
		return accountIdcard;
	}
	public void setAccountIdcard(String accountIdcard) {
		this.accountIdcard = accountIdcard;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountProvince() {
		return accountProvince;
	}
	public void setAccountProvince(String accountProvince) {
		this.accountProvince = accountProvince;
	}
	public String getAccountCity() {
		return accountCity;
	}
	public void setAccountCity(String accountCity) {
		this.accountCity = accountCity;
	}
	public String getAccountArea() {
		return accountArea;
	}
	public void setAccountArea(String accountArea) {
		this.accountArea = accountArea;
	}
	public String getAccountSubBank() {
		return accountSubBank;
	}
	public void setAccountSubBank(String accountSubBank) {
		this.accountSubBank = accountSubBank;
	}
	public String getApplyCarryCash() {
		return applyCarryCash;
	}
	public void setApplyCarryCash(String applyCarryCash) {
		this.applyCarryCash = applyCarryCash;
	}
	public String getCounterFee() {
		return counterFee;
	}
	public void setCounterFee(String counterFee) {
		this.counterFee = counterFee;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
}
