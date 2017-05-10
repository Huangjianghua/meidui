package com.meiduimall.service.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.ApplicationConstant;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.service.AccountDetailService;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.AccountServices;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DoubleCalculate;
import com.meiduimall.service.account.util.StringUtil;

/**
 * 类名:  AccountServices<br>
 * 描述:  会员帐户相关服务实现类，与帐户直接关系的基础方法都在此类中定义 <br>
 * 创建时间: 2017-02-23
 */
@Component
public class AccountServicesImpl implements AccountServices {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountServicesImpl.class);

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private AccountFreezeDetailService  accountFreezeDetailService;
	
	@Autowired
	private AccountDetailService  accountDetailService;
	
	/**
	 * 修改会员积分数值
	 * @param memId
	 * @param accountPoint
	 * @return
	 * @throws MdSysException
	 */
	private boolean updateAccountPoint(String memId, Double accountPoint) throws MdSysException {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		paramsMap.put("accountPoint", DESC.encryption(String.valueOf(accountPoint), memId));
		try {
			Integer updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateAccountPoint");
			if(updateFlag <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("修改会员积分出现错误，会员编号：%s，错误信息：%s", memId, e.getMessage());
			return false;
		}
	}
	
	/**
	 * 新增会员账户信息
	 * @param memId
	 * @param type
	 * @param balance
	 * @param freezeBalance
	 * @return
	 */
	private String insertAccount(String memId, String type, String balance,
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
	
	/**
	 * 修改账户余额与冻结余额
	 * @param id
	 * @param balance
	 * @param freezeBalance
	 * @return
	 */
	private boolean updateAccount(String id, Double balance, Double freezeBalance){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ApplicationConstant.ACCOUNT_TYPE_MONEY);
		paramsMap.put("balance", String.valueOf(balance));
		paramsMap.put("freezeBalance", String.valueOf(freezeBalance));
		try {
			Integer updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateAccount");
			if(updateFlag <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("修改会员账户余额与冻结余额出现错误，会员账户ID：%s，错误信息：%s", id, e.getMessage());
			return false;
		}
	}
	
	/**
	 * 修改账户余额
	 * @param id
	 * @param balance
	 * @return
	 */
	private boolean updateAccountBalance(String id, Double balance){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ApplicationConstant.ACCOUNT_TYPE_MONEY);
		paramsMap.put("balance", String.valueOf(balance));
		try {
			Integer updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateAccountBalance");
			if(updateFlag <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("修改会员账户余额出现错误，会员账户ID：%s，错误信息：%s", id, e.getMessage());
			return false;
		}
	}
	
	/**
	 * 修改账户冻结余额
	 * @param id
	 * @param freezeBalance
	 * @return
	 */
	private boolean updateAccountFreezeBalance(String id, Double freezeBalance){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ApplicationConstant.ACCOUNT_TYPE_MONEY);
		paramsMap.put("freezeBalance", String.valueOf(freezeBalance));
		try {
			Integer updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateFreezeBalanceByMemIdAndType");
			if(updateFlag <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("修改会员账户冻结余额出现错误，会员账户ID：%s，错误信息：%s", id, e.getMessage());
			return false;
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
	public Double getFreezeConsumePoints(String memId) {
		/** 冻结和解冻积分的总和 */
		Double realPoints = Double.valueOf("0");
		try{
			String freezeAccountPoint = baseDao.selectOne(memId, "MSAccountMapper.getFreezeUnFreezePointsSumByMemId");
			realPoints = Double.valueOf(freezeAccountPoint);
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
			realPoints = DoubleCalculate.add(getFreezeConsumePoints(memId), 
					getTotalConsumePoints(memId));
		}catch(Exception e){
			realPoints = Double.valueOf("0");
		}
		return realPoints;
	}

	@Override
	public boolean addMDConsumePoints(String memId, String consumePoints, boolean isLock) throws MdSysException {
		boolean returnBool = false;
		//增加基本账户总额
		double addAtq = DoubleCalculate.add(getTotalConsumePoints(memId),
				Double.valueOf(consumePoints));
		//修改会员基本账户总额
		if(updateAccountPoint(memId,addAtq)){
			returnBool = true;
		}
		return returnBool;
	}

	@Override
	public boolean cutMDConsumePoints(String memId, String consumePoints, boolean isLock) throws MdSysException {
		boolean returnBool = false;
		//扣除基本账户总额
		double cutAtq = DoubleCalculate.sub(getTotalConsumePoints(memId),
				Double.valueOf(consumePoints));
		//修改会员基本账户总额
		if(updateAccountPoint(memId,cutAtq)){
			returnBool = true;
		}
		return returnBool;
	}
	
	@Override
	public boolean addMDConsumePointsAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark) throws MdSysException {
		//增加基本账户总额
		double addAtq = DoubleCalculate.add(getTotalConsumePoints(memId),
				Double.valueOf(consumePoints));
		//调用增加积分方法
		boolean flag = addMDConsumePoints(memId, consumePoints, false);
		if(flag){
			//写入积分明细
			accountDetailService.saveConsumePoints(memId,
					orderId, orderSource, consumePoints, "0", String.valueOf(addAtq),
					operatorType, operator, remark);
		}
		return flag;
	}

	@Override
	public boolean cutMDConsumePointsAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark) throws MdSysException {
		//计算扣除后基本账户总额
		double cutAtq = DoubleCalculate.sub(getTotalConsumePoints(memId),
				Double.valueOf(consumePoints));
		//调用扣除方法
		boolean flag = cutMDConsumePoints(memId, consumePoints, false);
		if(flag){
			//写入积分明细
			accountDetailService.saveConsumePoints(memId,
					orderId, orderSource, "0", consumePoints,String.valueOf(cutAtq),
					operatorType, operator, remark);
		}
		return flag;
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
		paramsMap.put("accountType", ApplicationConstant.ACCOUNT_TYPE_MONEY);
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
	public Double addConsumeMoney(String memId, String tradeAmount) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = getAccountMoney(memId);
		if(account != null){
			//增加会员账户余额
			Double balance = DoubleCalculate.add(Double.valueOf(account.getBalance()),
					Double.valueOf(tradeAmount));
			//判断如果计算后账户余额小于0返回-1不成功
			if(balance < 0){
				return returnBalance;
			}
			//修改会员账户余额
			boolean flag = updateAccountBalance(account.getId(), balance);
			if(flag){
				returnBalance = balance;
			}
		}else{
			//无账户信息，新增会员账户
			String id = insertAccount(memId, ApplicationConstant.ACCOUNT_TYPE_MONEY, 
					tradeAmount, "0");
			if(!StringUtil.isEmptyByString(id)){
				returnBalance = Double.valueOf(tradeAmount);
			}
		}
		return returnBalance;
	}

	@Override
	public Double cutConsumeMoney(String memId, String tradeAmount) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = getAccountMoney(memId);
		if(account != null){
			//判断余额是否能够扣减
			Double useBalance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),
					Double.valueOf(account.getFreezeBalance()));
			if(useBalance >= Double.valueOf(tradeAmount)){
				//增加会员账户余额
				Double balance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),
						Double.valueOf(tradeAmount));
				//判断如果计算后账户余额小于0返回-1不成功
				if(balance < 0){
					return returnBalance;
				}
				//修改会员账户余额
				boolean flag = updateAccountBalance(account.getId(), balance);
				if(flag){
					returnBalance = balance;
				}
			}
		}
		return returnBalance;
	}

	@Override
	public Double addConsumeFreezeMoney(String memId, String tradeAmount) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = getAccountMoney(memId);
		if(account != null){
			//判断余额是否能够扣减冻结
			Double useBalance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),
					Double.valueOf(account.getFreezeBalance()));
			if(useBalance >= Double.valueOf(tradeAmount)){
				//增加会员冻结账户余额
				Double freezeBalance = DoubleCalculate.add(Double.valueOf(account.getFreezeBalance()),
						Double.valueOf(tradeAmount));
				//判断如果计算后冻结账户余额小于0返回-1不成功
				if(freezeBalance < 0){
					return returnBalance;
				}
				//修改会员冻结账户余额
				boolean flag = updateAccountFreezeBalance(account.getId(), freezeBalance);
				if(flag){
					returnBalance = freezeBalance;
				}
			}
		}
		return returnBalance;
	}

	@Override
	public Double cutConsumeFreezeMoney(String memId, String tradeAmount) throws MdBizException{
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = getAccountMoney(memId);
		if(account != null){
			//减少会员冻结账户余额
			Double freezeBalance = DoubleCalculate.sub(Double.valueOf(account.getFreezeBalance()),
					Double.valueOf(tradeAmount));
			//判断如果扣减后冻结账户余额小于0返回-1不成功
			if(freezeBalance < 0){
				return returnBalance;
			}
			//修改会员冻结账户余额
			boolean flag = updateAccountFreezeBalance(account.getId(), freezeBalance);
			if(flag){
				returnBalance = freezeBalance;
			}
		}
		return returnBalance;
	}
	
	@Override
	public boolean addFreezeMoneyAndCutMoney(String memId,
			String tradeAmount, String freezeTradeAmount) {
		boolean returnBool = false;
		MSAccount account = getAccountMoney(memId);
		if(account != null){
			//增加会员账户余额
			Double balance = DoubleCalculate.add(Double.valueOf(account.getBalance()),
					Double.valueOf(tradeAmount));
			//减少会员冻结账户余额
			Double freezeBalance = DoubleCalculate.sub(Double.valueOf(account.getFreezeBalance()),
					Double.valueOf(freezeTradeAmount));
			//判断如果计算后账户余额小于0或如果扣减后冻结账户余额小于0返回-1不成功
			if(freezeBalance < 0 || balance < 0){
				return false;
			}
			//修改会员账户余额与冻结余额
			boolean flag = updateAccount(account.getId(), balance, freezeBalance);
			if(flag){
				returnBool = true;
			}
		}
		return returnBool;
	}

	@Override
	public boolean cutFreezeMoneyAndCutMoney(String memId,
			String tradeAmount, String freezeTradeAmount) {
		boolean returnBool = false;
		MSAccount account = getAccountMoney(memId);
		if(account != null){
			//减少会员冻结账户余额
			Double freezeBalance = DoubleCalculate.sub(Double.valueOf(account.getFreezeBalance()),
					Double.valueOf(freezeTradeAmount));
			//扣减会员账户余额
			Double balance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),
					Double.valueOf(tradeAmount));
			//判断如果计算后账户余额小于0或如果扣减后冻结账户余额小于0返回-1不成功
			if(freezeBalance < 0 || balance < 0){
				return false;
			}
			//修改会员账户余额与冻结余额
			boolean flag = updateAccount(account.getId(), balance, freezeBalance);
			if(flag){
				returnBool = true;
			}
		}
		return returnBool;
	}
	
	@Override
	public boolean addConsumeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) {
		boolean returnBool = false;
		Double balance = this.addConsumeMoney(memId,tradeAmount);
		if(balance >= 0){
			MSAccount account = getAccountMoney(memId);
			//增加明细
			accountDetailService.saveAddAccountDetail(memId, orderId,
					account.getId(), account.getType(), tradeType, tradeAmount,
					tradeDate, String.valueOf(balance), remark);
			
			returnBool = true;
		}else{
			throw new RuntimeException("余额变动失败");
		}
		return returnBool;
	}

	@Override
	public boolean cutConsumeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) throws MdBizException{
		boolean returnBool = false;
		Double balance = this.cutConsumeMoney(memId,tradeAmount);
		if(balance >= 0){
			MSAccount account = getAccountMoney(memId);
			//增加明细
			accountDetailService.saveCutAccountDetail(memId, orderId,
					account.getId(), account.getType(), tradeType, tradeAmount,
					tradeDate, String.valueOf(balance), remark);
			returnBool = true;
		}else{
			throw new MdBizException(ApiStatusConst.FROZEN_BALANCE_FAILED_ERROR);
		}
		return returnBool;
	}

	@Override
	public boolean addConsumeFreezeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) {
		boolean returnBool = false;
		Double balance = this.addConsumeFreezeMoney(memId,tradeAmount);
		if(balance >= 0){
			MSAccount account = getAccountMoney(memId);
			//增加明细
			accountFreezeDetailService.saveAccountFreezeDetail(memId, orderId,
					account.getId(), account.getType(), tradeType, tradeAmount,
					tradeDate, String.valueOf(balance), remark);
			returnBool = true;
		}else{
			throw new RuntimeException("冻结余额变动失败");
		}
		return returnBool;
	}

	@Override
	public boolean cutConsumeFreezeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) throws MdBizException{
		boolean returnBool = false;
		Double balance = this.cutConsumeFreezeMoney(memId,tradeAmount);
		if(balance >= 0){
			MSAccount account = getAccountMoney(memId);
			//增加明细
			accountFreezeDetailService.saveAccountUnFreezeDetail(memId, orderId,
					account.getId(), account.getType(), tradeType, tradeAmount,
					tradeDate, String.valueOf(balance), remark);
			returnBool = true;
		}else{
			throw new MdBizException(ApiStatusConst.FROZEN_BALANCE_FAILED_ERROR);
		}
		return returnBool;
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
