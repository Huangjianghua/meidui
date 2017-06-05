package com.meiduimall.service.account.model.response;

/**
 * 提现主表ms_bank_withdraw_deposit实体类
 */
public class ResponseBankWithdrawDeposit {

	/** 业务单号 */
	private String businessNo;

	/** 审核/提现状态 0：待审核/提现申请 1：审核通过/提现成功 2；审核不通过/提现失败 */
	private String status;

	/** 申请时间 */
	private String applyDate;

	/** 审核时间 */
	private String auditDate;

	/** 银行卡号 */
	private String bankCardNo;

	/** 银行卡户名 */
	private String accountName;

	/** 银行卡开户行 */
	private String accountBank;

	/** 申请提现金额 */
	private Double applyCarryCash;

	/** 手续费 */
	private Double counterFee;

	/** 实际提现金额 */
	private Double actualCarryCash;

	/** 审核说明 */
	private String auditState;

	/** 备注 */
	private String remark;

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
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

	public Double getApplyCarryCash() {
		return applyCarryCash;
	}

	public void setApplyCarryCash(Double applyCarryCash) {
		this.applyCarryCash = applyCarryCash;
	}

	public Double getCounterFee() {
		return counterFee;
	}

	public void setCounterFee(Double counterFee) {
		this.counterFee = counterFee;
	}

	public Double getActualCarryCash() {
		return actualCarryCash;
	}

	public void setActualCarryCash(Double actualCarryCash) {
		this.actualCarryCash = actualCarryCash;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
