package com.meiduimall.service.account.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstPointsChangeType;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.constant.ConstTradeType;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.model.MSAccountFreezeDetail;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.MSConsumePointsFreezeInfo;
import com.meiduimall.service.account.model.MSMemberConsumeRecords;
import com.meiduimall.service.account.model.MSMemberIntegral;
import com.meiduimall.service.account.model.request.MSMemberConsumeRecordsReq;
import com.meiduimall.service.account.model.request.RequestSaveOrder;
import com.meiduimall.service.account.model.request.RequestCancelOrder;
import com.meiduimall.service.account.service.AccountAdjustService;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.AccountService;
import com.meiduimall.service.account.service.BankAccountService;
import com.meiduimall.service.account.service.BankWithdrawDepositService;
import com.meiduimall.service.account.service.ConsumeRecordsService;
import com.meiduimall.service.account.service.ConsumePointsDetailService;
import com.meiduimall.service.account.service.ConsumePointsFreezeInfoService;
import com.meiduimall.service.account.service.MSMemberConsumeRecordsService;
import com.meiduimall.service.account.service.TradeService;
import com.meiduimall.service.account.service.ValidateService;
import com.meiduimall.service.account.service.PointsService;
import com.meiduimall.service.account.util.DateUtil;
import com.meiduimall.service.account.util.DoubleCalculate;
import com.meiduimall.service.account.util.GenerateNumber;
import com.meiduimall.service.account.util.SerialStringUtil;
import com.meiduimall.service.account.util.StringUtil;

/**
 * 订单交易相关逻辑接口{@link=TradeService}实现类
 * @author chencong
 *
 */
@Service
public class TradeServiceImpl implements TradeService {

	private final static Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Autowired
	private PointsService pointsService;

	@Autowired
	private AccountService accountServices;

	@Autowired
	private BankWithdrawDepositService bankWithdrawDepositService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private AccountAdjustService accountAdjustService;

	@Autowired
	private ConsumePointsDetailService pointsDetailService;

	@Autowired
	private MSMemberConsumeRecordsService memberConsumeHistoryService;

	@Autowired
	private AccountReportService accountReportService;
	
	@Autowired
	private AccountFreezeDetailService accountFreezeDetailService;

	private ValidateService validateService;
	
	@Autowired
	private ConsumeRecordsService consumeRecordsService;
	
	@Autowired
	private ConsumePointsFreezeInfoService pointsFreezeInfoService;
	
	@Override
	@Transactional
	public ResBodyData saveOrder(RequestSaveOrder model) throws MdSysException{
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,"保存订单成功");
		
