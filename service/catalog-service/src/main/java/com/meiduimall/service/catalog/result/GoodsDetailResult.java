package com.meiduimall.service.catalog.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 查询结果--商品详情对象
 * @author yangchang
 *
 */
public class GoodsDetailResult {
	
	@JsonProperty("item_id")
	private String itemId;
	
	// 
	private String title;
	
	// 商品子标题
	@JsonProperty("sub_title")
	private String subTitle;
	
	// 不参与活动时的价格
	private String price;
	
	// 不参与活动时的美兑积分
	private String point;
	
	// 默认图片
	@JsonProperty("image_default_id")
	private String imageDefaultId;
	
	// 商品访问地址
	private String url;
	
	// 活动价格
	@JsonProperty("activity_price")
	private String activityPrice = "";
	
	// 活动积分价，活动时可用美兑积分
	@JsonProperty("activity_point")
	private int activityPoint;
	
	// 活动开始时间
	@JsonProperty("activity_start_time")
	private String activityStartTime = "";
	
	// 活动结束时间
	@JsonProperty("activity_end_time")
	private String activityEndTime = "";
	
	// 是否参与活动:0表示不参与，1表示参与
	@JsonProperty("is_join_activity")
	private int isJoinActivity = 0;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getImageDefaultId() {
		return imageDefaultId;
	}

	public void setImageDefaultId(String imageDefaultId) {
		this.imageDefaultId = imageDefaultId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(String activityPrice) {
		this.activityPrice = activityPrice;
	}

	public int getActivityPoint() {
		return activityPoint;
	}

	public void setActivityPoint(int activityPoint) {
		this.activityPoint = activityPoint;
	}

	public String getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	public String getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public int getIsJoinActivity() {
		return isJoinActivity;
	}

	public void setIsJoinActivity(int isJoinActivity) {
		this.isJoinActivity = isJoinActivity;
	}
}
