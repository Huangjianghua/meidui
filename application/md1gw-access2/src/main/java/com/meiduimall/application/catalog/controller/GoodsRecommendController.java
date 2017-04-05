package com.meiduimall.application.catalog.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.meiduimall.application.catalog.util.HttpGatewayUtils;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;

/**
 * 商品推荐相关类
 * @author yangchangfu
 */
@Controller
@RequestMapping("/md1gwmall/md1gw_access/v1/goodsRecommend")
public class GoodsRecommendController {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(GoodsRecommendController.class);

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
			logger.info("根据推荐类型，查询推荐商品，推荐类型：%s", type);

			int reco_type = Integer.parseInt(type);
			int reco_req_id = Integer.parseInt(req_id);
			String result = requestCommendByUtils(reco_type, reco_req_id);

			// 开发环境增加头部
			response.setHeader("Access-Control-Allow-Origin", "*");

			return result;
		} catch (Exception e) {
			logger.error("根据推荐类型，查询推荐商品，服务器异常：%s", e);
			ResBodyData errorBody = new ResBodyData();
			errorBody.setStatus(BaseApiCode.OPERAT_FAIL);
			errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			errorBody.setData(new JSONObject());
			return JSON.toJSONString(errorBody);
		}
	}

	/**
	 * 使用进行网络工具请求
	 * 
	 * @param type
	 * @param req_id
	 * @return
	 * @throws Exception
	 */
	private String requestCommendByUtils(int type, int req_id) throws Exception {

		String uri = "/mall/catalog-service/v1/goodsRecommend/getFirstRecommend";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("type", "" + type);
		params.put("req_id", "" + req_id);
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}
}
