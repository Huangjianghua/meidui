package com.meiduimall.service.account.model;

import java.io.Serializable;

public class AccountRechargeApply implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9169330321048734969L;
	/**充值申请表主键ID**/
	private String recId;
	/**第三方企业信息详情表主键ID**/
	private String detId;
	/**订单号**/
	private String orderId;
	/**外部订单号**/
	private String extorderId;
	/**四位英文企业标识，由美兑发放,通过线下途径申请**/
	private String extCompanyCode;
	/**企业名字**/
	private String companyName;
	/**会员账号**/
	private String accountId;
	/**帐户类型**/
	private String accountType;
	/**帐户名字**/
	private String accountName;
	/**充值金额**/
	private String rechargeAmout;
	/**充值申请时间**/
	private String rechargeDate;
	/**实际充值时间**/
	private String realityRechargeDate;
	/**审批状态**/
	private String status;
	/**备注**/
	private String remark;
	/**回调地址**/
	private String callbackUrl;
	/**是否分页  1：是  0:否	*/
	private String flg;  
	/**phone**/
	private String phone;
	/**memId**/
	private String memId;
	public String getRecId() {
		return recId;
	}

	public void setRecId(String recId) {
		this.recId = recId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExtorderId() {
		return extorderId;
	}

	public void setExtorderId(String extorderId) {
		this.extorderId = extorderId;
	}
	
	public String getExtCompanyCode() {
		return extCompanyCode;
	}

	public void setExtCompanyCode(String extCompanyCode) {
		this.extCompanyCode = extCompanyCode;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getRechargeAmout() {
		return rechargeAmout;
	}

	public void setRechargeAmout(String rechargeAmout) {
		this.rechargeAmout = rechargeAmout;
	}

	public String getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(String rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	public String getRealityRechargeDate() {
		return realityRechargeDate;
	}

	public void setRealityRechargeDate(String realityRechargeDate) {
		this.realityRechargeDate = realityRechargeDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDetId() {
		return detId;
	}

	public void setDetId(String detId) {
		this.detId = detId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}	
	
	
}
