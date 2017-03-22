package com.meiduimall.service.settlement.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.settlement.vo.BilledWaterVO2Merge;

public interface WaterService {

	/**
	 * 根据流水编号获取提现流水详情
	 * @param waterId
	 * @return
	 */
	public Map<String,Object> getWaterType1Detail(String waterId, String waterType) throws Exception;
	
	public Map<String,Object> getWaterDetail(String waterId, String waterType) throws Exception;
	
	
	
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<BilledWaterVO2Merge> getBilledWatersToMerge() throws Exception;
	
	
}
