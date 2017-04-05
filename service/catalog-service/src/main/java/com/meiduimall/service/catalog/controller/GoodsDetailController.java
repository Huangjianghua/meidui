package com.meiduimall.service.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.service.GoodsDetailService;
import com.meiduimall.service.catalog.util.HttpTooUtils;
import com.meiduimall.service.catalog.util.Logger;

/**
 * 商品详情相关操作
 * 
 * @author yangchang
 *
 */
@RestController
@RequestMapping("/mall/catalog-service/v1/goodsDetail")
public class GoodsDetailController {

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
			String ip = HttpTooUtils.getIpAddr(request);
			Logger.info("请求IP：%s", ip);
			Logger.info("根据商品编号，查询商品是否存在，商品编号：%s", item_id);

			int id = 0;

			try {
				id = Integer.parseInt(item_id);
			} catch (NumberFormatException e) {
				Logger.error("根据商品编号，查询商品是否存在，商品编号：%s", e);
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData("{}");
				return result;
			}

			return goodsDetailService.checkItemIsExistById(id);

		} catch (Exception e) {
			Logger.error("根据商品编号，查询商品是否存在，服务器异常：%s", e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData("{}");
			return result;
		}
	}

	/**
	 * 根据商品编号，查询商品信息
	 * 
	 * @param item_id
	 *            商品编号，必须传入，否则报错
	 * @return
	 */
	@RequestMapping(value = "/getItem")
	public ResBodyData getItemDetail(String token, String item_id) {
		try {
			String ip = HttpTooUtils.getIpAddr(request);
			Logger.info("请求IP：%s", ip);
			Logger.info("根据商品编号，获取商品详情，商品编号：%s", item_id);

			int id = 0;
			try {
				id = Integer.parseInt(item_id);
			} catch (NumberFormatException e) {
				Logger.error("根据商品编号，获取商品详情，服务器报异常：%s", e);
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData("{}");
				return result;
			}

			return goodsDetailService.getItemDetailById(token, id);

		} catch (Exception e) {
			Logger.error("根据商品编号，获取商品详情，服务器报异常：%s", e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData("{}");
			return result;
		}
	}
}
