package com.meiduimall.service.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSMemberConsumeRecords;
import com.meiduimall.service.account.model.request.MSMemberConsumeRecordsReq;
import com.meiduimall.service.account.service.MemberConsumeRecordsService;

/**
 * 会员消费记录业务逻辑接口{@link=MemberConsumeRecordsService}实现类
 * @author chencong
 *
 */
@Service
public class MemberConsumeRecordsServiceImpl implements MemberConsumeRecordsService {
	

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
	public void insertConsumeRecord(MSMemberConsumeRecords model) {
		baseDao.insert(model,"MSMemberConsumeRecordsMapper.insertConsumeRecord");
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
