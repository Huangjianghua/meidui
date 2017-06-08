package com.meiduimall.service.account.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.account.model.MSMemberConsumeRecords;
import com.meiduimall.service.account.model.request.MSMemberConsumeRecordsReq;

/**
 * 会员消费记录业务逻辑接口
 * @author chencong
 *
 */
public interface MemberConsumeRecordsService {

	
	
	List<MSMemberConsumeRecords> listByOrderIdInfo(MSMemberConsumeRecordsReq MemberConsum);

	/**
	 * 插入消费记录
	 * @param model 会员消费记录表实体
	 */
	void insertConsumeRecord(MSMemberConsumeRecords model);
	
	/**
	 * 根据条件查询会员消费记录信息
	 * @param MemberConsum 消费信息
	 * @return
	 */
	MSMemberConsumeRecords queryByOrderIdInfo(MSMemberConsumeRecordsReq MemberConsum);
	
	/**
	 * 更新消费记录的订单状态
	 * @param mapContion 更新条件
	 */
	void updateOrderStatus(Map<String,Object> mapContion);
}
