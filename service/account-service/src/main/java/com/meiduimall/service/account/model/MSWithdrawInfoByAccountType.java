package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 提现子表ms_withdraw_info_by_account_type实体类
 */
public class MSWithdrawInfoByAccountType extends BaseModel implements Serializable{

	private static final long serialVersionUID = -1276071522471649187L;

	/** 银行提现表 主键 */
	private String id;

	/** 提现主表ms_bank_withdraw_deposit表的id */
	private String master_id;
	
	/** ms_account表的account_no */
	private String account_no;
	
	/** 当前账户类型对应的提现金额 */
	private Double withdrawAmount;

	/** 实提现时余额 */
	private Double withdrawBalance;

	/** 申请时间 */
	private Date applyDate;

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

	public String getMaster_id() {
		return master_id;
	}

	public void setMaster_id(String master_id) {
		this.master_id = master_id;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public Double getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(Double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public Double getWithdrawBalance() {
		return withdrawBalance;
	}

	public void setWithdrawBalance(Double withdrawBalance) {
		this.withdrawBalance = withdrawBalance;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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
