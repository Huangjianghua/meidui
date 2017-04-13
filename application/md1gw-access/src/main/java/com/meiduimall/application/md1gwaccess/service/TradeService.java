package com.meiduimall.application.md1gwaccess.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.meiduimall.application.md1gwaccess.model.EctoolsPayments;
import com.meiduimall.application.md1gwaccess.model.EctoolsTradePaybill;
import com.meiduimall.application.md1gwaccess.model.SystradeLog;
import com.meiduimall.application.md1gwaccess.model.SystradeOrder;
import com.meiduimall.application.md1gwaccess.model.SystradePTrade;
import com.meiduimall.application.md1gwaccess.model.SystradePromotionDetail;
import com.meiduimall.application.md1gwaccess.model.SystradeTrade;

public interface TradeService {


	/**
	 * 获取订单金额
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	List<Map<String,Object>> getTradeMoney(String tid)throws Exception;
	
	/**
	 * 获取订单金额
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	List<Map<String,Object>> getTradeMoney(List<Object> list)throws Exception;
	
	/**
	 * 获取平台订单表信息
	 * @param platformId
	 * @return
	 * @throws Exception
	 */
	SystradePTrade getSystradePTrade(BigInteger platformId)throws Exception;
	
	/**
	 * 子订单获取所有商家订单号tid
	 * @param paymentId
	 * @return
	 * @throws Exception
	 */
	List<EctoolsTradePaybill> listEctoolsTradePaybill(String paymentId)throws Exception;
	
	/**
	 * 更新子支付状态
	 * @param ectoolsTradePaybill
	 * @throws Exception
	 */
	Integer updateEctoolsTradePaybill(List<EctoolsTradePaybill> ectoolsTradePaybill)throws Exception;

	/**
	 * 清除ectools_trade_paybill表过期数据
	 * @param deleteParams
	 */
	Integer deleteEctoolsTradePaybill(List<EctoolsTradePaybill> ectoolsTradePaybill)throws Exception;
	
	/**
	 * 更新平台订单表第三方支付的款和状态
	 * @param systradePTrade
	 * @throws Exception
	 */
	Integer updateSystradePTrade(SystradePTrade systradePTrade)throws Exception;
	
	/**
	 * 冻结余额更新到平台表
	 * @param systradePTrade
	 * @throws Exception
	 */
	Integer updateWalletPay(SystradePTrade systradePTrade)throws Exception;
	
	/**
	 * 把冻结的金额更新到平台订单表(systrade_p_trade)
	 * @param systradePTrade
	 * @throws Exception
	 */
	Integer updateCSP(SystradePTrade systradePTrade)throws Exception;
	
	/**
	 * 更新支付单表
	 * @param s
	 * @throws Exception
	 */
	Integer updateEPStatus(EctoolsPayments s)throws Exception;
	
	/**
	 * 更新子支付单表
	 * @param s
	 * @throws Exception
	 */
	Integer updateETPStatus(EctoolsTradePaybill s)throws Exception;
	
    /**
     * 更新平台订单表
     * @param s
     * @throws Exception
     */
	Integer updateSPTStatus(SystradePTrade s)throws Exception;
	 
	/**
	 * 获取支付单号对应的所有商家订单信息
	 * @param i
	 * @return
	 * @throws Exception
	 */
	List<SystradeTrade> listSystradeTrade(BigInteger i)throws Exception;
	
	/**
	 * 获取指定订单信息
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	SystradeTrade getTradeInfo(BigInteger tid)throws Exception;
	
	
	/**
	 * 【订单支付状态更改接口】
	 * @param tradelist
	 * @throws Exception
	 */
	Boolean TradePayFinish(SystradeTrade trade)throws Exception;
	
	/**
	 * 商品订单信息
	 * @param systradeOrder
	 * @return
	 * @throws Exception
	 */
	List<SystradeOrder> listTradeInfoOrder(SystradeOrder systradeOrder)throws Exception;

	/**
	 * 更新商家订单信息
	 * @param systradeTrade
	 * @return
	 */
	Integer updateSystradeTradeBytid(SystradeTrade systradeTrade)throws Exception;
	
	/**
	 * 更新商品订单信息
	 * @param systradeOrder
	 * @return
	 * @throws Exception
	 */
	Integer updateSystradeOrderBytid(SystradeOrder systradeOrder)throws Exception;
	
	/**
	 * 记录订单操作日志
	 * @param systradeLog
	 * @return
	 * @throws Exception
	 */
	Integer insertSystradeLog(SystradeLog systradeLog)throws Exception;
	
	/**
	 * 更新平台订单信息,标识已同步订单到会员系统
	 * @param systradePTrade
	 * @return
	 * @throws Exception
	 */
	Integer updateIsSyncByplatformId(SystradePTrade systradePTrade)throws Exception;
	
	/**
	 * 获取订单列表接口
	 * @return
	 * @throws Exception
	 */
	List<Map<String,Object>> listTrade(List<Object> list)throws Exception;
	
	/**
	 * 获取商品订单表信息列表
	 * @param list
	 * @return
	 * @throws Exception
	 */
	List<Map<String,Object>> listOrder(List<Object> list)throws Exception;
	
	/**
	 * 获取商品参加促销活动信息
	 * @param list
	 * @return
	 * @throws Exception
	 */
	SystradePromotionDetail getpromotionActivityData(Long oid)throws Exception;
	
	/**
	 * 获取商家手机号
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	Map<String, String> getShopMobile(BigInteger tid)throws Exception;
	
	/**
	 * is_pay_succ
	 * @param ectoolsPayments
	 * @return
	 * @throws Exception
	 */
	EctoolsPayments getIsPaySucc(EctoolsPayments ectoolsPayments) throws Exception;
}
