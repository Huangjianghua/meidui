package com.meiduimall.application.search.domain;

public class MessagesWithBLOBs extends Messages {
    private String mesContent;

    private String mesRemark;

    public String getMesContent() {
        return mesContent;
    }

    public void setMesContent(String mesContent) {
        this.mesContent = mesContent == null ? null : mesContent.trim();
    }

    public String getMesRemark() {
        return mesRemark;
    }

    public void setMesRemark(String mesRemark) {
        this.mesRemark = mesRemark == null ? null : mesRemark.trim();
    }
}