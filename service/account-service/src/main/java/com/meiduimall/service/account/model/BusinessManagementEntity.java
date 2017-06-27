package com.meiduimall.service.account.model;

import java.io.Serializable;

import com.meiduimall.service.account.util.PageHelp;

public class BusinessManagementEntity extends PageHelp implements Serializable{

	private static final long serialVersionUID = -6791960390223131523L;
	/**主键ID**/
	private String entId;
	/**企业标识**/
	private String enterpriseIdentity;
	/**企业名称**/
	private String enterpriseName;
	/**企业KEY**/
	private String enterpriseKey;
	/**详情表主键ID**/
	private String detId;
	/**企业账户名称**/
	private String enterpriseAccountName;
	/**个人账户类型**/
	private String personalAccountType;
	/**授信额度**/
	private String lineOfCredit;
	/**现金帐户**/
	private String cashAccount;
	/**充值现金**/
	private String cashRecharge;
	/**总额度**/
	private String totalAmount;
	/**是否分页  1：是  0:否	*/
	private String flg;
	
	public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
	}
	public String getEnterpriseIdentity() {
		return enterpriseIdentity;
	}
	public void setEnterpriseIdentity(String enterpriseIdentity) {
		this.enterpriseIdentity = enterpriseIdentity;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getEnterpriseKey() {
		return enterpriseKey;
	}
	public void setEnterpriseKey(String enterpriseKey) {
		this.enterpriseKey = enterpriseKey;
	}
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	public String getDetId() {
		return detId;
	}
	public void setDetId(String detId) {
		this.detId = detId;
	}
	public String getEnterpriseAccountName() {
		return enterpriseAccountName;
	}
	public void setEnterpriseAccountName(String enterpriseAccountName) {
		this.enterpriseAccountName = enterpriseAccountName;
	}
	public String getPersonalAccountType() {
		return personalAccountType;
	}
	public void setPersonalAccountType(String personalAccountType) {
		this.personalAccountType = personalAccountType;
	}
	public String getLineOfCredit() {
		return lineOfCredit;
	}
	public void setLineOfCredit(String lineOfCredit) {
		this.lineOfCredit = lineOfCredit;
	}
	public String getCashAccount() {
		return cashAccount;
	}
	public void setCashAccount(String cashAccount) {
		this.cashAccount = cashAccount;
	}
	public String getCashRecharge() {
		return cashRecharge;
	}
	public void setCashRecharge(String cashRecharge) {
		this.cashRecharge = cashRecharge;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	} 
	
}
