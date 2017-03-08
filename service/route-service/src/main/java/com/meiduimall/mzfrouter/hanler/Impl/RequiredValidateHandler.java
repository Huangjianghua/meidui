package com.meiduimall.mzfrouter.hanler.Impl;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.meiduimall.BaseApiCode;
import com.meiduimall.mzfrouter.ResponsePackUtil;
import com.meiduimall.mzfrouter.hanler.Handler;
import com.netflix.zuul.context.RequestContext;

public class RequiredValidateHandler implements Handler{
	
	private static Logger log = LoggerFactory.getLogger(RequiredValidateHandler.class);
	
	
	/**
	 * 功能描述:   验证必填参数
	 * Author:  陈建宇
	 * Date:    2016年12月19日 上午10:16:21
	 * @param   ctx
	 * @return  Boolean     
	 */
	@Override
	public Boolean process(RequestContext ctx ) {
		HttpServletRequest request = ctx.getRequest();
		Map<String, String> param = (Map<String, String>) ctx.get("param");
		String clientID = param.get("clientID");
		if (StringUtils.isEmpty(clientID)) {
			log.info("clientId必填验证处理层,url:{},请求参数:{}", request.getRequestURL().toString(),
					JSON.toJSONString(param));
			ResponsePackUtil.responseWrapper(ctx, BaseApiCode.CLIENTID_PARAM_INVALID);
			return false;
		}
		String timestamp = param.get("timestamp");
		if (StringUtils.isEmpty(timestamp) || (!NumberUtils.isNumber(timestamp))) {
			log.info("timestamp必填验证处理层,url:{},请求参数:{}", request.getRequestURL().toString(),
					JSON.toJSONString(param));
			ResponsePackUtil.responseWrapper(ctx, BaseApiCode.TIMESTAMP_PARAM_INVALID);
			return false;
		}
		String sign = param.get("sign");
		if (StringUtils.isEmpty(sign)) {
			log.info("sign必填验证处理层,url:{},请求参数:{}", request.getRequestURL().toString(),
					JSON.toJSONString(param));
			ResponsePackUtil.responseWrapper(ctx, BaseApiCode.SIGN_PARAM_INVALID);
			return false;
		}
		return true;
	}

}
