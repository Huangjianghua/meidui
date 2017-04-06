package com.meiduimall.service.catalog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.HttpTools;
import com.meiduimall.service.catalog.service.GoodsRecommendService;

/**
 * 商品推荐相关操作
 * 
 * @author yangchangfu
 *
 */
@RestController
@RequestMapping("/mall/catalog-service/v1/goodsRecommend")
public class GoodsRecommendController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(GoodsRecommendController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private GoodsRecommendService goodsRecommendService;

	/**
	 * 批量插入，或者单个插入推荐商品
	 * 
	 * @param item_id
	 *            商品编号，可以传一个或者多个，不能为空
	 * @param type
	 *            推荐类型，不能为空，1代表支付成功推荐，2代表注册成功推荐
	 * @param opt_user
	 *            操作人
	 * @param reco_level
	 *            推荐等级
	 * @return
	 */
	@RequestMapping(value = "/insertBatch")
	public ResBodyData insertBatchItems(String item_ids, String type, String opt_user,
			@RequestParam(value = "level", required = false, defaultValue = "0") String level) {
		try {
			String ip = HttpTools.getIpAddr(request);
			logger.info("请求IP：" + ip);
			logger.info("批量插入推荐商品，商品编号：" + item_ids);

			int reco_type = 0;
			int reco_level = 0;

			try {
				reco_type = Integer.parseInt(type);
				reco_level = Integer.parseInt(level);
			} catch (NumberFormatException e) {
				logger.error("批量插入推荐商品，商品编号：" + e);
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData(new JSONObject());
				return result;
			}

			if (StringUtils.isEmpty(item_ids)) {
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData(new JSONObject());
				return result;
			} else {
				String[] str_ids = item_ids.split(",");
				if (str_ids != null && str_ids.length > 0) {
					int[] ids = new int[str_ids.length];
					for (int i = 0; i < str_ids.length; i++) {
						ids[i] = Integer.parseInt(str_ids[i]);
					}
					return goodsRecommendService.insertBatchItems(ids, reco_type, opt_user, ip, reco_level);
				} else {
					ResBodyData result = new ResBodyData();
					result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
					result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
					result.setData(new JSONObject());
					return result;
				}
			}

		} catch (Exception e) {
			logger.error("批量插入推荐商品，服务器异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
			return result;
		}
	}

	/**
	 * 获取推荐商品
	 * 
	 * @param type
	 *            推荐类型，不能为空，1代表支付成功推荐，2代表注册成功推荐
	 * @param count
	 *            推荐个数，默认是4
	 * @param req_id
	 *            客户端编号，1为手机，2为PC，默认是1
	 * @return
	 */
	@RequestMapping(value = "/getFirstRecommend")
	public ResBodyData getFirstRecommendItems(String type,
			@RequestParam(value = "count", required = false, defaultValue = "4") String count,
			@RequestParam(value = "req_id", required = false, defaultValue = "1") String req_id) {
		try {
			String ip = HttpTools.getIpAddr(request);
			logger.info("请求IP：" + ip);
			logger.info("获取推荐商品，推荐类型：" + type);

			int reco_type = 0;
			int reco_count = 0;
			int reco_req_id = 0;

			try {
				reco_type = Integer.parseInt(type);
				reco_count = Integer.parseInt(count);
				reco_req_id = Integer.parseInt(req_id);
			} catch (NumberFormatException e) {
				logger.error("获取推荐商品，推荐类型：" + e);
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData(new JSONObject());
				return result;
			}

			return goodsRecommendService.getFirstRecommendItems(reco_type, reco_count, reco_req_id);

		} catch (Exception e) {
			logger.error("获取推荐商品，服务器异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
			return result;
		}
	}

	/**
	 * 获取正在推荐的商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getFirstRecommendItemId")
	public ResBodyData getFirstRecommendItemsAllType() {
		try {
			logger.info("获取当前正在显示的推荐商品，不分类型推荐类型：" + 4);
			return goodsRecommendService.getFirstRecommendItemsAllType(4);
		} catch (Exception e) {
			logger.error("获取推荐商品，服务器异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
			return result;
		}
	}
}
