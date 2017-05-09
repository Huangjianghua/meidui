package com.meiduimall.application.mall.pay.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EctoolsPaymentsSucc implements Serializable {
    private String paymentId;

    private BigDecimal money;

    private String status;

    private String payAppId;

    private String payName;

    private Integer payedTime;

    private String account;

    private String bank;

    private String payAccount;

    private BigDecimal paycost;

    private String payVer;

    private String ip;

    private String tradeNo;

    private static final long serialVersionUID = 1L;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPayAppId() {
        return payAppId;
    }

    public void setPayAppId(String payAppId) {
        this.payAppId = payAppId == null ? null : payAppId.trim();
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    public Integer getPayedTime() {
        return payedTime;
    }

    public void setPayedTime(Integer payedTime) {
        this.payedTime = payedTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount == null ? null : payAccount.trim();
    }

    public BigDecimal getPaycost() {
        return paycost;
    }

    public void setPaycost(BigDecimal paycost) {
        this.paycost = paycost;
    }

    public String getPayVer() {
        return payVer;
    }

    public void setPayVer(String payVer) {
        this.payVer = payVer == null ? null : payVer.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paymentId=").append(paymentId);
        sb.append(", money=").append(money);
        sb.append(", status=").append(status);
        sb.append(", payAppId=").append(payAppId);
        sb.append(", payName=").append(payName);
        sb.append(", payedTime=").append(payedTime);
        sb.append(", account=").append(account);
        sb.append(", bank=").append(bank);
        sb.append(", payAccount=").append(payAccount);
        sb.append(", paycost=").append(paycost);
        sb.append(", payVer=").append(payVer);
        sb.append(", ip=").append(ip);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}