package com.meiduimall.application.catalog.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.catalog.controller.http.MDGoodsRecommendControllerHttp;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;

/**
 * 商品推荐相关类
 * 
 * @author yangchangfu
 */
@Controller
@RequestMapping("/md1gwmall/md1gw_access/v1/goodsRecommend")
public class MDGoodsRecommendController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MDGoodsRecommendController.class);

	@Autowired
	private Environment env;

	@Autowired
	private HttpServletResponse response;

	/**
	 * 根据推荐类型，获取推荐商品
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/getRecommend")
	public @ResponseBody String getFirstRecommendGoods(String type,
			@RequestParam(value = "req_id", required = false, defaultValue = "1") String req_id) {
		try {
			logger.info("根据推荐类型，查询推荐商品，推荐类型：" + type);

			int reco_type = 0;
			int reco_req_id = 0;

			try {
				reco_type = Integer.parseInt(type);
				reco_req_id = Integer.parseInt(req_id);
			} catch (Exception e) {
				logger.error("根据推荐类型，查询推荐商品，服务器异常：" + e);
				ResBodyData errorBody = new ResBodyData();
				errorBody.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				errorBody.setData(new JSONObject());
				return JSON.toJSONString(errorBody);
			}

			String result = MDGoodsRecommendControllerHttp.getFirstRecommendGoodsHttp(env, reco_type, reco_req_id);

			// 增加头部--解决JS跨域问题
			response.setHeader("Access-Control-Allow-Origin", "*");

			return result;

		} catch (Exception e) {
			logger.error("根据推荐类型，查询推荐商品，服务器异常：" + e);
			ResBodyData errorBody = new ResBodyData();
			errorBody.setStatus(BaseApiCode.OPERAT_FAIL);
			errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			errorBody.setData(new JSONObject());
			return JSON.toJSONString(errorBody);
		}
	}

}
