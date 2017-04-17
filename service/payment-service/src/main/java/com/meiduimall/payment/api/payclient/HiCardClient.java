package com.meiduimall.payment.api.payclient;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.core.util.XmlSupport;
import com.meiduimall.exception.ApiException;
import com.meiduimall.payment.api.model.api.PaymentParamModel;
import com.meiduimall.payment.api.model.hicardpay.HiCardRequestModel;
import com.meiduimall.payment.api.model.hicardpay.HiCardResponeModel;

/**
 * Created by nico on 2017/2/22.
 */
@Component
public class HiCardClient {
	
	private static Logger log = LoggerFactory.getLogger(HiCardClient.class);
	
	@Value("${pay.huik.mchid.app}")
    private  String hiCard_merchNo;
	@Value("${pay.huik.orgid.app}")
    private  String hiCard_organNo;

    

	@Value("${pay.huik.gateway}")
    private  String hiCard_gateway;
	@Value("${pay.huik.key}")
    private  String key;
    /**
     * 生成签名
     *
     * @param object
     * @return
     */
    public String buildSign(Object object) throws Exception {

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer();

        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(object) != null && f.get(object) != "") {
                sb.append(f.getName() + "=" + f.get(object) + "&");
            }
        }
        sb.append(key);
        String str = sb.toString();
        log.info("buildSign::Sign data -> %s", str);
       return DigestUtils.md5Hex(str);
       //return Md5Encrypt.md5Digest(str);
    }


    public HiCardRequestModel buildBody(PaymentParamModel paramModel, String type) throws Exception {

        HiCardRequestModel requestModel = new HiCardRequestModel();

        requestModel.setVersion("V001");
        requestModel.setOrganNo(hiCard_organNo);
        requestModel.setHicardMerchNo(hiCard_merchNo);
        requestModel.setPayType(type);
        requestModel.setBizType("180");
        requestModel.setMerchOrderNo(paramModel.getOrderNo());
        requestModel.setShowPage("0");
        requestModel.setAmount(paramModel.getOrderAmount());
        // requestModel.setFrontEndUrl(hiCard_return_url);
        requestModel.setBackEndUrl(paramModel.getNotifyUrl());
        requestModel.setSign(buildSign(requestModel));
        requestModel.setGoodsName(paramModel.getBody());
        return requestModel;
    }


    /**
     * 汇卡支付接口
     *
     * @param requestModel
     * @throws Exception
     */
    public HiCardResponeModel hiCardAppTrade(HiCardRequestModel requestModel) throws Exception {

        HiCardResponeModel responeModel;

        try {
            String json = new Gson().toJson(requestModel);
            log.info("hiCardTrade::request data: \n%s", json);
            String result = HttpUtils.post(hiCard_gateway, json, null);
            log.info("hiCardTrade::result data: \n%s", result);
            HashMap map = new ObjectMapper().readValue(result, HashMap.class);
            result =XmlSupport.hashMapToJson(map);
            responeModel=new Gson().fromJson(result, HiCardResponeModel.class);
            
        } catch (Exception e) {
            throw new ApiException(e);
        }
        return responeModel;
    }


}
