package com.meiduimall.service.catalog.service;

import com.meiduimall.core.ResBodyData;

/**
 * 商品推荐相关操作
 * 
 * @author yangchangfu
 *
 */
public interface GoodsRecommendService {

	/**
	 * 批量插入推荐商品
	 * 
	 * @param itemIds
	 *            商品ID，一个或者多个
	 * @param type
	 *            推荐类型
	 * @param optUser
	 *            操作人
	 * @param ip
	 *            操作IP
	 * @param recoLevel
	 *            推荐等级
	 * @return
	 */
	ResBodyData insertBatchItems(int[] itemIds, int type, String optUser, String ip, int recoLevel);

	/**
	 * 根据类型，获得优先推荐的商品
	 * 
	 * @param type
	 *            推荐类型
	 * @param count
	 *            推荐个数
	 * @param sourceId
	 *            客户端编号
	 * @return
	 */
	ResBodyData getFirstRecommendItems(int type, int count, int sourceId);

	/**
	 * 获取所有类型，优先推荐的商品
	 * @param count 推荐个数
	 * @return
	 */
	ResBodyData getFirstRecommendItemsAllType(int count);
}
