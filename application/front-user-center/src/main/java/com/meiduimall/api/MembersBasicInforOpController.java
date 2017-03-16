/*package com.meiduimall.api;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.constant.HttpRequstConst;
import com.meiduimall.constant.OauthConst;
import com.meiduimall.constant.SysParaNameConst;
import com.meiduimall.oauth.OauthValidate;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.util.HttpClientUtil;
import com.meiduimall.util.Logger;
import com.meiduimall.util.SystemConfig;

*//**
 * 获取用户基本信息
 * @author chencong
 *
 *//*
@RestController
@RequestMapping("/membersbasicinforop")
public class MembersBasicInforOpController extends BaseController{
	
	@Autowired
	private HttpServletResponse response;
	
	*//**
	 * 获取当前会员基本信息
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getmemberbasicinfo",method=RequestMethod.GET)
	void getmemberbasicinfo() throws Exception {	
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
			
			*//**从本地变量获取已解析过的json**//*
			JSONObject j=OauthValidate.postjson.get();
			Logger.info("接收登录请求："+j.toString());
			
			*//**转发请求到服务层**//*
			String url=SystemConfig.getInstance().configMap.get("Login_Url_Dev");
			//头部
			Map<String,String> heads=new HashMap<>();
			heads.put(HttpRequstConst.CONTENT_TYPE,HttpRequstConst.MEDIATYPE_JSON_FOR_APP);
			String result=HttpClientUtil.doPost(url, j.toString(),heads);
			Logger.info("用户"+j.getString("user_id")+"请求结果"+result);
			pw.print(result);
		} catch (Exception e) {
			pw.println(getServerError(e));
		}
	    }

}
*/