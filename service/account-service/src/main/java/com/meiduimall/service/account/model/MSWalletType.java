package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 钱包类型
 * @author jun.wu@meiduimall.com
 *
 */
public class MSWalletType implements Serializable{
	
	
	private static final long serialVersionUID = 3648448998794056597L;

	private String id;

    private String walletName;

    private String walletNo;

    private String allowWithdraw;

    private String needPoundage;

    private String allowRefund;

    private Byte poundageScale;

    private String accountType;

    private Byte withdrawLevel;

    private Byte spendLevel;

    private Date createDate;

    private Date updateDate;

    private String updateMan;

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
}