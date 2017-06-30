package com.meiduimall.service.account.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.model.MSAccountFreezeDetail;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.AccountReportService;
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
	
	@Autowired
	private AccountFreezeDetailService  accountFreezeDetailService;
	
	@Autowired
	private AccountReportService  accountReportService;
	
	@Override
	public MSAccount getAccountInfoByMemIdAndAccountTypeNo(String memId, String accountTypeNo) {
		Map<String, Object> mapCondition=new HashMap<>();
		mapCondition.put("memId",memId);
		mapCondition.put("accountTypeNo",accountTypeNo);
		return baseDao.selectOne(mapCondition,"MSAccountMapper.getAccountByCondition");
	}
	
	@Override
	public MSAccount getAccountInfoByMemIdAndAccountNo(String memId, String accountNo) {
		Map<String, Object> mapCondition=new HashMap<>();
		mapCondition.put("memId",memId);
		mapCondition.put("accountNo",accountNo);
		return baseDao.selectOne(mapCondition,"MSAccountMapper.getAccountByCondition");
	}

	@Override
	public List<MSAccount> getAccountInfoByMemId(String memId) {
		Map<String, Object> mapCondition=new HashMap<>();
		mapCondition.put("memId",memId);
		return baseDao.selectList(mapCondition,"MSAccountMapper.getAccountByCondition");
	}
	
	@Override
	public Boolean checkAccountExistByTypeAndMemId(String memId, String accountTypeNo) {
		return this.getAccountInfoByMemIdAndAccountTypeNo(memId,accountTypeNo)!=null?true:false;
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

	@Transactional
	@Override
	public void freezeAccountBySpendPriority(String memId,MSAccountFreezeDetail accountFreezeDetail) throws MdSysException {
		Double newFreezeBalance=accountFreezeDetail.getFreezeBalance();
		for(MSAccount account:this.getBalanceAccountListOrderBySpendPriority(memId)){
			//如果需要冻结的余额已经全部被冻结，就终止
			if(newFreezeBalance<=0)
			{
				break;
			}
			accountFreezeDetail.setId(UUID.randomUUID().toString());
			accountFreezeDetail.setAccountNo(account.getAccountNo());
			accountFreezeDetail.setCreateUser("账户服务");
			accountFreezeDetail.setUpdateUser("账户服务");
			accountFreezeDetail.setFreezeBalance(newFreezeBalance);
			//如果该类型账户可用余额大于当前冻结金额，就全部冻结
			if(account.getBalance()-account.getFreezeBalance()>=newFreezeBalance){
				//写入冻结流水
				accountFreezeDetailService.insertAccoutFreezeDetail(accountFreezeDetail);
				//更新账户
				account.setFreezeBalance(account.getFreezeBalance()+newFreezeBalance);
				account.setFreezeBalanceEncrypt(DESC.encryption(String.valueOf(account.getFreezeBalance()),memId));
				baseDao.update(account,"MSAccountMapper.updateAccountByCondition");
				//更新账户报表
				Map<String,Object> mapCondition=new HashMap<>();
				mapCondition.put(account.getAccountTypeNo(),newFreezeBalance);
				mapCondition.put("freezeBalance",newFreezeBalance);
				mapCondition.put("memId",memId);
				baseDao.update(mapCondition,"MSAccountReportMapper.updateFreezeBalance");
				break;
			}
			//如果该类型账户可用余额不够，就冻结完，继续冻结下一个账户
			else{
				//如果该类型账户可用余额为0，无需冻结，直接跳到下一个账户继续冻结
				if(account.getBalance()-account.getFreezeBalance()<=0)
				{					
					continue;
				}
				//写入冻结流水
				accountFreezeDetail.setFreezeBalance(account.getBalance());
				accountFreezeDetailService.insertAccoutFreezeDetail(accountFreezeDetail);
				//更新账户
				account.setFreezeBalance(account.getBalance());
				account.setFreezeBalanceEncrypt(DESC.encryption(String.valueOf(account.getFreezeBalance()),memId));
				baseDao.update(account,"MSAccountMapper.updateAccountByCondition");
				//更新账户报表
				Map<String,Object> mapCondition=new HashMap<>();
				mapCondition.put(account.getAccountTypeNo(),account.getBalance());
				mapCondition.put("freezeBalance",account.getBalance());
				mapCondition.put("memId",memId);
				baseDao.update(mapCondition,"MSAccountReportMapper.updateFreezeBalance");
				newFreezeBalance=newFreezeBalance-account.getBalance();
				continue;
			}
		}
	}

	@Override
	public List<MSAccount> getBalanceAccountList(String memId) {
		Map<String,Object> mapCondition=new HashMap<>();
		mapCondition.put("memId",memId);
		return baseDao.selectList(mapCondition,"MSAccountMapper.getBalanceAccountByCondition");
	}

	@Override
	public List<MSAccount> getBalanceAccountListOrderBySpendPriority(String memId) {
		List<MSAccount> listMsAccount=this.getBalanceAccountList(memId);
		Collections.sort(listMsAccount,Comparator.comparing(MSAccount::getSpendPriority));
		return listMsAccount;
	}
	
	@Override
	public List<MSAccount> getBalanceAccountListOrderByWithDrawPriority(String memId) {
		List<MSAccount> listMsAccount=this.getBalanceAccountList(memId);
		Collections.sort(listMsAccount,Comparator.comparing(MSAccount::getWithdrawPriority));
		return listMsAccount;
	}
	 

	@Override
	public void updateAccountTotalPoints(String memId, Double changePoints) throws MdSysException {
		Double totalPoints=accountReportService.getTotalPointsByMemId(memId);
		String newTotalPoints=DESC.encryption(String.valueOf(totalPoints+changePoints),memId);
		Map<String,Object> mapCondition=new HashMap<>();
		mapCondition.put("memId",memId);
		mapCondition.put("newTotalPoints",newTotalPoints);
		baseDao.update(mapCondition,"MSAccountMapper.updateAccountTotalPoints");
	}

	@Override
	public Double getAllowWithdrawBalance(String memId) {
		return baseDao.selectOne(memId,"MSAccountMapper.getAllowWithdrawBalance");
	}



}
