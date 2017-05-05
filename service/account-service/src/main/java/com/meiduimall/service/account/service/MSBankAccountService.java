package com.meiduimall.service.account.service;

import com.meiduimall.service.account.model.MSBankAccount;

public interface MSBankAccountService {
	
	public MSBankAccount getBankAccount(String memId, String accountNo);
	
	public int addBankAccount(MSBankAccount bankAccount);

}
