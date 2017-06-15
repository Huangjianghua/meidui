package com.meiduimall.service.catalog.entity;

import java.math.BigDecimal;

public class SyspromotionActivityItem {
    private Integer id;

    private Integer activityId;

    private Integer shopId;

    private Integer itemId;

    private Integer catId;

    private String title;

    private String itemDefaultImage;

    private BigDecimal price;

    private BigDecimal activityPrice;

    private Integer salesCount;

    private String verifyStatus;

    private Integer startTime;

    private Integer endTime;

    private String activityTag;

    private BigDecimal costPrice;

    private BigDecimal activityCostPrice;

    private Integer activityPoint;

    private Integer virtualSalesCount;

    private Integer spareItemId;

    private String activitySku;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getItemDefaultImage() {
        return itemDefaultImage;
    }

    public void setItemDefaultImage(String itemDefaultImage) {
        this.itemDefaultImage = itemDefaultImage == null ? null : itemDefaultImage.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(BigDecimal activityPrice) {
        this.activityPrice = activityPrice;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus == null ? null : verifyStatus.trim();
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getActivityTag() {
        return activityTag;
    }

    public void setActivityTag(String activityTag) {
        this.activityTag = activityTag == null ? null : activityTag.trim();
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getActivityCostPrice() {
        return activityCostPrice;
    }

    public void setActivityCostPrice(BigDecimal activityCostPrice) {
        this.activityCostPrice = activityCostPrice;
    }

    public Integer getActivityPoint() {
        return activityPoint;
    }

    public void setActivityPoint(Integer activityPoint) {
        this.activityPoint = activityPoint;
    }

    public Integer getVirtualSalesCount() {
        return virtualSalesCount;
    }

    public void setVirtualSalesCount(Integer virtualSalesCount) {
        this.virtualSalesCount = virtualSalesCount;
    }

    public Integer getSpareItemId() {
        return spareItemId;
    }

    public void setSpareItemId(Integer spareItemId) {
        this.spareItemId = spareItemId;
    }

    public String getActivitySku() {
        return activitySku;
    }

    public void setActivitySku(String activitySku) {
        this.activitySku = activitySku == null ? null : activitySku.trim();
    }
}