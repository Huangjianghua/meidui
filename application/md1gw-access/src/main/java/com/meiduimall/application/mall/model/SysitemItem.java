package com.meiduimall.application.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysitemItem implements Serializable {
    private Integer itemId;

    private Integer shopId;

    private Integer catId;

    private Integer brandId;

    private String shopCatId;

    private String title;

    private String subTitle;

    private String bn;

    private BigDecimal price;

    private BigDecimal costPrice;

    private BigDecimal mktPrice;

    private BigDecimal weight;

    private String imageDefaultId;

    private Integer store;

    private Integer freez;

    private Integer orderSort;

    private Integer modifiedTime;

    private Boolean hasDiscount;

    private Boolean isVirtual;

    private Boolean isTiming;

    private Boolean violation;

    private Boolean isSelfshop;

    private Boolean nospec;

    private Integer subStock;

    private String outerId;

    private Boolean isOffline;

    private String barcode;

    private Boolean disabled;

    private String usePlatform;

    private Boolean isScore;

    private BigDecimal score;

    private BigDecimal rewardScore;

    private String checkStatus;

    private String supplyType;

    private Boolean isbreak;

    private Boolean isdirect;

    private String throughReason;

    private Integer point;

    private Boolean isShowWeight;

    private String listImage;

    private String specDesc;

    private String propsName;

    private String params;

    private static final long serialVersionUID = 1L;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getShopCatId() {
        return shopCatId;
    }

    public void setShopCatId(String shopCatId) {
        this.shopCatId = shopCatId == null ? null : shopCatId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public String getBn() {
        return bn;
    }

    public void setBn(String bn) {
        this.bn = bn == null ? null : bn.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMktPrice() {
        return mktPrice;
    }

    public void setMktPrice(BigDecimal mktPrice) {
        this.mktPrice = mktPrice;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getImageDefaultId() {
        return imageDefaultId;
    }

    public void setImageDefaultId(String imageDefaultId) {
        this.imageDefaultId = imageDefaultId == null ? null : imageDefaultId.trim();
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

    public Integer getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(Integer orderSort) {
        this.orderSort = orderSort;
    }

    public Integer getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Integer modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Boolean getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Boolean isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Boolean getIsTiming() {
        return isTiming;
    }

    public void setIsTiming(Boolean isTiming) {
        this.isTiming = isTiming;
    }

    public Boolean getViolation() {
        return violation;
    }

    public void setViolation(Boolean violation) {
        this.violation = violation;
    }

    public Boolean getIsSelfshop() {
        return isSelfshop;
    }

    public void setIsSelfshop(Boolean isSelfshop) {
        this.isSelfshop = isSelfshop;
    }

    public Boolean getNospec() {
        return nospec;
    }

    public void setNospec(Boolean nospec) {
        this.nospec = nospec;
    }

    public Integer getSubStock() {
        return subStock;
    }

    public void setSubStock(Integer subStock) {
        this.subStock = subStock;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId == null ? null : outerId.trim();
    }

    public Boolean getIsOffline() {
        return isOffline;
    }

    public void setIsOffline(Boolean isOffline) {
        this.isOffline = isOffline;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getUsePlatform() {
        return usePlatform;
    }

    public void setUsePlatform(String usePlatform) {
        this.usePlatform = usePlatform == null ? null : usePlatform.trim();
    }

    public Boolean getIsScore() {
        return isScore;
    }

    public void setIsScore(Boolean isScore) {
        this.isScore = isScore;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getRewardScore() {
        return rewardScore;
    }

    public void setRewardScore(BigDecimal rewardScore) {
        this.rewardScore = rewardScore;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    public String getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(String supplyType) {
        this.supplyType = supplyType == null ? null : supplyType.trim();
    }

    public Boolean getIsbreak() {
        return isbreak;
    }

    public void setIsbreak(Boolean isbreak) {
        this.isbreak = isbreak;
    }

    public Boolean getIsdirect() {
        return isdirect;
    }

    public void setIsdirect(Boolean isdirect) {
        this.isdirect = isdirect;
    }

    public String getThroughReason() {
        return throughReason;
    }

    public void setThroughReason(String throughReason) {
        this.throughReason = throughReason == null ? null : throughReason.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Boolean getIsShowWeight() {
        return isShowWeight;
    }

    public void setIsShowWeight(Boolean isShowWeight) {
        this.isShowWeight = isShowWeight;
    }

    public String getListImage() {
        return listImage;
    }

    public void setListImage(String listImage) {
        this.listImage = listImage == null ? null : listImage.trim();
    }

    public String getSpecDesc() {
        return specDesc;
    }

    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc == null ? null : specDesc.trim();
    }

    public String getPropsName() {
        return propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName == null ? null : propsName.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", shopId=").append(shopId);
        sb.append(", catId=").append(catId);
        sb.append(", brandId=").append(brandId);
        sb.append(", shopCatId=").append(shopCatId);
        sb.append(", title=").append(title);
        sb.append(", subTitle=").append(subTitle);
        sb.append(", bn=").append(bn);
        sb.append(", price=").append(price);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", mktPrice=").append(mktPrice);
        sb.append(", weight=").append(weight);
        sb.append(", imageDefaultId=").append(imageDefaultId);
        sb.append(", store=").append(store);
        sb.append(", freez=").append(freez);
        sb.append(", orderSort=").append(orderSort);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", hasDiscount=").append(hasDiscount);
        sb.append(", isVirtual=").append(isVirtual);
        sb.append(", isTiming=").append(isTiming);
        sb.append(", violation=").append(violation);
        sb.append(", isSelfshop=").append(isSelfshop);
        sb.append(", nospec=").append(nospec);
        sb.append(", subStock=").append(subStock);
        sb.append(", outerId=").append(outerId);
        sb.append(", isOffline=").append(isOffline);
        sb.append(", barcode=").append(barcode);
        sb.append(", disabled=").append(disabled);
        sb.append(", usePlatform=").append(usePlatform);
        sb.append(", isScore=").append(isScore);
        sb.append(", score=").append(score);
        sb.append(", rewardScore=").append(rewardScore);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", supplyType=").append(supplyType);
        sb.append(", isbreak=").append(isbreak);
        sb.append(", isdirect=").append(isdirect);
        sb.append(", throughReason=").append(throughReason);
        sb.append(", point=").append(point);
        sb.append(", isShowWeight=").append(isShowWeight);
        sb.append(", listImage=").append(listImage);
        sb.append(", specDesc=").append(specDesc);
        sb.append(", propsName=").append(propsName);
        sb.append(", params=").append(params);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}