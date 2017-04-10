package com.meiduimall.service.settlement.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
	
	@Autowired
	private BaseMapper baseMapper;
	
	@Override
	public boolean updateShareStatus(String orderSn) throws ServiceException {
		Integer cnt=baseMapper.update(orderSn, "EcmMzfOrderStatusMapper.updateShareStatus");
		return cnt>0?true:false;
	}

	@Override
	public boolean updateScoreStatus(String orderSn) throws ServiceException {
		Integer cnt=baseMapper.update(orderSn, "EcmMzfOrderStatusMapper.updateScoreStatus");
		return cnt>0?true:false;
	}

	@Override
	public boolean updateBillStatus(EcmMzfOrderStatus orderStatus) throws ServiceException {
		Integer cnt=baseMapper.update(orderStatus, "EcmMzfOrderStatusMapper.updateBillStatus");
		return cnt>0?true:false;
	}

	@Override
	public boolean updateCashStatus(String orderSn) throws ServiceException {
		Integer cnt=baseMapper.update(orderSn, "EcmMzfOrderStatusMapper.updateCashStatus");
		return cnt>0?true:false;
	}

	@Override
	public boolean batchUpdCashStatus(Collection<String> orderSns) throws ServiceException {
		Integer cnt=baseMapper.update(ImmutableMap.of("orderSns", orderSns), "EcmMzfOrderStatusMapper.batchUpdateCashStatus");
		return cnt>0?true:false;
	}

}
