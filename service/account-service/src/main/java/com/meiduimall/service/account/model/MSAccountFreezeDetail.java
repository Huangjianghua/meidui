package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户冻结明细表ms_account_freeze_detail实体类
 * @author chencong
 *
 */
public class MSAccountFreezeDetail implements Serializable {
	
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
    
    /**冻结解冻标识（1：冻结；-1：解冻）*/
    private int inOrOut;
    
    /**冻结余额*/
    private Double freezeBalance;
    
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

	public Double getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(Double freezeBalance) {
		this.freezeBalance = freezeBalance;
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
   
}