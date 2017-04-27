package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.account.util.DESC;

/**
 * @Copyright (C), 2002-2017, 美兑壹购物
 * @FileName: AccountReviseDetail.java
 * @Author: jianhua.huang
 * @Date: 2017年4月20日 上午11:18:54
 * @Description:添加或更新会员余额调整明细Model
 */
public class AddOrUpdateAccountReviseDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	/**
	 * 会员编号
	 */
	private String memId;
	/** 用户账户 */
	private String memLoginName;
	/** 手机号 */
	private String memPhone;
	/**
	 * 调整类型(1-调增,2-调减)
	 */
	private String reviseType;
	/**
	 * 调整前金额
	 */
	private BigDecimal beforeBalance;
	/**
	 * 调整金额
	 */
	private BigDecimal reviseBalance;
	/**
	 * 调整说明
	 */
	private String reviseRemark;
	/**
	 * 审核说明
	 */
	private String reviewRemark;
	/**
	 * 状态(WR-待审核,AR-已审核,RR-已拒绝)
	 */
	private String status;
	/**
	 * 
	 */
	private String createdBy;
	/**
	 * 
	 */
	private String createdDate;
	/**
	 * 
	 */
	private String updatedBy;

	private String updatedDate;

	private String isDelete;

	/**
	 * agree -同意 reject-拒绝
	 */
	private String operate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId == null ? null : memId.trim();
	}

	public String getReviseType() {
		return reviseType;
	}

	public void setReviseType(String reviseType) {
		this.reviseType = reviseType == null ? null : reviseType.trim();
	}

	public BigDecimal getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(BigDecimal beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public BigDecimal getReviseBalance() {
		return reviseBalance;
	}

	public void setReviseBalance(BigDecimal reviseBalance) {
		this.reviseBalance = reviseBalance;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy == null ? null : createdBy.trim();
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy == null ? null : updatedBy.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getReviseRemark() {
		return reviseRemark;
	}

	public void setReviseRemark(String reviseRemark) {
		this.reviseRemark = reviseRemark == null ? null : reviseRemark.trim();
	}

	public String getReviewRemark() {
		return reviewRemark;
	}

	public void setReviewRemark(String reviewRemark) {
		this.reviewRemark = reviewRemark == null ? null : reviewRemark.trim();
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getMemLoginName() throws SystemException {
		return  DESC.deyption(memLoginName);
	}

	public void setMemLoginName(String memLoginName) {
		this.memLoginName = memLoginName;
	}

	public String getMemPhone() throws SystemException {
		return DESC.deyption(memPhone);
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

}