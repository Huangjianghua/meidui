package com.meiduimall.oauth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.alibaba.fastjson.JSONObject;
import com.meiduimall.constant.HttpReqConst;
import com.meiduimall.constant.OauthConst;
import com.meiduimall.constant.ApiRespConst;
import com.meiduimall.util.HttpClientUtil;
import com.meiduimall.util.Logger;
import com.meiduimall.util.MD5Util;
import com.meiduimall.util.StringUtil;
import com.meiduimall.util.SystemConfig;

/**
 * 
 * @author fengkaixuan
 *
 */
public class OauthValidate {
	
	/*存储在拦截器中已经解析过的post json数据，API接口处直接调用，避免重复转换*/
	public static ThreadLocal<JSONObject> postjson=new ThreadLocal<>();
	
	@SuppressWarnings("static-access")
	public static JSONObject validate(HttpServletRequest request, boolean hasToken) {
		JSONObject json = new JSONObject();
		JSONObject requestj=null;
		
		boolean is_old_app=false;//老APP请求，临时代码
		
		List<Parameter> parameters=null;
		try {
			boolean tokenFlag = true;
			parameters = new ArrayList<Parameter>();
			Parameter token = null;
			
			Logger.info("APP请求类型："+request.getContentType()+request.getMethod());
			
			/**组装请求参数**/
			//如果请求方式是get，只认key value形式的请求
			if(request.getMethod().equals(HttpReqConst.GET))
			{
				requestj=HttpClientUtil.readGetStringToJsonObject(request);
				for(String key:requestj.keySet())
				{
					parameters.add(new Parameter(key,requestj.getString(key)));
					if (OauthConst.TOKEN.equals(key))
						token = new Parameter(key,requestj.getString(key));
				}
				//临时代码，适配get key value不带timestamp,clientID,sign参数的请求
				if(!requestj.containsKey(OauthConst.CLIENT_ID)||!requestj.containsKey(OauthConst.TIMESATAMP)||!requestj.containsKey(OauthConst.SIGN))
				{
					is_old_app=true;
				}
			}
			//如果请求方式是post，只认json格式的数据请求
			else if(request.getMethod().equals(HttpReqConst.POST))
			{
				if(!StringUtil.isEmptyByString(request.getContentType()))
				{
					if(request.getContentType().contains(HttpReqConst.MEDIATYPE_JSON))
					{
						requestj=HttpClientUtil.readStreamToJsonObject(request);
						for(String key:requestj.keySet())
						{
							parameters.add(new Parameter(key,requestj.getString(key)));
							if (OauthConst.TOKEN.equals(key))
								token = new Parameter(key,requestj.getString(key));
						}
					}
					else
					{
						//适配老APP临时添加，老版本的APP是post keyvalue的形式请求，等老的APP都强制升级后再删除
						requestj=HttpClientUtil.readPostFormToJsonObject(request);
						Logger.info("解析后的JSON："+requestj.toString());
						for(String key:requestj.keySet())
						{
							parameters.add(new Parameter(key,requestj.getString(key)));
							if (OauthConst.TOKEN.equals(key))
								token = new Parameter(key,requestj.getString(key));
						}
						is_old_app=true;
					}
					
				}
				else
				{
					/*json.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.NOT_JSON_FORMAT_REQUEST);
					json.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.NOT_JSON_FORMAT_REQUEST_C);
					return json;*/
				}
			}
			
			/**如果接口需要token,就校验token,注意如果通过接入层请求checktoken这个接口，不能加@hasToken这个注解**/
			if (hasToken) {
				if (null != token) 
				{
					JSONObject valToken = OauthToken.validateToken(token.getValue());
					if (!"0".equals(valToken.get(ApiRespConst.STATUS_CODE))) {
						return valToken;
					}
					else
					{
						requestj.put("memId",valToken.getString(ApiRespConst.RESULT));
						parameters.add(new Parameter("memId",valToken.getString(ApiRespConst.RESULT)));//memId也要用于生成签名
						//移除token
						for (Parameter parameter : parameters) {
							if(parameter.getKey().equals(OauthConst.TOKEN))
								parameters.remove(parameter);
						}
						requestj.remove(OauthConst.TOKEN);
					}
				}
				else
				{
					json.put(ApiRespConst.STATUS_CODE, "9998");
					json.put(ApiRespConst.RESULT_MSG, "缺少请求参数,token不能为空!");
					Logger.info("缺少请求参数,token为空!");
					tokenFlag = false;
				}
				
			}
			/**临时取消时间戳，签名验证码，因为老的APP没有做这些校验**/
			/*if (tokenFlag) {
				JSONObject valParam = OauthValidate.validateParamters(parameters);
				if ("0".equals(valParam.get(SysParaNameConst.STATUS_CODE))) {
					JSONObject result = valParam.getJSONObject(SysParaNameConst.RESULT);
					if (StringUtil.checkObj(result.get(OauthConst.TIMESATAMP))) {
						JSONObject valTimesatamp = OauthValidate.validateTimesatamp(Long.valueOf(result.get(OauthConst.TIMESATAMP).toString()));
						if ("0".equals(valTimesatamp.get(SysParaNameConst.STATUS_CODE))) {
							if (StringUtil.checkObj(result.get(OauthConst.SIGN))) {
								JSONObject valSign = OauthValidate.validateSign(parameters, result.get(OauthConst.SIGN).toString());
								if ("0".equals(valSign.get(SysParaNameConst.STATUS_CODE))) {
									json.put(SysParaNameConst.STATUS_CODE, "0");
									json.put(SysParaNameConst.RESULT_MSG, "签名验证通过!");
								} else {
									return valSign;
								}
							} else {
								json.put(SysParaNameConst.STATUS_CODE, "9998");
								json.put(SysParaNameConst.RESULT_MSG, "签名不正确!");
								Logger.info("签名参数为空!");
							}
						} else {
							return valTimesatamp;
						}
					} else {
						json.put(SysParaNameConst.STATUS_CODE, "9998");
						json.put(SysParaNameConst.RESULT_MSG, "时间戳不正确!");
						Logger.info("时间戳参数为空!");
					}
				} else {
					return valParam;
				}
			}*/
		} catch (Exception e) {
			json.put(ApiRespConst.STATUS_CODE, "9999");
			json.put(ApiRespConst.RESULT_MSG, "服务器错误!");
			Logger.error("签名验证错误: %s", e.getMessage());
		}
		/**更换clientID,key,timestamp,重新生成签名,本地变量加入解析过的json**/
		String signMiddle;
		String clienIDMiddle=SystemConfig.getInstance().configMap.get("CLIENTID_MIDDLE");
		String timeStampMiddle=String.valueOf(System.currentTimeMillis());
		String keyMiddle=SystemConfig.getInstance().configMap.get("KEY_MIDDLE");
		try {
			if(is_old_app)
			{
				parameters.add(new Parameter(OauthConst.CLIENT_ID,clienIDMiddle));
				parameters.add(new Parameter(OauthConst.TIMESATAMP,timeStampMiddle));
				parameters.add(new Parameter(OauthConst.SIGN,""));
			}
			signMiddle = MD5Util.getSign(parameters,keyMiddle,clienIDMiddle,timeStampMiddle,2);
			requestj.put("clientID", clienIDMiddle);
			requestj.put("timestamp",timeStampMiddle);
			requestj.put("sign",signMiddle);
			json.put(ApiRespConst.STATUS_CODE, "0");
			json.put(ApiRespConst.RESULT_MSG, "success");
			postjson.set(requestj);
		} catch (Exception e) {
			json.put(ApiRespConst.STATUS_CODE, "9999");
			json.put(ApiRespConst.RESULT_MSG, "服务器错误!");
			Logger.error("转发请求生成签名错误: %s", e.getMessage());
		}
		return json;
	}
	
