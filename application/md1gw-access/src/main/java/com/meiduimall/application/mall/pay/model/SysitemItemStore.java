package com.meiduimall.application.mall.pay.model;

import java.io.Serializable;

public class SysitemItemStore implements Serializable {
    private Integer itemId;

    private Integer store;

    private Integer freez;

    private static final long serialVersionUID = 1L;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getFreez() {
        return freez;
    }

    public void setFreez(Integer freez) {
        this.freez = freez;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", store=").append(store);
        sb.append(", freez=").append(freez);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}