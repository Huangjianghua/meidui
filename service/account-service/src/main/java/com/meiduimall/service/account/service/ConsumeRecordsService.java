package com.meiduimall.service.account.service;

import com.meiduimall.service.account.model.MSMemberConsumeRecords;

/**
 * 消费记录业务逻辑接口
 * @author chencong
 *
 */
public interface ConsumeRecordsService {
	
	/**
	 * 根据订单号，订单来源，订单状态查询消费信息
	 * @param orderId 订单号
	 * @param orderSource 订单来源
	 * @param orderStatus 订单状态
	 * @return 消费信息
	 */
	public MSMemberConsumeRecords getConsumeRecords(String orderId,String orderSource,Integer orderStatus);
	

}
