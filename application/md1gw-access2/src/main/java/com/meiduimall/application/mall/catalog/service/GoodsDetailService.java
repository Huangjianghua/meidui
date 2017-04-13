package com.meiduimall.application.mall.catalog.service;

public interface GoodsDetailService {
	
	/**
	 * 根据商品item_id获取商品详情
	 * 
	 * @param item_id
	 *            商品ID
	 * @param mem_id
	 *            会员系统ID
	 * @return
	 */
	public String getItemDetailHttp(int item_id, String mem_id);
}
