package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccountType;
import com.meiduimall.service.account.service.AccountTypeService;

/**
 * 账户类型业务逻辑接口{@link=AccountTypeService}实现类
 * @author chencong
 *
 */
@Transactional
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
	public Long updateSequenceByAccountTypeNo(String accountTypeNo) {
		Long sequence=this.getSequenceByAccountTypeNo(accountTypeNo);
		Map<String,Object> mapCondition=new HashMap<>();
		mapCondition.put("accountTypeNo", accountTypeNo);
		mapCondition.put("sequence", sequence);
		baseDao.update(mapCondition,"MSAccountTypeMapper.updateSequenceByAccountTypeNo");
		return this.getSequenceByAccountTypeNo(accountTypeNo);
	}

}
