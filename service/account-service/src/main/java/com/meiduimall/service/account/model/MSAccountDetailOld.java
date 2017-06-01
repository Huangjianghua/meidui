package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户明细表ms_account_detail实体类
 * @author wujun
 *
 */
public class MSAccountDetailOld implements Serializable {

	private static final long serialVersionUID = 2295995824548157186L;

	private String id;   

	private String accountId;

	private String accountType;

	private String memId;

	private String tradeType;

	private String tradeAmount;

	private Date tradeDate;

	private Integer inOrOut;

	private String balance;

	private String businessNo;

	private String remark;

	private Date createDate;

	private Long currTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType == null ? null : accountType.trim();
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId == null ? null : memId.trim();
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType == null ? null : tradeType.trim();
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount == null ? null : tradeAmount.trim();
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Integer getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(Integer inOrOut) {
		this.inOrOut = inOrOut;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance == null ? null : balance.trim();
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo == null ? null : businessNo.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCurrTime() {
		return currTime;
	}

	public void setCurrTime(Long currTime) {
		this.currTime = currTime;
	}

}