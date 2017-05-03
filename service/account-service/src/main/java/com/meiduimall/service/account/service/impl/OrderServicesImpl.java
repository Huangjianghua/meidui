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

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.ApplicationConstant;
import com.meiduimall.service.account.constant.SysParamsConst;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.MSMemberConsumeHistory;
import com.meiduimall.service.account.model.ResBodyData;
import com.meiduimall.service.account.model.request.RequestFreezeUnFreeze;
import com.meiduimall.service.account.model.request.RequestUnfreezeDecut;
import com.meiduimall.service.account.service.AccountServices;
import com.meiduimall.service.account.service.BankAccountService;
import com.meiduimall.service.account.service.BankWithdrawDepositService;
import com.meiduimall.service.account.service.MoneyService;
import com.meiduimall.service.account.service.OrderService;
import com.meiduimall.service.account.service.PointsService;
import com.meiduimall.service.account.util.DoubleCalculate;
import com.meiduimall.service.account.util.GenerateNumber;
import com.meiduimall.service.account.util.StringUtil;

@Service
public class OrderServicesImpl implements OrderService {
	
	private final static Logger logger=LoggerFactory.getLogger(OrderServicesImpl.class);

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private PointsService pointsService;
	
	@Autowired
	private MoneyService moneyService;
	
	@Autowired
	private AccountServices accountServices;
	
	@Autowired
	private BankWithdrawDepositService bankWithdrawDepositService;
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@Override
	public ResBodyData freezeUnfreeze(RequestFreezeUnFreeze param){
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		String memId=param.getMemId();
		Double consumePoints=param.getConsume_points();
		Double consumeMoney=param.getConsume_money();
		String orderId=param.getOrderID();
		String orderSource=param.getOrder_source();
		
		/**冻结积分*/
		resBodyData=pointsService.freezePointsAndAddRecord(memId,consumePoints,orderId,orderSource);
		logger.info("冻结积分结果：{}",resBodyData.toString());
		if(resBodyData.getStatus()!=0){
			return resBodyData;
		}
		
		/**冻结余额*/
		resBodyData=moneyService.freezeMoneyAndAddRecord(memId,consumeMoney,orderId,orderSource);
		logger.info("冻结余额结果：{}",resBodyData.toString());
		if(resBodyData.getStatus()!=0){
			return resBodyData;
		}
		
		return resBodyData;
	}
	
