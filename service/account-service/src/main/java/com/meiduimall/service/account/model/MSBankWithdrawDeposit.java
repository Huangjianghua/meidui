package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 提现主表ms_bank_withdraw_deposit实体类
 */
public class MSBankWithdrawDeposit extends BaseModel implements Serializable{

	private static final long serialVersionUID = -1276071522471649187L;

	/** 银行提现表 主键 */
	private String id;

	/** 业务单号 */
	private String businessNo;

	/** 对应的银行卡信息ID */
	private String bankAccountId;

	/** 证件号码 */
	private String accountIdcard;

	/** 银行卡号 */
	private String bankCardNo;

	/** 银行卡户名 */
	private String accountName;

	/** 银行卡开户行 */
	private String accountBank;

	/** 所属省份 */
	private String accountProvince;

	/** 所属城市 */
	private String accountCity;

	/** 所属地区 */
	private String accountArea;

	/** 支行名称 */
	private String accountSubBank;

	/** 申请提现金额 */
	private Double applyWithdrawAmount;

	/** 手续费 */
	private Double poundageAmount;

	/** 实际提现金额 */
	private Double actualWithdrawAmount;
	
	/** 实际转账金额 */
	private Double actualTransferAmount;

	/** 审核说明 */
	private String auditState;

	/** 申请时间 */
	private Date applyDate;

	/** 审核人 */
	private String auditBy;

	/** 审核时间 */
	private Date auditDate;

	/** 审核/提现状态 0：待审核/提现申请 1：审核通过/提现成功 2；审核不通过/提现失败 */
	private String status;

	/** 创建时间 */
	private Date createDate;
	
	/** 创建人 */
	private String createUser;

	/** 修改时间 */
	private Date updateDate;
	
	/** 更新人*/
	private String updateUser;
	
	/**备注*/
	private String remark;
	
	private String isDelete;
	
	private String operate;
	
	private List<MSBankWithDrawOperateDetail> listDetail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(String bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public String getAccountIdcard() {
		return accountIdcard;
	}

	public void setAccountIdcard(String accountIdcard) {
		this.accountIdcard = accountIdcard;
	}

	/**
	 * @return the bankCardNo
	 */
	public String getBankCardNo() {
		return bankCardNo;
	}

	/**
	 * @param bankCardNo the bankCardNo to set
	 */
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

	public Double getApplyWithdrawAmount() {
		return applyWithdrawAmount;
	}

	public void setApplyWithdrawAmount(Double applyWithdrawAmount) {
		this.applyWithdrawAmount = applyWithdrawAmount;
	}

	public Double getPoundageAmount() {
		return poundageAmount;
	}

	public void setPoundageAmount(Double poundageAmount) {
		this.poundageAmount = poundageAmount;
	}

	public Double getActualWithdrawAmount() {
		return actualWithdrawAmount;
	}

	public void setActualWithdrawAmount(Double actualWithdrawAmount) {
		this.actualWithdrawAmount = actualWithdrawAmount;
	}

	public Double getActualTransferAmount() {
		return actualTransferAmount;
	}

	public void setActualTransferAmount(Double actualTransferAmount) {
		this.actualTransferAmount = actualTransferAmount;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the isDelete
	 */
	public String getIsDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the operate
	 */
	public String getOperate() {
		return operate;
	}

	/**
	 * @param operate the operate to set
	 */
	public void setOperate(String operate) {
		this.operate = operate;
	}

	/**
	 * @return the listDetail
	 */
	public List<MSBankWithDrawOperateDetail> getListDetail() {
		return listDetail;
	}

	/**
	 * @param listDetail the listDetail to set
	 */
	public void setListDetail(List<MSBankWithDrawOperateDetail> listDetail) {
		this.listDetail = listDetail;
	}

}
