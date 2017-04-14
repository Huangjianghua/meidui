package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.service.GoodsDetailService;
import com.meiduimall.application.mall.catalog.util.HttpGatewayUtils;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.exception.ApiException;

/**
 * GoodsDetailController的网络请求工具类
 * 
 * @author yangchang
 *
 */
@Service
public class GoodsDetailServiceImpl implements GoodsDetailService {

	private static Logger logger = LoggerFactory.getLogger(GoodsDetailServiceImpl.class);

	@Autowired
	private Environment env;

	@Override
	public String getItemDetailHttp(int item_id, String mem_id) {

		String uri = "/mall/catalog-service/v1/goodsDetail/getItem";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("item_id", String.valueOf(item_id));
		params.put("mem_id", "" + mem_id);

		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ApiException(BaseApiCode.REQUEST_SERVICE_ERROR);
		}
	}
}
