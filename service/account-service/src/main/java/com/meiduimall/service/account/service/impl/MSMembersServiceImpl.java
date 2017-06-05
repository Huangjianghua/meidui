package com.meiduimall.service.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccountReport;
import com.meiduimall.service.account.model.MSMembers;
import com.meiduimall.service.account.model.MsPersonalConsumption;
import com.meiduimall.service.account.model.response.ResponseAccountBalance;
import com.meiduimall.service.account.model.response.ResponsePersonalConsumptionPoints;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.ConsumePointsFreezeInfoService;
import com.meiduimall.service.account.service.MSMembersService;
import com.meiduimall.service.account.util.DoubleCalculate;

@Service
public class MSMembersServiceImpl implements MSMembersService {

	private final static Logger logger = LoggerFactory.getLogger(MSMembersServiceImpl.class);

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private AccountReportService accountReportService;
	
	@Autowired
	private ConsumePointsFreezeInfoService consumePointsFreezeInfoService;

	@Override
	public MSMembers getMemberInfo(String memId) {
		return baseDao.selectOne(memId, "MSMembersMapper.getMemberInfo");
	}

	@Override
	public ResponseAccountBalance getAccountBalance(String memId) {

		// 先查询用户是否存在
		if (!checkUserIsExistByMemId(memId)) {
			throw new ServiceException(ConstApiStatus.USER_NOT_EXIST);
		}

		// 目前暂时使用accountService提供的方法，不过这些方法还不确定是否正常，待确定后再调整
		// 全部积分，含冻结积分--目前积分没有小数点，需要转为int
		int totalConsumePoints = 0;
		try {
			Double currentPoints = accountReportService.getCurrentPointsByMemId(memId);
			if(currentPoints != null){
				totalConsumePoints = currentPoints.intValue();
			}
		} catch (MdSysException e) {
			logger.error("获取个人账户积分，会员总积分解密异常：" + e);
		}
		// 冻结积分
		int freezeConsumePoints = 0;
		Double freezePoints = consumePointsFreezeInfoService.getFreezeUnFreezePointsSumByMemId(memId);
		if(freezePoints != null){
			freezeConsumePoints = freezePoints.intValue();
		}
		// 可使用积分，不含冻结积分（注意：这里是相加）
		int useConsumePoints = totalConsumePoints + freezeConsumePoints;
		
		MSAccountReport report = accountReportService.getTotalAndFreezeBalanceByMemId(memId);
		// 全部余额，含冻结余额
		Double totalConsumeMoney = DoubleCalculate.getFormalValueTwo(report.getBalance());
		// 冻结余额
		Double freezeConsumeMoney = DoubleCalculate.getFormalValueTwo(report.getFreezeBalance());
		// 可使用账户余额，不含冻结余额（注意：这里是相减）
		Double useConsumeMoney = DoubleCalculate.getFormalValueTwo(totalConsumeMoney - freezeConsumeMoney);

		ResponseAccountBalance data = new ResponseAccountBalance();
		data.setAllPoints(String.valueOf(totalConsumePoints));
		data.setUsePoints(String.valueOf(useConsumePoints));
		// 冻结积分freezeConsumePoints查出来的是负值，但是返回前端要变成正值，所以需要取绝对值
		data.setFreezePoints(String.valueOf(Math.abs(freezeConsumePoints)));
		data.setAllMoney(String.valueOf(totalConsumeMoney));
		data.setUseMoney(String.valueOf(useConsumeMoney));
		data.setFreezeMoney(String.valueOf(freezeConsumeMoney));
		return data;
	}

	@Override
	public boolean checkUserIsExistByMemId(String memId) {
		Integer count = baseDao.selectOne(memId, "MSMembersMapper.selectCountByMemId");
		if (count != null && count.intValue() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ResBodyData personalConsumptionPoints(String memId) {
		// 先查询用户是否存在
		if (!checkUserIsExistByMemId(memId)) {
			logger.info("当前用户在会员系统不存在，查询不到memId: " + memId);
			throw new ServiceException(ConstApiStatus.USER_NOT_EXIST);
		}

		ResponsePersonalConsumptionPoints data = new ResponsePersonalConsumptionPoints();
		
		// 获取个人账户积分
		int totalConsumePoints = 0;
		try {
			Double currentPoints = accountReportService.getCurrentPointsByMemId(memId);
			if(currentPoints != null){
				totalConsumePoints = currentPoints.intValue();
			}
		} catch (MdSysException e) {
			logger.error("获取个人账户积分，会员总积分解密异常：" + e);
		}
		data.setAccountIntegral(String.valueOf(totalConsumePoints));

		// 个人消费金额
		MsPersonalConsumption consumption = baseDao.selectOne(memId, "MsPersonalConsumptionMapper.getEntityByMemId");
		Double consumMoney = 0.00;
		if (consumption != null) {
			try {
				consumMoney = DoubleCalculate.getFormalValueTwo(Double.parseDouble(consumption.getPersonalMoney()));
			} catch (NumberFormatException e) {
				logger.error("获取个人消费金额异常：" + e);
			}
		}
		data.setConsumMoney(String.valueOf(consumMoney));

		// 粉丝团人数
		MSMembers members = baseDao.selectOne(memId, "MSMembersMapper.getChildValueByMemId");
		int childPersons = 0;
		String memChildValues = members.getMemParentValue2();
		if (!Strings.isNullOrEmpty(memChildValues)) {
			String[] memChildValue = memChildValues.split(",");
			if(memChildValue != null){
				childPersons = memChildValue.length;
			}
		}
		data.setChildPersons(childPersons);

		// 返回结果数据
		ResBodyData result = new ResBodyData();
		result.setData(data);
		result.setStatus(ConstApiStatus.SUCCESS);
		result.setMsg(ConstApiStatus.SUCCESS_C);
		return result;
	}
}
