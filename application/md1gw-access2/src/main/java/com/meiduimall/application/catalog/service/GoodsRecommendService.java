package com.meiduimall.application.catalog.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.catalog.util.HttpGatewayUtils;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.exception.ServiceException;

/**
 * GoodsRecommendController的网络请求工具类
 * 
 * @author yangchang
 *
 */
@Service
public class GoodsRecommendService {

	private static Logger logger = LoggerFactory.getLogger(GoodsRecommendService.class);

	@Autowired
	private Environment env;

	/**
	 * 根据推荐类型，获取推荐商品
	 * 
	 * @param type
	 *            推荐类型
	 * @param req_id
	 *            请求来源
	 * @return
	 */
	public String getFirstRecommendGoodsHttp(int type, int req_id) {

		String uri = "/mall/catalog-service/v1/goodsRecommend/getFirstRecommend";
		String host = env.getProperty("service.host");
		String clientID = env.getProperty("service.sign-clientID");
		String signKey = env.getProperty("service.sign-key");
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("type", String.valueOf(type));
		params.put("req_id", String.valueOf(req_id));

		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ServiceException(BaseApiCode.REQUEST_SERVICE_ERROR);
		}
	}
}
