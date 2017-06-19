package com.meiduimall.service.account.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.account.constant.AccountReviseStatusEnum;
import com.meiduimall.service.account.constant.AccountReviseTypeEnum;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.ApplicationConstant;
import com.meiduimall.service.account.constant.Constant;
import com.meiduimall.service.account.constant.MSBankWithDrawDepositStatusEnum;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.AccountReviseDetail;
import com.meiduimall.service.account.model.AddOrUpdateAccountReviseDetail;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.model.MSAccountDetail;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.MSAccountList;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.model.MSBankWithDrawOperateDetail;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.MSDict;
import com.meiduimall.service.account.model.request.RequestAccountReviseDetail;
import com.meiduimall.service.account.model.request.RequestMSAccountList;
import com.meiduimall.service.account.model.request.RequestMSBankWithDrawDepostie;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.AccountServices;
import com.meiduimall.service.account.service.BankAccountService;
import com.meiduimall.service.account.service.MSAccountDetailService;
import com.meiduimall.service.account.util.DateUtil;
import com.meiduimall.service.account.util.DoubleCalculate;
import com.meiduimall.service.account.util.GenerateNumber;
@Transactional
@Component
public class MSAccountDetailServiceImpl implements MSAccountDetailService {

	private final static Logger logger=LoggerFactory.getLogger(MSAccountDetailServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;
	@Autowired
	AccountServices accountServices;
	@Autowired
	private AccountFreezeDetailService  accountFreezeDetailService;
	@Autowired
	private BankAccountService bankAccountService;
	
	@Override
	public List<MSAccountDetail> listMSAccountDetail(MSAccountDetailGet mSAccountDetail) throws Exception {
		if(null != mSAccountDetail.getTradeType() && !"".equals(mSAccountDetail.getTradeType())){
			String[] split = mSAccountDetail.getTradeType().split(",");
			List<Object> arrayList = new ArrayList<Object>();
			for (String s : split) {
				arrayList.add(s);
			}
			mSAccountDetail.setTradeTypeList(arrayList);
		}
		List<MSAccountDetail> selectList = baseDao.selectList(mSAccountDetail, "MsAccountDetailMapper.listMSAccountDetail");
		return selectList;
	}
	
	@Override
	public List<MSAccountDetail> listMSAccountCondition(MSAccountDetailCondition mSAccountDetailCondition) {
		List<MSAccountDetail> selectList = null;
		try {
			selectList = baseDao.selectList(mSAccountDetailCondition, "listMSAccountCondition");
		} catch (Exception e) {
			logger.error("查询余额流水出现错误，错误信息：{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return selectList;
	}

	@Override
	public List<MSDict> listMSDict(String dicparentid) throws Exception {
		List<MSDict>  selectList= baseDao.selectList(dicparentid, "MSDictMapper.queryMsDictByParentId");
		return selectList;
	}

	@Override
	public List<MSAccountList> listMSAccount(RequestMSAccountList msAccountListRequest) throws MdBizException {
		List<MSAccountList> selectList=null;
		try {
			selectList=baseDao.selectList(msAccountListRequest, "MSAccountMapper.queryListMSAccount");
			if(!CollectionUtils.isEmpty(selectList)) return selectList;
		}catch(Exception e){
			logger.error("查询会员列表出现错误,错误信息:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.QUERY_MEMBER_LIST_ERROR);
		}
		return selectList;
	}

	@Override
	public void addMSAccountReviseDetail(AddOrUpdateAccountReviseDetail dto) throws MdBizException {
		String reviseId = UUID.randomUUID().toString();
		dto.setId(reviseId);
		try {
			 baseDao.insert(dto, "AccountReviseDetailMapper.insertAccountReviseDetail");
		} catch (Exception e) {
			logger.error("添加调整余额addMSAccountReviseDetail错误:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.INSERT_MEMBER_REVISE_DETAIL_ERROR);
		}
	}

	@Override
	public Integer updateMSAccountReviseDetail(AddOrUpdateAccountReviseDetail dto) throws MdBizException {
		Integer result=0;
		try {
			result=baseDao.update(dto, "AccountReviseDetailMapper.updateAccountReviseDetail");
		} catch (Exception e) {
			logger.error("修改调整余额updateMSAccountReviseDetail异常:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.UPDATE_ACCOUNT_REVISE_BALANCE_ERROR);
		}
		return result;
	}

	@Override
	public AccountReviseDetail getMSAccountReviseDetail(String id) throws MdBizException {
		AccountReviseDetail detail=null;
		if(StringUtils.isBlank(id)) throw new MdBizException(ApiStatusConst.REQUIRED_PARAM_EMPTY);
		try {
			detail=baseDao.selectOne(id, "AccountReviseDetailMapper.getAccountReviseDetail");
			if(detail!=null) return detail;
		} catch (Exception e) {
			logger.error("根据Id:{},查询会员余额明细getMSAccountReviseDetail异常:{}", id,e.getMessage());
			throw new MdBizException(ApiStatusConst.ACCOUNT_REVISE_IS_NULL_ERROR);
		}
		return detail;
	}

	@Override
	public List<AccountReviseDetail> queryMSAccountReviseDetailList(RequestAccountReviseDetail dto) throws MdBizException {
		List<AccountReviseDetail> list=null;
		try {
			list=baseDao.selectList(dto, "AccountReviseDetailMapper.queryAccountReviseDetailList");
			if(!CollectionUtils.isEmpty(list)) return list;
		} catch (Exception e) {
			logger.error("查询会员余额明细列表queryMSAccountReviseDetailList异常:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.ACCOUNT_REVISE_IS_NULL_ERROR);
		}
		return list;
	}

	@Override
	public ResBodyData examineMSAccountReviseDetail(AddOrUpdateAccountReviseDetail dto) throws MdBizException {
		//step1 查询余额调整记录
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
		AccountReviseDetail detail=this.getMSAccountReviseDetail(dto.getId());
		//审核同意操作
		if(dto.getOperate().equals(Constant.OPERATE_AGREE)){
			//step2 修改余额调整记录状态为"已审核"
			dto.setStatus(AccountReviseStatusEnum.AUDITED_REVIEW.getCode());
			this.updateMSAccountReviseDetail(dto);
			//step3  查询用户余额 修改余额
			resBodyData=dealWithAccountMoney(detail);
		}else{
			//驳回操作  修改状态为"已拒绝"
			dto.setStatus(AccountReviseStatusEnum.REJECTED_REVIEW.getCode());
			this.updateMSAccountReviseDetail(dto);
		}
		return resBodyData;
	}
	
	/**
	 * 修改用户余额
	 * @param detail
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年4月26日 下午5:40:56
	 */
	private ResBodyData dealWithAccountMoney(AccountReviseDetail detail) throws MdBizException{
		//step1 查询账号
		Double balance=Constant.ZERO;
		MSAccount account=this.queryAccountByMemId(detail.getMemId());
		//step2  判断调整类型   1-调增金额   2-调减金额  
		String type=Constant.ONE; //表示余额明细 支出类型  1表示收入  -1表示支出
		if(detail.getReviseType().equals(AccountReviseTypeEnum.CUT_DOWN.getName())){
			balance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),detail.getReviseBalance().doubleValue());
			if(balance<Constant.ZERO) throw new MdBizException(ApiStatusConst.ACCOUNT_REVISE_BALANCE_ERROR);
			type=Constant.NEGATIVE_NUMBER_ONE;
		}else{
			balance = DoubleCalculate.add(Double.valueOf(account.getBalance()),detail.getReviseBalance().doubleValue());
		}
		//step3 修改会员账户余额
		this.updateAccountBalance(account.getId(), balance);
		//step4 记录调整金额流水记录
		this.saveAccountDetail(detail,account,type,balance);
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	
	/**
	 * @Description: 修改会员余额
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午4:55:03
	 */
	private Integer updateAccountBalance(String id, Double balance) throws MdBizException{
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ApplicationConstant.ACCOUNT_TYPE_MONEY);
		paramsMap.put("balance", String.valueOf(balance));
		Integer updateFlag=0;
		try {
			updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateAccountBalance");
		} catch (Exception e) {
			logger.error("修改会员账户余额updateAccountBalance接口,ID:{},balance:{},异常:{}", id,balance,e.getMessage());
			throw new MdBizException(ApiStatusConst.UPDATE_ACCOUNT_BALANCE_ERROR);
		}
		return updateFlag;
	}
	
	/**
	 * @Description: 添加余额流水明细
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午4:49:13
	 */
	private void saveAccountDetail(AccountReviseDetail detail,MSAccount account,String type,Double balance) throws MdBizException{
		//业务流水号  CWTZ+年月日时+6位随机数
		StringBuffer businesNo=new StringBuffer(Constant.TRADETYPE);
		businesNo.append(DateUtil.format(new Date(), DateUtil.YYYYMMDDHH));
		businesNo.append(100000+new Random().nextInt(900000));
		
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", detail.getMemId());
		paramsMap.put("orderId", businesNo.toString());
		paramsMap.put("accountId", account.getId());
		paramsMap.put("accountType", account.getType());
		paramsMap.put("tradeType", Constant.TRADETYPE);
		paramsMap.put("tradeAmount", detail.getReviseBalance().toString());
		paramsMap.put("balance", balance.toString());
		paramsMap.put("remark", detail.getReviseRemark());
		paramsMap.put("inOrOut", type);
		paramsMap.put("tradeDate", DateUtil.format(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS));
		try {
			baseDao.insert(paramsMap, "MSAccountDetailMapper.insertAccountDetail");
		} catch (Exception e) {
			logger.error("写入账户变动明细saveAccountDetail异常:{},会员编号:{},订单编号:{}",e.getMessage(),detail.getMemId(), detail.getId());
			throw new MdBizException(ApiStatusConst.INSERT_ACCOUNT_DETAIL_ERROR);
		}
	}

	@Override
	public Integer updateWithDraw(RequestMSBankWithDrawDepostie deposit) throws MdBizException {
		Integer result=0;
		try {
			//step1 修改提现记录
			result=baseDao.update(deposit, "MSBankWithdrawDepositMapper.updateWidthDrawDeposit");
			//stpe2 新增修改操作记录
			MSBankWithDrawOperateDetail detail=new MSBankWithDrawOperateDetail();
			detail.setId(UUID.randomUUID().toString()); 
			detail.setCreateBy(deposit.getAuditBy());//操作人
			detail.setCreateDate(new Date());//操作时间
			detail.setDepositId(deposit.getId());
			detail.setName(deposit.getAuditBy());
			detail.setOperate(deposit.getOperate());  //操作说明
			detail.setRemark(deposit.getRemark());//备注说明
			baseDao.insert(detail, "MSBankWithDrawOperateDetail.insertOperateDetail");
		} catch (Exception e) {
			logger.error("修改提现记录updateWithDraw异常:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.UPDATE_BANK_WITHDRAW_DEPOSIT_ERROR);
		}
		return result;
	}

	/**
	 * @param deposit
	 * @throws MdBizException
	 */
	@Override
	public void rejectWithDraw(RequestMSBankWithDrawDepostie deposit) throws MdBizException {
		//step1 判断是否客服审核
		if(Constant.CUSTOMER_OPERATE.equals(deposit.getOperate())){
			deposit.setOperate(Constant.CUSTOMER_OPERATE_DESCRIPTION);
			deposit.setStatus(MSBankWithDrawDepositStatusEnum.WR.getCode());
		   //step2 查询提现记录
			MSBankWithdrawDeposit withdrawDeposit=this.queryMSBankWithdrawDepositById(deposit.getId());
			Date date=new Date();
			//step3 修改会员金额变动
			accountServices.cutConsumeFreezeMoneyAndDetail(withdrawDeposit.getMemId(),withdrawDeposit.getBusinessNo(),
					ApplicationConstant.MONEY_TRADE_TYPE_YETX, date,
					withdrawDeposit.getActualCarryCash(), Constant.ACCOUNT_BALANCE_DETAIL_REMARK);
			
			accountServices.cutConsumeFreezeMoneyAndDetail(withdrawDeposit.getMemId(),withdrawDeposit.getBusinessNo(),
					ApplicationConstant.MONEY_TRADE_TYPE_TXSX, date,
					withdrawDeposit.getCounterFee(), Constant.ACCOUNT_FEE_DETAIL_REMARK);
		}else{
			//财务审核驳回   修改状态为"待客服审核"
			deposit.setOperate(Constant.FINANCE_OPERATE_DESCRIPTION);
			deposit.setStatus(MSBankWithDrawDepositStatusEnum.WCR.getCode());
		}
		//step4 修改提现操作
		this.updateWithDraw(deposit);
	}
	@Override
	public MSBankWithdrawDeposit queryMSBankWithdrawDepositDetail(String id) throws MdBizException {
		//step1 查询提现记录
		MSBankWithdrawDeposit deposit=queryMSBankWithdrawDepositById(id);
		try {
			//step2 查询提现操作记录
			List<MSBankWithDrawOperateDetail> listDetail=baseDao.selectList(deposit.getId(), "MSBankWithDrawOperateDetail.queryOperateDetailList");
			if(!CollectionUtils.isEmpty(listDetail)) deposit.setListDetail(listDetail);
		} catch (Exception e) {
			logger.error("查看提现记录queryMSBankWithdrawDepositDetail异常:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.QUERY_BANK_WITHDRAW__DETAIL_BY_ID_ERROR);
		}
		return deposit;
	}

	@Override
	public void settlementWithDraw(RequestMSBankWithDrawDepostie deposit) throws MdBizException {
		//step1查询提现记录
		MSBankWithdrawDeposit withdrawDeposit=this.queryMSBankWithdrawDepositById(deposit.getId());
		Date date=new Date();
		try {
			//step2调用 提现处理冻结金额
			accountServices.cutConsumeFreezeMoneyAndDetail(withdrawDeposit.getMemId(),withdrawDeposit.getBusinessNo(),
					ApplicationConstant.MONEY_TRADE_TYPE_YETX, date,withdrawDeposit.getActualCarryCash(), Constant.ACCOUNT_BALANCE_DETAIL_REMARK);
			
			accountServices.cutConsumeFreezeMoneyAndDetail(withdrawDeposit.getMemId(),withdrawDeposit.getBusinessNo(),
					ApplicationConstant.MONEY_TRADE_TYPE_TXSX, date,withdrawDeposit.getCounterFee(), Constant.ACCOUNT_FEE_DETAIL_REMARK);
			
			//step2调用 提现处理可用金额
			accountServices.cutConsumeMoneyAndDetail(withdrawDeposit.getMemId(),withdrawDeposit.getBusinessNo(),
					ApplicationConstant.MONEY_TRADE_TYPE_YETX, date,withdrawDeposit.getActualCarryCash(), Constant.ACCOUNT_BALANCE_DETAIL_REMARK);
			
			accountServices.cutConsumeMoneyAndDetail(withdrawDeposit.getMemId(),withdrawDeposit.getBusinessNo(),
					ApplicationConstant.MONEY_TRADE_TYPE_TXSX, date,withdrawDeposit.getCounterFee(), Constant.ACCOUNT_FEE_DETAIL_REMARK);
		} catch (Exception e) {
			logger.error("结算操作settlementWithDraw 处理用户账号余额异常:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.DEALWLTH_ACCOUNT_MONEY_ERROR);
		}
		//step3 修改提现状态
		this.updateWithDraw(deposit);
	}
	
	/**
	 * 查看提现记录  根据ID
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年4月27日 下午12:11:16
	 */
	private MSBankWithdrawDeposit queryMSBankWithdrawDepositById(String id)throws MdBizException{
		MSBankWithdrawDeposit withdrawDeposit=null;
		if(StringUtils.isBlank(id)) throw new MdBizException(ApiStatusConst.REQUIRED_PARAM_EMPTY);
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", id);
			withdrawDeposit=baseDao.selectOne(map, "MSBankWithdrawDepositMapper.queryWidthDrawById");
		} catch (Exception e) {
			logger.error("根据ID:{},查看提现记录queryMSBankWithdrawDepositById错误:{}",id,e.getMessage());
			throw new MdBizException(ApiStatusConst.QUERY_BANK_WITHDRAW_BY_ID_ERROR);
		}
		return withdrawDeposit;
	}
	
	/**
	 * 根据memId 查询账号信息
	 * @param memId
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年4月26日 下午5:38:45
	 */
	private MSAccount queryAccountByMemId(String memId) throws MdBizException{
		MSAccount account=null;
		try {
			Map<String,String> paramsMap = new HashMap<String,String>();
			paramsMap.put("memId", memId);
			paramsMap.put("accountType", ApplicationConstant.ACCOUNT_TYPE_MONEY);
			account=baseDao.selectOne(paramsMap, "MSAccountMapper.getAccountByMemId");
			if(account==null) throw new MdBizException(ApiStatusConst.ACCOUNT_IS_NULL_ERROR);
		} catch (Exception e) {
			logger.error("根据memID:{},查询账号queryAccountByMemId错误:{}",memId,e);
			throw new MdBizException(ApiStatusConst.ACCOUNT_IS_NULL_ERROR);
		}
		return account;
	}

	/**
	 * @param deposit
	 * @throws MdBizException
	 */
	@Override
	public void saveBankWithdrawDeposit(RequestMSBankWithDrawDepostie deposit) throws MdBizException {
		//step1 检查账号信息
		MSAccount account=this.checkAccountMeg(deposit.getMemId(),deposit.getAccountNo(),deposit.getApplyCarryCash());
		//step2 提现
		this.applyBankWithdrawDeposit(deposit,account);
	}
	
	/**
	 * 检查账号提现信息
	 * @param memId
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年4月28日 上午11:26:48
	 */
	private MSAccount checkAccountMeg(String memId,String accountNo,String applyCarryCash)throws MdBizException{
		//step1 查询账号信息
		MSAccount account =this.queryAccountByMemId(memId);
		//step2 查看银行卡信息
		MSBankAccount bankAccount=bankAccountService.getBankAccount(memId, accountNo);
		if(bankAccount==null){
			logger.error("会员银行卡账户信息不存在memID:{},accountNo:{}",memId,accountNo);
			throw new MdBizException(ApiStatusConst.ACCOUNT_BANK_CARD_IS_NULL);
		}
		//step3 检查申请余额，并计算  
		final Double old_useMoney = accountServices.getUseConsumeMoney(memId);
		final Double old_applyCarryCash = Double.valueOf(applyCarryCash);
		//申请提现余额超过最大可提现金额50000
		if(old_applyCarryCash > Constant.FIFTY_THOUSAND){
			logger.error("超过最大可提现限制50000,memID:{},提现金额applyCarryCash:{}",memId,old_applyCarryCash);
			throw new MdBizException(ApiStatusConst.ACCOUNT_APPLY_CARRY_CASH_ERROR);
		}
		//计算申请提现余额是否超最大余额
		if(old_applyCarryCash > old_useMoney){
			logger.error("余额不足无法支付,memID:{},提现金额applyCarryCash:{},账号余额:{}",memId,old_applyCarryCash,old_useMoney);
			throw new MdBizException(ApiStatusConst.ACCOUNT_INSUFFICIENT_BALANCE_ERROR);
		}
		//计算当前余额是否低于最低提现金额
		if(old_useMoney <= ApplicationConstant.MIN_APPLY_CARRY_CASH){
			logger.error("余额低于最低提现金额,无法支付,memID:{},账号余额:{}",memId,old_useMoney);
			throw new MdBizException(ApiStatusConst.ACCOUNT_BALANCE_BELOW_WITHDRAW_ERROR);
		}
		return account;
	}
	/**
	 * 申请银行提现操作
	 * @param deposit
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年4月28日 上午10:55:00
	 */
	private void applyBankWithdrawDeposit(RequestMSBankWithDrawDepostie deposit,MSAccount account)throws MdBizException{
		String memId = deposit.getMemId();
		// 计算扣除金额与手续费
		Map<String, String> returnMap = this.calcBankWithdrawDeposit(memId, deposit.getApplyCarryCash());
		// 计算后实际金额
		String calcActualCarryCash = returnMap.get("calc_actualCarryCash");
		// 计算后手续费
		String calcCounterFee = returnMap.get("calc_counterFee");
		// 数据检查 实际提现金额不能大于申请提现金额
		if (Double.parseDouble(calcActualCarryCash) > Double.parseDouble(deposit.getApplyCarryCash())) {
			logger.error("实际提现金额:{},不能大于申请提现金额:{}",calcActualCarryCash,deposit.getApplyCarryCash());
			throw new MdBizException(ApiStatusConst.WITHDRAW_ERROR);
		}
		// 数据检查 实际提现金额必须大于0
		if (Double.parseDouble(calcActualCarryCash) <= Constant.ZERO) {
			logger.error("实际提现金额:{},必须大于最低提现手续费2元",memId,calcActualCarryCash);
			throw new MdBizException(ApiStatusConst.ACTUAL_WITHDRAW_MONEY_THEN_ZERO_ERROR);
		}
		deposit.setActualCarryCash(calcActualCarryCash);
		deposit.setCounterFee(calcCounterFee);

		//插入提现记录返回业务单号
		String businessNo = this.addBankWithdrawDeposit(deposit);
			if(StringUtils.isNotBlank(businessNo)){
				String freezeBalance = account.getFreezeBalance(); //当前冻结余额
				Double carryCashFreezeBalance = DoubleCalculate.add(Double.valueOf(freezeBalance), Double.valueOf(calcActualCarryCash)); //提现余额+当前冻结余额=提现后冻结余额
				Double counterFeeBalance = DoubleCalculate.add(carryCashFreezeBalance, Double.valueOf(calcCounterFee)); //提现手续费+当前冻结余额=提现手续费后冻结余额
				Double addFreezeMoney = DoubleCalculate.add(Double.valueOf(calcActualCarryCash), Double.valueOf(calcCounterFee));  //提现余额+手续费
				//增加提现冻结余额
				Double freezeFlag = accountServices.addConsumeFreezeMoney(memId, String.valueOf(addFreezeMoney));
				if(freezeFlag >= Constant.ZERO){
				//增加明细
				accountFreezeDetailService.saveAccountFreezeDetail(memId, businessNo,account.getId(), account.getType(), ApplicationConstant.MONEY_TRADE_TYPE_YETX, calcActualCarryCash,deposit.getApplyDate(), String.valueOf(carryCashFreezeBalance),  Constant.ACCOUNT_BALANCE_DETAIL_REMARK);
				//增加明细
				accountFreezeDetailService.saveAccountFreezeDetail(memId, businessNo,account.getId(), account.getType(), ApplicationConstant.MONEY_TRADE_TYPE_TXSX, calcCounterFee,deposit.getApplyDate(), String.valueOf(counterFeeBalance),  Constant.ACCOUNT_FEE_DETAIL_REMARK);
			}
		}
	}
	
	/**
	 * 新增提现记录
	 * @param dto
	 * @return
	 * @author: jianhua.huang  2017年5月5日 上午11:07:41
	 */
	public String addBankWithdrawDeposit(RequestMSBankWithDrawDepostie dto) throws MdBizException{
		Date nowDate = new Date(System.currentTimeMillis());
		String businessNo=null;
		try{
			String id = UUID.randomUUID().toString();
		    businessNo = GenerateNumber.generateBusinessNo(ApplicationConstant.MONEY_TRADE_TYPE_YETX);
		    
			MSBankAccount bankAccount=bankAccountService.getBankAccount(dto.getMemId(), dto.getAccountNo());
			MSBankWithdrawDeposit entity = new MSBankWithdrawDeposit();
			entity.setId(id);
			entity.setMemId(dto.getMemId());
			entity.setBusinessNo(businessNo);
			entity.setBankAccountId(bankAccount.getId());
			entity.setAccountIdcard(bankAccount.getAccountIdcard());
			entity.setAccountNo(dto.getAccountNo());
			entity.setAccountName(bankAccount.getAccountName());
			entity.setAccountBank(bankAccount.getAccountBank());
			entity.setAccountProvince(bankAccount.getAccountProvince());
			entity.setAccountCity(bankAccount.getAccountCity());
			entity.setAccountArea(bankAccount.getAccountArea());
			entity.setAccountSubBank(bankAccount.getAccountSubBank());
			entity.setApplyDate(nowDate);
			entity.setApplyCarryCash(dto.getApplyCarryCash());
			entity.setCounterFee(dto.getCounterFee());
			entity.setActualCarryCash(dto.getActualCarryCash());
			entity.setRemark(dto.getRemark());
			entity.setCreateDate(nowDate);
			entity.setIsDelete(Constant.IS_N);
			baseDao.insert(entity, "MSBankWithdrawDepositMapper.insertBankWithdrawDeposit");
			return businessNo;
		}catch(Exception e){
			logger.error("新增提现记录addBankWithdrawDeposit异常:{}",e);
			throw new MdBizException(ApiStatusConst.INSERT_WITHDRAW_ERROR);
		}
	}
	/**
	 * 方法名: calcBankWithdrawDeposit<br>
	 * 描述: 计算可提现金额与手续费 <br>
	 * 创建时间: 2016-12-19
	 * @param dto
	 * @return
	 */
	private Map<String,String> calcBankWithdrawDeposit(String memId,String applyCarryCash) throws MdBizException{
		Map<String,String> returnMap = new HashMap<String, String>();
		final Double old_useMoney = accountServices.getUseConsumeMoney(memId);
		final Double old_applyCarryCash = Double.valueOf(applyCarryCash);
		final Double calc_feeScale = Constant.FEESCALE; //手续费比例
		final Double calc_minFee = ApplicationConstant.MIN_APPLY_CARRY_FEE;   //最小手续费
		Double calc_counterFee = Constant.ZERO;
		Double calc_actualCarryCash = Constant.ZERO;
		//step1计算手续费
		calc_counterFee = DoubleCalculate.mul(old_applyCarryCash, calc_feeScale);
		if(calc_counterFee < calc_minFee){
			calc_counterFee = calc_minFee;
		}
		//stpe2 全部提取，手续费从申请申请提取金额中扣除，实际提现金额=申请提取金额-手续费
		if(old_applyCarryCash == old_useMoney){
			calc_actualCarryCash = DoubleCalculate.sub(old_applyCarryCash, calc_counterFee);
		}else{
			//部分提取，手续费直接从提现金额中扣除，实际提现金额=申请提取金额 -手续费     余额不足扣除手续费时，实际提现金额=申请提取金额-扣除余额后不足的手续费
			Double subUseMoney = DoubleCalculate.sub(old_useMoney, old_applyCarryCash);
			//余额够扣除手续费时，实际提现金额=申请提取金额-手续费
			if(subUseMoney >= calc_counterFee){
				calc_actualCarryCash =DoubleCalculate.sub(old_applyCarryCash, calc_counterFee);
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
}
