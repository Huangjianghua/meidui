package com.meiduimall.application.search.manage.domain;

public class MembersKey {
    private String memId;

    private String memPreorderRight;

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId == null ? null : memId.trim();
    }

    public String getMemPreorderRight() {
        return memPreorderRight;
    }

    public void setMemPreorderRight(String memPreorderRight) {
        this.memPreorderRight = memPreorderRight == null ? null : memPreorderRight.trim();
    }
}