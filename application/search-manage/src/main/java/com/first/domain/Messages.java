package com.first.domain;

import java.util.Date;

public class Messages {
    private String mesId;

    private String mesTitle;

    private String mesCreatedBy;

    private Date mesCreatedDate;

    private String mesUpdatedBy;

    private Date mesUpdatedDate;

    private String mesStatus;

    private Date mesReaderDate;

    private String memId;

    public String getMesId() {
        return mesId;
    }

    public void setMesId(String mesId) {
        this.mesId = mesId == null ? null : mesId.trim();
    }

    public String getMesTitle() {
        return mesTitle;
    }

    public void setMesTitle(String mesTitle) {
        this.mesTitle = mesTitle == null ? null : mesTitle.trim();
    }

    public String getMesCreatedBy() {
        return mesCreatedBy;
    }

    public void setMesCreatedBy(String mesCreatedBy) {
        this.mesCreatedBy = mesCreatedBy == null ? null : mesCreatedBy.trim();
    }

    public Date getMesCreatedDate() {
        return mesCreatedDate;
    }

    public void setMesCreatedDate(Date mesCreatedDate) {
        this.mesCreatedDate = mesCreatedDate;
    }

    public String getMesUpdatedBy() {
        return mesUpdatedBy;
    }

    public void setMesUpdatedBy(String mesUpdatedBy) {
        this.mesUpdatedBy = mesUpdatedBy == null ? null : mesUpdatedBy.trim();
    }

    public Date getMesUpdatedDate() {
        return mesUpdatedDate;
    }

    public void setMesUpdatedDate(Date mesUpdatedDate) {
        this.mesUpdatedDate = mesUpdatedDate;
    }

    public String getMesStatus() {
        return mesStatus;
    }

    public void setMesStatus(String mesStatus) {
        this.mesStatus = mesStatus == null ? null : mesStatus.trim();
    }

    public Date getMesReaderDate() {
        return mesReaderDate;
    }

    public void setMesReaderDate(Date mesReaderDate) {
        this.mesReaderDate = mesReaderDate;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId == null ? null : memId.trim();
    }
}