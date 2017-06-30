package com.meiduimall.service.catalog.entity;

public class SystradeTradeWithBLOBs extends SystradeTrade {
	private String cancelReason;

	private String shopMemo;

	private String tradeMemo;

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason == null ? null : cancelReason.trim();
	}

	public String getShopMemo() {
		return shopMemo;
	}

	public void setShopMemo(String shopMemo) {
		this.shopMemo = shopMemo == null ? null : shopMemo.trim();
	}

	public String getTradeMemo() {
		return tradeMemo;
	}

	public void setTradeMemo(String tradeMemo) {
		this.tradeMemo = tradeMemo == null ? null : tradeMemo.trim();
	}
}