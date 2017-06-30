package com.meiduimall.service.catalog.entity;

import java.math.BigDecimal;

public class SystradePTrade {

	private Long platformId;

	private Integer userId;

	private String status;

	private BigDecimal totalMoney;

	private BigDecimal xfc;

	private BigDecimal walletPay;

	private BigDecimal coupPay;

	private BigDecimal shopingPay;

	private BigDecimal cashPay;

	private BigDecimal tWalletPay;

	private BigDecimal tCashPay;

	private BigDecimal tCoupPay;

	private BigDecimal tShopingPay;

	private BigDecimal tXfc;

	private Integer createdTime;

	private Integer updateTime;

	private Integer isSync;

	private Integer payType;

	private Integer errorNum;

	private Integer isPaying;

	private String tradeFrom;

	private Integer isUpdate;

	private Integer totalPoint;

	private Integer tTotalPoint;

	private Integer pointPay;

	private Integer tPointPay;

	private Integer isDelete;

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getXfc() {
		return xfc;
	}

	public void setXfc(BigDecimal xfc) {
		this.xfc = xfc;
	}

	public BigDecimal getWalletPay() {
		return walletPay;
	}

	public void setWalletPay(BigDecimal walletPay) {
		this.walletPay = walletPay;
	}

	public BigDecimal getCoupPay() {
		return coupPay;
	}

	public void setCoupPay(BigDecimal coupPay) {
		this.coupPay = coupPay;
	}

	public BigDecimal getShopingPay() {
		return shopingPay;
	}

	public void setShopingPay(BigDecimal shopingPay) {
		this.shopingPay = shopingPay;
	}

	public BigDecimal getCashPay() {
		return cashPay;
	}

	public void setCashPay(BigDecimal cashPay) {
		this.cashPay = cashPay;
	}

	public BigDecimal gettWalletPay() {
		return tWalletPay;
	}

	public void settWalletPay(BigDecimal tWalletPay) {
		this.tWalletPay = tWalletPay;
	}

	public BigDecimal gettCashPay() {
		return tCashPay;
	}

	public void settCashPay(BigDecimal tCashPay) {
		this.tCashPay = tCashPay;
	}

	public BigDecimal gettCoupPay() {
		return tCoupPay;
	}

	public void settCoupPay(BigDecimal tCoupPay) {
		this.tCoupPay = tCoupPay;
	}

	public BigDecimal gettShopingPay() {
		return tShopingPay;
	}

	public void settShopingPay(BigDecimal tShopingPay) {
		this.tShopingPay = tShopingPay;
	}

	public BigDecimal gettXfc() {
		return tXfc;
	}

	public void settXfc(BigDecimal tXfc) {
		this.tXfc = tXfc;
	}

	public Integer getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Integer createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsSync() {
		return isSync;
	}

	public void setIsSync(Integer isSync) {
		this.isSync = isSync;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}

	public Integer getIsPaying() {
		return isPaying;
	}

	public void setIsPaying(Integer isPaying) {
		this.isPaying = isPaying;
	}

	public String getTradeFrom() {
		return tradeFrom;
	}

	public void setTradeFrom(String tradeFrom) {
		this.tradeFrom = tradeFrom == null ? null : tradeFrom.trim();
	}

	public Integer getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Integer isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer gettTotalPoint() {
		return tTotalPoint;
	}

	public void settTotalPoint(Integer tTotalPoint) {
		this.tTotalPoint = tTotalPoint;
	}

	public Integer getPointPay() {
		return pointPay;
	}

	public void setPointPay(Integer pointPay) {
		this.pointPay = pointPay;
	}

	public Integer gettPointPay() {
		return tPointPay;
	}

	public void settPointPay(Integer tPointPay) {
		this.tPointPay = tPointPay;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}