package com.meiduimall.service.account.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.service.BankService;

/**
 * 个人银行卡信息相关
 * @author guidl
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class BankController {
	
	private static final Logger logger = LoggerFactory.getLogger(BankController.class);
	
	@Autowired
	private BankService bankService;
	
	/**
	 * 新增会员银行账户信息
	 * @return
	 */
	@PostMapping("/addBankInfo")
	public ResBodyData addBankInfo(@RequestBody MSBankAccount mSBankAccount) {
		int result = bankService.addBankInfo(mSBankAccount);
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M, result);
	}
	
	/**
	 * 修改会员银行账户信息
	 * @return
	 */
	@PostMapping("/changeBankInfo")
	public ResBodyData changeBankInfo() {
		return null;
	}
	
	/**
	 * 查询会员所有银行账户信息
	 * @return
	 */
	@PostMapping("/getMemberBankInfo")
	public ResBodyData getMemberBankInfo() {
		return null;
	}

}
