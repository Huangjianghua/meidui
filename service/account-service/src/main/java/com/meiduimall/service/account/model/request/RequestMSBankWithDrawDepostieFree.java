/**
 * 
 */
package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author:   jianhua.huang 
 * @version:  2017年6月15日 上午11:44:30 0.1 
 * Description: 提现查询手续费model
 */
public class RequestMSBankWithDrawDepostieFree implements Serializable{

	private static final long serialVersionUID = 180839605719741969L;

	@NotNull(message="memId不能为空")
	private String memId;
	
	@NotNull(message="可提现金额不能为空")
	private String allow_withdraw_balance;

	/**
	 * @return the memId
	 */
	public String getMemId() {
		return memId;
	}

	/**
	 * @param memId the memId to set
	 */
	public void setMemId(String memId) {
		this.memId = memId;
	}

	/**
	 * @return the allow_withdraw_balance
	 */
	public String getAllow_withdraw_balance() {
		return allow_withdraw_balance;
	}

	/**
	 * @param allow_withdraw_balance the allow_withdraw_balance to set
	 */
	public void setAllow_withdraw_balance(String allow_withdraw_balance) {
		this.allow_withdraw_balance = allow_withdraw_balance;
	}

}
