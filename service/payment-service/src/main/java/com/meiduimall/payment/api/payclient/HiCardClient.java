package com.meiduimall.payment.api.payclient;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.core.util.XmlSupport;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.exception.SystemException;
import com.meiduimall.payment.api.constant.ServicePaymentApiCode;
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
    public String buildSign(Object object){

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer();

        for (Field f : fields) {
            f.setAccessible(true);
            try {
				if (f.get(object) != null && f.get(object) != "") {
				    sb.append(f.getName() + "=" + f.get(object) + "&");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new DaoException(ServicePaymentApiCode.CLASS_REFLECT_ERROR, ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.CLASS_REFLECT_ERROR));
			}
        }
        sb.append(key);
        String str = sb.toString();
        log.info("buildSign::Sign data -> %s", str);
       return DigestUtils.md5Hex(str);
       //return Md5Encrypt.md5Digest(str);
    }


    public HiCardRequestModel buildBody(PaymentParamModel paramModel, String type){

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
    public HiCardResponeModel hiCardAppTrade(HiCardRequestModel requestModel){

        HiCardResponeModel responeModel;

        
            String json = new Gson().toJson(requestModel);
            log.info("hiCardTrade::request data: \n%s", json);
            Map<String,String>  rmap = new HashMap<String,String>();
            rmap.put("Content-Type", "application/json");
            String result;
			try {
				result = HttpUtils.post(hiCard_gateway, json, rmap);
			} catch (IOException e) {
				throw new DaoException(ServicePaymentApiCode.HIKA_API_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.HIKA_API_ERROR));
			}
            log.info("hiCardTrade::result data: \n%s", result);
            HashMap map = null;
			try {
				map = new ObjectMapper().readValue(result, HashMap.class);
			} catch (IOException e) {
				throw new DaoException(ServicePaymentApiCode.HIKA_API_TRANS_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.HIKA_API_TRANS_ERROR));
			}
            result =XmlSupport.hashMapToJson(map);
            responeModel=new Gson().fromJson(result, HiCardResponeModel.class);
       
            return responeModel;
    }


}
