package com.meiduimall.service.search.entity;

import com.meiduimall.service.search.page.PageView;

public class ItemModel extends PageView {

	/**
	 * ID主键
	 */
	private String id;

	/**
	 * 商品编号
	 */
	private String itemId;

	/**
	 * 商品名称
	 */
	private String title;

	/**
	 * 价格
	 */
	private double price;

	/**
	 * 积分
	 */
	private double point;

	/**
	 * 成本价
	 */
	private double costPrice;

	/**
	 * 市场价
	 */
	private double mktPrice;

	/**
	 * 品牌ID
	 */
	private String brandId;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 分类ID
	 */
	private String catId;

	/**
	 * 分类
	 */
	private String cat;

	/**
	 * 分类路径
	 */
	private String catPath;

	/**
	 * 父分类ID
	 */
	private String catParentId;

	/**
	 * 父分类名
	 */
	private String catParentName;

	/**
	 * 图片地址
	 */
	private String image;

	/**
	 * 销售属性序列化
	 */
	private String specDesc;

	/**
	 * 店铺Id
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
	 * 店铺Logo
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

	/**
	 * 商品销量
	 */
	private Integer soldQuantity;

	/**
	 * 评论次数
	 */
	private Integer rateCount;

	/**
	 * 好评次数
	 */
	private Integer rateGoodCount;

	/**
	 * 购买次数
	 */
	private Integer buyCount;

	/**
	 * 访问次数
	 */
	private Integer viewCount;

	/**
	 * 收藏数量
	 */
	private Integer favCount;

	/**
	 * 上架时间
	 */
	private Integer listTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getMktPrice() {
		return mktPrice;
	}

	public void setMktPrice(double mktPrice) {
		this.mktPrice = mktPrice;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getCatPath() {
		return catPath;
	}

	public void setCatPath(String catPath) {
		this.catPath = catPath;
	}

	public String getCatParentId() {
		return catParentId;
	}

	public void setCatParentId(String catParentId) {
		this.catParentId = catParentId;
	}

	public String getCatParentName() {
		return catParentName;
	}

	public void setCatParentName(String catParentName) {
		this.catParentName = catParentName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSpecDesc() {
		return specDesc;
	}

	public void setSpecDesc(String specDesc) {
		this.specDesc = specDesc;
	}

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

	public Integer getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(Integer soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Integer getRateCount() {
		return rateCount;
	}

	public void setRateCount(Integer rateCount) {
		this.rateCount = rateCount;
	}

	public Integer getRateGoodCount() {
		return rateGoodCount;
	}

	public void setRateGoodCount(Integer rateGoodCount) {
		this.rateGoodCount = rateGoodCount;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getFavCount() {
		return favCount;
	}

	public void setFavCount(Integer favCount) {
		this.favCount = favCount;
	}

	public Integer getListTime() {
		return listTime;
	}

	public void setListTime(Integer listTime) {
		this.listTime = listTime;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

}
