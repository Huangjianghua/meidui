package com.meiduimall.service.catalog.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.request.ShopProductRequest;

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
	 * @param mem_id 会员系统ID
	 * @return
	 */
	ResBodyData getShopDetail(Integer shopId, String mem_id);

	/**
	 * 收藏或者取消收藏店铺
	 * 
	 * @param shop_id
	 * @param sysuserAccount 用户基本账户信息
	 * @param isCollect 1表示收藏，0表示取消收藏
	 * @return
	 */
	ResBodyData collectOrCancelShop(Integer shopId, SysuserAccount sysuserAccount, int isCollect);

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
	ResBodyData getShopProductList(ShopProductRequest params);
}
