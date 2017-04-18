package com.meiduimall.application.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysuserWalletPaylog implements Serializable {
    private Integer wpid;

    private String wpno;

    private Integer uid;

    private String loginName;

    private String orderNo;

    private BigDecimal vorMoney;

    private BigDecimal money;

    private String wptime;

    private BigDecimal afMoney;

    private static final long serialVersionUID = 1L;

    public Integer getWpid() {
        return wpid;
    }

    public void setWpid(Integer wpid) {
        this.wpid = wpid;
    }

    public String getWpno() {
        return wpno;
    }

    public void setWpno(String wpno) {
        this.wpno = wpno == null ? null : wpno.trim();
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

    public String getWptime() {
        return wptime;
    }

    public void setWptime(String wptime) {
        this.wptime = wptime == null ? null : wptime.trim();
    }

    public BigDecimal getAfMoney() {
        return afMoney;
    }

    public void setAfMoney(BigDecimal afMoney) {
        this.afMoney = afMoney;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wpid=").append(wpid);
        sb.append(", wpno=").append(wpno);
        sb.append(", uid=").append(uid);
        sb.append(", loginName=").append(loginName);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", vorMoney=").append(vorMoney);
        sb.append(", money=").append(money);
        sb.append(", wptime=").append(wptime);
        sb.append(", afMoney=").append(afMoney);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}