package com.meiduimall.service.catalog.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.service.ShopService;
import com.meiduimall.service.catalog.service.common.ShopCommonService;

@Service
public class ShopServiceImpl implements ShopService {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Override
	public ResBodyData getShopDetail(Integer shopId, String token) {
		ResBodyData result = new ResBodyData();// 最终返回的数据对象
		try {
			result.setData(ShopCommonService.getJsonItemDetailResult_ShopData(baseDao, shopId, token));
			result.setStatus(BaseApiCode.SUCCESS);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));
		} catch (Exception e) {
			logger.error("根据商品编号，获取商品详情，service报异常：" + e);
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
		}
		return result;
	}

	@Override
	public ResBodyData cancelOrCollectShop(Integer shopId, String token, int isCollect) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResBodyData getShopProductCatalog(Integer shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResBodyData getShopProductList(Integer shopId, String orderBy, String column) {
		// TODO Auto-generated method stub
		return null;
	}

}
