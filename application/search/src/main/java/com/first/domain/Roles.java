package com.first.domain;

import java.util.Date;

public class Roles {
    private String roleId;

    private String dictId;

    private String roleType;

    private String roleRemark;

    private String roleCreatedBy;

    private Date roleCreatedDate;

    private String roleUpdatedBy;

    private Date roleUpdatedDate;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark == null ? null : roleRemark.trim();
    }

    public String getRoleCreatedBy() {
        return roleCreatedBy;
    }

    public void setRoleCreatedBy(String roleCreatedBy) {
        this.roleCreatedBy = roleCreatedBy == null ? null : roleCreatedBy.trim();
    }

    public Date getRoleCreatedDate() {
        return roleCreatedDate;
    }

    public void setRoleCreatedDate(Date roleCreatedDate) {
        this.roleCreatedDate = roleCreatedDate;
    }

    public String getRoleUpdatedBy() {
        return roleUpdatedBy;
    }

    public void setRoleUpdatedBy(String roleUpdatedBy) {
        this.roleUpdatedBy = roleUpdatedBy == null ? null : roleUpdatedBy.trim();
    }

    public Date getRoleUpdatedDate() {
        return roleUpdatedDate;
    }

    public void setRoleUpdatedDate(Date roleUpdatedDate) {
        this.roleUpdatedDate = roleUpdatedDate;
    }
}