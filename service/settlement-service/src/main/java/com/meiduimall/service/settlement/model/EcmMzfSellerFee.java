package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 服务费
 * @author guidl
 *
 */
public class EcmMzfSellerFee implements Serializable {

	private static final long serialVersionUID = -1457529960747401241L;
	
	//主键id
	private int id;
	//账单编号
	private String billId;
	//商家编号
	private String sellerName;
	//商家账号
	private String sellerPhone;
    //金额（服务费/奖励金）
	private BigDecimal money;
	//状态 1-发放成功，2-未发放，3-发放失败
	private String state;
	//账单创建时间
	private long createTime;
	//账单修改时间
	private long updateTime;
	//发放时间
	private long issueTime;
	//类型
	private String moneyType;
	//备注
	private String remark;
	//账单期
	private String billPeriod;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerPhone() {
		return sellerPhone;
	}
	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public long getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(long issueTime) {
		this.issueTime = issueTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	public String getBillPeriod() {
		return billPeriod;
	}
	public void setBillPeriod(String billPeriod) {
		this.billPeriod = billPeriod;
	}

}
