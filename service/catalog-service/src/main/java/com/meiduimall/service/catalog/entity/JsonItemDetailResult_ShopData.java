package com.meiduimall.service.catalog.entity;

/**
 * 店铺信息
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResult_ShopData {
	
	private String shop_id;
	private String shop_name;// 店铺名称
	private String shop_descript;// 店铺描述
	
	// 店铺状态：self 运营商自营店铺， brand 品牌专卖店， cat 类目专营店， flag 品牌旗舰店
	private String shop_type;
	
	private String shop_logo;// 店铺默认图片
	private String tally_dsr;// 店铺评分--描述相符
	private String attitude_dsr;// 店铺评分--描述相符
	private String delivery_speed_dsr;// 店铺评分--描述相符
	private String shop_area;//店铺所在地
	private String open_time;//开店时间
	private String is_collect;//是否收藏了该店铺：0没有收藏，1收藏了

	public String getShop_area() {
		return shop_area;
	}
	public void setShop_area(String shop_area) {
		this.shop_area = shop_area;
	}
	public String getOpen_time() {
		return open_time;
	}
	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}
	public String getIs_collect() {
		return is_collect;
	}
	public void setIs_collect(String is_collect) {
		this.is_collect = is_collect;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_descript() {
		return shop_descript;
	}
	public void setShop_descript(String shop_descript) {
		this.shop_descript = shop_descript;
	}
	public String getShop_type() {
		return shop_type;
	}
	public void setShop_type(String shop_type) {
		this.shop_type = shop_type;
	}
	public String getShop_logo() {
		return shop_logo;
	}
	public void setShop_logo(String shop_logo) {
		this.shop_logo = shop_logo;
	}
	public String getTally_dsr() {
		return tally_dsr;
	}
	public void setTally_dsr(String tally_dsr) {
		this.tally_dsr = tally_dsr;
	}
	public String getAttitude_dsr() {
		return attitude_dsr;
	}
	public void setAttitude_dsr(String attitude_dsr) {
		this.attitude_dsr = attitude_dsr;
	}
	public String getDelivery_speed_dsr() {
		return delivery_speed_dsr;
	}
	public void setDelivery_speed_dsr(String delivery_speed_dsr) {
		this.delivery_speed_dsr = delivery_speed_dsr;
	}
}
