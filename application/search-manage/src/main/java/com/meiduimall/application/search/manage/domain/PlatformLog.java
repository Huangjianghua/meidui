package com.meiduimall.application.search.manage.domain;

import java.util.Date;

public class PlatformLog {
    private String plogId;

    private String pfId;

    private String plogIp;

    private String plogModule;

    private String plogAction;

    private String plogCreatedBy;

    private Date plogCreatedDate;

    public String getPlogId() {
        return plogId;
    }

    public void setPlogId(String plogId) {
        this.plogId = plogId == null ? null : plogId.trim();
    }

    public String getPfId() {
        return pfId;
    }

    public void setPfId(String pfId) {
        this.pfId = pfId == null ? null : pfId.trim();
    }

    public String getPlogIp() {
        return plogIp;
    }

    public void setPlogIp(String plogIp) {
        this.plogIp = plogIp == null ? null : plogIp.trim();
    }

    public String getPlogModule() {
        return plogModule;
    }

    public void setPlogModule(String plogModule) {
        this.plogModule = plogModule == null ? null : plogModule.trim();
    }

    public String getPlogAction() {
        return plogAction;
    }

    public void setPlogAction(String plogAction) {
        this.plogAction = plogAction == null ? null : plogAction.trim();
    }

    public String getPlogCreatedBy() {
        return plogCreatedBy;
    }

    public void setPlogCreatedBy(String plogCreatedBy) {
        this.plogCreatedBy = plogCreatedBy == null ? null : plogCreatedBy.trim();
    }

    public Date getPlogCreatedDate() {
        return plogCreatedDate;
    }

    public void setPlogCreatedDate(Date plogCreatedDate) {
        this.plogCreatedDate = plogCreatedDate;
    }
}