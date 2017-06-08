package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 账户类型
 * @author jun.wu@meiduimall.com
 *
 */
public class MSAccountType implements Serializable{

	private static final long serialVersionUID = -6955727232474967171L;

	private String id;
	
	/**账户类型名称*/
    private String accountTypeName;

    /**账户类型编号*/
    private String accountTypeNo;

    /** 对应ms_account_type表的account_no_sequence */
	private Long accountNoSequence;
	
	/**是否可提现 0可提现 1不可提现*/
	private int allowWithdraw;
	
	/** 提现手续费比例，例如0.20 */
	private Double withdrawPoundageScale;
	
	/** 提现手续费下限 */
	private Double withdrawPoundageMin;
	
	/**提现手续费上限 */
	private Double withdrawPoundageMax;
	
	/** 是否支持退款 0支持 1不支持 */
	private int allowRefund;
	
	/** 退款手续费比例，例如0.20 */
	private Double refundPoundageScale;
	
	/** 退款手续费下限 */
	private Double refundPoundageMin;
	
	/**退款手续费上限 */
	private Double refundPoundageMax;

	/** 提现优先级*/
	private int withdrawPriority;
	
	/** 消费优先级 */
	private int spendPriority;

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

	 

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public String getAccountTypeNo() {
		return accountTypeNo;
	}

	public void setAccountTypeNo(String accountTypeNo) {
		this.accountTypeNo = accountTypeNo;
	}

	public Long getAccountNoSequence() {
		return accountNoSequence;
	}

	public void setAccountNoSequence(Long accountNoSequence) {
		this.accountNoSequence = accountNoSequence;
	}

	public int getAllowWithdraw() {
		return allowWithdraw;
	}

	public void setAllowWithdraw(int allowWithdraw) {
		this.allowWithdraw = allowWithdraw;
	}

	public Double getWithdrawPoundageScale() {
		return withdrawPoundageScale;
	}

	public void setWithdrawPoundageScale(Double withdrawPoundageScale) {
		this.withdrawPoundageScale = withdrawPoundageScale;
	}

	public Double getWithdrawPoundageMin() {
		return withdrawPoundageMin;
	}

	public void setWithdrawPoundageMin(Double withdrawPoundageMin) {
		this.withdrawPoundageMin = withdrawPoundageMin;
	}

	public Double getWithdrawPoundageMax() {
		return withdrawPoundageMax;
	}

	public void setWithdrawPoundageMax(Double withdrawPoundageMax) {
		this.withdrawPoundageMax = withdrawPoundageMax;
	}

	public int getAllowRefund() {
		return allowRefund;
	}

	public void setAllowRefund(int allowRefund) {
		this.allowRefund = allowRefund;
	}

	public Double getRefundPoundageScale() {
		return refundPoundageScale;
	}

	public void setRefundPoundageScale(Double refundPoundageScale) {
		this.refundPoundageScale = refundPoundageScale;
	}

	public Double getRefundPoundageMin() {
		return refundPoundageMin;
	}

	public void setRefundPoundageMin(Double refundPoundageMin) {
		this.refundPoundageMin = refundPoundageMin;
	}

	public Double getRefundPoundageMax() {
		return refundPoundageMax;
	}

	public void setRefundPoundageMax(Double refundPoundageMax) {
		this.refundPoundageMax = refundPoundageMax;
	}

	public int getWithdrawPriority() {
		return withdrawPriority;
	}

	public void setWithdrawPriority(int withdrawPriority) {
		this.withdrawPriority = withdrawPriority;
	}

	public int getSpendPriority() {
		return spendPriority;
	}

	public void setSpendPriority(int spendPriority) {
		this.spendPriority = spendPriority;
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