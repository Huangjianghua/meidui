package com.meiduimall.service.settlement.service;

import java.util.Collection;
import java.util.List;

import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.EcmOrder;
import com.meiduimall.service.settlement.vo.ShareProfitVO;

/**
 * 
 * @author 许彦雄
 * 订单分润
 */
public interface OrderService {
	
	
	/**
	 * @param ecmOrder
	 * @param errors
	 * @return
	 * @throws Exception
	 */
	public EcmMzfShareProfit buildShareProfit(EcmOrder ecmOrder,Collection<String> errors) throws Exception;
	

	/**
	 * 根据订单号列表查询订单状态接口
	 * @param orderSns
	 * @return
	 * @throws Exception
	 * @author wujun
	 */
	 
	public List<EcmMzfOrderStatus> queryOrderStatus(List<String> orderSns) throws Exception;
	

	//public boolean shareProfit(EcmOrder ecmOrder) throws Exception;
	
	/**
	 * 为了解决 Spring声明式事务 同一类内该方法被saveShareProfit方法调用事务失效,需要抽出为接口
	 * @param shareProfit
	 * @param errors
	 * @throws Exception
	 */
	public void saveShareProfit(EcmMzfShareProfit shareProfit) throws Exception;
	

	/**
	 * 同步订单审核状态接口
	 * @param input
	 */
	public Boolean syncVerifyStatus(EcmMzfOrderStatus input) throws Exception;
	
	
	/**
	 * 根据订单号列表查询订单分润数据
	 * @param orderSns
	 * @return
	 * @throws Exception
	 */
	public List<EcmMzfShareProfit> queryShareProfit(Collection<String> orderSns) throws Exception;


	/**
	 * 按登陆的个代或区代查询今日佣金金额和待结算金额
	 * @param code
	 * @param accountRoleType
	 * @return
	 * @throws Exception
	 */
	public ShareProfitVO queryProfitByRole(String code, Integer accountRoleType) throws Exception;


	/**
	 * 按流水编号,
	 * @param waterId
	 * @param loginType
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<EcmMzfShareProfit> queryProfitByWaterByType(String waterId, Integer loginType, String code,Integer pageNumber,Integer pageSize)throws Exception;
	
	
	/**
	 * @param waterId
	 * @param loginType
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public int queryProfitCountByWaterId(String waterId)throws Exception;
	
/*	
	*//**
	 * @param shareProfit
	 * @param errors
	 * @param shareProfitSource
	 * @param retryType
	 * @throws Exception
	 *//*
	public void updateScore2MemberSystem(EcmMzfShareProfit shareProfit, String shareProfitSource,String retryType);*/


	/**
	 * 根据订单号查看分润数据是否存在
	 * @param orderSn
	 * @return
	 */
	public boolean checkShareProfitExisted(String orderSn)throws Exception;


}
