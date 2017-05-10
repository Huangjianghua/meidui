package com.meiduimall.service.account.service;

import org.omg.CORBA.SystemException;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.MSMembersPaypwd;
import com.meiduimall.service.account.model.request.RequestRetrievePaypwd;
import com.meiduimall.service.account.model.request.RequestUpdatePaypwd;

/**
 * 支付密码相关操作
 * @author chencong
 *
 */
public interface PaypwdService {
	
	/**
	 * 验证支付密码
	 * @param msMembersPaypwd 支付密码信息
	 * @return 统一数据返回格式
	 * @throws MdSysException
	 */
	ResBodyData validePaypwd(MSMembersPaypwd msMembersPaypwd) throws MdSysException;
	
	/**
	 * 设置支付密码
	 * @param msMembersPaypwd 支付密码信息
	 * @return 统一数据返回格式
	 * @throws MdSysException
	 */
	ResBodyData setPaypwd(MSMembersPaypwd msMembersPaypwd) throws MdSysException;
	
	/**
	 * 根据memId查询是否设置过支付密码
	 * @param memId 会员ID
	 * @return 1：存在  0：不存在
	 * @throws Exception
	 */
	ResBodyData isExistPaypwd(String memId);
	
	/**
	 * 修改支付密码
	 * @param requestUpdatePaypwd 修改支付密码请求映射实体
	 * @return 统一数据返回格式
	 * @throws MdSysException 检查类型异常
	 */


	ResBodyData updatePaypwd(RequestUpdatePaypwd requestUpdatePaypwd) throws  MdSysException;
	
	/**
	 * 找回支付密码
	 * @param requestRetrievePaypwd 修改支付密码请求映射实体
	 * @return 统一数据返回格式
	 * @throws SystemException 检查类型异常
	 * @throws MdSysException 
	 */
	void retrievePaypwd(RequestRetrievePaypwd requestRetrievePaypwd) throws MdSysException;
}
