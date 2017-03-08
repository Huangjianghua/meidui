package com.meiduimall.model;

import java.io.Serializable;

import com.meiduimall.util.DESC;

/**
 * 会员基本账户表
 * @author chencong
 *
 */
public class MSMemberBasicAccount implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 会员基本账户表ID **/
	private String mbaId;

	/** 会员ID **/
	private String memId;
	
	/** 消费返利积分  **/
	private String mbaConsumeProfit;
	
	/** 购物劵余额  **/
	private String mbaShoppingCoupon;

	/** 基本账户总额 **/
	private String mbaTotalQuantity;

	/** 创建时间 **/
	private java.util.Date mbaCreatedDate;

	/** 更新时间 **/
	private java.util.Date mbaUpdatedDate;

	/** 更新人 **/
	private String mbaUpdatedBy;

	/** 基本账户状态ID **/
	private String mbaStatus;

	/** 备注 **/
	private String mbaRemark;
	
	public String getMbaConsumeProfit() {
		if(this.mbaConsumeProfit!=null && !"".equals(this.mbaConsumeProfit)) {
			return DESC.deyption(this.mbaConsumeProfit, this.memId);
		} else {
			return "0";
		}
	}

	public void setMbaConsumeProfit(String mbaConsumeProfit) {
		this.mbaConsumeProfit = DESC.encryption(mbaConsumeProfit,this.memId);
	}

	public String getMbaShoppingCoupon() {
		if(this.mbaShoppingCoupon!=null && !"".equals(this.mbaShoppingCoupon)) {
			return DESC.deyption(this.mbaShoppingCoupon, this.memId);
		} else {
			return "0";
		}
	}

	public void setMbaShoppingCoupon(String mbaShoppingCoupon) {
		this.mbaShoppingCoupon = DESC.encryption(mbaShoppingCoupon,this.memId);
	}

	public void setMbaId(String mbaId) {
		this.mbaId = mbaId;
	}

	public String getMbaId() {
		return this.mbaId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setMbaTotalQuantity(String mbaTotalQuantity) {
		this.mbaTotalQuantity = DESC.encryption(mbaTotalQuantity,this.memId);
	}

	public String getMbaTotalQuantity() {
		return DESC.deyption(this.mbaTotalQuantity,this.memId);
	}

	public void setMbaCreatedDate(java.util.Date mbaCreatedDate) {
		this.mbaCreatedDate = mbaCreatedDate;
	}

	public java.util.Date getMbaCreatedDate() {
		return this.mbaCreatedDate;
	}

	public void setMbaUpdatedDate(java.util.Date mbaUpdatedDate) {
		this.mbaUpdatedDate = mbaUpdatedDate;
	}

	public java.util.Date getMbaUpdatedDate() {
		return this.mbaUpdatedDate;
	}

	public void setMbaUpdatedBy(String mbaUpdatedBy) {
		this.mbaUpdatedBy = mbaUpdatedBy;
	}

	public String getMbaUpdatedBy() {
		return this.mbaUpdatedBy;
	}

	public void setMbaStatus(String mbaStatus) {
		this.mbaStatus = mbaStatus;
	}

	public String getMbaStatus() {
		return this.mbaStatus;
	}

	public void setMbaRemark(String mbaRemark) {
		this.mbaRemark = mbaRemark;
	}

	public String getMbaRemark() {
		return this.mbaRemark;
	}
}