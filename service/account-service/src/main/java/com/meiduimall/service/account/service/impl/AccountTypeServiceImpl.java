package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccountType;
import com.meiduimall.service.account.service.AccountTypeService;

/**
 * 账户类型管理相关业务逻辑{@link=AccountPropertyManageService}实现类
 * @author chencong
 *
 */
@Service
public class AccountTypeServiceImpl implements AccountTypeService {
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public List<MSAccountType> getAccountTypeList(){
		return baseDao.selectList(null,"MSAccountTypeMapper.getAccountTypeList");
	}

	@Override
	public Long getSequenceByAccountTypeNo(String accountTypeNo) {
		return baseDao.selectOne(accountTypeNo,"MSAccountTypeMapper.getSequenceByAccountTypeNo");
	}

	@Override
	public synchronized Long updateSequenceByAccountTypeNo(String accountTypeNo) {
		Long newSequence=this.getSequenceByAccountTypeNo(accountTypeNo)+1;
		Map<String,Object> mapCondition=new HashMap<>();
		mapCondition.put("accountTypeNo", accountTypeNo);
		mapCondition.put("newSequence", newSequence);
		baseDao.update(mapCondition,"MSAccountTypeMapper.updateSequenceByAccountTypeNo");
		return newSequence;
	}

}
