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
 * GoodsDetailController的网络请求工具类
 * 
 * @author yangchang
 *
 */
@Service
public class GoodsDetailService {

	private static Logger logger = LoggerFactory.getLogger(GoodsDetailService.class);

	@Autowired
	private Environment env;

	/**
	 * 根据商品item_id获取商品详情
	 * 
	 * @param item_id
	 *            商品ID
	 * @param mem_id
	 *            会员系统ID
	 * @return
	 */
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
			throw new ServiceException(BaseApiCode.REQUEST_SERVICE_ERROR);
		}
	}
}
