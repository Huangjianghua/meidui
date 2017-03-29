package com.meiduimall.mzfrouter.hanler.Impl;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.util.ExceptionUtils;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.mzfrouter.ResponsePackUtil;
import com.meiduimall.mzfrouter.hanler.Handler;
import com.netflix.zuul.context.RequestContext;

public class PraseJsonHandler implements Handler {

	private static Logger log = LoggerFactory.getLogger(PraseJsonHandler.class);
	
	
	/**
	 * 功能描述:  解析封装json格式的参数
	 * Author: 陈建宇
	 * Date:   2016年12月19日 上午10:15:44
	 * @param  ctx
	 * @return Boolean     
	 */
	@Override
	public Boolean process(RequestContext ctx) {
		HttpServletRequest request = ctx.getRequest();
		String json = null;
		InputStream in=null;
		try {
			in=ctx.getRequest().getInputStream();
			json = CharStreams.toString(new InputStreamReader(in));
			Map param=JsonUtils.jsonToMap(json, String.class);
			ctx.set("param", param);
		} catch (Exception e) {
			log.error("请求参数Json格式解析处理层,url:{},请求body:{},异常信息:{}",request.getRequestURL().toString(),
					json, ExceptionUtils.getFullStackTrace(e));
			ResponsePackUtil.responseWrapper(ctx, BaseApiCode.EXCEPTION_PRASE_JSON);
			return false;
		}finally{
			Closeables.closeQuietly(in);
		}
		log.info("请求参数Json格式解析处理层,url:{},请求body:{}",request.getRequestURL().toString(),
				json);
		return true;
	}

}
