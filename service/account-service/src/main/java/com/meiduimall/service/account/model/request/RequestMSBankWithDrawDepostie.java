/**
 * 
 */
package com.meiduimall.service.account.model.request;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:   jianhua.huang 
 * @version:  2017年5月2日 上午11:30:10 0.1 
 * Description: 提现操作
 */
public class RequestMSBankWithDrawDepostie implements Serializable{
	private static final long serialVersionUID = 9095074600418113368L;
	/** 银行提现表 主键 */
	private String id;
	/** 业务单号 */
	private String businessNo;
	/** 会员标识 */
	private String memId;
	/** 会员登录账号 */
	private String loginName;
	private String phone;
	/** 银行卡号 */
	private String accountNo;
	/** 申请提现金额 */
	private Double applyWithdrawAmount;
	/** 申请提现金额 */
	private String ApplyCarryCash;
	/** 提现时余额*/
	private String withdrawBalance;
	/** 手续费 */
	private String counterFee;
	/** 实际提现金额 */
	private String actualCarryCash;
	/** 实际转账金额 */
	private String actualTransferAmount;
	/** 申请时间 */
	private Date applyDate;
	/** 审核人 */
	private String auditBy;
	/** 审核时间 */
	private Date auditDate;
	/** 提现状态 0:待客服审核/提现申请 1:待财务审核  2:待结算 3:审核通过/提现成功 3:审核不通过/提现失败  */
	private String status;
	/** 备注 */
	private String remark;

	private String balance;
	/**
	 * 操作提现
	 */
	private String operate;
	
	private String isDelete;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the businessNo
	 */
	public String getBusinessNo() {
		return businessNo;
	}

	/**
	 * @param businessNo the businessNo to set
	 */
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	/**
	 * @return the memId
	 */
	public String getMemId() {
		return memId;
	}

	/**
	 * @param memId the memId to set
	 */
	public void setMemId(String memId) {
		this.memId = memId;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the applyWithdrawAmount
	 */
	public Double getApplyWithdrawAmount() {
		return Double.valueOf(this.getApplyCarryCash());
	}

	/**
	 * @param applyWithdrawAmount the applyWithdrawAmount to set
	 */
	public void setApplyWithdrawAmount(Double applyWithdrawAmount) {
		this.applyWithdrawAmount = applyWithdrawAmount;
	}

	/**
	 * @return the withdrawBalance
	 */
	public String getWithdrawBalance() {
		return withdrawBalance;
	}

	/**
	 * @param withdrawBalance the withdrawBalance to set
	 */
	public void setWithdrawBalance(String withdrawBalance) {
		this.withdrawBalance = withdrawBalance;
	}

	/**
	 * @return the counterFee
	 */
	public String getCounterFee() {
		return counterFee;
	}

	/**
	 * @param counterFee the counterFee to set
	 */
	public void setCounterFee(String counterFee) {
		this.counterFee = counterFee;
	}

	/**
	 * @return the actualCarryCash
	 */
	public String getActualCarryCash() {
		return actualCarryCash;
	}

	/**
	 * @param actualCarryCash the actualCarryCash to set
	 */
	public void setActualCarryCash(String actualCarryCash) {
		this.actualCarryCash = actualCarryCash;
	}

	/**
	 * @return the actualTransferAmount
	 */
	public String getActualTransferAmount() {
		return actualTransferAmount;
	}

	/**
	 * @param actualTransferAmount the actualTransferAmount to set
	 */
	public void setActualTransferAmount(String actualTransferAmount) {
		this.actualTransferAmount = actualTransferAmount;
	}

	/**
	 * @return the applyDate
	 */
	public Date getApplyDate() {
		return applyDate;
	}

	/**
	 * @param applyDate the applyDate to set
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * @return the auditBy
	 */
	public String getAuditBy() {
		return auditBy;
	}

	/**
	 * @param auditBy the auditBy to set
	 */
	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}

	/**
	 * @return the auditDate
	 */
	public Date getAuditDate() {
		return auditDate;
	}

	/**
	 * @param auditDate the auditDate to set
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the balance
	 */
	public String getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance) {
		this.balance = balance;
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
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the applyCarryCash
	 */
	public String getApplyCarryCash() {
		return ApplyCarryCash;
	}

	/**
	 * @param applyCarryCash the applyCarryCash to set
	 */
	public void setApplyCarryCash(String applyCarryCash) {
		ApplyCarryCash = applyCarryCash;
	}
	
}
