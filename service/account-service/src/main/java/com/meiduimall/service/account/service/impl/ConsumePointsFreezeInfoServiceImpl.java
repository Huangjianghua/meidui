package com.meiduimall.service.account.service.impl;

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
		return baseDao.selectOne(memId,"MSConsumePointsFreezeInfoMapper.getFreezeUnFreezePointsSumByMemId");
	}

	@Override
	public void insertConsumePointsFreezeInfo(MSConsumePointsFreezeInfo model,String freezeType) throws MdSysException {
		String pointsBalance=null;
		if(ConstPointsChangeType.POINTS_FREEZE_TYPE_DJ.getCode().equals(freezeType)){
			pointsBalance = String.valueOf(DoubleCalculate.sub(accountReportService.getAvailablePoints(model.getMemId()), 
					Double.valueOf(model.getMcpfConsumePoints())));
			model.setMcpfConsumePoints(ConstSpecialSymbol.SUB+model.getMcpfConsumePoints());
		}
		else if (ConstPointsChangeType.POINTS_FREEZE_TYPE_JD.getCode().equals(freezeType)) {
			pointsBalance = String.valueOf(DoubleCalculate.add(accountReportService.getAvailablePoints(model.getMemId()), 
					Double.valueOf(model.getMcpfConsumePoints())));
		}
		else {
			throw new ServiceException(ConstApiStatus.FREEZE_TYPE_UNNORMAL);
		}
		model.setMcpfConsumePointsBalance(pointsBalance);
		baseDao.insert(model,"MSAccountMapper.insertConsumePointsFreezeInfo");
	}

}
