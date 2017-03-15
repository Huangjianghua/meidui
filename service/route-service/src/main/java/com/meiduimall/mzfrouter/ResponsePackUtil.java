package com.meiduimall.mzfrouter;
import com.alibaba.fastjson.JSON;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
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
        ctx.setResponseStatusCode(Constants.UNAUTHORIZED);
        ResBodyData res=new ResBodyData(responseCode,BaseApiCode.getZhMsg(responseCode));
        ctx.setResponseBody(JSON.toJSONString(res));
	}
	
}

