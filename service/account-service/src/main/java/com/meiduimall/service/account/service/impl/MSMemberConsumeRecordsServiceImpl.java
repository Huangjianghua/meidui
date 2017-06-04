package com.meiduimall.service.account.service.impl;

import java.util.List;

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
	public List<MSMemberConsumeRecords> listByOrderIdInfo(MSMemberConsumeRecordsReq MemberConsum) {

		return baseDao.selectList(MemberConsum, "MSMemberConsumeRecordsMapper.getConsumeRecordsByContidion");
	}

	@Override
	public void save(MSMemberConsumeRecords mConHis) {
		 
		baseDao.insert(mConHis, "MSMemberConsumeRecordsMapper.insertConsumeRecords");
		
	}

}
