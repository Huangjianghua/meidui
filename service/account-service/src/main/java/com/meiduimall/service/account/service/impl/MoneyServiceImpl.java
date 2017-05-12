package com.meiduimall.service.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.ApplicationConstant;
import com.meiduimall.service.account.constant.SysParamsConst;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.service.MoneyService;
import com.meiduimall.service.account.util.DateUtil;
import com.meiduimall.service.account.util.DoubleCalculate;

@Service
public class MoneyServiceImpl implements MoneyService {
	
	private final static Logger logger=LoggerFactory.getLogger(MoneyServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public ResBodyData freezeMoneyAndAddRecord(String memId,Double consumeMoney,String orderId,String orderSource) {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		if(this.updateAccountFreezeMoney(memId,consumeMoney)){
			MSAccount account = this.getAccountByMemId(memId);
			this.saveAccountFreezeDetail(memId,orderId,account.getId(),account.getType(),consumeMoney,account.getFreezeBalance());
		}
		else{
			resBodyData.setStatus(ApiStatusConst.OPERATION_DB_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.OPERATION_DB_EX));
		}
		return resBodyData;
	}
	
	/**
	 * 判断可用余额是否能抵扣消费金额，如果可以，修改账户冻结余额
	 * @param memId 会员ID
	 * @param consumeMoney 消费金额
	 * @return
	 */
	private boolean updateAccountFreezeMoney(String memId,Double consumeMoney){
		MSAccount account = this.getAccountByMemId(memId);
		if(account != null){
			/**获取可用余额*/
			Double availableMoney = DoubleCalculate.sub(Double.valueOf(account.getBalance()),Double.valueOf(account.getFreezeBalance()));
			/**判断可用余额是否能扣减消费金额*/
			if(availableMoney>=consumeMoney){
				Double newFreezeBalance = DoubleCalculate.add(Double.valueOf(account.getFreezeBalance()),consumeMoney);
				if(newFreezeBalance < 0){
					return false;
				}
				this.updateAccountFreezeBalance(account.getId(), newFreezeBalance);
			}
		}
		return true;
	}
	

	private MSAccount getAccountByMemId(String memId){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		paramsMap.put("accountType", ApplicationConstant.ACCOUNT_TYPE_MONEY);
		try {
			MSAccount msAccount = baseDao.selectOne(paramsMap, "MSAccountMapper.getAccountByMemId");
			logger.info("会员ID：{}的账户信息：{}",memId,msAccount.toString());
			return msAccount;
		} catch (Exception e) {
			return null;
		}
	}
	
	private boolean updateAccountFreezeBalance(String id, Double freezeBalance){
		Map<String,Object> paramsMap=new HashMap<>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ApplicationConstant.ACCOUNT_TYPE_MONEY);
		paramsMap.put("freezeBalance",freezeBalance);
		try {
			int updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateFreezeBalanceByMemIdAndType");
			if(updateFlag <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("修改会员账户冻结余额出现错误，会员账户ID：{}，错误信息：{}", id, e.getMessage());
			return false;
		}
	}
	
	private void saveAccountFreezeDetail(String memId, String orderId,String accountId, String accountType,Double consumeMoney, String freezeBalance) {
		Map<String,Object> paramsMap=new HashMap<>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("accountId", accountId);
		paramsMap.put("accountType", accountType);
		paramsMap.put("tradeAmount", consumeMoney);
		paramsMap.put("freezeBalance", freezeBalance);
		paramsMap.put("tradeType",SysParamsConst.MONEY_TRADE_TYPE_YEXF);
		paramsMap.put("inOrOut", "1");
		
		try {
			baseDao.insert(paramsMap, "MSAccountFreezeDetailMapper.insertAccountFreezeDetail");
		} catch (Exception e) {
			logger.error("写入账户冻结明细出现错误-1001，会员编号：{}，订单编号：{}，错误信息：{}", 
					memId, orderId, e.getMessage());
		}
	}

	@Override
	public ResBodyData unFreezeMoneyAndAddRecord(String memId, Double consumeMoney, String orderId, String orderSource,Map<String,Object>  dataMap) {
		Double balance = this.calculateAndUpdateFreezeMoney(memId,consumeMoney);
		dataMap.put("before_total_money",Double.valueOf(getAccountByMemId(memId).getBalance()));
		if(balance >= 0){
			MSAccount account = getAccountByMemId(memId);
			this.saveAccountUnFreezeDetail(memId,orderId,account.getId(), account.getType(),"1",consumeMoney,null,balance,"");
		}else{
			throw new RuntimeException("冻结余额变动失败");
		}
		return null;
	}
	
