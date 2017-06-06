package com.meiduimall.service.account.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseAccountBalance {

	// 可使用积分，不含冻结积分
	@JsonProperty("use_points")
	private String usePoints;
	
	// 全部积分，含冻结积分
	@JsonProperty("all_points")
	private String allPoints;
	
	// 冻结积分
	@JsonProperty("freeze_points")
	private String freezePoints;
	
	// 可使用账户余额，不含冻结余额
	@JsonProperty("use_money")
	private String useMoney;
	
	// 全部余额，含冻结余额
	@JsonProperty("all_money")
	private String allMoney;
	
	// 冻结余额
	@JsonProperty("freeze_money")
	private String freezeMoney;

	public String getUsePoints() {
		return usePoints;
	}

	public void setUsePoints(String usePoints) {
		this.usePoints = usePoints;
	}

	public String getAllPoints() {
		return allPoints;
	}

	public void setAllPoints(String allPoints) {
		this.allPoints = allPoints;
	}

	public String getFreezePoints() {
		return freezePoints;
	}

	public void setFreezePoints(String freezePoints) {
		this.freezePoints = freezePoints;
	}

	public String getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(String useMoney) {
		this.useMoney = useMoney;
	}

	public String getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}

	public String getFreezeMoney() {
		return freezeMoney;
	}

	public void setFreezeMoney(String freezeMoney) {
		this.freezeMoney = freezeMoney;
	}
}
