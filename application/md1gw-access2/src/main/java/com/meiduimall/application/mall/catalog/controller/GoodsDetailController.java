package com.meiduimall.application.mall.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.mall.catalog.constant.ApplicationMallApiCode;
import com.meiduimall.application.mall.catalog.service.impl.GoodsDetailServiceImpl;
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
	private GoodsDetailServiceImpl goodsDetailService;

	/**
	 * 根据商品itemId获取商品详情
	 * 
	 * @param request
	 * @param itemId
	 *            商品ID
	 * @return
	 */
	@RequestMapping("/getItem")
	public String getItemDetail(HttpServletRequest request, String itemId) {
		int item_id = 0;
		String memId = (String) request.getAttribute("memId");
		try {
			item_id = Integer.parseInt(itemId);
		} catch (NumberFormatException e) {
			logger.error("根据商品item_id获取商品详情，服务器异常：" + e);
			throw new ApiException(ApplicationMallApiCode.REQUEST_PARAMS_ERROR);
		}
		return goodsDetailService.getItemDetailHttp(item_id, memId);
	}
}
