package com.meiduimall.application.md1gwaccess.model;

import java.io.Serializable;

public class SysuserAccount implements Serializable {
    private Integer userId;

    private String loginAccount;

    private String email;

    private String mobile;

    private String loginPassword;

    private String loginToken;

    private String loginType;

    private String shareMan;

    private String area;

    private Boolean disabled;

    private Integer createtime;

    private Integer modifiedTime;

    private String payPassword;

    private String memId;

    private Boolean usePayPassword;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken == null ? null : loginToken.trim();
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType == null ? null : loginType.trim();
    }

    public String getShareMan() {
        return shareMan;
    }

    public void setShareMan(String shareMan) {
        this.shareMan = shareMan == null ? null : shareMan.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Integer getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Integer modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId == null ? null : memId.trim();
    }

    public Boolean getUsePayPassword() {
        return usePayPassword;
    }

    public void setUsePayPassword(Boolean usePayPassword) {
        this.usePayPassword = usePayPassword;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", loginAccount=").append(loginAccount);
        sb.append(", email=").append(email);
        sb.append(", mobile=").append(mobile);
        sb.append(", loginPassword=").append(loginPassword);
        sb.append(", loginToken=").append(loginToken);
        sb.append(", loginType=").append(loginType);
        sb.append(", shareMan=").append(shareMan);
        sb.append(", area=").append(area);
        sb.append(", disabled=").append(disabled);
        sb.append(", createtime=").append(createtime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", payPassword=").append(payPassword);
        sb.append(", memId=").append(memId);
        sb.append(", usePayPassword=").append(usePayPassword);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}