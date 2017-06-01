package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.service.AccountDetailService;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.AccountService;
import com.meiduimall.service.account.service.MSConsumePointsFreezeService;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DoubleCalculate;

/**
 * 会员账户业务逻辑接口{@link=AccountService}实现类
 * @author chencong
 *
 */
@Service
public class AccountServiceImpl implements AccountService {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private AccountFreezeDetailService  accountFreezeDetailService;
	
	@Autowired
	private AccountDetailService  accountDetailService;
	
	@Autowired
	private MSConsumePointsFreezeService  pointsFreezeService;

	@Override
	public String insertAccount(String memId, String type, String balance,
			String freezeBalance){
		String accountId = UUID.randomUUID().toString();
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("accountId", accountId);
		paramsMap.put("memId", memId);
		paramsMap.put("type", type);
		paramsMap.put("balance", balance);
		paramsMap.put("freezeBalance", freezeBalance);
		try {
			Integer insertFlag = baseDao.insert(paramsMap, "MSAccountMapper.insertAccount");
			if(insertFlag <= 0){
				return null;
			}
			return accountId;
		} catch (Exception e) {
			logger.error("新增会员账户信息出现错误，会员编号：%s，错误信息：%s", memId, e.getMessage());
			return null;
		}
	}

	
	@Override
	public Double getTotalConsumePoints(String memId) {
		/** 获取当前积分总额   */
		Double realPoints = Double.valueOf("0");
		try{
			String accountPoint = baseDao.selectOne(memId, "MSAccountMapper.getCurrentPointsByMemId");
			realPoints = Double.valueOf(DESC.deyption(accountPoint,memId));
		}catch(Exception e){
			realPoints = Double.valueOf("0");
		}
		return realPoints;
	}
	

	@Override
	public Double getUseConsumePoints(String memId) {
		/*** 计算积分余额 = 冻结前当前积分余额-当前冻结积分 **/
		Double realPoints = Double.valueOf("0");
		try{
			realPoints = DoubleCalculate.add(pointsFreezeService.getFreezeConsumePoints(memId), 
					getTotalConsumePoints(memId));
		}catch(Exception e){
			realPoints = Double.valueOf("0");
		}
		return realPoints;
	}
	
	@Override
	public boolean addMDConsumePointsFreezeAndDetail(String memId, String consumePoints, String orderId,
			String orderSource, String operatorType, String operator, String remark) {
		accountFreezeDetailService.saveFreezePoints(memId, orderId, consumePoints, operator, remark);
		return true;
	}

	@Override
	public boolean cutMDConsumePointsFreezeAndDetail(String memId, String consumePoints, String orderId,
			String orderSource, String operatorType, String operator, String remark) {
		accountFreezeDetailService.saveUnFreezePoints(memId, orderId, consumePoints, operator, remark);
		return true;
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
