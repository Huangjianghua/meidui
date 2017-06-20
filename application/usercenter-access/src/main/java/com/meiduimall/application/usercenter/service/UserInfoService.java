package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * 会员账号信息业务逻辑接口
 * @author chencong
 *
 */
public interface UserInfoService {

	/**
	 * 根据memdId查询会员基本信息
	 * @param reqJson 请求的数据
	 * @return 统一数据返回格式
	 * @throws MdSysException 系统异常
	 */
	 ResBodyData getmemberbasicinfo(JSONObject reqJson) throws MdSysException;

	/**
	 * 修改会员基本信息
	 * @param reqJson 请求的数据
	 * @return 统一数据返回格式
	 * @throws MdSysException 系统异常
	 */
	ResBodyData updateMemberBasicInfo(JSONObject reqJson) throws MdSysException;
	
}
