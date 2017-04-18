package com.meiduimall.application.mall.catalog.service;

import com.meiduimall.application.mall.catalog.request.ShopProductRequest;

public interface ShopService {

	/**
	 * 根据店铺shop_id，获取店铺详情
	 * 
	 * @param shop_id
	 *            店铺ID
	 * @param mem_id
	 *            会员系统ID
	 * @return
	 */
	public String getShopDetailHttp(int shop_id, String mem_id);

	/**
	 * 收藏店铺或者取消收藏
	 * 
	 * @param shop_id
	 *            店铺ID
	 * @param is_collect
	 *            收藏1，取消收藏0
	 * @param mem_id
	 *            会员系统ID
	 * @return
	 */
	public String collectOrCancelShopHttp(int shop_id, int is_collect, String mem_id);

	/**
	 * 获取商家自定义商品分类列表
	 * 
	 * @param shop_id
	 *            店铺ID
	 * @return
	 */
	public String getShopProductCatalogHttp(int shop_id);

	/**
	 * 获取店铺的商品列表
	 * 
	 * @param param
	 * @return
	 */
	public String getShopProductList(ShopProductRequest param);
}
