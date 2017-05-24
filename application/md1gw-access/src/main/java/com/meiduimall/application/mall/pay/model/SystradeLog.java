package com.meiduimall.application.mall.pay.model;

import java.io.Serializable;
import java.math.BigInteger;

public class SystradeLog implements Serializable {
    private Integer logId;

    private BigInteger relId;

    private Integer opId;

    private String opName;

    private String opRole;

    private String behavior;

    private Integer logTime;

    private String logText;

    private static final long serialVersionUID = 1L;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public BigInteger getRelId() {
        return relId;
    }

    public void setRelId(BigInteger relId) {
        this.relId = relId;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public String getOpRole() {
        return opRole;
    }

    public void setOpRole(String opRole) {
        this.opRole = opRole == null ? null : opRole.trim();
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior == null ? null : behavior.trim();
    }

    public Integer getLogTime() {
        return logTime;
    }

    public void setLogTime(Integer logTime) {
        this.logTime = logTime;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText == null ? null : logText.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", relId=").append(relId);
        sb.append(", opId=").append(opId);
        sb.append(", opName=").append(opName);
        sb.append(", opRole=").append(opRole);
        sb.append(", behavior=").append(behavior);
        sb.append(", logTime=").append(logTime);
        sb.append(", logText=").append(logText);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}