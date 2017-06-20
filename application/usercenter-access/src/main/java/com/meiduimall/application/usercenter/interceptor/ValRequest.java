package com.meiduimall.application.usercenter.interceptor;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.constant.SysParamsConst;
import com.meiduimall.application.usercenter.util.HttpResolveUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;

/**
 * 校验 API请求
 * @author chencong
 *
 */
public class ValRequest {
	
	private static Logger logger = LoggerFactory.getLogger(ValRequest.class);
	
	/**API请求数据*/
	public static ThreadLocal<JSONObject> apiReqData=new ThreadLocal<>();
	
	/**
	 * 校验API请求
	 * @param request HttpServletRequest对象
	 * @param hasToken API接口方法是否有token注解
	 * @return 统一数据返回格式
	 */
	public static ResBodyData validate(HttpServletRequest request, boolean hasToken){
		logger.info("拦截器开始校验request数据和token");
		String contentType=request.getContentType();
		String method=request.getMethod();
		logger.info("API请求数据类型：{},请求方式：{},是否标识token注解：{}",contentType,method,hasToken);
		JSONObject reqJson=null;
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,null);
		/**解析请求参数，三种：get,post json,post表单*/
		try {
			if("GET".equals(method)){
				reqJson=HttpResolveUtils.readGetStringToJsonObject(request);
			}				
			if("POST".equals(method)&&contentType.contains(MediaType.APPLICATION_JSON_VALUE)){
				reqJson=HttpResolveUtils.readStreamToJsonObject(request);
			}				
			if("POST".equals(method)&&MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType)){
				reqJson=HttpResolveUtils.readPostFormToJsonObject(request);
			}
		} catch (Exception e) {
			resBodyData.setStatus(ApiStatusConst.RESOLVE_REQUEST_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.RESOLVE_REQUEST_EX));
			return resBodyData;
		}
		logger.info("拦截器解析请求后的json：{}",reqJson.toString());
		/**校验必填参数，时间戳，签名*/
		resBodyData=validateParamters(reqJson);
		
		/**判断是否来自外部，调用校验**/
		if(reqJson.containsKey("biz_id")){
			
			if(reqJson.containsKey("md_user")){
				String mdUser = (String) reqJson.get("md_user");
				if(mdUser==null || mdUser.equals("")){
					resBodyData.setStatus(ApiStatusConst.MDUSER_EMPTY);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.MDUSER_EMPTY));
					return resBodyData;
				}
			}else if(reqJson.containsKey("recharge_amout")){
				String rechargeAmout = (String) reqJson.get("recharge_amout");
				if(rechargeAmout==null || rechargeAmout.equals("")){
					resBodyData.setStatus(ApiStatusConst.RECHARGE_AMOUT_EMPTY);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.RECHARGE_AMOUT_EMPTY));
					return resBodyData;
				}
			}else if(reqJson.containsKey("recharge_type")){
				String rechargeType = (String) reqJson.get("recharge_type");
				if(rechargeType==null || rechargeType.equals("")){
					resBodyData.setStatus(ApiStatusConst.RECHARGE_TYPE_EMPTY);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.RECHARGE_TYPE_EMPTY));
					return resBodyData;
				}
			}else if(reqJson.containsKey("callback_url")){
				String callbackUrl = (String) reqJson.get("callback_url");
				if(callbackUrl==null || callbackUrl.equals("")){
					resBodyData.setStatus(ApiStatusConst.CALLBACK_URL_EMPTY);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.CALLBACK_URL_EMPTY));
					return resBodyData;
				}
			}
			
			
			String bizId = (String) reqJson.get("biz_id");
			if(bizId==null || bizId.equals("")){
				resBodyData.setStatus(ApiStatusConst.BIZID_EMPTY);
				resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.BIZID_EMPTY));
				return resBodyData;
			}else{
				if(resBodyData.getStatus()!=0)
					return resBodyData;
				resBodyData=validateTimesatamp(reqJson.getLong(SysParamsConst.REQ_TIM));
				if(resBodyData.getStatus()!=0)
					return resBodyData;
				resBodyData=validateSign(reqJson);
				if(resBodyData.getStatus()!=0)
					return resBodyData;
			}
		}
		/**校验token，将token转换为memId*/
		if (hasToken) {
			if (reqJson.containsKey(SysParamsConst.TOKEN)){
				resBodyData = ValToken.valToken(reqJson.getString(SysParamsConst.TOKEN));
				if(resBodyData.getStatus()!=0){
					return resBodyData;
				}
				String memId=resBodyData.getData().toString();
				reqJson.remove(SysParamsConst.TOKEN);
				reqJson.put(SysParamsConst.MEMID,memId);
			}
			else{
				resBodyData.setStatus(ApiStatusConst.TOKEN_NOT_EXIST);
				resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.TOKEN_NOT_EXIST));
				return resBodyData;
			}				
		}
		apiReqData.set(reqJson);
		return resBodyData;
	}
	
	/**
	 * 校验必填参数
	 * @param reqJson 请求的json格式数据
	 * @return 统一数据返回格式
	 */
	private static ResBodyData validateParamters(JSONObject reqJson){
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		if(!reqJson.containsKey(SysParamsConst.CLIENT_ID)){
			resBodyData.setStatus(ApiStatusConst.CLIENTID_EMPTY);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.CLIENTID_EMPTY));
		}
		if(!reqJson.containsKey(SysParamsConst.REQ_TIM)){
			resBodyData.setStatus(ApiStatusConst.TIMESTAMP_EMPTY);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.TIMESTAMP_EMPTY));
		}
		if(!reqJson.containsKey(SysParamsConst.SIGN)){
			resBodyData.setStatus(ApiStatusConst.SIGN_EMPTY);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.SIGN_EMPTY));
		}
		logger.info("拦截器校验必填参数戳结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**
	 * 校验时间戳
	 * @param timesatamp 13位时间戳
	 * @return 统一数据返回格式
	 */
	private static ResBodyData validateTimesatamp(Long timesatamp){
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		Long currentTime = System.currentTimeMillis();
		Long beforeTime = currentTime - SysParamsConst.REQUEST_EFFECTIVE_TIME;
		if(timesatamp.toString().length()!=13){
			resBodyData.setStatus(ApiStatusConst.TIMESTAMP_FORMAT_ERROR);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.TIMESTAMP_FORMAT_ERROR));
			return resBodyData;
		}
		if (timesatamp<beforeTime){
			resBodyData.setStatus(ApiStatusConst.REQUEST_TIMEOUT);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.REQUEST_TIMEOUT));
		}
		logger.info("拦截器校验时间戳结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**
	 * 校验签名
	 * @param reqJson 请求的json格式数据
	 * @return 统一数据返回格式
	 */
	private static ResBodyData validateSign(JSONObject reqJson){
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		if(reqJson.getString(SysParamsConst.SIGN).length()!=32){
			resBodyData.setStatus(ApiStatusConst.SIGN_FORMAT_ERROR);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.SIGN_FORMAT_ERROR));
			return resBodyData;
		}
		String clientSign=reqJson.getString(SysParamsConst.SIGN);
		String secretkey="Test123";//RedisTemplate.getJedisInstance().execGetFromCache(SysParamsConst.memberSecretJson);
		//String clientID=reqJson.getString(SysParamsConst.CLIENTID);
		//String secretkey=JSONObject.parseObject(memberSecretJson).getString(clientID);
		resBodyData = MD5Utils.getSign(reqJson,secretkey);
		if(resBodyData.getStatus()!=0)
			return resBodyData;
		if (clientSign.equals(resBodyData.getData())){
			resBodyData.setStatus(ApiStatusConst.SUCCESS);
		} else{
			resBodyData.setStatus(ApiStatusConst.SIGN_ERROR);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.SIGN_ERROR));
		}
		logger.info("拦截器校验签名结果：{}",resBodyData.toString());
		return resBodyData;
	}
}