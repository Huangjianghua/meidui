package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 账户类型
 * @author jun.wu@meiduimall.com
 *
 */
public class MSWalletType implements Serializable{

	private static final long serialVersionUID = -6955727232474967171L;

	private String id;
	
	/**账户名称*/
    private String walletName;

    /**账户编号*/
    private String walletNo;

    /**是否可提现 0可提现 1不可提现*/
    private String allowWithdraw;

    /**是否需要手续费 0需要 1不需要*/
    private String needPoundage;

    /**是否支持退款 0支持 1不支持*/
    private String allowRefund;

    /**手续费比例*/
    private Byte poundageScale;

    /**账户类型，AT01积分账户，AT02余额账户*/
    private String accountType;

    /**提现优先级*/
    private Byte withdrawLevel;

    /**消费优先级*/
    private Byte spendLevel;

    /**创建日期*/
    private Date createDate;

    /**更新日期*/
    private Date updateDate;

    /**更新人*/
    private String updateMan;

    /**备注*/
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName == null ? null : walletName.trim();
    }

    public String getWalletNo() {
        return walletNo;
    }

    public void setWalletNo(String walletNo) {
        this.walletNo = walletNo == null ? null : walletNo.trim();
    }

    public String getAllowWithdraw() {
        return allowWithdraw;
    }

    public void setAllowWithdraw(String allowWithdraw) {
        this.allowWithdraw = allowWithdraw == null ? null : allowWithdraw.trim();
    }

    public String getNeedPoundage() {
        return needPoundage;
    }

    public void setNeedPoundage(String needPoundage) {
        this.needPoundage = needPoundage == null ? null : needPoundage.trim();
    }

    public String getAllowRefund() {
        return allowRefund;
    }

    public void setAllowRefund(String allowRefund) {
        this.allowRefund = allowRefund == null ? null : allowRefund.trim();
    }

    public Byte getPoundageScale() {
        return poundageScale;
    }

    public void setPoundageScale(Byte poundageScale) {
        this.poundageScale = poundageScale;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public Byte getWithdrawLevel() {
        return withdrawLevel;
    }

    public void setWithdrawLevel(Byte withdrawLevel) {
        this.withdrawLevel = withdrawLevel;
    }

    public Byte getSpendLevel() {
        return spendLevel;
    }

    public void setSpendLevel(Byte spendLevel) {
        this.spendLevel = spendLevel;
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
        this.updateMan = updateMan == null ? null : updateMan.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String toString() {
		return "MSWalletType [id=" + id + ", walletName=" + walletName + ", walletNo=" + walletNo + ", allowWithdraw="
				+ allowWithdraw + ", needPoundage=" + needPoundage + ", allowRefund=" + allowRefund + ", poundageScale="
				+ poundageScale + ", accountType=" + accountType + ", withdrawLevel=" + withdrawLevel + ", spendLevel="
				+ spendLevel + ", createDate=" + createDate + ", updateDate=" + updateDate + ", updateMan=" + updateMan
				+ ", remark=" + remark + "]";
	}
  
}