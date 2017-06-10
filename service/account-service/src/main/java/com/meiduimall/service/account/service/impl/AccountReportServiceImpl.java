package com.meiduimall.service.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccountReport;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.ConsumePointsFreezeInfoService;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DoubleCalculate;

/**
 * 会员账户报表业务逻辑接口{@link=AccountReportService}实现类
 * @author chencong
 *
 */
@Service
public class AccountReportServiceImpl implements AccountReportService {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountReportServiceImpl.class);

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private ConsumePointsFreezeInfoService consumePointsFreezeInfoService;

	@Override
	public MSAccountReport getTotalAndFreezeBalanceByMemId(String memId) {
		MSAccountReport model=baseDao.selectOne(memId,"MSAccountReportMapper.getTotalAndFreezeBalanceByMemId");
		if(model==null){
			logger.warn("会员：{}不存在任何账户信息",memId);
			throw new ServiceException(ConstApiStatus.ACCOUNT_NOT_EXIST);
		}
		return model;
	}

	@Override
	public Double getAvailableBalance(String memId) {
		MSAccountReport model=this.getTotalAndFreezeBalanceByMemId(memId);
		return model.getBalance()-model.getFreezeBalance();
	}
	
	@Override
	public Double getAvailablePoints(String memId) throws MdSysException {
		return  DoubleCalculate.add(this.getTotalPointsByMemId(memId),consumePointsFreezeInfoService.getFreezeUnFreezePointsSumByMemId(memId));
	}
	
	@Override
	public Double getTotalPointsByMemId(String memId) throws MdSysException {
		String currentPoints=baseDao.selectOne(memId,"MSMembersMapper.getTotalPointsByMemId");
		return Double.valueOf(DESC.deyption(currentPoints,memId));
	}

}
