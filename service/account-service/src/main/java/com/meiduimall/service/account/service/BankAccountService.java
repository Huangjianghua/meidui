package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.model.MSBankInfo;


/**
 * 类名:  BankAccountService<br>
 * 描述:  银行账户信息服务接口类<br>
 * 创建时间: 2016-12-18
 */
public interface BankAccountService {
	 
	/**
	 * 方法名: addBankAccount<br>
	 * 描述:  增加一条银行账户信息<br>
	 * 创建时间: 2016-12-18
	 * @param dto
	 * @return
	 */
	public String addBankAccount(MSBankAccount dto);
	
	/**
	 * 方法名: deleteBankAccount<br>
	 * 描述:  根据会员标识与银行卡号，删除一条银行账户信息<br>
	 * 创建时间: 2016-12-18
	 * @param memId
	 * @param accountNo
	 * @return
	 */
	public boolean deleteBankAccount(String memId,String accountNo);
	
	/**
	 * 方法名: updateBankAccount<br>
	 * 描述:  根据会员标识与银行卡号，修改银行账户信息<br>
	 * 创建时间: 2016-12-18
	 * @param memId
	 * @param accountNo
	 * @param updateMap
	 * @return
	 */
	public boolean updateBankAccount(String memId,String accountNo,java.util.Map<String,String> updateMap);
	
	/**
	 * 方法名: getBankAccount<br>
	 * 描述:  根据会员标识与银行卡号，获取银行账户信息<br>
	 * 创建时间: 2016-12-18
	 * @param memId
	 * @param accountNo
	 * @return
	 */
	public MSBankAccount getBankAccount(String memId,String accountNo);
	
	/**
	 * 方法名: checkBankAccount<br>
	 * 描述: 检查银行账户信息是否存在，存在为true，不存在为false  <br>
	 * 创建时间: 2016-12-19
	 * @param memId
	 * @param accountNo
	 * @return
	 */
	public boolean checkBankAccount(String memId,String accountNo);
	
	/**
	 * 方法名: getBankAccountList<br>
	 * 描述: 根据会员标识，获取银行账户信息集合 <br>
	 * 创建时间: 2016-12-18
	 * @param memId
	 * @return
	 */
	public List<MSBankAccount> getBankAccountList(String memId);
	
	/**
	 * 方法名: getBankInfoList<br>
	 * 描述: 查询银行信息 <br>
	 * 创建时间: 2016-12-22
	 * @return
	 */
	public List<MSBankInfo> getBankInfoList();
}
