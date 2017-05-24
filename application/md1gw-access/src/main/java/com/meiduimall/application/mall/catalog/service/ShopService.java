package com.meiduimall.application.mall.catalog.service;

import com.meiduimall.application.mall.catalog.request.ShopProductRequest;
import com.meiduimall.core.ResBodyData;

public interface ShopService {

	/**
	 * 请求微服务，根据店铺shopId，获取店铺详情
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param memId
	 *            会员系统ID
	 * @return 店铺详情
	 */
	public ResBodyData getShopDetailHttp(int shopId, String memId);

	/**
	 * 请求微服务，收藏店铺或者取消收藏
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param isCollect
	 *            收藏1，取消收藏0
	 * @param memId
	 *            会员系统ID
	 * @return 收藏/取消收藏结果
	 */
	public ResBodyData collectOrCancelShopHttp(int shopId, int isCollect, String memId);

	/**
	 * 请求微服务，获取商家自定义商品分类列表
	 * 
	 * @param shopId
	 *            店铺ID
	 * @return 商品分类列表
	 */
	public ResBodyData getShopProductCatalogHttp(int shopId);

	/**
	 * 请求微服务，获取店铺的商品列表
	 * 
	 * @param param
	 *            请求参数封装ShopProductRequest对象
	 * @return 商品列表
	 */
	public ResBodyData getShopProductList(ShopProductRequest param);
}
