package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营后台 提现操作 model
 * @author:   jianhua.huang 
 * @version:  2017年5月5日 下午5:52:56 0.1 
 * Description:
 */
public class MSBankWithDrawOperateDetail implements Serializable{
	
    private static final long serialVersionUID = 6024472957800362716L;
    /**
     * 主键
     */
	private String id;
	/**
     * 提现主表 ID
     */
    private String depositId;
    /**
     * 用户
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 操作
     */
    private String operate;

    private String createBy;

    private Date createDate;

    private String isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId == null ? null : depositId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

	@Override
	public String toString() {
		return "MSBankWithDrawOperateDetail [id=" + id + ", depositId=" + depositId + ", name=" + name + ", remark="
				+ remark + ", operate=" + operate + ", createBy=" + createBy + ", createDate=" + createDate
				+ ", isDelete=" + isDelete + "]";
	}
    
}