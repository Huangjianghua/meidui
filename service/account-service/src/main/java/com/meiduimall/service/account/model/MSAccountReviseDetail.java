package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户调账表ms_account_revise_detail实体类
 */
public class MSAccountReviseDetail implements Serializable{

	private static final long serialVersionUID = -1276071522471649187L;

	private String id;

	/** ms_account表account_no */
	private String accontNo;
	
	/** 调整类型(1-调增,2-调减) */
	private Integer reviseType;
	
	/** 调整前金额 */
	private Double beforeBalance;
	
	/** 调整金额 */
	private Double reviseBalance;
	
	/**状态(WR-待审核,AR-已审核,RR-已拒绝)  */
	private String status;
	
	/** 调整说明 */
	private String revise_remark;
	
	/** 审核说明 */
	private String review_remark;
	
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

	public String getAccontNo() {
		return accontNo;
	}

	public void setAccontNo(String accontNo) {
		this.accontNo = accontNo;
	}

	public Integer getReviseType() {
		return reviseType;
	}

	public void setReviseType(Integer reviseType) {
		this.reviseType = reviseType;
	}

	public Double getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(Double beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public Double getReviseBalance() {
		return reviseBalance;
	}

	public void setReviseBalance(Double reviseBalance) {
		this.reviseBalance = reviseBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRevise_remark() {
		return revise_remark;
	}

	public void setRevise_remark(String revise_remark) {
		this.revise_remark = revise_remark;
	}

	public String getReview_remark() {
		return review_remark;
	}

	public void setReview_remark(String review_remark) {
		this.review_remark = review_remark;
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
