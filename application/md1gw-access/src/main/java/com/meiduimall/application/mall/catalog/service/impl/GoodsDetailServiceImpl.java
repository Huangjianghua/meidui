package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.service.GoodsDetailService;
import com.meiduimall.application.mall.constant.MallConstant;
import com.meiduimall.application.mall.util.HttpGatewayUtils;
import com.meiduimall.core.ResBodyData;

/**
 * GoodsDetailController的网络请求工具类
 * 
 * @author yangchang
 *
 */
@Service
public class GoodsDetailServiceImpl implements GoodsDetailService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(GoodsDetailServiceImpl.class);

	@Autowired
	private Environment env;

	@Override
	public ResBodyData getItemDetailHttp(int itemId, String memId) {

		String clientID = env.getProperty(MallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(MallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(MallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = MallConstant.SERVICE_CATALOG_BASE_URL + "/goodsDetail/getItem";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("itemId", String.valueOf(itemId));
		if (!StringUtils.isBlank(memId)) {
			params.put("memId", memId);
		}
		return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
	}
}
