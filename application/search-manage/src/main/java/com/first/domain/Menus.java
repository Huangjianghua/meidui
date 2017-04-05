package com.first.domain;

import java.util.Date;

public class Menus {
    private String menId;

    private String dictId;

    private Integer menNo;

    private String menUrl;

    private String menRemark;

    private String menCreateBy;

    private Date menCreateDate;

    private String menUpdatedBy;

    private Date menUpdatedDate;

    private String menImageUrl;

    private String menStyleName;

    private Integer menIsQuick;

    private String menIsOpen;

    public String getMenId() {
        return menId;
    }

    public void setMenId(String menId) {
        this.menId = menId == null ? null : menId.trim();
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    public Integer getMenNo() {
        return menNo;
    }

    public void setMenNo(Integer menNo) {
        this.menNo = menNo;
    }

    public String getMenUrl() {
        return menUrl;
    }

    public void setMenUrl(String menUrl) {
        this.menUrl = menUrl == null ? null : menUrl.trim();
    }

    public String getMenRemark() {
        return menRemark;
    }

    public void setMenRemark(String menRemark) {
        this.menRemark = menRemark == null ? null : menRemark.trim();
    }

    public String getMenCreateBy() {
        return menCreateBy;
    }

    public void setMenCreateBy(String menCreateBy) {
        this.menCreateBy = menCreateBy == null ? null : menCreateBy.trim();
    }

    public Date getMenCreateDate() {
        return menCreateDate;
    }

    public void setMenCreateDate(Date menCreateDate) {
        this.menCreateDate = menCreateDate;
    }

    public String getMenUpdatedBy() {
        return menUpdatedBy;
    }

    public void setMenUpdatedBy(String menUpdatedBy) {
        this.menUpdatedBy = menUpdatedBy == null ? null : menUpdatedBy.trim();
    }

    public Date getMenUpdatedDate() {
        return menUpdatedDate;
    }

    public void setMenUpdatedDate(Date menUpdatedDate) {
        this.menUpdatedDate = menUpdatedDate;
    }

    public String getMenImageUrl() {
        return menImageUrl;
    }

    public void setMenImageUrl(String menImageUrl) {
        this.menImageUrl = menImageUrl == null ? null : menImageUrl.trim();
    }

    public String getMenStyleName() {
        return menStyleName;
    }

    public void setMenStyleName(String menStyleName) {
        this.menStyleName = menStyleName == null ? null : menStyleName.trim();
    }

    public Integer getMenIsQuick() {
        return menIsQuick;
    }

    public void setMenIsQuick(Integer menIsQuick) {
        this.menIsQuick = menIsQuick;
    }

    public String getMenIsOpen() {
        return menIsOpen;
    }

    public void setMenIsOpen(String menIsOpen) {
        this.menIsOpen = menIsOpen == null ? null : menIsOpen.trim();
    }
}