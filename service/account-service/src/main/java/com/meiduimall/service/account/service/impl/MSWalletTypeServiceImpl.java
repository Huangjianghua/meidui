package com.meiduimall.service.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSWalletType;
import com.meiduimall.service.account.service.MSWalletTypeService;

@Component
public class MSWalletTypeServiceImpl implements MSWalletTypeService {
	
	@Autowired
	private BaseDao baseDao;


	@Override
	public List<MSWalletType> listWalletType() {
		
		return baseDao.selectList(null,"MSWalletTypeMapper.listWalletType");
	}

}
