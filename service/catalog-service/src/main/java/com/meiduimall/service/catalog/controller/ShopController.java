package com.meiduimall.service.catalog.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.service.ShopService;
import com.meiduimall.service.catalog.util.StringUtil;

@RestController
@RequestMapping("/mall/catalog-service/v1/shopInfo")
public class ShopController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private ShopService shopService;

	/**
	 * 获取店铺详情
	 * 
	 * @param shop_id
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/getShopDetail")
	public ResBodyData getShopDetail(String shop_id, String token) {
		try {
			logger.error("根据店铺ID，查询店铺详细信息，店铺ID：" + shop_id);

			int shopId = 0;
			try {
				shopId = Integer.parseInt(shop_id);
			} catch (Exception e) {
				logger.error("根据店铺ID，查询店铺详细信息，店铺ID错误：" + e);
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData(new JSONObject());
				return result;
			}
			return shopService.getShopDetail(shopId, token);
		} catch (Exception e) {
			logger.error("根据店铺ID，查询店铺详细信息，服务器异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
			return result;
		}
	}

	/**
	 * 收藏或者取消收藏店铺
	 * 
	 * @param shop_id
	 * @param token
	 * @param is_collect
	 *            1代表收藏，0代表取消收藏
	 * @return
	 */
	@RequestMapping(value = "/collectShop")
	public ResBodyData cancelOrCollectShop(String shop_id, String token, String is_collect) {
		try {
			logger.error("收藏店铺：" + shop_id + token + is_collect);

			if (StringUtil.isEmptyByString(token)) {
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.NO_LOGIN);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NO_LOGIN));
				result.setData(new JSONObject());
				return result;
			}

			int shopId = 0;
			int isCollect = 0;
			try {
				shopId = Integer.parseInt(shop_id);
				isCollect = Integer.parseInt(is_collect);
			} catch (Exception e) {
				logger.error("收藏店铺，服务器异常：" + e);
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData(new JSONObject());
				return result;
			}
			return shopService.cancelOrCollectShop(shopId, token, isCollect);
		} catch (Exception e) {
			logger.error("收藏店铺，服务器异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
			return result;
		}
	}

	/**
	 * 获取店铺商品分类
	 * 
	 * @param shop_id
	 * @return
	 */
	@RequestMapping(value = "/getShopCatalog")
	public ResBodyData getShopProductCatalog(String shop_id) {
		try {
			logger.error("获取店铺商品分类，店铺ID：" + shop_id);
			int shopId = 0;
			try {
				shopId = Integer.parseInt(shop_id);
			} catch (Exception e) {
				logger.error("获取店铺商品分类，店铺ID错误：" + e);
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData(new JSONObject());
				return result;
			}
			return shopService.getShopProductCatalog(shopId);
		} catch (Exception e) {
			logger.error("获取店铺商品分类，服务器异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
			return result;
		}
	}

	/**
	 * 获取店铺列表
	 * 
	 * @param shop_id
	 * @param order_by
	 *            排序字段：store按销量，updateTime按修改时间，price按价格，point按积分；默认store按销量
	 * @param column
	 *            排序规则：desc降序，asc升序；默认desc降序
	 * @return
	 */
	@RequestMapping(value = "/getProductList")
	public ResBodyData getShopProductList(String shop_id, String order_by, String column) {
		try {
			logger.error("获取店铺商品列表，店铺ID：" + shop_id);
			int shopId = 0;
			try {
				shopId = Integer.parseInt(shop_id);
			} catch (Exception e) {
				logger.error("获取店铺商品列表，店铺ID错误：" + e);
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData(new JSONObject());
				return result;
			}
			return shopService.getShopProductList(shopId, order_by, column);
		} catch (Exception e) {
			logger.error("获取店铺商品列表，服务器异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
			return result;
		}
	}
}
