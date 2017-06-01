package com.meiduimall.application.mall.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import com.meiduimall.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.mall.catalog.annotation.HasToken;
import com.meiduimall.application.mall.catalog.constant.MallApiCode;
import com.meiduimall.application.mall.catalog.request.ShopProductRequest;
import com.meiduimall.application.mall.catalog.service.ShopService;
import com.meiduimall.core.ResBodyData;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1/shopInfo")
public class ShopController {

	private static Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ShopService shopService;

	/**
	 * 根据店铺shopId，获取店铺详情
	 * 
	 * @param shopId
	 *            店铺ID
	 * @return 店铺详情
	 */
	@RequestMapping(value = "/getShopDetail")
	public ResBodyData getShopDetail(String shopId) {
		int intShopId = 0;
		String memId = (String) request.getAttribute("memId");
		try {
			intShopId = Integer.parseInt(shopId);
		} catch (NumberFormatException e) {
			logger.error("根据店铺shop_id，获取店铺详情，服务器异常：" + e);
			throw new ApiException(MallApiCode.REQUEST_PARAMS_ERROR,
					MallApiCode.getZhMsg(MallApiCode.REQUEST_PARAMS_ERROR));
		}
		return shopService.getShopDetailHttp(intShopId, memId);
	}

	/**
	 * 收藏店铺或者取消收藏
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param isCollect
	 *            收藏1，取消收藏0
	 * @return 收藏/取消收藏结果
	 */
	@HasToken
	@RequestMapping(value = "/collectShop")
	public ResBodyData collectOrCancelShop(String shopId, String isCollect) {
		int intShopId = 0;
		int intIsCollect = 0;
		String memId = (String) request.getAttribute("memId");
		try {
			intShopId = Integer.parseInt(shopId);
			intIsCollect = Integer.parseInt(isCollect);
		} catch (NumberFormatException e) {
			logger.error("收藏店铺或者取消收藏，服务器异常：" + e);
			throw new ApiException(MallApiCode.REQUEST_PARAMS_ERROR,
					MallApiCode.getZhMsg(MallApiCode.REQUEST_PARAMS_ERROR));
		}
		return shopService.collectOrCancelShopHttp(intShopId, intIsCollect, memId);
	}

	/**
	 * 获取商家自定义商品分类列表
	 * 
	 * @param shopId
	 *            店铺ID
	 * @return 商品分类列表
	 */
	@RequestMapping(value = "/getShopCatalog")
	public ResBodyData getShopProductCatalog(String shopId) {
		int intShopId = 0;
		try {
			intShopId = Integer.parseInt(shopId);
		} catch (NumberFormatException e) {
			logger.error("获取商家自定义商品分类列表，服务器异常：" + e);
			throw new ApiException(MallApiCode.REQUEST_PARAMS_ERROR,
					MallApiCode.getZhMsg(MallApiCode.REQUEST_PARAMS_ERROR));
		}
		return shopService.getShopProductCatalogHttp(intShopId);
	}

	/**
	 * 获取店铺的商品列表
	 * 
	 * @param param
	 *            请求参数封装ShopProductRequest对象
	 * @return 商品列表
	 */
	@RequestMapping(value = "/getProductList")
	public ResBodyData getShopProductList(@Validated ShopProductRequest param) {
		return shopService.getShopProductList(param);
	}
}
