package com.meiduimall.application.md1gwaccess.service.impl;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.client.RestTemplate;

import com.meiduimall.application.md1gwaccess.config.MyProps;
import com.meiduimall.application.md1gwaccess.constant.HttpRConst;
import com.meiduimall.application.md1gwaccess.constant.OauthConst;
import com.meiduimall.application.md1gwaccess.constant.ResponseBodyData;
import com.meiduimall.application.md1gwaccess.constant.SysParaNameConst;
import com.meiduimall.application.md1gwaccess.dao.BaseMapper;
import com.meiduimall.application.md1gwaccess.model.EctoolsPayments;
import com.meiduimall.application.md1gwaccess.model.EctoolsPaymentsSucc;
import com.meiduimall.application.md1gwaccess.model.EctoolsTradePaybill;
import com.meiduimall.application.md1gwaccess.model.PaymentTrade;
import com.meiduimall.application.md1gwaccess.model.SystradePTrade;
import com.meiduimall.application.md1gwaccess.model.SystradeTrade;
import com.meiduimall.application.md1gwaccess.model.SysuserWalletPaylog;
import com.meiduimall.application.md1gwaccess.service.PaymentService;
import com.meiduimall.application.md1gwaccess.service.TPPaymentService;
import com.meiduimall.application.md1gwaccess.service.TradeService;
import com.meiduimall.application.md1gwaccess.service.UserService;
import com.meiduimall.application.md1gwaccess.util.CommonUtil;
import com.meiduimall.application.md1gwaccess.util.DateUtil;
import com.meiduimall.application.md1gwaccess.util.GatewaySignUtil;
import com.meiduimall.application.md1gwaccess.util.Logger;

import net.sf.json.JSONObject;

