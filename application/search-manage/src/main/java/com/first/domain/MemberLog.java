package com.first.domain;

import java.util.Date;

public class MemberLog {
    private String mlogId;

    private String memId;

    private String mlogModule;

    private String mlogAction;

    private Date mlogDate;

    private String mlogIp;

    private String mlogCreatedBy;

    private Date mlogCreatedDate;

    public String getMlogId() {
        return mlogId;
    }

    public void setMlogId(String mlogId) {
        this.mlogId = mlogId == null ? null : mlogId.trim();
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId == null ? null : memId.trim();
    }

    public String getMlogModule() {
        return mlogModule;
    }

    public void setMlogModule(String mlogModule) {
        this.mlogModule = mlogModule == null ? null : mlogModule.trim();
    }

    public String getMlogAction() {
        return mlogAction;
    }

    public void setMlogAction(String mlogAction) {
        this.mlogAction = mlogAction == null ? null : mlogAction.trim();
    }

    public Date getMlogDate() {
        return mlogDate;
    }

    public void setMlogDate(Date mlogDate) {
        this.mlogDate = mlogDate;
    }

    public String getMlogIp() {
        return mlogIp;
    }

    public void setMlogIp(String mlogIp) {
        this.mlogIp = mlogIp == null ? null : mlogIp.trim();
    }

    public String getMlogCreatedBy() {
        return mlogCreatedBy;
    }

    public void setMlogCreatedBy(String mlogCreatedBy) {
        this.mlogCreatedBy = mlogCreatedBy == null ? null : mlogCreatedBy.trim();
    }

    public Date getMlogCreatedDate() {
        return mlogCreatedDate;
    }

    public void setMlogCreatedDate(Date mlogCreatedDate) {
        this.mlogCreatedDate = mlogCreatedDate;
    }
}