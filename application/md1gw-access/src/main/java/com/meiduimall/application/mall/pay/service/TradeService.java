package com.meiduimall.application.mall.pay.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.meiduimall.application.mall.pay.model.EctoolsPayments;
import com.meiduimall.application.mall.pay.model.EctoolsTradePaybill;
import com.meiduimall.application.mall.pay.model.SystradeLog;
import com.meiduimall.application.mall.pay.model.SystradeOrder;
import com.meiduimall.application.mall.pay.model.SystradePTrade;
import com.meiduimall.application.mall.pay.model.SystradePromotionDetail;
import com.meiduimall.application.mall.pay.model.SystradeTrade;

public interface TradeService {


	/**
	 * 获取订单金额
	 * @param tid tid
	 * @return map
	 */
	List<Map<String,Object>> getTradeMoney(String tid);
	
	/**
	 * 获取订单金额
	 * @param list list
	 * @return map
	 */
	List<Map<String,Object>> getTradeMoney(List<Object> list);
	
	/**
	 * 获取平台订单表信息
	 * @param platformId platformId
	 * @return SystradePTrade
	 */
	SystradePTrade getSystradePTrade(BigInteger platformId);
	
	/**
	 * 子订单获取所有商家订单号tid
	 * @param paymentId paymentId
	 * @return EctoolsTradePaybill
	 */
	List<EctoolsTradePaybill> listEctoolsTradePaybill(String paymentId);
	
	/**
	 * 更新子支付状态
	 * @param ectoolsTradePaybill ectoolsTradePaybill
	 * @return Integer
	 */
	Integer updateEctoolsTradePaybill(List<EctoolsTradePaybill> ectoolsTradePaybill);

	/**
	 * 清除ectools_trade_paybill表过期数据
	 * @param ectoolsTradePaybill ectoolsTradePaybill
	 * @return Integer
	 */
	Integer deleteEctoolsTradePaybill(List<EctoolsTradePaybill> ectoolsTradePaybill);
	
	/**
	 * 更新平台订单表第三方支付的款和状态
	 * @param systradePTrade systradePTrade
	 * @return Integer
	 */
	Integer updateSystradePTrade(SystradePTrade systradePTrade);
	
	/**
	 * 冻结余额更新到平台表
	 * @param systradePTrade
	 */
	Integer updateWalletPay(SystradePTrade systradePTrade);
	
	/**
	 * 把冻结的金额更新到平台订单表(systrade_p_trade)
	 * @param systradePTrade systradePTrade
	 * @return Integer
	 */
	Integer updateCSP(SystradePTrade systradePTrade);
	
	/**
	 * 更新支付单表
	 * @param s s
	 * @return Integer
	 */
	Integer updateEPStatus(EctoolsPayments s);
	
	/**
	 * 更新子支付单表
	 * @param s s
	 * @return Integer
	 */
	Integer updateETPStatus(EctoolsTradePaybill s);
	
    /**
     * 更新平台订单表
     * @param s s
     * @return Integer
     */
	Integer updateSPTStatus(SystradePTrade s);
	 
	/**
	 * 获取支付单号对应的所有商家订单信息
	 * @param i i
	 * @return SystradeTrade 
	 */
	List<SystradeTrade> listSystradeTrade(BigInteger i);
	
	/**
	 * 获取指定订单信息
	 * @param tid tid
	 * @return SystradeTrade
	 */
	SystradeTrade getTradeInfo(BigInteger tid);
	
	
	/**
	 * 【订单支付状态更改接口】
	 * @param trade 商品订单表信息
	 * @return Boolean
	 */
	Boolean tradePayFinish(SystradeTrade trade);
	
	/**
	 * 商品订单信息
	 * @param systradeOrder 商品订单信息
	 * @return SystradeOrder
	 */
	List<SystradeOrder> listTradeInfoOrder(SystradeOrder systradeOrder);

	/**
	 * 更新商家订单信息
	 * @param systradeTrade 商家订单信息
	 * @return Integer
	 */
	Integer updateSystradeTradeBytid(SystradeTrade systradeTrade);
	
	/**
	 * 更新商品订单信息
	 * @param systradeOrder systradeOrder
	 * @return Integer
	 */
	Integer updateSystradeOrderBytid(SystradeOrder systradeOrder);
	
	/**
	 * 记录订单操作日志
	 * @param systradeLog systradeLog
	 * @return Integer
	 */
	Integer insertSystradeLog(SystradeLog systradeLog);
	
	/**
	 * 更新平台订单信息,标识已同步订单到会员系统
	 * @param systradePTrade 平台订单信息
	 * @return Integer
	 */
	Integer updateIsSyncByplatformId(SystradePTrade systradePTrade);
	
	/**
	 * 获取订单列表接口
	 * @param list list
	 * @return map
	 */
	List<Map<String,Object>> listTrade(List<Object> list);
	
	/**
	 * 获取商品订单表信息列表
	 * @param list list
	 * @return map
	 */
	List<Map<String,Object>> listOrder(List<Object> list);
	
	/**
	 * 获取商品参加促销活动信息
	 * @param oid
	 * @return SystradePromotionDetail
	 */
	SystradePromotionDetail getpromotionActivityData(Long oid);
	
	/**
	 * 获取商家手机号
	 * @param tid 商家id
	 * @return map
	 */
	Map<String, String> getShopMobile(BigInteger tid);
	
	/**
	 * is_pay_succ
	 * @param ectoolsPayments 支付单信息
	 * @return EctoolsPayments
	 */
	EctoolsPayments getIsPaySucc(EctoolsPayments ectoolsPayments) ;
}
