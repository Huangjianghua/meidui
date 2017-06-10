package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.dto.ServiceToServiceDTO;
import com.meiduimall.service.account.model.MSConsumePointsDetail;
import com.meiduimall.service.account.model.MSConsumePointsDetailGet;

/**
 * 积分明细业务逻辑接口
 * @author chencong
 *
 */
public interface ConsumePointsDetailService {
	
	/**
	 * 积分 流水接口  分页  
	 * @param mSConsumePointsDetail
	 * @return
	 * @throws Exception
	 */
	public List<MSConsumePointsDetail> listMSConsumePointsDetail(MSConsumePointsDetailGet mSConsumePointsDetail) throws Exception;
	
	/**
	 * 方法名: addMDConsumePointsAndDetail<br>
	 * 描述:  增加美兑积分并写入日志<br>
	 * 创建时间: 2016-11-18
	 * @param memId
	 * @param consumePoints
	 * @param orderId
	 * @param orderSource
	 * @param operatorType
	 * @param operator
	 * @param remark
	 * @return
	 * @throws MdSysException
	 */
	public boolean addMDConsumePointsAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark) throws MdSysException;


	void getConsumePointsDetail(ServiceToServiceDTO dto) throws Exception;

	void insertConsumePointsDetail(String memId, String orderId, String orderSource, String inConsumePoints,
			String outConsumePoints, String balancePoints, String operatorType, String operator, String remark)
			throws MdSysException;
}
