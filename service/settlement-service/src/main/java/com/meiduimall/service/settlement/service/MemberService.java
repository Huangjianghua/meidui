package com.meiduimall.service.settlement.service;

import java.util.List;

import com.meiduimall.service.settlement.model.EcmMzfShareProfit;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: MemberService.java
 * Author:   不详
 * Date:     2017年3月14日 下午3:37:58
 * Description: 会员系统送积分或现金奖励服务
 */
public interface MemberService {
	
	/**
	 * 功能描述:  更新用户积分（对接的壹购物那边的）
	 * Author: 
	 * Date:   2016年12月14日 下午3:38:26
	 * param phone
	 * param credit
	 * param source 数据来源：1gw-壹购物，o2o-线下O2O，app-手机APP，md-美兑系统,md1gw-美兑壹购物
	 * param order_id 流水号: 系统简称+代理编号+当前时间秒值，比如：DL+b001+1478260021
	 * return  boolean
	 * throws Exception
	 */
    Boolean addConsumePoints(String phone,String credit,String source,String order_id) throws Exception;

	/**
	 * 功能描述:  给会员系统送积分
	 * Author: 许彦雄
	 * Date:   2017年2月20日 下午3:38:26
	 * param shareProfit
	 * return  List<String>
	 */
    public List<String> sendScore(EcmMzfShareProfit shareProfit);

	/**
	 * 功能描述:  更新一级推荐人1%现金余额到会员系统
	 * Author: 许彦雄
	 * Date:   2017年2月20日 下午3:38:26
	 * return  
	 * throws Exception
	 */
    public void updateReferrerCash() throws Exception;
}