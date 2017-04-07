package com.meiduimall.service.catalog.service;

import com.meiduimall.core.ResBodyData;

/**
 * 店铺相关
 * @author yangchang
 *
 */
public interface ShopService {

	/**
	 * 查询店铺详情
	 * 
	 * @param shop_id
	 * @param token
	 * @return
	 */
	ResBodyData getShopDetail(Integer shopId, String token);

	/**
	 * 收藏或者取消收藏店铺
	 * 
	 * @param shop_id
	 * @param token
	 * @param isCollect 1表示收藏，0表示取消收藏
	 * @return
	 */
	ResBodyData cancelOrCollectShop(Integer shopId, String token, int isCollect);

	/**
	 * 查询店铺的商品分类
	 * 
	 * @param shop_id
	 * @return
	 */
	ResBodyData getShopProductCatalog(Integer shopId);

	/**
	 * 查询店铺商品列表
	 * 
	 * @param shopId
	 * @param orderBy 排序字段：store按销量，updateTime按修改时间，price按价格，point按积分；默认store按销量
	 * @param column 排序规则：desc降序，asc升序；默认desc降序
	 * @return
	 */
	ResBodyData getShopProductList(Integer shopId, String orderBy, String column);
}
