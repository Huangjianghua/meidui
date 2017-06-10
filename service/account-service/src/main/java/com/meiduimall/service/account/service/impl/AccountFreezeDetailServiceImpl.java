package com.meiduimall.service.account.service.impl;

import java.util.List;
import java.util.Map;

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
	public List<MSAccountFreezeDetail> getRecordsByOrderId(Map<String, Object> hashMap) {
		return baseDao.selectList(hashMap,"MSAccountFreezeDetailMapper.getRecordsByCondition");
	}

	@Override
	public void batchInsertAccoutFreezeDetail(List<MSAccountFreezeDetail> model) {
		baseDao.insertBatch(model, "MSAccountFreezeDetailMapper.batchInsertAccoutFreezeDetail");
	}


}
