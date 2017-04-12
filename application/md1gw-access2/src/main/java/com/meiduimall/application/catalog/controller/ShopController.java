package com.meiduimall.application.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.catalog.annotation.HasToken;
import com.meiduimall.application.catalog.service.ShopService;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.exception.ApiException;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1/shopInfo")
public class ShopController {

	private static Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private ShopService shopService;

	/**
	 * 根据店铺shop_id，获取店铺详情
	 * 
	 * @param request
	 * @param shop_id
	 *            店铺ID
	 * @return
	 */
	@RequestMapping(value = "/getShopDetail")
	public String getShopDetail(HttpServletRequest request, String shop_id) {
		int shopId = 0;
		try {
			shopId = Integer.parseInt(shop_id);
		} catch (NumberFormatException e) {
			logger.error("根据店铺shop_id，获取店铺详情，服务器异常：" + e);
			throw new ApiException(BaseApiCode.REQUEST_PARAMS_ERROR);
		}
		String mem_id = (String) request.getAttribute("mem_id");
		return shopService.getShopDetailHttp(shopId, mem_id);
	}

	/**
	 * 收藏店铺或者取消收藏
	 * 
	 * @param request
	 * @param shop_id
	 *            店铺ID
	 * @param is_collect
	 *            收藏1，取消收藏0
	 * @return
	 */
	@HasToken
	@RequestMapping(value = "/collectShop")
	public String collectOrCancelShop(HttpServletRequest request, String shop_id, String is_collect) {
		int shopId = 0;
		int isCollect = 0;
		try {
			shopId = Integer.parseInt(shop_id);
			isCollect = Integer.parseInt(is_collect);
		} catch (NumberFormatException e) {
			logger.error("收藏店铺或者取消收藏，服务器异常：" + e);
			throw new ApiException(BaseApiCode.REQUEST_PARAMS_ERROR);
		}
		String mem_id = (String) request.getAttribute("mem_id");
		return shopService.collectOrCancelShopHttp(shopId, isCollect, mem_id);
	}

	/**
	 * 获取商家自定义商品分类列表
	 * 
	 * @param shop_id
	 *            店铺ID
	 * @return
	 */
	@RequestMapping(value = "/getShopCatalog")
	public String getShopProductCatalog(String shop_id) {
		int shopId = 0;
		try {
			shopId = Integer.parseInt(shop_id);
		} catch (NumberFormatException e) {
			logger.error("获取商家自定义商品分类列表，服务器异常：" + e);
			throw new ApiException(BaseApiCode.REQUEST_PARAMS_ERROR);
		}
		return shopService.getShopProductCatalogHttp(shopId);
	}
}
