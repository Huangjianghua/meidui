package com.meiduimall.application.search.manage.pojo;

public class ShopInfo {

	/**
	 * 店铺编号id
	 */
	private String shopId;
	
	/**
	 * 店铺名称
	 */
	private String shopName;
	
	/**
	 * 店铺描述
	 */
	private String shopDescript;
	
	/**
	 * 提交logo
	 */
	private String shopLogo;
	
	/**
	 * 商铺评分
	 */
	private String tallyScore;
	
	/**
	 * 服务评分
	 */
	private String attitudeScore;
	
	/**
	 * 配送得分
	 */
	private String deliverySpeedScore;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopDescript() {
		return shopDescript;
	}

	public void setShopDescript(String shopDescript) {
		this.shopDescript = shopDescript;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getTallyScore() {
		return tallyScore;
	}

	public void setTallyScore(String tallyScore) {
		this.tallyScore = tallyScore;
	}

	public String getAttitudeScore() {
		return attitudeScore;
	}

	public void setAttitudeScore(String attitudeScore) {
		this.attitudeScore = attitudeScore;
	}

	public String getDeliverySpeedScore() {
		return deliverySpeedScore;
	}

	public void setDeliverySpeedScore(String deliverySpeedScore) {
		this.deliverySpeedScore = deliverySpeedScore;
	}
}
