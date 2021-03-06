package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.meiduimall.exception.MdSysException;

import com.meiduimall.service.account.util.DESC;

/**
 * 添加或更新会员余额调整明细Model
 * 
 * @author: jianhua.huang
 * @version: 2017年5月10日 下午3:47:01 0.1 Description:
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
	/** 钱包类型 **/
	private String accountTypeName;
	private String accountTypeNo;
	private String accountNo;
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
	private String createUser;
	/**
	 * 
	 */
	private Date createDate;
	/**
	 * 
	 */
	private String updateUser;

	private Date updateDate;

	private String isDelete;

	/**
	 * agree -同意 reject-拒绝
	 */
	private String operate;

	private String remark;

	public String getAccountTypeNo() {
		return accountTypeNo;
	}

	public void setAccountTypeNo(String accountTypeNo) {
		this.accountTypeNo = accountTypeNo;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
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

	public String getMemLoginName() throws MdSysException {

		if (StringUtils.isNotBlank(this.memLoginName))
			DESC.deyption(memLoginName);
		return memLoginName;
	}

	public void setMemLoginName(String memLoginName) {
		this.memLoginName = memLoginName;
	}

	public String getMemPhone() throws MdSysException {

		if (StringUtils.isNotBlank(this.memLoginName))
			DESC.deyption(memLoginName);
		return memPhone;
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

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo
	 *            the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return
	 */
	@Override
	public String toString() {
		return "AddOrUpdateAccountReviseDetail [id=" + id + ", memId=" + memId + ", memLoginName=" + memLoginName
				+ ", memPhone=" + memPhone + ", accountTypeName=" + accountTypeName + ", accountTypeNo=" + accountTypeNo
				+ ", accountNo=" + accountNo + ", reviseType=" + reviseType + ", beforeBalance=" + beforeBalance
				+ ", reviseBalance=" + reviseBalance + ", reviseRemark=" + reviseRemark + ", reviewRemark="
				+ reviewRemark + ", status=" + status + ", createUser=" + createUser + ", createDate=" + createDate
				+ ", updateUser=" + updateUser + ", updateDate=" + updateDate + ", isDelete=" + isDelete + ", operate="
				+ operate + ", remark=" + remark + "]";
	}

}