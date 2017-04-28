package com.meiduimall.service.account.service;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.account.model.ResBodyData;
import com.meiduimall.service.account.model.request.RequestRetrievePaypwd;

/**
 * 调用短信服务
 * @author chencong
 *
 */
public interface SmsService {
	
	/**
	 * 获取短信验证码
	 * @param requestRetrievePaypwd 修改支付密码请求映射实体
	 * @return 统一数据返回格式
	 * @throws SystemException 检查类型异常
	 */
	ResBodyData getValidateCode(RequestRetrievePaypwd requestRetrievePaypwd) throws SystemException;
}
