package com.meiduimall.service.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSMembers;
import com.meiduimall.service.account.model.response.AccountBalanceResult;
import com.meiduimall.service.account.service.AccountService;
import com.meiduimall.service.account.service.MSMembersService;

@Component
public class MSMembersServiceImpl implements MSMembersService {

	@Autowired
	private BaseDao baseDao;

	@Autowired
	private AccountService accountService;

	@Override
	public MSMembers getMemberInfo(String memId) {
		return baseDao.selectOne(memId, "MSMembersMapper.getMemberInfo");
	}

	@Override
	public AccountBalanceResult getAccountBalance(String memId, String userId) {
		
		// 先查询用户是否存在
		if(!checkUserIsExistByMemId(memId)){
			throw new ServiceException(ConstApiStatus.USER_NOT_EXIST);
		}
		
		// 目前暂时使用accountService提供的方法，不过这些方法还不确定是否正常，待确定后再调整
//		Double totalConsumePoints = accountService.getTotalConsumePoints(memId);
//		Double useConsumePoints = accountService.getUseConsumePoints(memId);
//		Double freezeConsumePoints = accountService.getFreezeConsumePoints(memId);
//		Double totalConsumeMoney = accountService.getTotalConsumeMoney(memId);
//		Double useConsumeMoney = accountService.getUseConsumeMoney(memId);
//		Double freezeConsumeMoney = accountService.getFreezeConsumeMoney(memId);
//
//		AccountBalanceResult data = new AccountBalanceResult();
//		data.setAllPoints(String.valueOf(totalConsumePoints));
//		data.setUsePoints(String.valueOf(useConsumePoints));
//		data.setFreezePoints(String.valueOf(freezeConsumePoints));
//		data.setAllMoney(String.valueOf(totalConsumeMoney));
//		data.setUseMoney(String.valueOf(useConsumeMoney));
//		data.setFreezeMoney(String.valueOf(freezeConsumeMoney));
//		return data;
		return null;
	}

	@Override
	public boolean checkUserIsExistByMemId(String memId) {
		// TODO Auto-generated method stub
		return false;
	}
}
