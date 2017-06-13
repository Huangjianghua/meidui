package com.meiduimall.service.account.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.Constants;
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
import com.meiduimall.service.account.model.MSAccountDetail;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.MSAccountFreezeDetail;
import com.meiduimall.service.account.model.MSAccountReport;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.MSConsumePointsDetail;
import com.meiduimall.service.account.model.MSConsumePointsFreezeInfo;
import com.meiduimall.service.account.model.MSMemberConsumeRecords;
import com.meiduimall.service.account.model.request.MSMemberConsumeRecordsReq;
import com.meiduimall.service.account.model.request.RequestSaveOrder;
import com.meiduimall.service.account.model.request.RequestCancelOrder;
import com.meiduimall.service.account.service.AccountAdjustService;
import com.meiduimall.service.account.service.AccountDetailService;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.AccountService;
import com.meiduimall.service.account.service.BankAccountService;
import com.meiduimall.service.account.service.BankWithdrawDepositService;
import com.meiduimall.service.account.service.ConsumeRecordsService;
import com.meiduimall.service.account.service.ConsumePointsDetailService;
import com.meiduimall.service.account.service.ConsumePointsFreezeInfoService;
import com.meiduimall.service.account.service.TradeService;
import com.meiduimall.service.account.service.ValidateService;
import com.meiduimall.service.account.service.PointsService;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DateUtil;
import com.meiduimall.service.account.util.DoubleCalculate;
import com.meiduimall.service.account.util.GenerateNumber;
import com.meiduimall.service.account.util.SerialStringUtil;
import com.meiduimall.service.account.util.StringUtil;

