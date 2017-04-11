package com.meiduimall.application.search.manage.domain;

import java.util.Date;

public class Platforms {
    private String pfId;

    private String pfLoginName;

    private String islock;

    private String pfDepartment;

    private String pfNickName;

    private String pfPic;

    private String pfEmail;

    private String pfPhone;

    private String pfPwd;

    private String pfRemark;

    private String pfCreatedBy;

    private Date pfCreatedDate;

    private Date pfLastLoginDate;

    private String pfUpdatedBy;

    private Date pfUpdatedDate;

    private String pfLicenseKey;
    

    public String getPfId() {
        return pfId;
    }

    public void setPfId(String pfId) {
        this.pfId = pfId == null ? null : pfId.trim();
    }

    public String getPfLoginName() {
        return pfLoginName;
    }

    public void setPfLoginName(String pfLoginName) {
        this.pfLoginName = pfLoginName == null ? null : pfLoginName.trim();
    }

    public String getIslock() {
        return islock;
    }

    public void setIslock(String islock) {
        this.islock = islock == null ? null : islock.trim();
    }

    public String getPfDepartment() {
        return pfDepartment;
    }

    public void setPfDepartment(String pfDepartment) {
        this.pfDepartment = pfDepartment == null ? null : pfDepartment.trim();
    }

    public String getPfNickName() {
        return pfNickName;
    }

    public void setPfNickName(String pfNickName) {
        this.pfNickName = pfNickName == null ? null : pfNickName.trim();
    }

    public String getPfPic() {
        return pfPic;
    }

    public void setPfPic(String pfPic) {
        this.pfPic = pfPic == null ? null : pfPic.trim();
    }

    public String getPfEmail() {
        return pfEmail;
    }

    public void setPfEmail(String pfEmail) {
        this.pfEmail = pfEmail == null ? null : pfEmail.trim();
    }

    public String getPfPhone() {
        return pfPhone;
    }

    public void setPfPhone(String pfPhone) {
        this.pfPhone = pfPhone == null ? null : pfPhone.trim();
    }

    public String getPfPwd() {
        return pfPwd;
    }

    public void setPfPwd(String pfPwd) {
        this.pfPwd = pfPwd == null ? null : pfPwd.trim();
    }

    public String getPfRemark() {
        return pfRemark;
    }

    public void setPfRemark(String pfRemark) {
        this.pfRemark = pfRemark == null ? null : pfRemark.trim();
    }

    public String getPfCreatedBy() {
        return pfCreatedBy;
    }

    public void setPfCreatedBy(String pfCreatedBy) {
        this.pfCreatedBy = pfCreatedBy == null ? null : pfCreatedBy.trim();
    }

    public Date getPfCreatedDate() {
        return pfCreatedDate;
    }

    public void setPfCreatedDate(Date pfCreatedDate) {
        this.pfCreatedDate = pfCreatedDate;
    }

    public Date getPfLastLoginDate() {
        return pfLastLoginDate;
    }

    public void setPfLastLoginDate(Date pfLastLoginDate) {
        this.pfLastLoginDate = pfLastLoginDate;
    }

    public String getPfUpdatedBy() {
        return pfUpdatedBy;
    }

    public void setPfUpdatedBy(String pfUpdatedBy) {
        this.pfUpdatedBy = pfUpdatedBy == null ? null : pfUpdatedBy.trim();
    }

    public Date getPfUpdatedDate() {
        return pfUpdatedDate;
    }

    public void setPfUpdatedDate(Date pfUpdatedDate) {
        this.pfUpdatedDate = pfUpdatedDate;
    }

    public String getPfLicenseKey() {
        return pfLicenseKey;
    }

    public void setPfLicenseKey(String pfLicenseKey) {
        this.pfLicenseKey = pfLicenseKey == null ? null : pfLicenseKey.trim();
    }

    
    
}