package com.meiduimall.service.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.service.GoodsRecommendService;
import com.meiduimall.service.catalog.util.HttpHeaderTools;

/**
 * 商品推荐相关操作
 * 
 * @author yangchangfu
 *
 */
@RestController
@RequestMapping("/mall/catalog-service/v1/goodsRecommend")
public class GoodsRecommendController {

	private static Logger logger = LoggerFactory.getLogger(GoodsRecommendController.class);

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private GoodsRecommendService goodsRecommendService;

	/**
	 * 批量插入，或者单个插入推荐商品
	 * 
	 * @param item_ids
	 *            商品编号，可以传一个或者多个，不能为空
	 * @param type
	 *            推荐类型，不能为空，1代表支付成功推荐，2代表注册成功推荐
	 * @param opt_user
	 *            操作人
	 * @param level
	 *            推荐等级
	 * @return
	 */
	@RequestMapping(value = "/insertBatch")
	public ResBodyData insertBatchItems(String item_ids, String type, String opt_user,
			@RequestParam(value = "level", required = false, defaultValue = "0") String level) {

		String ip = HttpHeaderTools.getIpAddr(request);
		logger.info("请求IP：" + ip);

		int reco_type = 0;
		int reco_level = 0;

		try {
			reco_type = Integer.parseInt(type);
			reco_level = Integer.parseInt(level);

			if (reco_type != 1 && reco_type != 2) {
				logger.error("批量插入，或者单个插入推荐商品，请求参数错误，type= " + reco_type);
				throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
			}

		} catch (NumberFormatException e) {
			logger.error("批量插入，或者单个插入推荐商品，请求参数错误: " + e);
			throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}

		if (StringUtils.isBlank(item_ids)) {
			logger.error("批量插入，或者单个插入推荐商品，请求参数错误，item_ids: " + item_ids);
			throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		} else {
			String[] str_ids = item_ids.split(",");
			if (str_ids != null && str_ids.length > 0) {
				int[] ids = new int[str_ids.length];
				try {
					for (int i = 0; i < str_ids.length; i++) {
						ids[i] = Integer.parseInt(str_ids[i]);
					}
				} catch (NumberFormatException e) {
					logger.error("批量插入，或者单个插入推荐商品，请求参数错误，item_ids: " + item_ids);
					throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
				}
				return goodsRecommendService.insertBatchItems(ids, reco_type, opt_user, ip, reco_level);
			} else {
				logger.error("批量插入，或者单个插入推荐商品，请求参数错误，item_ids: " + item_ids);
				throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
			}
		}
	}

	/**
	 * 获取推荐商品
	 * 
	 * @param type
	 *            推荐类型，不能为空，1代表支付成功推荐，2代表注册成功推荐
	 * @param count
	 *            推荐个数，默认是4
	 * @param sourceId
	 *            客户端编号，1为手机，2为PC，默认是1
	 * @return
	 */
	@RequestMapping(value = "/getFirstRecommend")
	public ResBodyData getFirstRecommendItems(String type,
			@RequestParam(value = "count", required = false, defaultValue = "4") String count,
			@RequestParam(value = "sourceId", required = false, defaultValue = "1") String sourceId) {

		int reco_type = 0;
		int reco_count = 0;
		int reco_source_id = 0;
		try {
			reco_type = Integer.parseInt(type);
			reco_count = Integer.parseInt(count);
			reco_source_id = Integer.parseInt(sourceId);
		} catch (NumberFormatException e) {
			logger.error("获取推荐商品: " + e);
			throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}

		return goodsRecommendService.getFirstRecommendItems(reco_type, reco_count, reco_source_id);
	}

	/**
	 * 获取正在推荐的商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getFirstRecommendItemId")
	public ResBodyData getFirstRecommendItemsAllType() {
		return goodsRecommendService.getFirstRecommendItemsAllType(4);
	}
}
