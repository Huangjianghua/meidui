package com.meiduimall.application.search.pojo;

import java.util.Date;

public class PackageIntegral {
    private String pId;

    private String pProductId;

    private String pName;

    private Integer pType;

    private String pCardIssuer;

    private Double pActualPrice;

    private Double pActualSectionS;

    private Double pActualSectionE;

    private Double pInprice;

    private Double pSalePrice;

    private Double pBackIntegral;

    private Double pBackProportion;

    private Date pCreatedDate;

    private String pRemark;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getpProductId() {
        return pProductId;
    }

    public void setpProductId(String pProductId) {
        this.pProductId = pProductId == null ? null : pProductId.trim();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public Integer getpType() {
        return pType;
    }

    public void setpType(Integer pType) {
        this.pType = pType;
    }

    public String getpCardIssuer() {
        return pCardIssuer;
    }

    public void setpCardIssuer(String pCardIssuer) {
        this.pCardIssuer = pCardIssuer == null ? null : pCardIssuer.trim();
    }

    public Double getpActualPrice() {
        return pActualPrice;
    }

    public void setpActualPrice(Double pActualPrice) {
        this.pActualPrice = pActualPrice;
    }

    public Double getpActualSectionS() {
        return pActualSectionS;
    }

    public void setpActualSectionS(Double pActualSectionS) {
        this.pActualSectionS = pActualSectionS;
    }

    public Double getpActualSectionE() {
        return pActualSectionE;
    }

    public void setpActualSectionE(Double pActualSectionE) {
        this.pActualSectionE = pActualSectionE;
    }

    public Double getpInprice() {
        return pInprice;
    }

    public void setpInprice(Double pInprice) {
        this.pInprice = pInprice;
    }

    public Double getpSalePrice() {
        return pSalePrice;
    }

    public void setpSalePrice(Double pSalePrice) {
        this.pSalePrice = pSalePrice;
    }

    public Double getpBackIntegral() {
        return pBackIntegral;
    }

    public void setpBackIntegral(Double pBackIntegral) {
        this.pBackIntegral = pBackIntegral;
    }

    public Double getpBackProportion() {
        return pBackProportion;
    }

    public void setpBackProportion(Double pBackProportion) {
        this.pBackProportion = pBackProportion;
    }

    public Date getpCreatedDate() {
        return pCreatedDate;
    }

    public void setpCreatedDate(Date pCreatedDate) {
        this.pCreatedDate = pCreatedDate;
    }

    public String getpRemark() {
        return pRemark;
    }

    public void setpRemark(String pRemark) {
        this.pRemark = pRemark == null ? null : pRemark.trim();
    }
    
    /**
     * 
     * @param obj
     * @return
     */
    @Override
	public boolean equals(Object obj) {
		if(obj == null){  
            return false;  
        }else {           
                if(this.getClass() == obj.getClass()){  
                    PackageIntegral p = (PackageIntegral) obj;  
                    if(this.getpActualPrice() == p.getpActualPrice()
                    		&& this.getpActualSectionE() == p.getpActualSectionE()
                    		&& this.getpActualSectionS() == p.getpActualSectionS()
                    		&& this.getpBackIntegral() == p.getpBackIntegral()
                    		&& this.getpBackProportion() == p.getpBackProportion()
                    		&& this.getpCardIssuer().equals(p.getpCardIssuer())
                    		&& this.getpCreatedDate().getTime() == p.getpCreatedDate().getTime()
                    		&& this.getpId().equals(p.getpId())
                    		&& this.getpInprice() == p.getpInprice()
                    		&& this.getpName().equals(p.getpName())
                    		&& this.getpProductId().equals(p.getpProductId())
                    		&& this.getpSalePrice() == p.getpSalePrice()
                    		&& this.getpType() == p.getpType()) {
                    	return true;
                    } else {
                    	return false;
                    }
                  
            }else{  
                return false;  
            }  
        }     
	}
}