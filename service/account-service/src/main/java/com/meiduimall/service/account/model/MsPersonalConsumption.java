package com.meiduimall.service.account.model;

import java.util.Date;

public class MsPersonalConsumption {
	
	
    private String id;

    private String memId;

    private String allchildMoney;

    private String personalMoney;

    private String consumptionRebate;

    private String consumptionPersonal;

    private Integer allchildAmount;

    private String allchildIntegral;

    private Date createdDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId == null ? null : memId.trim();
    }

    public String getAllchildMoney() {
        return allchildMoney;
    }

    public void setAllchildMoney(String allchildMoney) {
        this.allchildMoney = allchildMoney == null ? null : allchildMoney.trim();
    }

    public String getPersonalMoney() {
        return personalMoney;
    }

    public void setPersonalMoney(String personalMoney) {
        this.personalMoney = personalMoney == null ? null : personalMoney.trim();
    }

    public String getConsumptionRebate() {
        return consumptionRebate;
    }

    public void setConsumptionRebate(String consumptionRebate) {
        this.consumptionRebate = consumptionRebate == null ? null : consumptionRebate.trim();
    }

    public String getConsumptionPersonal() {
        return consumptionPersonal;
    }

    public void setConsumptionPersonal(String consumptionPersonal) {
        this.consumptionPersonal = consumptionPersonal == null ? null : consumptionPersonal.trim();
    }

    public Integer getAllchildAmount() {
        return allchildAmount;
    }

    public void setAllchildAmount(Integer allchildAmount) {
        this.allchildAmount = allchildAmount;
    }

    public String getAllchildIntegral() {
        return allchildIntegral;
    }

    public void setAllchildIntegral(String allchildIntegral) {
        this.allchildIntegral = allchildIntegral == null ? null : allchildIntegral.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}