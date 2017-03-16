package com.meiduimall.mzfrouter.hanler.Impl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.Constants;
import com.meiduimall.core.util.ExceptionUtils;
import com.meiduimall.core.util.JacksonUtil;
import com.meiduimall.mzfrouter.ResponsePackUtil;
import com.meiduimall.mzfrouter.hanler.Handler;
import com.meiduimall.redis.util.JedisUtil;
import com.netflix.zuul.context.RequestContext;

public class BlackListValidateHandler implements Handler{
	
	private static Logger log = LoggerFactory.getLogger(BlackListValidateHandler.class);
	
	
	/**
	 * 功能描述:  验证请求url是不是在黑名单中
	 * Author: 陈建宇
	 * Date:   2017年2月22日 上午10:02:55
	 */
	@Override
	public Boolean process(RequestContext ctx) {
		HttpServletRequest request = ctx.getRequest();
		try {
			String blackListJson = JedisUtil.getJedisInstance().execGetFromCache(Constants.BLACK_LIST_JSON);
			List<String> blackList=JacksonUtil.jsonToList(blackListJson, String.class);
			if(CollectionUtils.isNotEmpty(blackList)&&isBlackList(request.getRequestURL().toString(),blackList)){
				log.info("黑名单验证处理层,url:{},黑名单:{}",request.getRequestURL().toString(), blackListJson);
				ResponsePackUtil.responseWrapper(ctx, BaseApiCode.FAIL_BLACKLIST_VALIDATE);
				return false;
			}
		} catch (Throwable e) {
			log.error("黑名单验证处理层异常,url:{},异常:{}",request.getRequestURL().toString(),ExceptionUtils.getFullStackTrace(e));
			ResponsePackUtil.responseWrapper(ctx, BaseApiCode.EXCEPTION_BLACKLIST);
			return false;
		}
		return true;
	}
	
	
	private Boolean isBlackList(String url, List<String> blackList) {
		for (String key : blackList) {
			if (url.contains(key)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 功能描述:  解析ant风格表达式
	 * Author: 陈建宇
	 * Date:   2017年2月22日 上午11:06:53   
	 * return  Boolean
	 */
	private Boolean isBlackListByAnt(String url, List<String> blackList){
		PathMatcher matcher = new AntPathMatcher();
		for (String key : blackList) {
			if (matcher.match(key, url)) {
				return true;
			}
		}
		return false;
	}

}
