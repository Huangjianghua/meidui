package com.meiduimall.service.settlement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.Draw;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmMzfAgentWater;
import com.meiduimall.service.settlement.model.EcmMzfDrawWater;
import com.meiduimall.service.settlement.model.EcmMzfStoreRecord;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.model.EcmSystemSetting;
import com.meiduimall.service.settlement.model.ShareProfitAgentLog;
import com.meiduimall.service.settlement.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService {
	
	@Autowired
	private BaseMapper baseMapper;

	
	@Override
	public int insertAgentWater(EcmMzfAgentWater agentWater) throws Exception{
		return baseMapper.insert(agentWater, "EcmMzfAgentWaterMapper.insertAgentWater");
	}

	/**
	 * 暂时用synchronized同步，后期再优化
	 */
	@Override
	public synchronized int updateAccount(EcmMzfAccount account) throws Exception {
		return baseMapper.update(account, "EcmMzfAccountMapper.updateAccountByCode");
	}

	@Override
	public int insertWater(EcmMzfWater water) throws Exception {
		return baseMapper.insert(water, "EcmMzfWaterMapper.insertWater");
	}
	
	@Override
	public int updateScoreStatusByCode(int id, String code, int score) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("code", code);
		params.put("score", score);
		return baseMapper.update(params, "EcmMzfAgentWaterMapper.updateScoreStatusByCode");
	}
	
	@Override
	public int insertAccount(EcmMzfAccount account) throws Exception {
		return baseMapper.insert(account, "EcmMzfAccountMapper.insertAccount");
	}
	
	@Override
	public EcmMzfAccount findAccountByCode(String code) throws Exception {
		return baseMapper.selectOne(code, "EcmMzfAccountMapper.findAccountByCode");
	}
	
	@Override
	public EcmMzfAgentWater findAgentWaterByCode(String code) throws Exception {
		return baseMapper.selectOne(code, "EcmMzfAgentWaterMapper.findAgentWaterByCode");
	}
	
	@Override
	public List<EcmMzfAgentWater> findAgentWaterByAgentCode(int id)
			throws Exception {
		return baseMapper.selectList(id, "EcmMzfAgentWaterMapper.findAgentWaterByAgentCode");
	}
	
	@Override
	public List<EcmMzfAgentWater> getAgentWaterScore() throws Exception {
		return baseMapper.selectList(null, "EcmMzfAgentWaterMapper.getAgentWaterScore");
	}

	
	@Override
	public int insertStoreRecord(EcmMzfStoreRecord ecmMzfStoreRecord) throws Exception {
		return baseMapper.insert(ecmMzfStoreRecord, "EcmStoreMapper.insertStoreRecord");
	}
	
	@Override
	public List<EcmSystemSetting> quertSharefit() throws Exception {
		return baseMapper.selectList(null, "ShareProfitMapper.quertSharefit");
	}
	

	@Override
	public List<EcmMzfWater> getWaterList(Map<String, Object> params) throws Exception {
		return baseMapper.selectList(params, "EcmMzfWaterMapper.getWaterList");
	}
	
	@Override
	public int getWaterCount(Map<String, Object> params) throws Exception {
		return baseMapper.selectOne(params, "EcmMzfWaterMapper.getWaterCount");
	}

	@Override
	public EcmMzfWater getWaterDetailByWaterId(String waterId, String waterType) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("waterId", waterId);
		params.put("waterType", waterType);
		return baseMapper.selectOne(params, "EcmMzfWaterMapper.getWaterDetailByWaterId");
	}

	@Override
	public Draw getDrawDetailByDrawCode(String drawCode) throws Exception {
		return baseMapper.selectOne(drawCode, "EcmMzfWaterMapper.getDrawDetailByDrawCode");
	}

	@Override
	public int insertShareProfitAgentLog(ShareProfitAgentLog shareProfitAgentLog) throws Exception {
		return baseMapper.insert(shareProfitAgentLog, "ShareProfitAgentLogMapper.insertShareProfitAgentLog");
	}
	
	@Override
	public int updateRetryFlag(String agentNo) throws Exception {
		return baseMapper.update(agentNo, "ShareProfitAgentLogMapper.updateRetryFlag");
	}
	
	@Override
	public int updateStatusFlag(String agentNo) throws Exception {
		return baseMapper.update(agentNo, "ShareProfitAgentLogMapper.updateStatusFlag");
	}

	@Override
	public List<ShareProfitAgentLog> getAgentsRetry(int currentTimestamp, String key) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("currentTimestamp", ImmutableMap.of("currentTimestamp",currentTimestamp));
		params.put("key", key);
		return baseMapper.selectList(params, "ShareProfitAgentLogMapper.getAgentsRetry");
	}

	@Override
	public String getRecommenderMoney(Map<String, Object> params) throws Exception {
		return baseMapper.selectOne(params, "EcmMzfWaterMapper.getRecommenderMoney");
	}

	@Override
	public List<EcmMzfAgentWater> getShareProfitResult(int id, String recNo) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("recNo", recNo);
		return baseMapper.selectList(params, "EcmMzfAgentWaterMapper.getShareProfitResult");
	}

	@Override
	public EcmMzfDrawWater getDrawWaterInfo(String drawCode) throws Exception {
		return baseMapper.selectOne(drawCode, "EcmMzfWaterMapper.getDrawWaterInfo");
	}

	@Override
	public int getCountCreateWaterId(Map<String, Object> params) throws Exception {
		return baseMapper.selectOne(params, "EcmMzfWaterMapper.getCountCreateWaterId");
	}
	
	
}
