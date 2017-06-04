package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.service.account.model.MSMemberConsumeHistory;
import com.meiduimall.service.account.model.request.MSMemberConsumeHistoryReq;

public interface MemberConsumeHistoryService {

	
	MSMemberConsumeHistory queryByOrderIdInfo(MSMemberConsumeHistoryReq MemberConsum);
	
	List<MSMemberConsumeHistory> listByOrderIdInfo(MSMemberConsumeHistoryReq MemberConsum);

	void save(MSMemberConsumeHistory mConHis);
}
