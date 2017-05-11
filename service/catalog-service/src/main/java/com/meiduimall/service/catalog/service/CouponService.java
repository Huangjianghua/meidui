package com.meiduimall.service.catalog.service;

import com.meiduimall.core.ResBodyData;

public interface CouponService {

	/**
	 * 查询所有的优惠券规则
	 * 
	 * @return 优惠券赠送规则
	 */
	ResBodyData selectAllCouponRule();
}
