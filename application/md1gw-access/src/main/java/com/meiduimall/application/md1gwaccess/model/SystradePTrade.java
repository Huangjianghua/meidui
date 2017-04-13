package com.meiduimall.application.md1gwaccess.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SystradePTrade implements Serializable {
    private BigInteger platformId;

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

    private Integer createdTime;

    private Integer updateTime;

    private Byte isSync;

    private Integer payType;

    private BigDecimal tXfc;

    private Byte errorNum;

    private Byte isPaying;

    private String tradeFrom;

    private Byte isUpdate;

    private Integer totalPoint;

    private Integer pointPay;

    private Integer tPointPay;

    private Integer tTotalPoint;

    private static final long serialVersionUID = 1L;

    
    
    public SystradePTrade(BigInteger platformId, BigDecimal walletPay) {
		super();
		this.platformId = platformId;
		this.walletPay = walletPay;
	}
    
    

	public SystradePTrade(BigInteger platformId, String status) {
		super();
		this.platformId = platformId;
		this.status = status;
	}



	public SystradePTrade() {
		super();
	}

	public BigInteger getPlatformId() {
        return platformId;
    }

    public void setPlatformId(BigInteger platformId) {
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

    

    public Byte getIsSync() {
		return isSync;
	}



	public void setIsSync(Byte isSync) {
		this.isSync = isSync;
	}



	public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal gettXfc() {
        return tXfc;
    }

    public void settXfc(BigDecimal tXfc) {
        this.tXfc = tXfc;
    }

    
    public String getTradeFrom() {
        return tradeFrom;
    }

    public void setTradeFrom(String tradeFrom) {
        this.tradeFrom = tradeFrom == null ? null : tradeFrom.trim();
    }

     

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
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

    public Integer gettTotalPoint() {
        return tTotalPoint;
    }

    public void settTotalPoint(Integer tTotalPoint) {
        this.tTotalPoint = tTotalPoint;
    }

    
    
    public Byte getErrorNum() {
		return errorNum;
	}



	public void setErrorNum(Byte errorNum) {
		this.errorNum = errorNum;
	}



	public Byte getIsPaying() {
		return isPaying;
	}



	public void setIsPaying(Byte isPaying) {
		this.isPaying = isPaying;
	}



	public Byte getIsUpdate() {
		return isUpdate;
	}



	public void setIsUpdate(Byte isUpdate) {
		this.isUpdate = isUpdate;
	}



	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", platformId=").append(platformId);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", totalMoney=").append(totalMoney);
        sb.append(", xfc=").append(xfc);
        sb.append(", walletPay=").append(walletPay);
        sb.append(", coupPay=").append(coupPay);
        sb.append(", shopingPay=").append(shopingPay);
        sb.append(", cashPay=").append(cashPay);
        sb.append(", tWalletPay=").append(tWalletPay);
        sb.append(", tCashPay=").append(tCashPay);
        sb.append(", tCoupPay=").append(tCoupPay);
        sb.append(", tShopingPay=").append(tShopingPay);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isSync=").append(isSync);
        sb.append(", payType=").append(payType);
        sb.append(", tXfc=").append(tXfc);
        sb.append(", errorNum=").append(errorNum);
        sb.append(", isPaying=").append(isPaying);
        sb.append(", tradeFrom=").append(tradeFrom);
        sb.append(", isUpdate=").append(isUpdate);
        sb.append(", totalPoint=").append(totalPoint);
        sb.append(", pointPay=").append(pointPay);
        sb.append(", tPointPay=").append(tPointPay);
        sb.append(", tTotalPoint=").append(tTotalPoint);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}