package com.meiduimall.service.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.annotation.HasMemId;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.service.OrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mall/catalog-service/v1")
public class OrderController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private OrderService orderService;

	/**
	 * 获取各种状态的订单数量
	 * @return
	 */
	@ApiOperation(value="获取各种状态的订单数量", notes="获取各种状态的订单数量")
	@HasMemId
	@RequestMapping(value = "/order_getEveryOrderTypeCount")
	public ResBodyData getEveryOrderTypeCount() {
		SysuserAccount sysuserAccount = (SysuserAccount) request.getAttribute("sysuserAccount");
		return orderService.getEveryOrderTypeCount(sysuserAccount);
	}
}
