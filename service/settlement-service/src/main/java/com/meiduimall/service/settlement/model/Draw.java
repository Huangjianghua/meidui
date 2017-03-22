package com.meiduimall.service.settlement.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 提现申请记录
 * @author guidl
 *
 */
public class Draw implements Serializable {

	private static final long serialVersionUID = -44890491738228041L;

	//提现编号
	private String drawCode;
	
	//收款人姓名
	private String realname;
	
	//银行名称
	private String bankname;
	
	//银行卡号
	private String banknum;
	
	//银行地址
	private String bankaddress;
	
	//提现手续费
	private BigDecimal cashWithdrawalFee;

	public String getDrawCode() {
		return drawCode;
	}

	public void setDrawCode(String drawCode) {
		this.drawCode = drawCode;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBanknum() {
		return banknum;
	}

	public void setBanknum(String banknum) {
		this.banknum = banknum;
	}
	
	public String getBankaddress() {
		return bankaddress;
	}

	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}

	public BigDecimal getCashWithdrawalFee() {
		return cashWithdrawalFee;
	}

	public void setCashWithdrawalFee(BigDecimal cashWithdrawalFee) {
		this.cashWithdrawalFee = cashWithdrawalFee;
	}
	
}
