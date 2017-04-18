package com.meiduimall.payment.api.service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.payment.api.model.alipay.AlipayRequestModel;
import com.meiduimall.payment.api.model.api.PaymentLogsModel;
import com.meiduimall.payment.api.model.api.PaymentParamModel;
import com.meiduimall.payment.api.model.api.PaymenttTradeModel;
import com.meiduimall.payment.api.model.hicardpay.HiCardRequestModel;
import com.meiduimall.payment.api.model.hicardpay.HiCardResponeModel;
import com.meiduimall.payment.api.model.wechat.WeChatAppModel;
import com.meiduimall.payment.api.model.wechat.WeChatRequestModel;
import com.meiduimall.payment.api.model.wechat.WeChatResponeModel;
import com.meiduimall.payment.api.payclient.AlipayClient;
import com.meiduimall.payment.api.payclient.HiCardClient;
import com.meiduimall.payment.api.payclient.WeChatClient;

/**
 * @author nico
 * @since 2017/2/20.
 */
@Service
public class HandlerService {

    @Value("${pay.server.ip}")
    private String serverIp;

    @Autowired
    AlipayClient alipayClient;
    @Autowired
    WeChatClient weChatClient;
    @Autowired
    HiCardClient hiCardClient;

   
    @Autowired
    PaymentTradeService tradeService;
    @Autowired
    PaymentDataService configService;
    @Autowired
    PaymentLogsService logsService;


    /**
     * 微信APP支付
     *
     * @param paramModel
     * @return
     */
    public ResBodyData handleWeChatAppSign(PaymentParamModel paramModel) throws Exception {
    	ResBodyData result = null;

        //PaymentConfigModel configModel = configService.findById(10001L);

        WeChatRequestModel requestModel = new WeChatRequestModel();
        requestModel.setBody("APP-PRODUCT");
        requestModel.setOut_trade_no(paramModel.getTradeNo());
        requestModel.setTotal_fee(Integer.parseInt(paramModel.getPayAmount()));
        requestModel.setSpbill_create_ip(serverIp);
        requestModel.setNotify_url(paramModel.getNotifyUrl());
        //签名获取预支付单
        WeChatResponeModel responeModel = weChatClient.getAppSign(requestModel,paramModel.getAccountType());
        
        if(!responeModel.getReturn_code().equalsIgnoreCase("SUCCESS")){
        	result= new ResBodyData(1,responeModel.getReturn_msg());
        }else{
        	if(!responeModel.getResult_code().equalsIgnoreCase("SUCCESS")){
        		result= new ResBodyData(1,responeModel.getErr_code_des());
        	}else{
        		//获取请求参数
        		WeChatAppModel weChatAppModel = new WeChatAppModel();
        		weChatAppModel.setPrepayid(responeModel.getPrepay_id());
        		weChatAppModel.setPkg("Sign=WXPay");
        		weChatAppModel.setTimestamp(String.valueOf(System.currentTimeMillis()).substring(0, 10));
        		WeChatAppModel rweChatAppModel = weChatClient.buildSignBody(weChatAppModel,paramModel.getAccountType());
        		
        		result = new ResBodyData(0, "success");
        		
        		Map map = new LinkedHashMap();
        		 Class<?> clazz = rweChatAppModel.getClass();
        	     Field[] fields = clazz.getDeclaredFields();
        	     List<String> list = new ArrayList<String>();
        	        for (Field f : fields) {
        	            f.setAccessible(true);
        	            if (f.get(rweChatAppModel) != null && f.get(rweChatAppModel) != "") {
        	            	if(f.getName().equalsIgnoreCase("pkg")){
        	            		map.put("package",f.get(rweChatAppModel));
        	            		continue;
        	            	}
        	            	map.put(f.getName(),f.get(rweChatAppModel));
        	            }
        	        }
        	        result.setData(map);
                //记录日志、流水
                handlePayRecord(paramModel,map.toString());
        	}
        	
        }
        
        return result;
    }


