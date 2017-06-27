package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 次日奖励金明细表
 * @author guidl
 *
 */
public class EcmMzfBonusMorrowDetail implements Serializable {

	private static final long serialVersionUID = 3329092601235142264L;
	
	//主键id
	private int id;
	//订单编号
	private String orderSn;
	//商家编号
	private String sellerName;
	//商家账号
	private String sellerPhone; 
	//奖励金计算系数
	private String ratio;
	//奖励金
	private BigDecimal money;
	//创建时间
	private long createTime;
	//次日奖励金账单id
	private String billId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
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
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}

}
