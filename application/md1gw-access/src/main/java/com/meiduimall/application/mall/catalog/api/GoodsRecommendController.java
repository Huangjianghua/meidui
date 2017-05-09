package com.meiduimall.application.mall.catalog.api;

import javax.servlet.http.HttpServletResponse;

import com.meiduimall.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.mall.constant.MallApiCode;
import com.meiduimall.application.mall.catalog.service.GoodsRecommendService;
import com.meiduimall.core.ResBodyData;

/**
 * 商品推荐相关类
 * 
 * @author yangchangfu
 */
@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1/goodsRecommend")
public class GoodsRecommendController {

	private static Logger logger = LoggerFactory.getLogger(GoodsRecommendController.class);

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private GoodsRecommendService goodsRecommendService;

	/**
	 * 根据推荐类型，获取推荐商品
	 * 
	 * @param type
	 *            推荐类型
	 * @param sourceId
	 *            请求来源
	 * @return 推荐商品列表
	 */
	@RequestMapping("/getRecommend")
	public ResBodyData getFirstRecommendGoods(String type,
			@RequestParam(value = "sourceId", required = false, defaultValue = "1") String sourceId) {

		int intType = 0;
		int intSourceId = 0;
		try {
			intType = Integer.parseInt(type);
			intSourceId = Integer.parseInt(sourceId);
		} catch (NumberFormatException e) {
			logger.error("根据推荐类型，获取推荐商品，服务器异常：" + e);
			throw new ApiException(MallApiCode.REQUEST_PARAMS_ERROR,
					MallApiCode.getZhMsg(MallApiCode.REQUEST_PARAMS_ERROR));
		}

		ResBodyData result = goodsRecommendService.getFirstRecommendGoodsHttp(intType, intSourceId);
		// 增加头部--解决JS跨域问题
		response.setHeader("Access-Control-Allow-Origin", "*");
		return result;
	}
}
