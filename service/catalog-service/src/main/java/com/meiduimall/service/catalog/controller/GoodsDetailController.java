package com.meiduimall.service.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.service.GoodsDetailService;
import com.meiduimall.service.catalog.util.HttpTools;

/**
 * 商品详情相关操作
 * 
 * @author yangchangfu
 *
 */
@RestController
@RequestMapping("/mall/catalog-service/v1/goodsDetail")
public class GoodsDetailController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(GoodsDetailController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private GoodsDetailService goodsDetailService;

	/**
	 * 根据商品编号，查询商品是否存在
	 * 
	 * @param item_id
	 *            商品编号，必须传入，否则报错
	 * @return
	 */
	@RequestMapping(value = "/isExist")
	public ResBodyData checkItemIsExist(String item_id) {
		try {
			String ip = HttpTools.getIpAddr(request);
			logger.info("请求IP：" + ip);
			logger.info("根据商品编号，查询商品是否存在，商品编号：" + item_id);

			int id = 0;

			try {
				id = Integer.parseInt(item_id);
			} catch (NumberFormatException e) {
				logger.error("根据商品编号，查询商品是否存在，商品编号：" + e);
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData(new JSONObject());
				return result;
			}

			return goodsDetailService.checkItemIsExistById(id);

		} catch (Exception e) {
			logger.error("根据商品编号，查询商品是否存在，服务器异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
			return result;
		}
	}

	/**
	 * 根据商品编号，查询商品详情
	 * 
	 * @param mem_id 会员系统ID
	 * @param item_id
	 *            商品编号，必须传入，否则报错
	 * @return
	 */
	@RequestMapping(value = "/getItem")
	public ResBodyData getItemDetail(String mem_id, String item_id) {
		try {
			String ip = HttpTools.getIpAddr(request);
			logger.info("请求IP：" + ip);
			logger.info("根据商品编号，获取商品详情，商品编号：" + item_id);

			int id = 0;
			try {
				id = Integer.parseInt(item_id);
			} catch (NumberFormatException e) {
				logger.error("根据商品编号，获取商品详情，服务器报异常：" + e);
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData(new JSONObject());
				return result;
			}

			return goodsDetailService.getItemDetailById(mem_id, id);

		} catch (Exception e) {
			logger.error("根据商品编号，获取商品详情，服务器报异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
			return result;
		}
	}
}
