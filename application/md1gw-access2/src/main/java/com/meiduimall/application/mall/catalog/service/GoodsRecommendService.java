package com.meiduimall.application.mall.catalog.service;

public interface GoodsRecommendService {

	/**
	 * 根据推荐类型，获取推荐商品
	 * 
	 * @param type
	 *            推荐类型
	 * @param req_id
	 *            请求来源
	 * @return
	 */
	public String getFirstRecommendGoodsHttp(int type, int req_id);
}