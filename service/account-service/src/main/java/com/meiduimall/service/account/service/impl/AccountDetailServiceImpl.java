package com.meiduimall.service.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccountDetail;
import com.meiduimall.service.account.service.AccountDetailService;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.util.DateUtil;
import com.meiduimall.service.account.util.DoubleCalculate;


/**
 * 账户明细操作接口{@link=AccountDetailService}实现类
 * @author chencong
 *
 */
@Service
public class AccountDetailServiceImpl implements AccountDetailService{
	
	private final static Logger logger=LoggerFactory.getLogger(AccountDetailServiceImpl.class);

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private AccountReportService accountReportService;
	
	@Override
	public void insertAccountDetail(MSAccountDetail model) {
		baseDao.insert(model,"MSAccountDetailMapper.insertAccountDetail");
	}
	
	@Override
	public void saveAddAccountDetail(String memId, String orderId,
			String accountId, String accountType, String tradeType,
			String tradeAmount, Date tradeDate, String balance, String remark) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("accountId", accountId);
		paramsMap.put("accountType", accountType);
		paramsMap.put("tradeType", tradeType);
		paramsMap.put("tradeAmount", tradeAmount);
		paramsMap.put("balance", balance);
		paramsMap.put("remark", remark);
		paramsMap.put("inOrOut", "1");
		paramsMap.put("tradeDate", DateUtil.format(tradeDate,DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		try {
			baseDao.insert(paramsMap, "MSAccountMapper.insertAccountDetail");
		} catch (Exception e) {
			logger.error("写入账户变动明细出现错误-1001，会员编号：%s，订单编号：%s，错误信息：%s", 
					memId, orderId, e.getMessage());
		}
	}

	@Override
	public void saveCutAccountDetail(String memId, String orderId,
			String accountId, String accountType, String tradeType,
			String tradeAmount, Date tradeDate, String balance, String remark) {
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("accountNo", accountId);
		paramsMap.put("tradeType", tradeType);
		paramsMap.put("tradeAmount", tradeAmount);
		paramsMap.put("inOrOut", "-1");
		paramsMap.put("remark", remark);
		paramsMap.put("createUser", "system");
		paramsMap.put("createDate", new Date());
		paramsMap.put("updateUser", "system");
		paramsMap.put("updateDate", new Date());
		paramsMap.put("balance", balance);
		paramsMap.put("businessNo", orderId);
		paramsMap.put("tradeDate", DateUtil.format(tradeDate,DateUtil.YYYY_MM_DD_HH_MM_SS));
		try {
			baseDao.insert(paramsMap, "MSAccountDetailMapper.insertAccountDetail");
		} catch (Exception e) {
			logger.error("写入账户变动明细出现错误-1002，会员编号：%s，订单编号：%s，错误信息：%s", 
					memId, orderId, e.getMessage());
		}
	}

	@Override
	public void saveAddConsumePoints(String memId, String orderId,
			String orderSource, String consumePoints, String operatorType,
			String operator, String remark) throws MdSysException {
		
		double realPoints =accountReportService.getAvailablePoints(memId);

		String balancePoints = String.valueOf(DoubleCalculate.add(realPoints,
				Double.valueOf(consumePoints)));
		//写入明细
		insertConsumePointsDetail(memId, orderId, orderSource, consumePoints,
				"0", balancePoints, operatorType, operator, remark, "2001");
		
	}

	@Override
	public void saveCutConsumePoints(String memId, String orderId,
			String orderSource, String consumePoints, String operatorType,
			String operator, String remark) throws MdSysException {
		
		double realPoints = accountReportService.getAvailablePoints(memId);

		String balancePoints = String.valueOf(DoubleCalculate.sub(realPoints,
				Double.valueOf(consumePoints)));
		//写入明细
		insertConsumePointsDetail(memId, orderId, orderSource,
				"0",consumePoints, balancePoints, operatorType, operator, remark, "2002");
		
	}

	@Override
	public void saveConsumePoints(String memId, String orderId,
			String orderSource, String inConsumePoints,
			String outConsumePoints, String operatorType, String operator,
			String remark) throws MdSysException {
		double realPoints = accountReportService.getAvailablePoints(memId);

		String balancePoints = String.valueOf(DoubleCalculate.add(realPoints,
				Double.valueOf(inConsumePoints))); //收入
		balancePoints = String.valueOf(DoubleCalculate.sub(realPoints,
				Double.valueOf(outConsumePoints))); //支出
		//写入明细
		insertConsumePointsDetail(memId, orderId, orderSource,
				inConsumePoints, outConsumePoints, balancePoints, operatorType, operator, remark, "2003");
	}
	
	@Override
	public void saveConsumePoints(String memId, String orderId,
			String orderSource, String inConsumePoints,
			String outConsumePoints, String balancePoints, String operatorType,
			String operator, String remark) {
		//写入明细
		insertConsumePointsDetail(memId, orderId, orderSource,
				inConsumePoints, outConsumePoints, balancePoints, operatorType, operator, remark, "2004");
	}

	/**
	 * 方法名: insertConsumePointsDetail<br>
	 * 描述:  写入会员积分消费明细<br>
	 * 创建时间: 2016-11-18
	 * @param memId
	 * @param orderId
	 * @param orderSource
	 * @param inConsumePoints
	 * @param outConsumePoints
	 * @param balancePoints
	 * @param operatorType
	 * @param operator
	 * @param remark
	 */
	private void insertConsumePointsDetail(String memId, String orderId,
			String orderSource, String inConsumePoints,
			String outConsumePoints, String balancePoints, String operatorType,
			String operator, String remark, String calcFlag) {
		
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("operatorType", operatorType);
		paramsMap.put("orderSource", orderSource);
		paramsMap.put("inConsumePoints", inConsumePoints);
		paramsMap.put("outConsumePoints", outConsumePoints);
		paramsMap.put("balancePoints", balancePoints);
		paramsMap.put("operator", operator);
		paramsMap.put("remark", remark);
		
		try {
			baseDao.insert(paramsMap, "MSAccountMapper.insertAccountPointDetail");
		} catch (Exception e) {
			logger.error("写入积分变动明细出现错误-%s，会员编号：%s，订单编号：%s，错误信息：%s", 
					calcFlag, memId, orderId, e.getMessage());
		}
	}
	
}
