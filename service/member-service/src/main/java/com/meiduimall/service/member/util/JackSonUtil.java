package com.meiduimall.service.member.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.constant.ConstApiStatus;

/**
 * jackson工具类
 * @author chencong
 *
 */
public class JackSonUtil {

	private final static Logger logger=LoggerFactory.getLogger(JackSonUtil.class);
	
	/**
	 * 将{@link=ResBodyData}的data字段解析成Map
	 * @param obj ResBodyData的data字段
	 * @return 解析后的Map
	 * @throws MdSysException 系统异常
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getJsonMap(Object obj) throws MdSysException{
		String json=JsonUtils.beanToJson(obj);
		try {
			return JsonUtils.getInstance().readValue(json,Map.class);
		} catch (Exception e) {
			logger.error("执行getJsonMap()方法异常：{}",e.toString());
			throw new MdSysException(ConstApiStatus.JSON_PARSE_EXCEPTION);
		}
	}
}
