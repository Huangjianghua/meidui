package com.meiduimall.service.catalog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.result.OrderTypeCountResult;
import com.meiduimall.service.catalog.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private BaseDao baseDao;

	@Override
	public ResBodyData getEveryOrderTypeCount(SysuserAccount sysuserAccount) {

		OrderTypeCountResult data = new OrderTypeCountResult();

		Integer userId = sysuserAccount.getUserId();
		// 查询用户的购物车商品数量
		Integer cartNum = baseDao.selectOne(userId, "SystradeCartMapper.selectCartNumByUserId");
		if (cartNum != null) {
			data.setCartNum(cartNum);
		}
		// 查询待付款订单数量
		Integer waitPay = baseDao.selectOne(userId, "SystradePTradeMapper.selectWaitPayCountByUserId");
		if (waitPay != null) {
			data.setWaitPay(waitPay);
		}
		// 查询待发货数量
		Integer waitDelivery = baseDao.selectOne(userId, "SystradeTradeMapper.selectWaitDeliveryCountByUserId");
		if (waitDelivery != null) {
			data.setWaitDelivery(waitDelivery);
		}
		// 查询待收货数量
		Integer waitEnter = baseDao.selectOne(userId, "SystradeTradeMapper.selectWaitEnterCountByUserId");
		if (waitEnter != null) {
			data.setWaitEnter(waitEnter);
		}
		// 查询待评价订单数量
		Map<String, Integer> map = new HashMap<>();
		map.put("userId", userId);
		map.put("time", new Long((System.currentTimeMillis() / 1000 - 15 * 24 * 3600l)).intValue());
		List<String> tradeList = baseDao.selectList(map, "SystradeOrderMapper.selectWaitRateTradeByUserId");
		if (tradeList != null) {
			data.setWaitRate(tradeList.size());
		}

		ResBodyData result = new ResBodyData();
		result.setData(data);
		result.setStatus(ServiceCatalogApiCode.SUCCESS);
		result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_SUCCESS));
		return result;
	}

}
