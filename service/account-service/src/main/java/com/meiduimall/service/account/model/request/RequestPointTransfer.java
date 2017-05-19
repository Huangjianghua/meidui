/**
 * 
 */
package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import com.meiduimall.service.account.util.PageHelp;

/**
 * @author: jianhua.huang
 * @version: 2017年5月18日 上午11:47:37 0.1 Description: 查询提现积分
 */
public class RequestPointTransfer extends PageHelp implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 转出账号
	 */
	private String accountTurnOut;
	/**
	 * 转入账号
	 */
	private String accountTurnIN;

	/**
	 * 转账时间开始
	 */
	private String beginTurnDate;
	/**
	 * 转账时间开始
	 */
	private String endTurnDate;
	/**
	 * 是否分页
	 */
	private String flag;
	/**
	 * 转账类型
	 */
	private String type;
	/**
	 * 转账流水号
	 */
	private String mthNo;

	public String getAccountTurnOut() {
		return accountTurnOut;
	}

	public String getAccountTurnIN() {
		return accountTurnIN;
	}

	public void setAccountTurnIN(String accountTurnIN) {
		this.accountTurnIN = accountTurnIN;
	}

	public void setAccountTurnOut(String accountTurnOut) {
		this.accountTurnOut = accountTurnOut;
	}

	public String getBeginTurnDate() {
		return beginTurnDate;
	}

	public void setBeginTurnDate(String beginTurnDate) {
		this.beginTurnDate = beginTurnDate;
	}

	public String getEndTurnDate() {
		return endTurnDate;
	}

	public void setEndTurnDate(String endTurnDate) {
		this.endTurnDate = endTurnDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMthNo() {
		return mthNo;
	}

	public void setMthNo(String mthNo) {
		this.mthNo = mthNo;
	}

	/**
	 * @return
	 */
	@Override
	public String toString() {
		return "RequestPointTransfer [AccountTurnOut=" + accountTurnOut + ", AccountTurnIN=" + accountTurnIN
				+ ", beginTurnDate=" + beginTurnDate + ", endTurnDate=" + endTurnDate + ", flag=" + flag + ", type="
				+ type + "]";
	}

}
