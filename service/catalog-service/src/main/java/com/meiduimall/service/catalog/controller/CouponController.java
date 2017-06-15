package com.meiduimall.service.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.service.CouponService;

@RestController
@RequestMapping("/mall/catalog-service/v1/coupon")
public class CouponController {

	@Autowired
	private CouponService couponService;

	/**
	 * 查询所有的优惠券规则  
	 * 
	 * @return 优惠券赠送规则
	 */
	@RequestMapping("/getAllRule")
	public ResBodyData selectAllCouponRule() {
		return couponService.selectAllCouponRule();
	}
}
