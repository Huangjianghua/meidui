package com.meiduimall.service.catalog.entity;

import java.math.BigDecimal;

public class SystradeTrade {
	private Long tid;

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

	private Integer needInvoice;

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

	private Long platformId;

	private Integer isRecharge;

	private BigDecimal lastUnrefunds;

	private String receiverStreet;

	private Integer meiduiPoint;

	private String isUpdate;

	private Integer isDelete;

	private String personalNo;

	private String personalName;

	private String regionNo;

	private String refCompanyName;

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
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

	public Integer getSellerRate() {
		return sellerRate;
	}

	public void setSellerRate(Integer sellerRate) {
		this.sellerRate = sellerRate;
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

	public Integer getNeedInvoice() {
		return needInvoice;
	}

	public void setNeedInvoice(Integer needInvoice) {
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

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getPersonalNo() {
		return personalNo;
	}

	public void setPersonalNo(String personalNo) {
		this.personalNo = personalNo == null ? null : personalNo.trim();
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName == null ? null : personalName.trim();
	}

	public String getRegionNo() {
		return regionNo;
	}

	public void setRegionNo(String regionNo) {
		this.regionNo = regionNo == null ? null : regionNo.trim();
	}

	public String getRefCompanyName() {
		return refCompanyName;
	}

	public void setRefCompanyName(String refCompanyName) {
		this.refCompanyName = refCompanyName == null ? null : refCompanyName.trim();
	}
}