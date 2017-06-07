package com.meiduimall.service.account.model.response;

/**
 * 提现申请列表返回对象
 */
public class ResponseBankWithdrawDeposit {
	// 所属地区
	private String accountArea;
	// 所属城市
	private String accountCity;
	// 证件号码
	private String accountIdcard;
	// 银行卡号
	private String accountNo;
	// 所属省份
	private String accountProvince;
	// 支行名称
	private String accountSubBank;
	// 审核人
	private String auditBy;
	// ???
	private String bankAccountId;
	// 创建时间
	private String createDate;
	// 主键ID
	private String id;
	// 会员ID
	private String memId;

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

	public String getAccountArea() {
		return accountArea;
	}

	public void setAccountArea(String accountArea) {
		this.accountArea = accountArea;
	}

	public String getAccountCity() {
		return accountCity;
	}

	public void setAccountCity(String accountCity) {
		this.accountCity = accountCity;
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

	public String getAccountProvince() {
		return accountProvince;
	}

	public void setAccountProvince(String accountProvince) {
		this.accountProvince = accountProvince;
	}

	public String getAccountSubBank() {
		return accountSubBank;
	}

	public void setAccountSubBank(String accountSubBank) {
		this.accountSubBank = accountSubBank;
	}

	public String getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}

	public String getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(String bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
}
