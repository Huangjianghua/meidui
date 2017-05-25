package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccountByWalletType;
import com.meiduimall.service.account.service.AccountByWalletTypeService;

/**
 * 按业务类型账户表相关业务逻辑接口{@link=AccountByWalletTypeService}实现类
 * @author chencong
 *
 */
@Service
public class AccountByWalletTypeServiceImpl implements AccountByWalletTypeService {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountByWalletTypeServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public MSAccountByWalletType getModelByWalletNoAndMemId(String walletNo, String memId) {
		Map<String, Object> mapCondition=new HashMap<>();
		mapCondition.put("walletNo",walletNo);
		mapCondition.put("memId",memId);
		return baseDao.selectOne(mapCondition,"MSAccountByWalletTypeMapper.getAccountByWalletTypeInfoByNoAndMemId");
	}

	@Override
	public boolean checkAccountByWalletTypeExist(String walletNo, String memId) {
		if(this.getModelByWalletNoAndMemId(walletNo,memId)==null){
			logger.warn("会员{}类型为{}的账户不存在",memId,walletNo);
			return false;
		} 
		logger.info("会员{}类型为{}的账户存在",memId,walletNo);
		return true;
	}
	
	

}