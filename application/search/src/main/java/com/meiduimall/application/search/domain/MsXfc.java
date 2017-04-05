package com.meiduimall.application.search.domain;

import java.util.Date;

public class MsXfc {
    private String xfcId;

    private String xfcConaition;

    private Date xfcCreateDate;

    private String xfcCreateBy;

    private String xfcPrice;

    private String xfcRemark;

    private String xfcYear;

    public String getXfcId() {
        return xfcId;
    }

    public void setXfcId(String xfcId) {
        this.xfcId = xfcId == null ? null : xfcId.trim();
    }

    public String getXfcConaition() {
        return xfcConaition;
    }

    public void setXfcConaition(String xfcConaition) {
        this.xfcConaition = xfcConaition == null ? null : xfcConaition.trim();
    }

    public Date getXfcCreateDate() {
        return xfcCreateDate;
    }

    public void setXfcCreateDate(Date xfcCreateDate) {
        this.xfcCreateDate = xfcCreateDate;
    }

    public String getXfcCreateBy() {
        return xfcCreateBy;
    }

    public void setXfcCreateBy(String xfcCreateBy) {
        this.xfcCreateBy = xfcCreateBy == null ? null : xfcCreateBy.trim();
    }

    public String getXfcPrice() {
        return xfcPrice;
    }

    public void setXfcPrice(String xfcPrice) {
        this.xfcPrice = xfcPrice == null ? null : xfcPrice.trim();
    }

    public String getXfcRemark() {
        return xfcRemark;
    }

    public void setXfcRemark(String xfcRemark) {
        this.xfcRemark = xfcRemark == null ? null : xfcRemark.trim();
    }

    public String getXfcYear() {
        return xfcYear;
    }

    public void setXfcYear(String xfcYear) {
        this.xfcYear = xfcYear == null ? null : xfcYear.trim();
    }
}