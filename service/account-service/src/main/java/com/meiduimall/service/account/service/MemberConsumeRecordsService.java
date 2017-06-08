package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.service.account.model.MSMemberConsumeRecords;
import com.meiduimall.service.account.model.request.MSMemberConsumeRecordsReq;

/**
 * 会员消费记录业务逻辑接口
 * @author chencong
 *
 */
public interface MemberConsumeRecordsService {

	
	
	List<MSMemberConsumeRecords> listByOrderIdInfo(MSMemberConsumeRecordsReq MemberConsum);

	void insertConsumeRecord(MSMemberConsumeRecords model);
	
	/**
	 * 根据条件查询会员消费记录信息
	 * @param MemberConsum 消费信息
	 * @return
	 */
	MSMemberConsumeRecords queryByOrderIdInfo(MSMemberConsumeRecordsReq MemberConsum);
	
	void save(MSMemberConsumeRecordsReq mmt);
	
	/**
	 * 将订单状态为1(已支付)的订单更新为订单状态为2(已退单)
	 * @param mmt 消费信息
	 */
	void updateOrderStatus(MSMemberConsumeRecordsReq mmt);
}