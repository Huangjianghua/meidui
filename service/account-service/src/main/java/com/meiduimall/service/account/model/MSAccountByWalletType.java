package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 会员按业务类型账户表Model
 * @author chencong
 *
 */
public class MSAccountByWalletType extends BaseModel implements Serializable {

	private static final long serialVersionUID = 8080490869514026587L;
	
	/** 账户标识 */
	private String id;
	
	/** 对应ms_wallet_type表的主键*/
	private String walletType;

	/** 当前类型总金额明文*/
	private String balance;

	/**当前类型冻结余额明文 */
	private String balanceEncrypt;
	
	/**冻结总金额明文*/
	private String freezeBalance;

	/**当前类型冻结余额密文*/
	private String freezeBalanceEncrypt;
	
	/**账户状态,0 正常 1禁用*/
	private String accountStatus;
	
	/**账户状态密文,0 正常 1禁用*/
	private String accountStatusEncrypt;

	/** 创建时间 */
	private Date createDate;

	/** 修改时间 */
	private Date updateDate;
	
	/**更新人*/
	private String updateMan;
	
	/**备注*/
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWalletType() {
		return walletType;
	}

	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBalanceEncrypt() {
		return balanceEncrypt;
	}

	public void setBalanceEncrypt(String balanceEncrypt) {
		this.balanceEncrypt = balanceEncrypt;
	}

	public String getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(String freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	public String getFreezeBalanceEncrypt() {
		return freezeBalanceEncrypt;
	}

	public void setFreezeBalanceEncrypt(String freezeBalanceEncrypt) {
		this.freezeBalanceEncrypt = freezeBalanceEncrypt;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountStatusEncrypt() {
		return accountStatusEncrypt;
	}

	public void setAccountStatusEncrypt(String accountStatusEncrypt) {
		this.accountStatusEncrypt = accountStatusEncrypt;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
