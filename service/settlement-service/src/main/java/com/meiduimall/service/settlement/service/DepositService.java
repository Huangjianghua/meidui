package com.meiduimall.service.settlement.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.settlement.model.EcmAgent;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmStore;
import com.meiduimall.service.settlement.model.ShareProfitAgentLog;

/**
 * 保证金分润
 * @author guidl
 *
 */
public interface DepositService {
	
	/**
	 * 保证金分账 业务逻辑方法
	 * @param ecmAgent
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> shareDeposit(EcmAgent ecmAgent) throws Exception;
	
	/**
	 * 新商家送积分
	 * @param ecmStore
	 * @return
	 */
	public List<Map<String, Object>> updateStoreScore(EcmStore ecmStore);
	
	/**
	 * 创建账户
	 * @param ecmMzfAccount
	 * @return
	 */
	public int createAccount(EcmMzfAccount ecmMzfAccount) throws Exception;
	
	/**
	 * 保证金分润主方法
	 * @param ecmAgent
	 * @param systemSetting
	 * @throws Exception
	 */
	public void shareDepositMain(EcmAgent ecmAgent, Map<String, String> systemSetting) throws Exception;
	
	/**
	 * 记录保证金分润异常日志
	 * @param agentLog
	 * @param retryType
	 * @throws Exception
	 */
	public void shareProfitAgentLog(ShareProfitAgentLog agentLog, String retryType) throws Exception;
	
	
}
