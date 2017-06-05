package com.meiduimall.service.account.model.response;

public class ResponsePersonalConsumptionPoints {

	private String accountIntegral;// 个人账户积分

	private String consumMoney;// 个人消费金额

	private int childPersons;// 粉丝团人数

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

	public int getChildPersons() {
		return childPersons;
	}

	public void setChildPersons(int childPersons) {
		this.childPersons = childPersons;
	}

}
