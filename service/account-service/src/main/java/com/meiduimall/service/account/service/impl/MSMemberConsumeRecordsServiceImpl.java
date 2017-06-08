package com.meiduimall.service.account.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSMemberConsumeRecords;
import com.meiduimall.service.account.model.request.MSMemberConsumeRecordsReq;
import com.meiduimall.service.account.service.MSMemberConsumeRecordsService;

@Component
public class MSMemberConsumeRecordsServiceImpl implements MSMemberConsumeRecordsService {
	

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public MSMemberConsumeRecords queryByOrderIdInfo(MSMemberConsumeRecordsReq MemberConsum) {
		return baseDao.selectOne(MemberConsum, "MSMemberConsumeRecordsMapper.getConsumeRecordsByContidion");
	}

	@Override
	public void save(MSMemberConsumeRecordsReq mmt) {
		baseDao.insert(mmt, "MSMemberConsumeRecordsMapper.insertConsumeRecords");
	}

	@Override
	public void updateOrderStatus(MSMemberConsumeRecordsReq mmt) {
		baseDao.update(mmt, "MSMemberConsumeRecordsMapper.updateOrderStatus");
	}

}
