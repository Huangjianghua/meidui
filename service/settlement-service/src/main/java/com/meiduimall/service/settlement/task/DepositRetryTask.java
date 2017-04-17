package com.meiduimall.service.settlement.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.settlement.common.CronExpression;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.model.EcmAgent;
import com.meiduimall.service.settlement.model.EcmSystemSetting;
import com.meiduimall.service.settlement.model.ShareProfitAgentLog;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.util.DateUtil;


/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: DepositRetryTask.java
 * Author:   桂冬玲
 * Date:     2017年2月26日 下午6:15:47
 * Description: 保证金分润重试机制 定时任务
 */
@Service
public class DepositRetryTask {
	
	private static final Logger logger = LoggerFactory.getLogger(DepositRetryTask.class);
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private AsyncTaskService asyncTaskService;
	
	/**
	 * 重试送积分定时任务
	 */
	@Scheduled(cron = CronExpression.EVERY_10_MINUTE)
	@Async
	public void retryShareDeposit(){
		try {
			
			//查询基本分润配置
			List<EcmSystemSetting> settingList = agentService.quertSharefit();
			Map<String, String> systemSetting = ShareProfitUtil.queryShareProfit(settingList);
			int score = Integer.parseInt(systemSetting.get(ShareProfitConstants.NEWBIE_PERSON_POINT));//新加盟个代获得积分 6500
			
			//获取新个代送积分失败日志记录
			Map<String, Object> result = getAgentRetry();
			
			if (result != null && !result.isEmpty()) {
				String retryType = "";
				
				for (Map.Entry<String, Object> entry : result.entrySet()) {
					String shareProfitJsonObj = RedisUtils.get(ShareProfitConstants.REDIS_KEY_PRIFIX_AGENT + entry.getKey());
					EcmAgent ecmAgent = JsonUtils.jsonToBean(shareProfitJsonObj, EcmAgent.class);
					
					if (ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND.equals(entry.getValue())) {
						retryType = ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND;
					}
					
					//调用新个代送积分方法
					asyncTaskService.updateScore(ecmAgent, score, ShareProfitConstants.SHARE_PROFIT_SOURCE_CACHE, retryType);
				}
			}
		} catch (ServiceException e) {
			logger.error("新个代重试送积分失败：{}", e);
		}
		
	}
	
	/**
	 * 获取新个代送积分失败日志记录
	 * @return
	 * @
	 */
	private Map<String, Object> getAgentRetry() {
		
		int currentTimestamp = DateUtil.getCurrentTimeSec();
		
		List<String> list = new ArrayList<String>();
		list.add("share5MinRetry");
		list.add("share30MinRetry");
		list.add("share12HoursRetry");
		
		List<ShareProfitAgentLog> share5MinRetry = new ArrayList<ShareProfitAgentLog>();//获取5分钟后重新送积分的个代
		List<ShareProfitAgentLog> share30MinRetry = new ArrayList<ShareProfitAgentLog>();//获取30分钟后重新送积分的个代
		List<ShareProfitAgentLog> share12HoursRetry = new ArrayList<ShareProfitAgentLog>();//获取12小时后重新送积分的个代
		
		final Map<String, Object> retryAgents = Maps.newHashMap();
		
		
		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i);
			if("share5MinRetry".equals(key)){
				share5MinRetry = agentService.getAgentsRetry(currentTimestamp, key);
			}else if("share30MinRetry".equals(key)){
				share30MinRetry = agentService.getAgentsRetry(currentTimestamp, key);
			}else if("share12HoursRetry".equals(key)){
				share12HoursRetry = agentService.getAgentsRetry(currentTimestamp, key);
			}
		}
		
		if (share12HoursRetry != null && !share12HoursRetry.isEmpty()) {
			for (ShareProfitAgentLog agentLog : share12HoursRetry) {
				retryAgents.put(agentLog.getAgentNo(), ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND);
			}
		}

		if (share30MinRetry != null && !share30MinRetry.isEmpty()) {
			for (ShareProfitAgentLog agentLog : share30MinRetry) {
				retryAgents.put(agentLog.getAgentNo(), "");
			}
		}

		if (share5MinRetry != null && !share5MinRetry.isEmpty()) {
			for (ShareProfitAgentLog agentLog : share5MinRetry) {
				retryAgents.put(agentLog.getAgentNo(), "");
			}
		}
		return retryAgents;
	}
}
