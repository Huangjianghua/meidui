package com.meiduimall.service.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccountReport;
import com.meiduimall.service.account.service.AccountReportService;

/**
 * 会员账户汇总信息业务逻辑接口{@link=AccountReportService}实现类
 * @author chencong
 *
 */
@Service
public class AccountReportServiceImpl implements AccountReportService {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountReportServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Override
	public MSAccountReport getTotalAndFreezeBalanceByMemId(String memId) {
		MSAccountReport model=baseDao.selectOne(memId,"MSAccountReportMapper.getTotalAndFreezeBalanceByMemId");
		if(model==null){
			logger.warn("会员：{}不存在任何账户信息",memId);
			throw new ServiceException(ConstApiStatus.ACCOUNT_NOT_EXIST);
		}
		return model;
	}
	


}
