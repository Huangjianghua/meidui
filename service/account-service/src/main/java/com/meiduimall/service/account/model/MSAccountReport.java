package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 会员账户按账户类型统计表
 * @author chencong
 *
 */
public class MSAccountReport extends BaseModel implements Serializable {

	private static final long serialVersionUID = 8080490869514026587L;

	private String id;
	
	private String memId;

	/**美兑积分金额*/
	private int balanceJFZH;
	
	/**美兑积分冻结金额*/
	private int freezeBalanceJFZH;
	
	/**总余额*/
	private Double balance;
	
	/**冻结总金额明文*/
	private Double freezeBalance;
	
	/**凯富充值1余额明文*/
	private Double balance_KFCZ1;
	
	/**凯富充值1冻结余额明文*/
	private Double freeze_balance_KFCZ1;
	
	/**凯富充值2余额明文*/
	private Double balance_KFCZ2;
	
	/**凯富充值2冻结余额明文*/
	private Double freeze_balance_KFCZ2;
	
	/**财务调整（需手续费）余额明文*/
	private Double balance_CWTZ1;
	
	/**财务调整（需手续费）冻结余额明文*/
	private Double freeze_balance_CWTZ1;
	
	/**财务调整（免手续费）余额明文*/
	private Double balance_CWTZ2;
	
	/**财务调整（免手续费）冻结余额明文*/
	private Double freeze_balance_CWTZ2;
	
	/**财务调整（不可提现）余额明文*/
	private Double balance_CWTZ3;
	
	/**财务调整（不可提现）冻结余额明文*/
	private Double freeze_balance_CWTZ3;
	
	/**商家订单奖励余额明文*/
	private Double balance_SJJL;
	
	/**商家订单奖励冻结余额明文*/
	private Double freeze_balance_SJJL;
	
	/**商家充值余额明文*/
	private Double balance_SJYE;
	
	/**商家充值冻结余额明文*/
	private Double freeze_balance_SJYE;
	
	/**个代充值余额明文*/
	private Double balance_GDYE;
	
	/**个代充值冻结余额明文*/
	private Double freeze_balance_GDYE;
	
	/**区代充值余额明文*/
	private Double balance_QDYE;
	
	/**区代充值冻结余额明文*/
	private Double freeze_balance_QDYE;
	
	/**附近消费奖励余额明文*/
	private Double balance_FJJL;
	
	/**附近消费奖励冻结余额明文*/
	private Double freeze_balance_FJJL;
	
	/**商城购物奖励余额明文*/
	private Double balance_SCJL;
	
	/**商城购物奖励冻结余额明文*/
	private Double freeze_balance_SCJL;
	
	/**商城余额导入余额明文*/
	private Double balance_YEDR;
	
	/**商城余额导入冻结余额明文*/
	private Double freeze_balance_YEDR;
	
	/** 创建时间 */
	private Date createDate;
	
	/** 创建人 */
	private String createUser;

	/** 修改时间 */
	private Date updateDate;
	
	/** 更新人*/
	private String updateUser;
	
	/**备注*/
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBalanceJFZH() {
		return balanceJFZH;
	}

	public void setBalanceJFZH(int balanceJFZH) {
		this.balanceJFZH = balanceJFZH;
	}

	public int getFreezeBalanceJFZH() {
		return freezeBalanceJFZH;
	}

