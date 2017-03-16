package com.meiduimall.mzfrouter;


import org.apache.http.HttpStatus;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JacksonUtil;
import com.netflix.zuul.context.RequestContext;

/**
 * test
 * @author chencong
 *
 */
public class ResponsePackUtil {
	/**
	 * 功能描述:  封装网关层返回信息
	 * Author: 陈建宇
	 * Date:   2016年12月19日 上午10:11:07
	 * @param  ctx
	 * @param  responseCode    
	 */
	public static void responseWrapper(RequestContext ctx,Integer responseCode){
		ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        ResBodyData res=new ResBodyData(responseCode,BaseApiCode.getZhMsg(responseCode));
        ctx.setResponseBody(JacksonUtil.beanToJson(res));
	}
	
}

