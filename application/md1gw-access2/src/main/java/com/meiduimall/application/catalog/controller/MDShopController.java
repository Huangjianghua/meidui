package com.meiduimall.application.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.catalog.annotation.HasToken;
import com.meiduimall.application.catalog.controller.http.MDShopControllerHttp;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1/shopInfo")
public class MDShopController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MDGoodsDetailController.class);

	@Autowired
	private Environment env;
	
	@Autowired
	private HttpServletRequest request;

	/**
	 * 根据店铺shop_id，获取店铺详情
	 * 
	 * @param shop_id
	 * @param mem_id
	 * @return
	 */
	@RequestMapping(value = "/getShopDetail")
	public String getShopDetail(String shop_id) {
		try {
			logger.info("请求店铺详情，店铺shop_id：" + shop_id);
			int shopId = 0;
			try {
				shopId = Integer.parseInt(shop_id);
			} catch (Exception e) {
				logger.error("请求店铺详情，服务器异常：" + e);
				ResBodyData errorBody = new ResBodyData();
				errorBody.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				errorBody.setData(new JSONObject());
				return JSON.toJSONString(errorBody);
			}
			
			String mem_id = (String) request.getAttribute("mem_id");
			
			return MDShopControllerHttp.getShopDetailHttp(env, shopId, mem_id);
		} catch (Exception e) {
			logger.error("请求店铺详情，服务器异常：" + e);
			ResBodyData errorBody = new ResBodyData();
			errorBody.setStatus(BaseApiCode.OPERAT_FAIL);
			errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			errorBody.setData(new JSONObject());
			return JSON.toJSONString(errorBody);
		}
	}

	/**
	 * 
	 * @param shop_id
	 * @param is_collect
	 * @param mem_id
	 * @return
	 */
	@HasToken
	@RequestMapping(value = "/collectShop")
	public String collectOrCancelShop(String shop_id, String is_collect) {
		try {
			logger.info("收藏或者取消收藏，店铺shop_id：" + shop_id);
			int shopId = 0;
			int isCollect = 0;
			try {
				shopId = Integer.parseInt(shop_id);
				isCollect = Integer.parseInt(is_collect);
			} catch (Exception e) {
				logger.error("收藏或者取消收藏，服务器异常：" + e);
				ResBodyData errorBody = new ResBodyData();
				errorBody.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				errorBody.setData(new JSONObject());
				return JSON.toJSONString(errorBody);
			}
			
			String mem_id = (String) request.getAttribute("mem_id");
			
			return MDShopControllerHttp.collectOrCancelShopHttp(env, shopId, isCollect, mem_id);

		} catch (Exception e) {
			logger.error("收藏或者取消收藏，服务器异常：" + e);
			ResBodyData errorBody = new ResBodyData();
			errorBody.setStatus(BaseApiCode.OPERAT_FAIL);
			errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			errorBody.setData(new JSONObject());
			return JSON.toJSONString(errorBody);
		}
	}

	/**
	 * 请求商家自定义商品分类列表
	 * 
	 * @param shop_id
	 * @return
	 */
	@RequestMapping(value = "/getShopCatalog")
	public String getShopProductCatalog(String shop_id) {
		try {
			logger.info("请求店铺自定义商品分类，店铺shop_id：" + shop_id);
			int shopId = 0;
			try {
				shopId = Integer.parseInt(shop_id);
			} catch (Exception e) {
				logger.error("请求店铺自定义商品分类，服务器异常：" + e);
				ResBodyData errorBody = new ResBodyData();
				errorBody.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				errorBody.setData(new JSONObject());
				return JSON.toJSONString(errorBody);
			}
			return MDShopControllerHttp.getShopProductCatalogHttp(env, shopId);
		} catch (Exception e) {
			logger.error("请求店铺自定义商品分类，服务器异常：" + e);
			ResBodyData errorBody = new ResBodyData();
			errorBody.setStatus(BaseApiCode.OPERAT_FAIL);
			errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			errorBody.setData(new JSONObject());
			return JSON.toJSONString(errorBody);
		}
	}
}