@Component
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private BaseMapper baseMapper;

	@Autowired
	private TradeService tradeService;

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MyProps myProps;
	
	@Autowired
	private TPPaymentService tPPaymentService;

	@Override
	public Map<String, Object> getPaymentBill(String paymentId) throws Exception {
		Map<String, Object> selectOne = baseMapper.selectOne(paymentId, "EctoolsPaymentsMapper.getPaymentBill");
		return selectOne;
	}

	@Override
	public Integer updateEctoolsPayments(EctoolsPayments ectoolsPayments) throws Exception {
		Integer update = baseMapper.update(ectoolsPayments, "EctoolsPaymentsMapper.updateEctoolsPayments");
		return update;
	}

	/**
	 * 【订单支付请求支付网关接口】
	 * 
	 * @param paymentTradePay
	 * @param paymentBill
	 * @param obj_p_trade_info
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@SuppressWarnings({ "unused", "static-access" })
	public ResponseBodyData PaymentTrade(PaymentTrade paymentTrade, SystradePTrade obj_p_trade_info, List<Object> list,
			JSONObject memberBasicInfo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 3.更新支付单信息
			Integer updateEctoolsPayments = updateEctoolsPayments(CommonUtil.UpdateEctoolsPaymentsCommon(paymentTrade));
			if (updateEctoolsPayments <= 0) {
				Logger.info("更新支付单信息失败!");
				throw new RuntimeException("更新支付单信息失败!");
			}

			// 4.子订单获取所有商家订单号tid
			List<EctoolsTradePaybill> tradePaybill = tradeService
					.listEctoolsTradePaybill(paymentTrade.getPayment_id().toString());
			if (tradePaybill == null) {
				Logger.info("子订单获取所有商家订单为空!");
				throw new Exception("子订单获取所有商家订单为空!");
			}

			Map<String, Object> paymentBill = getPaymentBill(paymentTrade.getPayment_id());
			if (paymentBill == null) {
				Logger.info("重新获取支付单信息为空!");
				return new ResponseBodyData(11, "重新获取支付单信息为空!");
			}

			// 6.通过tid -> 获取订单列表接口
			// tradeService.listTrade(list);
			List<Map<String, Object>> trades = tradeService.getTradeMoney(list);
			if (trades == null) {
				Logger.info("通过获取订单列表为空!");
				throw new Exception("通过tid获取订单列表为空!");
			}

			// 7.更新子支付状态 (批处理)
			// 8.找出的无效tid(遍历的时候元素里面的tid在list里面则为有效数据)和无效的payment_id
			// 存入变量deleteParams
			List<EctoolsTradePaybill> deleteParams = new ArrayList<EctoolsTradePaybill>();
			List<EctoolsTradePaybill> listetpb = new ArrayList<EctoolsTradePaybill>();
			List<Object> B = new ArrayList<Object>();
			trades.forEach(trade -> {
				EctoolsTradePaybill etpb = new EctoolsTradePaybill();
				etpb.setStatus(SysParaNameConst.paying);
				etpb.setPayment(new BigDecimal(trade.get("payment").toString()));
				etpb.setTid(trade.get("tid").toString());
				etpb.setPaymentId(paymentTrade.getPayment_id());
				listetpb.add(etpb);
				B.add(trade.get("tid").toString());
			});

			Integer updateEctoolsTradePaybill = tradeService.updateEctoolsTradePaybill(listetpb);
			if (updateEctoolsTradePaybill <= 0) {
				Logger.info("更新子支付状态失败!");
				throw new RuntimeException("更新子支付状态失败!");
			}

			List<Object> A = new ArrayList<Object>();
			for (EctoolsTradePaybill ectoolsTradePaybill : tradePaybill) {
				A.add(ectoolsTradePaybill.getTid());
			}

			// 取子支付单与商家订单的差集
			A.removeAll(B);
			Logger.info("需要删除的非法数据有:%s条", A.size());
			if (A.size() != 0) {
				for (Object object : A) {
					EctoolsTradePaybill ectoolsTradePaybill = new EctoolsTradePaybill();
					ectoolsTradePaybill.setTid(object.toString());
					ectoolsTradePaybill.setPaymentId(paymentTrade.getPayment_id());
					deleteParams.add(ectoolsTradePaybill);
				}
				// 9.通过 变量deleteParams 清除ectools_trade_paybill表过期数据
				Integer deleteEctoolsTradePaybill = tradeService.deleteEctoolsTradePaybill(deleteParams);
			}

			// 10. -- 第一次支付过来判断开始 --
			if (obj_p_trade_info.getIsPaying() == 0) {
				obj_p_trade_info.setPayType(3);// 第三方支付或者钱包,混合支付
				// 有积分的支付方式
				if (obj_p_trade_info.getPointPay() > 0) {
					obj_p_trade_info.setPayType(2);
				}

				// 更新平台订单表第三方支付的款和状态
				obj_p_trade_info.setIsPaying(SysParaNameConst.isPaying);
				obj_p_trade_info.setCashPay(new BigDecimal(paymentTrade.getMoney()));
				Integer updateSystradePTrade = tradeService.updateSystradePTrade(obj_p_trade_info);
				if (updateSystradePTrade <= 0) {
					Logger.info("更新平台订单表第三方支付的款和状态失败!");
					throw new RuntimeException("更新平台订单表第三方支付的款和状态失败!");
				}

				// 有使用余额，冻结余额判断开始
				if (new BigDecimal(paymentBill.get("walletPay").toString()).compareTo(new BigDecimal(0)) == 1
						&& obj_p_trade_info.getWalletPay().compareTo(new BigDecimal(0)) == 0) {

					// 余额更新到平台表
					Integer updateWalletPay = tradeService.updateWalletPay(
							new SystradePTrade(new BigInteger(paymentBill.get("platformId").toString()),
									new BigDecimal(paymentBill.get("walletPay").toString())));
					if (updateWalletPay <= 0) {
						Logger.info("结余额更新到平台表失败!");
						throw new RuntimeException("结余额更新到平台表失败!");
					}

				} // 有使用余额，冻结余额判断结束

				// 冻结积分等判断开始
				if (Integer.valueOf(paymentBill.get("pointPay").toString()) > 0
						&& obj_p_trade_info.getPointPay() == 0) {

					// 把冻结的金额更新到平台订单表(systrade_p_trade)
					Logger.info("支付单积分:%s", paymentBill.get("pointPay"));
					Integer updateCSP = tradeService.updateCSP(CommonUtil.UpdateCSPCommon(paymentBill));
					if (updateCSP <= 0) {
						Logger.info("把冻结的积分到平台订单表 失败!");
						throw new RuntimeException("把冻结的金额到平台订单表 失败!");
					}
					
				}
				
				// 调用会员中心冻结积分和余额接口【freeze】积分
				JSONObject freezeUnfreeze = userService.freezeUnfreeze(paymentTrade, paymentBill);
				if (freezeUnfreeze.getInt("status") != 0) {
					Logger.info("冻结积分,余额到会员系统失败!");
					throw new RuntimeException("冻结积分,余额到会员系统失败!");
				}
				
				

			} // -- 第一次支付过来判断结束 --

			// ---- 没有第三方支付判断开始 paymentBill["cur_money"] <= 0 ----
			Logger.info("支付货币金额:%s", paymentBill.get("curMoney").toString());
			if (new BigDecimal(paymentBill.get("curMoney").toString()).compareTo(new BigDecimal(0)) != 1) {

				Logger.info("进入没有第三方支付!");
				// 更新支付单表
				Integer updateEPStatus = tradeService
						.updateEPStatus(new EctoolsPayments(paymentTrade.getPayment_id(), SysParaNameConst.SUCC));
				if (updateEPStatus <= 0) {
					Logger.info("更新支付单表失败!");
					throw new RuntimeException("更新支付单表失败!");
				}

				// 更新子支付单表
				Integer updateETPStatus = tradeService
						.updateETPStatus(new EctoolsTradePaybill(SysParaNameConst.SUCC, paymentTrade.getPayment_id()));
				if (updateETPStatus <= 0) {
					Logger.info("更新子支付单表失败!");
					throw new RuntimeException("更新子支付单表失败!");
				}

				// 更新平台订单表
				Integer updateSPTStatus = tradeService
						.updateSPTStatus(new SystradePTrade(new BigInteger(paymentBill.get("platformId").toString()),
								SysParaNameConst.WAIT_SELLER_SEND_GOODS));
				if (updateSPTStatus <= 0) {
					Logger.info("更新平台订单表失败!");
					throw new RuntimeException("更新平台订单表失败!");
				}

				// 获取支付单号对应的所有商家订单信息
				List<SystradeTrade> tradelist = tradeService
						.listSystradeTrade(new BigInteger(paymentBill.get("platformId").toString()));
				if (tradelist == null) {
					Logger.info("获取支付单号对应的所有商家订单信息为空!");
					throw new Exception("获取支付单号对应的所有商家订单信息为空!");
				}

				// 调用【订单支付状态更改接口】减库存
				tradelist.forEach(trade -> {
					try {
						tradeService.TradePayFinish(trade);
					} catch (Exception e) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						Logger.error("系统错误:%s", e.getMessage());
					}
				});
				
				//获取memId
				JSONObject memIdByUserId = userService.getMemIdByUserId(Integer.valueOf(paymentBill.get("userId").toString()));
				if(memIdByUserId.getInt("status") != 1){
					Logger.info("获取memId失败", memIdByUserId.getString("msg"));
					return new ResponseBodyData(11, memIdByUserId.getString("msg"));
				}
				JSONObject fromObject = new JSONObject().fromObject(memIdByUserId.get("data"));

				// 同步订单到会员系统
				ResponseBodyData saveOrderAndUpdateFunction = SaveOrderAndUpdateFunction(paymentBill, obj_p_trade_info,fromObject);
				if(saveOrderAndUpdateFunction.getStatus() != 0){
					return new ResponseBodyData(11,"fail");
				}else{
					return new ResponseBodyData("success");
				}
				// ---- 没有第三方支付判断开始结束 ----
			} else {
				// 有第三方支付
				Logger.info("进入第三方支付!");
				ResponseBodyData payment = tPPaymentService.Payment(paymentTrade, obj_p_trade_info);
				if(payment.getStatus() == 0){
					return new ResponseBodyData(payment.getData(),3,"success");
				}else{
					return new ResponseBodyData(11,payment.getMsg());
				} 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			Logger.error("系统错误:%s", e.getMessage());
			return new ResponseBodyData(11, "系统错误:" + e.getMessage());
		}

	}

	/**
	 * 组合参数调用会员中心接口【SaveOrder接口】，同步订单到会员系统
	 */
	public ResponseBodyData SaveOrderAndUpdateFunction(Map<String, Object> paymentBill, SystradePTrade obj_p_trade_info,JSONObject fromObject) throws Exception {
		// 更新平台订单信息,标识已同步订单到会员系统
		SystradePTrade systradePTrade = new SystradePTrade();
		systradePTrade.setIsSync(SysParaNameConst.isSync);
		systradePTrade.setPlatformId(new BigInteger(paymentBill.get("platformId").toString()));
		Integer updateSystradePTrade = tradeService.updateIsSyncByplatformId(systradePTrade);
		if (updateSystradePTrade == null) {
			Logger.info("更新平台订单信息,标识已同步订单到会员系统 失败!");
			throw new Exception("更新平台订单信息,标识已同步订单到会员系统 失败!");
		}
		
		JSONObject unfreezeDeduct = null;
		
		// 支付成功! 解冻并扣减余额 和 积分
		if (Integer.valueOf(paymentBill.get("pointPay").toString())  > 0 || 
				new BigDecimal(paymentBill.get("walletPay").toString()).compareTo(new BigDecimal(0)) == 1) {
			Logger.info("进入解冻并扣减");
			//解冻并扣减
			unfreezeDeduct = userService.unfreezeDeduct(paymentBill,fromObject.getString("memId"));
			if(unfreezeDeduct.getInt("status") != 0){
				Logger.info("获取memId失败", unfreezeDeduct.getString("msg"));
				return new ResponseBodyData(11, unfreezeDeduct.getString("msg"));
			}
			
		}

		 return new ResponseBodyData(unfreezeDeduct);

	}

	/**
	 * 支付完成
	 * 
	 * @param paymentBill
	 * @return
	 */
	public ResponseBodyData PayFinish(String paymentId) throws Exception {
		try {
			Map<String, Object> paymentBill = getPaymentBill(paymentId);
			if (paymentBill == null) {
				Logger.info("支付单信息为空!");
				return new ResponseBodyData(11, "支付单信息为空!");
			}
			if (paymentBill.get("status").equals(SysParaNameConst.progress)
					|| paymentBill.get("status").equals(SysParaNameConst.SUCC)) {
				List<EctoolsTradePaybill> tradePaybill = tradeService.listEctoolsTradePaybill(paymentId);
				if(tradePaybill == null){
					Logger.info("获取商家订单失败,无法发短信!");
				}
				for (EctoolsTradePaybill ectoolsTradePaybill : tradePaybill) {
					if(null != ectoolsTradePaybill.getTid() && !"".equals(ectoolsTradePaybill.getTid())){
							// 获取商家手机号
							Map<String, String> mobile = tradeService.getShopMobile(new BigInteger(ectoolsTradePaybill.getTid()));
								 if(null != mobile.get("mobile") && !"".equals(mobile.get("mobile"))){
									 boolean isPhone =Pattern.compile(SysParaNameConst.PHONE_PATTERN).matcher(mobile.get("mobile")).matches();
									 Logger.info("验证是否是手机号 : %s", isPhone);
									 if(isPhone){
										 // 请求会员中心短信接口，给商家发送短信
										 JSONObject sendSmsMessage = userService.sendSmsMessage(mobile.get("mobile"), ectoolsTradePaybill.getTid());
										 if(sendSmsMessage.getInt("status") != 0){
											 Logger.info("短信发送失败: %s ; 商家tid: %s ; 商家手机号: %s", sendSmsMessage.getString("msg"), 
													 ectoolsTradePaybill.getTid(), mobile.get("mobile"));
										 }
									 }
								 }
							
					}
				}
				

				return new ResponseBodyData("支付完成!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.info("系统错误:%s", e.getMessage());
			return new ResponseBodyData(11, "系统错误!");
		}
		return new ResponseBodyData(11, "支付失败!");

	}

 
 

	@Override
	public EctoolsPaymentsSucc getEctoolsPaymentsSucc(String paymentId) throws Exception {

		return baseMapper.selectOne(paymentId, "EctoolsPaymentsSuccMapper.getEctoolsPaymentsSucc");
	}

	@Override
	public Integer insertEctoolsPaymentsSucc(EctoolsPaymentsSucc EctoolsPaymentsSucc) throws Exception {

		return baseMapper.insert(EctoolsPaymentsSucc, "EctoolsPaymentsSuccMapper.insertEctoolsPaymentsSucc");
	}

	/**
	 * 支付回调
	 */
	@SuppressWarnings({ "static-access" })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public ResponseBodyData PayCallBack(EctoolsPaymentsSucc ectoolsPaymentsSucc, String notice)
			throws Exception {
		
		Logger.info("回调数据:%s", ectoolsPaymentsSucc.toString());
		
		// 查询第三方支付成功记录表
		EctoolsPaymentsSucc payMdl_succ_info = getEctoolsPaymentsSucc(ectoolsPaymentsSucc.getPaymentId());
		if (payMdl_succ_info == null) {
			Integer integer = insertEctoolsPaymentsSucc(ectoolsPaymentsSucc);
			if (integer <= 0) {
				Logger.info("记录支付成功记录 失败!");
				throw new Exception("记录支付成功记录 失败!");
			}

		}

		// 验证支付表是否付款
		Map<String, Object> result_payment = getPaymentBill(ectoolsPaymentsSucc.getPaymentId());
		if (result_payment == null) {
			Logger.info("支付信息不存在!");
			return new ResponseBodyData(11, "支付信息不存在!");
		}

		// result_payment['status'] == 'succ' 已经支付过了
		if (SysParaNameConst.SUCC.equals(result_payment.get("status"))) {
			Logger.info("已经支付过了!");
			return new ResponseBodyData(11, "已经支付过了!");
		}

		// 判断订单是不是重复支付了
		SystradePTrade obj_p_trade_info = tradeService
				.getSystradePTrade(new BigInteger(result_payment.get("platformId").toString()));
		if (obj_p_trade_info == null) {
			Logger.info("平台订单信息不存在!");
			return new ResponseBodyData(11, "平台订单信息不存在!");
		}

		// 查询支付单信息
		EctoolsPayments isPaySucc = tradeService
				.getIsPaySucc(CommonUtil.getIsPaySuccCommon(result_payment, ectoolsPaymentsSucc));

		// obj_p_trade_info['cash_pay']赋值给result_payment['cur_money']，覆盖result_payment['cur_money']原来的值
		result_payment.put("curMoney", obj_p_trade_info.getCashPay());

		Map<String, Object> paymentBill = null;
		JSONObject fromObject = null;
		try {

			// 总支付金额(cur_money)小于5、重复支付(is_pay_succ不为空)、支付金额不相等发送报警短信【会员系统SMSSendNewTemplate接口】
			if (new BigDecimal(result_payment.get("curMoney").toString()).compareTo(new BigDecimal(5)) == -1
					|| isPaySucc != null
					|| new BigDecimal(result_payment.get("curMoney").toString())
							.compareTo(ectoolsPaymentsSucc.getMoney()) != 0
					|| obj_p_trade_info.getStatus().equals(SysParaNameConst.WAIT_SELLER_SEND_GOODS)) {
				// 发送报警短信【会员系统SMSSendNewTemplate接口】
				// TODO
				Logger.info("支付错误金额,总支付金额小于5 等等!");
			}

			// result_payment['cur_money']!=$postdata['cur_money']用户提交与数据库不一致
			Logger.info("数据库:%s ;; 用户提交:%s ;; 比较结果:%s", new BigDecimal(result_payment.get("curMoney").toString()),ectoolsPaymentsSucc.getMoney(),
					new BigDecimal(result_payment.get("curMoney").toString()).compareTo(ectoolsPaymentsSucc.getMoney()) != 0);
			if (new BigDecimal(result_payment.get("curMoney").toString()).compareTo(ectoolsPaymentsSucc.getMoney()) != 0) {
				// 更新支付单错误金额
				Integer updateErrorMoney = updateErrorMoney(
						new EctoolsPayments(ectoolsPaymentsSucc.getPaymentId(), SysParaNameConst.error_money));
				if (updateErrorMoney <= 0) {
					Logger.info("更新支付单错误金额 失败!");
					throw new Exception("更新支付单错误金额 失败!");
				}
			}

			// 更新支付单表
			EctoolsPayments ep = new EctoolsPayments();
			ep.setStatus(SysParaNameConst.SUCC);
			ep.setPayName(ectoolsPaymentsSucc.getPayName());
			if(null != ectoolsPaymentsSucc.getPayedTime()){
				ep.setPayedTime(ectoolsPaymentsSucc.getPayedTime());
			}
			ep.setAccount(ectoolsPaymentsSucc.getAccount());
			ep.setBank(ectoolsPaymentsSucc.getBank());
			ep.setPayAccount(ectoolsPaymentsSucc.getPayAccount());
			ep.setPayVer("1.0");
			ep.setIp(InetAddress.getLocalHost().getHostAddress());
			ep.setTradeNo(ectoolsPaymentsSucc.getTradeNo());
			ep.setPaymentId(ectoolsPaymentsSucc.getPaymentId());
			Integer updateEctoolsPayments = updateEctoolsPayments(ep);
			if (updateEctoolsPayments <= 0) {
				Logger.info("更新支付单表 失败!");
				throw new Exception("更新支付单表 失败!");
			}

			// 支付单表更新成功，更新子支付单表
			Integer updateETPStatus = tradeService.updateETPStatus(
					new EctoolsTradePaybill(SysParaNameConst.SUCC, ectoolsPaymentsSucc.getPaymentId()));
			if (updateETPStatus <= 0) {
				Logger.info("更新子支付单 失败!");
				throw new Exception("更新子支付单 失败!");
			}
			
			// 获取支付单信息
			paymentBill = getPaymentBill(ectoolsPaymentsSucc.getPaymentId());
			if (paymentBill == null) {
				Logger.info("修改支付单状态之后,获取支付单信息 为空!");
			}
			
			// 更新平台订单表信息
			Integer updateSPTStatus = tradeService.updateSPTStatus(
					new SystradePTrade(new BigInteger(paymentBill.get("platformId").toString()),
							SysParaNameConst.WAIT_SELLER_SEND_GOODS));
			if (updateSPTStatus <= 0) {
				Logger.info("更新平台订单表信息 失败!");
				throw new Exception("更新平台订单表信息 失败!");
			}

			// 获取平台订单信息，组合参数调用会员中心接口【SaveOrder接口】，同步订单到会员系统

			SystradePTrade systradePTrade = tradeService
					.getSystradePTrade(new BigInteger(paymentBill.get("platformId").toString()));

			List<EctoolsTradePaybill> listEctoolsTradePaybill = tradeService
					.listEctoolsTradePaybill(ectoolsPaymentsSucc.getPaymentId());

			listEctoolsTradePaybill.forEach(ETP -> {
				try {
					// 调用【订单支付状态更改接口】
					tradeService.TradePayFinish(new SystradeTrade(new BigInteger(ETP.getTid())));
				} catch (Exception e) {
					Logger.error("系统错误:%s", e.getMessage());
				}
			});

			// paymentBill['status'] == "succ" || $paymentBill['status'] ==
			// "progress"处理混合支付判断
			if (paymentBill.get("status").equals(SysParaNameConst.SUCC)
					|| paymentBill.get("status").equals(SysParaNameConst.progress)) {

				// paymentBill["wallet_pay"] > 0使用余额判断开始
				if (new BigDecimal(paymentBill.get("walletPay").toString()).compareTo(new BigDecimal(0)) == 1) {

					
					//获取memId
					JSONObject memIdByUserId = userService.getMemIdByUserId(Integer.valueOf(paymentBill.get("userId").toString()));
					if(memIdByUserId.getInt("status") != 1){
						Logger.info("获取memId失败", memIdByUserId.getString("msg"));
						return new ResponseBodyData(11, memIdByUserId.getString("msg"));
					}
					fromObject = new JSONObject().fromObject(memIdByUserId.get("data"));
					
					
					// 同步订单到会员系统
					PaymentTrade paymentTrade = new PaymentTrade();
					paymentTrade.setUser_id(paymentBill.get("userId").toString());
					paymentTrade.setPayway("3");
					
					ResponseBodyData saveOrderAndUpdateFunction = SaveOrderAndUpdateFunction(paymentBill, systradePTrade,fromObject);
					if(saveOrderAndUpdateFunction.getStatus() != 0){
					    Logger.info("失败 %s", saveOrderAndUpdateFunction.getMsg());	
					    throw new RuntimeException(saveOrderAndUpdateFunction.getMsg());
					}
					

					// 【会员钱包支付接口】,写入钱包支付记录
					MemberWalletPay(paymentBill,new JSONObject().fromObject(saveOrderAndUpdateFunction.getData()));
					
				}
				
				// 同步到支付服务系统
				JSONObject json = new JSONObject();
				json.put("orderNo", paymentBill.get("platformId").toString()); // 平台订单号
				json.put("payTime", DateUtil.orderTime(String.valueOf(ectoolsPaymentsSucc.getPayedTime()))); // 支付时间
				json.put("paymentNo", ectoolsPaymentsSucc.getPaymentId()); // 支付单号
				json.put("payStatus", "1"); // 支付状态
				json.put("notifyTime", DateUtil.timeStamp(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")); // 回调时间
				json.put("notifyData", notice); // 回调数据
				json.put("notifyStatus", "1"); // 回调状态 支付状态、回调状态 1：成功 0 失败
				NoticePaymentService(json);
			}
			

		} catch (Exception e) {
			Logger.error("系统错误:%s", e.getMessage());
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CallBackDeal(paymentBill, fromObject);
			return new ResponseBodyData(1, "系统错误!");
		}
		return new ResponseBodyData(0, "回调完成!");

	}

	/**
	 * 回滚后查询第三方推送回滚次数,第三方支付推送三次以上订单依然更新失败就中止停，把支付的金额退到用户钱包里面
	 * 
	 * @param paymentBill
	 */
	public void CallBackDeal(Map<String, Object> paymentBill,JSONObject json) throws Exception {
		try {
			// 推送次数大于2判断
			if ((byte) paymentBill.get("errorNum") > 2) {
				// 更新用户余额
				 JSONObject unfreezeDeduct = userService.unfreezeDeduct(paymentBill, json.getString("memId"));
				 if (unfreezeDeduct.getInt("status") != 0) {
					 Logger.info("回滚 更新用户余额 失败!");
					 throw new Exception("回滚 更新用户余额 失败!");
				 }

				// 调用【会员钱包支付接口】,写入钱包支付记录
				MemberWalletPay(paymentBill, unfreezeDeduct.getJSONObject("data"));

			} else {// 推送次数小于等于2判断
					// 推送次数加1
				Integer updateErrorNum = updateErrorNum(paymentBill.get("paymentId").toString());
				if (updateErrorNum <= 0) {
					Logger.info("推送次数加1 失败!");
					throw new Exception("推送次数加1 失败!");
				}
			}
		} catch (Exception e) {
			Logger.error("系统错误:%s", e.getMessage());
		}

	}

	/**
	 * 【会员钱包支付接口】,写入钱包支付记录
	 * 
	 * @throws Exception
	 */
	public void MemberWalletPay(Map<String, Object> paymentBill, JSONObject json) throws Exception {
		SysuserWalletPaylog sysuserWalletPaylog = new SysuserWalletPaylog();
		sysuserWalletPaylog.setUid(Integer.valueOf(paymentBill.get("userId").toString()));

		sysuserWalletPaylog.setLoginName(paymentBill.get("userName").toString());
		sysuserWalletPaylog.setOrderNo(paymentBill.get("platformId").toString());
		sysuserWalletPaylog.setVorMoney(new BigDecimal(json.getString("before_total_money")));
		sysuserWalletPaylog.setMoney(new BigDecimal(paymentBill.get("walletPay").toString()));
		sysuserWalletPaylog.setAfMoney(new BigDecimal(json.getString("now_total_money")));

		Integer updateUsersWalletPay = userService.updateUsersWalletPay(sysuserWalletPaylog);
		if (updateUsersWalletPay <= 0) {
			Logger.info("【会员钱包支付接口】,写入钱包支付记录失败!");
			throw new Exception("【会员钱包支付接口】,写入钱包支付记录失败!");
		}
	}

	@Override
	public Integer updateErrorMoney(EctoolsPayments ep) throws Exception {
		return baseMapper.update(ep, "EctoolsPaymentsMapper.updateErrorMoney");
	}

	/**
	 * 推送次数加1
	 */
	@Override
	public Integer updateErrorNum(String paymentId) throws Exception {

		return baseMapper.update(paymentId, "EctoolsPaymentsMapper.updateErrorNum");
	}

	/**
	 * 支付回调 通知支付服务
	 */
	@Override
	public void NoticePaymentService(JSONObject json) throws Exception {
		String url = myProps.getRouteServiceUrl()+"/pay/payment-service/v1/paynotify";
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType(HttpRConst.MEDIATYPE_JSON_FOR_APP);
		headers.setContentType(type);

		json.put(OauthConst.CLIENT_ID, OauthConst.CLIENT_ID_VALUE);
		json.put(OauthConst.TIMESATAMP, String.valueOf(System.currentTimeMillis()));
		json.put(OauthConst.SIGN, GatewaySignUtil.buildsign(OauthConst.SECRETKEY_VALUE, json));
        Logger.info("通知支付服务参数:%s", json);
		HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(json, headers);
	    String postForObject = restTemplate.postForObject(url, formEntity, String.class);
		Logger.info("通知支付服务==>:%s", postForObject);

	}
	
}
