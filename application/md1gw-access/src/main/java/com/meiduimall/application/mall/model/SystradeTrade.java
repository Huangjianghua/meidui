package com.meiduimall.application.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SystradeTrade implements Serializable {
    private BigInteger tid;

    private Integer shopId;

    private Integer userId;

    private Integer dlytmplId;

    private String status;

    private String payType;

    private BigDecimal payment;

    private BigDecimal orderXfc;

    private BigDecimal totalFee;

    private BigDecimal postFee;

    private BigDecimal payedFee;

    private Integer sellerRate;

    private String receiverName;

    private Integer createdTime;

    private Integer payTime;

    private Integer consignTime;

    private Integer endTime;

    private Integer modifiedTime;

    private Integer timeoutActionTime;

    private Integer sendTime;

    private String receiverState;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;

    private String receiverMobile;

    private String receiverPhone;

    private String title;

    private BigDecimal discountFee;

    private Integer consumePointFee;

    private String buyerMessage;

    private Boolean needInvoice;

    private String invoiceName;

    private String invoiceType;

    private String invoiceMain;

    private BigDecimal adjustFee;

    private String tradeFrom;

    private Integer itemnum;

    private String shopFlag;

    private String buyerArea;

    private Integer areaId;

    private String stepTradeStatus;

    private BigDecimal stepPaidFee;

    private String shippingType;

    private Long obtainPointFee;

    private Integer buyerRate;

    private Integer isPartConsign;

    private Integer realPointFee;

    private String ip;

    private Integer anony;

    private Integer isClearing;

    private Integer disabled;

    private String zitiAddr;

    private String zitiMemo;

    private String payOrderId;

    private Integer orderType;

    private String mpid;

    private Long chargenumber;

    private Long chargestatus;

    private Long submitchargetimes;

    private BigInteger platformId;

    private Integer isRecharge;

    private BigDecimal lastUnrefunds;

    private BigDecimal skuPrice;

    private String receiverStreet;

    private Integer meiduiPoint;

    private String isUpdate;

    private String cancelReason;

    private String shopMemo;

    private String tradeMemo;
    
    
    

    public SystradeTrade(BigInteger tid) {
		super();
		this.tid = tid;
	}

	public SystradeTrade() {
		super();
	}

	private static final long serialVersionUID = 1L;

    public BigInteger getTid() {
        return tid;
    }

    public void setTid(BigInteger tid) {
        this.tid = tid;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDlytmplId() {
        return dlytmplId;
    }

    public void setDlytmplId(Integer dlytmplId) {
        this.dlytmplId = dlytmplId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getOrderXfc() {
        return orderXfc;
    }

    public void setOrderXfc(BigDecimal orderXfc) {
        this.orderXfc = orderXfc;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getPostFee() {
        return postFee;
    }

    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    public BigDecimal getPayedFee() {
        return payedFee;
    }

    public void setPayedFee(BigDecimal payedFee) {
        this.payedFee = payedFee;
    }

   
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getPayTime() {
        return payTime;
    }

    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    public Integer getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Integer consignTime) {
        this.consignTime = consignTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Integer modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getTimeoutActionTime() {
        return timeoutActionTime;
    }

    public void setTimeoutActionTime(Integer timeoutActionTime) {
        this.timeoutActionTime = timeoutActionTime;
    }

    public Integer getSendTime() {
        return sendTime;
    }

    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState == null ? null : receiverState.trim();
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict == null ? null : receiverDistrict.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip == null ? null : receiverZip.trim();
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(BigDecimal discountFee) {
        this.discountFee = discountFee;
    }

    public Integer getConsumePointFee() {
        return consumePointFee;
    }

    public void setConsumePointFee(Integer consumePointFee) {
        this.consumePointFee = consumePointFee;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }

    public Boolean getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(Boolean needInvoice) {
        this.needInvoice = needInvoice;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName == null ? null : invoiceName.trim();
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getInvoiceMain() {
        return invoiceMain;
    }

    public void setInvoiceMain(String invoiceMain) {
        this.invoiceMain = invoiceMain == null ? null : invoiceMain.trim();
    }

    public BigDecimal getAdjustFee() {
        return adjustFee;
    }

    public void setAdjustFee(BigDecimal adjustFee) {
        this.adjustFee = adjustFee;
    }

    public String getTradeFrom() {
        return tradeFrom;
    }

    public void setTradeFrom(String tradeFrom) {
        this.tradeFrom = tradeFrom == null ? null : tradeFrom.trim();
    }

    public Integer getItemnum() {
        return itemnum;
    }

    public void setItemnum(Integer itemnum) {
        this.itemnum = itemnum;
    }

    public String getShopFlag() {
        return shopFlag;
    }

    public void setShopFlag(String shopFlag) {
        this.shopFlag = shopFlag == null ? null : shopFlag.trim();
    }

    public String getBuyerArea() {
        return buyerArea;
    }

    public void setBuyerArea(String buyerArea) {
        this.buyerArea = buyerArea == null ? null : buyerArea.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getStepTradeStatus() {
        return stepTradeStatus;
    }

    public void setStepTradeStatus(String stepTradeStatus) {
        this.stepTradeStatus = stepTradeStatus == null ? null : stepTradeStatus.trim();
    }

    public BigDecimal getStepPaidFee() {
        return stepPaidFee;
    }

    public void setStepPaidFee(BigDecimal stepPaidFee) {
        this.stepPaidFee = stepPaidFee;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType == null ? null : shippingType.trim();
    }

    public Long getObtainPointFee() {
        return obtainPointFee;
    }

    public void setObtainPointFee(Long obtainPointFee) {
        this.obtainPointFee = obtainPointFee;
    }

    

    public Integer getRealPointFee() {
        return realPointFee;
    }

    public void setRealPointFee(Integer realPointFee) {
        this.realPointFee = realPointFee;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

     

    public String getZitiAddr() {
        return zitiAddr;
    }

    public void setZitiAddr(String zitiAddr) {
        this.zitiAddr = zitiAddr == null ? null : zitiAddr.trim();
    }

    public String getZitiMemo() {
        return zitiMemo;
    }

    public void setZitiMemo(String zitiMemo) {
        this.zitiMemo = zitiMemo == null ? null : zitiMemo.trim();
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId == null ? null : payOrderId.trim();
    }

    

    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid == null ? null : mpid.trim();
    }

    public Long getChargenumber() {
        return chargenumber;
    }

    public void setChargenumber(Long chargenumber) {
        this.chargenumber = chargenumber;
    }

    public Long getChargestatus() {
        return chargestatus;
    }

    public void setChargestatus(Long chargestatus) {
        this.chargestatus = chargestatus;
    }

    public Long getSubmitchargetimes() {
        return submitchargetimes;
    }

    public void setSubmitchargetimes(Long submitchargetimes) {
        this.submitchargetimes = submitchargetimes;
    }

    public BigInteger getPlatformId() {
        return platformId;
    }

    public void setPlatformId(BigInteger platformId) {
        this.platformId = platformId;
    }

    
    
    public Integer getSellerRate() {
		return sellerRate;
	}

	public void setSellerRate(Integer sellerRate) {
		this.sellerRate = sellerRate;
	}

	public Integer getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(Integer buyerRate) {
		this.buyerRate = buyerRate;
	}

	public Integer getIsPartConsign() {
		return isPartConsign;
	}

	public void setIsPartConsign(Integer isPartConsign) {
		this.isPartConsign = isPartConsign;
	}

	public Integer getAnony() {
		return anony;
	}

	public void setAnony(Integer anony) {
		this.anony = anony;
	}

	public Integer getIsClearing() {
		return isClearing;
	}

	public void setIsClearing(Integer isClearing) {
		this.isClearing = isClearing;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getIsRecharge() {
		return isRecharge;
	}

	public void setIsRecharge(Integer isRecharge) {
		this.isRecharge = isRecharge;
	}

	public BigDecimal getLastUnrefunds() {
        return lastUnrefunds;
    }

    public void setLastUnrefunds(BigDecimal lastUnrefunds) {
        this.lastUnrefunds = lastUnrefunds;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getReceiverStreet() {
        return receiverStreet;
    }

    public void setReceiverStreet(String receiverStreet) {
        this.receiverStreet = receiverStreet == null ? null : receiverStreet.trim();
    }

    public Integer getMeiduiPoint() {
        return meiduiPoint;
    }

    public void setMeiduiPoint(Integer meiduiPoint) {
        this.meiduiPoint = meiduiPoint;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate == null ? null : isUpdate.trim();
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public String getShopMemo() {
        return shopMemo;
    }

    public void setShopMemo(String shopMemo) {
        this.shopMemo = shopMemo == null ? null : shopMemo.trim();
    }

    public String getTradeMemo() {
        return tradeMemo;
    }

    public void setTradeMemo(String tradeMemo) {
        this.tradeMemo = tradeMemo == null ? null : tradeMemo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tid=").append(tid);
        sb.append(", shopId=").append(shopId);
        sb.append(", userId=").append(userId);
        sb.append(", dlytmplId=").append(dlytmplId);
        sb.append(", status=").append(status);
        sb.append(", payType=").append(payType);
        sb.append(", payment=").append(payment);
        sb.append(", orderXfc=").append(orderXfc);
        sb.append(", totalFee=").append(totalFee);
        sb.append(", postFee=").append(postFee);
        sb.append(", payedFee=").append(payedFee);
        sb.append(", sellerRate=").append(sellerRate);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", consignTime=").append(consignTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", timeoutActionTime=").append(timeoutActionTime);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", receiverState=").append(receiverState);
        sb.append(", receiverCity=").append(receiverCity);
        sb.append(", receiverDistrict=").append(receiverDistrict);
        sb.append(", receiverAddress=").append(receiverAddress);
        sb.append(", receiverZip=").append(receiverZip);
        sb.append(", receiverMobile=").append(receiverMobile);
        sb.append(", receiverPhone=").append(receiverPhone);
        sb.append(", title=").append(title);
        sb.append(", discountFee=").append(discountFee);
        sb.append(", consumePointFee=").append(consumePointFee);
        sb.append(", buyerMessage=").append(buyerMessage);
        sb.append(", needInvoice=").append(needInvoice);
        sb.append(", invoiceName=").append(invoiceName);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", invoiceMain=").append(invoiceMain);
        sb.append(", adjustFee=").append(adjustFee);
        sb.append(", tradeFrom=").append(tradeFrom);
        sb.append(", itemnum=").append(itemnum);
        sb.append(", shopFlag=").append(shopFlag);
        sb.append(", buyerArea=").append(buyerArea);
        sb.append(", areaId=").append(areaId);
        sb.append(", stepTradeStatus=").append(stepTradeStatus);
        sb.append(", stepPaidFee=").append(stepPaidFee);
        sb.append(", shippingType=").append(shippingType);
        sb.append(", obtainPointFee=").append(obtainPointFee);
        sb.append(", buyerRate=").append(buyerRate);
        sb.append(", isPartConsign=").append(isPartConsign);
        sb.append(", realPointFee=").append(realPointFee);
        sb.append(", ip=").append(ip);
        sb.append(", anony=").append(anony);
        sb.append(", isClearing=").append(isClearing);
        sb.append(", disabled=").append(disabled);
        sb.append(", zitiAddr=").append(zitiAddr);
        sb.append(", zitiMemo=").append(zitiMemo);
        sb.append(", payOrderId=").append(payOrderId);
        sb.append(", orderType=").append(orderType);
        sb.append(", mpid=").append(mpid);
        sb.append(", chargenumber=").append(chargenumber);
        sb.append(", chargestatus=").append(chargestatus);
        sb.append(", submitchargetimes=").append(submitchargetimes);
        sb.append(", platformId=").append(platformId);
        sb.append(", isRecharge=").append(isRecharge);
        sb.append(", lastUnrefunds=").append(lastUnrefunds);
        sb.append(", skuPrice=").append(skuPrice);
        sb.append(", receiverStreet=").append(receiverStreet);
        sb.append(", meiduiPoint=").append(meiduiPoint);
        sb.append(", isUpdate=").append(isUpdate);
        sb.append(", cancelReason=").append(cancelReason);
        sb.append(", shopMemo=").append(shopMemo);
        sb.append(", tradeMemo=").append(tradeMemo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}