package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 会员按业务类型账户表实体
 * @author chencong
 *
 */
public class MSAccountByWalletType implements Serializable {

	private static final long serialVersionUID = 8080490869514026587L;
	
	/** 账户标识 */
	private String id;

	/** 会员ID */
	private String memId;

	/** 账户类型（AT01：积分账户；AT02：现金账户；） */
	private String type;

	/** 总金额明文*/
	private String balance;

	/**总金额密文 */
	private String balance_encrypt;
	
	/**冻结总金额明文*/
	private String freeze_balance;

	/**冻结总金额密文*/
	private String freeze_balance_encrypt;

	/** 创建时间 */
	private Date createDate;

	/** 修改时间 */
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBalance_encrypt() {
		return balance_encrypt;
	}

	public void setBalance_encrypt(String balance_encrypt) {
		this.balance_encrypt = balance_encrypt;
	}

	public String getFreeze_balance() {
		return freeze_balance;
	}

	public void setFreeze_balance(String freeze_balance) {
		this.freeze_balance = freeze_balance;
	}

	public String getFreeze_balance_encrypt() {
		return freeze_balance_encrypt;
	}

	public void setFreeze_balance_encrypt(String freeze_balance_encrypt) {
		this.freeze_balance_encrypt = freeze_balance_encrypt;
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
	
}
