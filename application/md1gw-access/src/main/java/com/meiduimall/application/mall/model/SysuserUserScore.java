package com.meiduimall.application.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysuserUserScore implements Serializable {
    private Integer scid;

    private String scno;

    private Integer uid;

    private String loginName;

    private String orderNo;

    private BigDecimal vorMoney;

    private BigDecimal money;

    private BigDecimal afMoney;

    private String utime;

    private String status;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public String getScno() {
        return scno;
    }

    public void setScno(String scno) {
        this.scno = scno == null ? null : scno.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getVorMoney() {
        return vorMoney;
    }

    public void setVorMoney(BigDecimal vorMoney) {
        this.vorMoney = vorMoney;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getAfMoney() {
        return afMoney;
    }

    public void setAfMoney(BigDecimal afMoney) {
        this.afMoney = afMoney;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime == null ? null : utime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scid=").append(scid);
        sb.append(", scno=").append(scno);
        sb.append(", uid=").append(uid);
        sb.append(", loginName=").append(loginName);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", vorMoney=").append(vorMoney);
        sb.append(", money=").append(money);
        sb.append(", afMoney=").append(afMoney);
        sb.append(", utime=").append(utime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}