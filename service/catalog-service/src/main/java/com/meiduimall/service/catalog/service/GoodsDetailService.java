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
	 * @param item_id
	 * @return
	 */
	ResBodyData checkItemIsExistById(int item_id);

	/**
	 * 根据商品id获得商品详细信息
	 * 
	 * @param token
	 * @param item_id
	 * @return
	 */
	ResBodyData getItemDetailById(String token, Integer item_id);
}
