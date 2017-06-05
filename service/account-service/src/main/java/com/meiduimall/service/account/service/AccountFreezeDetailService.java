package com.meiduimall.service.account.service;


import java.util.List;
import com.meiduimall.service.account.model.MSAccountFreezeDetail;

/**
 * 账户冻结解冻明细业务逻辑接口
 * @author chencong
 *
 */
public interface AccountFreezeDetailService {	
	
	/**
	 * 根据订单号查询余额冻结解冻记录
	 * @param orderId 订单号
	 * @return 冻结解冻记录列表
	 */
	public List<MSAccountFreezeDetail> getRecordsByOrderId(String orderId);
	
}
