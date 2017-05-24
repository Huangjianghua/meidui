package com.meiduimall.service.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Maps;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.service.MSBankAccountService;

@Component
public class MSBankAccountServiceImpl implements MSBankAccountService {

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public MSBankAccount getBankAccount(String memId, String accountNo) {
		Map<String,Object> params = Maps.newHashMap();
		params.put("memId", memId);
		params.put("accountNo", accountNo);
		return baseDao.selectOne(params, "MSBankAccountMapper.selectMemberBankAccount");
	}

	@Override
	public int addBankAccount(MSBankAccount bankAccount) {
		return baseDao.insert(bankAccount, "MSBankAccountMapper.insertBankAccount");
	}

	@Override
	public int deleteBankAccount(String memId, String accountNo) {
		Map<String, Object> params = Maps.newHashMap();
		return baseDao.delete(params, "MSBankAccountMapper.deleteBankAccount");
	}

	@Override
	public int updateBankAccount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
