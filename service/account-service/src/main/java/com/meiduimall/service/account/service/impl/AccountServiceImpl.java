package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.service.AccountService;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DoubleCalculate;

/**
 * 会员账户业务逻辑接口{@link=AccountService}实现类
 * @author chencong
 *
 */
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public MSAccount getAccountInfo(String memId, String accountTypeNo) {
		Map<String, Object> mapCondition=new HashMap<>();
		mapCondition.put("memId",memId);
		mapCondition.put("accountTypeNo",accountTypeNo);
		return baseDao.selectOne(mapCondition,"MSAccountMapper.getAccountByMemIdAndAccountTypeNo");
	}

	@Override
	public MSAccount getAccountInfo(String memId) {
		Map<String, Object> mapCondition=new HashMap<>();
		mapCondition.put("memId",memId);
		return baseDao.selectOne(mapCondition,"MSAccountMapper.getAccountByMemIdAndAccountTypeNo");
	}
	
	@Override
	public Boolean checkAccountExistByType(String memId, String accountTypeNo) {
		return getAccountInfo(memId,accountTypeNo)!=null?true:false;
	}

	@Override
	public Boolean insertAccountByType(MSAccount msAccount){
		int i=baseDao.insert(msAccount,"MSAccountMapper.insertAccount");
		return i>0?true:false;
	}

	@Override
	public MSAccount getAccountMoney(String memId) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		paramsMap.put("accountType", ConstSysParamsDefination.ACCOUNT_TYPE_MONEY);
		try {
			Object resultObj = baseDao.selectOne(paramsMap, "MSAccountMapper.getAccountByMemId");
			if(resultObj == null){
				return null;
			}
			return (MSAccount) resultObj;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Double getTotalConsumeMoney(String memId) {
		Double balance = Double.valueOf("0");
		try{
			MSAccount account = getAccountMoney(memId);
			if(account != null){
				balance = Double.valueOf(account.getBalance());
			}
		}catch(Exception e){
			balance = Double.valueOf("0");
		}
		return balance;
	}

	@Override
	public Double getFreezeConsumeMoney(String memId) {
		Double freezeBalance = Double.valueOf("0");
		try{
			MSAccount account = getAccountMoney(memId);
			if(account != null){
				freezeBalance = Double.valueOf(account.getFreezeBalance());
			}
		}catch(Exception e){
			freezeBalance = Double.valueOf("0");
		}
		return freezeBalance;
	}

	@Override
	public Double getUseConsumeMoney(String memId) {
		Double useBalance = Double.valueOf("0");
		try{
			MSAccount account = getAccountMoney(memId);
			if(account != null){
				Double balance = Double.valueOf(account.getBalance());
				Double freezeBalance = Double.valueOf(account.getFreezeBalance());
				useBalance = DoubleCalculate.sub(balance, freezeBalance);
			}
		}catch(Exception e){
			useBalance = Double.valueOf("0");
		}
		return useBalance;
	}

	

	@Override
	public String getMemIdByUserId(String userId) {
		try{
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("userId", DESC.encryption(userId));
			paramMap.put("memId", userId);
			String memId = baseDao.selectOne(paramMap, "MSAccountMapper.getMemIdByUserId");
			return memId;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean checkFreezePointByOrderId(String orderId) {
		try {
			Integer check = (Integer) baseDao.selectOne(orderId, "MSAccountMapper.checkFreezePointByOrderId");
			if(check > 0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean checkFreezeMoneyByOrderId(String orderId) {
		try {
			Integer check = (Integer) baseDao.selectOne(orderId, "MSAccountMapper.checkFreezeMoneyByOrderId");
			if(check > 0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
