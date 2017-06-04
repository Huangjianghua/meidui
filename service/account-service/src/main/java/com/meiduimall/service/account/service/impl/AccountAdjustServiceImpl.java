package com.meiduimall.service.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.model.request.RequestAccountAdjustAmount;
import com.meiduimall.service.account.service.AccountAdjustService;
import com.meiduimall.service.account.service.AccountDetailService;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.AccountService;
import com.meiduimall.service.account.service.ValidateService;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DoubleCalculate;
import com.meiduimall.service.account.util.StringUtil;

/**
 * 账户调整相关接口{@link=AccountAdjustService}实现类
 * @author chencong
 *
 */
@Service
public class AccountAdjustServiceImpl implements AccountAdjustService {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountAdjustServiceImpl.class);
	
	@Autowired
	private ValidateService validateService;
	
	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountDetailService accountDetailService;
	
	@Autowired
	private AccountFreezeDetailService accountFreezeDetailService;
	
	@Autowired
	private AccountReportService accountReportService;

	@Override
	public ResBodyData accountAdjustAmount(RequestAccountAdjustAmount model) {
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.SUCCESS_C);
		//校验调账类型是否合法
		validateService.checkAdjustType(model.getDirection());
		//校验交易类型是否合法
		validateService.checkTradeType(model.getTrade_type());
		//校验交易金额是否合法
		validateService.checkTradeAmount(model.getTrade_amount(),"0+");
		
		
		return resBodyData;
	}
	
	@Override
	public boolean addMDConsumePoints(String memId, String consumePoints, boolean isLock) throws MdSysException {
		boolean returnBool = false;
		//增加基本账户总额
		double addAtq = DoubleCalculate.add(accountReportService.getCurrentPointsByMemId(memId),
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
		double cutAtq = DoubleCalculate.sub(accountReportService.getCurrentPointsByMemId(memId),
				Double.valueOf(consumePoints));
		//修改会员基本账户总额
		if(updateAccountPoint(memId,cutAtq)){
			returnBool = true;
		}
		return returnBool;
	}
	
	@Override
	public Double addConsumeMoney(String memId, String tradeAmount) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = accountService.getAccountMoney(memId);
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
		/*	String id = accountService.insertAccount(memId, ConstSysParamsDefination.ACCOUNT_TYPE_MONEY, 
					tradeAmount, "0");
			if(!StringUtil.isEmptyByString(id)){
				returnBalance = Double.valueOf(tradeAmount);
			}*/
		}
		return returnBalance;
	}

	@Override
	public Double cutConsumeMoney(String memId, String tradeAmount) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = accountService.getAccountMoney(memId);
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
	public boolean cutMDConsumePointsAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark) throws MdSysException {
		//计算扣除后基本账户总额
		double cutAtq = DoubleCalculate.sub(accountReportService.getCurrentPointsByMemId(memId),
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
	public Double addConsumeFreezeMoney(String memId, String tradeAmount) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = accountService.getAccountMoney(memId);
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
		MSAccount account = accountService.getAccountMoney(memId);
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
		MSAccount account = accountService.getAccountMoney(memId);
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
		MSAccount account = accountService.getAccountMoney(memId);
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
			MSAccount account = accountService.getAccountMoney(memId);
			//增加明细
			accountDetailService.saveAddAccountDetail(memId, orderId,
					account.getId(),"", tradeType, tradeAmount,
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
			MSAccount account = accountService.getAccountMoney(memId);
			//增加明细
			accountDetailService.saveCutAccountDetail(memId, orderId,
					account.getId(),"", tradeType, tradeAmount,
					tradeDate, String.valueOf(balance), remark);
			returnBool = true;
		}else{
			throw new MdBizException(ConstApiStatus.FROZEN_BALANCE_FAILED_ERROR);
		}
		return returnBool;
	}

	@Override
	public boolean addConsumeFreezeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) {
		boolean returnBool = false;
		Double balance = this.addConsumeFreezeMoney(memId,tradeAmount);
		if(balance >= 0){
			MSAccount account = accountService.getAccountMoney(memId);
			//增加明细
			accountFreezeDetailService.saveAccountFreezeDetail(memId, orderId,
					account.getId(),"", tradeType, tradeAmount,
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
			MSAccount account = accountService.getAccountMoney(memId);
			//增加明细
			accountFreezeDetailService.saveAccountUnFreezeDetail(memId, orderId,
					account.getId(),"", tradeType, tradeAmount,
					tradeDate, String.valueOf(balance), remark);
			returnBool = true;
		}else{
			throw new MdBizException(ConstApiStatus.FROZEN_BALANCE_FAILED_ERROR);
		}
		return returnBool;
	}
	
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
	 * 修改账户余额
	 * @param id
	 * @param balance
	 * @return
	 */
	private boolean updateAccountBalance(String id, Double balance){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ConstSysParamsDefination.ACCOUNT_TYPE_MONEY);
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
	 * 修改账户余额与冻结余额
	 * @param id
	 * @param balance
	 * @param freezeBalance
	 * @return
	 */
	private boolean updateAccount(String id, Double balance, Double freezeBalance){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ConstSysParamsDefination.ACCOUNT_TYPE_MONEY);
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
	 * 修改账户冻结余额
	 * @param id
	 * @param freezeBalance
	 * @return
	 */
	private boolean updateAccountFreezeBalance(String id, Double freezeBalance){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ConstSysParamsDefination.ACCOUNT_TYPE_MONEY);
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


}