	public void setFreezeBalanceJFZH(int freezeBalanceJFZH) {
		this.freezeBalanceJFZH = freezeBalanceJFZH;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(Double freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	public Double getBalance_KFCZ1() {
		return balance_KFCZ1;
	}

	public void setBalance_KFCZ1(Double balance_KFCZ1) {
		this.balance_KFCZ1 = balance_KFCZ1;
	}

	public Double getFreeze_balance_KFCZ1() {
		return freeze_balance_KFCZ1;
	}

	public void setFreeze_balance_KFCZ1(Double freeze_balance_KFCZ1) {
		this.freeze_balance_KFCZ1 = freeze_balance_KFCZ1;
	}

	public Double getBalance_KFCZ2() {
		return balance_KFCZ2;
	}

	public void setBalance_KFCZ2(Double balance_KFCZ2) {
		this.balance_KFCZ2 = balance_KFCZ2;
	}

	public Double getFreeze_balance_KFCZ2() {
		return freeze_balance_KFCZ2;
	}

	public void setFreeze_balance_KFCZ2(Double freeze_balance_KFCZ2) {
		this.freeze_balance_KFCZ2 = freeze_balance_KFCZ2;
	}

	public Double getBalance_CWTZ1() {
		return balance_CWTZ1;
	}

	public void setBalance_CWTZ1(Double balance_CWTZ1) {
		this.balance_CWTZ1 = balance_CWTZ1;
	}

	public Double getFreeze_balance_CWTZ1() {
		return freeze_balance_CWTZ1;
	}

	public void setFreeze_balance_CWTZ1(Double freeze_balance_CWTZ1) {
		this.freeze_balance_CWTZ1 = freeze_balance_CWTZ1;
	}

	public Double getBalance_CWTZ2() {
		return balance_CWTZ2;
	}

	public void setBalance_CWTZ2(Double balance_CWTZ2) {
		this.balance_CWTZ2 = balance_CWTZ2;
	}

	public Double getFreeze_balance_CWTZ2() {
		return freeze_balance_CWTZ2;
	}

	public void setFreeze_balance_CWTZ2(Double freeze_balance_CWTZ2) {
		this.freeze_balance_CWTZ2 = freeze_balance_CWTZ2;
	}

	public Double getBalance_CWTZ3() {
		return balance_CWTZ3;
	}

	public void setBalance_CWTZ3(Double balance_CWTZ3) {
		this.balance_CWTZ3 = balance_CWTZ3;
	}

	public Double getFreeze_balance_CWTZ3() {
		return freeze_balance_CWTZ3;
	}

	public void setFreeze_balance_CWTZ3(Double freeze_balance_CWTZ3) {
		this.freeze_balance_CWTZ3 = freeze_balance_CWTZ3;
	}

	public Double getBalance_SJJL() {
		return balance_SJJL;
	}

	public void setBalance_SJJL(Double balance_SJJL) {
		this.balance_SJJL = balance_SJJL;
	}

	public Double getFreeze_balance_SJJL() {
		return freeze_balance_SJJL;
	}

	public void setFreeze_balance_SJJL(Double freeze_balance_SJJL) {
		this.freeze_balance_SJJL = freeze_balance_SJJL;
	}

	public Double getBalance_SJYE() {
		return balance_SJYE;
	}

	public void setBalance_SJYE(Double balance_SJYE) {
		this.balance_SJYE = balance_SJYE;
	}

	public Double getFreeze_balance_SJYE() {
		return freeze_balance_SJYE;
	}

	public void setFreeze_balance_SJYE(Double freeze_balance_SJYE) {
		this.freeze_balance_SJYE = freeze_balance_SJYE;
	}

	public Double getBalance_GDYE() {
		return balance_GDYE;
	}

	public void setBalance_GDYE(Double balance_GDYE) {
		this.balance_GDYE = balance_GDYE;
	}

	public Double getFreeze_balance_GDYE() {
		return freeze_balance_GDYE;
	}

	public void setFreeze_balance_GDYE(Double freeze_balance_GDYE) {
		this.freeze_balance_GDYE = freeze_balance_GDYE;
	}

	public Double getBalance_QDYE() {
		return balance_QDYE;
	}

	public void setBalance_QDYE(Double balance_QDYE) {
		this.balance_QDYE = balance_QDYE;
	}

	public Double getFreeze_balance_QDYE() {
		return freeze_balance_QDYE;
	}

	public void setFreeze_balance_QDYE(Double freeze_balance_QDYE) {
		this.freeze_balance_QDYE = freeze_balance_QDYE;
	}

	public Double getBalance_FJJL() {
		return balance_FJJL;
	}

	public void setBalance_FJJL(Double balance_FJJL) {
		this.balance_FJJL = balance_FJJL;
	}

	public Double getFreeze_balance_FJJL() {
		return freeze_balance_FJJL;
	}

	public void setFreeze_balance_FJJL(Double freeze_balance_FJJL) {
		this.freeze_balance_FJJL = freeze_balance_FJJL;
	}

	public Double getBalance_SCJL() {
		return balance_SCJL;
	}

	public void setBalance_SCJL(Double balance_SCJL) {
		this.balance_SCJL = balance_SCJL;
	}

	public Double getFreeze_balance_SCJL() {
		return freeze_balance_SCJL;
	}

	public void setFreeze_balance_SCJL(Double freeze_balance_SCJL) {
		this.freeze_balance_SCJL = freeze_balance_SCJL;
	}

	public Double getBalance_YEDR() {
		return balance_YEDR;
	}

	public void setBalance_YEDR(Double balance_YEDR) {
		this.balance_YEDR = balance_YEDR;
	}

	public Double getFreeze_balance_YEDR() {
		return freeze_balance_YEDR;
	}

	public void setFreeze_balance_YEDR(Double freeze_balance_YEDR) {
		this.freeze_balance_YEDR = freeze_balance_YEDR;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
}
