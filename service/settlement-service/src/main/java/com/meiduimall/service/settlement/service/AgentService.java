package com.meiduimall.service.settlement.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.settlement.model.Draw;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmMzfAgentWater;
import com.meiduimall.service.settlement.model.EcmMzfDrawWater;
import com.meiduimall.service.settlement.model.EcmMzfStoreRecord;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.model.EcmSystemSetting;
import com.meiduimall.service.settlement.model.ShareProfitAgentLog;

/**
 * 个代保证金分润service
 * @author guidl
 *
 */
public interface AgentService {
	
	
	/**
	 * 插入代理分保证金流水
	 * @param agentWater
	 * @return
	 * @throws Exception
	 */
	public int insertAgentWater(EcmMzfAgentWater agentWater) throws Exception;
	
	/**
	 * 更新账户余额
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public int updateAccount(EcmMzfAccount account) throws Exception;
	
	/**
	 * 新增账户信息
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public int insertAccount(EcmMzfAccount account) throws Exception;
	
	/**
	 * 根据code查询account是否存在
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public EcmMzfAccount findAccountByCode(String code) throws Exception;

	
	/**
	 * 插入流水
	 * @param agentWater
	 * @return
	 * @throws Exception
	 */
	public int insertWater(EcmMzfWater water) throws Exception;
	
	/**
	 * 修改积分状态为已更新
	 * @param id 代理唯一标识id
	 * @param code 代理编号
	 * @param score 积分
	 * @return
	 * @throws Exception
	 */
	public int updateScoreStatusByCode(int id, String code, int score) throws Exception;
	
	/**
	 * 根据代理编号获取分润记录
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public EcmMzfAgentWater findAgentWaterByCode(String code) throws Exception;
	
	/**
	 * 根据代理表中的code 获取代理流水 用于php接口
	 * @param code 代理表中的code
	 * @return
	 * @throws Exception
	 */
	public List<EcmMzfAgentWater> findAgentWaterByAgentCode(int id) throws Exception;
	
	
	/**
	 * 获取积分未更新到会员系统的数据列表
	 * @return
	 * @throws Exception
	 */
	public List<EcmMzfAgentWater> getAgentWaterScore() throws Exception;
	
	
	/**
	 * 插入商家送积分记录
	 * @param ecmMzfStoreRecord
	 * @return
	 * @throws Exception
	 */
	public int insertStoreRecord(EcmMzfStoreRecord ecmMzfStoreRecord) throws Exception;
	
	
	/**
	 * 查询基本分润配置
	 * @return
	 * @throws Exception
	 */
	public List<EcmSystemSetting> quertSharefit() throws Exception;
	
	
	/**
	 * 获取流水列表
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<EcmMzfWater> getWaterList(Map<String,Object> params) throws Exception;
	
	/**
	 * 获取流水总数
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int getWaterCount(Map<String,Object> params) throws Exception;
	
	
	/**
	 * 根据流水编号获取流水详情
	 * @param waterId 流水编号
	 * @return
	 * @throws Exception
	 */
	public EcmMzfWater getWaterDetailByWaterId(String waterId, String waterType) throws Exception;
	
	/**
	 * 根据提现编号获取提现详情
	 * @param drawCode
	 * @return
	 * @throws Exception
	 */
	public Draw getDrawDetailByDrawCode(String drawCode) throws Exception;
	
	
	/**
	 * 插入代理分润异常日志
	 * @param shareProfitAgentLog
	 * @return
	 * @throws Exception
	 */
	public int insertShareProfitAgentLog(ShareProfitAgentLog shareProfitAgentLog) throws Exception;
	
	/**
	 * 重试三次失败  修改重试标识为 不需要重试0
	 * @param agentNo
	 * @return
	 * @throws Exception
	 */
	public int updateRetryFlag(String agentNo) throws Exception;
	
	/**
	 * 重试成功 修改重试标识
	 * @param agentNo
	 * @return
	 * @throws Exception
	 */
	public int updateStatusFlag(String agentNo) throws Exception;
	
	/**
	 * 从分润异常日志表中  获取重新分润的代理
	 * @param currentTimestamp
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<ShareProfitAgentLog> getAgentsRetry(int currentTimestamp, String key) throws Exception;
	
	/**
	 * 根据推荐人编号和推荐单号获取推荐人的推荐费
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String getRecommenderMoney(Map<String, Object> params) throws Exception;
	
	/**
	 * 获取分润结果 用于判断当前个代是否重新分润
	 * @param id,recNo
	 * @return
	 * @throws Exception
	 */
	public List<EcmMzfAgentWater> getShareProfitResult(int id, String recNo) throws Exception;
	
	/**
	 * 根据提现编号，获取提现流水详情
	 * @param drawCode
	 * @return
	 * @throws Exception
	 */
	public EcmMzfDrawWater getDrawWaterInfo(String drawCode) throws Exception;
	
	/**
	 * 根据code、waterType、nowTime获取count
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int getCountCreateWaterId(Map<String,Object> params) throws Exception;
}
