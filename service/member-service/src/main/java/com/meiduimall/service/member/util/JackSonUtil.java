package com.meiduimall.service.member.util;

import java.util.Map;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.constant.ConstApiStatus;

public class JackSonUtil {

	@SuppressWarnings("unchecked")
	public static Map<String,Object> getJsonMap(Object obj) throws MdSysException{
		String json=JsonUtils.beanToJson(obj);
		try {
			return JsonUtils.getInstance().readValue(json,Map.class);
		} catch (Exception e) {
			throw new MdSysException(ConstApiStatus.ACCOUNT_EXCEPTION);
		}
	}
}
