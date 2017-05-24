package com.meiduimall.service.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSWalletType;
import com.meiduimall.service.account.service.AccountPropertyManageService;

/**
 * 账户类型管理相关业务逻辑{@link=WalletTypeManageService}实现类
 * @author chencong
 *
 */
@Service
public class AccountPropertyManageServiceImpl implements AccountPropertyManageService {
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public List<MSWalletType> getCwtzWalletTypeList(){
		return baseDao.selectList(null,"MSWalletTypeMapper.listWalletType");
	}

}
