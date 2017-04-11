package com.meiduimall.application.search.manage.domain;

public class MemberLogWithBLOBs extends MemberLog {
    private String mlogContent;

    private String mlogRemark;

    public String getMlogContent() {
        return mlogContent;
    }

    public void setMlogContent(String mlogContent) {
        this.mlogContent = mlogContent == null ? null : mlogContent.trim();
    }

    public String getMlogRemark() {
        return mlogRemark;
    }

    public void setMlogRemark(String mlogRemark) {
        this.mlogRemark = mlogRemark == null ? null : mlogRemark.trim();
    }
}