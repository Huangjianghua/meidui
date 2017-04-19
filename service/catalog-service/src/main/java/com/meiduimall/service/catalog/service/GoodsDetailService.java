package com.meiduimall.service.catalog.service;

import com.meiduimall.core.ResBodyData;

/**
 * 商品详情基本操作
 * 
 * @author yangchangfu
 *
 */
public interface GoodsDetailService {

	/**
	 * 根据商品id查询该商品是否存在
	 * 
	 * @param itemId
	 *            商品ID
	 * @return
	 */
	ResBodyData checkItemIsExistById(int itemId);

	/**
	 * 根据商品id获得商品详细信息
	 * 
	 * @param memId
	 *            会员系统ID
	 * @param itemId
	 *            商品ID
	 * @return
	 */
	ResBodyData getItemDetailById(String memId, Integer itemId);
}
