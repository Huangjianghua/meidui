package com.meiduimall.service.account.model;

import java.io.Serializable;

/**
 * 类名:  MSBankAccountDTO<br>
 * 描述: 银行账户信息DTO <br>
 */
public class MSBankAccount implements Serializable{

	private static final long serialVersionUID = 2756041136883852516L;

	/** 银行账户信息表 主键 */
	private String id;

	/** 会员标识 */
	private String memId;

	/** 卡号 */
	private String accountNo;
	
	/** 证件号码 */
	private String accountIdcard;

	/** 卡户名 */
	private String accountName;

	/** 开户行 */
	private String accountBank;

	/** 所属省份 */
	private String accountProvince;

	/** 所属城市 */
	private String accountCity;

	/** 所属地区 */
	private String accountArea;

	/** 支行名称 */
	private String accountSubBank;

	/** 是否是默认的银行卡 0：否；1：是； */
	private String isDefault;

	/** 备注 */
	private String remark;

	/** 数据创建时间 */
	private String createDate;

	/** 数据更新时间 */
	private String updateDate;

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

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
