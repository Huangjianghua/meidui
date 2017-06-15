package com.meiduimall.application.mall.catalog.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * O2O数据
 * @author yangchang
 */
public class OtoInfoResult {
	
	/** o2o数据库存的userId */
	@JsonProperty("oto_user_id")
	private String otoUserId = "";

	/** 是否是商家 */
	@JsonProperty("is_store")
	private int isStore;

	/** 是否是代理 */
	@JsonProperty("is_agent")
	private int isAgent;

	/** O2O待付款订单 */
	@JsonProperty("wait_sure_order")
	private int waitSureOrder;

	/** O2O待评价订单 */
	@JsonProperty("already_sure_order")
	private int alreadySureOrder;

	/** 个人二维码地址 */
	private String qrcode = "";

	public String getOtoUserId() {
		return otoUserId;
	}

	public void setOtoUserId(String otoUserId) {
		this.otoUserId = otoUserId;
	}

	public int getIsStore() {
		return isStore;
	}

	public void setIsStore(int isStore) {
		this.isStore = isStore;
	}

	public int getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(int isAgent) {
		this.isAgent = isAgent;
	}

	public int getWaitSureOrder() {
		return waitSureOrder;
	}

	public void setWaitSureOrder(int waitSureOrder) {
		this.waitSureOrder = waitSureOrder;
	}

	public int getAlreadySureOrder() {
		return alreadySureOrder;
	}

	public void setAlreadySureOrder(int alreadySureOrder) {
		this.alreadySureOrder = alreadySureOrder;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
}
