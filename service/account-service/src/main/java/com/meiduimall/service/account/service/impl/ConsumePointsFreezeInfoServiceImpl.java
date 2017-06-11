package com.meiduimall.service.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstPointsChangeType;
import com.meiduimall.service.account.constant.ConstSpecialSymbol;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSConsumePointsFreezeInfo;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.ConsumePointsFreezeInfoService;
import com.meiduimall.service.account.util.DoubleCalculate;

/**
 * 积分冻结解冻明细业务逻辑接口{@link=ConsumePointsFreezeInfoService}实现类
 * @author chencong
 *
 */
@Service
public class ConsumePointsFreezeInfoServiceImpl implements ConsumePointsFreezeInfoService {

	@Autowired
	private BaseDao  baseDao;
	
	@Autowired
	private AccountReportService accountReportService;
	
	@Override
	public Double getFreezeUnFreezePointsSumByMemId(String memId) {
		Double sum=baseDao.selectOne(memId,"MSConsumePointsFreezeInfoMapper.getFreezeUnFreezePointsSumByMemId");
		return sum==null?0.00:sum;
	}

	@Override
	public void insertConsumePointsFreezeInfo(MSConsumePointsFreezeInfo model,String freezeType) throws MdSysException {
		//积分余额
		String pointsBalance=null;
		if(ConstPointsChangeType.POINTS_FREEZE_TYPE_DJ.getCode().equals(freezeType)){
			//计算出冻结后的积分余额，如果是冻结就相减
			pointsBalance = String.valueOf(DoubleCalculate.sub(accountReportService.getAvailablePoints(model.getMemId()), 
					Double.valueOf(model.getMcpfConsumePoints())));
			//如果是冻结，则冻结积分存负数
			model.setMcpfConsumePoints(ConstSpecialSymbol.SUB+model.getMcpfConsumePoints());
		}
		else if (ConstPointsChangeType.POINTS_FREEZE_TYPE_JD.getCode().equals(freezeType)) {
			//计算出解冻后的积分余额，如果是解冻就相加
			pointsBalance = String.valueOf(DoubleCalculate.add(accountReportService.getAvailablePoints(model.getMemId()), 
					Double.valueOf(model.getMcpfConsumePoints())));
		}
		else {
			throw new ServiceException(ConstApiStatus.FREEZE_TYPE_UNNORMAL);
		}
		model.setMcpfConsumePointsBalance(pointsBalance);
		model.setMcpfFreezeType(freezeType);
		baseDao.insert(model,"MSConsumePointsFreezeInfoMapper.insertConsumePointsFreezeInfo");
	}

	@Override
	public List<MSConsumePointsFreezeInfo> getRecordsByOrderId(String orderId) {
		return baseDao.selectList(orderId,"MSConsumePointsFreezeInfoMapper.getRecordsByOrderId");
	}

	@Override
	public MSConsumePointsFreezeInfo getPointsFreezeByOrderId(String orderId) {
		return baseDao.selectOne(orderId,"MSConsumePointsFreezeInfoMapper.getRecordsByOrderId");
	}

}
