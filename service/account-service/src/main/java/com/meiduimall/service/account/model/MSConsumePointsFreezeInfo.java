package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分冻结解冻明细表ms_consume_points_freeze_info实体类
 * @author chencong
 *
 */
public class MSConsumePointsFreezeInfo implements Serializable{
     
	private static final long serialVersionUID = 6676261434640382390L;

	private String mcpfId;

    private String memId;

    private String mcpfOrderId;

    private String mcpfFreezeType;

    private String mcpfConsumePoints;

    private String mcpfConsumePointsBalance;

    private String mcpfCreatedBy;

    private Date mcpfCreatedDate;

    private String mcpfUpdatedBy;

    private Date mcpfUpdatedDate;

    private String mcpfRemark;

    public String getMcpfId() {
        return mcpfId;
    }

    public void setMcpfId(String mcpfId) {
        this.mcpfId = mcpfId == null ? null : mcpfId.trim();
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId == null ? null : memId.trim();
    }

    public String getMcpfOrderId() {
        return mcpfOrderId;
    }

    public void setMcpfOrderId(String mcpfOrderId) {
        this.mcpfOrderId = mcpfOrderId == null ? null : mcpfOrderId.trim();
    }

    public String getMcpfFreezeType() {
        return mcpfFreezeType;
    }

    public void setMcpfFreezeType(String mcpfFreezeType) {
        this.mcpfFreezeType = mcpfFreezeType == null ? null : mcpfFreezeType.trim();
    }

    public String getMcpfConsumePoints() {
        return mcpfConsumePoints;
    }

    public void setMcpfConsumePoints(String mcpfConsumePoints) {
        this.mcpfConsumePoints = mcpfConsumePoints == null ? null : mcpfConsumePoints.trim();
    }

    public String getMcpfConsumePointsBalance() {
        return mcpfConsumePointsBalance;
    }

    public void setMcpfConsumePointsBalance(String mcpfConsumePointsBalance) {
        this.mcpfConsumePointsBalance = mcpfConsumePointsBalance == null ? null : mcpfConsumePointsBalance.trim();
    }

    public String getMcpfCreatedBy() {
        return mcpfCreatedBy;
    }

    public void setMcpfCreatedBy(String mcpfCreatedBy) {
        this.mcpfCreatedBy = mcpfCreatedBy == null ? null : mcpfCreatedBy.trim();
    }

    public Date getMcpfCreatedDate() {
        return mcpfCreatedDate;
    }

    public void setMcpfCreatedDate(Date mcpfCreatedDate) {
        this.mcpfCreatedDate = mcpfCreatedDate;
    }

    public String getMcpfUpdatedBy() {
        return mcpfUpdatedBy;
    }

    public void setMcpfUpdatedBy(String mcpfUpdatedBy) {
        this.mcpfUpdatedBy = mcpfUpdatedBy == null ? null : mcpfUpdatedBy.trim();
    }

    public Date getMcpfUpdatedDate() {
        return mcpfUpdatedDate;
    }

    public void setMcpfUpdatedDate(Date mcpfUpdatedDate) {
        this.mcpfUpdatedDate = mcpfUpdatedDate;
    }

    public String getMcpfRemark() {
        return mcpfRemark;
    }

    public void setMcpfRemark(String mcpfRemark) {
        this.mcpfRemark = mcpfRemark == null ? null : mcpfRemark.trim();
    }
    
}