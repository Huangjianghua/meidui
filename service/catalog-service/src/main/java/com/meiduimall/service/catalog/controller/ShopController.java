package com.meiduimall.service.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.catalog.annotation.HasMemId;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.request.ShopProductRequest;
import com.meiduimall.service.catalog.service.ShopService;

@RestController
@RequestMapping("/mall/catalog-service/v1/shopInfo")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private HttpServletRequest request;

	/**
	 * 获取店铺详情
	 * 
	 * @param shop_id
	 * @param mem_id
	 *            会员系统ID
	 * @return
	 */
	@RequestMapping(value = "/getShopDetail")
	public ResBodyData getShopDetail(String shop_id, String mem_id) {

		int shopId = 0;
		try {
			shopId = Integer.parseInt(shop_id);
		} catch (NumberFormatException e) {
			throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}
		return shopService.getShopDetail(shopId, mem_id);

	}

	/**
	 * 收藏或者取消收藏店铺
	 * 
	 * @HasMemId 验证memId是否有效，并通过request传递SysuserAccount对象
	 * 
	 * @param shop_id
	 *            店铺ID
	 * @param is_collect
	 *            1代表收藏，0代表取消收藏
	 * @return
	 */
	@HasMemId
	@RequestMapping(value = "/collectShop")
	public ResBodyData collectOrCancelShop(String shop_id, String is_collect) {

		int shopId = 0;
		int isCollect = 0;
		try {
			shopId = Integer.parseInt(shop_id);
			isCollect = Integer.parseInt(is_collect);
		} catch (NumberFormatException e) {
			throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}
		SysuserAccount sysuserAccount = (SysuserAccount) request.getAttribute("sysuserAccount");
		return shopService.collectOrCancelShop(shopId, sysuserAccount, isCollect);
	}

	/**
	 * 获取店铺商品分类
	 * 
	 * @param shop_id
	 * @return
	 */
	@RequestMapping(value = "/getShopCatalog")
	public ResBodyData getShopProductCatalog(String shop_id) {

		int shopId = 0;
		try {
			shopId = Integer.parseInt(shop_id);
		} catch (NumberFormatException e) {
			throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}
		return shopService.getShopProductCatalog(shopId);
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
