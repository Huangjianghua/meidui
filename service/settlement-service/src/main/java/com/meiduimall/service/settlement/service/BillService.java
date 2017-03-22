package com.meiduimall.service.settlement.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.meiduimall.service.settlement.model.EcmMzfBillWater;
import com.meiduimall.service.settlement.vo.BilledWaterVO2Merge;
import com.meiduimall.service.settlement.vo.OrderToBilledVO;

/**
 * @author 许彦 雄
 * @since 21.02.2017
 */
public interface BillService {
	
	/**
	 * @return Collection<String> orderSn collection
	 * @throws Exception
	 */
	public Collection<String> createBills() throws Exception;
	
	/**
	 * create bill water records.
	 * @param bill
	 * @param billCreatedtime
	 * @param billtime
	 * @throws Exception
	 */
	public void handleBill(EcmMzfBillWater bill,Date billCreatedtime,Date billtime,Timestamp opTime) throws Exception;
	
	/**
	 * build the relationship between billId and orderSn
	 * @param bill
	 * @param orderToBilledList
	 * @throws Exception
	 */
	public void createBillAndOrderMapping(EcmMzfBillWater bill,List<OrderToBilledVO> orderToBilledList) throws Exception;
	
	/**
	 * @param orderSnList
	 * @throws Exception
	 */
	public void updateOrderBillStatus(Collection<String> orderSnList) throws Exception;
	
	/**
	 * @throws Exception
	 */
	public void mergeBilledWaters(List<BilledWaterVO2Merge> waterVOList) throws Exception;
}
