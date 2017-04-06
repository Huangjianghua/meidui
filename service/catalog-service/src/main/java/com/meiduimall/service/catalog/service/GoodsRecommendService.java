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
	 * @param item_ids
	 *            商品ID，一个或者多个
	 * @param type
	 *            推荐类型
	 * @param opt_user
	 *            操作人
	 * @param ip
	 *            操作IP
	 * @param reco_level
	 *            推荐等级
	 * @return
	 */
	ResBodyData insertBatchItems(int[] item_ids, int type, String opt_user, String ip, int reco_level);

	/**
	 * 根据类型，获得优先推荐的商品
	 * 
	 * @param type
	 *            推荐类型
	 * @param count
	 *            推荐个数
	 * @param req_id
	 *            客户端编号
	 * @return
	 */
	ResBodyData getFirstRecommendItems(int type, int count, int req_id);

	/**
	 * 获得所有的推荐商品，需要分页
	 * 
	 * @param pageNo
	 *            页数，默认是1
	 * @param pageSize
	 *            每页数量，默认是10
	 * @return
	 */
	ResBodyData getAllRecommendItems(int pageNo, int pageSize);

	/**
	 * 获取所有类型，优先推荐的商品
	 * @param count 推荐个数
	 * @return
	 */
	ResBodyData getFirstRecommendItemsAllType(int count);
	
	/**
	 * 
	 * @param type
	 *            推荐类型
	 * @param pageNo
	 *            页数，默认是1
	 * @param pageSize
	 *            每页数量，默认是10
	 * @return
	 */
	ResBodyData getRecommendItemsByType(int type, int pageNo, int pageSize);

	/**
	 * 批量删除推荐商品
	 * @param item_id 商品编号
	 * @param opt_user 操作人
	 * @return
	 */
	ResBodyData deleteRecommendItems(int[] item_id, String opt_user);

	/**
	 * 更改推荐商品的推荐排序值
	 * @param item_id 商品编号
	 * @param opt_user 操作人
	 * @return
	 */
	ResBodyData updateRecommendItemLevel(int item_id, String opt_user);
}
