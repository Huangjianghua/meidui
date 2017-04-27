package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.model.MSBankInfo;
import com.meiduimall.service.account.service.BankAccountService;

/**
 * 类名:  BankAccountService<br>
 * 描述: 银行账户信息业务服务层，与银行账户信息相关的都定义在此  <br>
 * 创建时间: 2016-12-18
 */
@Transactional
@Component
public class BankAccountServiceImpl implements BankAccountService{

	private final static Logger logger=LoggerFactory.getLogger(BankAccountServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public String addBankAccount(MSBankAccount dto) {
		String bankAccountId = UUID.randomUUID().toString();
		dto.setId(bankAccountId);
		try {
			Integer insertFlag = baseDao.insert(dto, "MSBankAccountMapper.insertBankAccount");
			if(insertFlag <= 0){
				return null;
			}
			return bankAccountId;
		} catch (Exception e) {
			logger.error("新增会员银行账户信息出现错误，会员编号：{}，错误信息：{}", dto.getMemId(), e.getMessage());
			return null;
		}
	}

	@Override
	public boolean deleteBankAccount(String memId, String accountNo) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		paramsMap.put("accountNo", accountNo);
		try {
			MSBankAccount bankAccount = this.getBankAccount(memId, accountNo);
			if(bankAccount != null){
				paramsMap.put("id", bankAccount.getId());
				Integer deleteFlag = baseDao.delete(paramsMap, "MSBankAccountMapper.deleteBankAccount");
				if(deleteFlag <= 0){
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			logger.error("删除会员银行账户信息出现错误，银行账号：{}，错误信息：{}", accountNo, e.getMessage());
			return false;
		}
		return false;
	}

	@Override
	public boolean updateBankAccount(String memId, String accountNo, Map<String, String> updateMap) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		paramsMap.put("accountNo", accountNo);
		try {
			MSBankAccount bankAccount = this.getBankAccount(memId, accountNo);
			if(bankAccount != null){
				updateMap.put("id", bankAccount.getId());
				Integer updateFlag = baseDao.delete(updateMap, "MSBankAccountMapper.updateBankAccount");
				if(updateFlag <= 0){
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			logger.error("修改会员银行账户信息出现错误，银行账号：{}，错误信息：{}", accountNo, e.getMessage());
		}
		return false;
	}

	@Override
	public MSBankAccount getBankAccount(String memId, String accountNo) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		paramsMap.put("accountNo", accountNo);
		try {
			MSBankAccount bankAccount = baseDao.selectOne(paramsMap, "MSBankAccountMapper.selectMemberBankAccount");
			return bankAccount;
		} catch (Exception e) {
			logger.error("查询会员银行账户信息出现错误，银行账号：{}，错误信息：{}", accountNo, e.getMessage());
		}
		return null;
	}

	@Override
	public boolean checkBankAccount(String memId, String accountNo) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		paramsMap.put("accountNo", accountNo);
		MSBankAccount bankAccount = this.getBankAccount(memId, accountNo);
		if(bankAccount != null){
			return true;
		}
		return false;
	}

	@Override
	public List<MSBankAccount> getBankAccountList(String memId) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		try {
			List<MSBankAccount> list = baseDao.selectList(paramsMap, "MSBankAccountMapper.selectMemberBankAccount");
			if(list != null && list.size() > 0){
				return list;
			}
		} catch (Exception e) {
			logger.error("查询会员银行账户信息列表出现错误，会员编号：{}，错误信息：{}", memId, e.getMessage());
		}
		return null;
	}

	@Override
	public List<MSBankInfo> getBankInfoList() {
		Map<String,String> paramsMap = new HashMap<String,String>();
		try {
			List<MSBankInfo> list = baseDao.selectList(paramsMap, "MSBankAccountMapper.selectBankInfo");
			if(list != null && list.size() > 0){
				return list;
			}
		} catch (Exception e) {
			logger.error("查询系统中银行信息列表出现错误，错误信息：{}", e.getMessage());
		}
		return null;
	}
	

}
