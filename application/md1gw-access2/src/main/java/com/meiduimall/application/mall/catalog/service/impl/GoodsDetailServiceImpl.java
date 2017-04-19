package com.meiduimall.application.mall.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meiduimall.application.mall.catalog.constant.ApplMallApiCode;
import com.meiduimall.application.mall.catalog.constant.ApplMallConstant;
import com.meiduimall.application.mall.catalog.service.GoodsDetailService;
import com.meiduimall.application.mall.catalog.util.HttpGatewayUtils;
import com.meiduimall.exception.ServiceException;

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
	public String getItemDetailHttp(int itemId, String memId) {

		String clientID = env.getProperty(ApplMallConstant.KEY_SIGN_CLIENT_ID);
		String signKey = env.getProperty(ApplMallConstant.KEY_SIGN_KEY);
		String host = env.getProperty(ApplMallConstant.KEY_CATALOG_SERVICE_HOST);
		String uri = ApplMallConstant.SERVICE_CATALOG_BASE_URL + "/goodsDetail/getItem";
		String url = host + uri;

		Map<String, String> params = new HashMap<String, String>();
		params.put("itemId", String.valueOf(itemId));
		if(!StringUtils.isBlank(memId)){
			params.put("memId", memId);
		}

		try {
			return HttpGatewayUtils.sendGet(url, clientID, signKey, params);
		} catch (Exception e) {
			logger.error("请求微服务异常： " + e);
			throw new ServiceException(ApplMallApiCode.REQUEST_SERVICE_ERROR);
		}
	}
}
