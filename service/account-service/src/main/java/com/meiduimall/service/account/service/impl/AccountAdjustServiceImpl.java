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

import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.constant.ConstTradetTypeToAccountTypeNo;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.model.MSAccountDetail;
import com.meiduimall.service.account.model.MSAccountType;
import com.meiduimall.service.account.model.request.RequestAccountAdjustAmount;
import com.meiduimall.service.account.service.AccountAdjustService;
import com.meiduimall.service.account.service.AccountDetailService;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.AccountService;
import com.meiduimall.service.account.service.AccountTypeService;
import com.meiduimall.service.account.service.ValidateService;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DateUtil;
import com.meiduimall.service.account.util.DoubleCalculate;

/**
 * 账户调整相关接口{@link=AccountAdjustService}实现类
 * @author chencong
 *
 */
@Service
public class AccountAdjustServiceImpl implements AccountAdjustService {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountAdjustServiceImpl.class);
	
	@Autowired
	private ValidateService validateService;
	
	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountTypeService accountTypeService;
	
	@Autowired
	private AccountDetailService accountDetailService;
	
	@Autowired
	private AccountFreezeDetailService accountFreezeDetailService;
	
	@Autowired
	private AccountReportService accountReportService;

	@Transactional
	@Override
	public ResBodyData accountAdjustAmount(RequestAccountAdjustAmount model) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(Constants.CONSTANT_INT_ZERO,"调账成功");
		
		//校验调账类型是否合法
		validateService.checkAdjustType(model.getDirection());
		//校验交易类型是否合法
		validateService.checkTradeType(model.getTradeType());
		//校验交易金额是否合法
		validateService.checkTradeAmount(model.getTrade_amount(),"0+");
		//校验是否是重复的订单
		validateService.checkRepeatAccoutDetailByOrderId(model.getOrderId());
		
		//根据交易类型取出对应的账户类型
		String accountTypeNo=ConstTradetTypeToAccountTypeNo.getNameByCode(model.getTradeType());
		
		//根据会员ID和账户类型查询对应的账户
		MSAccount msAccount=accountService.getAccountInfoByMemIdAndAccountTypeNo(model.getMemId(),accountTypeNo);
		
		//若该类型的账户不存在，就创建一个
		if(msAccount==null){
			logger.warn("会员：{}类型为{}的账户不存在，开始生成",model.getMemId(),accountTypeNo);
			msAccount=new MSAccount();
			msAccount.setId(UUID.randomUUID().toString());
			msAccount.setMemId(model.getMemId());
			msAccount.setAccountTypeNo(accountTypeNo);
			msAccount.setAccountNoSequence(accountTypeService.updateSequenceByAccountTypeNo(accountTypeNo));
			msAccount.setAccountNo(msAccount.getAccountTypeNo()+msAccount.getAccountNoSequence());
			msAccount.setBalance(0.00);
			msAccount.setBalanceEncrypt(DESC.encryption(String.valueOf(msAccount.getBalance()),model.getMemId()));
			msAccount.setFreezeBalance(0.00);
			msAccount.setFreezeBalanceEncrypt(DESC.encryption(String.valueOf(msAccount.getFreezeBalance()),model.getMemId()));
			msAccount.setAccountStatus(Constants.CONSTANT_INT_ZERO);
			msAccount.setCreateUser("账户服务");
			msAccount.setUpdateUser("账户服务");
			msAccount.setRemark("账户服务-账户余额调增调减-生成不存在的账户");
			
			//根据账户类型编号查询账户类型信息
			Map<String,Object> mapCondition=new HashMap<>();
			mapCondition.put("accountTypeNo", accountTypeNo);
			MSAccountType msAccountType=accountTypeService.getAccountTypeByCondition(mapCondition);
			if(msAccountType==null){
				logger.warn("不存在账户类型：{}的信息",accountTypeNo);
				throw new ServiceException(ConstApiStatus.ACCOUNT_TYPE_NOT_EXIST);
			}
			
			//继续为账户生成账户类型信息
			msAccount.setAllowWithdraw(msAccountType.getAllowWithdraw());
			msAccount.setWithdrawPoundageScale(msAccountType.getWithdrawPoundageScale());
			msAccount.setWithdrawPoundageMin(msAccountType.getRefundPoundageMin());
			msAccount.setWithdrawPoundageMax(msAccountType.getRefundPoundageMax());
			msAccount.setWithdrawPriority(msAccountType.getWithdrawPriority());
			msAccount.setAllowRefund(msAccountType.getAllowRefund());
			msAccount.setRefundPoundageScale(msAccountType.getRefundPoundageScale());
			msAccount.setRefundPoundageMin(msAccountType.getRefundPoundageMin());
			msAccount.setRefundPoundageMax(msAccountType.getRefundPoundageMax());
			msAccount.setSpendPriority(msAccountType.getSpendPriority());
			//开始生成账户，若失败，则直接返回
			if(!accountService.insertAccountByType(msAccount)){
				logger.warn("账户创建失败");
				throw new ServiceException(ConstApiStatus.CREATE_ACCOUNT_FAILED);
			}
		}
		
		//变化金额，若调减则为负数
		Double changeBalance=null;
		//调增调减转化为收入支出 1：收入；-1：支出
		int inOrOut=0;
		
		//如果调增
		if(ConstSysParamsDefination.CAPITAL_IN.equals(model.getDirection())){	
			changeBalance=model.getTrade_amount();
			inOrOut=1;
		}
		//如果调减
		if(ConstSysParamsDefination.CAPITAL_OUT.equals(model.getDirection())){
			//判断当前类型的账户是否够调减
			if(Double.valueOf(DESC.deyption(msAccount.getBalanceEncrypt(),model.getMemId()))<model.getTrade_amount()){
				logger.warn("当前账户余额不足，调减失败");
				throw new ServiceException(ConstApiStatus.BALANCE_CANNOT_AFFORD);
			}
			changeBalance=-model.getTrade_amount();
			inOrOut=-1;
		}
		
		//设置新的总金额，更新账户表
		msAccount.setBalance(msAccount.getBalance()+changeBalance);
		msAccount.setBalanceEncrypt(DESC.encryption(String.valueOf(msAccount.getBalance()),model.getMemId()));
		baseDao.update(msAccount,"MSAccountMapper.updateAccountByCondition");
		
		//更新账户报表
		Map<String,Object> mapCondition=new HashMap<>();
		mapCondition.put(accountTypeNo,changeBalance);
		mapCondition.put("balance",changeBalance);
		mapCondition.put("memId",model.getMemId());
		baseDao.update(mapCondition,"MSAccountReportMapper.updateBalance");
		
		//写入流水明细
		MSAccountDetail msAccountDetail=new MSAccountDetail();
		msAccountDetail.setId(UUID.randomUUID().toString());
		msAccountDetail.setAccountNo(msAccount.getAccountNo());
		msAccountDetail.setTradeType(model.getTradeType());
		msAccountDetail.setTradeAmount(model.getTrade_amount());
		msAccountDetail.setTradeDate(DateUtil.timestampToDate(Long.valueOf(model.getTradeTime())));
		msAccountDetail.setInOrOut(inOrOut);
		msAccountDetail.setBalance(accountReportService.getTotalAndFreezeBalanceByMemId(model.getMemId()).getBalance());
		msAccountDetail.setBusinessNo(model.getOrderId());
		msAccountDetail.setCreateUser("账户服务");
		msAccountDetail.setUpdateUser("账户服务");
		msAccountDetail.setRemark(StringUtils.isEmpty(model.getRemark())?"账户服务-余额调增调减-生成明细":model.getRemark());
		accountDetailService.insertAccountDetail(msAccountDetail);
		
		return resBodyData;
	}
	
	@Override
	public boolean addMDConsumePoints(String memId, String consumePoints, boolean isLock) throws MdSysException {
		boolean returnBool = false;
		//增加基本账户总额
		double addAtq = DoubleCalculate.add(accountReportService.getTotalPointsByMemId(memId),
				Double.valueOf(consumePoints));
		//修改会员基本账户总额
		if(updateAccountPoint(memId,addAtq)){
			returnBool = true;
		}
		return returnBool;
	}

	@Override
	public boolean cutMDConsumePoints(String memId, String consumePoints, boolean isLock) throws MdSysException {
		boolean returnBool = false;
		//扣除基本账户总额
		double cutAtq = DoubleCalculate.sub(accountReportService.getTotalPointsByMemId(memId),
				Double.valueOf(consumePoints));
		//修改会员基本账户总额
		if(updateAccountPoint(memId,cutAtq)){
			returnBool = true;
		}
		return returnBool;
	}
	
	@Override
	public Double addConsumeMoney(String memId, String tradeAmount) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = accountService.getAccountMoney(memId);
		if(account != null){
			//增加会员账户余额
			Double balance = DoubleCalculate.add(Double.valueOf(account.getBalance()),
					Double.valueOf(tradeAmount));
			//判断如果计算后账户余额小于0返回-1不成功
			if(balance < 0){
				return returnBalance;
			}
			//修改会员账户余额
			boolean flag = updateAccountBalance(account.getId(), balance);
			if(flag){
				returnBalance = balance;
			}
		}else{
			//无账户信息，新增会员账户
		/*	String id = accountService.insertAccount(memId, ConstSysParamsDefination.ACCOUNT_TYPE_MONEY, 
					tradeAmount, "0");
			if(!StringUtil.isEmptyByString(id)){
				returnBalance = Double.valueOf(tradeAmount);
			}*/
		}
		return returnBalance;
	}

	@Override
	public Double cutConsumeMoney(String memId, String tradeAmount) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = accountService.getAccountMoney(memId);
		if(account != null){
			//判断余额是否能够扣减
			Double useBalance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),
					Double.valueOf(account.getFreezeBalance()));
			if(useBalance >= Double.valueOf(tradeAmount)){
				//增加会员账户余额
				Double balance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),
						Double.valueOf(tradeAmount));
				//判断如果计算后账户余额小于0返回-1不成功
				if(balance < 0){
					return returnBalance;
				}
				//修改会员账户余额
				boolean flag = updateAccountBalance(account.getId(), balance);
				if(flag){
					returnBalance = balance;
				}
			}
		}
		return returnBalance;
	}
	
	@Override
	public boolean cutMDConsumePointsAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark) throws MdSysException {
		//计算扣除后基本账户总额
		double cutAtq = DoubleCalculate.sub(accountReportService.getTotalPointsByMemId(memId),
				Double.valueOf(consumePoints));
		//调用扣除方法
		boolean flag = cutMDConsumePoints(memId, consumePoints, false);
		if(flag){
			//写入积分明细
			accountDetailService.saveConsumePoints(memId,
					orderId, orderSource, "0", consumePoints,String.valueOf(cutAtq),
					operatorType, operator, remark);
		}
		return flag;
	}
	
	@Override
	public Double addConsumeFreezeMoney(String memId, String tradeAmount) {
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = accountService.getAccountMoney(memId);
		if(account != null){
			//判断余额是否能够扣减冻结
			Double useBalance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),
					Double.valueOf(account.getFreezeBalance()));
			if(useBalance >= Double.valueOf(tradeAmount)){
				//增加会员冻结账户余额
				Double freezeBalance = DoubleCalculate.add(Double.valueOf(account.getFreezeBalance()),
						Double.valueOf(tradeAmount));
				//判断如果计算后冻结账户余额小于0返回-1不成功
				if(freezeBalance < 0){
					return returnBalance;
				}
				//修改会员冻结账户余额
				boolean flag = updateAccountFreezeBalance(account.getId(), freezeBalance);
				if(flag){
					returnBalance = freezeBalance;
				}
			}
		}
		return returnBalance;
	}

	@Override
	public Double cutConsumeFreezeMoney(String memId, String tradeAmount) throws MdBizException{
		Double returnBalance = Double.valueOf("-1");
		MSAccount account = accountService.getAccountMoney(memId);
		if(account != null){
			//减少会员冻结账户余额
			Double freezeBalance = DoubleCalculate.sub(Double.valueOf(account.getFreezeBalance()),
					Double.valueOf(tradeAmount));
			//判断如果扣减后冻结账户余额小于0返回-1不成功
			if(freezeBalance < 0){
				return returnBalance;
			}
			//修改会员冻结账户余额
			boolean flag = updateAccountFreezeBalance(account.getId(), freezeBalance);
			if(flag){
				returnBalance = freezeBalance;
			}
		}
		return returnBalance;
	}
	
	@Override
	public boolean addFreezeMoneyAndCutMoney(String memId,
			String tradeAmount, String freezeTradeAmount) {
		boolean returnBool = false;
		MSAccount account = accountService.getAccountMoney(memId);
		if(account != null){
			//增加会员账户余额
			Double balance = DoubleCalculate.add(Double.valueOf(account.getBalance()),
					Double.valueOf(tradeAmount));
			//减少会员冻结账户余额
			Double freezeBalance = DoubleCalculate.sub(Double.valueOf(account.getFreezeBalance()),
					Double.valueOf(freezeTradeAmount));
			//判断如果计算后账户余额小于0或如果扣减后冻结账户余额小于0返回-1不成功
			if(freezeBalance < 0 || balance < 0){
				return false;
			}
			//修改会员账户余额与冻结余额
			boolean flag = updateAccount(account.getId(), balance, freezeBalance);
			if(flag){
				returnBool = true;
			}
		}
		return returnBool;
	}

	@Override
	public boolean cutFreezeMoneyAndCutMoney(String memId,
			String tradeAmount, String freezeTradeAmount) {
		boolean returnBool = false;
		MSAccount account = accountService.getAccountMoney(memId);
		if(account != null){
			//减少会员冻结账户余额
			Double freezeBalance = DoubleCalculate.sub(Double.valueOf(account.getFreezeBalance()),
					Double.valueOf(freezeTradeAmount));
			//扣减会员账户余额
			Double balance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),
					Double.valueOf(tradeAmount));
			//判断如果计算后账户余额小于0或如果扣减后冻结账户余额小于0返回-1不成功
			if(freezeBalance < 0 || balance < 0){
				return false;
			}
			//修改会员账户余额与冻结余额
			boolean flag = updateAccount(account.getId(), balance, freezeBalance);
			if(flag){
				returnBool = true;
			}
		}
		return returnBool;
	}
	
	@Override
	public boolean addConsumeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) {
		boolean returnBool = false;
		Double balance = this.addConsumeMoney(memId,tradeAmount);
		if(balance >= 0){
			MSAccount account = accountService.getAccountMoney(memId);
			//增加明细
			accountDetailService.saveAddAccountDetail(memId, orderId,
					account.getId(),"", tradeType, tradeAmount,
					tradeDate, String.valueOf(balance), remark);
			
			returnBool = true;
		}else{
			throw new RuntimeException("余额变动失败");
		}
		return returnBool;
	}

	@Override
	public boolean cutConsumeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) throws MdBizException{
		boolean returnBool = false;
		Double balance = this.cutConsumeMoney(memId,tradeAmount);
		if(balance >= 0){
			MSAccount account = accountService.getAccountMoney(memId);
			//增加明细
			accountDetailService.saveCutAccountDetail(memId, orderId,
					account.getId(),"", tradeType, tradeAmount,
					tradeDate, String.valueOf(balance), remark,ConstSysParamsDefination.SYSTEM_USER,ConstSysParamsDefination.IS_Y);
			returnBool = true;
		}else{
			throw new MdBizException(ConstApiStatus.FROZEN_BALANCE_FAILED_ERROR);
		}
		return returnBool;
	}

	@Override
	public boolean addConsumeFreezeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) {
		boolean returnBool = false;
		Double balance = this.addConsumeFreezeMoney(memId,tradeAmount);
		if(balance >= 0){
			MSAccount account = accountService.getAccountMoney(memId);
			//增加明细
			accountFreezeDetailService.insertAccoutFreezeDetail(null);
			returnBool = true;
		}else{
			throw new RuntimeException("冻结余额变动失败");
		}
		return returnBool;
	}

	@Override
	public boolean cutConsumeFreezeMoneyAndDetail(String memId, String orderId,
			String tradeType, Date tradeDate, String tradeAmount, String remark) throws MdBizException{
		boolean returnBool = false;
		Double balance = this.cutConsumeFreezeMoney(memId,tradeAmount);
		if(balance >= 0){
			MSAccount account = accountService.getAccountMoney(memId);
			//增加明细
			accountFreezeDetailService.insertAccoutFreezeDetail(null);
			returnBool = true;
		}else{
			throw new MdBizException(ConstApiStatus.FROZEN_BALANCE_FAILED_ERROR);
		}
		return returnBool;
	}
	
	/**
	 * 修改会员积分数值
	 * @param memId
	 * @param accountPoint
	 * @return
	 * @throws MdSysException
	 */
	private boolean updateAccountPoint(String memId, Double accountPoint) throws MdSysException {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		paramsMap.put("accountPoint", DESC.encryption(String.valueOf(accountPoint), memId));
		try {
			Integer updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateAccountPoint");
			if(updateFlag <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("修改会员积分出现错误，会员编号：%s，错误信息：%s", memId, e.getMessage());
			return false;
		}
	}
	
	/**
	 * 修改账户余额
	 * @param id
	 * @param balance
	 * @return
	 */
	private boolean updateAccountBalance(String id, Double balance){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ConstSysParamsDefination.ACCOUNT_TYPE_MONEY);
		paramsMap.put("balance", String.valueOf(balance));
		try {
			Integer updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateAccountBalance");
			if(updateFlag <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("修改会员账户余额出现错误，会员账户ID：%s，错误信息：%s", id, e.getMessage());
			return false;
		}
	}

	/**
	 * 修改账户余额与冻结余额
	 * @param id
	 * @param balance
	 * @param freezeBalance
	 * @return
	 */
	private boolean updateAccount(String id, Double balance, Double freezeBalance){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ConstSysParamsDefination.ACCOUNT_TYPE_MONEY);
		paramsMap.put("balance", String.valueOf(balance));
		paramsMap.put("freezeBalance", String.valueOf(freezeBalance));
		try {
			Integer updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateAccount");
			if(updateFlag <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("修改会员账户余额与冻结余额出现错误，会员账户ID：%s，错误信息：%s", id, e.getMessage());
			return false;
		}
	}
	
	/**
	 * 修改账户冻结余额
	 * @param id
	 * @param freezeBalance
	 * @return
	 */
	private boolean updateAccountFreezeBalance(String id, Double freezeBalance){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ConstSysParamsDefination.ACCOUNT_TYPE_MONEY);
		paramsMap.put("freezeBalance", String.valueOf(freezeBalance));
		try {
			Integer updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateFreezeBalanceByMemIdAndType");
			if(updateFlag <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("修改会员账户冻结余额出现错误，会员账户ID：%s，错误信息：%s", id, e.getMessage());
			return false;
		}
	}

	/**
	 * 会员退单更新余额
	 * @param msAccount 账户表
	 * @author wujun
	 */
	@Override
	public void batchUpdateBalance(List<MSAccount> msAccount) {
		
		baseDao.update(msAccount, "MSAccountMapper.batchUpdateBalance");
		
	}


}
