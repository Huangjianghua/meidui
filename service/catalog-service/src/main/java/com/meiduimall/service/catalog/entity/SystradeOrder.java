package com.meiduimall.service.catalog.entity;

import java.math.BigDecimal;

public class SystradeOrder {
	
    private String oid;

    private String tid;

    private Integer shopId;

    private Integer userId;

    private Integer itemId;

    private Integer skuId;

    private String bn;

    private String title;

    private BigDecimal price;

    private Integer num;

    private Double sendnum;

    private String skuPropertiesName;

    private String refundId;

    private Integer isOversold;

    private Integer payTime;

    private Integer endTime;

    private Integer consignTime;

    private String shippingType;

    private String bindOid;

    private String logisticsCompany;

    private String invoiceNo;

    private BigDecimal divideOrderFee;

    private BigDecimal partMjzDiscount;

    private BigDecimal totalFee;

    private BigDecimal payment;

    private BigDecimal discountFee;

    private BigDecimal adjustFee;

    private Integer modifiedTime;

    private String status;

    private String aftersalesStatus;

    private String complaintsStatus;

    private BigDecimal refundFee;

    private Integer buyerRate;

    private Integer anony;

    private Integer sellerRate;

    private BigDecimal catServiceRate;

    private String orderFrom;

    private String picPath;

    private Integer timeoutActionTime;

    private String outerIid;

    private String outerSkuId;

    private Integer subStock;

    private Integer orderType;

    private String mpid;

    private Long chargenumber;

    private Long chargemobile;

    private String productid;

    private Long platformId;

    private BigDecimal operateCost;

    private BigDecimal skuPrice;

    private BigDecimal operateTotalCost;

    private BigDecimal skuTotalPrice;

    private Integer isUpdate;

    private Integer oMeiduiPoint;

    private String tkstatus;

    private Integer isDelete;

    private String specNatureInfo;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getBn() {
        return bn;
    }

    public void setBn(String bn) {
        this.bn = bn == null ? null : bn.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getSendnum() {
        return sendnum;
    }

    public void setSendnum(Double sendnum) {
        this.sendnum = sendnum;
    }

    public String getSkuPropertiesName() {
        return skuPropertiesName;
    }

    public void setSkuPropertiesName(String skuPropertiesName) {
        this.skuPropertiesName = skuPropertiesName == null ? null : skuPropertiesName.trim();
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId == null ? null : refundId.trim();
    }

    public Integer getIsOversold() {
        return isOversold;
    }

    public void setIsOversold(Integer isOversold) {
        this.isOversold = isOversold;
    }

    public Integer getPayTime() {
        return payTime;
    }

    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Integer consignTime) {
        this.consignTime = consignTime;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType == null ? null : shippingType.trim();
    }

    public String getBindOid() {
        return bindOid;
    }

    public void setBindOid(String bindOid) {
        this.bindOid = bindOid == null ? null : bindOid.trim();
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany == null ? null : logisticsCompany.trim();
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    public BigDecimal getDivideOrderFee() {
        return divideOrderFee;
    }

    public void setDivideOrderFee(BigDecimal divideOrderFee) {
        this.divideOrderFee = divideOrderFee;
    }

    public BigDecimal getPartMjzDiscount() {
        return partMjzDiscount;
    }

    public void setPartMjzDiscount(BigDecimal partMjzDiscount) {
        this.partMjzDiscount = partMjzDiscount;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(BigDecimal discountFee) {
        this.discountFee = discountFee;
    }

    public BigDecimal getAdjustFee() {
        return adjustFee;
    }

    public void setAdjustFee(BigDecimal adjustFee) {
        this.adjustFee = adjustFee;
    }

    public Integer getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Integer modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAftersalesStatus() {
        return aftersalesStatus;
    }

    public void setAftersalesStatus(String aftersalesStatus) {
        this.aftersalesStatus = aftersalesStatus == null ? null : aftersalesStatus.trim();
    }

    public String getComplaintsStatus() {
        return complaintsStatus;
    }

    public void setComplaintsStatus(String complaintsStatus) {
        this.complaintsStatus = complaintsStatus == null ? null : complaintsStatus.trim();
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(Integer buyerRate) {
        this.buyerRate = buyerRate;
    }

    public Integer getAnony() {
        return anony;
    }

    public void setAnony(Integer anony) {
        this.anony = anony;
    }

    public Integer getSellerRate() {
        return sellerRate;
    }

    public void setSellerRate(Integer sellerRate) {
        this.sellerRate = sellerRate;
    }

    public BigDecimal getCatServiceRate() {
        return catServiceRate;
    }

    public void setCatServiceRate(BigDecimal catServiceRate) {
        this.catServiceRate = catServiceRate;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom == null ? null : orderFrom.trim();
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    public Integer getTimeoutActionTime() {
        return timeoutActionTime;
    }

    public void setTimeoutActionTime(Integer timeoutActionTime) {
        this.timeoutActionTime = timeoutActionTime;
    }

    public String getOuterIid() {
        return outerIid;
    }

    public void setOuterIid(String outerIid) {
        this.outerIid = outerIid == null ? null : outerIid.trim();
    }

    public String getOuterSkuId() {
        return outerSkuId;
    }

    public void setOuterSkuId(String outerSkuId) {
        this.outerSkuId = outerSkuId == null ? null : outerSkuId.trim();
    }

    public Integer getSubStock() {
        return subStock;
    }

    public void setSubStock(Integer subStock) {
        this.subStock = subStock;
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

    public Long getChargemobile() {
        return chargemobile;
    }

    public void setChargemobile(Long chargemobile) {
        this.chargemobile = chargemobile;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid == null ? null : productid.trim();
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public BigDecimal getOperateCost() {
        return operateCost;
    }

    public void setOperateCost(BigDecimal operateCost) {
        this.operateCost = operateCost;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public BigDecimal getOperateTotalCost() {
        return operateTotalCost;
    }

    public void setOperateTotalCost(BigDecimal operateTotalCost) {
        this.operateTotalCost = operateTotalCost;
    }

    public BigDecimal getSkuTotalPrice() {
        return skuTotalPrice;
    }

    public void setSkuTotalPrice(BigDecimal skuTotalPrice) {
        this.skuTotalPrice = skuTotalPrice;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Integer getoMeiduiPoint() {
        return oMeiduiPoint;
    }

    public void setoMeiduiPoint(Integer oMeiduiPoint) {
        this.oMeiduiPoint = oMeiduiPoint;
    }

    public String getTkstatus() {
        return tkstatus;
    }

    public void setTkstatus(String tkstatus) {
        this.tkstatus = tkstatus == null ? null : tkstatus.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getSpecNatureInfo() {
        return specNatureInfo;
    }

    public void setSpecNatureInfo(String specNatureInfo) {
        this.specNatureInfo = specNatureInfo == null ? null : specNatureInfo.trim();
    }
}