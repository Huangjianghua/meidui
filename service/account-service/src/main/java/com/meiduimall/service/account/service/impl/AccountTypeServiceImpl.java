package com.meiduimall.service.account.service.impl;

import java.util.List;

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
	public List<MSAccountType> getCwtzWalletTypeList(){
		return baseDao.selectList(null,"MSWalletTypeMapper.getCwtzWalletTypeList");
	}

}
