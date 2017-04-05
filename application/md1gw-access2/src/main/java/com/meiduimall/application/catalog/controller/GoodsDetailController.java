package com.meiduimall.application.catalog.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.catalog.util.HttpGatewayUtils;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;

/**
 * 商品详情相关类
 * @author yangchangfu
 */
@Controller
@RequestMapping("/md1gwmall/md1gw_access/v1/goodsDetail")
public class GoodsDetailController {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(GoodsDetailController.class);

	@Autowired
	private Environment env;

	@RequestMapping("/getItem")
	public @ResponseBody String getItemDetail(String item_id, String token) {
		try {
			logger.info("请求商品详情，商品item_id：%s", item_id);
			int id = Integer.parseInt(item_id);
			String result = requestDataByUtils(id, token);
			return result;
		} catch (Exception e) {
			logger.error("请求商品详情，服务器异常：%s", e);
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
	private String requestDataByUtils(int item_id, String token) throws Exception {

		String uri = "/mall/catalog-service/v1/goodsDetail/getItem";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("item_id", "" + item_id);
		params.put("token", "" + token);
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}

}
