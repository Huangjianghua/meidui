package com.meiduimall.service.account.model.response;

public class PersonalConsumptionPointsResult {

	private String accountIntegral;//个人账户积分
	
	private String consumMoney;//个人消费金额
	
	private String consumptionPersonal;//个人消费返还
	
	private int childPersons;//粉丝团人数
	
	private String allChildMoney;//粉丝团总消费
	
	private String allChildIntegral;//粉丝团总消费返还
	
	private String priceXFC;//最新XFC价格

	public String getAccountIntegral() {
		return accountIntegral;
	}

	public void setAccountIntegral(String accountIntegral) {
		this.accountIntegral = accountIntegral;
	}

	public String getConsumMoney() {
		return consumMoney;
	}

	public void setConsumMoney(String consumMoney) {
		this.consumMoney = consumMoney;
	}

	public String getConsumptionPersonal() {
		return consumptionPersonal;
	}

	public void setConsumptionPersonal(String consumptionPersonal) {
		this.consumptionPersonal = consumptionPersonal;
	}

	public int getChildPersons() {
		return childPersons;
	}

	public void setChildPersons(int childPersons) {
		this.childPersons = childPersons;
	}

	public String getAllChildMoney() {
		return allChildMoney;
	}

	public void setAllChildMoney(String allChildMoney) {
		this.allChildMoney = allChildMoney;
	}

	public String getAllChildIntegral() {
		return allChildIntegral;
	}

	public void setAllChildIntegral(String allChildIntegral) {
		this.allChildIntegral = allChildIntegral;
	}

	public String getPriceXFC() {
		return priceXFC;
	}

	public void setPriceXFC(String priceXFC) {
		this.priceXFC = priceXFC;
	}
}
