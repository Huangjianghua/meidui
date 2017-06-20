package com.meiduimall.application.mall.pay.api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.mall.constant.ResponseBodyData;
import com.meiduimall.application.mall.constant.SysParaNameConst;
import com.meiduimall.application.mall.pay.alipay.AlipayNotify;
import com.meiduimall.application.mall.pay.model.EctoolsPaymentsSucc;
import com.meiduimall.application.mall.pay.service.PaymentService;
import com.meiduimall.application.mall.util.DateUtil;
import com.meiduimall.application.mall.util.Logger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1")
@Api("支付宝异步回调调相关api")
public class AlipayController {

//	total_amount=2.00     //订单金额
//	&buyer_id=2088102116773037   //买家支付宝用户号
//	&body=大乐透2.1
//	&trade_no=2016071921001003030200089909   //支付宝交易号
//	&refund_fee=0.00                     //总退款金额
//	&notify_time=2016-07-19 14:10:49
//	&subject=大乐透2.1
//	&sign_type=RSA2
//	&charset=utf-8
//	&notify_type=trade_status_sync
//	&out_trade_no=0719141034-6418      //订单号
//	&gmt_close=2016-07-19 14:10:46     //支付完成时间
//	&gmt_payment=2016-07-19 14:10:47    //交易付款时间
//	&trade_status=TRADE_SUCCESS     //付款状态
//	&version=1.0                     
//	&sign=kPbQIjX+xQc8F0/A6/AocELIjhhZnGbcBN6G4MM/HmfWL4ZiHM6fWl5NQhzXJusaklZ1LFuMo+lHQUELAYeugH8LYFvxnNajOvZhuxNFbN2LhF0l/KL8ANtj8oyPM4NN7Qft2kWJTDJUpQOzCzNnV9hDxh5AaT9FPqRS6ZKxnzM=
//	&gmt_create=2016-07-19 14:10:44   //付款创建时间
//	&app_id=2015102700040153      //支付宝分配给开发者的应用Id
//	&seller_id=2088102119685838   //卖家支付宝用户号	
//	&notify_id=4a91b7a78a503640467525113fb7d8bg8e
	
	
	@Autowired
	private PaymentService paymentService; 
	
	
	@ApiOperation(value="支付宝异步回调", notes="支付宝的通知返回参数，可参考支付宝官方文档中页面跳转同步通知参数列表(以下仅供参考)")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "out_trade_no", value = "商户订单号", required = true, dataType = "String"),
        @ApiImplicitParam(name = "trade_no", value = "支付宝交易号", required = true, dataType = "String"),
        @ApiImplicitParam(name = "trade_status", value = "交易状态", required = true, dataType = "String"),
        @ApiImplicitParam(name = "notify_id", value = "异步通知ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "sign", value = "sign", required = true, dataType = "String"),
        @ApiImplicitParam(name = "total_fee", value = "订单金额", required = true, dataType = "String"),
        @ApiImplicitParam(name = "gmt_payment", value = "交易结束时间", required = true, dataType = "String"),
        @ApiImplicitParam(name = "seller_id", value = "seller_id", required = true, dataType = "String")
	})
	@SuppressWarnings({ "rawtypes" })
	@GetMapping(value =  "/getAliPayNotify" )
	public String getPayNotify(HttpServletRequest request) throws Exception {
		Logger.info("进入支付宝异步回调");
		
		Map<String,String> params = new HashMap<>();
		Map requestParams = request.getParameterMap();
        Logger.info("支付宝得到的报文:%s", requestParams.toString());
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
		valueStr = (i == values.length - 1) ? valueStr + values[i]:valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
		params.put(name, valueStr);
		Logger.info("%s:%s", name,valueStr);
		}
		
		
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号  
		String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号  
		String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");


		//交易状态
		String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//异步通知ID
		String notifyId = new String(request.getParameter("notify_id").getBytes("ISO-8859-1"),"UTF-8");

		//sign
		String sign = new String(request.getParameter("sign").getBytes("ISO-8859-1"),"UTF-8");
		
		//订单金额
		String totalFee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		
		String gmtPayment = "";
		
		if(null != request.getParameter("gmt_payment")){
			 gmtPayment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"),"UTF-8");
		} 
		//交易结束时间
		
		
		String sellerId = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"),"UTF-8");
		
		

		if(notifyId!=""){
				if("true".equals(AlipayNotify.verifyResponse(notifyId))){//判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
					if(AlipayNotify.getSignVeryfy(params, sign)){//使用支付宝公钥验签
						if("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus)){
						//业务处理
					    EctoolsPaymentsSucc ectoolsPaymentsSucc = new EctoolsPaymentsSucc();
					    ectoolsPaymentsSucc.setPaymentId(outTradeNo);    
					    ectoolsPaymentsSucc.setTradeNo(tradeNo);
					    ectoolsPaymentsSucc.setStatus(SysParaNameConst.SUCC);
					    ectoolsPaymentsSucc.setMoney(new BigDecimal(totalFee));
					    if(!"".equals(gmtPayment)){
					    	ectoolsPaymentsSucc.setPayedTime(Integer.valueOf(DateUtil.date2TimeStamp(gmtPayment, "yyyy-MM-dd HH:mm:ss")));
					    }
					    ectoolsPaymentsSucc.setBank("支付宝");
					    ectoolsPaymentsSucc.setPayName("支付宝");
					    ectoolsPaymentsSucc.setPayAccount("支付宝AILPAY");
					    ectoolsPaymentsSucc.setAccount(sellerId);
						ResponseBodyData payCallBack = paymentService.payCallBack(ectoolsPaymentsSucc,requestParams.toString());
						Logger.info("支付宝回调处理结果:%s", payCallBack.toString());
						}
						Logger.info("success");
						return "success" ;
					}else{//验证签名失败
						Logger.info("sign fail");
						return "sign fail" ;
					}
				}else{//验证是否来自支付宝的通知失败
					Logger.info("response fail");
					return "response fail" ;
				}
		}else{
			Logger.info("no notify message");
			return "no notify message" ;
		}
		}
		
	
}
