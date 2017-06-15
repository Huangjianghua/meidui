/**
 * 
 */
package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author:   jianhua.huang 
 * @version:  2017年6月15日 上午11:44:30 0.1 
 * Description: 提现查询手续费model
 */
public class RequestMSBankWithDrawDepostieFree implements Serializable{

	private static final long serialVersionUID = 5475129089699436850L;
	@NotNull(message="memId不能为空")
	private String memId;
	
	@JsonProperty("allow_withdraw_balance")
	@NotNull(message="提现金额不能为空")
	private String allowWithdrawBalance;

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
	 * @return the allowWithdrawBalance
	 */
	public String getAllowWithdrawBalance() {
		return allowWithdrawBalance;
	}

	/**
	 * @param allowWithdrawBalance the allowWithdrawBalance to set
	 */
	public void setAllowWithdrawBalance(String allowWithdrawBalance) {
		this.allowWithdrawBalance = allowWithdrawBalance;
	}
}
