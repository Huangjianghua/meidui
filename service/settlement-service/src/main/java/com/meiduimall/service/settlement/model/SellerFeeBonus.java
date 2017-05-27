package com.meiduimall.service.settlement.model;

import java.io.Serializable;

public class SellerFeeBonus implements Serializable {

	private static final long serialVersionUID = -4121679067665857382L;
	
	//账单编号
	private String billId;
	//账单期
	private String billPeriod;
	//服务费
	private String fwMoney;
	//奖励金
	private String jlMoney;
	//奖励金发放账号
	private String sellerPhone;
	//账单创建时间
	private long createTime;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getBillPeriod() {
		return billPeriod;
	}

	public void setBillPeriod(String billPeriod) {
		this.billPeriod = billPeriod;
	}

	public String getFwMoney() {
		return fwMoney;
	}

	public void setFwMoney(String fwMoney) {
		this.fwMoney = fwMoney;
	}

	public String getJlMoney() {
		return jlMoney;
	}

	public void setJlMoney(String jlMoney) {
		this.jlMoney = jlMoney;
	}

	public String getSellerPhone() {
		return sellerPhone;
	}

	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	
}
