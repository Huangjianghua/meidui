package com.meiduimall.service.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.MSConsumePointsFreezeService;

@Service
public class MSConsumePointsFreezeServiceImpl implements MSConsumePointsFreezeService {

	@Autowired
	private BaseDao  baseDao;
	
	@Autowired
	private AccountFreezeDetailService  accountFreezeDetailService;
	
	@Override
	public Double getConsumePoints(String memId) throws Exception {
		/*MemberGet member = baseDao.selectOne(memId,"MemberMapper.getPhoneAndAccountScoreByMemId");*/
		String member=null;
		/*** 计算积分余额 = 冻结前当前积分余额-当前冻结积分 **/
		/** 冻结和解冻积分的总和 */
		Double fzPoints = queryFreezePoints(memId);
		/** 当前积分余额 */
		Double realPoints = Double.valueOf("0");
	/*	try{
			realPoints = DoubleCalculate.add((fzPoints),
					Double.valueOf(member.getMemBasicAccountTotalQuantity()));
		}catch(Exception e){
		}*/
		return realPoints;
	}
	@Override
	public Double queryFreezePoints(String memId) throws Exception {
		List  list = baseDao.selectList(memId,"MSConsumePointsFreezeInfoMapper.findByMemId");
		Double mcpfConsumePoints = (double) 0;
		if (list != null && list.size() > 0) {
			mcpfConsumePoints = (Double) list.get(0);
		}
		return mcpfConsumePoints;
	}
	
	@Override
	public Double getFreezeConsumePoints(String memId) {
		/** 冻结和解冻积分的总和 */
		Double realPoints = Double.valueOf("0");
		try{
			String freezeAccountPoint = baseDao.selectOne(memId, "MSConsumePointsFreezeInfoMapper.getFreezeUnFreezePointsSumByMemId");
			realPoints = Double.valueOf(freezeAccountPoint);
		}catch(Exception e){
			realPoints = Double.valueOf("0");
		}
		return realPoints;
	}
	
	@Override
	public boolean addMDConsumePointsFreezeAndDetail(String memId, String consumePoints, String orderId,
			String orderSource, String operatorType, String operator, String remark) {
		accountFreezeDetailService.saveFreezePoints(memId, orderId, consumePoints, operator, remark);
		return true;
	}

	@Override
	public boolean cutMDConsumePointsFreezeAndDetail(String memId, String consumePoints, String orderId,
			String orderSource, String operatorType, String operator, String remark) {
		accountFreezeDetailService.saveUnFreezePoints(memId, orderId, consumePoints, operator, remark);
		return true;
	}
	

}
