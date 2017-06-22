package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;

import com.meiduimall.service.account.util.PageHelp;

public class MSRechargeApply extends PageHelp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9169330321048734969L;
	/**充值申请表主键ID**/
	private String recId;
	/**订单号**/
	private String orderId;
	/**外部订单号**/
	private String extorderId;
	/**四位英文企业标识，由美兑发放,通过线下途径申请**/
	private String extCompanyCode;
	/**会员账号**/
	private String accountId;
	/**帐户类型**/
	private String accountType;
	/**帐户名字**/
	private String accountName;
	/**充值金额**/
	private String rechargeAmout;
	/**充值申请时间**/
	private Date rechargeDate;
	/**实际充值时间**/
	private Date realityRechargeDate;
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
	
	/**充值申请开始时间**/
	private String rechargeTimeBegin;
	/**充值申请开始时间**/
	private String rechargeTimeEnd;
	
	/**实际充值开始时间**/
	private String realityRechargeTimeBegin;
	/**实际充值结束时间**/
	private String realityRechargeTimeEnd;
	
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

	public Date getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(Date rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	public Date getRealityRechargeDate() {
		return realityRechargeDate;
	}

	public void setRealityRechargeDate(Date realityRechargeDate) {
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

	public String getRechargeTimeBegin() {
		return rechargeTimeBegin;
	}

	public void setRechargeTimeBegin(String rechargeTimeBegin) {
		this.rechargeTimeBegin = rechargeTimeBegin;
	}

	public String getRechargeTimeEnd() {
		return rechargeTimeEnd;
	}

	public void setRechargeTimeEnd(String rechargeTimeEnd) {
		this.rechargeTimeEnd = rechargeTimeEnd;
	}

	public String getRealityRechargeTimeBegin() {
		return realityRechargeTimeBegin;
	}

	public void setRealityRechargeTimeBegin(String realityRechargeTimeBegin) {
		this.realityRechargeTimeBegin = realityRechargeTimeBegin;
	}

	public String getRealityRechargeTimeEnd() {
		return realityRechargeTimeEnd;
	}

	public void setRealityRechargeTimeEnd(String realityRechargeTimeEnd) {
		this.realityRechargeTimeEnd = realityRechargeTimeEnd;
	}	
	
	
}
