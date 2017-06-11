package com.meiduimall.service.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSMemberConsumeRecords;
import com.meiduimall.service.account.service.ConsumeRecordsService;

/**
 * 消费记录业务逻辑接口{@link=ConsumeRecordsService}实现类
 * @author chencong
 *
 */
@Service
public class ConsumeRecordsServiceImpl implements ConsumeRecordsService {
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public MSMemberConsumeRecords getConsumeRecords(String orderId, String orderSource, Integer orderStatus) {
		MSMemberConsumeRecords consumeRecords=new MSMemberConsumeRecords();
		consumeRecords.setOrderId(orderId);
		consumeRecords.setOrderStatus(orderStatus);
		consumeRecords.setOrderSource(orderSource);
		return baseDao.selectOne(consumeRecords,"MSMemberConsumeRecordsMapper.getConsumeRecordsByContidion");
	}

	@Override
	public void insertConsumeRecords(MSMemberConsumeRecords model) {
		baseDao.insert(model,"MSMemberConsumeRecordsMapper.insertConsumeRecords");
	}

	@Override
	public void updateOrderStatus(Map<String,Object> mapCondition) {
		baseDao.update(mapCondition,"MSMemberConsumeRecordsMapper.updateOrderStatus");
	}
}
