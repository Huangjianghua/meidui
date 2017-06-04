package com.meiduimall.service.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.ConsumePointsFreezeInfoService;

/**
 * 积分冻结解冻明细业务逻辑接口{@link=ConsumePointsFreezeInfoService}实现类
 * @author chencong
 *
 */
@Service
public class ConsumePointsFreezeInfoServiceImpl implements ConsumePointsFreezeInfoService {

	@Autowired
	private BaseDao  baseDao;
	
	@Autowired
	private AccountFreezeDetailService  accountFreezeDetailService;
	
	
	@Override
	public Double getFreezeUnFreezePointsSumByMemId(String memId) {
		return baseDao.selectOne(memId,"MSConsumePointsFreezeInfoMapper.getFreezeUnFreezePointsSumByMemId");
	}
	
	@Override
	public boolean addMDConsumePointsFreezeAndDetail(String memId, String consumePoints, String orderId,
			String orderSource, String operatorType, String operator, String remark) throws MdSysException {
		accountFreezeDetailService.saveFreezePoints(memId, orderId, consumePoints, operator, remark);
		return true;
	}

	@Override
	public boolean cutMDConsumePointsFreezeAndDetail(String memId, String consumePoints, String orderId,
			String orderSource, String operatorType, String operator, String remark) throws MdSysException {
		accountFreezeDetailService.saveUnFreezePoints(memId, orderId, consumePoints, operator, remark);
		return true;
	}
	

}
