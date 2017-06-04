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
import com.meiduimall.service.account.model.SubMemberIntegral;
import com.meiduimall.service.account.model.response.ResponseAccountBalance;
import com.meiduimall.service.account.model.response.ResponsePersonalConsumptionPoints;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.MSMembersService;
import com.meiduimall.service.account.util.Arith;
import com.meiduimall.service.account.util.DESC;

@Service
public class MSMembersServiceImpl implements MSMembersService {

	private final static Logger logger = LoggerFactory.getLogger(MSMembersServiceImpl.class);

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private AccountReportService accountReportService;

	@Override
	public MSMembers getMemberInfo(String memId) {
		return baseDao.selectOne(memId, "MSMembersMapper.getMemberInfo");
	}

	@Override
	public ResponseAccountBalance getAccountBalance(String memId) throws MdSysException {

		// 先查询用户是否存在
		if (!checkUserIsExistByMemId(memId)) {
			throw new ServiceException(ConstApiStatus.USER_NOT_EXIST);
		}

		// 目前暂时使用accountService提供的方法，不过这些方法还不确定是否正常，待确定后再调整
		// 全部积分，含冻结积分
		Double totalConsumePoints = accountReportService.getCurrentPointsByMemId(memId);
		// 冻结积分
/*		Double freezeConsumePoints = mSConsumePointsFreezeService.getFreezeConsumePoints(memId);*/
		Double freezeConsumePoints =0.00;
		// 可使用积分，不含冻结积分
//		Double useConsumePoints = accountService.getUseConsumePoints(memId);
		Double useConsumePoints = totalConsumePoints - freezeConsumePoints;
		
		MSAccountReport report = accountReportService.getTotalAndFreezeBalanceByMemId(memId);
		// 全部余额，含冻结余额
		Double totalConsumeMoney = report.getBalance();
		// 冻结余额
		Double freezeConsumeMoney = report.getFreezeBalance();
		// 可使用账户余额，不含冻结余额
		Double useConsumeMoney = totalConsumeMoney - freezeConsumeMoney;

		ResponseAccountBalance data = new ResponseAccountBalance();
		data.setAllPoints(String.valueOf(totalConsumePoints));
		data.setUsePoints(String.valueOf(useConsumePoints));
		data.setFreezePoints(String.valueOf(freezeConsumePoints));
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

		// 获取个人账户积分
		MSMembers members = baseDao.selectOne(memId, "MSMembersMapper.getQuantityByMemId");
		ResponsePersonalConsumptionPoints data = new ResponsePersonalConsumptionPoints();
		/*try {
			if (!Strings.isNullOrEmpty(members.getMemBasicAccountTotalQuantity())) {
				data.setAccountIntegral(DoubleCalculate
						.getFormalValueTwo(DESC.deyption(members.getMemBasicAccountTotalQuantity(), memId)));
			} else {
				data.setAccountIntegral("0.000");
			}
		} catch (MdSysException e) {
			logger.error("解密会员积分异常：" + e);
			throw new ServiceException(ConstApiStatus.DECRYPTION_EXCEPTION);
		}*/

		// 个人消费金额、粉丝团总消费
		MsPersonalConsumption consumption = baseDao.selectOne(memId, "MsPersonalConsumptionMapper.getEntityByMemId");
		String consumMoney = "0.00";
		String allChildMoney = "0.00";
		if (consumption != null) {
			/*consumMoney = DoubleCalculate.getFormalValueTwo(consumption.getPersonalMoney());
			allChildMoney = DoubleCalculate.getFormalValueTwo(consumption.getAllchildMoney());*/
		}
		data.setConsumMoney(consumMoney);
		data.setAllChildMoney(allChildMoney);

		// 个人消费返还、粉丝团总消费返还
		SubMemberIntegral integralVo = baseDao.selectOne(memId, "MsMemberIntegralMapper.selectConsumeByMemId");
		String consumptionPersonal = "0.00";
		String allChildIntegral = "0.00";
		if (integralVo != null) {
			try {
				// 消费返本累计
				double consumePerPrincipal = 0;
				if (!Strings.isNullOrEmpty(integralVo.getMintTotalConsumeReturn())) {
					consumePerPrincipal = Double.valueOf(DESC.deyption(integralVo.getMintTotalConsumeReturn(), memId));
				}
				// 消费返利累计（总）
				double consumeRebate = 0;
				if (!Strings.isNullOrEmpty(integralVo.getMintTotalConsumeProfit())) {
					consumeRebate = Double.valueOf(DESC.deyption(integralVo.getMintTotalConsumeProfit(), memId));
				}
				// 个人消费返利累计（个人）
				double perRebate = 0;
				if (!Strings.isNullOrEmpty(integralVo.getMintPersonalTotalConsumeProfit())) {
					perRebate = Double.valueOf(DESC.deyption(integralVo.getMintPersonalTotalConsumeProfit(), memId));
				}
				double xfc = Arith.add(consumePerPrincipal, perRebate);
				double allFansXfc = Arith.sub(consumeRebate, perRebate);
				if (allFansXfc < 0) {
					allFansXfc = 0.000;
				}
			/*	consumptionPersonal = DoubleCalculate.getFormalValueTwo(String.valueOf(xfc));
				allChildIntegral = DoubleCalculate.getFormalValueTwo(String.valueOf(allFansXfc));*/
			} catch (MdSysException e) {
				logger.error("解密会员积分异常：" + e);
				throw new ServiceException(ConstApiStatus.DECRYPTION_EXCEPTION);
			}
		}
		data.setConsumptionPersonal(consumptionPersonal);
		data.setAllChildIntegral(allChildIntegral);

		// 粉丝团人数
		int childPersons = 0;
		String memChildValues = members.getMemParentValue2();
		if (memChildValues != null && !"".equals(memChildValues) && !"null".equals(memChildValues)) {
			String[] memChildValue = memChildValues.split(",");
			childPersons = memChildValue.length;
		}
		data.setChildPersons(childPersons);

		// 最新XFC价格
		data.setPriceXFC("0.000");

		// 返回结果数据
		ResBodyData result = new ResBodyData();
		result.setData(data);
		result.setStatus(ConstApiStatus.SUCCESS);
		result.setMsg(ConstApiStatus.SUCCESS_C);
		return result;
	}
}
