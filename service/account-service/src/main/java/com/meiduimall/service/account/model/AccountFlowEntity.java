package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;

import com.meiduimall.service.account.util.PageHelp;

public class AccountFlowEntity extends PageHelp implements Serializable{

	private static final long serialVersionUID = 7365383375759846252L;
	/**第三方流水明细表主键ID**/
	private String flowId; 
	/**流水类型**/
	private String flowType; 
	/**外部公司名称**/
	private String extcompanyCode; 
	/**账户名称**/
	private String accountType; 
	/**账户名称**/
	private String enterpriseAccountName; 
	/**变动前余额**/
	private String beforeChangeAmout; 
	/**变动后余额**/
	private String afterChangeAmout; 
	/**收入**/
	private String income; 
	/**支出**/
	private String expenses; 
	/**订单号**/
	private String orderId; 
	/**创建时间**/
	private Date createdDate; 
	/**备注**/
	private String remark; 
	/**创建人**/
	private String createdBy; 
	/**是否分页  1：是  0:否**/
	private String flg;
	/**开始时间**/
	private String flowTimeBegin;
	/**结束时间**/
	private String flowTimeEnd;
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getFlowType() {
		return flowType;
	}
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}
	public String getExtcompanyCode() {
		return extcompanyCode;
	}
	public void setExtcompanyCode(String extcompanyCode) {
		this.extcompanyCode = extcompanyCode;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBeforeChangeAmout() {
		return beforeChangeAmout;
	}
	public void setBeforeChangeAmout(String beforeChangeAmout) {
		this.beforeChangeAmout = beforeChangeAmout;
	}
	public String getAfterChangeAmout() {
		return afterChangeAmout;
	}
	public void setAfterChangeAmout(String afterChangeAmout) {
		this.afterChangeAmout = afterChangeAmout;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getExpenses() {
		return expenses;
	}
	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	public String getFlowTimeBegin() {
		return flowTimeBegin;
	}
	public void setFlowTimeBegin(String flowTimeBegin) {
		this.flowTimeBegin = flowTimeBegin;
	}
	public String getFlowTimeEnd() {
		return flowTimeEnd;
	}
	public void setFlowTimeEnd(String flowTimeEnd) {
		this.flowTimeEnd = flowTimeEnd;
	}
	public String getEnterpriseAccountName() {
		return enterpriseAccountName;
	}
	public void setEnterpriseAccountName(String enterpriseAccountName) {
		this.enterpriseAccountName = enterpriseAccountName;
	} 
	
}
