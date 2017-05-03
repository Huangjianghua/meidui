package com.meiduimall.application.mall.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meiduimall.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.meiduimall.application.mall.constant.SysParaNameConst;
import com.meiduimall.application.mall.dao.BaseMapper;
import com.meiduimall.application.mall.exception.MallApiCode;
import com.meiduimall.application.mall.model.EctoolsPayments;
import com.meiduimall.application.mall.model.EctoolsTradePaybill;
import com.meiduimall.application.mall.model.SysitemItem;
import com.meiduimall.application.mall.model.SysitemItemStore;
import com.meiduimall.application.mall.model.SysitemSku;
import com.meiduimall.application.mall.model.SysitemSkuStore;
import com.meiduimall.application.mall.model.SystradeLog;
import com.meiduimall.application.mall.model.SystradeOrder;
import com.meiduimall.application.mall.model.SystradePTrade;
import com.meiduimall.application.mall.model.SystradePromotionDetail;
import com.meiduimall.application.mall.model.SystradeTrade;
import com.meiduimall.application.mall.service.SysitemService;
import com.meiduimall.application.mall.service.TradeService;
import com.meiduimall.application.mall.util.Logger;

@Component
public class TradeServiceImpl implements TradeService {

	@Autowired
	private BaseMapper baseMapper;

	@Autowired
	private SysitemService sysitemService;

	@Override
	public List<Map<String, Object>> getTradeMoney(String tid) {
		String[] split = tid.split(",");
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			list.add(split[i]);
		}

