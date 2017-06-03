package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.request.RequestMSBankWithDrawDepostie;
import com.meiduimall.service.account.model.request.RequestSaveBankWithdrawDeposit;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.BankAccountService;
import com.meiduimall.service.account.service.MSAccountDetailService;
import com.meiduimall.service.account.service.MSMembersService;
import com.meiduimall.service.account.service.WithDrawService;

/**
 * 提现相关Service接口{@link=WithDrawService}实现类
 * 
 * @author chencong
 *
 */
@Service
public class WithDrawServiceImpl implements WithDrawService {

	private final static Logger logger = LoggerFactory.getLogger(WithDrawServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Autowired
	private MSMembersService mSMembersService;
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private AccountReportService accountReportService;
	
	@Autowired
	private MSAccountDetailService mSAccountDetailService;

	@Override
	public List<MSBankWithdrawDeposit> getBankWithdrawDepositsList(String memId) {
		// 检查该用户是否存在
		if (!mSMembersService.checkUserIsExistByMemId(memId)) {
			throw new ServiceException(ConstApiStatus.USER_NOT_EXIST);
		}
		Map<String, String> params = new HashMap<>();
		params.put("memId", memId);
		List<MSBankWithdrawDeposit> list = baseDao.selectList(params,
				"MSBankWithdrawDepositMapper.selectBankWithdrawDeposit");
		return list;
	}

	@Override
	public void saveBankWithdrawDeposit(RequestSaveBankWithdrawDeposit model) {
//		/** 关键数据校验开始 */
//		/** 检查该用户是否存在 */
//		if (!mSMembersService.checkUserIsExistByMemId(model.getMemId())) {
//			throw new ServiceException(ConstApiStatus.USER_NOT_EXIST);
//		}
//		/** 检查银行信息是否存在 */
//		MSBankAccount bankAccount = bankAccountService.getBankAccount(model.getMemId(), model.getAccountNo());
//		if(bankAccount == null){
//			throw new ServiceException(ConstApiStatus.ACCOUNT_BANK_CARD_IS_NULL);
//		}
//		/** 检查申请余额，并计算  */
//		final Double old_useMoney = accountReportService.getAvailableBalance(model.getMemId());
//		final Double old_applyCarryCash = Double.valueOf(model.getApplyCarryCash());
//		//申请提现余额超过最大可提现金额
//		if(old_applyCarryCash > 50000){
//			throw new ServiceException(ConstApiStatus.ACCOUNT_APPLY_CARRY_CASH_ERROR);
//		}
//		//计算申请提现余额是否超最大余额
//		if(old_applyCarryCash > old_useMoney){
//			throw new ServiceException(ConstApiStatus.ACCOUNT_INSUFFICIENT_BALANCE_ERROR);
//		}
//		//计算当前余额是否低于最低提现金额
//		if(old_useMoney <= ConstSysParamsDefination.MIN_APPLY_CARRY_CASH){
//			throw new ServiceException(ConstApiStatus.ACCOUNT_INSUFFICIENT_BALANCE_ERROR);
//		}
//		/** 关键数据校验结束  */
		
		// 申请银行提现操作
		RequestMSBankWithDrawDepostie deposit = new RequestMSBankWithDrawDepostie();
		deposit.setMemId(model.getMemId());
		deposit.setAccountNo(model.getAccountNo());
		deposit.setApplyCarryCash(model.getApplyCarryCash());
		mSAccountDetailService.saveBankWithdrawDeposit(deposit);
	}
}