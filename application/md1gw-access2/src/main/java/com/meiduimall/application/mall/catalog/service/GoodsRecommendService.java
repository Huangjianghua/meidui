package com.meiduimall.application.mall.catalog.service;

import com.meiduimall.core.ResBodyData;

public interface GoodsRecommendService {

	/**
	 * 请求微服务，根据推荐类型，获取推荐商品
	 * 
	 * @param type
	 *            推荐类型
	 * @param sourceId
	 *            请求来源
	 * @return 推荐商品列表
	 */
	public ResBodyData getFirstRecommendGoodsHttp(int type, int sourceId);
}