		return baseMapper.selectList(ImmutableMap.of("tid", list), "SystradeTradeMapper.getTradeMoney");

	}

	@Override
	public List<Map<String, Object>> getTradeMoney(List<Object> list) {

		return baseMapper.selectList(ImmutableMap.of("tid", list), "SystradeTradeMapper.getTradeMoney");

	}

	@Override
	public SystradePTrade getSystradePTrade(BigInteger platformId) {

		return baseMapper.selectOne(platformId, "SystradePTradeMapper.getSystradePTrade");

	}

	@Override
	public List<EctoolsTradePaybill> listEctoolsTradePaybill(String paymentId) {

		return baseMapper.selectList(paymentId, "EctoolsTradePaybillMapper.listEctoolsTradePaybill");

	}

	@Override
	public Integer updateEctoolsTradePaybill(List<EctoolsTradePaybill> ectoolsTradePaybill) {

		return baseMapper.update(ectoolsTradePaybill, "EctoolsTradePaybillMapper.updateEctoolsTradePaybill");

	}

	@Override
	public Integer deleteEctoolsTradePaybill(List<EctoolsTradePaybill> ectoolsTradePaybill) {

		return baseMapper.delete(ectoolsTradePaybill, "EctoolsTradePaybillMapper.deleteEctoolsTradePaybill");

	}

	@Override
	public Integer updateSystradePTrade(SystradePTrade systradePTrade) {

		return baseMapper.update(systradePTrade, "SystradePTradeMapper.updateSystradePTrade");

	}

	@Override
	public Integer updateWalletPay(SystradePTrade systradePTrade) {

		return baseMapper.update(systradePTrade, "SystradePTradeMapper.updateWalletPay");

	}

	@Override
	public Integer updateCSP(SystradePTrade systradePTrade) {

		return baseMapper.update(systradePTrade, "SystradePTradeMapper.updateCSP");

	}

	@Override
	public Integer updateEPStatus(EctoolsPayments s) {

		return baseMapper.update(s, "EctoolsPaymentsMapper.updateEPStatus");

	}

	@Override
	public Integer updateETPStatus(EctoolsTradePaybill s) {

		return baseMapper.update(s, "EctoolsTradePaybillMapper.updateETPStatus");

	}

	@Override
	public Integer updateSPTStatus(SystradePTrade s) {

		return baseMapper.update(s, "SystradePTradeMapper.updateSPTStatus");

	}

	@Override
	public List<SystradeTrade> listSystradeTrade(BigInteger i) {

		return baseMapper.selectList(i, "SystradeTradeMapper.listSystradeTrade");

	}

	@Override
	public SystradeTrade getTradeInfo(BigInteger tid) {

		return baseMapper.selectOne(tid, "SystradeTradeMapper.getTradeInfo");

	}

	/**
	 * 【订单支付状态更改接口】
	 */
	@Override
	public Boolean TradePayFinish(SystradeTrade trade) {
		// 获取指定订单信息
		SystradeTrade tradeInfo = getTradeInfo(trade.getTid());
		if (tradeInfo == null) {
			Logger.info("获取指定订单信息为空!");
			throw new ServiceException(MallApiCode.TRADEINFO_EMPTY, MallApiCode.getZhMsg(MallApiCode.TRADEINFO_EMPTY));
		}

		// 待付款状态 终止当前这一次循环
		if (!tradeInfo.getStatus().equals(SysParaNameConst.WAIT_BUYER_PAY))
			return false;

		// order_type==0商城实物订单
		if (null != tradeInfo.getOrderType()) {
			if (tradeInfo.getOrderType() == 0) {
				List<SystradeOrder> listTradeInfoOrder = listTradeInfoOrder(new SystradeOrder(trade.getTid()));
				if (listTradeInfoOrder == null) {
					Logger.info("商品订单信息为空!");
					throw new ServiceException(MallApiCode.TRADEINFOORDER_EMPTY,MallApiCode.getZhMsg(MallApiCode.TRADEINFOORDER_EMPTY));
				}
				listTradeInfoOrder.forEach(list -> {
					Map<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("item_id", list.getItemId());
					hashMap.put("sku_id", list.getSkuId());
					hashMap.put("quantity", list.getNum());
					hashMap.put("sub_stock", list.getSubStock());
					hashMap.put("status", list.getStatus());
					try {
						// 【下单或支付时扣减库存接口】
						OrderOrPay(hashMap);

					} catch (Exception e) {
						Logger.error("系统错误:", e);
					}
				});
			} // order_type==0判断结束
		}
		// 更新商家订单信息
		SystradeTrade systradeTrade = new SystradeTrade();
		systradeTrade.setStatus(SysParaNameConst.WAIT_SELLER_SEND_GOODS);
		systradeTrade.setPayedFee(tradeInfo.getPayment());
		systradeTrade.setTid(trade.getTid());
		Integer systradeTradeBytid = updateSystradeTradeBytid(systradeTrade);
		if (systradeTradeBytid <= 0) {
			Logger.info("更新商家订单信息失败!");
			throw new ServiceException(MallApiCode.UPDATEST_FAIL, MallApiCode.getZhMsg(MallApiCode.UPDATEST_FAIL));
		}

		// 更新商品订单信息
		SystradeOrder systradeOrder = new SystradeOrder();
		systradeOrder.setStatus(SysParaNameConst.WAIT_SELLER_SEND_GOODS);
		systradeOrder.setTid(trade.getTid());
		Integer systradeOrderBytid = updateSystradeOrderBytid(systradeOrder);
		if (systradeOrderBytid <= 0) {
			Logger.info("更新商品订单信息失败!");
			throw new ServiceException(MallApiCode.UPDATESO_FAIL, MallApiCode.getZhMsg(MallApiCode.UPDATESO_FAIL));
		}

		// 记录订单操作日志
		SystradeLog systradeLog = new SystradeLog();
		systradeLog.setRelId(trade.getTid());
		systradeLog.setOpId(SysParaNameConst.op_id);
		systradeLog.setOpName(SysParaNameConst.op_name);
		systradeLog.setOpRole(SysParaNameConst.op_role);
		systradeLog.setBehavior(SysParaNameConst.behavior);
		systradeLog.setLogText(SysParaNameConst.logText);
		Integer insertsystradeLog = insertSystradeLog(systradeLog);
		if (insertsystradeLog <= 0) {
			Logger.info("记录订单操作日志失败!");
			throw new ServiceException(MallApiCode.INSERTSLOG_FAIL, MallApiCode.getZhMsg(MallApiCode.INSERTSLOG_FAIL));
		}

		return true;
	}

	/**
	 * 【下单或支付时扣减库存接口】
	 * 
	 * @throws Exception
	 */
	public Boolean OrderOrPay(Map<String, Object> hashMap) {
		// sub_stock!=0下单减库存(有恶意下单占库存风险)判断开始
		if ((Integer) hashMap.get("sub_stock") != 0) {

			// 更新货品库存
			Integer updateSysitemSkuStore = sysitemService.updateSysitemSkuStore(hashMap);
			if (updateSysitemSkuStore <= 0) {
				Logger.info("更新货品库存失败!");
				throw new ServiceException(MallApiCode.UPDATESSS_FAIL, MallApiCode.getZhMsg(MallApiCode.UPDATESSS_FAIL));
			}

			// 更新商品库存
			Integer updateSysitemItemStore = sysitemService.updateSysitemItemStore(hashMap);
			if (updateSysitemItemStore <= 0) {
				Logger.info("更新商品库存失败!");
				throw new ServiceException(MallApiCode.UPDATESIS_FAIL, MallApiCode.getZhMsg(MallApiCode.UPDATESIS_FAIL));
			}

			return false;

		} else { // 付款减库存，有库存超卖风险
			if ("on".equals(hashMap.get("status"))) {
				if ("".equals(hashMap.get("item_id").toString())) {
					// 根据sku_id获取item_id，再次调用'item_id'参数存在的逻辑
					SysitemSku sysitemSkuByskuId = sysitemService.getSysitemSkuByskuId((Integer) hashMap.get("sku_id"));
					if (sysitemSkuByskuId == null) {
						Logger.info("根据sku_id获取item_id失败!");
						throw new ServiceException(MallApiCode.SSKUBYSKUID_FAIL, MallApiCode.getZhMsg(MallApiCode.SSKUBYSKUID_FAIL));
					}
					hashMap.put("item_id", sysitemSkuByskuId.getItemId());
				}
				// 更新Freez
				updateFreez(hashMap);
			}

			if ("success".equals(hashMap.get("status"))) {

				SysitemItem itemInfo = sysitemService.getSysitemItemBysubStock(hashMap);
				if (itemInfo == null){
					Logger.info("根据itemId获取subStock失败!");
					throw new ServiceException(MallApiCode.ITEMINFO_FAIL, MallApiCode.getZhMsg(MallApiCode.ITEMINFO_FAIL));
				}
				// 是否支持下单减库存
				if (itemInfo.getSubStock() > 0)
					return false;

				// 获取sku库存表信息
				SysitemSkuStore skuIdInfo = sysitemService.getSysitemSkuStore((Integer) hashMap.get("sku_id"));
				if (skuIdInfo == null){
					Logger.info("获取sku库存表信息失败!");
					throw new ServiceException(MallApiCode.SKUIDINFO_FAIL, MallApiCode.getZhMsg(MallApiCode.SKUIDINFO_FAIL));
				}
				// 根据skuInfo['item_id']获取商品库存表信息
				SysitemItemStore itemInfoS = sysitemService.getSysitemItemStore(skuIdInfo.getItemId());
				if (itemInfoS == null){
					Logger.info("获取商品库存表信息失败!");
					throw new ServiceException(MallApiCode.ITEMINFOS_FAIL, MallApiCode.getZhMsg(MallApiCode.ITEMINFOS_FAIL));
				}

				if ((skuIdInfo.getStore() - (Integer) hashMap.get("quantity")) < 0)
					return false;

				if ((skuIdInfo.getFreez() - (Integer) hashMap.get("quantity")) < 0)
					return false;

				Integer freez = skuIdInfo.getFreez() - (Integer) hashMap.get("quantity");

				Integer store = skuIdInfo.getStore() - (Integer) hashMap.get("quantity");

				// 更新sku库存表
				SysitemSkuStore sss = new SysitemSkuStore();
				sss.setFreez(freez);
				sss.setStore(store);
				sss.setSkuId((Integer) hashMap.get("sku_id"));
				Integer sysitemSkuStoreByfreezANDstore = sysitemService.updateSysitemSkuStoreByfreezANDstore(sss);
				if (sysitemSkuStoreByfreezANDstore <= 0){
					Logger.info("更新库存表失败!");
					throw new ServiceException(MallApiCode.UPDATESKU_FAIL, MallApiCode.getZhMsg(MallApiCode.UPDATESKU_FAIL));
				}
				Integer freezs = itemInfoS.getFreez() - (Integer) hashMap.get("quantity");
				Integer stores = itemInfoS.getStore() - (Integer) hashMap.get("quantity");

				// 更新sku库存表更新成功,更新商品库存表
				SysitemItemStore sis = new SysitemItemStore();
				sis.setFreez(freezs);
				sis.setStore(stores);
				sis.setItemId(skuIdInfo.getItemId());
				Integer sysitemItemStoreByfreezANDstore = sysitemService.updateSysitemItemStoreByfreezANDstore(sis);
				if (sysitemItemStoreByfreezANDstore <= 0){
					Logger.info("更新商品库存表失败!");
					throw new ServiceException(MallApiCode.UPDATEITEMSTORE_FAIL, MallApiCode.getZhMsg(MallApiCode.UPDATEITEMSTORE_FAIL));
				}
			}
			return false;
		} // arrParams[sub_stock]!=0下单减库存(有恶意下单占库存风险)判断结束

	}

	/**
	 * 商品订单信息
	 */
	@Override
	public List<SystradeOrder> listTradeInfoOrder(SystradeOrder systradeOrder) {

		return baseMapper.selectList(systradeOrder, "SystradeOrderMapper.listTradeInfoOrder");

	}

	// 更新Freez
	public void updateFreez(Map<String, Object> hashMap) {
		// 修改sku_store表的冻结库存
		Integer updateSkuStoreFreez = sysitemService.updateSkuStoreFreez(hashMap);
		if (updateSkuStoreFreez <= 0) {
			Logger.info("修改sku_store表的冻结库存失败!");
			throw new ServiceException(MallApiCode.UPDATESKUSTORE_FAIL, MallApiCode.getZhMsg(MallApiCode.UPDATESKUSTORE_FAIL));
		}

		// 修改商品主item_store表的冻结库存
		Integer updateItemStoreFreez = sysitemService.updateItemStoreFreez(hashMap);
		if (updateItemStoreFreez <= 0) {
			Logger.info("修改商品主item_store表的冻结库存失败!");
			throw new ServiceException(MallApiCode.UPDATEIS_FAIL, MallApiCode.getZhMsg(MallApiCode.UPDATEIS_FAIL));
		}
	}

	@Override
	public Integer updateSystradeTradeBytid(SystradeTrade systradeTrade) {

		return baseMapper.update(systradeTrade, "SystradeTradeMapper.updateSystradeTradeBytid");

	}

	@Override
	public Integer updateSystradeOrderBytid(SystradeOrder systradeOrder) {

		return baseMapper.update(systradeOrder, "SystradeOrderMapper.updateSystradeOrderBytid");

	}

	@Override
	public Integer insertSystradeLog(SystradeLog systradeLog) {

		return baseMapper.insert(systradeLog, "SystradeLogMapper.insertSystradeLog");

	}

	@Override
	public Integer updateIsSyncByplatformId(SystradePTrade systradePTrade) {

		return baseMapper.update(systradePTrade, "SystradePTradeMapper.updateIsSyncByplatformId");

	}

	@Override
	public List<Map<String, Object>> listTrade(List<Object> list) {

		// 获取商家订单信息 存入变量tradeLists
		List<Map<String, Object>> tradeLists = getTradeMoney(list);

		// 获取商品订单表信息 存入变量orderLists
		List<Map<String, Object>> orderLists = listOrder(list);

		// 从orderLists获取所有商品订单号oid集合
		orderLists.forEach(orders -> {

			try {

				// 【获取单个SKU的基本信息】
				SysitemSku sysitemSkuByskuId = sysitemService.getSysitemSkuByskuId((Integer) orders.get("skuId"));

				// 获取商品参加促销活动信息(一个子订单只可参加一次标签促销活动) 存入变量promotionActivityData
				SystradePromotionDetail promotionActivityData = getpromotionActivityData((Long) orders.get("oid"));

				Map<String, Object> order = new HashMap<String, Object>();
				order.put("skuInfo", sysitemSkuByskuId);
				order.put("promotion_tag", promotionActivityData);
				orderLists.add(order);

			} catch (Exception e) {
				Logger.error("系统错误:%s", e);
			}

		});

		// 以tid为key合并同类项（orderLists、promotionActivityData）到tradeLists
		tradeLists.forEach(trades -> {
			orderLists.forEach(orders -> {
				if (trades.get("tid").equals(orders.get("tid"))) {
					trades.put("order", orders);
				}

			});
		});

		return tradeLists;
	}

	@Override
	public List<Map<String, Object>> listOrder(List<Object> list) {

		return baseMapper.selectList(ImmutableMap.of("tid", list), "SystradeOrderMapper.listOrder");

	}

	@Override
	public SystradePromotionDetail getpromotionActivityData(Long oid) {

		return baseMapper.selectOne(oid, "SystradePromotionDetailMapper.getpromotionActivityData");

	}

	@Override
	public Map<String, String> getShopMobile(BigInteger tid) {

		return baseMapper.selectOne(tid, "SystradeTradeMapper.getShopMobile");

	}

	@Override
	public EctoolsPayments getIsPaySucc(EctoolsPayments ectoolsPayments) {

		return baseMapper.selectOne(ectoolsPayments, "EctoolsPaymentsMapper.getIsPaySucc");

	}

}
