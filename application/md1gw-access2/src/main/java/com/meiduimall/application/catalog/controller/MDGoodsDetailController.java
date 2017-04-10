package com.meiduimall.application.catalog.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.catalog.controller.http.MDGoodsDetailControllerHttp;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;

/**
 * 商品详情相关类
 * 
 * @author yangchangfu
 */
@Controller
@RequestMapping("/md1gwmall/md1gw_access/v1/goodsDetail")
public class MDGoodsDetailController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MDGoodsDetailController.class);

	@Autowired
	private Environment env;

	/**
	 * 根据商品item_id获取商品详情
	 * 
	 * @param item_id
	 * @param mem_id
	 * @return
	 */
	@RequestMapping("/getItem")
	public @ResponseBody String getItemDetail(String item_id, String mem_id) {
		try {
			logger.info("请求商品详情，商品item_id：" + item_id);

			int id = 0;
			try {
				id = Integer.parseInt(item_id);
			} catch (Exception e) {
				logger.error("请求商品详情，服务器异常：" + e);
				ResBodyData errorBody = new ResBodyData();
				errorBody.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				errorBody.setData(new JSONObject());
				return JSON.toJSONString(errorBody);
			}

			return MDGoodsDetailControllerHttp.getItemDetailHttp(env, id, mem_id);
		} catch (Exception e) {
			logger.error("请求商品详情，服务器异常：" + e);
			ResBodyData errorBody = new ResBodyData();
			errorBody.setStatus(BaseApiCode.OPERAT_FAIL);
			errorBody.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			errorBody.setData(new JSONObject());
			return JSON.toJSONString(errorBody);
		}
	}

}
