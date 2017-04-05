package com.first.domain;

import java.util.Date;

public class Rights {
    private String rightId;

    private String dictId;

    private String rightRemark;

    private String rightCreatedBy;

    private Date rightCreatedDate;

    private String rightUpdatedBy;

    private Date rightUpdatedDate;

    private String rightIsOpen;

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId == null ? null : rightId.trim();
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    public String getRightRemark() {
        return rightRemark;
    }

    public void setRightRemark(String rightRemark) {
        this.rightRemark = rightRemark == null ? null : rightRemark.trim();
    }

    public String getRightCreatedBy() {
        return rightCreatedBy;
    }

    public void setRightCreatedBy(String rightCreatedBy) {
        this.rightCreatedBy = rightCreatedBy == null ? null : rightCreatedBy.trim();
    }

    public Date getRightCreatedDate() {
        return rightCreatedDate;
    }

    public void setRightCreatedDate(Date rightCreatedDate) {
        this.rightCreatedDate = rightCreatedDate;
    }

    public String getRightUpdatedBy() {
        return rightUpdatedBy;
    }

    public void setRightUpdatedBy(String rightUpdatedBy) {
        this.rightUpdatedBy = rightUpdatedBy == null ? null : rightUpdatedBy.trim();
    }

    public Date getRightUpdatedDate() {
        return rightUpdatedDate;
    }

    public void setRightUpdatedDate(Date rightUpdatedDate) {
        this.rightUpdatedDate = rightUpdatedDate;
    }

    public String getRightIsOpen() {
        return rightIsOpen;
    }

    public void setRightIsOpen(String rightIsOpen) {
        this.rightIsOpen = rightIsOpen == null ? null : rightIsOpen.trim();
    }
}