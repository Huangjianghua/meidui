package com.meiduimall.service.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.service.MSConsumePointsFreezeService;

@Component
public class MSConsumePointsFreezeServiceImpl implements MSConsumePointsFreezeService {

	@Autowired
	private BaseDao  baseDao;
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

}
