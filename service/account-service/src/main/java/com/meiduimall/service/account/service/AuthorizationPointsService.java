package com.meiduimall.service.account.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
/**
 * @author  feixiaojie
 * 类名:  IAuthorizationPointsServices<br>
 * 描述:  会员积分相关服务接口，与积分直接关系的业务都在此类中定义<br>
 * 创建时间: 2016-11-30
 */
public interface AuthorizationPointsService
{
	/**
	 * 方法名: getPointsDetailOfToken<br>
	 * 描述:   获取会员积分明细,需要token校验<br>
	 * 创建时间: 2016-12-2
	 * @param jsonobj
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String getPointsDetailOfToken(JSONObject jsonobj,HttpServletRequest request, HttpServletResponse response)throws Exception;
}