		//校验交易金额合法性
		validateService.checkConsumeAmountRelation(model.getConsumeAmount(),model.getConsumeMoney(),model.getConsumePoints());
		//将数据来源转换为字典值
		model.setOrderSource(SerialStringUtil.getDictOrderSource(model.getOrderSource()));
		//将前端请求的订单状态转换为会员消费记录的订单状态
		int recordsOrderStatus=0;
		switch(model.getOrderStatus()){
		case 1:recordsOrderStatus=0;break;
		case 2:recordsOrderStatus=1;break;
		default:;
		}
		//查询该笔交易是否已写入消费记录表
		MSMemberConsumeRecords consumeRecords=consumeRecordsService.getConsumeRecords(model.getOrderId(),model.getOrderSource(),recordsOrderStatus);
		if(consumeRecords!=null){
			logger.warn("重复提交的订单：{}",model.getOrderId());
			throw new ServiceException(ConstApiStatus.REPEAT_ORDER);
		}
		else {
			Double availablePoints=accountReportService.getAvailablePoints(model.getMemId());
			Double availableBalance=accountReportService.getAvailableBalance(model.getMemId());
			if(model.getConsumePoints()>availablePoints){
				logger.warn("积分不足无法支付");
				throw new ServiceException(ConstApiStatus.POINTS_CANNOT_AFFORD);
			}
			if(model.getConsumeMoney()>availableBalance){
				logger.warn("余额不足无法支付");
				throw new ServiceException(ConstApiStatus.BALANCE_CANNOT_AFFORD);
			}
			//订单状态为1表示下单未支付，需要冻结积分和余额
			if(model.getOrderStatus()==1){
				List<MSConsumePointsFreezeInfo> listPointsFreezeInfo=baseDao.selectList(model.getOrderId(),"MSConsumePointsFreezeInfoMapper.getRecordsByOrderId");
				List<MSAccountFreezeDetail> listBalanceFreeze=baseDao.selectList(model.getOrderId(),"MSAccountFreezeDetailMapper.getRecordsByOrderId");
				if(listPointsFreezeInfo.size()>0||listBalanceFreeze.size()>0){
					logger.warn("重复提交的冻结订单");
					throw new ServiceException(ConstApiStatus.REPEAT_FREEZ_ORDER);
				}
				//写入积分冻结解冻记录表
				MSConsumePointsFreezeInfo freezeInfo=new MSConsumePointsFreezeInfo();
				freezeInfo.setMcpfId(UUID.randomUUID().toString());
				freezeInfo.setMemId(model.getMemId());
				freezeInfo.setMcpfOrderId(model.getOrderId());
				freezeInfo.setMcpfConsumePoints(String.valueOf(model.getConsumePoints()));
				freezeInfo.setMcpfRemark("冻结消费积分");
				pointsFreezeInfoService.insertConsumePointsFreezeInfo(freezeInfo,ConstPointsChangeType.POINTS_FREEZE_TYPE_DJ.getCode());
			}
			//订单状态为2表示已支付，需要解冻并扣减积分和余额
			else if (model.getOrderStatus()==2) {
				
			}
			else {
				logger.warn("订单状态不合法");
				throw new ServiceException(ConstApiStatus.ORDER_STATUS_UNNORMAL);
			}
		}
		return resBodyData;
	}
	

	@Override
	public ResBodyData cancelOrder(RequestCancelOrder model)  {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String memId=model.getMemId();
		/**解冻并扣减积分*/
		Map<String,Object> dataMap=new HashMap<>();
		/*if(pointsService.getFreezeUnfreezeRecordByOrderId(orderId)){
			pointsService.unFreezePointsAndAddRecord(memId,consumePoints,orderId,orderSource,dataMap);
			pointsService.deductPointsAndAddRecord(memId,consumePoints,orderId,orderSource,dataMap);
			}
		*//**解冻并扣减余额*//*
		if(moneyService.getFreezeUnfreezeRecordByOrderId(orderId)){
			moneyService.unFreezeMoneyAndAddRecord(memId,consumeMoney,orderId,orderSource,dataMap);
			moneyService.deductMoneyAndAddRecord(memId,consumeMoney,orderId,orderSource,dataMap);
		}*/
		resBodyData.setData(dataMap);
		/**写入会员消费记录*/
		/*MSMemberConsumeHistory history = new MSMemberConsumeHistory();*/
		/*history.setMchId(UUID.randomUUID().toString());
		history.setMemId(memId);
		history.setOrderId(orderId);
		history.setMchProductName(productName);
		history.setMchOrginType(orderSource);
		history.setMchOrginMemId("");
		history.setMchPayType(payType);
		history.setMchStatus("1");
		history.setMchConsumePointsCount(consumePoints);
		history.setMchShoppingCouponCount(consumeMoney);
		history.setMchSettingStatus(1);*/
		/*history.setMchIssueStatus(1);
		try {
			this.saveMemberTradeHistory(history);
		} catch (Exception e) {
 
		
		}*/
		return resBodyData;
	}

	@Override
	public JSONObject accountTradeCancel(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*
		 * resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		 * resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);
		 */

		final String userId = param.getString("user_id"); // 用户标识
		final String orderId = param.getString("order_id");// 订单号
		final String orderSource = param.getString("order_source");// 数据来源
		final String tradeType = param.getString("trade_type"); // 业务类型
		final String tradeDateStr = param.getString("trade_date"); // 订单发生时间
		final String payType = param.getString("pay_type"); // 支付方式
		final String productName = param.getString("product_name"); // 消费项目
		final String tradeTotalMoney = param.getString("trade_total_money"); // 消费总额
		final String tradeMoney = param.getString("trade_money"); // 消费使用现金金额
		final String tradeAmount = param.getString("trade_amount"); // 消费使用余额
		final String tradePoint = param.getString("trade_point"); // 消费使用积分
		final String remark = param.getString("remark"); // 备注
		boolean bsFlag = true;

		// 数据转换
		Date tradeDate = new Date(Long.parseLong(tradeDateStr));
		// 检查会员
		String memId = accountServices.getMemIdByUserId(userId);

		// 解冻积分,并扣减积分
		if (bsFlag) {
			/*
			 * if(accountServices.checkFreezePointByOrderId(orderId)){ bsFlag =
			 * accountServices.cutMDConsumePointsFreezeAndDetail(memId,
			 * tradePoint, orderId, orderSource, tradeType, memId, remark); }
			 */
		}
		// 解冻余额，并扣减余额
		if (bsFlag) {
			if (accountServices.checkFreezeMoneyByOrderId(orderId)) {
				bsFlag = accountAdjustService.cutConsumeFreezeMoneyAndDetail(memId, orderId, tradeType, tradeDate,
						tradeAmount, remark);
			}
		}

		// 出现错误返回运行时异常回滚事务
		if (!bsFlag) {
			throw new RuntimeException("accountTradeCancel-业务处理时出现错误-1002，回滚事务。");
		}

		return resultJson;
	}

	@Override
	public JSONObject accountTradeRefundApply(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*
		 * resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		 * resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);
		 */
		// 暂不实现，库表不支持
		return resultJson;
	}

	@Override
	public JSONObject accountTradeRefundAffirm(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*
		 * resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		 * resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);
		 */

		final String userId = param.getString("user_id"); // 用户标识
		final String orderId = param.getString("order_id");// 订单号
		final String orderSource = param.getString("order_source");// 数据来源
		final String tradeType = param.getString("trade_type"); // 业务类型
		final String payType = param.getString("pay_type"); // 支付方式
		final String productName = param.getString("product_name"); // 项目
		final String tradeTotalMoney = param.getString("trade_total_money"); // 总额
		final String tradeMoney = param.getString("trade_money"); // 使用现金金额
		final String tradeDateStr = param.getString("trade_date"); // 订单发生时间
		final String tradeAmount = param.getString("trade_amount"); // 退款余额
		final String tradePoint = param.getString("trade_point"); // 退款积分
		final String remark = param.getString("remark"); // 备注
		boolean bsFlag = true;

		// 数据转换
		Date tradeDate = new Date(Long.parseLong(tradeDateStr));
		// 获取会员id
		String memId = accountServices.getMemIdByUserId(userId);
		// 检查订单退款有效性
		List<Map<String, String>> historyList = baseDao.selectList(orderId,
				"MSAccountMapper.getConsumeHistoryByOrderId");
		Double pointTotal = new Double("0"); // 积分总额
		Double pointRefundTotal = new Double("0");// 退款积分总额
		Double moneyTotal = new Double("0"); // 余额总额
		Double moneyRefundTotal = new Double("0");// 退款余额总额
		for (Map<String, String> tmpMap : historyList) {
			String status = tmpMap.get("status");
			// 获取已完成订单的积分与余额
			if ("1".equals(status)) {
				moneyTotal = Double.valueOf(tmpMap.get("money"));
				pointTotal = Double.valueOf(tmpMap.get("point"));
			}
			// 获取已退款的积分与余额
			if ("2".equals(status)) {
				moneyRefundTotal = DoubleCalculate.add(moneyRefundTotal, Double.valueOf(tmpMap.get("money")));
				pointRefundTotal = DoubleCalculate.add(pointRefundTotal, Double.valueOf(tmpMap.get("point")));
			}
		}
		if (DoubleCalculate.sub(DoubleCalculate.sub(moneyTotal, moneyRefundTotal), Double.valueOf(tradeAmount)) < 0) {

			return resultJson;
		}
		if (DoubleCalculate.sub(DoubleCalculate.sub(pointTotal, pointRefundTotal), Double.valueOf(tradePoint)) < 0) {

			return resultJson;
		}

		// 退款增加积分
		if (bsFlag) {
			if (accountServices.checkFreezePointByOrderId(orderId)) {
				if (!StringUtil.isEmptyByString(tradePoint) && StringUtil.checkNumber(tradePoint, "+")) {
					bsFlag = pointsDetailService.addMDConsumePointsAndDetail(memId, tradePoint, orderId, orderSource,
							tradeType, memId, remark);
				}
			}
		}
		// 退款增加余额
		if (bsFlag) {
			if (accountServices.checkFreezeMoneyByOrderId(orderId)) {
				if (!StringUtil.isEmptyByString(tradeAmount) && StringUtil.checkFloat(tradeAmount, "+")) {
					bsFlag = accountAdjustService.addConsumeMoneyAndDetail(memId, orderId, tradeType, tradeDate,
							tradeAmount, remark);
				}
			}
		}
		//消费记录
		if(bsFlag){
			/*MSMemberConsumeHistory history = new MSMemberConsumeHistory();
			history.setMchId(UUID.randomUUID().toString());
			history.setMemId(memId);
			history.setOrderId(orderId);
			history.setMchProductName(productName);
			history.setMchOrginType(orderSource);
			history.setMchOrginMemId("");
			history.setMchCreatedDate(tradeDate);
			history.setMchPayType(payType);
			history.setMchStatus("2");
 
			history.setMchConsumePointsCount(tradePoint);
			history.setMchShoppingCouponCount(tradeAmount);
			history.setMchSettingStatus(1);
			history.setMchIssueStatus(1);
			saveMemberTradeHistory(history);*/
		}

		// 出现错误返回运行时异常回滚事务
		if (!bsFlag) {
			throw new RuntimeException("accountTradeRefundAffirm-业务处理时出现错误-1004，回滚事务。");
		}

		return resultJson;
	}

	@Override
	public JSONObject bankWithdrawDepositApply(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*
		 * resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		 * resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);
		 */

		final String userId = param.getString("user_id"); // 用户标识
		final String accountIdcard = param.getString("account_idcard");// 身份证
		final String accountNo = param.getString("account_no");// 银行卡号
		final String accountName = param.getString("account_name");// 银行卡户名
		final String accountBank = param.getString("account_bank");// 银行名称
		String accountProvince = "";// 银行所属省份
		if (param.containsKey("account_province")) {
			accountProvince = param.getString("account_province");// 银行所属省份
		}
		String accountCity = "";// 银行所属城市
		if (param.containsKey("account_city")) {
			accountCity = param.getString("account_city");// 银行所属城市
		}
		String accountArea = "";// 银行所属地区
		if (param.containsKey("account_area")) {
			accountArea = param.getString("account_area");// 银行所属地区
		}
		final String accountSubBank = param.getString("account_sub_bank");// 支行名称
		final String applyCarryCash = param.getString("apply_carry_cash");// 申请提现金额
		final String counterFee = param.getString("counter_fee");// 申请提现手续费
		final String applyDate = param.getString("apply_date");// 申请提现时间

		// 检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if (StringUtil.isEmptyByString(memId)) {
			/*
			 * resultJson.put(SysParaNameConst.STATUS_CODE,
			 * ApiStatusConst.USERNAME_ERROR);
			 * resultJson.put(SysParaNameConst.RESULT_MSG,
			 * ApiStatusConst.USERNAME_ERROR_C);
			 */
			return resultJson;
		}
		// 检查银行信息是否存在
		MSBankAccount bankAccount = bankAccountService.getBankAccount(memId, accountNo);
		if (bankAccount == null) {
			return resultJson;
		}
		// 检查申请余额，并计算
		final Double old_useMoney = accountServices.getUseConsumeMoney(memId);
		final Double old_applyCarryCash = Double.valueOf(applyCarryCash);
		// 申请提现余额超过最大可提现金额
		if (old_applyCarryCash > 50000) {
			return resultJson;
		}
		// 计算申请提现余额是否超最大余额
		if (old_applyCarryCash > old_useMoney) {
			return resultJson;
		}
		// 计算当前余额是否低于最低提现金额
		if (old_useMoney <= ConstSysParamsDefination.MIN_APPLY_CARRY_CASH) {
			return resultJson;
		}

		String businessNo = GenerateNumber.generateBusinessNo(ConstTradeType.TRADE_TYPE_YETX.getCode());
		MSBankWithdrawDeposit dto = new MSBankWithdrawDeposit();
		dto.setBusinessNo(businessNo);
		dto.setMemId(memId);
		dto.setBankAccountId(bankAccount.getId());
		dto.setAccountIdcard(accountIdcard);
		// dto.setAccountNo(accountNo);
		dto.setAccountBank(accountBank);
		dto.setAccountName(accountName);
		dto.setAccountProvince(accountProvince);
		dto.setAccountCity(accountCity);
		dto.setAccountArea(accountArea);
		dto.setAccountSubBank(accountSubBank);
		/*
		 * dto.setApplyCarryCash(applyCarryCash); dto.setCounterFee(counterFee);
		 * dto.setApplyDate(new Date(Long.parseLong(applyDate)));
		 * dto.setStatus("0");
		 * 
		 * //计算扣除金额与手续费 Map<String, String> returnMap =
		 * this.calcBankWithdrawDeposit(memId, dto.getApplyCarryCash(),
		 * dto.getCounterFee());
		 */
		// 计算后实际金额
		/*
		 * String calcActualCarryCash = returnMap.get("calc_actualCarryCash");
		 * //计算后手续费 String calcCounterFee = returnMap.get("calc_counterFee");
		 * //数据检查 实际提现金额不能大于申请提现金额 if(Double.parseDouble(calcActualCarryCash) >
		 * Double.parseDouble(dto.getApplyCarryCash())){ throw new
		 * RuntimeException("bankWithdrawDepositApply-业务处理时出现错误-1001，回滚事务。"); }
		 * //数据检查 实际提现金额必须大于0 if(Double.parseDouble(calcActualCarryCash) <= 0){
		 * throw new
		 * RuntimeException("bankWithdrawDepositApply-业务处理时出现错误-1002，回滚事务。"); }
		 * //添加到dto中 dto.setActualCarryCash(calcActualCarryCash);
		 * dto.setCounterFee(calcCounterFee);
		 */
		// 提现申请时间
		Date tradeDate = new Date(Long.parseLong(applyDate));

		// 增加一条提现记录，返回业务流水号
		String id = bankWithdrawDepositService.addBankWithdrawDeposit(dto);
		if (!StringUtil.isEmptyByString(id)) {
			MSAccount account = accountServices.getAccountMoney(memId);
			if (account != null) {
				/* 临时注销代码 */
				// 余额提现冻结余额
				/*
				 * accountServices.addConsumeFreezeMoneyAndDetail(memId,
				 * businessNo, ConstTradeType.TRADE_TYPE_YETX.getCode(),
				 * tradeDate, calcActualCarryCash, "余额提现"); //余额提现冻结手续费
				 * accountServices.addConsumeFreezeMoneyAndDetail(memId,
				 * businessNo, ConstTradeType.TRADE_TYPE_TXSX.getCode(),
				 * tradeDate, calcActualCarryCash, "提现手续费");
				 */
			}
		} else {
			throw new RuntimeException("bankWithdrawDepositApply-业务处理时出现错误-1003，回滚事务。");
		}

		return resultJson;
	}

	@Override
	public JSONObject getBankWithdrawDeposits(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*
		 * resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		 * resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);
		 */

		final String userId = param.getString("user_id"); // 用户标识
		final String page = param.getString("current_page");// 当前页数
		final String pagesize = param.getString("page_size");// 每页数量

		// 检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if (StringUtil.isEmptyByString(memId)) {
			/*
			 * resultJson.put(SysParaNameConst.STATUS_CODE,
			 * ApiStatusConst.USERNAME_ERROR);
			 * resultJson.put(SysParaNameConst.RESULT_MSG,
			 * ApiStatusConst.USERNAME_ERROR_C);
			 */
			return resultJson;
		}
		Map<String, String> resultMap = new HashMap<>();
		Map<String, String> parasMap = new HashMap<>();
		parasMap.put("current_page", page);
		parasMap.put("page_size", pagesize);
		List<MSBankWithdrawDeposit> bankWithdrawDepositList = bankWithdrawDepositService
				.getBankWithdrawDepositList(memId, true, parasMap, resultMap);
		if (bankWithdrawDepositList != null) {
			final JSONObject dataJson = new JSONObject();
			dataJson.put("total_page", resultMap.get("pageTotal"));
			/*
			 * dataJson.put("data_list",
			 * JSONArray.toJSON(bankWithdrawDepositList).toString());
			 */

			resultJson.put("result", dataJson);
		} else {
			final JSONObject dataJson = new JSONObject();
			dataJson.put("total_page", "0");
			dataJson.put("data_list", "[]");
			resultJson.put("result", dataJson);
		}
		return resultJson;
	}

	/**
	 * 方法名: calcBankWithdrawDeposit<br>
	 * 描述: 计算可提现金额与手续费 <br>
	 * 创建时间: 2016-12-19
	 * 
	 * @param dto
	 * @return
	 */
	private Map<String, String> calcBankWithdrawDeposit(String memId, String applyCarryCash, String counterFee) {

		Map<String, String> returnMap = new HashMap<String, String>();

		final Double old_useMoney = accountServices.getUseConsumeMoney(memId);
		final Double old_applyCarryCash = Double.valueOf(applyCarryCash);
		// final Double old_counterFee = Double.valueOf(counterFee);
		final Double calc_feeScale = new Double("0.01"); // 手续费比例
		final Double calc_minFee = ConstSysParamsDefination.MIN_APPLY_CARRY_FEE; // 最小手续费

		Double calc_counterFee = new Double("0");
		Double calc_actualCarryCash = new Double("0");

		// 计算手续费与实际提现金额
		if (old_applyCarryCash == old_useMoney) {
			// 全部提取，手续费从申请申请提取金额中扣除，实际提现金额=申请提取金额-手续费
			calc_counterFee = DoubleCalculate.mul(old_applyCarryCash, calc_feeScale);
			if (calc_counterFee < calc_minFee) {
				calc_counterFee = calc_minFee;
			}
			calc_actualCarryCash = DoubleCalculate.sub(old_applyCarryCash, calc_counterFee);
		} else {
			// 部分提取，手续费直接从余额中扣除，实际提现金额=申请提取金额；余额不足扣除手续费时，实际提现金额=申请提取金额-扣除余额后不足的手续费
			calc_counterFee = DoubleCalculate.mul(old_applyCarryCash, calc_feeScale);
			if (calc_counterFee < calc_minFee) {
				calc_counterFee = calc_minFee;
			}
			// 计算扣除申请提现金额后的余额
			Double subUseMoney = DoubleCalculate.sub(old_useMoney, old_applyCarryCash);
			// 余额够扣除手续费时，实际提现金额=申请提取金额
			if (subUseMoney >= calc_counterFee) {
				calc_actualCarryCash = old_applyCarryCash;
			} else {
				// 余额不足扣除手续费时，实际提现金额=申请提取金额-扣除余额后不足的手续费
				Double subCounterFee = DoubleCalculate.sub(calc_counterFee, subUseMoney); // 扣除余额后剩余未扣减手续费
				calc_actualCarryCash = DoubleCalculate.sub(old_applyCarryCash, subCounterFee);
			}
		}
		returnMap.put("calc_actualCarryCash", String.valueOf(calc_actualCarryCash));
		returnMap.put("calc_counterFee", String.valueOf(calc_counterFee));
		return returnMap;
	}

	/*private boolean saveMemberTradeHistory(MSMemberConsumeHistory history) throws Exception {
		int flag = baseDao.insert(history, "MSAccountMapper.insertMemberConsumeHistory");
		if (flag <= 0) {
			logger.error("写入会员消费记录表失败，会员编号：{}，订单编号：{}", history.getMemId(), history.getOrderId());
			return false;
		}
		return true;
	}*/

	@Override
	@Transactional
	public ResBodyData updateMemberOrder(MSMemberConsumeRecordsReq ms) {

		JSONObject json;
		try {
			double oldShoppingCoupon = 0;
			double oldConsumePoints = 0; // 美兑积分

			MSMemberConsumeRecords mConHis = new MSMemberConsumeRecords();

			mConHis.setId(UUID.randomUUID().toString());
			// 会员ID
			mConHis.setMemId(ms.getMemId());
			// 订单ID
			mConHis.setOrderId(ms.getOrderId());

			// 消费商品名称
			mConHis.setProductName(ms.getProductName());

			// 消费来源
			mConHis.setOrderSource(ms.getOrderSource());
			 
			// 消费时间
			mConHis.setCreateDate(new Date(System.currentTimeMillis()));
			// 支付方式
			mConHis.setPayType(ms.getPayType());
			// 订单状态
			/*mConHis.setOrderStatus(ms.getOrderStatus());*/


			// 查询数据库是否已存在该订单，如果不存在则直接保存，如果存在则修改
			MSMemberConsumeRecords history = memberConsumeHistoryService
					.queryByOrderIdInfo(new MSMemberConsumeRecordsReq());
			List<MSMemberConsumeRecords> listhistory = memberConsumeHistoryService
					.listByOrderIdInfo(new MSMemberConsumeRecordsReq());

			/** 订单状态1表示已经完，2表示已退单 */
			if ("1".equals(ms.getOrderStatus())) {
				if (null != history) {
					logger.info("重复提交的订单");
					return new ResBodyData(2021, "重复提交的订单");
				}
			} else {
				if (null == history) {
					logger.info("当前退单的订单号与已提交的订单号不匹配");
					return new ResBodyData(2063, "当前退单的订单号与已提交的订单号不匹配");
				}
			}
			if (DateUtil.daysBetween(history.getCreateDate(), new Date()) > 30) {
				logger.info("当前退单时间超过下单时的时间，无法退单");
				return new ResBodyData(2066, "当前退单时间超过下单时的时间，无法退单");
			}

			for (MSMemberConsumeRecords mc : listhistory) {
				// 美兑积分
				if (Double.valueOf(mc.getConsumePoints()) < 0) {
					String mchConsumePointsCount = String.valueOf(mc.getConsumePoints());
					oldConsumePoints = DoubleCalculate.add(oldConsumePoints,
							Double.valueOf(mchConsumePointsCount.substring(1, mchConsumePointsCount.length())));
				} else {
					oldConsumePoints = DoubleCalculate.add(Double.valueOf(oldConsumePoints),
							Double.valueOf(mc.getConsumePoints()));
				}
			}

			// 历史使用积分 = 历史退款积分 + 当前退款积分
			if (ms.getConsumePoints() != null) {
				oldConsumePoints = DoubleCalculate.add(oldConsumePoints,
						Double.valueOf(ms.getConsumePoints()));
			}

			logger.info("当前退单订单号=" + ms.getOrderId() + "支付余额总计(包含本次)" + oldShoppingCoupon);
			if (oldShoppingCoupon > Double.valueOf(history.getConsumeMoney())) {
				logger.info("当前退单的账户余额超出订单使用的余额");
				return new ResBodyData(2069, "当前退单的账户余额超出订单使用的余额");
			}

			// 增加美兑积分需求 2016-11-01
			logger.info("当前退单订单号=" + ms.getOrderId() + "美积分总计(包含本次)" + oldConsumePoints);
			if (oldConsumePoints > Double.valueOf(history.getConsumePoints())) {
				logger.info("当前退单的积分超出订单使用的积分");
				return new ResBodyData(2069, "当前退单的积分超出订单使用的积分");
			}

			json = new JSONObject();
			json.put("mem_id", ms.getMemId());

			// 获取退费前积分余额
			Double preConsumePoints = pointsService.getAvailablePointsByMemId(ms.getMemId());
			// 获取退费前余额
			Double preConsumeMoney = accountReportService.getAvailableBalance(ms.getMemId());
			// 增加美兑积分需求 2016-11-01 退费前积分余额
			json.put("pre_consume_points", StringUtil.interceptionCharacter(2, preConsumePoints));
			json.put("pre_shopping_coupon", StringUtil.interceptionCharacter(2, preConsumeMoney));

			// 判断支付方式
			if ("1".equals(ms.getPayType()) || "2".equals(ms.getPayType())) {

				// 消费卷全部返回0
				json.put("pre_consume_coupon", "0.00");
				json.put("after_consume_coupon", "0.00");

				/******************************** 执行返回扣取购物券*****************开始 ***********************/
				// 如果有余额,调用方会传递过来
				logger.info("如果为 null 将不会进行退余额操作: " + ms.getConsumeMoney());
				if ("2".equals(ms.getPayType()) && null != ms.getConsumeMoney()) {
					logger.info("余额的返还金额为: " + ms.getConsumeMoney());
					// 退单增加余额
					accountAdjustService.addConsumeMoneyAndDetail(ms.getMemId(), ms.getOrderId(),
							ConstTradeType.TRADE_TYPE_TKSH.getCode(), new Date(), ms.getConsumeMoney(),
							ConstTradeType.TRADE_TYPE_TKSH.getCode());

					// 退单后余额
					double afterMoney = DoubleCalculate.add(preConsumeMoney,
							Double.valueOf(ms.getConsumeMoney()));
					// 同时更新订单表的
					mConHis.setConsumeMoney(ms.getConsumeMoney());
					// 返回退单后余额
					json.put("after_shopping_coupon", StringUtil.interceptionCharacter(2, afterMoney));

					logger.info("退费订单号：" + ms.getOrderId() + "，当次退费余额是：" + ms.getConsumeMoney());

				}
				/******************************** 执行返回扣取购物券*****************结束 ***********************/
				// 增加美兑积分需求 2016-11-01
				String consumePoints = ms.getConsumePoints();
				if ("2".equals(ms.getPayType()) && null != consumePoints) {
					logger.info("退费订单号：" + ms.getOrderId() + "，进入退费美积分计算方法.");
					// 退单返回美兑积分
					try {
						accountAdjustService.addMDConsumePoints(ms.getMemId(), consumePoints, false);
					} catch (MdSysException e) {
						logger.error("退单返回美兑积分错误:{}", e);
						throw new ServiceException(ConstApiStatus.MD_POINTS_ERROR,
								ConstApiStatus.getZhMsg(ConstApiStatus.MD_POINTS_ERROR));
					}

					// 退单后积分余额
					double afterPoints = DoubleCalculate.add(preConsumePoints, Double.valueOf(consumePoints));
					// 同时更新订单表的
					mConHis.setConsumePoints(consumePoints);
					// 返回退单后积分
					json.put("after_consume_points", StringUtil.interceptionCharacter(2, afterPoints));

					logger.info("退费订单号：" + ms.getOrderId() + "，当月退费美积分金额是：" + consumePoints);
				}
			}

			memberConsumeHistoryService.save(mConHis);

			logger.info("当前退余额: " + ms.getConsumeMoney() + "当前退积分：" + ms.getConsumePoints());
			// 更新退单消费表示
			this.cancelOrder(new MSMemberIntegral(ms.getMemId(), history.getCreateDate()));
		} catch (DaoException e) {
			logger.error(ConstApiStatus.getZhMsg(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION) + ":{}", e);
			throw new ServiceException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION,
					ConstApiStatus.getZhMsg(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION));
		}

		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M, json);
	}

	/** 更新退单消费表示 **/
	private void cancelOrder(MSMemberIntegral mSMemberIntegral) {
		baseDao.update(mSMemberIntegral, "MsMemberIntegralMapper.cancelOrder");
	}

	@Override
	@Transactional
	public ResBodyData saveMemberOrder(MSMemberConsumeRecordsReq mmt) throws MdSysException {
		JSONObject result = new JSONObject();
		MSMemberConsumeRecords memConHis = new MSMemberConsumeRecords();

		memConHis.setId(UUID.randomUUID().toString());
		// 会员ID
		memConHis.setMemId(mmt.getMemId());
		// 订单ID
		memConHis.setOrderId(mmt.getOrderId());

		// 消费商品名称
		memConHis.setProductName(mmt.getProductName());

		// 消费来源
		memConHis.setOrderSource(mmt.getOrderSource());
		 
		// 消费时间
		memConHis.setCreateDate(new Date(System.currentTimeMillis()));
		// 支付方式
		memConHis.setPayType(mmt.getPayType());
		// 订单状态
		/*memConHis.setOrderStatus(mmt.getOrderStatus());*/
		memConHis.setConsumeMoney(mmt.getConsumeMoney());
		// 增加美兑积分逻辑 2016-10-31
		memConHis.setConsumePoints(mmt.getConsumePoints());
		// 查询数据库是否已存在该订单，如果不存在则直接保存，如果存在则修改
		List<MSMemberConsumeRecords> listByOrderIdInfo = memberConsumeHistoryService
				.listByOrderIdInfo(new MSMemberConsumeRecordsReq());
		if (StringUtils.isEmpty(listByOrderIdInfo)) {
			return new ResBodyData(ConstApiStatus.REPEAT_ORDER, ConstApiStatus.getZhMsg(ConstApiStatus.REPEAT_ORDER));
		} else {

			// 根据订单查询冻结积分
			List<MSConsumePointsFreezeInfo> pointsList = pointsService.getRecordsByOrderId(mmt.getOrderId());
			// 获取积分余额
			Double consumePoints = pointsService.getAvailablePointsByMemId(mmt.getMemId());
			// 获取可使用余额
			Double useConsumeMoney = accountReportService.getAvailableBalance(mmt.getMemId());

			// 根据订单查询冻结余额
			List<MSAccountFreezeDetail> moneyFreezeList = accountFreezeDetailService.getRecordsByOrderId(mmt.getOrderId());

			logger.info("当前可使用积分：" + consumePoints + "，可使用余额：" + useConsumeMoney);

				 
				// 增加美兑积分逻辑 2016-10-31 美兑积分解冻
				if (isZero(mmt.getConsumePoints())) {
					logger.info("订单编号：" + mmt.getOrderId() + ",进入积分解冻方法.");
					if (pointsList.size() > 0) {
						// 检查重复解冻
						if (pointsList.size() > 1) {
							logger.info("重复提交的冻结订单" + mmt.getOrderId() + ";冻结" + mmt.getConsumePoints());
							return new ResBodyData(ConstApiStatus.REPEAT_FREEZ_ORDER, ConstApiStatus.getZhMsg(ConstApiStatus.REPEAT_FREEZ_ORDER));
						}
						// 解冻金额和冻结金额是否一样
						if (DoubleCalculate.add(Double.valueOf(mmt.getConsumePoints()),
								Double.valueOf(pointsList.get(0).getMcpfConsumePoints())) != 0.0) {
							logger.info("订单解冻积分不等于冻结积分");
							return new ResBodyData(ConstApiStatus.DJ_NOT_EQUALS_DJ, ConstApiStatus.getZhMsg(ConstApiStatus.DJ_NOT_EQUALS_DJ));
						}
						// 写入积分冻结表
						/*accountFreezeDetailService.saveUnFreezePoints(mmt.getMemId(), mmt.getOrderId(), mmt.getConsumePoints(),
								ConstPointsChangeType.POINTS_FREEZE_TYPE_JD.getCode(), ConstPointsChangeType.POINTS_FREEZE_TYPE_JD.getName());*/
						
						logger.info(mmt.getOrderId() + ";解冻" + mmt.getConsumePoints());
					} else {
						logger.info("没有冻结的积分记录");
						return new ResBodyData(ConstApiStatus.NO_DJ_POINTS, ConstApiStatus.getZhMsg(ConstApiStatus.NO_DJ_POINTS));
					}
				}
				// 增加账户余额支付逻辑 2017-03-02
				if (isZero(mmt.getConsumeMoney())) {
					logger.info("订单编号：" + mmt.getOrderId() + ",进入余额解冻方法.");
					if (moneyFreezeList.size() > 0) {
						// 检查重复解冻
						if (moneyFreezeList.size() > 1) {
							logger.info("重复提交的冻结订单" + mmt.getOrderId() + ";冻结" + mmt.getConsumeMoney());
							return new ResBodyData(ConstApiStatus.REPEAT_FREEZ_ORDER, ConstApiStatus.getZhMsg(ConstApiStatus.REPEAT_FREEZ_ORDER));
						}
						// 解冻金额和冻结金额是否一样
						if (DoubleCalculate.sub(Double.valueOf(mmt.getConsumeMoney()),
								Double.valueOf(moneyFreezeList.get(0).getTradeAmount())) != 0.0) {
							logger.info("订单解冻余额不等于冻结余额!");
							return new ResBodyData(ConstApiStatus.MONEY_DJ_NOT_EQUALS_DJ, ConstApiStatus.getZhMsg(ConstApiStatus.MONEY_DJ_NOT_EQUALS_DJ));
						}
						// 写入积分解冻表
						accountAdjustService.cutConsumeFreezeMoneyAndDetail(mmt.getMemId(), mmt.getOrderId(), ConstTradeType.TRADE_TYPE_TKQX.getCode(),
								new Date(), mmt.getConsumeMoney(), ConstTradeType.TRADE_TYPE_TKQX.getName());

						logger.info(mmt.getOrderId() + ";解冻" + mmt.getConsumeMoney());
					} else {
						logger.info("没有冻结的余额记录");
						return new ResBodyData(ConstApiStatus.NO_DJ_MONEY, ConstApiStatus.getZhMsg(ConstApiStatus.NO_DJ_MONEY));
					}
				}
			 
				 
				memberConsumeHistoryService.save(memConHis);

				Double beforeCouponsBalance = Double.parseDouble("0");
				Double endCouponsBalance = Double.parseDouble("0");
				// 美兑积分
				Double beforeConsumePoints = DoubleCalculate.add(consumePoints, Double
						.parseDouble(mmt.getConsumePoints() == null ? "0" : mmt.getConsumePoints())); // 扣除积分前余额=当前余额+扣除积分
				// 账户余额
				Double beforeShoppingBalance = DoubleCalculate.add(useConsumeMoney, Double
						.parseDouble(mmt.getConsumeMoney() == null ? "0" : mmt.getConsumeMoney())); // 扣除前余额=当前余额+扣除余额

				result.put("beforeCouponsBalance", beforeCouponsBalance);
				result.put("endCouponsBalance", endCouponsBalance);
				result.put("beforeShoppingBalance", beforeShoppingBalance);
				result.put("endShoppingBalance", useConsumeMoney);
				result.put("beforeConsumePointsBalance", beforeConsumePoints);
				result.put("endConsumePointsBalance", consumePoints);
			 
			logger.info("操作成功");
			return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M, result);
		}

	}
	
	
	
	/**
	 * 方法名: isZero<br>
	 * 描述: 判断是否为零，如果为空返回false，如果不为数字返回 false，如果等于0返回 false，大于0返回true <br>
	 * 创建时间: 2017-3-24
	 * @param obj
	 * @return
	 */
	private boolean isZero(Object obj){
		if(obj == null){
			return false;
		}
		try{
			if(Double.valueOf(obj.toString()) > 0){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}

}
