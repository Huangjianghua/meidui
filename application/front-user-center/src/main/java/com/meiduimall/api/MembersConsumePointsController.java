package com.meiduimall.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.annotation.HasToken;
import com.meiduimall.constant.ApiRespConst;
import com.meiduimall.constant.HttpReqConst;
import com.meiduimall.interceptor.ValInterceptor;

import com.meiduimall.oauth.OauthValidate;
import com.meiduimall.util.HttpClientUtil;
import com.meiduimall.util.Logger;
import com.meiduimall.util.SystemConfig;

/**
 * 1，新增余额流水接口 分页
 * 2，积分 流水接口  分页  
 * @author jun.wu@meiduimall.com
 *
 */
@RestController
@RequestMapping("/member/front_user_center/v1")
public class MembersConsumePointsController {

	@Autowired
	private HttpServletResponse response;
	
	/**
	 * 积分 流水接口  分页  
	 * @param mSConsumePointsDetail
	 * @return
	 * @throws Exception
	 */
	@HasToken
	@RequestMapping(value="/list_consume_points_detail")
    String listConsumePointsDetail() throws Exception{
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");

			/**从本地变量获取已解析过的json**/
			JSONObject requestj=OauthValidate.postjson.get();
			JSONObject check_result=ValInterceptor.check_result.get();

			/**如果拦截器校验失败，直接返回**/
			if(!"0".equals(check_result.getString(ApiRespConst.STATUS_CODE)))
				return check_result.toString();
			Logger.info("积分流水接口请求json参数："+requestj.toString());
			

			/**转发请求到服务层**/
			String url=SystemConfig.getInstance().configMap.get("PointWater_Url_Dev");
			Logger.info("请求网关的URL："+url);
			//头部
			Map<String,String> heads=new HashMap<>();
			heads.put(HttpReqConst.CONTENT_TYPE,HttpReqConst.MEDIATYPE_JSON_FOR_APP);//如果请求方式是Post json，网关要求必带此头部
			String result=HttpClientUtil.doPost(url, requestj.toString(),heads);
			Logger.info("请求结果："+result);
			return result;
		} catch (Exception e) {
			return ApiRespConst.ERROR_MSG;
		}
	}
	
	/**
	 * 新增余额流水接口 分页
	 * @param mSAccountDetail
	 * @return
	 * @throws Exception
	 */
	@HasToken
	@RequestMapping(value="/list_account_detail")
	String listMSAccountDetail() throws Exception{
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");

			/**从本地变量获取已解析过的json**/
			JSONObject requestj=OauthValidate.postjson.get();
			JSONObject check_result=ValInterceptor.check_result.get();

			/**如果拦截器校验失败，直接返回**/
			if(!"0".equals(check_result.getString(ApiRespConst.STATUS_CODE)))
				return check_result.toString();
			Logger.info("余额流水接口请求json参数："+requestj.toString());
			

			/**转发请求到服务层**/
			String url=SystemConfig.getInstance().configMap.get("MoneyWater_Url_Dev");
			Logger.info("请求网关的URL："+url);
			//头部
			Map<String,String> heads=new HashMap<>();
			heads.put(HttpReqConst.CONTENT_TYPE,HttpReqConst.MEDIATYPE_JSON_FOR_APP);//如果请求方式是Post json，网关要求必带此头部
			String result=HttpClientUtil.doPost(url, requestj.toString(),heads);
			Logger.info("请求结果："+result);
			return result;
		} catch (Exception e) {
			return ApiRespConst.ERROR_MSG;
		}
	}
}
