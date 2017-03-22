package com.meiduimall.service.settlement.service;

import java.util.Collection;

import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;

public interface OrderStatusService {
	
	/**
	 * 更新订单分润状态
	 * @param orderSn
	 * @return
	 * @throws Exception
	 */
	public boolean updateShareStatus(String orderSn)throws Exception;
	
	/**
	 * 更新订单积分是否成功送出
	 * @param orderSn
	 * @return
	 * @throws Exception
	 */
	public boolean updateScoreStatus(String orderSn)throws Exception;
	
	/**
	 * 更新账单是否创建成功状态
	 * @param orderStatus
	 * @return
	 * @throws Exception
	 */
	public boolean updateBillStatus(EcmMzfOrderStatus orderStatus)throws Exception;
	
	/**
	 * 更新一级推荐人1%现金余额是否成功送出状态
	 * @param orderSn
	 * @return
	 * @throws Exception
	 */
	public boolean updateCashStatus(String orderSn)throws Exception;
	
	
	//注意：好像如果orderSns.size()>5000, in update 语句好像或报错。将来最好把orderSns进行分割，使每次orderSns小于5000
	/**
	 * 批量更新一级推荐人1%现金余额是否成功送出状态 
	 * @param orderSns
	 * @return
	 * @throws Exception
	 */
	public boolean batchUpdCashStatus(Collection<String> orderSns)throws Exception;
	

	
}
