/*package com.meiduimall.api;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.oauth.OauthValidate;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.util.HttpClientUtil;
import com.meiduimall.util.Logger;
import com.meiduimall.util.SystemConfig;

*//**
 * 推荐人和粉丝相关
 * @author chencong
 *
 *//*
@RestController
@RequestMapping("/sharemenandfunsinfo")
public class ShareMenAndFunsController extends BaseController{
	
	@Autowired
	private HttpServletResponse response;
	
	*//**
	 * 获取会员二级推荐人接口 http://IP:PORT/Authorized/querySecondLevelShareMem
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/getsecondlevelsharemen",method=RequestMethod.GET)
	public void getsecondlevelsharemen() throws Exception {		
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
			*//**从本地变量获取已解析过的json**//*
			JSONObject j=OauthValidate.postjson.get();
			Logger.info("接收登录请求："+j.toString());
			
			*//**转发请求到服务层**//*
			@SuppressWarnings("static-access")
			Map  map=j;
			@SuppressWarnings("static-access")
			String url=SystemConfig.getInstance().configMap.get("GetSecondLevelsShareMen_Url_Dev");
			String result=HttpClientUtil.doGet(url,map,null);
			Logger.info("请求结果"+result);
			pw.print(result);
		} catch (Exception e) {
			pw.println(getServerError(e));
		}
	}
	
	
	*//**
	 * 粉丝明细接口 http://IP:PORT/ AuthorizationMembers/fansDetailList
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/funsdetaillist",method=RequestMethod.GET)
	public void funsdetaillist() throws Exception {
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
			JSONObject j=OauthValidate.postjson.get();
			Logger.info("接收登录请求："+j.toString());
			
			*//**转发请求到服务层**//*
			@SuppressWarnings("static-access")
			Map  map=j;
			String url=SystemConfig.getInstance().configMap.get("FunsDetailList_Url_Dev");
			String result=HttpClientUtil.doGet(url,map,null);
			Logger.info("请求结果"+result);
			pw.print(result);
		} catch (Exception e) {
			pw.println(getServerError(e));
		}
	}
	
	*//**
	 * 粉丝数量接口 http://IP:PORT/AuthorizationMembers/funscountbylevel
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/funscountbylevel",method=RequestMethod.GET)
	public void funscountbylevel() throws Exception {		
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
			JSONObject j=OauthValidate.postjson.get();
			Logger.info("接收登录请求："+j.toString());
			
			*//**转发请求到服务层**//*
			@SuppressWarnings("static-access")
			Map  map=j;
			String url=SystemConfig.getInstance().configMap.get("FunScountByLevel_Url_Dev");
			String result=HttpClientUtil.doGet(url,map,null);
			Logger.info("请求结果"+result);
			pw.print(result);
		} catch (Exception e) {
			pw.println(getServerError(e));
		}
	}
}*/