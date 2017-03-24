package com.meiduimall.service.settlement.service;

import com.meiduimall.service.settlement.model.CreateBillLog;
import com.meiduimall.service.settlement.model.ShareProfitOrderLog;

/**
 * @author 许彦雄
 * desc:记录分润过程中的一些重要错误/关键日志
 */

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: ShareProfitLogService.java
 * Author:   许彦雄
 * Date:     2017年2月21日 下午6:15:47
 * Description: 记录订单分润出错日志信息服务
 */
public interface ShareProfitLogService {
	
	/**
	 * 功能描述:  订单分润相关日志
	 * Author: 许彦雄
	 * Date:   2017年2月28日 下午3:38:26   
	 * param orderLog
	 * param retryType
	 * param retryStatus
	 * return  
	 * throws Exception
	 */
	
	public void logShareProfitOrder(ShareProfitOrderLog orderLog,String retryType,Integer retryStatus) throws Exception;
	
	/**
	 * 功能描述:  账单生成相关日志
	 * Author: 许彦雄
	 * Date:   2017年2月28日 下午3:38:26   
	 * param createBillLog
	 * return  boolean
	 * throws Exception
	 */
	public boolean logCreateBillLog(CreateBillLog createBillLog) throws Exception;
	
	/**
	 * 功能描述:  移除重试标识
	 * Author: 许彦雄
	 * Date:   2017年2月28日 下午3:38:26   
	 * param orderSn
	 * return  boolean
	 * throws Exception
	 */
	public boolean removeRetryFlag(String orderSn)throws Exception;
	


}
