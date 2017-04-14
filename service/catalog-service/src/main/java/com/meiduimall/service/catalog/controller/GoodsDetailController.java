package com.meiduimall.service.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.service.GoodsDetailService;

/**
 * 商品详情相关操作
 * 
 * @author yangchangfu
 *
 */
@RestController
@RequestMapping("/mall/catalog-service/v1/goodsDetail")
public class GoodsDetailController {

	@Autowired
	private GoodsDetailService goodsDetailService;

	/**
	 * 根据商品编号，查询商品是否存在
	 * 
	 * @param item_id
	 *            商品编号，必须
	 * @return
	 */
	@RequestMapping(value = "/isExist")
	public ResBodyData checkItemIsExist(String item_id) {
		int id = 0;
		try {
			id = Integer.parseInt(item_id);
		} catch (NumberFormatException e) {
			throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}
		return goodsDetailService.checkItemIsExistById(id);
	}

	/**
	 * 根据商品编号，查询商品详情
	 * 
	 * @param mem_id
	 *            会员系统ID
	 * @param item_id
	 *            商品编号，必须
	 * @return
	 */
	@RequestMapping(value = "/getItem")
	public ResBodyData getItemDetail(String mem_id, String item_id) {
		int id = 0;
		try {
			id = Integer.parseInt(item_id);
		} catch (NumberFormatException e) {
			throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}
		return goodsDetailService.getItemDetailById(mem_id, id);
	}
}
