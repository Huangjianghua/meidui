package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.service.account.model.MSMemberConsumeRecords;
import com.meiduimall.service.account.model.request.MSMemberConsumeRecordsReq;

public interface MSMemberConsumeRecordsService {

	
	MSMemberConsumeRecords queryByOrderIdInfo(MSMemberConsumeRecordsReq MemberConsum);
	
	List<MSMemberConsumeRecords> listByOrderIdInfo(MSMemberConsumeRecordsReq MemberConsum);

	void save(MSMemberConsumeRecords mConHis);
}
