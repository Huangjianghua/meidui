package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.constant.ApplicationMallApiCode;
import com.meiduimall.application.mall.catalog.service.GoodsRecommendService;
import com.meiduimall.application.mall.catalog.util.HttpGatewayUtils;
import com.meiduimall.exception.ApiException;

/**
 * GoodsRecommendController的网络请求工具类
 * 
 * @author yangchang
 *
 */
@Service
public class GoodsRecommendServiceImpl implements GoodsRecommendService {

	private static Logger logger = LoggerFactory.getLogger(GoodsRecommendServiceImpl.class);

	@Autowired
	private Environment env;

	/**
	 * 请求微服务，根据推荐类型，获取推荐商品
	 * 
	 * @param type
	 *            推荐类型
	 * @param sourceId
	 *            请求来源
	 * @return
	 */
	@Override
	public String getFirstRecommendGoodsHttp(int type, int sourceId) {

		String uri = "/mall/catalog-service/v1/goodsRecommend/getFirstRecommend";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("type", String.valueOf(type));
		params.put("sourceId", String.valueOf(sourceId));

		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ApiException(ApplicationMallApiCode.REQUEST_SERVICE_ERROR);
		}
	}
}
