package com.meiduimall.application.mall.pay.alipay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.meiduimall.application.mall.util.Logger;


public class AlipayNotify {
 


	/**
     * 支付宝消息验证地址
     */
    private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";


    /**
     * 根据反馈回来的信息，生成签名结果
     * @param params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
    public static boolean getSignVeryfy(Map<String, String> params, String sign) {
    //过滤空值、sign与sign_type参数
    Map<String, String> sParaNew = AlipayCore.paraFilter(params);
        //获取待签名字符串
        String preSignStr = AlipayCore.createLinkString(sParaNew);
        //获得签名验证结果
        boolean isSign = false;
        if("RSA".equals(AlipayConfig.SIGN_TYPE)){
        isSign = RSA.verify(preSignStr, sign, AlipayConfig.ALI_PAYPUBLIC_KEY, AlipayConfig.INPUT_CHARSET);
        }
        return isSign;
    }


    /**
    * 获取远程服务器ATN结果,验证返回URL
    * @param notifyId 通知校验ID
    * @return 服务器ATN结果
    * 验证结果集：
    * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
    * true 返回正确信息
    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    */
    public static String verifyResponse(String notifyId) {
        //获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求


        String partner = AlipayConfig.PARTNER;
        String veryfyUrl = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notifyId;


        return checkUrl(veryfyUrl);
    }


    /**
    * 获取远程服务器ATN结果
    * @param urlvalue 指定URL路径地址
    * @return 服务器ATN结果
    * 验证结果集：
    * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
    * true 返回正确信息
    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    */
    public static String checkUrl(String urlvalue) {
        String inputLine = "";


        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
                .getInputStream()));
            inputLine = in.readLine();
        } catch (Exception e) {
            Logger.error("system error: %s",e);
            inputLine = "";
        }


        return inputLine;
    }
}
