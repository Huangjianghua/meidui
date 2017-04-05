package com.first.domain;

import java.util.Date;

public class MemberPreorderHistory {
    private String mphId;

    private String memId;

    private String mphTotalquantity;

    private String mphQuantity;

    private String mphStatus;

    private String mphCreatedBy;

    private Date mphCreatedDate;

    private String mphUpdatedBy;

    private Date mphUpdatedDate;

    private String bcCurrentLevel;

    private String bcNextLevel;

    private String mphNo;

    private String oldMphId;

    private String mphRemark;

    public String getMphId() {
        return mphId;
    }

    public void setMphId(String mphId) {
        this.mphId = mphId == null ? null : mphId.trim();
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId == null ? null : memId.trim();
    }

    public String getMphTotalquantity() {
        return mphTotalquantity;
    }

    public void setMphTotalquantity(String mphTotalquantity) {
        this.mphTotalquantity = mphTotalquantity == null ? null : mphTotalquantity.trim();
    }

    public String getMphQuantity() {
        return mphQuantity;
    }

    public void setMphQuantity(String mphQuantity) {
        this.mphQuantity = mphQuantity == null ? null : mphQuantity.trim();
    }

    public String getMphStatus() {
        return mphStatus;
    }

    public void setMphStatus(String mphStatus) {
        this.mphStatus = mphStatus == null ? null : mphStatus.trim();
    }

    public String getMphCreatedBy() {
        return mphCreatedBy;
    }

    public void setMphCreatedBy(String mphCreatedBy) {
        this.mphCreatedBy = mphCreatedBy == null ? null : mphCreatedBy.trim();
    }

    public Date getMphCreatedDate() {
        return mphCreatedDate;
    }

    public void setMphCreatedDate(Date mphCreatedDate) {
        this.mphCreatedDate = mphCreatedDate;
    }

    public String getMphUpdatedBy() {
        return mphUpdatedBy;
    }

    public void setMphUpdatedBy(String mphUpdatedBy) {
        this.mphUpdatedBy = mphUpdatedBy == null ? null : mphUpdatedBy.trim();
    }

    public Date getMphUpdatedDate() {
        return mphUpdatedDate;
    }

    public void setMphUpdatedDate(Date mphUpdatedDate) {
        this.mphUpdatedDate = mphUpdatedDate;
    }

    public String getBcCurrentLevel() {
        return bcCurrentLevel;
    }

    public void setBcCurrentLevel(String bcCurrentLevel) {
        this.bcCurrentLevel = bcCurrentLevel == null ? null : bcCurrentLevel.trim();
    }

    public String getBcNextLevel() {
        return bcNextLevel;
    }

    public void setBcNextLevel(String bcNextLevel) {
        this.bcNextLevel = bcNextLevel == null ? null : bcNextLevel.trim();
    }

    public String getMphNo() {
        return mphNo;
    }

    public void setMphNo(String mphNo) {
        this.mphNo = mphNo == null ? null : mphNo.trim();
    }

    public String getOldMphId() {
        return oldMphId;
    }

    public void setOldMphId(String oldMphId) {
        this.oldMphId = oldMphId == null ? null : oldMphId.trim();
    }

    public String getMphRemark() {
        return mphRemark;
    }

    public void setMphRemark(String mphRemark) {
        this.mphRemark = mphRemark == null ? null : mphRemark.trim();
    }
}