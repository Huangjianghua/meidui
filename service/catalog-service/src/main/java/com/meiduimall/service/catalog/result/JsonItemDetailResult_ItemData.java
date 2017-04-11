package com.meiduimall.service.catalog.result;

/**
 * 商品详情
 * 
 * @author yangchang
 *
 */
public class JsonItemDetailResult_ItemData {

	private String itme_id;
	private String price;// 商品价格
	private String point;// 可用美兑积分
	private String title;// 商品标题、商品名称
	private String sub_title;// 子标题
	private String list_image;// 商品图片集合
	private String image_default_id;// 商品默认图片
	private String is_collect;// 用户是否收藏了该商品--根据token判断:1表示收藏，0表示没有收藏
	private String sales_volume;// 商品销量
	private String rate_count;// 被评论数量
	private String html_detail_url;// HTML商品详情页
	private String bn;// 商品编号
	private String weight;// 商品重量(毛重)
	private String approve_status;// 商品状态：onsale 出售中，instock 库中
	private String list_time;// 商品上架时间
	private String item_store;// 商品库存
	private String is_show_weight;//是否显示重量
	
	public String getIs_show_weight() {
		return is_show_weight;
	}

	public void setIs_show_weight(String is_show_weight) {
		this.is_show_weight = is_show_weight;
	}

	public String getItem_store() {
		return item_store;
	}

	public void setItem_store(String item_store) {
		this.item_store = item_store;
	}

	public String getItme_id() {
		return itme_id;
	}

	public void setItme_id(String itme_id) {
		this.itme_id = itme_id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getList_image() {
		return list_image;
	}

	public void setList_image(String list_image) {
		this.list_image = list_image;
	}

	public String getImage_default_id() {
		return image_default_id;
	}

	public void setImage_default_id(String image_default_id) {
		this.image_default_id = image_default_id;
	}

	public String getIs_collect() {
		return is_collect;
	}

	public void setIs_collect(String is_collect) {
		this.is_collect = is_collect;
	}

	public String getSales_volume() {
		return sales_volume;
	}

	public void setSales_volume(String sales_volume) {
		this.sales_volume = sales_volume;
	}

	public String getRate_count() {
		return rate_count;
	}

	public void setRate_count(String rate_count) {
		this.rate_count = rate_count;
	}

	public String getHtml_detail_url() {
		return html_detail_url;
	}

	public void setHtml_detail_url(String html_detail_url) {
		this.html_detail_url = html_detail_url;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getApprove_status() {
		return approve_status;
	}

	public void setApprove_status(String approve_status) {
		this.approve_status = approve_status;
	}

	public String getList_time() {
		return list_time;
	}

	public void setList_time(String list_time) {
		this.list_time = list_time;
	}
}
