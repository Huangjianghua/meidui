package com.meiduimall.service.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meiduimall.service.account.constant.ApplicationConstant;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.AccountServices;
import com.meiduimall.service.account.util.DateUtil;
import com.meiduimall.service.account.util.DoubleCalculate;

/**
 * 类名:  AccountFreezeDetailService<br>
 * 描述:  账户变更冻结明细业务服务层，与账户冻结明细相关的都定义在此 <br>
 * 创建时间: 2017-02-23
 */
@Component
public class AccountFreezeDetailServiceImpl implements AccountFreezeDetailService{
	
	private final static Logger logger=LoggerFactory.getLogger(AccountDetailServiceImpl.class);

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private AccountServices accountServices;
	
	@Override
	public void saveAccountFreezeDetail(String memId, String orderId,
			String accountId, String accountType, String tradeType,
			String tradeAmount, Date tradeDate, String freezeBalance,
			String remark) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("accountId", accountId);
		paramsMap.put("accountType", accountType);
		paramsMap.put("tradeType", tradeType);
		paramsMap.put("tradeAmount", tradeAmount);
		paramsMap.put("freezeBalance", freezeBalance);
		paramsMap.put("remark", remark);
		paramsMap.put("inOrOut", "1");
		paramsMap.put("tradeDate", DateUtil.format(tradeDate,DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		try {
			baseDao.insert(paramsMap, "MSAccountMapper.insertAccountFreezeDetail");
		} catch (Exception e) {
			logger.error("写入账户冻结明细出现错误-1001，会员编号：%s，订单编号：%s，错误信息：%s", 
					memId, orderId, e.getMessage());
		}
	}

	@Override
	public void saveAccountUnFreezeDetail(String memId, String orderId,
			String accountId, String accountType, String tradeType,
			String tradeAmount, Date tradeDate, String freezeBalance,
			String remark) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("accountId", accountId);
		paramsMap.put("accountType", accountType);
		paramsMap.put("tradeType", tradeType);
		paramsMap.put("tradeAmount", tradeAmount);
		paramsMap.put("freezeBalance", freezeBalance);
		paramsMap.put("remark", remark);
		paramsMap.put("inOrOut", "-1");
		paramsMap.put("tradeDate", DateUtil.format(tradeDate,DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		try {
			baseDao.insert(paramsMap, "MSAccountMapper.insertAccountFreezeDetail");
		} catch (Exception e) {
			logger.error("写入账户冻结明细出现错误-1002，会员编号：%s，订单编号：%s，错误信息：%s", 
					memId, orderId, e.getMessage());
		}
	}
	
	@Override
	public void saveFreezePoints(String memId, String orderId,
			String freezePoints, String operator, String remark) {
		/** 冻结后积分余额 */
		String balancePoints = String.valueOf(DoubleCalculate.sub(accountServices.getUseConsumePoints(memId), 
				Double.valueOf(freezePoints)));
		
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("freezeType", ApplicationConstant.POINTS_FREEZE_TYPE_DJ);
		paramsMap.put("freezePoints", ("-"+freezePoints));
		paramsMap.put("balancePoints", balancePoints);
		paramsMap.put("operator", operator);
		paramsMap.put("remark", remark);
		
		try {
			baseDao.insert(paramsMap, "MSAccountMapper.insertPointsFreezeUnFreezeRecord");
		} catch (Exception e) {
			logger.error("写入积分冻结明细出现错误-2001，会员编号：%s，订单编号：%s，错误信息：%s", memId, orderId, e.getMessage());
		}
	}

	@Override
	public void saveUnFreezePoints(String memId, String orderId,
			String unFreezePoints, String operator, String remark) {
		/** 解冻后积分余额 */
		String balancePoints = String.valueOf(DoubleCalculate.add(accountServices.getUseConsumePoints(memId), 
				Double.valueOf(unFreezePoints)));
		
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("freezeType", ApplicationConstant.POINTS_FREEZE_TYPE_DJ);
		paramsMap.put("freezePoints", unFreezePoints);
		paramsMap.put("balancePoints", balancePoints);
		paramsMap.put("operator", operator);
		paramsMap.put("remark", remark);
		
		try {
			baseDao.insert(paramsMap, "MSAccountMapper.insertPointsFreezeUnFreezeRecord");
		} catch (Exception e) {
			logger.error("写入积分冻结明细出现错误-2002，会员编号：%s，订单编号：%s，错误信息：%s", memId, orderId, e.getMessage());
		}
	}


}