/**
 * 订单交易相关逻辑接口{@link=TradeService}实现类
 * 
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
	private AccountReportService accountReportService;

	@Autowired
	private AccountFreezeDetailService accountFreezeDetailService;

	@Autowired
	private ValidateService validateService;

	@Autowired
	private ConsumeRecordsService consumeRecordsService;

	@Autowired
	private ConsumePointsFreezeInfoService pointsFreezeInfoService;
	
	@Autowired
	private AccountDetailService accountDetailService;
	
	 

	@Override
	@Transactional
	public ResBodyData saveOrder(RequestSaveOrder model) throws MdSysException {
	    ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,"账户冻结成功");
		
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
		
		//根据订单ID，订单来源，订单类型查询该笔交易是否已写入消费记录表
		MSMemberConsumeRecords consumeRecords=consumeRecordsService.getConsumeRecords(model.getOrderId(),model.getOrderSource(),recordsOrderStatus);
		if(consumeRecords!=null){
			logger.warn("重复提交的订单：{}",model.getOrderId());
			throw new ServiceException(ConstApiStatus.REPEAT_ORDER);
		}
		
		//根据订单号查询积分冻结解冻的记录
		List<MSConsumePointsFreezeInfo> listPointsFreezeInfo=pointsFreezeInfoService.getRecordsByOrderId(model.getOrderId());
		//根据订单号查询余额冻结解冻的记录
		List<MSAccountFreezeDetail> listBalanceFreeze=accountFreezeDetailService.getRecordsByOrderId(model.getOrderId());
		
		//获取当前会员可用积分
		Double availablePoints=accountReportService.getAvailablePoints(model.getMemId());
		//获取当前会员可用余额
		Double availableBalance=accountReportService.getAvailableBalance(model.getMemId());
		
		//订单状态为1表示下单未支付，需要冻结积分和余额
		if(model.getOrderStatus()==1){
			
			if(listPointsFreezeInfo.size()>0||listBalanceFreeze.size()>0){
				logger.warn("重复提交的冻结订单");
				throw new ServiceException(ConstApiStatus.REPEAT_FREEZ_ORDER);
			}
			
			if(model.getConsumePoints()>availablePoints){
				logger.warn("积分不足无法支付");
				throw new ServiceException(ConstApiStatus.POINTS_CANNOT_AFFORD);
			}
			if(model.getConsumeMoney()>availableBalance){
				logger.warn("余额不足无法支付");
				throw new ServiceException(ConstApiStatus.BALANCE_CANNOT_AFFORD);
			}
			
			//写入积分冻结解冻记录表
			MSConsumePointsFreezeInfo freezeUnfreezeInfo=new MSConsumePointsFreezeInfo();
			freezeUnfreezeInfo.setMcpfId(UUID.randomUUID().toString());
			freezeUnfreezeInfo.setMemId(model.getMemId());
			freezeUnfreezeInfo.setMcpfOrderId(model.getOrderId());
			freezeUnfreezeInfo.setMcpfConsumePoints(String.valueOf(model.getConsumePoints()));
			freezeUnfreezeInfo.setMcpfRemark("冻结消费积分");
			pointsFreezeInfoService.insertConsumePointsFreezeInfo(freezeUnfreezeInfo,ConstPointsChangeType.POINTS_FREEZE_TYPE_DJ.getCode());
			
			//按照消费优先级冻结账户
			MSAccountFreezeDetail accountFreezeDetail=new MSAccountFreezeDetail();
			accountFreezeDetail.setTradeAmount(model.getConsumeAmount());
			accountFreezeDetail.setTradeType(ConstTradeType.TRADE_TYPE_YEXF.getCode());
			accountFreezeDetail.setTradeDate(new Date());
			accountFreezeDetail.setInOrOut(Constants.CONSTANT_INT_ONE);
			accountFreezeDetail.setFreezeBalance(model.getConsumeMoney());
			accountFreezeDetail.setBusinessNo(model.getOrderId());
			accountFreezeDetail.setRemark("按照消费优先级冻结账户");
			accountServices.freezeAccountBySpendPriority(model.getMemId(),accountFreezeDetail);
			
			//写入消费记录表，订单状态是未支付
			consumeRecords=new MSMemberConsumeRecords();
			BeanUtils.copyProperties(model,consumeRecords);
			consumeRecords.setId(UUID.randomUUID().toString());
			consumeRecords.setTradeTime(accountFreezeDetail.getTradeDate());
			consumeRecords.setOrderStatus(recordsOrderStatus);
			consumeRecords.setCreateUser("账户服务");
			consumeRecords.setUpdateUser("账户服务");
			consumeRecordsService.insertConsumeRecords(consumeRecords);			
		}
		//订单状态为2表示已支付，需要解冻并扣减积分和余额
		if (model.getOrderStatus()==2) {
			//校验冻结的积分或余额和解冻的积分或余额是否相等
			Double freezePoints=0.00;
			for (MSConsumePointsFreezeInfo item : listPointsFreezeInfo) {
				freezePoints+=Double.valueOf(item.getMcpfConsumePoints());
			}
			Double freezeMoney=0.00;
			for (MSAccountFreezeDetail item : listBalanceFreeze) {
				freezeMoney+=item.getFreezeBalance();
			}
			if(freezeMoney.doubleValue()!=model.getConsumeMoney().doubleValue()||(-freezePoints.doubleValue())!=model.getConsumePoints().doubleValue()){
				logger.warn("解冻的积分或余额不等于冻结的积分或余额");
				throw new ServiceException(ConstApiStatus.FREEZE_POINTS_AND_MONEY_NOT_EQUALS_UNFREEZE);
			}
			
			//查询当前会员的总金额
			MSAccountReport accountReport=accountReportService.getTotalAndFreezeBalanceByMemId(model.getMemId());
			double beforeTotalMoney=accountReport.getBalance();
			//扣减后的总金额
			double nowTotalMoney=beforeTotalMoney-model.getConsumeMoney();
			
			//写入积分解冻信息到冻结解冻流水
			MSConsumePointsFreezeInfo freezeUnfreezeInfo=new MSConsumePointsFreezeInfo();
			freezeUnfreezeInfo.setMcpfId(UUID.randomUUID().toString());
			freezeUnfreezeInfo.setMemId(model.getMemId());
			freezeUnfreezeInfo.setMcpfOrderId(model.getOrderId());
			freezeUnfreezeInfo.setMcpfConsumePoints(String.valueOf(model.getConsumePoints()));
			freezeUnfreezeInfo.setMcpfRemark("解冻消费积分");
			pointsFreezeInfoService.insertConsumePointsFreezeInfo(freezeUnfreezeInfo,ConstPointsChangeType.POINTS_FREEZE_TYPE_JD.getCode());
			
			//更新会员积分
			accountServices.updateAccountTotalPoints(model.getMemId(),-model.getConsumePoints());
			
			//写入积分变动明细
			pointsDetailService.insertConsumePointsDetail(model.getMemId(),
					model.getOrderId(),
					model.getOrderSource(),
					"0.00",
					String.valueOf(model.getConsumePoints()),
					String.valueOf(accountReportService.getTotalPointsByMemId(model.getMemId())-model.getConsumePoints()),
					ConstPointsChangeType.POINTS_OPERATOR_TYPE_XF.getCode(),
					model.getMemId(),
					SerialStringUtil.getPointsRemark(ConstPointsChangeType.POINTS_OPERATOR_TYPE_XF.getCode(),model.getMemId()));
			
			//写入账户解冻信息到冻结解冻流水，并更新会员账户和会员账户报表
			Map<String,Double> mapCondition=new HashMap<>();
			mapCondition.put("balance",0.00);
			mapCondition.put("freezeBalance",0.00);
			for (MSAccountFreezeDetail item : listBalanceFreeze) {
				item.setId(UUID.randomUUID().toString());
				item.setTradeDate(new Date());
				item.setInOrOut(Constants.CONSTANT_INT_INVALID);
				item.setRemark("余额解冻");
				accountFreezeDetailService.insertAccoutFreezeDetail(item);
				
				MSAccount msAccount=accountServices.getAccountInfoByMemIdAndAccountNo(model.getMemId()	,item.getAccountNo());
				msAccount.setBalance(msAccount.getBalance()-item.getFreezeBalance());
				msAccount.setBalanceEncrypt(DESC.encryption(String.valueOf(msAccount.getBalance()),model.getMemId()));
				msAccount.setFreezeBalance(msAccount.getFreezeBalance()-item.getFreezeBalance());
				msAccount.setFreezeBalanceEncrypt(DESC.encryption(String.valueOf(msAccount.getFreezeBalanceEncrypt()),model.getMemId()));
				baseDao.update(msAccount,"MSAccountMapper.updateAccountByCondition");
				
				mapCondition.put("balance",mapCondition.get("balance")-item.getFreezeBalance());
				mapCondition.put("freezeBalance",mapCondition.get("freezeBalance")-item.getFreezeBalance());
				mapCondition.put(msAccount.getAccountTypeNo(), -item.getFreezeBalance());
				mapCondition.put("freezeBalance"+msAccount.getAccountTypeNo(),-item.getFreezeBalance());
				
				//写入账户变动明细
				MSAccountDetail msAccountDetail=new MSAccountDetail();
				msAccountDetail.setId(UUID.randomUUID().toString());
				msAccountDetail.setAccountNo(item.getAccountNo());
				msAccountDetail.setTradeType(item.getTradeType());
				msAccountDetail.setTradeAmount(item.getFreezeBalance());
				msAccountDetail.setTradeDate(item.getTradeDate());
				msAccountDetail.setInOrOut(Constants.CONSTANT_INT_INVALID);
				msAccountDetail.setBalance(accountReportService.getTotalAndFreezeBalanceByMemId(model.getMemId()).getBalance());
				msAccountDetail.setBusinessNo(item.getBusinessNo());
				msAccountDetail.setCreateUser("账户服务");
				msAccountDetail.setUpdateUser("账户服务");
				accountDetailService.insertAccountDetail(msAccountDetail);
			}			
			baseDao.update(mapCondition,"MSAccountReportMapper.updateBalanceAndFreezeBalance");
			//更新消费记录表订单状态
			Map<String,Object> mapMcr=new HashMap<>();
			mapMcr.put("orderId",model.getOrderId());
			mapMcr.put("orderStatus",Constants.CONSTANT_INT_ZERO);
			mapMcr.put("newOrderStatus",Constants.CONSTANT_INT_ONE);
			mapMcr.put("orderSource",model.getOrderSource());
			baseDao.update(mapMcr,"MSMemberConsumeRecordsMapper.updateOrderStatus");
			
			Map<String,Object> mapResult=new HashMap<>();
			mapResult.put("before_total_points",String.valueOf(Double.valueOf(listPointsFreezeInfo.get(0).getMcpfConsumePointsBalance())+model.getConsumePoints()));
			mapResult.put("now_total_points",listPointsFreezeInfo.get(0).getMcpfConsumePointsBalance());
			mapResult.put("before_total_money",beforeTotalMoney);
			mapResult.put("now_total_money",nowTotalMoney);
			resBodyData.setMsg("账户扣减成功");
			resBodyData.setData(mapResult);
		}
		return resBodyData;
	}

	@Override
	public ResBodyData cancelOrder(RequestCancelOrder model) {
		ResBodyData resBodyData = new ResBodyData(Constants.CONSTANT_INT_ZERO,"订单取消成功");
		
		int orderStatus=model.getOrderStatus();
		String memId=model.getMemId();
		String orderId=model.getOrderId();
		
		//根据订单号查询积分冻结解冻的记录
		List<MSConsumePointsFreezeInfo> listPointsFreezeInfo=pointsFreezeInfoService.getRecordsByOrderId(orderId);
		//根据订单号查询余额冻结解冻的记录
		List<MSAccountFreezeDetail> listBalanceFreeze=accountFreezeDetailService.getRecordsByOrderId(orderId);
		
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
		// 消费记录
		if (bsFlag) {
			/*
			 * MSMemberConsumeHistory history = new MSMemberConsumeHistory();
			 * history.setMchId(UUID.randomUUID().toString());
			 * history.setMemId(memId); history.setOrderId(orderId);
			 * history.setMchProductName(productName);
			 * history.setMchOrginType(orderSource);
			 * history.setMchOrginMemId("");
			 * history.setMchCreatedDate(tradeDate);
			 * history.setMchPayType(payType); history.setMchStatus("2");
			 * 
			 * history.setMchConsumePointsCount(tradePoint);
			 * history.setMchShoppingCouponCount(tradeAmount);
			 * history.setMchSettingStatus(1); history.setMchIssueStatus(1);
			 * saveMemberTradeHistory(history);
			 */
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

	/*
	 * private boolean saveMemberTradeHistory(MSMemberConsumeHistory history)
	 * throws Exception { int flag = baseDao.insert(history,
	 * "MSAccountMapper.insertMemberConsumeHistory"); if (flag <= 0) {
	 * logger.error("写入会员消费记录表失败，会员编号：{}，订单编号：{}", history.getMemId(),
	 * history.getOrderId()); return false; } return true; }
	 */

	@Override
	@Transactional
	public ResBodyData recedeOrder(MSMemberConsumeRecordsReq ms)  throws MdSysException{

		JSONObject json;
		try {
			// 查询数据库是否已存在该订单
			MSMemberConsumeRecords history = consumeRecordsService.getConsumeRecords(ms.getOrderId(), ms.getOrderSource(), Integer.valueOf(ms.getOrderStatus()));
			
			if (null == history) {
				logger.info("当前退单的订单号与已提交的订单号不匹配");
				return new ResBodyData(2063, "当前退单的订单号与已提交的订单号不匹配");
			}

			if (DateUtil.daysBetween(history.getCreateDate(), new Date()) > 30) {
				logger.info("当前退单时间超过下单时的时间，无法退单");
				return new ResBodyData(2066, "当前退单时间超过下单时的时间，无法退单");
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

			// 消费卷全部返回0
			json.put("pre_consume_coupon", "0.00");
			json.put("after_consume_coupon", "0.00");
			logger.info("退费订单号：{}，进入退费美积分计算方法.", ms.getOrderId());
			logger.info("返还余额为: {} , 返还积分为: {}", ms.getConsumeMoney(), ms.getConsumePoints());
			if (null != ms.getConsumeMoney()) {
				// 退单增加余额
				/*accountAdjustService.addConsumeMoneyAndDetail(ms.getMemId(), ms.getOrderId(),
						ConstTradeType.TRADE_TYPE_TKSH.getCode(), new Date(), ms.getConsumeMoney(),
						ConstTradeType.TRADE_TYPE_TKSH.getCode());*/
				
				//根据订单号查询账户明细
				List<MSAccountDetail> listAccountDetail = accountDetailService.listAccountDetail(new MSAccountDetailGet(ms.getOrderId()));
				List<MSAccount> msAccountlist = new ArrayList<MSAccount>();
				//根据memId查询会员余额
				List<MSAccount> balanceAccountList = accountServices.getBalanceAccountList(ms.getMemId());
			    for (MSAccount msAccount : balanceAccountList) {
			    	for (MSAccountDetail msAccountDetail : listAccountDetail) {
			    		MSAccount account = new MSAccount();
			    		if(msAccount.getAccountNo().equals(msAccountDetail.getAccountNo())){
			    			  account.setAccountNo(msAccount.getAccountNo());
			    		      account.setBalance(msAccountDetail.getTradeAmount()+msAccount.getBalance());
			    		      account.setBalanceEncrypt(DESC.encryption(String.valueOf(msAccountDetail.getTradeAmount()+msAccount.getBalance()), msAccount.getMemId()));
			    		      msAccountlist.add(account);
			    		}
			    		
			    	}
				}
				
				accountAdjustService.batchUpdateBalance(msAccountlist);
				

				// 退单后余额
				double afterMoney = DoubleCalculate.add(preConsumeMoney, Double.valueOf(ms.getConsumeMoney()));
				
				Map<String, Object> map = new HashMap<>();
				map.put("balance", ms.getConsumeMoney());
				map.put("memId", ms.getMemId());
				accountReportService.updateBalanceAndfreezeBalance(map);

				// 返回退单后余额
				json.put("after_shopping_coupon", StringUtil.interceptionCharacter(2, afterMoney));

				logger.info("退费订单号：" + ms.getOrderId() + "，当次退费余额是：" + ms.getConsumeMoney());

			}
			// 增加美兑积分需求 2016-11-01
			if (null != ms.getConsumePoints()) {
				// 退单返回美兑积分
				try {
					accountAdjustService.addMDConsumePoints(ms.getMemId(), ms.getConsumePoints(), false);
				} catch (MdSysException e) {
					logger.error("退单返回美兑积分错误:{}", e);
					throw new ServiceException(ConstApiStatus.MD_POINTS_ERROR,
							ConstApiStatus.getZhMsg(ConstApiStatus.MD_POINTS_ERROR));
				}
				// 退单后积分余额
				double afterPoints = DoubleCalculate.add(preConsumePoints, Double.valueOf(ms.getConsumePoints()));
				// 同时更新订单表的

				// 返回退单后积分
				json.put("after_consume_points", StringUtil.interceptionCharacter(2, afterPoints));

				logger.info("退费订单号：" + ms.getOrderId() + "，当月退费美积分是：" + ms.getConsumePoints());
			}
			
			Map<String,Object> mapCondition=new HashMap<>();
			mapCondition.put("newOrderStatus",2);
			mapCondition.put("orderId",ms.getOrderId());
			mapCondition.put("orderSource",ms.getOrderSource());
			mapCondition.put("orderStatus",ms.getOrderStatus());
			consumeRecordsService.updateOrderStatus(mapCondition);

			logger.info("当前退余额: " + ms.getConsumeMoney() + "当前退积分：" + ms.getConsumePoints());
			 
		} catch (DaoException e) {
			logger.error(ConstApiStatus.getZhMsg(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION) + ":{}", e);
			throw new ServiceException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION,
					ConstApiStatus.getZhMsg(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION));
		}

		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M, json);
	}


	@Override
	@Transactional
	public ResBodyData saveMemberOrder(MSMemberConsumeRecordsReq mmt) throws MdSysException {
		JSONObject result = new JSONObject();
		mmt.setId(UUID.randomUUID().toString());
		// 消费时间
		mmt.setCreateDate(new Date());
		// 更新时间
		mmt.setUpdateDate(new Date());
		mmt.setCreateUser(ConstSysParamsDefination.CREATE_USER_NAME);
		mmt.setUpdateUser(ConstSysParamsDefination.CREATE_USER_NAME);

		// 查询数据库是否已存在该订单，如果不存在则直接保存，如果存在则修改
		MSMemberConsumeRecords queryByOrderIdInfo=consumeRecordsService.getConsumeRecords(mmt.getOrderId(),mmt.getOrderSource(),Integer.valueOf(mmt.getOrderStatus()));

		if (StringUtils.isEmpty(queryByOrderIdInfo)) {
			logger.info("重复提交的订单");
			return new ResBodyData(ConstApiStatus.REPEAT_ORDER, ConstApiStatus.getZhMsg(ConstApiStatus.REPEAT_ORDER));
		} else {

			// 根据订单查询冻结积分
			MSConsumePointsFreezeInfo points = pointsFreezeInfoService.getPointsFreezeByOrderId(mmt.getOrderId());
			// 获取可使用积分
			Double consumePoints = pointsService.getAvailablePointsByMemId(mmt.getMemId());
			// 获取可使用余额
			Double useConsumeMoney = accountReportService.getAvailableBalance(mmt.getMemId());

			// 根据订单查询冻结余额
			List<MSAccountFreezeDetail> moneyFreezeList =accountFreezeDetailService.getRecordsByOrderId(mmt.getOrderId());
			

			logger.info("当前可使用积分：" + consumePoints + "，可使用余额：" + useConsumeMoney);

			// 增加美兑积分逻辑 2016-10-31 美兑积分解冻
			if (isZero(mmt.getConsumePoints())) {
				logger.info("订单编号：" + mmt.getOrderId() + ",进入积分解冻方法.");
				if (!StringUtils.isEmpty(points)) {
					  
					// 解冻金额和冻结金额是否一样
					if (DoubleCalculate.add(Double.valueOf(mmt.getConsumePoints()),
							Double.valueOf(points.getMcpfConsumePoints())) != 0.0) {
						logger.info("订单解冻积分不等于冻结积分");
						return new ResBodyData(ConstApiStatus.DJ_NOT_EQUALS_DJ,
								ConstApiStatus.getZhMsg(ConstApiStatus.DJ_NOT_EQUALS_DJ));
					}
				 
					//插入ms_consume_points_freeze_info表数据(冻结解冻类型 为 解冻)
					points.setMcpfId(UUID.randomUUID().toString());
					points.setMcpfRemark(ConstPointsChangeType.POINTS_FREEZE_TYPE_JD.getName());
					pointsFreezeInfoService.insertConsumePointsFreezeInfo(points, ConstPointsChangeType.POINTS_FREEZE_TYPE_JD.getCode());
					MSConsumePointsDetail msConsumePoints = new MSConsumePointsDetail();
					msConsumePoints.setMcpId(UUID.randomUUID().toString());
					msConsumePoints.setMemId(points.getMemId());
					msConsumePoints.setMcpOrderId(points.getMcpfOrderId());
					msConsumePoints.setMcpOrderSource(mmt.getOrderSource());
					msConsumePoints.setMcpOperatorType(ConstPointsChangeType.POINTS_OPERATOR_TYPE_TJ.getCode());
					msConsumePoints.setMcpExpenditure(points.getMcpfConsumePoints());
					msConsumePoints.setMcpBalance(points.getMcpfConsumePointsBalance());
					msConsumePoints.setMcpCreatedBy(mmt.getCreateUser());
					msConsumePoints.setMcpUpdatedBy(mmt.getUpdateUser());
					
					//去插入ms_consume_points_detail(操作类型 为 调减)
					pointsDetailService.insertConsumePointsDetail(msConsumePoints);
					

					//更新ms_members的总积分和冻结积分
					accountAdjustService.cutMDConsumePoints(mmt.getMemId(), mmt.getConsumePoints(), false);
					
					logger.info(mmt.getOrderId() + ";解冻" + mmt.getConsumePoints());
				} else {
					logger.info("没有冻结的积分记录");
					return new ResBodyData(ConstApiStatus.NO_DJ_POINTS,
							ConstApiStatus.getZhMsg(ConstApiStatus.NO_DJ_POINTS));
				}
			}
			
			
			
			// 增加账户余额支付逻辑 2017-03-02
			if (isZero(mmt.getConsumeMoney())) {
				logger.info("订单编号：" + mmt.getOrderId() + ",进入余额解冻方法.");
				if (moneyFreezeList.size() > 0) {
					// 检查重复解冻
					if (moneyFreezeList.size() > 1) {
						logger.info("重复提交的冻结订单" + mmt.getOrderId() + ";冻结" + mmt.getConsumeMoney());
						return new ResBodyData(ConstApiStatus.REPEAT_FREEZ_ORDER,
								ConstApiStatus.getZhMsg(ConstApiStatus.REPEAT_FREEZ_ORDER));
					}
					
					
					//查询冻结的余额,解冻,扣减.
					double moneySum = 0.00;
					List<MSAccountDetail> MSAccountDetaillist = new ArrayList<>();
					for (MSAccountFreezeDetail msAccountFreezeDetail : moneyFreezeList) {
						msAccountFreezeDetail.setId(UUID.randomUUID().toString());
						msAccountFreezeDetail.setInOrOut(-1);
						msAccountFreezeDetail.setRemark(ConstPointsChangeType.POINTS_FREEZE_TYPE_JD.getName());
						
						//为账户明细表设置数据
						MSAccountDetail msAccountDetail = new MSAccountDetail();
						msAccountDetail.setId(UUID.randomUUID().toString());
						msAccountDetail.setAccountNo(msAccountFreezeDetail.getAccountNo());
						msAccountDetail.setTradeType(msAccountFreezeDetail.getTradeType());
						msAccountDetail.setTradeAmount(msAccountFreezeDetail.getTradeAmount());
						msAccountDetail.setTradeDate(msAccountFreezeDetail.getTradeDate());
						msAccountDetail.setInOrOut(-1);
						msAccountDetail.setBalance(msAccountFreezeDetail.getFreezeBalance());
						msAccountDetail.setBusinessNo(msAccountFreezeDetail.getBusinessNo());
						msAccountDetail.setCreateUser(mmt.getCreateUser());
						msAccountDetail.setUpdateUser(mmt.getUpdateUser());
						msAccountDetail.setRemark(ConstPointsChangeType.POINTS_FREEZE_TYPE_JD.getName());
						MSAccountDetaillist.add(msAccountDetail);
						
						//计算总的冻结余额
						moneySum += msAccountFreezeDetail.getFreezeBalance();
					}
					
					// 解冻金额和冻结金额是否一样
					if (DoubleCalculate.sub(Double.valueOf(mmt.getConsumeMoney()),
							moneySum) != 0.0) {
						logger.info("订单解冻余额不等于冻结余额!");
						return new ResBodyData(ConstApiStatus.MONEY_DJ_NOT_EQUALS_DJ,
								ConstApiStatus.getZhMsg(ConstApiStatus.MONEY_DJ_NOT_EQUALS_DJ));
					}
					
					//批量插入账户余额冻结解冻明细
					accountFreezeDetailService.batchInsertAccoutFreezeDetail(moneyFreezeList);
					//批量插入账户余额明细
					accountDetailService.batchInsertAccoutDetail(MSAccountDetaillist);
					
					
					//accountReport表 冻结余额
					MSAccountReport totalAndFreezeBalanceByMemId = accountReportService.getTotalAndFreezeBalanceByMemId(mmt.getMemId());
					Double freezeBalance = totalAndFreezeBalanceByMemId.getFreezeBalance() - Double.valueOf(mmt.getConsumeMoney());
					Map<String, Object> map = new HashMap<>();
					map.put("balance", mmt.getConsumeMoney());
					map.put("freezeBalance", freezeBalance);
					map.put("memId", mmt.getMemId());
					//更新accountReport表
					accountReportService.updateBalanceAndfreezeBalance(map);

					logger.info(mmt.getOrderId() + ";解冻" + mmt.getConsumeMoney());
				} else {
					logger.info("没有冻结的余额记录");
					return new ResBodyData(ConstApiStatus.NO_DJ_MONEY,
							ConstApiStatus.getZhMsg(ConstApiStatus.NO_DJ_MONEY));
				}
			}
			
			
			
			
			Map<String,Object> mapCondition=new HashMap<>();
			mapCondition.put("newOrderStatus",1);
			mapCondition.put("orderId",mmt.getOrderId());
			mapCondition.put("orderSource",mmt.getOrderSource());
			mapCondition.put("orderStatus",mmt.getOrderStatus());
			consumeRecordsService.updateOrderStatus(mapCondition);

			Double beforeCouponsBalance = Double.parseDouble("0");
			Double endCouponsBalance = Double.parseDouble("0");
			// 美兑积分
			Double beforeConsumePoints = DoubleCalculate.add(consumePoints,
					Double.parseDouble(mmt.getConsumePoints() == null ? "0" : mmt.getConsumePoints())); // 扣除积分前余额=当前余额+扣除积分
			// 账户余额
			Double beforeShoppingBalance = DoubleCalculate.add(useConsumeMoney,
					Double.parseDouble(mmt.getConsumeMoney() == null ? "0" : mmt.getConsumeMoney())); // 扣除前余额=当前余额+扣除余额

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
	 * 
	 * @param obj
	 * @return
	 */
	private boolean isZero(Object obj) {
		if (obj == null) {
			return false;
		}
		try {
			if (Double.valueOf(obj.toString()) > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
