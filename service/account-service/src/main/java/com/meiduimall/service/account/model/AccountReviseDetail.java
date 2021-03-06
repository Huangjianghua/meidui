
package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.math.BigDecimal;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ConstAccountAdjustType;
import com.meiduimall.service.account.constant.ConstAccountAdjustStatus;
import com.meiduimall.service.account.util.DESC;

/**
 * * * @author: jianhua.huang * @version: 2017年5月5日 下午5:51:45 0.1 *
 * Description:会员余额调整明细Model
 */
public class AccountReviseDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	/** * 会员编号 */
	private String memId;
	/** 用户账户 */
	private String memLoginName;
	/** 手机号 */
	private String memPhone;
	private String accountTypeName;
	private String accountTypeNo;
	private String accountNo;
	/** * 调整类型(1-调增,2-调减) */
	private String reviseType;
	/** * 调整前金额 */
	private BigDecimal beforeBalance;
	/** * 调整金额 */
	private BigDecimal reviseBalance;
	/** * 调整说明 */
	private String reviseRemark;
	/** * 审核说明 */
	private String reviewRemark;
	/** * 状态(WR-待审核,AR-已审核,RR-已拒绝) */
	private String status;
	/** * */
	private String createUser;
	/** * */
	private String createDate;
	/** * */
	private String updateUser;
	private String updateDate;
	private String remark;

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** * agree -同意 reject-拒绝 */
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
		return ConstAccountAdjustType.getNameByCode(reviseType);
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return ConstAccountAdjustStatus.getNameByCode(status);
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

	public String getMemLoginName() throws MdSysException {
		return DESC.deyption(memLoginName);
	}

	public void setMemLoginName(String memLoginName) {
		this.memLoginName = memLoginName;
	}

	public String getMemPhone() throws MdSysException {
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

	/** * @return the accountNo */
	public String getAccountNo() {
		return accountNo;
	}

	/** * @param accountNo the accountNo to set */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/** * @return the accountTypeNo */
	public String getAccountTypeNo() {
		return accountTypeNo;
	}

	/** * @param accountTypeNo the accountTypeNo to set */
	public void setAccountTypeNo(String accountTypeNo) {
		this.accountTypeNo = accountTypeNo;
	}
}
