package com.meiduimall.service.catalog.entity;

import java.math.BigDecimal;

public class SystradeCart {

	private Integer cartId;

	private Integer userId;

	private String userIdent;

	private Integer shopId;

	private String objType;

	private Integer itemId;

	private Integer skuId;

	private String title;

	private String imageDefaultId;

	private Double quantity;

	private BigDecimal cXfc;

	private Integer isChecked;

	private String selectedPromotion;

	private Integer createdTime;

	private Integer modifiedTime;

	private Integer activityId;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserIdent() {
		return userIdent;
	}

	public void setUserIdent(String userIdent) {
		this.userIdent = userIdent == null ? null : userIdent.trim();
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType == null ? null : objType.trim();
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getImageDefaultId() {
		return imageDefaultId;
	}

	public void setImageDefaultId(String imageDefaultId) {
		this.imageDefaultId = imageDefaultId == null ? null : imageDefaultId.trim();
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getcXfc() {
		return cXfc;
	}

	public void setcXfc(BigDecimal cXfc) {
		this.cXfc = cXfc;
	}

	public Integer getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}

	public String getSelectedPromotion() {
		return selectedPromotion;
	}

	public void setSelectedPromotion(String selectedPromotion) {
		this.selectedPromotion = selectedPromotion == null ? null : selectedPromotion.trim();
	}

	public Integer getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Integer createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Integer modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
}