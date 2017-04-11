package com.meiduimall.application.search.manage.domain;

public class PlatformLogWithBLOBs extends PlatformLog {
    private String plogContent;

    private String plogRemark;

    public String getPlogContent() {
        return plogContent;
    }

    public void setPlogContent(String plogContent) {
        this.plogContent = plogContent == null ? null : plogContent.trim();
    }

    public String getPlogRemark() {
        return plogRemark;
    }

    public void setPlogRemark(String plogRemark) {
        this.plogRemark = plogRemark == null ? null : plogRemark.trim();
    }
}