package com.meiduimall.application.mall.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.meiduimall.application.mall.model.EctoolsPayments;
import com.meiduimall.application.mall.model.EctoolsTradePaybill;
import com.meiduimall.application.mall.model.SystradeLog;
import com.meiduimall.application.mall.model.SystradeOrder;
import com.meiduimall.application.mall.model.SystradePTrade;
import com.meiduimall.application.mall.model.SystradePromotionDetail;
import com.meiduimall.application.mall.model.SystradeTrade;

public interface TradeService {


	/**
	 * 获取订单金额
	 * @param tid
	 * @return
	 * @
	 */
	List<Map<String,Object>> getTradeMoney(String tid);
	
	/**
	 * 获取订单金额
	 * @param tid
	 * @return
	 * @
	 */
	List<Map<String,Object>> getTradeMoney(List<Object> list);
	
	/**
	 * 获取平台订单表信息
	 * @param platformId
	 * @return
	 * @
	 */
	SystradePTrade getSystradePTrade(BigInteger platformId);
	
	/**
	 * 子订单获取所有商家订单号tid
	 * @param paymentId
	 * @return
	 * @
	 */
	List<EctoolsTradePaybill> listEctoolsTradePaybill(String paymentId);
	
	/**
	 * 更新子支付状态
	 * @param ectoolsTradePaybill
	 * @
	 */
	Integer updateEctoolsTradePaybill(List<EctoolsTradePaybill> ectoolsTradePaybill);

	/**
	 * 清除ectools_trade_paybill表过期数据
	 * @param deleteParams
	 */
	Integer deleteEctoolsTradePaybill(List<EctoolsTradePaybill> ectoolsTradePaybill);
	
	/**
	 * 更新平台订单表第三方支付的款和状态
	 * @param systradePTrade
	 * @
	 */
	Integer updateSystradePTrade(SystradePTrade systradePTrade);
	
	/**
	 * 冻结余额更新到平台表
	 * @param systradePTrade
	 * @
	 */
	Integer updateWalletPay(SystradePTrade systradePTrade);
	
	/**
	 * 把冻结的金额更新到平台订单表(systrade_p_trade)
	 * @param systradePTrade
	 * @
	 */
	Integer updateCSP(SystradePTrade systradePTrade);
	
	/**
	 * 更新支付单表
	 * @param s
	 * @
	 */
	Integer updateEPStatus(EctoolsPayments s);
	
	/**
	 * 更新子支付单表
	 * @param s
	 * @
	 */
	Integer updateETPStatus(EctoolsTradePaybill s);
	
    /**
     * 更新平台订单表
     * @param s
     * @
     */
	Integer updateSPTStatus(SystradePTrade s);
	 
	/**
	 * 获取支付单号对应的所有商家订单信息
	 * @param i
	 * @return
	 * @
	 */
	List<SystradeTrade> listSystradeTrade(BigInteger i);
	
	/**
	 * 获取指定订单信息
	 * @param tid
	 * @return
	 * @
	 */
	SystradeTrade getTradeInfo(BigInteger tid);
	
	
	/**
	 * 【订单支付状态更改接口】
	 * @param tradelist
	 * @
	 */
	Boolean TradePayFinish(SystradeTrade trade)throws Exception;
	
	/**
	 * 商品订单信息
	 * @param systradeOrder
	 * @return
	 * @
	 */
	List<SystradeOrder> listTradeInfoOrder(SystradeOrder systradeOrder);

	/**
	 * 更新商家订单信息
	 * @param systradeTrade
	 * @return
	 */
	Integer updateSystradeTradeBytid(SystradeTrade systradeTrade);
	
	/**
	 * 更新商品订单信息
	 * @param systradeOrder
	 * @return
	 * @
	 */
	Integer updateSystradeOrderBytid(SystradeOrder systradeOrder);
	
	/**
	 * 记录订单操作日志
	 * @param systradeLog
	 * @return
	 * @
	 */
	Integer insertSystradeLog(SystradeLog systradeLog);
	
	/**
	 * 更新平台订单信息,标识已同步订单到会员系统
	 * @param systradePTrade
	 * @return
	 * @
	 */
	Integer updateIsSyncByplatformId(SystradePTrade systradePTrade);
	
	/**
	 * 获取订单列表接口
	 * @return
	 * @
	 */
	List<Map<String,Object>> listTrade(List<Object> list);
	
	/**
	 * 获取商品订单表信息列表
	 * @param list
	 * @return
	 * @
	 */
	List<Map<String,Object>> listOrder(List<Object> list);
	
	/**
	 * 获取商品参加促销活动信息
	 * @param list
	 * @return
	 * @
	 */
	SystradePromotionDetail getpromotionActivityData(Long oid);
	
	/**
	 * 获取商家手机号
	 * @param tid
	 * @return
	 * @
	 */
	Map<String, String> getShopMobile(BigInteger tid);
	
	/**
	 * is_pay_succ
	 * @param ectoolsPayments
	 * @return
	 * @
	 */
	EctoolsPayments getIsPaySucc(EctoolsPayments ectoolsPayments) ;
}
