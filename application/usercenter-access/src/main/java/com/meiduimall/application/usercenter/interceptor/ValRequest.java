package com.meiduimall.application.usercenter.interceptor;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.usercenter.constant.ConstApiStatus;
import com.meiduimall.application.usercenter.constant.SysParamsConst;
import com.meiduimall.application.usercenter.util.HttpResolveUtils;
import com.meiduimall.application.usercenter.util.MD5Utils;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.redis.util.RedisTemplate;

/**
 * 校验API请求
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
	 * @throws MdSysException 系统异常
	 */
	public static void validate(HttpServletRequest request, boolean hasToken) throws MdSysException{
		String contentType=request.getContentType();
		String method=request.getMethod();
		logger.info("API请求数据类型：{},请求方式：{},是否标识token注解：{}",contentType,method,hasToken);
		JSONObject reqJson=null;
		try {
			if(SysParamsConst.HTTP_GET.equals(method)){
				reqJson=HttpResolveUtils.readGetStringToJsonObject(request);
			}				
			if(SysParamsConst.HTTP_POST.equals(method)&&contentType.contains(MediaType.APPLICATION_JSON_VALUE)){
				reqJson=HttpResolveUtils.readStreamToJsonObject(request);
			}				
			if(SysParamsConst.HTTP_POST.equals(method)&&MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType)){
				reqJson=HttpResolveUtils.readPostFormToJsonObject(request);
			}
		} catch (Exception e) {
			logger.error("解析request对象异常：{}",e.toString());
			throw new MdSysException(ConstApiStatus.SYSTEM_ERROR);
		}
		logger.info("拦截器解析请求后的json：{}",reqJson.toString());
		/**校验必填参数，时间戳，签名*/
		/*validateParamters(reqJson);
		validateTimesatamp(reqJson.getLong(SysParamsConst.TIMESATAMP));
		validateSign(reqJson);*/
		/**校验token，将token转换为memId*/
		if (hasToken) {
			if (reqJson.containsKey(SysParamsConst.TOKEN)){ 
				if(StringUtils.isEmpty(reqJson.getString(SysParamsConst.TOKEN))){
					logger.warn("token参数为空");
					throw new MdSysException(ConstApiStatus.TOKEN_EMPTY);
				}
				String memId=ValToken.valToken(reqJson.getString(SysParamsConst.TOKEN));
				reqJson.remove(SysParamsConst.TOKEN);
				reqJson.put(SysParamsConst.MEMID,memId);
			}
			else{
				logger.warn("请求缺少token参数");
				throw new MdSysException(ConstApiStatus.TOKEN_EMPTY);
			}				
		}
		apiReqData.set(reqJson);
	}
	
	/**
	 * 校验必填参数
	 * @param reqJson 请求的json格式数据
	 * @return 统一数据返回格式
	 * @throws MdSysException 系统异常
	 */
	private static void validateParamters(JSONObject reqJson) throws MdSysException{
		if(!reqJson.containsKey(SysParamsConst.CLIENTID)){
			logger.warn("校验必填参数>>clientID为空");
			throw new MdSysException(ConstApiStatus.CLIENTID_EMPTY);
		}
		if(!reqJson.containsKey(SysParamsConst.TIMESATAMP)){
			logger.warn("校验必填参数>>时间戳为空");
			throw new MdSysException(ConstApiStatus.TIMESTAMP_EMPTY);
		}
		if(!reqJson.containsKey(SysParamsConst.SIGN)){
			logger.warn("校验必填参数>>签名为空");
			throw new MdSysException(ConstApiStatus.SIGN_EMPTY);
		}
		logger.info("拦截器校验必填参数通过");
	}
	
	/**
	 * 校验时间戳
	 * @param timesatamp 13位时间戳
	 * @return 统一数据返回格式
	 * @throws MdSysException 系统异常
	 */
	private static void validateTimesatamp(Long timesatamp) throws MdSysException{
		Long currentTime = System.currentTimeMillis();
		Long beforeTime = currentTime - SysParamsConst.REQUEST_EFFECTIVE_TIME;
		if(timesatamp.toString().length()!=13){
			logger.warn("校验时间戳>>时间戳格式错误，非13位");
			throw new MdSysException(ConstApiStatus.TIMESTAMP_FORMAT_ERROR);
		}
		if (timesatamp<beforeTime){
			logger.warn("校验时间戳>>请求超时，超过五分钟");
			throw new MdSysException(ConstApiStatus.REQUEST_TIMEOUT);
		}
		logger.info("拦截器校验时间戳通过");
	}
	
	/**
	 * 校验签名
	 * @param reqJson 请求的json格式数据
	 * @return 统一数据返回格式
	 * @throws MdSysException 系统异常
	 */
	private static void validateSign(JSONObject reqJson) throws MdSysException{
		if(reqJson.getString(SysParamsConst.SIGN).length()!=32){
			logger.warn("校验签名>>签名格式错误，非32位");
			throw new MdSysException(ConstApiStatus.SIGN_FORMAT_ERROR);
		}
		String clientSign=reqJson.getString(SysParamsConst.SIGN);		
		String memberSecretJson=RedisTemplate.getJedisInstance().execGetFromCache(SysParamsConst.memberSecretJson);
		String clientID=reqJson.getString(SysParamsConst.CLIENTID);
		String secretkey=JSONObject.parseObject(memberSecretJson).getString(clientID);
		String serverSign = MD5Utils.getSign(reqJson,secretkey);
		if (!clientSign.equals(serverSign)){
			logger.info("校验签名>>签名校验不通过");
			throw new MdSysException(ConstApiStatus.SIGN_ERROR);
		}
		logger.info("签名校验通过");
	}
}