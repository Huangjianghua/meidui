package com.meiduimall.service.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.service.catalog.annotation.HasMemId;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.request.ShopProductRequest;
import com.meiduimall.service.catalog.service.ShopService;

@RestController
@RequestMapping("/mall/catalog-service/v1/shopInfo")
public class ShopController {

	private static Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ShopService shopService;

	/**
	 * 获取店铺详情
	 * 
	 * @param shopId
	 * @param memId
	 *            会员系统ID
	 * @return
	 */
	@RequestMapping(value = "/getShopDetail")
	public ResBodyData getShopDetail(String shopId, String memId) {

		int shop_id = 0;
		try {
			shop_id = Integer.parseInt(shopId);
		} catch (NumberFormatException e) {
			logger.error("获取店铺详情: " + e);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}
		return shopService.getShopDetail(shop_id, memId);

	}

	/**
	 * 收藏或者取消收藏店铺
	 * 
	 * @param shopId
	 *            店铺ID
	 * @param isCollect
	 *            1代表收藏，0代表取消收藏
	 * @return
	 */
	@HasMemId
	@RequestMapping(value = "/collectShop")
	public ResBodyData collectOrCancelShop(String shopId, String isCollect) {

		int shop_id = 0;
		int is_collect = 0;
		try {
			shop_id = Integer.parseInt(shopId);
			is_collect = Integer.parseInt(isCollect);
		} catch (NumberFormatException e) {
			logger.error("收藏或者取消收藏店铺: " + e);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}
		SysuserAccount sysuserAccount = (SysuserAccount) request.getAttribute("sysuserAccount");
		return shopService.collectOrCancelShop(shop_id, sysuserAccount, is_collect);
	}

	/**
	 * 获取店铺商品分类
	 * 
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/getShopCatalog")
	public ResBodyData getShopProductCatalog(String shopId) {

		int shop_id = 0;
		try {
			shop_id = Integer.parseInt(shopId);
		} catch (NumberFormatException e) {
			logger.error("获取店铺商品分类: " + e);
			throw new ApiException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}
		return shopService.getShopProductCatalog(shop_id);
	}

	/**
	 * 获取店铺的商品列表
	 * 
	 * @param param
	 *            请求参数封装对象
	 * @return
	 */
	@RequestMapping(value = "/getProductList")
	public ResBodyData getShopProductList(@Validated ShopProductRequest param) {
		return shopService.getShopProductList(param);
	}
}
