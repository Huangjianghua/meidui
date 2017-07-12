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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.ConstAccountAdjustStatus;
import com.meiduimall.service.account.constant.ConstAccountAdjustType;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.constant.ConstTradeType;
import com.meiduimall.service.account.constant.ConstWithdrawStatus;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.AccountReviseDetail;
import com.meiduimall.service.account.model.AddOrUpdateAccountReviseDetail;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.model.MSAccountDetail;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.MSAccountFreezeDetail;
import com.meiduimall.service.account.model.MSAccountList;
import com.meiduimall.service.account.model.MSAccountReport;
import com.meiduimall.service.account.model.MSAccountType;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.model.MSBankWithDrawOperateDetail;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.MSDict;
import com.meiduimall.service.account.model.MSMembers;
import com.meiduimall.service.account.model.MSWithdrawInfoByAccountType;
import com.meiduimall.service.account.model.request.RequestAccountReviseDetail;
import com.meiduimall.service.account.model.request.RequestMSAccountList;
import com.meiduimall.service.account.model.request.RequestMSBankWithDrawDepostie;
import com.meiduimall.service.account.service.AccountDetailService;
import com.meiduimall.service.account.service.AccountFreezeDetailService;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.AccountService;
import com.meiduimall.service.account.service.AccountTypeService;
import com.meiduimall.service.account.service.BankAccountService;
import com.meiduimall.service.account.service.MSAccountDetailService;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DateUtil;
import com.meiduimall.service.account.util.DoubleCalculate;
import com.meiduimall.service.account.util.GenerateNumber;

@Transactional
@Service
public class MSAccountDetailServiceImpl implements MSAccountDetailService {

	private final static Logger logger=LoggerFactory.getLogger(MSAccountDetailServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	AccountService accountServices;
	
	@Autowired
	private AccountFreezeDetailService  accountFreezeDetailService;
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private AccountReportService accountReportService;
	
	@Autowired
	private AccountDetailService accountDetailService;
	
	@Autowired
	private AccountTypeService accountTypeService;
	
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

		List<MSAccountDetail> selectList = baseDao.selectList(mSAccountDetail, "MSAccountDetailMapper.listMSAccountDetail");

		return selectList;
	}
	
