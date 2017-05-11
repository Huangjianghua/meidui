package com.meiduimall.application.mall.pay.model;

import java.io.Serializable;

public class SysitemSkuStore implements Serializable {
    private Integer itemId;

    private Integer skuId;

    private Integer store;

    private Integer freez;

    
	public SysitemSkuStore() {
		super();
	}

	private static final long serialVersionUID = 1L;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
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
        sb.append(", skuId=").append(skuId);
        sb.append(", store=").append(store);
        sb.append(", freez=").append(freez);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}