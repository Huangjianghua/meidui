package com.meiduimall.service.catalog.result;

import java.math.BigDecimal;

public class CouponRuleResult {

	private BigDecimal minprice;
	private BigDecimal maxprice;
	private BigDecimal deductMoney;

	public BigDecimal getMinprice() {
		return minprice;
	}

	public void setMinprice(BigDecimal minprice) {
		this.minprice = minprice;
	}

	public BigDecimal getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(BigDecimal maxprice) {
		this.maxprice = maxprice;
	}

	public BigDecimal getDeductMoney() {
		return deductMoney;
	}

	public void setDeductMoney(BigDecimal deductMoney) {
		this.deductMoney = deductMoney;
	}
}