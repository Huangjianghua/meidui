package com.meiduimall.application.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.catalog.service.GoodsDetailService;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.exception.ApiException;

/**
 * 商品详情相关类
 * 
 * @author yangchangfu
 */
@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1/goodsDetail")
public class GoodsDetailController {

	private static Logger logger = LoggerFactory.getLogger(GoodsDetailController.class);

	@Autowired
	private GoodsDetailService goodsDetailService;

	/**
	 * 根据商品item_id获取商品详情
	 * 
	 * @param request
	 * @param item_id
	 *            商品ID
	 * @return
	 */
	@RequestMapping("/getItem")
	public String getItemDetail(HttpServletRequest request, String item_id) {
		int itemId = 0;
		try {
			itemId = Integer.parseInt(item_id);
		} catch (NumberFormatException e) {
			logger.error("根据商品item_id获取商品详情，服务器异常：" + e);
			throw new ApiException(BaseApiCode.REQUEST_PARAMS_ERROR);
		}
		String mem_id = (String) request.getAttribute("mem_id");
		return goodsDetailService.getItemDetailHttp(itemId, mem_id);
	}
}
