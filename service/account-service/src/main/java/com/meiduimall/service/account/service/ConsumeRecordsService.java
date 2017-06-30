package com.meiduimall.service.account.service;

import java.util.Map;

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
	 * @return 会员消费信息记录表实体类
	 */
	MSMemberConsumeRecords getConsumeRecords(String orderId,String orderSource,Integer orderStatus);
	
	/**
	 * 插入会员消费记录
	 * @param model 会员消费信息记录表实体类
	 */
	void insertConsumeRecords(MSMemberConsumeRecords model);
	
	/**
	 * 更新消费记录的订单状态
	 * @param mapCondition 更新条件
	 */
	void updateOrderStatus(Map<String,Object> mapCondition); 
}
