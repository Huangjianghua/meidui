package com.meiduimall.application.search.manage.pojo;

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
     * @author Liujun
     * @date 2016-04-18
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
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pActualPrice == null) ? 0 : pActualPrice.hashCode());
		result = prime * result + ((pActualSectionE == null) ? 0 : pActualSectionE.hashCode());
		result = prime * result + ((pActualSectionS == null) ? 0 : pActualSectionS.hashCode());
		result = prime * result + ((pBackIntegral == null) ? 0 : pBackIntegral.hashCode());
		result = prime * result + ((pBackProportion == null) ? 0 : pBackProportion.hashCode());
		result = prime * result + ((pCardIssuer == null) ? 0 : pCardIssuer.hashCode());
		result = prime * result + ((pCreatedDate == null) ? 0 : pCreatedDate.hashCode());
		result = prime * result + ((pId == null) ? 0 : pId.hashCode());
		result = prime * result + ((pInprice == null) ? 0 : pInprice.hashCode());
		result = prime * result + ((pName == null) ? 0 : pName.hashCode());
		result = prime * result + ((pProductId == null) ? 0 : pProductId.hashCode());
		result = prime * result + ((pRemark == null) ? 0 : pRemark.hashCode());
		result = prime * result + ((pSalePrice == null) ? 0 : pSalePrice.hashCode());
		result = prime * result + ((pType == null) ? 0 : pType.hashCode());
		return result;
	}
    
}