	/**
	 * Description : 验证参数
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午3:50:17 
	 *  
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static JSONObject validateParamters(Collection<? extends Map.Entry<String, String>> parameters) throws Exception{
		JSONObject json = new JSONObject();
		JSONObject result = new JSONObject();
		if (!StringUtil.isEmptyByCollection(parameters)) {
			for (Map.Entry<String, String> p : parameters) {
				if (StringUtil.checkStr(p.getKey()) && StringUtil.checkStr(p.getValue())) {
					if (OauthConst.TIMESATAMP.equals(p.getKey()))
						result.put(p.getKey(), p.getValue());
					if (OauthConst.SIGN.equals(p.getKey()))
						result.put(p.getKey(), p.getValue());
				} else {
					json.put(ApiRespConst.STATUS_CODE, "9998");
					json.put(ApiRespConst.RESULT_MSG, "缺少认证参数!");
					Logger.info("参数名或值为空!");
					return json;
				}
			}
			json.put(ApiRespConst.STATUS_CODE, "0");
			json.put(ApiRespConst.RESULT_MSG, "参数验证通过");
			json.put(ApiRespConst.RESULT, result);
			Logger.info("参数验证通过!");
		} else {
			json.put(ApiRespConst.STATUS_CODE, "9998");
			json.put(ApiRespConst.RESULT_MSG, "缺少认证参数!");
			Logger.info("参数集合为空!");
		}
		return json;
	}
	
	/**
	 * Description : 验证时间戳, 请求到当前时间不超过5分钟
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午5:00:28 
	 * 
	 * @param timesatamp
	 * @return
	 * @throws Exception
	 */
	public static JSONObject validateTimesatamp(Long timesatamp) {
		JSONObject json = new JSONObject();
		try {
			Long currentTime = System.currentTimeMillis();
			Long beforeTime = currentTime - OauthConst.REQUEST_EFFECTIVE_TIME;
			if (timesatamp >= beforeTime) {
				json.put(ApiRespConst.STATUS_CODE, "0");
				json.put(ApiRespConst.RESULT_MSG, "时间戳验证通过!");
				Logger.info("时间戳验证通过!");
			} else {
				json.put(ApiRespConst.STATUS_CODE, "9998");
				json.put(ApiRespConst.RESULT_MSG, "缺少认证参数!");
				Logger.info("请求超时!");
			}
		} catch (Exception e) {
			json.put(ApiRespConst.STATUS_CODE, "9999");
			json.put(ApiRespConst.RESULT_MSG, "服务器错误!");
			Logger.error("时间戳验证错误: %s", e.getMessage());
		}
		return json;
	}
	
	/**
	 * Description : 验证签名
	 * Created By : Kaixuan.Feng 
	 * Creation Time : 2016年12月15日 下午5:01:21  
	 * 
	 * @param parameters
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public static JSONObject validateSign(Collection<? extends Map.Entry<String, String>> parameters, String sign) {
		JSONObject json = new JSONObject();
		try {
			String key_app=SystemConfig.getInstance().configMap.get("KEY_APP");
			String syssign=MD5Util.getSign(parameters,key_app,null,null,1);
			if (sign.equals(syssign)) {
				json.put(ApiRespConst.STATUS_CODE, "0");
				json.put(ApiRespConst.RESULT_MSG, "签名验证通过!");
				Logger.info("签名验证成功!");
			} else {
				json.put(ApiRespConst.STATUS_CODE, "9998");
				json.put(ApiRespConst.RESULT_MSG, "签名校验失败!");
				Logger.info("签名无效!");
			}
		} catch (Exception e) {
			json.put(ApiRespConst.STATUS_CODE, "9999");
			json.put(ApiRespConst.RESULT_MSG, "服务器错误!");
			Logger.error("验证签名错误: %s", e.getMessage());
		}
		return json;
	}
}