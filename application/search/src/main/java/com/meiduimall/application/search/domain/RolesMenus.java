package com.meiduimall.application.search.domain;

public class RolesMenus {
    private String roleId;

    private String menId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenId() {
        return menId;
    }

    public void setMenId(String menId) {
        this.menId = menId == null ? null : menId.trim();
    }
}