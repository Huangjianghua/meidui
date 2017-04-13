package com.meiduimall.service.settlement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
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

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: AgentServiceImpl.java
 * Author:   guidl
 * Date:     2017年3月24日 上午11:25:02
 * Description: 个代保证金分润、提现、流水相关
 */
@Service
public class AgentServiceImpl implements AgentService {
	
	@Autowired
	private BaseMapper baseMapper;

	
	@Override
	public int insertAgentWater(EcmMzfAgentWater agentWater) throws ServiceException{
		try {
			return baseMapper.insert(agentWater, "EcmMzfAgentWaterMapper.insertAgentWater");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	
	/**
	 * 暂时用synchronized同步，后期再优化
	 */
	@Override
	public synchronized int updateAccount(EcmMzfAccount account) throws ServiceException {
		try {
			return baseMapper.update(account, "EcmMzfAccountMapper.updateAccountByCode");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	
	@Override
	public int insertWater(EcmMzfWater water) throws ServiceException {
		try {
			return baseMapper.insert(water, "EcmMzfWaterMapper.insertWater");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public int updateScoreStatusByCode(int id, String code, int score) throws ServiceException {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("id", id);
			params.put("code", code);
			params.put("score", score);
			return baseMapper.update(params, "EcmMzfAgentWaterMapper.updateScoreStatusByCode");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public int insertAccount(EcmMzfAccount account) throws ServiceException {
		try {
			return baseMapper.insert(account, "EcmMzfAccountMapper.insertAccount");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public EcmMzfAccount findAccountByCode(String code) throws ServiceException {
		try {
			return baseMapper.selectOne(code, "EcmMzfAccountMapper.findAccountByCode");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public EcmMzfAgentWater findAgentWaterByCode(String code) throws ServiceException {
		try {
			return baseMapper.selectOne(code, "EcmMzfAgentWaterMapper.findAgentWaterByCode");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public List<EcmMzfAgentWater> findAgentWaterByAgentCode(int id) throws ServiceException {
		try {
			return baseMapper.selectList(id, "EcmMzfAgentWaterMapper.findAgentWaterByAgentCode");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public List<EcmMzfAgentWater> getAgentWaterScore() throws ServiceException {
		try {
			return baseMapper.selectList(null, "EcmMzfAgentWaterMapper.getAgentWaterScore");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	
	@Override
	public int insertStoreRecord(EcmMzfStoreRecord ecmMzfStoreRecord) throws ServiceException {
		try {
			return baseMapper.insert(ecmMzfStoreRecord, "EcmStoreMapper.insertStoreRecord");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public List<EcmSystemSetting> quertSharefit() throws ServiceException {
		try {
			return baseMapper.selectList(null, "ShareProfitMapper.quertSharefit");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	

	@Override
	public List<EcmMzfWater> getWaterList(Map<String, Object> params) throws ServiceException {
		try {
			return baseMapper.selectList(params, "EcmMzfWaterMapper.getWaterList");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public int getWaterCount(Map<String, Object> params) throws ServiceException {
		try {
			return baseMapper.selectOne(params, "EcmMzfWaterMapper.getWaterCount");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	
	@Override
	public EcmMzfWater getWaterDetailByWaterId(String waterId, String waterType) throws ServiceException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("waterId", waterId);
			params.put("waterType", waterType);
			return baseMapper.selectOne(params, "EcmMzfWaterMapper.getWaterDetailByWaterId");
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	
	@Override
	public Draw getDrawDetailByDrawCode(String drawCode) throws ServiceException {
		return baseMapper.selectOne(drawCode, "EcmMzfWaterMapper.getDrawDetailByDrawCode");
	}

	
	@Override
	public int insertShareProfitAgentLog(ShareProfitAgentLog shareProfitAgentLog) throws ServiceException {
		return baseMapper.insert(shareProfitAgentLog, "ShareProfitAgentLogMapper.insertShareProfitAgentLog");
	}
	
	
	@Override
	public int updateRetryFlag(String agentNo) throws ServiceException {
		return baseMapper.update(agentNo, "ShareProfitAgentLogMapper.updateRetryFlag");
	}
	
	
	@Override
	public int updateStatusFlag(String agentNo) throws ServiceException {
		return baseMapper.update(agentNo, "ShareProfitAgentLogMapper.updateStatusFlag");
	}

	
	@Override
	public List<ShareProfitAgentLog> getAgentsRetry(int currentTimestamp, String key) throws ServiceException {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("currentTimestamp", currentTimestamp);
		params.put("key", key);
		return baseMapper.selectList(params, "ShareProfitAgentLogMapper.getAgentsRetry");
	}

	
	@Override
	public String getRecommenderMoney(Map<String, Object> params) throws ServiceException {
		return baseMapper.selectOne(params, "EcmMzfWaterMapper.getRecommenderMoney");
	}

	
	@Override
	public List<EcmMzfAgentWater> getShareProfitResult(int id, String recNo) throws ServiceException {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("recNo", recNo);
		return baseMapper.selectList(params, "EcmMzfAgentWaterMapper.getShareProfitResult");
	}

	
	@Override
	public EcmMzfDrawWater getDrawWaterInfo(String drawCode) throws ServiceException {
		return baseMapper.selectOne(drawCode, "EcmMzfWaterMapper.getDrawWaterInfo");
	}

	
	@Override
	public int getCountCreateWaterId(Map<String, Object> params) throws ServiceException {
		return baseMapper.selectOne(params, "EcmMzfWaterMapper.getCountCreateWaterId");
	}
	
	
}
