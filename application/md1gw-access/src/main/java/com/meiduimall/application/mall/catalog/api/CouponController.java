package com.meiduimall.application.mall.catalog.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.mall.catalog.service.CouponService;
import com.meiduimall.core.ResBodyData;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1/coupon")
public class CouponController {
	
	@Autowired
	private HttpServletResponse response;

	@Autowired
	private CouponService couponService;

	/**
	 * 查询所有的优惠券赠送规则
	 * 
	 * @return 优惠券赠送规则
	 */
	@ApiOperation(value="查询所有的优惠券赠送规则", notes="查询所有的优惠券赠送规则")
	@RequestMapping("/getAllRule")
	public ResBodyData getAllCouponRule() {
		// 增加头部--解决JS跨域问题
		response.setHeader("Access-Control-Allow-Origin", "*");
		return couponService.getAllCouponRule();
	}
}
