package com.meiduimall.application.mall.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.mall.catalog.annotation.HasToken;
import com.meiduimall.application.mall.catalog.constant.ApplicationMallApiCode;
import com.meiduimall.application.mall.catalog.request.ShopProductRequest;
import com.meiduimall.application.mall.catalog.service.impl.ShopServiceImpl;
import com.meiduimall.exception.ApiException;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1/shopInfo")
public class ShopController {

	private static Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ShopServiceImpl shopService;

	/**
	 * 根据店铺shopId，获取店铺详情
	 * 
	 * @param shopId
	 *            店铺ID
	 * @return
	 */
	@RequestMapping(value = "/getShopDetail")
	public String getShopDetail(String shopId) {
		int shop_id = 0;
		String memId = (String) request.getAttribute("memId");
		try {
			shop_id = Integer.parseInt(shopId);
		} catch (NumberFormatException e) {
			logger.error("根据店铺shop_id，获取店铺详情，服务器异常：" + e);
			throw new ApiException(ApplicationMallApiCode.REQUEST_PARAMS_ERROR);
		}
		return shopService.getShopDetailHttp(shop_id, memId);
	}

	/**
	 * 收藏店铺或者取消收藏
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param isCollect
	 *            收藏1，取消收藏0
	 * @return
	 */
	@HasToken
	@RequestMapping(value = "/collectShop")
	public String collectOrCancelShop(String shopId, String isCollect) {
		int shop_id = 0;
		int is_collect = 0;
		String memId = (String) request.getAttribute("memId");
		try {
			shop_id = Integer.parseInt(shopId);
			is_collect = Integer.parseInt(isCollect);
		} catch (NumberFormatException e) {
			logger.error("收藏店铺或者取消收藏，服务器异常：" + e);
			throw new ApiException(ApplicationMallApiCode.REQUEST_PARAMS_ERROR);
		}
		return shopService.collectOrCancelShopHttp(shop_id, is_collect, memId);
	}

	/**
	 * 获取商家自定义商品分类列表
	 * 
	 * @param shopId
	 *            店铺ID
	 * @return
	 */
	@RequestMapping(value = "/getShopCatalog")
	public String getShopProductCatalog(String shopId) {
		int shop_id = 0;
		try {
			shop_id = Integer.parseInt(shopId);
		} catch (NumberFormatException e) {
			logger.error("获取商家自定义商品分类列表，服务器异常：" + e);
			throw new ApiException(ApplicationMallApiCode.REQUEST_PARAMS_ERROR);
		}
		return shopService.getShopProductCatalogHttp(shop_id);
	}

	/**
	 * 获取店铺的商品列表
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getProductList")
	public String getShopProductList(@Validated ShopProductRequest param) {
		return shopService.getShopProductList(param);
	}
}
