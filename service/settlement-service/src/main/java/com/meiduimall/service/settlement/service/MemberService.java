package com.meiduimall.service.settlement.service;

import java.util.List;

import com.meiduimall.service.settlement.model.EcmMzfShareProfit;

public interface MemberService {
	
	/**
	 * 更新用户积分（对接的壹购物那边的）
	 * @param phone
	 * @param credit
	 * @param source 数据来源：1gw-壹购物，o2o-线下O2O，app-手机APP，md-美兑系统,md1gw-美兑壹购物
	 * @param order_id 流水号: 系统简称+代理编号+当前时间秒值，比如：DL+b001+1478260021
	 * @return
	 */
    Boolean addConsumePoints(String phone,String credit,String source,String order_id) throws Exception;
    
    /**
     * 更新一级推荐人所获现金金额到会员系统
     * @param ctx 参数详见  会员结算系统外部接口 6.11 账户余额变动接口
     * @return
     */
    
   // Boolean updateAmout2MemberSystem(MemberSystemDataContext ctx) throws Exception;
    

    /**
     * 给会员 系统送积分 
     * @param shareProfit
     * @return List<String> error messages
     */
    public List<String> sendScore(EcmMzfShareProfit shareProfit);
    
    
    
    /**
     * 更新一级推荐人1%现金余额到会员系统
     * @return
     * @throws Exception
     */
    public void updateReferrerCash() throws Exception;
}
