package com.meiduimall.service.settlement.model;

/**
 * 商家奖励金记录
 * @author guidl
 *
 */
public class EcmMzfSellerBonus extends EcmMzfSellerFee {
	
	private static final long serialVersionUID = 6530725195847826338L;
	
	//平台服务费表主键id
	private int extId;

	public int getExtId() {
		return extId;
	}

	public void setExtId(int extId) {
		this.extId = extId;
	}
	
}
