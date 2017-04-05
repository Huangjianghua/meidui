package com.meiduimall.application.search.domain;

import java.util.Date;

public class PlatformLoginLog {
    private String pllogId;

    private String pfId;

    private String pllogIp;

    private String pllogModule;

    private String pllogAction;

    private String pllogCreatedBy;

    private Date pllogCreatedDate;

    private String pllogStatus;
    
    private String pllogContent;

    private String pllogRemark;

    public String getPllogId() {
        return pllogId;
    }

    public void setPllogId(String pllogId) {
        this.pllogId = pllogId == null ? null : pllogId.trim();
    }

    public String getPfId() {
        return pfId;
    }

    public void setPfId(String pfId) {
        this.pfId = pfId == null ? null : pfId.trim();
    }

    public String getPllogIp() {
        return pllogIp;
    }

    public void setPllogIp(String pllogIp) {
        this.pllogIp = pllogIp == null ? null : pllogIp.trim();
    }

    public String getPllogModule() {
        return pllogModule;
    }

    public void setPllogModule(String pllogModule) {
        this.pllogModule = pllogModule == null ? null : pllogModule.trim();
    }

    public String getPllogAction() {
        return pllogAction;
    }

    public void setPllogAction(String pllogAction) {
        this.pllogAction = pllogAction == null ? null : pllogAction.trim();
    }

    public String getPllogCreatedBy() {
        return pllogCreatedBy;
    }

    public void setPllogCreatedBy(String pllogCreatedBy) {
        this.pllogCreatedBy = pllogCreatedBy == null ? null : pllogCreatedBy.trim();
    }

    public Date getPllogCreatedDate() {
        return pllogCreatedDate;
    }

    public void setPllogCreatedDate(Date pllogCreatedDate) {
        this.pllogCreatedDate = pllogCreatedDate;
    }

    public String getPllogStatus() {
        return pllogStatus;
    }

    public void setPllogStatus(String pllogStatus) {
        this.pllogStatus = pllogStatus == null ? null : pllogStatus.trim();
    }

	public String getPllogContent() {
		return pllogContent;
	}

	public void setPllogContent(String pllogContent) {
		this.pllogContent = pllogContent;
	}

	public String getPllogRemark() {
		return pllogRemark;
	}

	public void setPllogRemark(String pllogRemark) {
		this.pllogRemark = pllogRemark;
	}
    
}