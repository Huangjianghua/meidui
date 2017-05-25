package com.meiduimall.service.member.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.redis.util.RedisTemplate;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.model.request.RequestExit;
import com.meiduimall.service.member.model.request.RequestLogin;
import com.meiduimall.service.member.model.request.RequestRegister;
import com.meiduimall.service.member.model.request.RequestRegisterO2O;
import com.meiduimall.service.member.service.BasicOpService;
import com.meiduimall.service.member.service.UserInfoService;
import com.meiduimall.service.member.util.HttpResolveUtils;

/**
 * 用户基本操作相关接口
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class BasicOpV1Controller {

	private final static Logger logger=LoggerFactory.getLogger(BasicOpV1Controller.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BasicOpService basicOpService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**旧会员系统获取token或创建token(临时接口)
	 * 因为APP可能没升级，还会请求旧会员系统的登录和注册，所以这两个接口的put token要走这个接口
	 * 其他的接口get token也是走这个接口，两种操作用type区分，1是get，2是put
	 * */
	@GetMapping(value = "/getput")
	String getput() { 
		String result=null;
		try {
			//从本地变量获取已解析过的json
			JSONObject j=HttpResolveUtils.readGetStringToJsonObject(request);
			logger.info("旧会员系统请求接口的JSON：{}"+j.toString());
			//处理请求
			Map<String, Object> map=basicOpService.getput(j);			
			result=JSON.toJSONString(map);
			logger.info("旧会员系统getput请求结果：{}"+result);
		} catch (Exception e) {
			
		}
		return result;
	    }
	
	/**
	 * 旧会员系统登出接口调用新会员系统的接口
	 * */
	@GetMapping(value = "/handlesignout")
	String handlesignout() { 
		String result=null;
		try {
			//从本地变量获取已解析过的json
			JSONObject j=HttpResolveUtils.readGetStringToJsonObject(request);
			logger.info("旧会员系统请求接口的JSON：{}"+j.toString());
			//处理请求
			Map<String, Object> map=basicOpService.handlesignout(j);
			result=JSON.toJSONString(map);
			logger.info("旧会员系统登出处理请求结果：{}"+result);
		} catch (Exception e) {
			
		}
		return result;
	    }
	
	/**
	 * 会员登录
	 * @param requestLogin 登录API请求映射实体
	 * @return 统一数据返回格式
	 * @throws MdSysException 系统异常
	 */
	@PostMapping(value = "/login")
	ResBodyData login(@RequestBody @Valid RequestLogin requestLogin) throws MdSysException{
		requestLogin.setIp(request.getRemoteAddr());
		String tokenKey=request.getHeader(SysParamsConst.TERMINAL_ID);
		if(StringUtils.isEmpty(tokenKey)){
			 tokenKey=request.getHeader(SysParamsConst.USER_AGENT);
		}
		requestLogin.setTokenKey(tokenKey);
		logger.info("收到会员登录API请求：",requestLogin.toString());
		ResBodyData resBodyData=null;
		try {
			resBodyData = basicOpService.login(requestLogin);
		} catch (MdSysException e) {
			logger.error("会员登录API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.LOGIN_EXCEPTION);
		}
		logger.info("会员登录API请求结果  ：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**
	 *　会员退出登录
	 * @param token 登录令牌
	 * @return 统一数据返回格式
	 * @throws MdSysException
	 */
	@PostMapping(value = "/exit")
	ResBodyData exit(@RequestBody @Valid RequestExit model ) throws MdSysException{
		logger.info("收到会员退出登录API请求：{}",model.getToken());
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		try {
			if(RedisTemplate.getJedisInstance().execExistsFromCache(model.getToken())){
				RedisTemplate.getJedisInstance().execDelToCache(model.getToken());
				logger.info("删除token成功");
			}
			else{
				logger.warn("token在redis中不存在");
				throw new ApiException(ApiStatusConst.EXIT_ERROR);
			}
		} catch (Exception e) {
			logger.error("校验或删除token异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.EXIT_ERROR);
		}
		return resBodyData;
	}
	
	
	/**普通会员注册*/
	@PostMapping(value = "/register")
	ResBodyData register(@RequestBody @Valid RequestRegister model){
		String tokenKey=request.getHeader(SysParamsConst.TERMINAL_ID);
		if(StringUtils.isEmpty(tokenKey)){
			 tokenKey=request.getHeader(SysParamsConst.USER_AGENT);
		}
		model.setTokenKey(tokenKey);
		logger.info("收到普通会员注册API请求：{}",model.toString());
		ResBodyData resBodyData=null;
		try {
			resBodyData=basicOpService.register(model);
		} catch (DaoException  | MdSysException e) {
			logger.error("普通会员注册API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.REGISTER_EXCEPTION);
		}
		return resBodyData; 
	}
	
	/**扫码注册（临时接口，不推荐使用）*/
	@PostMapping(value = "/register_scan_code")
	ResBodyData registerScanCode(@RequestBody @Valid RequestRegister model){
		String tokenKey=request.getHeader(SysParamsConst.TERMINAL_ID);
		if(StringUtils.isEmpty(tokenKey)){
			 tokenKey=request.getHeader(SysParamsConst.USER_AGENT);
		}
		model.setTokenKey(tokenKey);
		logger.info("收到扫码注册API请求：{}",model.toString());
		ResBodyData resBodyData=null;
		try {
			resBodyData=basicOpService.registerScanCode(model);
		} catch (DaoException  | MdSysException e) {
			logger.error("扫码注册API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.REGISTER_EXCEPTION);
		}
		return resBodyData; 
	}
	
	/**O2O系统（商家，代理，个代）注册*/
	@PostMapping(value = "/register_o2o")
	ResBodyData registerO2O(@RequestBody @Valid RequestRegisterO2O model){
		String tokenKey=request.getHeader(SysParamsConst.TERMINAL_ID);
		if(StringUtils.isEmpty(tokenKey)){
			 tokenKey=request.getHeader(SysParamsConst.USER_AGENT);
		}
		model.setTokenKey(tokenKey);
		logger.info("收到O2O系统注册API请求：{}",model.toString());
		ResBodyData resBodyData=null;
		try {
			resBodyData=basicOpService.registerO2O(model);
		} catch (DaoException | MdSysException e) {
			logger.error("O2O系统注册API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.REGISTER_EXCEPTION);
		}
		return resBodyData; 
	}
	
	/**我是谁（token转memId）*/
	@GetMapping(value = "/get_memid_by_token")
	ResBodyData getMemIdByToken(@RequestParam String token){
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,"");
		logger.info("收到我是谁API请求：{}",token);
		if(RedisTemplate.getJedisInstance().execExistsFromCache(token)){
			String memId=RedisTemplate.getJedisInstance().execGetFromCache(token);
			Map<String, Object> data=new HashMap<>();
			data.put(SysParamsConst.MEM_ID,memId);
			resBodyData.setData(data);
			return resBodyData;
		}
		else{
			throw new ApiException(ApiStatusConst.TOKEN_NOT_EXISTS);
		}
	}
	
	
	/**token校验*/
	@PostMapping(value = "/checktoken")
	ResBodyData checktoken(@RequestBody @Valid RequestExit model) {	
		logger.info("收到token校验API请求：{}",model.getToken());
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		try {
			if(RedisTemplate.getJedisInstance().execExistsFromCache(model.getToken())){
				logger.info("校验token成功");
				String memId=RedisTemplate.getJedisInstance().execGetFromCache(model.getToken());
				String userId=userInfoService.getUserIdByMemId(memId);
				Map<String,Object> mapData=new HashMap<>();
				mapData.put("user_id",userId);
				resBodyData.setData(mapData);
			}
			else{
				logger.warn("token在redis中不存在");
				throw new ApiException(ApiStatusConst.CHECK_TOKEN_NOT_PASS);
			}
		} catch (Exception e) {
			logger.error("校验token异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.CHECK_TOKEN_NOT_PASS);
		}
		return resBodyData;
}
	
}
