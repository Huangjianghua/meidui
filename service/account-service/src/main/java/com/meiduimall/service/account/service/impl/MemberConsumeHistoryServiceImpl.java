package com.meiduimall.service.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSMemberConsumeHistory;
import com.meiduimall.service.account.model.request.MSMemberConsumeHistoryReq;
import com.meiduimall.service.account.service.MemberConsumeHistoryService;

@Component
public class MemberConsumeHistoryServiceImpl implements MemberConsumeHistoryService {
	

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public MSMemberConsumeHistory queryByOrderIdInfo(MSMemberConsumeHistoryReq MemberConsum) {
		 
		return baseDao.selectOne(MemberConsum, "MSMemberConsumeHistoryMapper.queryByOrderIdInfo");
	}

	@Override
	public List<MSMemberConsumeHistory> listByOrderIdInfo(MSMemberConsumeHistoryReq MemberConsum) {

		return baseDao.selectList(MemberConsum, "MSMemberConsumeHistoryMapper.queryByOrderIdInfo");
	}

	@Override
	public void save(MSMemberConsumeHistory mConHis) {
		 
		baseDao.insert(mConHis, "MSMemberConsumeHistoryMapper.insertMemberConsumeHistory");
		
	}

}
