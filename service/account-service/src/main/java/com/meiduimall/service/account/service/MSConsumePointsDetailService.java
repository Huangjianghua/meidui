package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.dto.ServiceToServiceDTO;
import com.meiduimall.service.account.model.MSConsumePointsDetail;
import com.meiduimall.service.account.model.MSConsumePointsDetailGet;


public interface MSConsumePointsDetailService {

	/**
	 * 方法名: saveAddConsumePoints<br>
	 * 描述:  保存会员积分明细，交易收入时调用<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-28
	 * @param memId
	 * @param orderId
	 * @param orderSource
	 * @param consumePoints
	 * @param operatorType
	 * @param operator
	 * @param remark
	 */
	public void saveAddConsumePoints(String memId, String orderId,String orderSource, String consumePoints, String operatorType,String operator, String remark) throws Exception;
	
	/**
	 * 方法名: saveConsumePoints<br>
	 * 描述:  保存会员积分明细，不需要计算余额,由调用方给出<br>
	 * 创建时间: 2016-11-18
	 * @param memId
	 * @param orderId
	 * @param orderSource
	 * @param inConsumePoints
	 * @param outConsumePoints
	 * @param balancePoints
	 * @param operatorType
	 * @param operator
	 * @param remark
	 */
	public void saveConsumePoints(String memId, String orderId,String orderSource, String inConsumePoints,String outConsumePoints, String balancePoints, String operatorType,String operator, String remark);
	
	/**
	 * 方法名: getConsumePointsDetail<br>
	 * 描述:  查询积分明细，接口使用<br>
	 * 创建时间: 2016-12-2
	 * @param dto
	 * @throws Exception 
	 */
	public void getConsumePointsDetail(ServiceToServiceDTO dto) throws Exception;
	
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
}
