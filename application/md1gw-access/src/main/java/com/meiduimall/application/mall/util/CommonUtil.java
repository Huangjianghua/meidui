package com.meiduimall.application.mall.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meiduimall.application.mall.config.MyProps;
import com.meiduimall.application.mall.constant.OauthConst;
import com.meiduimall.application.mall.constant.SysParaNameConst;
import com.meiduimall.application.mall.exception.MallApiCode;
import com.meiduimall.application.mall.model.EctoolsPayments;
import com.meiduimall.application.mall.model.EctoolsPaymentsSucc;
import com.meiduimall.application.mall.model.PaymentTrade;
import com.meiduimall.application.mall.model.SystradePTrade;
import com.meiduimall.application.mall.model.SysuserAccount;
import com.meiduimall.application.mall.model.SysuserUser;
import com.meiduimall.application.mall.model.SysuserWalletPaylog;
import com.meiduimall.exception.ServiceException;

import net.sf.json.JSONObject;

public class CommonUtil {

	@Autowired
	private static MyProps myProps;
	/**
	 * 组装签名 公共的key value
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> commonMap(Map<String,String> map){
		map.put(OauthConst.CLIENT_ID, OauthConst.CLIENT_ID_VALUE);
		map.put(OauthConst.TIMESATAMP, String.valueOf(System.currentTimeMillis()));
		map.put(OauthConst.SIGN, GatewaySignUtil.sign(OauthConst.SECRETKEY_VALUE, map));
		return map;
		
	}
	/**
	 * 组装签名 公共的JSON
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static JSONObject commonJSON(JSONObject json){
		json.put(OauthConst.CLIENT_ID, OauthConst.CLIENT_ID_VALUE);
		json.put(OauthConst.TIMESATAMP, String.valueOf(System.currentTimeMillis()));
		json.put(OauthConst.SIGN, GatewaySignUtil.buildsign(OauthConst.SECRETKEY_VALUE, json));
		return json;
		
	}
	
	public static String onlySignJSON(JSONObject json){
		return GatewaySignUtil.buildsign(OauthConst.SECRETKEY_VALUE, json);
		
	}
	
	/**
	 * 组装更新 EctoolsPayments
	 * @param paymentTradePay
	 * @return
	 * @throws Exception
	 */
	public static EctoolsPayments updateEctoolsPaymentsCommon(PaymentTrade paymentTrade){
		EctoolsPayments ectoolsPayments = new EctoolsPayments();
		ectoolsPayments.setCurMoney(new BigDecimal(paymentTrade.getMoney()));     //支付货币金额 (第三方支付金额,必须money)
		ectoolsPayments.setWalletPay(new BigDecimal(paymentTrade.getUse_money()));   //钱包余额支付金额
		ectoolsPayments.setShopingPay(new BigDecimal(0));     //购物券支付金额 shoping_pay 忽略
		ectoolsPayments.setCoupPay(new BigDecimal(0));     //消费券支付金额 coup_pay 忽略
		ectoolsPayments.setPointPay(Integer.valueOf(paymentTrade.getUse_point())); //积分支付金额
		ectoolsPayments.setCartXfc(new BigDecimal(0));     //忽略
		ectoolsPayments.setStatus(SysParaNameConst.PAYING);  
		ectoolsPayments.setPaymentId(paymentTrade.getPayment_id()); //支付单号
		Operate operate = new Operate();
		try {
			operate.serializable(paymentTrade);
		} catch (Exception e) {
			Logger.error("序列化失败: %s", e);
			throw new ServiceException(MallApiCode.SERIALIZABLE_FAIL, MallApiCode.getZhMsg(MallApiCode.SERIALIZABLE_FAIL));
		}
		ectoolsPayments.setReturnUrl(myProps.getFinishUrl()+"?paymentTrade="+paymentTrade);    //支付返回地址
		return ectoolsPayments;
	}

	/**
	 * 组装更新到平台订单表 systradePTrade 
	 * @param paymentBill
	 * @return
	 * @throws Exception
	 */
	public static SystradePTrade updateCSPCommon(Map<String, Object> paymentBill){
	   SystradePTrade systradePTrade = new SystradePTrade();
	   systradePTrade.setCoupPay(new BigDecimal(paymentBill.get("coupPay").toString()));
	   systradePTrade.setShopingPay(new BigDecimal(paymentBill.get("shopingPay").toString()));
	   systradePTrade.setPointPay(Integer.valueOf(paymentBill.get("pointPay").toString()));
	   systradePTrade.setPlatformId(new BigInteger(paymentBill.get("platformId").toString()));
	   return systradePTrade;
	}

	/**
	 * 组装 【会员钱包支付接口】,写入钱包支付记录 SysuserWalletPaylog 
	 * @param paymentBill
	 * @param userMoney
	 * @param paymentTradePay
	 * @param sysuserAccount
	 * @return
	 * @throws Exception
	 */
	public static SysuserWalletPaylog updateUsersWalletPayCommon(Map<String, Object> paymentBill,
			SysuserUser userMoney,PaymentTrade paymentTrade,SysuserAccount sysuserAccount){
		SysuserWalletPaylog sysuserWalletPaylog = new SysuserWalletPaylog();
		sysuserWalletPaylog.setUid(Integer.valueOf(paymentTrade.getUser_id()));
		sysuserWalletPaylog.setLoginName(sysuserAccount.getLoginAccount());
		sysuserWalletPaylog.setOrderNo(paymentBill.get("platformId").toString());
		sysuserWalletPaylog.setVorMoney(userMoney.getMoney());
		sysuserWalletPaylog.setMoney(new BigDecimal(paymentBill.get("walletPay").toString()));
		double afMoney = userMoney.getMoney().subtract(new BigDecimal(paymentBill.get("walletPay").toString())).doubleValue();
		sysuserWalletPaylog.setAfMoney(BigDecimal.valueOf(afMoney));
		return sysuserWalletPaylog;
	}

	/**
	 * 组装 查询支付单信息 EctoolsPayments 
	 * @param result_payment
	 * @param ectoolsPaymentsSucc
	 * @return
	 */
	public static EctoolsPayments getIsPaySuccCommon(Map<String, Object> resultPayment,
			EctoolsPaymentsSucc ectoolsPaymentsSucc){
		EctoolsPayments ectoolsPayments = new EctoolsPayments();
		ectoolsPayments.setPlatformId(resultPayment.get("platformId").toString());
		ectoolsPayments.setPaymentId(ectoolsPaymentsSucc.getPaymentId());
		ectoolsPayments.setStatus(SysParaNameConst.SUCC);
		return ectoolsPayments;
	}

	/**
	 * //1.获取用户余额和已冻结金额  //2.更新余额、更新冻结金额
	 * @param userMoney
	 * @param paymentTradePay
	 * @return
	 */
	public static SysuserUser updateMF(SysuserUser userMoney, PaymentTrade paymentTrade){
		SysuserUser sysuserUser = new SysuserUser();
		sysuserUser.setMoney(userMoney.getMoney());
		sysuserUser.setFrozenMoney(userMoney.getFrozenMoney());
		sysuserUser.setUserId(Integer.valueOf(paymentTrade.getUser_id()));
		return sysuserUser;
	}
	
	
	
}
