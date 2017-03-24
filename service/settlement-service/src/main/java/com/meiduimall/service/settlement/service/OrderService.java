package com.meiduimall.service.settlement.service;

import java.util.Collection;
import java.util.List;

import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.EcmOrder;
import com.meiduimall.service.settlement.vo.ShareProfitVO;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: OrderService.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: 订单分润和分润数据查询相关服务
 */
public interface OrderService {
	
	/**
	 * 功能描述:  构建订单分润数据模型
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param ecmOrder
	 * param errors
	 * return  EcmMzfShareProfit
	 * throws Exception
	 */
	public EcmMzfShareProfit buildShareProfit(EcmOrder ecmOrder,Collection<String> errors) throws Exception;

	/**
	 * 功能描述:  根据订单号列表查询订单状态
	 * Author: 吴军
	 * Date:   2017年3月14日 下午3:38:26   
	 * param orderSns
	 * return  List<EcmMzfOrderStatus>
	 * throws Exception
	 */
	public List<EcmMzfOrderStatus> queryOrderStatus(List<String> orderSns) throws Exception;

	/**
	 * 功能描述:  保存分润数据(为了解决 Spring声明式事务 同一类内该方法被saveShareProfit方法调用事务失效,需要抽出为接口)
	 * Author: 许彦雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param shareProfit
	 * return 
	 * throws Exception
	 */
	public void saveShareProfit(EcmMzfShareProfit shareProfit) throws Exception;
	
	/**
	 * 功能描述:  同步订单审核状态接口
	 * Author: 许彦雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param orderStatus
	 * return  Boolean
	 * throws Exception
	 */
	public Boolean syncVerifyStatus(EcmMzfOrderStatus orderStatus) throws Exception;
	

	/**
	 * 功能描述:  根据订单号列表查询订单分润数据
	 * Author: 许彦雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param orderSns
	 * return  List<EcmMzfShareProfit>
	 * throws Exception
	 */
	public List<EcmMzfShareProfit> queryShareProfit(Collection<String> orderSns) throws Exception;
	
	/**
	 * 功能描述:  按登陆的个代或区代查询今日佣金金额和待结算金额
	 * Author: 许彦雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param code
	 * param accountRoleType
	 * return  ShareProfitVO
	 * throws Exception
	 */
	public ShareProfitVO queryProfitByRole(String code, Integer accountRoleType) throws Exception;

	/**
	 * 功能描述:  按登陆的个代或区代以及流水编号查询分润数据
	 * Author: 许彦雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param waterId
	 * param loginType
	 * param code
	 * param pageNumber
	 * param pageSize
	 * return  List<EcmMzfShareProfit>
	 * throws Exception
	 */
	public List<EcmMzfShareProfit> queryProfitByWaterByType(String waterId, Integer loginType, String code,Integer pageNumber,Integer pageSize)throws Exception;
	
	/**
	 * 功能描述:  按流水编号查询分润数据记录数
	 * Author: 许彦雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param waterId
	 * return  int
	 * throws Exception
	 */
	public int queryProfitCountByWaterId(String waterId)throws Exception;

	/**
	 * 功能描述:  根据订单号查询分润数据是否存在
	 * Author: 许彦雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param orderSn
	 * return  boolean
	 * throws Exception
	 */
	public boolean checkShareProfitExisted(String orderSn)throws Exception;


}