	@Override
	public ResBodyData unfreezeDeduct(RequestUnfreezeDecut param) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(null,null);
		String memId=param.getMemId();
		Double consumePoints=param.getConsume_points();
		Double consumeMoney=param.getConsume_money();
		String orderId=param.getOrderID();
		String orderSource=param.getOrder_source();
		String productName=param.getProduct_name();
		String payType=param.getPay_type();
		/**解冻并扣减积分*/
		Map<String,Object> dataMap=new HashMap<>();
		if(pointsService.getFreezeUnfreezeRecordByOrderId(orderId)){
			pointsService.unFreezePointsAndAddRecord(memId,consumePoints,orderId,orderSource,dataMap);
			pointsService.deductPointsAndAddRecord(memId,consumePoints,orderId,orderSource,dataMap);
			}
		/**解冻并扣减余额*/
		if(moneyService.getFreezeUnfreezeRecordByOrderId(orderId)){
			moneyService.unFreezeMoneyAndAddRecord(memId,consumeMoney,orderId,orderSource,dataMap);
			moneyService.deductMoneyAndAddRecord(memId,consumeMoney,orderId,orderSource,dataMap);
		}
		resBodyData.setData(dataMap);
		/**写入会员消费记录*/
		MSMemberConsumeHistory history = new MSMemberConsumeHistory();
		history.setMchId(UUID.randomUUID().toString());
		history.setMemId(memId);
		history.setOrderId(orderId);
		history.setMchProductName(productName);
		history.setMchOrginType(orderSource);
		history.setMchOrginMemId("");
		history.setMchPayType(payType);
		history.setMchStatus("1");
		history.setMchConsumePointsCount(consumePoints);
		history.setMchShoppingCouponCount(consumeMoney);
		history.setMchSettingStatus(1);
		history.setMchIssueStatus(1);
		try {
			this.saveMemberTradeHistory(history);
		} catch (Exception e) {
		
		}
		return resBodyData;
	}

	@Override
	public JSONObject accountTradeCancel(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		
		final String userId = param.getString("user_id");  //用户标识
		final String orderId = param.getString("order_id");//订单号
		final String orderSource = param.getString("order_source");//数据来源
		final String tradeType = param.getString("trade_type");    //业务类型
		final String tradeDateStr = param.getString("trade_date");    //订单发生时间
		final String payType = param.getString("pay_type");                  //支付方式
		final String productName = param.getString("product_name");          //消费项目
		final String tradeTotalMoney = param.getString("trade_total_money"); //消费总额
		final String tradeMoney = param.getString("trade_money");            //消费使用现金金额
		final String tradeAmount = param.getString("trade_amount");   //消费使用余额
		final String tradePoint = param.getString("trade_point");     //消费使用积分
		final String remark = param.getString("remark");              //备注
		boolean bsFlag = true;
		
		//数据转换
		Date tradeDate = new Date(Long.parseLong(tradeDateStr));
		//检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		
		//解冻积分,并扣减积分
		if(bsFlag){
			if(accountServices.checkFreezePointByOrderId(orderId)){
				bsFlag = accountServices.cutMDConsumePointsFreezeAndDetail(memId, tradePoint, orderId, orderSource,
						tradeType, memId, remark);
			}
		}
		//解冻余额，并扣减余额
		if(bsFlag){
			if(accountServices.checkFreezeMoneyByOrderId(orderId)){
				bsFlag = accountServices.cutConsumeFreezeMoneyAndDetail(memId, orderId, tradeType, tradeDate,
						tradeAmount, remark);
			}
		}
		
		//出现错误返回运行时异常回滚事务
		if(!bsFlag){
			throw new RuntimeException("accountTradeCancel-业务处理时出现错误-1002，回滚事务。");
		}
		
		return resultJson;
	}
	
	

	@Override
	public JSONObject accountTradeRefundApply(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		//暂不实现，库表不支持
		return resultJson;
	}

	@Override
	public JSONObject accountTradeRefundAffirm(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
	/*	resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		
		final String userId = param.getString("user_id");  //用户标识
		final String orderId = param.getString("order_id");//订单号
		final String orderSource = param.getString("order_source");//数据来源
		final String tradeType = param.getString("trade_type");    //业务类型
		final String payType = param.getString("pay_type");                  //支付方式
		final String productName = param.getString("product_name");          //项目
		final String tradeTotalMoney = param.getString("trade_total_money"); //总额
		final String tradeMoney = param.getString("trade_money");            //使用现金金额
		final String tradeDateStr = param.getString("trade_date");    //订单发生时间
		final String tradeAmount = param.getString("trade_amount");   //退款余额
		final String tradePoint = param.getString("trade_point");     //退款积分
		final String remark = param.getString("remark");              //备注
		boolean bsFlag = true;
		
		//数据转换
		Date tradeDate = new Date(Long.parseLong(tradeDateStr));
		//获取会员id
		String memId = accountServices.getMemIdByUserId(userId);
		//检查订单退款有效性
		List<Map<String,String>> historyList = baseDao.selectList(orderId, "MSAccountMapper.getConsumeHistoryByOrderId");
		Double pointTotal = new Double("0");      //积分总额
		Double pointRefundTotal = new Double("0");//退款积分总额 
		Double moneyTotal = new Double("0");      //余额总额
		Double moneyRefundTotal = new Double("0");//退款余额总额
		for (Map<String, String> tmpMap : historyList) {
			String status = tmpMap.get("status");
			//获取已完成订单的积分与余额
			if("1".equals(status)){
				moneyTotal = Double.valueOf(tmpMap.get("money"));
				pointTotal = Double.valueOf(tmpMap.get("point"));
			}
			//获取已退款的积分与余额
			if("2".equals(status)){
				moneyRefundTotal = DoubleCalculate.add(moneyRefundTotal,Double.valueOf(tmpMap.get("money")));
				pointRefundTotal = DoubleCalculate.add(pointRefundTotal,Double.valueOf(tmpMap.get("point")));
			}
		}
		if (DoubleCalculate.sub(DoubleCalculate.sub(moneyTotal, moneyRefundTotal), 
				Double.valueOf(tradeAmount)) < 0) {
			
			return resultJson;
		}
		if (DoubleCalculate.sub(DoubleCalculate.sub(pointTotal, pointRefundTotal), 
				Double.valueOf(tradePoint)) < 0) {
	
			return resultJson;
		}
		
		//退款增加积分
		if(bsFlag){
			if(accountServices.checkFreezePointByOrderId(orderId)){
				if(!StringUtil.isEmptyByString(tradePoint)
						&& StringUtil.checkNumber(tradePoint,"+")){
					bsFlag = accountServices.addMDConsumePointsAndDetail(memId, tradePoint, orderId, orderSource,
							tradeType, memId, remark);
				}
			}
		}
		//退款增加余额
		if(bsFlag){
			if(accountServices.checkFreezeMoneyByOrderId(orderId)){
				if(!StringUtil.isEmptyByString(tradeAmount)
						&& StringUtil.checkFloat(tradeAmount,"+")){
					bsFlag = accountServices.addConsumeMoneyAndDetail(memId, orderId, tradeType, tradeDate,
							tradeAmount, remark);
				}
			}
		}
		//消费记录
		if(bsFlag){
			MSMemberConsumeHistory history = new MSMemberConsumeHistory();
			history.setMchId(UUID.randomUUID().toString());
			history.setMemId(memId);
			history.setOrderId(orderId);
			history.setMchProductName(productName);
			history.setMchOrginType(orderSource);
			history.setMchOrginMemId("");
			history.setMchCreatedDate(tradeDate);
			history.setMchPayType(payType);
			history.setMchStatus("2");
/*			history.setMchConsumePointsCount(tradePoint);
			history.setMchShoppingCouponCount(tradeAmount);*/
			history.setMchSettingStatus(1);
			history.setMchIssueStatus(1);
			saveMemberTradeHistory(history);
		}
				
		//出现错误返回运行时异常回滚事务
		if(!bsFlag){
			throw new RuntimeException("accountTradeRefundAffirm-业务处理时出现错误-1004，回滚事务。");
		}
				
		return resultJson;
	}

	@Override
	public JSONObject bankWithdrawDepositApply(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		
		final String userId = param.getString("user_id");    //用户标识
		final String accountIdcard = param.getString("account_idcard");//身份证
		final String accountNo = param.getString("account_no");//银行卡号
		final String accountName = param.getString("account_name");//银行卡户名
		final String accountBank = param.getString("account_bank");//银行名称
		String accountProvince = "";//银行所属省份
		if(param.containsKey("account_province")){
			accountProvince = param.getString("account_province");//银行所属省份
		}
		String accountCity = "";//银行所属城市
		if(param.containsKey("account_city")){
			accountCity = param.getString("account_city");//银行所属城市
		}
		String accountArea = "";//银行所属地区
		if(param.containsKey("account_area")){
			accountArea = param.getString("account_area");//银行所属地区
		}
		final String accountSubBank = param.getString("account_sub_bank");//支行名称
		final String applyCarryCash = param.getString("apply_carry_cash");//申请提现金额
		final String counterFee = param.getString("counter_fee");//申请提现手续费
		final String applyDate = param.getString("apply_date");//申请提现时间
		
		//检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if(StringUtil.isEmptyByString(memId)){
		/*	resultJson.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
			resultJson.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);*/
			return resultJson;
		}
		//检查银行信息是否存在 
		MSBankAccount bankAccount = bankAccountService.getBankAccount(memId, accountNo);
		if(bankAccount == null){
			return resultJson;
		}
		//检查申请余额，并计算 
		final Double old_useMoney = accountServices.getUseConsumeMoney(memId);
		final Double old_applyCarryCash = Double.valueOf(applyCarryCash);
		//申请提现余额超过最大可提现金额
		if(old_applyCarryCash > 50000){
			return resultJson;
		}
		//计算申请提现余额是否超最大余额
		if(old_applyCarryCash > old_useMoney){
			return resultJson;
		}
		//计算当前余额是否低于最低提现金额
		if(old_useMoney <= ApplicationConstant.MIN_APPLY_CARRY_CASH){
			return resultJson;
		}
		
		String businessNo = GenerateNumber.generateBusinessNo(ApplicationConstant.MONEY_TRADE_TYPE_YETX);
		MSBankWithdrawDeposit dto = new MSBankWithdrawDeposit();
		dto.setBusinessNo(businessNo);
		dto.setMemId(memId);
		dto.setBankAccountId(bankAccount.getId());
		dto.setAccountIdcard(accountIdcard);
		dto.setAccountNo(accountNo);
		dto.setAccountBank(accountBank);
		dto.setAccountName(accountName);
		dto.setAccountProvince(accountProvince);
		dto.setAccountCity(accountCity);
		dto.setAccountArea(accountArea);
		dto.setAccountSubBank(accountSubBank);
		dto.setApplyCarryCash(applyCarryCash);
		dto.setCounterFee(counterFee);
		dto.setApplyDate(new Date(Long.parseLong(applyDate)));
		dto.setStatus("0");
		
		//计算扣除金额与手续费
		Map<String, String> returnMap = this.calcBankWithdrawDeposit(memId, dto.getApplyCarryCash(), 
				dto.getCounterFee());
		//计算后实际金额
		String calcActualCarryCash = returnMap.get("calc_actualCarryCash");
		//计算后手续费
		String calcCounterFee = returnMap.get("calc_counterFee");
		//数据检查  实际提现金额不能大于申请提现金额
		if(Double.parseDouble(calcActualCarryCash) > Double.parseDouble(dto.getApplyCarryCash())){
			throw new RuntimeException("bankWithdrawDepositApply-业务处理时出现错误-1001，回滚事务。");
		}
		//数据检查  实际提现金额必须大于0
		if(Double.parseDouble(calcActualCarryCash) <= 0){
			throw new RuntimeException("bankWithdrawDepositApply-业务处理时出现错误-1002，回滚事务。");
		}
		//添加到dto中
		dto.setActualCarryCash(calcActualCarryCash);
		dto.setCounterFee(calcCounterFee);
		//提现申请时间
		Date tradeDate = new Date(Long.parseLong(applyDate));
		
		//增加一条提现记录，返回业务流水号
		String id = bankWithdrawDepositService.addBankWithdrawDeposit(dto);
		if(!StringUtil.isEmptyByString(id)){
			MSAccount account = accountServices.getAccountMoney(memId);
			if(account != null){
				//余额提现冻结余额
				accountServices.addConsumeFreezeMoneyAndDetail(memId, businessNo,
						ApplicationConstant.MONEY_TRADE_TYPE_YETX, tradeDate, calcActualCarryCash, "余额提现");
				//余额提现冻结手续费
				accountServices.addConsumeFreezeMoneyAndDetail(memId, businessNo,
						ApplicationConstant.MONEY_TRADE_TYPE_TXSX, tradeDate, calcActualCarryCash, "提现手续费");
			}
		}else{
			throw new RuntimeException("bankWithdrawDepositApply-业务处理时出现错误-1003，回滚事务。");
		}
		
		return resultJson;
	}

	@Override
	public JSONObject getBankWithdrawDeposits(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		
		final String userId = param.getString("user_id");    //用户标识
		final String page = param.getString("current_page");//当前页数
		final String pagesize = param.getString("page_size");//每页数量
		
		//检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if(StringUtil.isEmptyByString(memId)){
			/*resultJson.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
			resultJson.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);*/
			return resultJson;
		}
		Map<String, String> resultMap = new HashMap<>();
		Map<String, String> parasMap = new HashMap<>();
		parasMap.put("current_page", page);
		parasMap.put("page_size", pagesize);
		List<MSBankWithdrawDeposit> bankWithdrawDepositList = bankWithdrawDepositService
				.getBankWithdrawDepositList(memId, true, parasMap, resultMap);
		if(bankWithdrawDepositList != null){
			final JSONObject dataJson = new JSONObject();
			dataJson.put("total_page", resultMap.get("pageTotal"));
			/*dataJson.put("data_list", JSONArray.toJSON(bankWithdrawDepositList).toString());*/
			
			resultJson.put(SysParamsConst.RESULT, dataJson);
		}else{
			final JSONObject dataJson = new JSONObject();
			dataJson.put("total_page", "0");
			dataJson.put("data_list", "[]");
			resultJson.put(SysParamsConst.RESULT, dataJson);
		}
		return resultJson;
	}
	
	/**
	 * 方法名: calcBankWithdrawDeposit<br>
	 * 描述: 计算可提现金额与手续费 <br>
	 * 创建时间: 2016-12-19
	 * @param dto
	 * @return
	 */
	private Map<String,String> calcBankWithdrawDeposit(String memId,String applyCarryCash,String counterFee){
		
		Map<String,String> returnMap = new HashMap<String, String>();
		
		final Double old_useMoney = accountServices.getUseConsumeMoney(memId);
		final Double old_applyCarryCash = Double.valueOf(applyCarryCash);
		//final Double old_counterFee = Double.valueOf(counterFee);
		final Double calc_feeScale = new Double("0.01"); //手续费比例
		final Double calc_minFee = ApplicationConstant.MIN_APPLY_CARRY_FEE;   //最小手续费
		
		Double calc_counterFee = new Double("0");
		Double calc_actualCarryCash = new Double("0");
		
		//计算手续费与实际提现金额
		if(old_applyCarryCash == old_useMoney){
			//全部提取，手续费从申请申请提取金额中扣除，实际提现金额=申请提取金额-手续费
			calc_counterFee = DoubleCalculate.mul(old_applyCarryCash, calc_feeScale);
			if(calc_counterFee < calc_minFee){
				calc_counterFee = calc_minFee;
			}
			calc_actualCarryCash = DoubleCalculate.sub(old_applyCarryCash, calc_counterFee);
		}else{
			//部分提取，手续费直接从余额中扣除，实际提现金额=申请提取金额；余额不足扣除手续费时，实际提现金额=申请提取金额-扣除余额后不足的手续费
			calc_counterFee = DoubleCalculate.mul(old_applyCarryCash, calc_feeScale);
			if(calc_counterFee < calc_minFee){
				calc_counterFee = calc_minFee;
			}
			//计算扣除申请提现金额后的余额
			Double subUseMoney = DoubleCalculate.sub(old_useMoney, old_applyCarryCash);
			//余额够扣除手续费时，实际提现金额=申请提取金额
			if(subUseMoney >= calc_counterFee){
				calc_actualCarryCash = old_applyCarryCash;
			}else{
				//余额不足扣除手续费时，实际提现金额=申请提取金额-扣除余额后不足的手续费
				Double subCounterFee = DoubleCalculate.sub(calc_counterFee,subUseMoney); //扣除余额后剩余未扣减手续费
				calc_actualCarryCash = DoubleCalculate.sub(old_applyCarryCash, subCounterFee);
			}
		}
		returnMap.put("calc_actualCarryCash", String.valueOf(calc_actualCarryCash));
		returnMap.put("calc_counterFee", String.valueOf(calc_counterFee));
		return returnMap;
	}
	

	private boolean saveMemberTradeHistory(MSMemberConsumeHistory history) throws Exception {
		int flag = baseDao.insert(history, "MSAccountMapper.insertMemberConsumeHistory");
		if(flag <= 0){
			logger.error("写入会员消费记录表失败，会员编号：{}，订单编号：{}", history.getMemId(), history.getOrderId());
			return false;
		}
		return true;
	}

}