	@Override
	public List<MSAccountDetail> listMSAccountCondition(MSAccountDetailCondition mSAccountDetailCondition) {
		List<MSAccountDetail> selectList = null;
		try {
			selectList = baseDao.selectList(mSAccountDetailCondition, "MSAccountDetailMapper.listMSAccountCondition");
		} catch (Exception e) {
			logger.error("查询余额流水出现错误，错误信息：{}", e.getMessage());
			throw new MdBizException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return selectList;
	}

	@Override
	public List<MSDict> listMSDict(String dicparentid) throws Exception {
		List<MSDict>  selectList= baseDao.selectList(dicparentid, "MSDictMapper.queryMsDictByParentId");
		return selectList;
	}

	@Override
	public Page<MSAccountList> listMSAccount(RequestMSAccountList msAccountListRequest) throws MdBizException {
		List<MSAccountList> selectList=null;
		Page<MSAccountList> pageInfo=null;
		try {
			Integer count=baseDao.selectOne(msAccountListRequest, "MSAccountMapper.queryListMSAccountCount");
			//分页查询
			if(msAccountListRequest.getFlg().equals(Constants.CONSTANT_STR_ONE)){
				//分页
				PageHelper.startPage(msAccountListRequest.getPageNum(), msAccountListRequest.getPageSize(),false);
				PageHelper.orderBy("memRegTime DESC");
			}else{
				//不分页
				PageHelper.startPage(msAccountListRequest.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("memRegTime DESC");
			}
			selectList=baseDao.selectList(msAccountListRequest, "MSAccountMapper.queryListMSAccount");
			pageInfo=(Page<MSAccountList>) selectList;
			pageInfo.setTotal(count);
		}catch(Exception e){
			logger.error("查询会员列表出现错误,错误信息:{}", e.getMessage());
			throw new MdBizException(ConstApiStatus.QUERY_MEMBER_LIST_ERROR);
		}
		return pageInfo;
	}

	@Override
	public void addMSAccountReviseDetail(AddOrUpdateAccountReviseDetail dto) throws MdBizException {
		String reviseId = UUID.randomUUID().toString();
		dto.setId(reviseId);
		dto.setCreateDate(new Date());
		dto.setUpdateUser(null);
		try {
			 MSAccount accountInfo = accountServices.getAccountInfoByMemIdAndAccountTypeNo(dto.getMemId(), dto.getAccountTypeNo());
			 if(org.springframework.util.StringUtils.isEmpty(accountInfo)){
				 MSAccount msAccount = new MSAccount();
				 msAccount.setId(UUID.randomUUID().toString());
				 msAccount.setMemId(dto.getMemId());
				 Map<String, Object> map = new HashMap<>();
				 map.put("accountTypeNo",dto.getAccountTypeNo());
				 MSAccountType accountType = accountTypeService.getAccountTypeByCondition(map);
				 Long updateSequenceByAccountTypeNo = accountTypeService.updateSequenceByAccountTypeNo(dto.getAccountTypeNo());
				 msAccount.setAccountNo(dto.getAccountTypeNo()+updateSequenceByAccountTypeNo);
				 msAccount.setAccountTypeNo(accountType.getAccountTypeNo());
				 msAccount.setAccountNoSequence(accountType.getAccountNoSequence());
				 msAccount.setBalance(Double.valueOf(Constants.CONSTANT_INT_ZERO));
				 msAccount.setBalanceEncrypt(DESC.encryption(dto.getReviseBalance().toString(), dto.getMemId()));
				 msAccount.setFreezeBalance(0.00);
				 msAccount.setFreezeBalanceEncrypt(DESC.encryption(String.valueOf(0.00), dto.getMemId()));
				 msAccount.setAllowWithdraw(accountType.getAllowWithdraw());
				 msAccount.setWithdrawPoundageScale(accountType.getWithdrawPoundageScale());
				 msAccount.setWithdrawPoundageMin(accountType.getWithdrawPoundageMin());
				 msAccount.setWithdrawPoundageMax(accountType.getRefundPoundageMax());
				 msAccount.setAllowRefund(accountType.getAllowRefund());
				 msAccount.setRefundPoundageScale(accountType.getRefundPoundageScale());
				 msAccount.setRefundPoundageMin(accountType.getRefundPoundageMin());
				 msAccount.setRefundPoundageMax(accountType.getRefundPoundageMax());
				 msAccount.setWithdrawPriority(accountType.getWithdrawPriority());
				 msAccount.setSpendPriority(accountType.getSpendPriority());
				 msAccount.setAccountStatus(0); //账户状态,0 正常 1禁用
				 msAccount.setCreateDate(new Date());
				 msAccount.setCreateUser("账户服务");
				 msAccount.setUpdateDate(new Date());
				 msAccount.setUpdateUser("账户服务");
				 msAccount.setRemark(accountType.getAccountTypeName());
				 accountServices.insertAccountByType(msAccount);
				 dto.setAccountNo(msAccount.getAccountNo());
			 }else{
				 dto.setAccountNo(accountInfo.getAccountNo());
			 }
			 baseDao.insert(dto, "MSAccountReviseDetailMapper.insertAccountReviseDetail");
		} catch (Exception e) {
			logger.error("添加调整余额addMSAccountReviseDetail错误:{}", e.getMessage());
			throw new MdBizException(ConstApiStatus.INSERT_MEMBER_REVISE_DETAIL_ERROR);
		}
	}

	@Override
	public Integer updateMSAccountReviseDetail(AddOrUpdateAccountReviseDetail dto) throws MdBizException {
		Integer result=0;
		dto.setUpdateDate(new Date());
		try {
			result=baseDao.update(dto, "MSAccountReviseDetailMapper.updateAccountReviseDetail");
		} catch (Exception e) {
			logger.error("修改调整余额updateMSAccountReviseDetail异常:{}", e.getMessage());
			throw new MdBizException(ConstApiStatus.UPDATE_ACCOUNT_REVISE_BALANCE_ERROR);
		}
		return result;
	}

	@Override
	public AccountReviseDetail getMSAccountReviseDetail(String id) throws MdBizException {
		AccountReviseDetail detail=null;
		if(StringUtils.isBlank(id)) throw new MdBizException(ConstApiStatus.REQUIRED_PARAM_EMPTY);
		try {
			detail=baseDao.selectOne(id, "MSAccountReviseDetailMapper.getAccountReviseDetail");
			if(detail!=null) return detail;
		} catch (Exception e) {
			logger.error("根据Id:{},查询会员余额明细getMSAccountReviseDetail异常:{}", id,e.getMessage());
			throw new MdBizException(ConstApiStatus.ACCOUNT_REVISE_IS_NULL_ERROR);
		}
		return detail;
	}

	@Override
	public List<AccountReviseDetail> queryMSAccountReviseDetailList(RequestAccountReviseDetail dto) throws MdBizException {
		List<AccountReviseDetail> list=null;
		try {
			list=baseDao.selectList(dto, "MSAccountReviseDetailMapper.queryAccountReviseDetailList");
			if(!CollectionUtils.isEmpty(list)) return list;
		} catch (Exception e) {
			logger.error("查询会员余额明细列表queryMSAccountReviseDetailList异常:{}", e.getMessage());
			throw new MdBizException(ConstApiStatus.ACCOUNT_REVISE_IS_NULL_ERROR);
		}
		return list;
	}

	@Override
	public ResBodyData examineMSAccountReviseDetail(AddOrUpdateAccountReviseDetail dto) throws MdBizException {
		//step1 查询余额调整记录
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
		AccountReviseDetail detail=this.getMSAccountReviseDetail(dto.getId());
		//审核同意操作
		if(dto.getOperate().equals(ConstSysParamsDefination.OPERATE_AGREE)){
			//step2 修改余额调整记录状态为"已审核"
			dto.setStatus(ConstAccountAdjustStatus.ALREADY_CHECK.getCode());
			this.updateMSAccountReviseDetail(dto);
			//step3  查询用户余额 修改余额
			detail.setUpdateUser(dto.getUpdateUser());
			resBodyData=dealWithAccountMoney(detail);
		}else{
			//驳回操作  修改状态为"已拒绝"
			dto.setStatus(ConstAccountAdjustStatus.ALREADY_REJECT.getCode());
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
		Double balance=Double.valueOf(Constants.CONSTANT_INT_ZERO);
		List<MSAccount> accountList=this.queryAccountList(null,null,detail.getAccountNo());
		MSAccount account=accountList.get(0);
		//step2  判断调整类型   1-调增金额   2-调减金额  
		Integer type=Constants.CONSTANT_INT_ONE; //表示余额明细 支出类型  1表示收入  -1表示支出
		Double balanceReport=detail.getReviseBalance().doubleValue();
		if(detail.getReviseType().equals(ConstAccountAdjustType.CUTDOWN.getName())){
			balance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),detail.getReviseBalance().doubleValue());
			balanceReport=-balanceReport;
			//step3 修改ms_account_report 对应的金额
			updateAccountBalanceReport(account.getAccountTypeNo(),-detail.getReviseBalance().doubleValue(),account.getMemId(),ConstSysParamsDefination.BALANCE_UPDATE_OPERATE);
			if(balance<Constants.CONSTANT_INT_ZERO) throw new MdBizException(ApiStatusConst.ACCOUNT_REVISE_BALANCE_ERROR);
			type=Constants.CONSTANT_INT_INVALID;
		}else{
			balance = DoubleCalculate.add(Double.valueOf(account.getBalance()),detail.getReviseBalance().doubleValue());
			//step3 修改ms_account_report 对应的金额
			updateAccountBalanceReport(account.getAccountTypeNo(),detail.getReviseBalance().doubleValue(),account.getMemId(),ConstSysParamsDefination.BALANCE_UPDATE_OPERATE);
		}
		//step4 修改会员账户余额
		this.updateAccountBalance(null, balance,detail.getAccountNo(),account.getMemId());
		//step5 修改总的金额    
		Map<String, Object> mapParam=new HashMap<>();
		mapParam.put("memId", account.getMemId());
		mapParam.put("balance", balanceReport);
		baseDao.update(mapParam, "MSBankWithdrawDepositMapper.updateAccountReportBalanceByMemId");
		//step6 记录调整金额流水记录
		this.saveAccountDetail(detail,account,type,account.getMemId());
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	
	/**
	 * @Description: 修改会员余额
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午4:55:03
	 */
	private Integer updateAccountBalance(String id, Double balance,String accountNo,String memId) throws MdBizException{
		Map<String,String> paramsMap = new HashMap<String,String>();
		Integer updateFlag=0;
		try {
			paramsMap.put("id", id);
			paramsMap.put("accountNo", accountNo);
			paramsMap.put("balance", String.valueOf(balance));
			paramsMap.put("balanceEncrypt", DESC.encryption(String.valueOf(balance), memId));
			updateFlag = baseDao.update(paramsMap, "MSBankWithdrawDepositMapper.updateAccountBalance");
		} catch (Exception e) {
			logger.error("修改会员账户余额updateAccountBalance接口,ID:{},balance:{},异常:{}", id,balance,e.getMessage());
			throw new MdBizException(ConstApiStatus.UPDATE_ACCOUNT_BALANCE_ERROR);
		}
		return updateFlag;
	}
	
	/**
	 * @Description: 添加余额流水明细
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午4:49:13
	 */
	private void saveAccountDetail(AccountReviseDetail detail,MSAccount account,Integer type,String memId) throws MdBizException{
		//业务流水号  CWTZ+年月日时+6位随机数
		StringBuffer businesNo=new StringBuffer(ConstSysParamsDefination.TRADETYPE);
		businesNo.append(DateUtil.format(new Date(), DateUtil.YYYYMMDDHH));
		businesNo.append(100000+new Random().nextInt(900000));
		
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("accountNo", detail.getAccountNo());
		paramsMap.put("createUser", detail.getUpdateUser());
		paramsMap.put("updateUser", detail.getUpdateUser());
		paramsMap.put("businessNo", businesNo.toString());
		paramsMap.put("tradeType", ConstSysParamsDefination.TRADETYPE);
		paramsMap.put("tradeAmount", detail.getReviseBalance().doubleValue());
		
		Double useMoney=accountReportService.getAvailableBalance(memId);
		paramsMap.put("balance", useMoney);
		paramsMap.put("remark", "账户调整金额:"+detail.getReviseBalance().doubleValue());
		paramsMap.put("inOrOut", type);
		paramsMap.put("tradeDate", new Date());
		paramsMap.put("createDate", DateUtil.format(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS));
		paramsMap.put("updateDate", DateUtil.format(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS));
		paramsMap.put("markWater", ConstSysParamsDefination.IS_Y);
		try {
			baseDao.insert(paramsMap, "MSAccountDetailMapper.insertAccountDetail");
		} catch (Exception e) {
			logger.error("写入账户变动明细saveAccountDetail异常:{},会员编号:{},订单编号:{}",e.getMessage(),detail.getMemId(), detail.getId());
			throw new MdBizException(ConstApiStatus.INSERT_ACCOUNT_DETAIL_ERROR);
		}
	}

	@Override
	public Integer updateWithDraw(RequestMSBankWithDrawDepostie deposit) throws MdBizException {
		Integer result=0;
		try {
			//step1 修改提现记录
			result=baseDao.update(deposit, "MSBankWithdrawDepositMapper.updateWidthDrawDepositNew");
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
			throw new MdBizException(ConstApiStatus.UPDATE_BANK_WITHDRAW_DEPOSIT_ERROR);
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
		if(ConstSysParamsDefination.CUSTOMER_OPERATE.equals(deposit.getOperate())){
			deposit.setOperate(ConstSysParamsDefination.CUSTOMER_OPERATE_DESCRIPTION);
			deposit.setStatus(ConstWithdrawStatus.ALREADY_REJECT.getCode());
		   //step2 查询提现记录
			MSBankWithdrawDeposit withdrawDeposit=this.queryMSBankWithdrawDepositById(deposit.getId());
			//step3 修改会员金额变动
			cutConsumeFreezeMoneyAndDetail(withdrawDeposit);
		}else{
			//财务审核驳回   修改状态为"待客服审核"
			deposit.setOperate(ConstSysParamsDefination.FINANCE_OPERATE_DESCRIPTION);
			deposit.setStatus(ConstWithdrawStatus.WAIT_CS_CHECK.getCode());
		}
		//step4 修改提现操作
		this.updateWithDraw(deposit);
	}
	
	/**
	 * 处理驳回操作
	 * @param withdrawDeposit
	 * @author: jianhua.huang  2017年6月2日 下午3:57:40
	 */
	private void  cutConsumeFreezeMoneyAndDetail(MSBankWithdrawDeposit withdrawDeposit) throws MdBizException{
		//step1 查询ms_withdraw_info_by_account_type   根据提现主表id  获取提现时  不同账号的扣钱记录  依次返回
		Map<String, Object> map=new HashMap<>();
		map.put("id", withdrawDeposit.getId());
		List<MSWithdrawInfoByAccountType> list= baseDao.selectList(map, "MSBankWithdrawDepositMapper.queryWithdrawInfoByAccountTypeList");
		if(CollectionUtils.isEmpty(list)) throw new MdBizException(ConstApiStatus.QUERY_WITHDRAW_BY_ACCOUNT_TYPE_ERROR);
		//step2 遍历数据  更新相关账号的金额
		Date date=new Date();
		for(MSWithdrawInfoByAccountType accountType:list){
			//查询账号 
			List<MSAccount> accountList=queryAccountList(null,null,accountType.getAccount_no());
			MSAccount account=accountList.get(0);
			Double freeze=DoubleCalculate.sub(account.getFreezeBalance(), accountType.getWithdrawAmount());
			updateAccountFreezeBalance(null,freeze,accountType.getAccount_no(),account.getMemId());
			//step3记录解冻明细
			updateAccountBalanceReport(account.getAccountTypeNo(),-accountType.getWithdrawAmount(),account.getMemId(),ConstSysParamsDefination.FREE_ALANCE_UPDATE_OPERATE);
			//step4 记录解冻明细
			insertAccoutFreezeDetail(account.getAccountNo(),withdrawDeposit.getBusinessNo(),ConstSysParamsDefination.THAW,ConstSysParamsDefination.ACCOUNT_BALANCE_DETAIL_REMARK,accountType.getWithdrawAmount(),
					ConstTradeType.TRADE_TYPE_TXSX.getCode(),account.getFreezeBalance(),date);
		}
		//step5 修改总的冻结金额
		Map<String, Object> mapParam=new HashMap<>();
		mapParam.put("memId", withdrawDeposit.getMemId());
		mapParam.put("freezeBalance", -withdrawDeposit.getApplyWithdrawAmount());
		baseDao.update(mapParam, "MSBankWithdrawDepositMapper.updateAccountReportByMemId");
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
			throw new MdBizException(ConstApiStatus.QUERY_BANK_WITHDRAW__DETAIL_BY_ID_ERROR);
		}
		return deposit;
	}

	@Override
	public void settlementWithDraw(RequestMSBankWithDrawDepostie deposit) throws MdBizException {
		//step1查询提现记录
		MSBankWithdrawDeposit withdrawDeposit=this.queryMSBankWithdrawDepositById(deposit.getId());
		try {
			//step2调用 提现处理冻结金额
			cutConsumeFreezeMoneyAndDetail(withdrawDeposit);
			//step3调用 提现处理可用金额
			cutConsumeMoneyAndDetail(withdrawDeposit,deposit.getAuditBy());
		} catch (Exception e) {
			logger.error("结算操作settlementWithDraw 处理用户账号余额异常:{}", e.getMessage());
			throw new MdBizException(ConstApiStatus.DEALWLTH_ACCOUNT_MONEY_ERROR);
		}
		//step3 修改提现状态
		this.updateWithDraw(deposit);
	}
	/**
	 * 记录余额明细     To Do
	 * @param withdrawDeposit
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年6月2日 下午6:14:13
	 */
	private void cutConsumeMoneyAndDetail(MSBankWithdrawDeposit withdrawDeposit,String updateUser)throws MdBizException{
		Map<String, Object> map=new HashMap<>();
		map.put("id", withdrawDeposit.getId());
		List<MSWithdrawInfoByAccountType> list= baseDao.selectList(map, "MSBankWithdrawDepositMapper.queryWithdrawInfoByAccountTypeList");
		if(CollectionUtils.isEmpty(list)) throw new MdBizException(ConstApiStatus.QUERY_WITHDRAW_BY_ACCOUNT_TYPE_ERROR);
		//step2 遍历数据  更新相关账号的金额
		for(MSWithdrawInfoByAccountType accountType:list){
			//查询账号 
			List<MSAccount> accountList=queryAccountList(null,null,accountType.getAccount_no());
			MSAccount account=accountList.get(0);
			Double blance=DoubleCalculate.sub(account.getBalance(), accountType.getWithdrawAmount());
			//更新账户余额
			updateAccountBalance(null, blance, accountType.getAccount_no(),account.getMemId());
			//step3
			updateAccountBalanceReport(account.getAccountTypeNo(),-accountType.getWithdrawAmount(),account.getMemId(),ConstSysParamsDefination.BALANCE_UPDATE_OPERATE);
			//step4记录账号余额明细   
			accountDetailService.saveCutAccountDetail(null,withdrawDeposit.getBusinessNo(),account.getAccountNo(),"",
					ConstTradeType.TRADE_TYPE_YETX.getCode(),String.valueOf(accountType.getWithdrawAmount()),
					 new Date(),String.valueOf(account.getBalance()),  ConstSysParamsDefination.ACCOUNT_BALANCE_DETAIL_REMARK,updateUser,ConstSysParamsDefination.IS_N);
			map.put("accountNo", account.getAccountNo());
		}
		//step5记录总流水
		accountDetailService.saveCutAccountDetail(null,withdrawDeposit.getBusinessNo(),map.get("accountNo").toString(),"",
				ConstTradeType.TRADE_TYPE_YETX.getCode(),String.valueOf(withdrawDeposit.getApplyWithdrawAmount()),
				 new Date(),String.valueOf(withdrawDeposit.getWithdrawBalance()),  "余额提现总流水",updateUser,ConstSysParamsDefination.IS_Y);
		//step6 修改总的金额    
		Map<String, Object> mapParam=new HashMap<>();
		mapParam.put("memId", withdrawDeposit.getMemId());
		mapParam.put("balance", -withdrawDeposit.getApplyWithdrawAmount());
		baseDao.update(mapParam, "MSBankWithdrawDepositMapper.updateAccountReportBalanceByMemId");
	}
	
	/**
	 * 查看提现记录  根据ID
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年4月27日 下午12:11:16
	 */
	private MSBankWithdrawDeposit queryMSBankWithdrawDepositById(String id)throws MdBizException{
		MSBankWithdrawDeposit withdrawDeposit=null;
		if(StringUtils.isBlank(id)) throw new MdBizException(ConstApiStatus.REQUIRED_PARAM_EMPTY);
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", id);
			withdrawDeposit=baseDao.selectOne(map, "MSBankWithdrawDepositMapper.queryWidthDrawById");
		} catch (Exception e) {
			logger.error("根据ID:{},查看提现记录queryMSBankWithdrawDepositById错误:{}",id,e.getMessage());
			throw new MdBizException(ConstApiStatus.QUERY_BANK_WITHDRAW_BY_ID_ERROR);
		}
		return withdrawDeposit;
	}
	
	/**
	 * 根据memId 查询账号信息
	 * @param memId
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年4月26日 下午5:38:45
	 */
	private MSAccountReport queryAccountByMemId(String memId) throws MdBizException{
		MSAccountReport account=null;
		try {
			account=accountReportService.getTotalAndFreezeBalanceByMemId(memId);
			if(account==null) throw new MdBizException(ConstApiStatus.ACCOUNT_IS_NULL_ERROR);
		} catch (Exception e) {
			logger.error("根据memID:{},查询账号信息queryAccountByMemId错误:{}",memId,e);
			throw new MdBizException(ConstApiStatus.ACCOUNT_IS_NULL_ERROR);
		}
		return account;
	}

	/**
	 * @param deposit
	 * @throws MdBizException
	 */
	@Override
	public String saveBankWithdrawDeposit(RequestMSBankWithDrawDepostie deposit) throws MdBizException {
		//step1 检查账号信息
		MSAccountReport account=this.checkAccountMeg(deposit.getMemId(),deposit.getAccountNo(),deposit.getApplyWithdrawAmount());
		//step2 提现
		return this.applyBankWithdrawDeposit(deposit,account);
	}
	
	/**
	 * 检查账号提现信息
	 * @param memId
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年4月28日 上午11:26:48
	 */
	private MSAccountReport checkAccountMeg(String memId,String accountNo,Double applyCarryCash)throws MdBizException{
		//step1 查询账号信息
		MSAccountReport account =this.queryAccountByMemId(memId);
		//step2 查看银行卡信息
		MSBankAccount bankAccount=bankAccountService.getBankAccount(memId, accountNo);
		if(bankAccount==null){
			logger.error("会员银行卡账户信息不存在memID:{},accountNo:{}",memId,accountNo);
			throw new MdBizException(ConstApiStatus.ACCOUNT_BANK_CARD_IS_NULL);
		}
		//step3 检查申请余额，并计算  
		Double old_useMoney=accountServices.getAllowWithdrawBalance(memId);
		Double old_applyCarryCash = Double.valueOf(applyCarryCash);
		//申请提现余额超过最大可提现金额50000
		if(old_applyCarryCash > ConstSysParamsDefination.FIFTY_THOUSAND){
			logger.error("超过最大可提现限制50000,memID:{},提现金额applyCarryCash:{}",memId,old_applyCarryCash);
			throw new MdBizException(ConstApiStatus.ACCOUNT_APPLY_CARRY_CASH_ERROR);
		}
		//计算申请提现余额是否超最大余额
		if(old_applyCarryCash > old_useMoney){
			logger.error("余额不足无法支付,memID:{},提现金额applyCarryCash:{},账号余额:{}",memId,old_applyCarryCash,old_useMoney);
			throw new MdBizException(ConstApiStatus.ACCOUNT_INSUFFICIENT_BALANCE_ERROR);
		}
		//计算当前余额是否低于最低提现金额
		if(old_useMoney <= ConstSysParamsDefination.MIN_APPLY_CARRY_CASH){
			logger.error("余额低于最低提现金额,无法支付,memID:{},账号余额:{}",memId,old_useMoney);
			throw new MdBizException(ConstApiStatus.ACCOUNT_BALANCE_BELOW_WITHDRAW_ERROR);
		}
		return account;
	}
	/**
	 * 申请银行提现操作
	 * @param deposit
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年4月28日 上午10:55:00
	 */
	private String applyBankWithdrawDeposit(RequestMSBankWithDrawDepostie deposit,MSAccountReport account)throws MdBizException{
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
			throw new MdBizException(ConstApiStatus.WITHDRAW_ERROR);
		}
		// 数据检查 实际提现金额必须大于0
		if (Double.parseDouble(calcActualCarryCash) <=Constants.CONSTANT_INT_ZERO) {
			logger.error("实际提现金额:{},必须大于最低提现手续费2元",memId,calcActualCarryCash);
			throw new MdBizException(ConstApiStatus.ACTUAL_WITHDRAW_MONEY_THEN_ZERO_ERROR);
		}
		deposit.setActualCarryCash(calcActualCarryCash);
		deposit.setCounterFee(calcCounterFee);
		deposit.setWithdrawBalance((String)returnMap.get("withdraw_balance"));
		//插入提现记录返回业务单号
		String id = UUID.randomUUID().toString();
		deposit.setId(id);
		String businessNo = this.addBankWithdrawDeposit(deposit);
		//处理结果
		this.addConsumeFreezeMoney(id,memId,Double.valueOf(deposit.getApplyCarryCash()),businessNo,new Date());
		return businessNo;
	}
	