	private Double calculateAndUpdateFreezeMoney(String memId, Double consumeMoney) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = getAccountByMemId(memId);
		if(account != null){
			Double freezeBalance = DoubleCalculate.sub(Double.valueOf(account.getFreezeBalance()),consumeMoney);
			if(freezeBalance < 0){
				return returnBalance;
			}
			boolean flag = updateAccountFreezeBalance(account.getId(), freezeBalance);
			if(flag){
				returnBalance = freezeBalance;
			}
		}
		return returnBalance;
	}
	
	private void saveAccountUnFreezeDetail(String memId, String orderId,String accountId, String accountType,String tradeType,Double tradeAmount, Date tradeDate, Double freezeBalance,String remark){
		Map<String,Object> paramsMap=new HashMap<>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("accountId", accountId);
		paramsMap.put("accountType", accountType);
		paramsMap.put("tradeType", tradeType);
		paramsMap.put("tradeAmount", tradeAmount);
		paramsMap.put("freezeBalance", freezeBalance);
		paramsMap.put("remark", remark);
		paramsMap.put("inOrOut", "-1");
		paramsMap.put("tradeDate", DateUtil.format(tradeDate,DateUtil.YYYY_MM_DD_HH_MM_SS));
		try {
			baseDao.insert(paramsMap, "MSAccountFreezeDetailMapper.insertAccountFreezeDetail");
		} catch (Exception e) {
			logger.error("写入账户冻结明细出现错误-1002，会员编号：{}，订单编号：{}，错误信息：{}", 
					memId, orderId, e.getMessage());
		}
	}

	@Override
	public ResBodyData deductMoneyAndAddRecord(String memId,Double consumeMoney,String orderId,String orderSource,Map<String,Object>  dataMap) {
		boolean returnBool = false;
		Double balance = this.cutConsumeMoney(memId,consumeMoney);
		if(balance >= 0){
			MSAccount account = getAccountByMemId(memId);
			dataMap.put("now_total_money",Double.valueOf(account.getBalance()));
			saveCutAccountDetail(memId, orderId,
					account.getId(), account.getType(),"", consumeMoney,
					null,balance, "");
			returnBool = true;
		}else{
			throw new RuntimeException("余额变动失败");
		}
		return null;
	}
	
	public Double cutConsumeMoney(String memId, Double consumeMoney) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = getAccountByMemId(memId);
		if(account != null){
			Double useBalance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),
					Double.valueOf(account.getFreezeBalance()));
			if(useBalance >= Double.valueOf(consumeMoney)){
				Double balance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),consumeMoney);
				if(balance < 0){
					return returnBalance;
				}
				boolean flag = updateAccountBalance(account.getId(),balance);
				if(flag){
					returnBalance = balance;
				}
			}
		}
		return returnBalance;
	}
	
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
			logger.error("修改会员账户余额出现错误，会员账户ID：{}，错误信息：{}", id, e.getMessage());
			return false;
		}
	}
	
	private void saveCutAccountDetail(String memId, String orderId,String accountId, String accountType, String tradeType,Double tradeAmount, Date tradeDate, Double balance, String remark) {
		Map<String,Object> paramsMap=new HashMap<>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("accountId", accountId);
		paramsMap.put("accountType", accountType);
		paramsMap.put("tradeType", tradeType);
		paramsMap.put("tradeAmount", tradeAmount);
		paramsMap.put("balance", balance);
		paramsMap.put("remark", remark);
		paramsMap.put("inOrOut", "-1");
		paramsMap.put("tradeDate", DateUtil.format(tradeDate,DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		try {
			baseDao.insert(paramsMap, "MSAccountDetailMapper.insertAccountDetail");
		} catch (Exception e) {
			logger.error("写入账户变动明细出现错误-1002，会员编号：{}，订单编号：{}，错误信息：{}", 
					memId, orderId, e.getMessage());
		}
	}
	
	@Override
	public boolean getFreezeUnfreezeRecordByOrderId(String orderId) {
		try {
			int check =baseDao.selectOne(orderId, "MSAccountFreezeDetailMapper.getRecordByOrderId");
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