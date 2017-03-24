package com.meiduimall.service.settlement.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.pagehelper.StringUtil;
import com.google.common.collect.ImmutableMap;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.service.settlement.common.CronExpression;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.ShareProfitOrderLog;
import com.meiduimall.service.settlement.util.DateUtil;

/**
 * 
 * @author 许彦雄
 * desc:分润重试机制定时任务
 */
@Component
public class ShareProfitRetryTask {
	
	private static final Logger log = LoggerFactory.getLogger(ShareProfitRetryTask.class);
	
	@Autowired
	private BaseMapper baseMapper;
	
	@Autowired
	private AsyncTaskService asyncTaskService;
	
	@Scheduled(cron = CronExpression.EVERY_10_MINUTE)
	@Async
	public void retryOrderShareProfit(){
		
		try {
			//从ecm_mzf_log_shareprofit_order表中查询需要重新分润的订单
			Map<String,String> map=getOrders2Retry();
			
			if(map!=null && !map.isEmpty()){
				for(Map.Entry<String, String> entry:map.entrySet()){
					String orderSn=entry.getKey();
					String shareProfitJsonObj=JedisUtil.getJedisInstance().execGetFromCache(ShareProfitConstants.REDIS_KEY_PREFIX_ORDER+orderSn);
					EcmMzfShareProfit  shareProfit=null;
					if(!StringUtil.isEmpty(shareProfitJsonObj)){ 
						shareProfit=JsonUtils.jsonToBean(shareProfitJsonObj, EcmMzfShareProfit.class);	
						
						String retryType="";
						if(ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND.equals(entry.getValue())){
							retryType=ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND;
						}
						
						if(shareProfit!=null){
							asyncTaskService.updateScore2MemberSystem(shareProfit, ShareProfitConstants.SHARE_PROFIT_SOURCE_CACHE, retryType);
						}
					}

				}
			}		
			
		} catch (Exception e) {
			log.error("retryOrderShareProfit() job got error:"+e.getMessage());
		}
		

	}
	
	private Map<String, String> getOrders2Retry() throws Exception {
		Integer currentTimestampSec=DateUtil.getCurrentTimeSec();
		log.info("current timestamp sec:{},Date:{}",currentTimestampSec,DateUtil.getCurrentTime());
		List<ShareProfitOrderLog> shareProfit5MinOrder2Retry =baseMapper.selectList(ImmutableMap.of("currentTimestamp",currentTimestampSec), "ShareProfitOrderLogMapper.get5MinOrders2Retry");
		List<ShareProfitOrderLog> shareProfit30MinOrder2Retry =baseMapper.selectList(ImmutableMap.of("currentTimestamp",currentTimestampSec), "ShareProfitOrderLogMapper.get30MinOrders2Retry");
		List<ShareProfitOrderLog> shareProfit12HOrder2Retry =baseMapper.selectList(ImmutableMap.of("currentTimestamp",currentTimestampSec), "ShareProfitOrderLogMapper.get12HOrders2Retry");
		
		final Map<String,String> retryOrders=new HashMap<String,String>();
		//注意retryOrders存放orderSn数据要安装这样的顺序:12H->30Min->5Min,因为要过滤掉已经重试过但失败的orderSn。
		if(shareProfit12HOrder2Retry!=null && !shareProfit12HOrder2Retry.isEmpty()){
			for(ShareProfitOrderLog orderLog:shareProfit12HOrder2Retry){
				log.info("shareProfit12HOrder2Retry orderSn:{}",orderLog.getOrderSn());
				retryOrders.put(orderLog.getOrderSn(), ShareProfitConstants.SHARE_PROFIT_RETRY_TYPE_FINAL_ROUND);
			}
		}
		
		if(shareProfit30MinOrder2Retry!=null && !shareProfit30MinOrder2Retry.isEmpty()){
			for(ShareProfitOrderLog orderLog:shareProfit30MinOrder2Retry){
				log.info("shareProfit30MinOrder2Retry orderSn:{}",orderLog.getOrderSn());
				retryOrders.put(orderLog.getOrderSn(), "");
			}
		}
		
		if(shareProfit5MinOrder2Retry!=null && !shareProfit5MinOrder2Retry.isEmpty()){
			for(ShareProfitOrderLog orderLog:shareProfit5MinOrder2Retry){
				log.info("shareProfit5MinOrder2Retry orderSn:{}",orderLog.getOrderSn());
				retryOrders.put(orderLog.getOrderSn(), "");
			}
		}
		return retryOrders;
	}
	
}
