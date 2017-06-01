package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.service.MSMembersService;
import com.meiduimall.service.account.service.WithDrawService;

/**
 * 提现相关Service接口{@link=WithDrawService}实现类
 * 
 * @author chencong
 *
 */
@Service
public class WithDrawServiceImpl implements WithDrawService {

	private final static Logger logger = LoggerFactory.getLogger(WithDrawServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Autowired
	private MSMembersService mSMembersService;

	@Override
	public List<MSBankWithdrawDeposit> getBankWithdrawDepositsList(String memId) {
		if (!mSMembersService.checkUserIsExistByMemId(memId)) {
			throw new ServiceException(ConstApiStatus.USER_NOT_EXIST);
		}
		Map<String, String> params = new HashMap<>();
		params.put("memId", memId);
		List<MSBankWithdrawDeposit> list = baseDao.selectList(params,
				"MSBankWithdrawDepositMapper.selectBankWithdrawDeposit");
		return list;
	}
}