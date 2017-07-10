package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 账户明细表ms_account_detail实体类
 * @author chencong
 *
 */
public class MSAccountDetail implements Serializable {
	
	private static final long serialVersionUID = 2295995824548157186L;

	private String id;

	/**ms_account表的account_no*/
    private String accountNo;
    
    /**交易类型*/
    private String tradeType;
    
    /**交易金额*/
    private Double tradeAmount;
    
    /**交易发生时间*/
    private Date tradeDate;
    
    /**账户变更标识（1：收入；-1：支出）*/
    private int inOrOut;
    
    /**余额*/
    private Double balance;
    
    /**业务单号*/
    private String businessNo;
    
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
	
	private String memId;
	
	private String memLoginName;
	
	private String memPhone;
	
	private String bizType;
	/**总流水标示			*/
	private String markWater;

	 

	public String getMemLoginName() {
		return memLoginName;
	}

	public void setMemLoginName(String memLoginName) {
		this.memLoginName = memLoginName;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public int getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(int inOrOut) {
		this.inOrOut = inOrOut;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
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

	public String getMarkWater() {
		return markWater;
	}

	public void setMarkWater(String markWater) {
		this.markWater = markWater;
	}
   
}