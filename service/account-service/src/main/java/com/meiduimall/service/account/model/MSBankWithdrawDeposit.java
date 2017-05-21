package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ConstWithdrawStatus;
import com.meiduimall.service.account.util.DESC;

/**
 * 类名:  MSBankWithdrawDepositDTO<br>
 * 描述:  银行提现信息DTO <br>
 */
public class MSBankWithdrawDeposit implements Serializable{

	private static final long serialVersionUID = -1276071522471649187L;

	/** 银行提现表 主键 */
	private String id;

	/** 业务单号 */
	private String businessNo;

	/** 会员标识 */
	private String memId;
	
	/** 会员登录账号 */
	private String loginName;
	
	private String phone;

	/** 对应的银行卡信息ID */
	private String bankAccountId;

	/** 证件号码 */
	private String accountIdcard;

	/** 银行卡号 */
	private String accountNo;

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
	private String applyCarryCash;
	/** 提现时余额*/
	private String withdrawBalance;

	/** 手续费 */
	private String counterFee;

	/** 实际提现金额 */
	private String actualCarryCash;
	
	/** 实际转账金额 */
	private String actualTransferCash;

	/** 申请时间 */
	private Date applyDate;

	/** 审核人 */
	private String auditBy;

	/** 审核时间 */
	private Date auditDate;

	/** 审核/提现状态 0：待审核/提现申请 1：审核通过/提现成功 2；审核不通过/提现失败 */
	private String status;

	/** 说明 */
	private String auditState;

	/** 备注 */
	private String remark;

	/** 数据创建时间 */
	private Date createDate;
	
	private String balance;
	
	/**
	 * 操作提现
	 */
	private String operate;
	
	/**
	 * 提现操作记录详情
	 */
	private List<MSBankWithDrawOperateDetail> listDetail;
	
	private String isDelete;

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

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
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

	public String getActualCarryCash() {
		return actualCarryCash;
	}

	public void setActualCarryCash(String actualCarryCash) {
		this.actualCarryCash = actualCarryCash;
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
		return ConstWithdrawStatus.getNameByCode(this.status);
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	

	public String getLoginName() throws MdSysException {
		if(StringUtils.isNotBlank(this.loginName)) return DESC.deyption(loginName);
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPhone() throws MdSysException{
		if(StringUtils.isNotBlank(this.phone)) return DESC.deyption(phone);
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getActualTransferCash() {
		return actualTransferCash;
	}

	public void setActualTransferCash(String actualTransferCash) {
		this.actualTransferCash = actualTransferCash;
	}

	public String getWithdrawBalance() {
		return withdrawBalance;
	}

	public void setWithdrawBalance(String withdrawBalance) {
		this.withdrawBalance = withdrawBalance;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public List<MSBankWithDrawOperateDetail> getListDetail() {
		return listDetail;
	}

	public void setListDetail(List<MSBankWithDrawOperateDetail> listDetail) {
		this.listDetail = listDetail;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

}
