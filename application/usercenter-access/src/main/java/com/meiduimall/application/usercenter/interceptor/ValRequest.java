package com.meiduimall.application.usercenter.interceptor;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.config.ProfileParamsConfig;
import com.meiduimall.application.usercenter.constant.ApiStatusConst;
import com.meiduimall.application.usercenter.constant.SysParamsConst;
import com.meiduimall.application.usercenter.util.HttpResolveUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.HttpUtils;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;

/**
 * 校验 API请求
 * @author chencong
 *
 */
@Configuration
public class ValRequest {
	
	private static Logger logger = LoggerFactory.getLogger(ValRequest.class);
	
	/**API请求数据*/
	public static ThreadLocal<JSONObject> apiReqData=new ThreadLocal<>();
	
    @Autowired  
    private ProfileParamsConfig profileParamsConfig;  
    private static ValRequest valRequest;  
  
    public void setUserInfo(ProfileParamsConfig profileParamsConfig) {  
        this.profileParamsConfig = profileParamsConfig;  
    }  
      
    @PostConstruct  
    public void init() {  
    	valRequest = this;  
    	valRequest.profileParamsConfig = this.profileParamsConfig;  
  
    } 
	/**
	 * 校验API请求
	 * @param request HttpServletRequest对象
	 * @param hasToken API接口方法是否有token注解
	 * @return 统一数据返回格式
	 * @throws MdSysException 
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
				String mdUser = ((String) reqJson.get("md_user")).trim();
				if(mdUser==null || mdUser.equals("")){
					resBodyData.setStatus(ApiStatusConst.MDUSER_EMPTY);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.MDUSER_EMPTY));
					return resBodyData;
				}
			}
			if(reqJson.containsKey("recharge_amout")){
				String rechargeAmout = ((String) reqJson.get("recharge_amout")).trim();
				if(rechargeAmout==null || rechargeAmout.equals("")){
					resBodyData.setStatus(ApiStatusConst.RECHARGE_AMOUT_EMPTY);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.RECHARGE_AMOUT_EMPTY));
					return resBodyData;
				}
				if(!isNumeric(rechargeAmout)){
					resBodyData.setStatus(ApiStatusConst.CALLBACK_URL_ISNUM);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.CALLBACK_URL_ISNUM));
					return resBodyData;
				}
				if(Double.valueOf(rechargeAmout)<0){
					resBodyData.setStatus(ApiStatusConst.CALLBACK_URL_NEGATIVE);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.CALLBACK_URL_NEGATIVE));
					return resBodyData;
				}
				if(Double.valueOf(rechargeAmout)==0){
					resBodyData.setStatus(ApiStatusConst.CALLBACK_URL_ZERO);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.CALLBACK_URL_ZERO));
					return resBodyData;
				}
				if(rechargeAmout.indexOf(".")!=-1){
					if (rechargeAmout.split("\\.")[1].length()>2){
						resBodyData.setStatus(ApiStatusConst.RECHARGE_AMOUT_DECIMAL);
						resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.RECHARGE_AMOUT_DECIMAL));
						return resBodyData;
					}
				}
				if (rechargeAmout.split("\\.")[0].length()>9){
					resBodyData.setStatus(ApiStatusConst.RECHARGE_AMOUT_MAX);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.RECHARGE_AMOUT_MAX));
					return resBodyData;
				}
			}
			if(reqJson.containsKey("recharge_type")){
				String rechargeType = ((String) reqJson.get("recharge_type")).trim();
				if(rechargeType==null || rechargeType.equals("")){
					resBodyData.setStatus(ApiStatusConst.RECHARGE_TYPE_EMPTY);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.RECHARGE_TYPE_EMPTY));
					return resBodyData;
				}
			}
			if(reqJson.containsKey("callback_url")){
				String callbackUrl = ((String) reqJson.get("callback_url")).trim();
				if(callbackUrl==null || callbackUrl.equals("")){
					resBodyData.setStatus(ApiStatusConst.CALLBACK_URL_EMPTY);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.CALLBACK_URL_EMPTY));
					return resBodyData;
				}
			}
			
			
			String bizId = ((String) reqJson.get("biz_id")).trim();
			if(bizId==null || bizId.equals("")){
				resBodyData.setStatus(ApiStatusConst.BIZID_EMPTY);
				resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.BIZID_EMPTY));
				return resBodyData;
			}else{
				int size = getRepeatOrderId(reqJson);
				if(size>0){
					resBodyData.setStatus(ApiStatusConst.BIZID_REPEAT);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.BIZID_REPEAT));
					return resBodyData;
				}
			}

			resBodyData=validateTimesatamp(reqJson.getLong(SysParamsConst.REQ_TIM));
			if(resBodyData.getStatus()!=0){
				return resBodyData;
			}
			resBodyData=validateSign(reqJson);
			if(resBodyData.getStatus()!=0){
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
	public static boolean isNumeric(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
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
	 * @throws MdSysException 
	 */
	@SuppressWarnings({ "rawtypes" })
	private static ResBodyData validateSign(JSONObject reqJson){
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		if(reqJson.getString(SysParamsConst.SIGN).length()!=32){
			resBodyData.setStatus(ApiStatusConst.SIGN_FORMAT_ERROR);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.SIGN_FORMAT_ERROR));
			return resBodyData;
		}
		String clientSign=reqJson.getString(SysParamsConst.SIGN);
		List array = getKey(reqJson);
		if(array.size()==0){
			resBodyData.setStatus(ApiStatusConst.CORPORATE_IDENTITY_EMPTY);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.CORPORATE_IDENTITY_EMPTY));
			return resBodyData;
		}
		String secretkey=(String) ((Map)array.get(0)).get("enterpriseKey");
		List arr = getAccountType((String) ((Map)array.get(0)).get("entId"),(String) reqJson.get("recharge_type"));
		if(arr.size()==0){
			resBodyData.setStatus(ApiStatusConst.ACCOUNT_TYPE_NON_EXISTENT);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.ACCOUNT_TYPE_NON_EXISTENT));
			return resBodyData;
		}
		//RedisTemplate.getJedisInstance().execGetFromCache(SysParamsConst.memberSecretJson);
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
	@SuppressWarnings("rawtypes")
	public static List getKey(JSONObject reqJson) {
		List array = new ArrayList<>();
		JSONObject js = new JSONObject();
		if(reqJson.containsKey(SysParamsConst.CLIENT_ID)){
			js.put("enterpriseIdentity", reqJson.get(SysParamsConst.CLIENT_ID));
			js.put("flg", "0");
		}
		ResBodyData resBodyData = new ResBodyData(null,null);
		String url = valRequest.profileParamsConfig.getServiceAccountUrl()+"v1/findBusinessManagementList";
		resBodyData = MD5Utils.updateSign(js,valRequest.profileParamsConfig.getRouteClientID(),valRequest.profileParamsConfig.getRouteKey());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(SysParamsConst.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result = HttpUtils.post(url,js.toString(),headers);
			resBodyData = JsonUtils.jsonToBean(result,ResBodyData.class);
			array=(List) ((Map)resBodyData.getData()).get("list");
		} catch (Exception e) {
			throw new ApiException(ApiStatusConst.REQUEST_GATEWAY_EX);
		}
		logger.info("---resBodyData");
		return array;
	}
	@SuppressWarnings("rawtypes")
	public static List getAccountType(String entId,String accountType) {
		JSONObject js = new JSONObject();
		List array = new ArrayList<>();
		js.put("personalAccountType", accountType);
		js.put("entId", entId);
		js.put("flg", "0");
		ResBodyData resBodyData = new ResBodyData(null,null);
		String url = valRequest.profileParamsConfig.getServiceAccountUrl()+"v1/findTripartiteEnterpriseDetailList";
		resBodyData = MD5Utils.updateSign(js,valRequest.profileParamsConfig.getRouteClientID(),valRequest.profileParamsConfig.getRouteKey());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(SysParamsConst.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result = HttpUtils.post(url,js.toString(),headers);
			resBodyData = JsonUtils.jsonToBean(result,ResBodyData.class);
			array=(List) ((Map)resBodyData.getData()).get("list");
		} catch (Exception e) {
			throw new ApiException(ApiStatusConst.REQUEST_GATEWAY_EX);
		}
	
		logger.info("---resBodyData");
		return array;
	}
	@SuppressWarnings("rawtypes")
	public static int getRepeatOrderId(JSONObject reqJson) {
		JSONObject js = new JSONObject();
		if(reqJson.containsKey("biz_id")){
			js.put("extorderId", reqJson.get("biz_id"));
			js.put("flg", "0");
		}
		int size=0;
		ResBodyData resBodyData = new ResBodyData(null,null);
		String url = valRequest.profileParamsConfig.getServiceAccountUrl()+"v1/findExternalRechargeList";
		resBodyData = MD5Utils.updateSign(js,valRequest.profileParamsConfig.getRouteClientID(),valRequest.profileParamsConfig.getRouteKey());
		try {
			Map<String, String> headers=new HashMap<>();
			headers.put(SysParamsConst.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
			String result = HttpUtils.post(url,js.toString(),headers);
			resBodyData = JsonUtils.jsonToBean(result,ResBodyData.class);
			
			List array=(List) ((Map)resBodyData.getData()).get("list");
			size = array.size();
		    logger.info("__"+array.size());
		} catch (Exception e) {
			throw new ApiException(ApiStatusConst.REQUEST_GATEWAY_EX);
		}
	
		logger.info("---resBodyData");
		return size;
	}
}