package com.meiduimall.service.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.model.MSMembers;
import com.meiduimall.service.account.service.BankService;
import com.meiduimall.service.account.service.MSBankAccountService;
import com.meiduimall.service.account.service.MSMembersService;

@Component
public class BankServiceImpl implements BankService {
	
	@Autowired
	private MSBankAccountService bankAccountService;
	
	@Autowired
	private MSMembersService memberService;

	@Override
	public int addBankInfo(MSBankAccount mSBankAccount) {
		MSMembers member = memberService.getMemberInfo(mSBankAccount.getMemId());
		if(member == null){
			throw new ServiceException(ApiStatusConst.USER_NOT_EXIST,ApiStatusConst.getZhMsg(ApiStatusConst.USER_NOT_EXIST));
		}
		MSBankAccount bankAccount = bankAccountService.getBankAccount(member.getMemId(), mSBankAccount.getAccountNo());
		if(bankAccount == null){
			throw new ServiceException(ApiStatusConst.BANK_INFO_ALREADY_EXIST,ApiStatusConst.getZhMsg(ApiStatusConst.BANK_INFO_ALREADY_EXIST));
		}
		return bankAccountService.addBankAccount(mSBankAccount);
	}

}
