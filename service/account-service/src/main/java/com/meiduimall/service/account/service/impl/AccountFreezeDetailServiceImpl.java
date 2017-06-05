package com.meiduimall.service.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccountFreezeDetail;
import com.meiduimall.service.account.service.AccountFreezeDetailService;

/**
 * 账户冻结解冻业务逻辑接口{@link=AccountFreezeDetailService}实现类
 * @author chencong
 *
 */
@Service
public class AccountFreezeDetailServiceImpl implements AccountFreezeDetailService{

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void insertAccoutFreezeDetail(MSAccountFreezeDetail model){
		baseDao.insert(model,"MSAccountFreezeDetailMapper.insertAccountFreezeDetail");
	}

	@Override
	public List<MSAccountFreezeDetail> getRecordsByOrderId(String orderId) {
		return baseDao.selectList(orderId,"MSAccountFreezeDetailMapper.getRecordsByOrderId");
	}


}
