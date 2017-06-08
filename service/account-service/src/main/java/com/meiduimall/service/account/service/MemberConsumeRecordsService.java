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

	
	MSMemberConsumeRecords queryByOrderIdInfo(MSMemberConsumeRecordsReq MemberConsum);
	
	List<MSMemberConsumeRecords> listByOrderIdInfo(MSMemberConsumeRecordsReq MemberConsum);

	void insertConsumeRecord(MSMemberConsumeRecords model);
}
