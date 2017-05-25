<<<<<<< HEAD
package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.config.ProfileConfig;
import com.meiduimall.application.mall.catalog.constant.MallConstant;
import com.meiduimall.application.mall.catalog.service.GoodsRecommendService;
import com.meiduimall.application.mall.catalog.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;

/**
 * 商品推荐服务
 * 
 * @author yangchang
 *
 */
@Service
public class GoodsRecommendServiceImpl implements GoodsRecommendService {

	@Autowired
	private ProfileConfig profileConfig;

	@Override
	public ResBodyData getFirstRecommendGoodsHttp(int type, int sourceId) {

		String clientID = profileConfig.getClientId();
		String signKey = profileConfig.getSingKey();
		String host = profileConfig.getHost();
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/goodsRecommend/getFirstRecommend";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("type", String.valueOf(type));
		params.put("sourceId", String.valueOf(sourceId));

		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}
}
=======
package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.constant.ApplMallConstant;
import com.meiduimall.application.mall.catalog.service.GoodsRecommendService;
import com.meiduimall.application.mall.catalog.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;

/**
 * GoodsRecommendController的网络请求工具类
 * 
 * @author yangchang
 *
 */
@Service
public class GoodsRecommendServiceImpl implements GoodsRecommendService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(GoodsRecommendServiceImpl.class);

	@Autowired
	private Environment env;

	@Override
	public ResBodyData getFirstRecommendGoodsHttp(int type, int sourceId) {

		String clientID = env.getProperty(ApplMallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(ApplMallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(ApplMallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = ApplMallConstant.SERVICE_CATALOG_BASE_URL + "/goodsRecommend/getFirstRecommend";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("type", String.valueOf(type));
		params.put("sourceId", String.valueOf(sourceId));

		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}
}
>>>>>>> refs/remotes/origin/feature/V4.0.2-Team2