	/**
	 * 处理金额
	 * @param addFreezeMoney
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年6月1日 下午3:39:03
	 */
	private void addConsumeFreezeMoney(String id,String memId,Double addFreezeMoney,String businessNo,Date applyDate)throws MdBizException{
		//step1 查询所有存在的账号信息  
		List<MSAccount> list=null;
		Double freezeBalance=0.0;
		Double totalFrezeMoney=addFreezeMoney;
		Double freeTotal=0.0; //手续费总和
		List<Double> listFree=new ArrayList<Double>();
		try {
			list=queryAccountList(memId,Constants.CONSTANT_STR_ONE,null);
			Date date=new Date();
			for(MSAccount account:list){
			//判断账号余额是否能够扣减冻结
			Double useBalance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),Double.valueOf(account.getFreezeBalance()));
			if(useBalance<=0){
				continue;
			}
			Double deductionMoney= DoubleCalculate.sub(addFreezeMoney, useBalance); // 扣减金额=提现金额-账号可用金额
			//step2 扣减金额<0 表示 第一个账号的钱足够扣除
			if(deductionMoney<=0){
				freezeBalance = DoubleCalculate.add(Double.valueOf(account.getFreezeBalance()),Math.abs(addFreezeMoney));//冻结金额+提现金额=新的冻结总额
				Double free=DoubleCalculate.mul(addFreezeMoney, account.getWithdrawPoundageScale()); //计算账号手续费
				//插入提现ms_withdraw_info_by_account_type  子表里
				addWithDrawInfoByAccountType(id,account.getAccountNo(),addFreezeMoney,useBalance);
				//更新用户冻结金额
				updateAccountFreezeBalance(account.getId(), Math.abs(freezeBalance),null,account.getMemId());
				//更新ms_account_report 表总对应账号的字段值
				updateAccountBalanceReport(account.getAccountTypeNo(),addFreezeMoney,account.getMemId(),ConstSysParamsDefination.FREE_ALANCE_UPDATE_OPERATE);
				//增加余额冻结明细
				insertAccoutFreezeDetail(account.getAccountNo(),businessNo,ConstSysParamsDefination.FREEZE,ConstSysParamsDefination.ACCOUNT_BALANCE_DETAIL_REMARK,addFreezeMoney,ConstTradeType.TRADE_TYPE_YETX.getCode(),freezeBalance,date);
				if(account.getWithdrawPoundageScale()>0) listFree.add(free);//保存每一个账号的手续费 用于累加
				break;
			}
			addFreezeMoney=deductionMoney;
			Double free=DoubleCalculate.mul(useBalance, account.getWithdrawPoundageScale());  //单个账号的手续费比例
			//根据优先级 修改账号的冻结金额  =以前的冻结金额+可用的余额
			freezeBalance = DoubleCalculate.add(Double.valueOf(account.getFreezeBalance()),Double.valueOf(useBalance));
			//更新用户冻结金额
			updateAccountFreezeBalance(account.getId(), freezeBalance,null,account.getMemId());
			//插入提现ms_withdraw_info_by_account_type  子表里
			addWithDrawInfoByAccountType(id,account.getAccountNo(),useBalance,useBalance);
			//更新ms_account_report 表总对应账号的字段值
			updateAccountBalanceReport(account.getAccountTypeNo(),useBalance,account.getMemId(),ConstSysParamsDefination.FREE_ALANCE_UPDATE_OPERATE);
			//增加明细
			insertAccoutFreezeDetail(account.getAccountNo(),businessNo,ConstSysParamsDefination.FREEZE,ConstSysParamsDefination.ACCOUNT_BALANCE_DETAIL_REMARK,useBalance,ConstTradeType.TRADE_TYPE_YETX.getCode(),freezeBalance,date);
			if(account.getWithdrawPoundageScale()>0)  listFree.add(free);//保存每一个账号的手续费 用于累加
			}
			if(listFree.size()>0){
				for(Double value:listFree){
					freeTotal=DoubleCalculate.add(freeTotal, value);
				}
				//判断手续费是否小于最低2元
				if(freeTotal<Constants.CONSTANT_INT_TWO) freeTotal=Double.valueOf(Constants.CONSTANT_INT_TWO);
			}
			//step3更新提现 实际金额和手续费
			updateWithDrawAmountAndFee(id,freeTotal,DoubleCalculate.sub(totalFrezeMoney, freeTotal));
			//step4 修改总的冻结金额
			Map<String, Object> mapParam=new HashMap<>();
			mapParam.put("memId", memId);
			mapParam.put("freezeBalance", totalFrezeMoney);
			baseDao.update(mapParam, "MSBankWithdrawDepositMapper.updateAccountReportByMemId");
		} catch (Exception e) {
			logger.error("提现申请操作异常:{}",e);
			throw new MdBizException(ConstApiStatus.WITHDRAW_APPLY_ERROR);
		}
	}
	/**
	 *  修改提现记录的 实际提现金额和总的手续费用
	 * @param id
	 * @param poundageAmount
	 * @param actualWithdrawAmount
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年6月9日 下午2:52:07
	 */
	private void updateWithDrawAmountAndFee(String id,Double poundageAmount,Double actualWithdrawAmount) throws MdBizException{
		try {
			MSBankWithdrawDeposit deposit=new MSBankWithdrawDeposit();
			deposit.setId(id);
			deposit.setPoundageAmount(poundageAmount);
			deposit.setActualWithdrawAmount(actualWithdrawAmount);
			baseDao.update(deposit, "MSBankWithdrawDepositMapper.updateWidthDrawDeposit");
		} catch (Exception e) {
			logger.error("修改提现 实际金额和手续费异常:{}",e);
			throw new MdBizException(ApiStatusConst.UPDATE_WITHDRAW_DEPOSIT_AMOUNT_ERROR);
		}
	}
	
	/**
	 * 更新account report 某一账号的金额
	 * @param accountType
	 * @param balance
	 * @param memId
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年6月5日 下午6:02:50
	 */
	private void updateAccountBalanceReport(String accountType,Double balance,String memId,String operate)throws MdBizException{
		try {
			Map<String, Object> map=new HashMap<>();
			map.put(accountType, balance);
			map.put("memId", memId);
			if(operate.equals(ConstSysParamsDefination.FREE_ALANCE_UPDATE_OPERATE)){
				baseDao.update(map, "MSAccountReportMapper.updateFreezeBalance");
			}else{
				baseDao.update(map, "MSAccountReportMapper.updateBalance");
			}
		} catch (Exception e) {
			logger.error("更新会员账户表异常:{},memId:{},accountType:{},balance:{}",e,memId,accountType,balance);
			throw new MdBizException(ConstApiStatus.QUERY_ACCOUNT_REPORT_ERROR);
		}
	}
	
	/**
	 * 插入冻结明细
	 * @param accountNo
	 * @param businessNo
	 * @param inOrOut
	 * @param remark
	 * @param tradeAmount
	 * @param tradeType
	 * @param FreezeBalance
	 * @param date
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年6月5日 下午6:02:36
	 */
	private void insertAccoutFreezeDetail(String accountNo,String businessNo,Integer inOrOut,String remark,Double
			tradeAmount,String tradeType,Double FreezeBalance,Date date) throws MdBizException{
		MSAccountFreezeDetail model=new MSAccountFreezeDetail();
		try{
			model.setAccountNo(accountNo);
			model.setBusinessNo(businessNo);
			model.setCreateDate(date);
			model.setCreateUser(ConstSysParamsDefination.SYSTEM_USER);
			model.setId(UUID.randomUUID().toString());
			model.setInOrOut(inOrOut);
			model.setRemark("账户:"+accountNo+","+remark+":"+tradeAmount);
			model.setTradeAmount(tradeAmount);
			model.setTradeDate(date);
			model.setTradeType(tradeType);
			model.setUpdateDate(date);
			model.setUpdateUser(ConstSysParamsDefination.SYSTEM_USER);
			model.setFreezeBalance(FreezeBalance);	
			//记录解冻明细
			accountFreezeDetailService.insertAccoutFreezeDetail(model);
		}catch(Exception e){
			logger.error("插入冻结余额明细异常:{},插入数据:{}",e,model.toString());
			throw new MdBizException(ConstApiStatus.INSERT_MEMBER_FREEZE_DETAIL_ERROR);
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
		    businessNo = GenerateNumber.generateBusinessNo(ConstTradeType.TRADE_TYPE_YETX.getCode());
		    
			MSBankAccount bankAccount=bankAccountService.getBankAccount(dto.getMemId(), dto.getAccountNo());
			MSBankWithdrawDeposit entity = new MSBankWithdrawDeposit();
			entity.setId(dto.getId());
			entity.setMemId(dto.getMemId());
			entity.setBusinessNo(businessNo);
			entity.setBankAccountId(bankAccount.getId());
			entity.setAccountIdcard(bankAccount.getAccountIdcard());
			entity.setBankCardNo(dto.getAccountNo());
			entity.setAccountName(bankAccount.getAccountName());
			entity.setAccountBank(bankAccount.getAccountBank());
			entity.setAccountProvince(bankAccount.getAccountProvince());
			entity.setAccountCity(bankAccount.getAccountCity());
			entity.setAccountArea(bankAccount.getAccountArea());
			entity.setAccountSubBank(bankAccount.getAccountSubBank());
			entity.setApplyDate(nowDate);
			entity.setApplyWithdrawAmount(Double.valueOf(dto.getApplyCarryCash()));
			entity.setPoundageAmount(Double.valueOf(dto.getCounterFee()));
			entity.setActualWithdrawAmount(Double.valueOf(dto.getActualCarryCash()));
			entity.setRemark(ConstSysParamsDefination.ACCOUNT_BALANCE_DETAIL_REMARK);
			entity.setCreateDate(nowDate);
			Map<String, Object> params=new HashMap<>();
			params.put("memId", dto.getMemId());
			MSMembers members=baseDao.selectOne(params, "MSBankWithdrawDepositMapper.getMemberInfoByCondition");
			if(members!=null)	entity.setCreateUser(DESC.deyption(members.getMemLoginName()));
			else 	entity.setCreateUser(ConstSysParamsDefination.SYSTEM_USER);
			entity.setIsDelete(ConstSysParamsDefination.IS_N);
			entity.setWithdrawBalance(dto.getWithdrawBalance()==null?0.0:Double.valueOf(dto.getWithdrawBalance()));
			baseDao.insert(entity, "MSBankWithdrawDepositMapper.insertBankWithdrawDeposit");
			return businessNo;
		}catch(Exception e){
 			logger.error("新增提现记录addBankWithdrawDeposit异常:{}",e);
			throw new MdBizException(ConstApiStatus.INSERT_WITHDRAW_ERROR);
		}
	}
	
	/**
	 * 新增提现子表  和账号相关记录
	 * @param masterId
	 * @param accountNo
	 * @param withdrawAmount
	 * @param withdrawBalance
	 * @author: jianhua.huang  2017年6月2日 下午12:16:01
	 */
	private void addWithDrawInfoByAccountType(String masterId,String accountNo,Double withdrawAmount,Double withdrawBalance){
		Date nowDate = new Date(System.currentTimeMillis());
		Map<String, Object> map=new HashMap<>();
		try{
			String id = UUID.randomUUID().toString();
			map.put("id", id);
			map.put("masterId", masterId);
			map.put("accountNo", accountNo);
			map.put("withdrawAmount", withdrawAmount);
			map.put("withdrawBalance", withdrawBalance);
			map.put("applyDate", nowDate);
			map.put("createDate", nowDate);
			map.put("createUser", ConstSysParamsDefination.SYSTEM_USER);
			baseDao.insert(map, "MSBankWithdrawDepositMapper.insertWithDrawInfoByAccountType");
		}catch(Exception e){
			logger.error("新增提现记录ms_withdraw_info_by_account_type表异常:{}",e);
			throw new MdBizException(ConstApiStatus.INSERT_WITHDRAW_ERROR);
		}
	}
	
	/**
	 * 修改账户冻结余额
	 * @param id
	 * @param freezeBalance
	 * @return
	 */
	private void updateAccountFreezeBalance(String id, Double freezeBalance,String accountNo,String memId) throws MdBizException{
		Map<String,String> paramsMap = new HashMap<String,String>();
		try {
			paramsMap.put("id", id);
			paramsMap.put("accountNo", accountNo);
			paramsMap.put("freezeBalance", String.valueOf(freezeBalance));
			paramsMap.put("freezeBalanceEncrypt", DESC.encryption(String.valueOf(freezeBalance), memId));
			baseDao.update(paramsMap, "MSBankWithdrawDepositMapper.updateFreezeBalanceByMemId");
		} catch (Exception e) {
			logger.error("修改会员账户冻结余额出现错误，会员账户ID:{},错误信息:{}", id, e.getMessage());
			throw new MdBizException(20);
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
		final Double old_useMoney = accountReportService.getAvailableBalance(memId);
		final Double old_applyCarryCash = Double.valueOf(applyCarryCash);
		final Double calc_feeScale = ConstSysParamsDefination.FEESCALE; //手续费比例
		final Double calc_minFee = ConstSysParamsDefination.MIN_APPLY_CARRY_FEE;   //最小手续费
		Double calc_counterFee = Double.valueOf(Constants.CONSTANT_INT_ZERO);
		Double calc_actualCarryCash = Double.valueOf(Constants.CONSTANT_INT_ZERO);
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
		returnMap.put("withdraw_balance", String.valueOf(old_useMoney));
		return returnMap;
	}

	/**
	 * 查询账号集合
	 * @param memId
	 * @param orderByName
	 * @return
	 * @author: jianhua.huang  2017年6月2日 上午10:38:41
	 */
	private List<MSAccount> queryAccountList(String memId,String orderByName,String accountNo) throws MdBizException{
		List<MSAccount> list=null;
		Map<String, Object> map=new HashMap<>();
		map.put("memId", memId);
		map.put("accountNo", accountNo);
		map.put("orderByName", orderByName);
		list=baseDao.selectList(map, "MSBankWithdrawDepositMapper.queryAccountByMemIdList");
		if(CollectionUtils.isEmpty(list)){//没有账户信息
			throw new MdBizException(ConstApiStatus.ACCOUNT_IS_NULL_ERROR);
		}
		return list;
	}
}

