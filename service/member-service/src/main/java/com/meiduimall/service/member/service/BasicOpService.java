package com.meiduimall.service.member.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.model.request.RequestLogin;
import com.meiduimall.service.member.model.request.RequestRegister;
import com.meiduimall.service.member.model.request.RequestRegisterNoCode;
import com.meiduimall.service.member.model.request.RequestRegisterO2O;

/**
 * 用户基本操作，登录，注册，退出，检查token
 * @author chencong
 *
 */
public interface BasicOpService {

	/**给旧会员系统用的临时接口*/
	public Map<String, Object> getput(JSONObject jsonObject) throws Exception;
	public Map<String, Object> handlesignout(JSONObject jsonObject) throws Exception;
	
	/**
	 * 普通会员注册
	 * @param model 普通会员注册请求映射实体
	 * @return 统一数据返回格式
	 * @throws MdSysException 
	 */
	public ResBodyData register(RequestRegister model) throws MdSysException;
	
	/**
	 * 扫码注册
	 * @param model 普通会员注册请求映射实体
	 * @return 统一数据返回格式
	 * @throws MdSysException 
	 */
	public ResBodyData registerScanCode(RequestRegister model) throws MdSysException;
	
	/**
	 * O2O系统注册
	 * @param model 普通会员注册请求映射实体
	 * @return 统一数据返回格式
	 * @throws MdSysException 
	 */
	public ResBodyData registerO2O(RequestRegisterO2O model) throws MdSysException;
	
	public ResBodyData login(RequestLogin requestLogin) throws MdSysException;
	
	
	/**
	 * 扫码注册
	 * @param model 普通会员注册请求映射实体
	 * @return 统一数据返回格式
	 * @throws MdSysException 
	 */
	public ResBodyData registerNoCheckCode(RequestRegisterNoCode model) throws MdSysException;

}
