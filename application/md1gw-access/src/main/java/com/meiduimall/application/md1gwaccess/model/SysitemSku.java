package com.meiduimall.application.md1gwaccess.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysitemSku implements Serializable {
    private Integer skuId;

    private Integer itemId;

    private String title;

    private String bn;

    private BigDecimal price;

    private BigDecimal costPrice;

    private BigDecimal mktPrice;

    private String barcode;

    private BigDecimal weight;

    private Integer createdTime;

    private Integer modifiedTime;

    private String status;

    private String outerId;

    private BigDecimal directPrices;

    private Boolean isdirect;

    private Integer point;

    private String properties;

    private String specInfo;

    private String specDesc;

    private static final long serialVersionUID = 1L;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBn() {
        return bn;
    }

    public void setBn(String bn) {
        this.bn = bn == null ? null : bn.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMktPrice() {
        return mktPrice;
    }

    public void setMktPrice(BigDecimal mktPrice) {
        this.mktPrice = mktPrice;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
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

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId == null ? null : outerId.trim();
    }

    public BigDecimal getDirectPrices() {
        return directPrices;
    }

    public void setDirectPrices(BigDecimal directPrices) {
        this.directPrices = directPrices;
    }

    public Boolean getIsdirect() {
        return isdirect;
    }

    public void setIsdirect(Boolean isdirect) {
        this.isdirect = isdirect;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    public String getSpecInfo() {
        return specInfo;
    }

    public void setSpecInfo(String specInfo) {
        this.specInfo = specInfo == null ? null : specInfo.trim();
    }

    public String getSpecDesc() {
        return specDesc;
    }

    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc == null ? null : specDesc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", skuId=").append(skuId);
        sb.append(", itemId=").append(itemId);
        sb.append(", title=").append(title);
        sb.append(", bn=").append(bn);
        sb.append(", price=").append(price);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", mktPrice=").append(mktPrice);
        sb.append(", barcode=").append(barcode);
        sb.append(", weight=").append(weight);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", status=").append(status);
        sb.append(", outerId=").append(outerId);
        sb.append(", directPrices=").append(directPrices);
        sb.append(", isdirect=").append(isdirect);
        sb.append(", point=").append(point);
        sb.append(", properties=").append(properties);
        sb.append(", specInfo=").append(specInfo);
        sb.append(", specDesc=").append(specDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}