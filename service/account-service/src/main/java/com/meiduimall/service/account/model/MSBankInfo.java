package com.meiduimall.service.account.model;

import java.io.Serializable;

/**
 * 类名:  MSBankInfoDTO<br>
 * 描述:  银行信息DTO <br>
 */
public class MSBankInfo implements Serializable{

	private static final long serialVersionUID = -1293907757054527479L;

	/** 银行标识 */
	private String bankId;

	/** 银行简称 */
	private String bankShort;

	/** 银行英文名称 */
	private String bankEnglishName;

	/** 银行中文名称 */
	private String bankChineName;

	/** 银行电话 */
	private String bankPhone;

	/** 银行网站 */
	private String bankNet;

	/** 银行排序号 */
	private String bankSort;

	/** 备注 */
	private String remark;

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankShort() {
		return bankShort;
	}

	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}

	public String getBankEnglishName() {
		return bankEnglishName;
	}

	public void setBankEnglishName(String bankEnglishName) {
		this.bankEnglishName = bankEnglishName;
	}

	public String getBankChineName() {
		return bankChineName;
	}

	public void setBankChineName(String bankChineName) {
		this.bankChineName = bankChineName;
	}

	public String getBankPhone() {
		return bankPhone;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}

	public String getBankNet() {
		return bankNet;
	}

	public void setBankNet(String bankNet) {
		this.bankNet = bankNet;
	}

	public String getBankSort() {
		return bankSort;
	}

	public void setBankSort(String bankSort) {
		this.bankSort = bankSort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
