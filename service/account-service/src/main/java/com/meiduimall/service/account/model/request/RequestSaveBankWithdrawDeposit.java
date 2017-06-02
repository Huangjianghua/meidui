package com.meiduimall.service.account.model.request;

public class RequestSaveBankWithdrawDeposit {

	private String memId;//用户标识
	private String accountIdcard;//身份证
	private String accountNo;//银行卡号
	private String accountName;//银行卡户名
	private String accountBank;//银行名称
	private String accountProvince;//银行所属省份
	private String accountCity;//银行所属城市
	private String accountArea;//银行所属地区
	private String accountSubBank;//支行名称
	private String applyCarryCash;//申请提现金额
	private String counterFee;//申请提现手续费
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