    /** 支付宝APP支付参数
     * @param paramModel
     * @return
     * @throws Exception
     */
    public ResBodyData handleAlipayAppSign(PaymentParamModel paramModel) throws Exception {
    	ResBodyData result = new ResBodyData(0 ,"success");
        

        AlipayRequestModel requestModel = new AlipayRequestModel();
        requestModel.setBody(paramModel.getBody());
        requestModel.setOut_trade_no(paramModel.getTradeNo());
        requestModel.setTotal_fee(paramModel.getPayAmount());
        requestModel.setNotify_url(paramModel.getNotifyUrl());
        requestModel.setSubject(paramModel.getSubject());
        String respondata = alipayClient.alipayTradeApp(requestModel);

        result.setData(respondata);
         //记录日志、流水
        handlePayRecord(paramModel,respondata);

        return result;
    }

    /**
     * 汇卡APP微信支付
     *
     * @param paramModel
     * @return
     * @throws Exception
     */
    public ResBodyData handleHiCardWeChatAppPay(PaymentParamModel paramModel) throws Exception {
    	ResBodyData result = null;
        HiCardRequestModel requestModel = hiCardClient.buildBody(paramModel, "006");//微信APP
        HiCardResponeModel responeModel = hiCardClient.hiCardAppTrade(requestModel);
        if(!responeModel.getRespCode().equalsIgnoreCase("00")){
        	result=new ResBodyData(1,responeModel.getRespMsg());
        }else{
        	result=new ResBodyData(0,"success");
        	result.setData(responeModel.getPayInfo());
        	 //记录日志、流水
            handlePayRecord(paramModel,new Gson().toJson(responeModel.getPayInfo()));
        }
        return result;
    }

    /**
     * 手机支付宝H5支付
     *
     * @param paramModel
     * @return
     * @throws Exception
     */
    public ResBodyData handleHiCardAlipayH5Pay(PaymentParamModel paramModel) throws Exception {
    	ResBodyData result = new ResBodyData(0, "success");
        HiCardRequestModel requestModel = hiCardClient.buildBody(paramModel, "007");
        HiCardResponeModel responeModel = hiCardClient.hiCardAppTrade(requestModel);
        if ("1".equals(responeModel.getIsHtml())) {
            result.setData(responeModel.getHtml());
        } else {
            result.setData("Request Bad.");
        }
        return result;
    }
    
    
    
    /**
     * 描述：流水、日志记录操作
     * @throws ParseException 
     */
    public void  handlePayRecord(PaymentParamModel paramModel,String sign) throws Exception{
    	 PaymenttTradeModel paymenttTradeModel = new PaymenttTradeModel();
         paymenttTradeModel.setMemId(String.valueOf((paramModel.getMemId())));
         paymenttTradeModel.setOrderInfo(paramModel.getBody());
         if(!StringUtils.isBlank(paramModel.getOrderTime())){
         	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         	paymenttTradeModel.setOrderTime(sdf.parse(paramModel.getOrderTime()));
         }
         paymenttTradeModel.setOrderId(paramModel.getOrderNo());
         paymenttTradeModel.setOrderAmount(Double.valueOf(StringUtils.isBlank(paramModel.getOrderAmount())?"0":paramModel.getOrderAmount())/100);
         paymenttTradeModel.setAmount(Double.valueOf(paramModel.getPayAmount())/100);
         paymenttTradeModel.setPayType(Integer.valueOf(paramModel.getPayType()));
         paymenttTradeModel.setCashAmount(Double.valueOf(StringUtils.isBlank(paramModel.getCashAmount())?"0":paramModel.getCashAmount()));
         paymenttTradeModel.setIntegral(Long.valueOf(StringUtils.isBlank(paramModel.getIntegral())?"0":paramModel.getIntegral()));
         paymenttTradeModel.setMerchanId(paramModel.getMerchantId());
         paymenttTradeModel.setNotifyUrl(paramModel.getNotifyUrl());
         paymenttTradeModel.setCreateTime(new Date());
         //记录流水记录
         tradeService.insertTrade(paymenttTradeModel);
      
         PaymentLogsModel model = new PaymentLogsModel();
         model.setTradeNo(paramModel.getTradeNo());
         model.setPayChannel(Integer.valueOf(paramModel.getPayChannel()));
         model.setPayType(Integer.valueOf(paramModel.getPayType()));
         model.setPayTotal(Double.valueOf(paramModel.getPayAmount())/100);
         model.setPayData(sign);
         model.setOrderId(paramModel.getOrderNo());
         model.setPaymentId(paramModel.getPaymentId());
         model.setPayStatus(0);//未支付
         model.setCreateTime(new Date());
         //记录日志信息
         logsService.insertLog(model);
        
    }

}
