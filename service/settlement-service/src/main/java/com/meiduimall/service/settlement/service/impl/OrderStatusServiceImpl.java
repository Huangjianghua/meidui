package com.meiduimall.service.settlement.service.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
	
	private static final Logger log=LoggerFactory.getLogger(OrderStatusServiceImpl.class);

	@Autowired
	private BaseMapper baseMapper;
	
	@Override
	public boolean updateShareStatus(String orderSn) throws Exception {
		Integer cnt=baseMapper.update(orderSn, "EcmMzfOrderStatusMapper.updateShareStatus");
		return cnt>0?true:false;
	}

	@Override
	public boolean updateScoreStatus(String orderSn) throws Exception {
		Integer cnt=baseMapper.update(orderSn, "EcmMzfOrderStatusMapper.updateScoreStatus");
		return cnt>0?true:false;
	}

	@Override
	public boolean updateBillStatus(EcmMzfOrderStatus orderStatus) throws Exception {
		Integer cnt=baseMapper.update(orderStatus, "EcmMzfOrderStatusMapper.updateBillStatus");
		return cnt>0?true:false;
	}

	@Override
	public boolean updateCashStatus(String orderSn) throws Exception {
		Integer cnt=baseMapper.update(orderSn, "EcmMzfOrderStatusMapper.updateCashStatus");
		return cnt>0?true:false;
	}

	@Override
	public boolean batchUpdCashStatus(Collection<String> orderSns) throws Exception {
		Integer cnt=baseMapper.update(ImmutableMap.of("orderSns", orderSns), "EcmMzfOrderStatusMapper.batchUpdateCashStatus");
		return cnt>0?true:false;
	}

}
