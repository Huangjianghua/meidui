package com.meiduimall.application.mall.pay.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SystradeOrder implements Serializable {
    private Long oid;

    private BigInteger tid;

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

    private Boolean isOversold;

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

    private BigDecimal oXfc;


    private BigDecimal oMoney;

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

    private BigInteger platformId;

    private BigDecimal operateCost;

    private BigDecimal skuPrice;

    private BigDecimal operateTotalCost;

    private BigDecimal skuTotalPrice;

    private BigDecimal costPrice;

    private Boolean isUpdate;

    private Integer oMeiduiPoint;

    private String specNatureInfo;
    
    
    

    public SystradeOrder(BigInteger tid) {
		super();
		this.tid = tid;
	}

	public SystradeOrder() {
		super();
	}

	private static final long serialVersionUID = 1L;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

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

    public Boolean getIsOversold() {
        return isOversold;
    }

    public void setIsOversold(Boolean isOversold) {
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

    public BigDecimal getoXfc() {
        return oXfc;
    }

    public void setoXfc(BigDecimal oXfc) {
        this.oXfc = oXfc;
    }

     

    public BigDecimal getoMoney() {
        return oMoney;
    }

    public void setoMoney(BigDecimal oMoney) {
        this.oMoney = oMoney;
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

    public BigInteger getPlatformId() {
        return platformId;
    }

    public void setPlatformId(BigInteger platformId) {
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

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Integer getoMeiduiPoint() {
        return oMeiduiPoint;
    }

    public void setoMeiduiPoint(Integer oMeiduiPoint) {
        this.oMeiduiPoint = oMeiduiPoint;
    }

    public String getSpecNatureInfo() {
        return specNatureInfo;
    }

    public void setSpecNatureInfo(String specNatureInfo) {
        this.specNatureInfo = specNatureInfo == null ? null : specNatureInfo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oid=").append(oid);
        sb.append(", tid=").append(tid);
        sb.append(", shopId=").append(shopId);
        sb.append(", userId=").append(userId);
        sb.append(", itemId=").append(itemId);
        sb.append(", skuId=").append(skuId);
        sb.append(", bn=").append(bn);
        sb.append(", title=").append(title);
        sb.append(", price=").append(price);
        sb.append(", num=").append(num);
        sb.append(", sendnum=").append(sendnum);
        sb.append(", skuPropertiesName=").append(skuPropertiesName);
        sb.append(", refundId=").append(refundId);
        sb.append(", isOversold=").append(isOversold);
        sb.append(", payTime=").append(payTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", consignTime=").append(consignTime);
        sb.append(", shippingType=").append(shippingType);
        sb.append(", bindOid=").append(bindOid);
        sb.append(", logisticsCompany=").append(logisticsCompany);
        sb.append(", invoiceNo=").append(invoiceNo);
        sb.append(", divideOrderFee=").append(divideOrderFee);
        sb.append(", partMjzDiscount=").append(partMjzDiscount);
        sb.append(", totalFee=").append(totalFee);
        sb.append(", payment=").append(payment);
        sb.append(", oXfc=").append(oXfc);
        sb.append(", oMoney=").append(oMoney);
        sb.append(", discountFee=").append(discountFee);
        sb.append(", adjustFee=").append(adjustFee);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", status=").append(status);
        sb.append(", aftersalesStatus=").append(aftersalesStatus);
        sb.append(", complaintsStatus=").append(complaintsStatus);
        sb.append(", refundFee=").append(refundFee);
        sb.append(", buyerRate=").append(buyerRate);
        sb.append(", anony=").append(anony);
        sb.append(", sellerRate=").append(sellerRate);
        sb.append(", catServiceRate=").append(catServiceRate);
        sb.append(", orderFrom=").append(orderFrom);
        sb.append(", picPath=").append(picPath);
        sb.append(", timeoutActionTime=").append(timeoutActionTime);
        sb.append(", outerIid=").append(outerIid);
        sb.append(", outerSkuId=").append(outerSkuId);
        sb.append(", subStock=").append(subStock);
        sb.append(", orderType=").append(orderType);
        sb.append(", mpid=").append(mpid);
        sb.append(", chargenumber=").append(chargenumber);
        sb.append(", chargemobile=").append(chargemobile);
        sb.append(", productid=").append(productid);
        sb.append(", platformId=").append(platformId);
        sb.append(", operateCost=").append(operateCost);
        sb.append(", skuPrice=").append(skuPrice);
        sb.append(", operateTotalCost=").append(operateTotalCost);
        sb.append(", skuTotalPrice=").append(skuTotalPrice);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", isUpdate=").append(isUpdate);
        sb.append(", oMeiduiPoint=").append(oMeiduiPoint);
        sb.append(", specNatureInfo=").append(specNatureInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}