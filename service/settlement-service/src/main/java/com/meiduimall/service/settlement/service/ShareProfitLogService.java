package com.meiduimall.service.settlement.service;

import com.meiduimall.service.settlement.model.CreateBillLog;
import com.meiduimall.service.settlement.model.ShareProfitOrderLog;

/**
 * @author 许彦雄
 * desc:记录分润过程中的一些重要错误/关键日志
 */
public interface ShareProfitLogService {
	
	public void logShareProfitOrder(ShareProfitOrderLog orderLog,String retryType,Integer retryStatus) throws Exception;
	
	public boolean logCreateBillLog(CreateBillLog createBillLog) throws Exception;
	
	public boolean removeRetryFlag(String orderSn)throws Exception;
	


}
