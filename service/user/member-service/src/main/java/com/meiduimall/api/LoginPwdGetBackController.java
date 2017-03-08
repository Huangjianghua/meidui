package com.meiduimall.api;


import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.api.BaseController;
import com.meiduimall.constant.SysConstant;
import com.meiduimall.dao.BaseDao;
import com.meiduimall.model.MemberGet;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.service.AuthorizationService;
import com.meiduimall.util.DESC;
import com.meiduimall.util.HttpClientUtil;
import com.meiduimall.util.Logger;
import com.meiduimall.util.StringUtil;
import com.meiduimall.util.checkparameter.AuthorizationCheckParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录密码找回
 * @author chencong
 *
 */
@RestController
@RequestMapping("/loginpwdgetback")
public class LoginPwdGetBackController extends BaseController{
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private  BaseDao  baseDao;
	
	@Autowired
	private  AuthorizationService  authorizationService;
	
	/**
	 * 会员修改登录密码 http://IP:PORT/Authorized/UpdateLoginPwd
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateloginpwd/{token}",method=RequestMethod.PUT)
	String updateloginpwd(@PathVariable(name="token")String token) throws Exception {	
		JSONObject result = new JSONObject();
		String  message="";
		try {
			JSONObject  jsonObject  =HttpClientUtil.readStreamToJsonObject(request);
			token = jsonObject.getString("token");    //用户标识
			String memid=JedisUtil.getJedisInstance().execGetFromCache(token);
			String ip =HttpClientUtil.getIpAddr(request);
			Logger.info("外部当前请求 修改登录密码 IP地址=" + ip + "开始");
			//String user_id = jsonObject.getString("user_id");
			String one_pass_word = jsonObject.getString("one_pass_word");//新密码
			String old_pass_word = jsonObject.getString("old_pass_word"); //旧密码
			MemberGet memberGet= baseDao.selectOne(memid,"MemberMapper.getPhoneAndAccountScoreByMemId");	
			
			if (old_pass_word.equals(one_pass_word)) {
				result.put(SysConstant.STATUS_CODE, "2063");
				result.put(SysConstant.RESULT_MSG, "原始密码与修改密码不能一样");
				message = result.toString();
				Logger.info("外部当前请求"+memid+"修改登录密码 IP地址=" + ip + "结束,密码输入错误,非MD5的32位数加密");
				return message;
			}
			
			jsonObject.put("ip",HttpClientUtil.getIpAddr(request));
			JSONObject result1 = new AuthorizationCheckParameter().vilidetePar(jsonObject);

			if (!Boolean.valueOf(result1.getString("boolean_result"))) {
				result1.remove("boolean_result");
				//return result1.toString();
			}
			if(memberGet==null){
				result.put(SysConstant.STATUS_CODE, "9997");
				result.put(SysConstant.RESULT_MSG, "非法操作");
				message = result.toString();
				Logger.info("外部当前请求"+memid+"修改登录密码 IP地址=" + ip + "结束,找不到当前登录的会员编号");
				return message;
			}
			jsonObject.put("memberId", memberGet.getMemId());
			
			message = authorizationService.updateLoginPwd(jsonObject);
			request.getQueryString();
		} catch (Exception e) {
			Logger.info("外部当前请求修改登录密码 IP地址=" + HttpClientUtil.getIpAddr(request) + "结束,出现异常");
			Logger.error("外部当前请修改登录密码 IP地址=" + HttpClientUtil.getIpAddr(request) + "异常:" + e);
			e.printStackTrace();
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误。缺少认证参数或服务器错误统一返回此参数");
			message = result.toString();
		}
		return  message;
	    }
	
	
	/**
	 * 手机找回登录密码 http://IP:PORT/Authorized/PhoneFindPwd
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getbackpwdbyphone/{phone}",method=RequestMethod.PUT)
	String getbackpwdbyphone(@PathVariable(name="phone")String phone) throws Exception {
		
		String  ip = HttpClientUtil.getIpAddr(request);//获取ip地址
		
		JSONObject  jsonObject = HttpClientUtil.readStreamToJsonObject(request);
		
		JSONObject  result  =  new  JSONObject();
		
		//String phone = jsonObject.getString("phone");//手机号码
		
		String one_pass_word = jsonObject.getString("one_pass_word");
		String two_pass_word = jsonObject.getString("two_pass_word");
		String  message="";
	     JedisUtil  jedisUtil=JedisUtil.getJedisInstance();
		try {
			if (!two_pass_word.equals(one_pass_word)) {
				result.put(SysConstant.STATUS_CODE, "1011");
				result.put(SysConstant.RESULT_MSG, "两次密码输入不一致");
				message = result.toString();
				Logger.info("外部当前请求手机找回登录密码IP地址=" + ip + "结束,两次密码输入不一致");
				return message;
			}
			if (one_pass_word.length() != 32 && two_pass_word.length() != 32) {
				result.put(SysConstant.STATUS_CODE, "1009");
				result.put(SysConstant.RESULT_MSG, "密码输入错误");
				message = result.toString();
				Logger.info("外部当前请求手机"+phone+"找回登录密码IP地址=" + ip + "结束,密码输入错误,非MD5的32位数加密");
				return message;
			}
			if (!StringUtil.isPhoneToRegex(phone)) {
				result.put(SysConstant.STATUS_CODE, "1013");
				result.put(SysConstant.RESULT_MSG, "手机号码错误");
				Logger.info("外部当前请求手机"+phone+"找回登录密码IP地址=" + ip + "结束,手机号码输入错误");
				message = result.toString();
				return message;
			}
			if((jedisUtil.execGetFromCache(phone + "app_code_zhaohui").isEmpty()))
			{
				result.put(SysConstant.STATUS_CODE, "2044");
				result.put(SysConstant.RESULT_MSG, "操作超时,请重新按照步骤操作");
				Logger.info("外部当前请求手机"+phone+"找回登录密码IP地址=" + ip + "结束,操作超时请重新按照步骤操作");
				message = result.toString();
				return message;
			}
            jsonObject.put("ip",HttpClientUtil.getIpAddr(request));
            message = authorizationService.updateLoginPwdByPhone(jsonObject);
		} catch (Exception e) {
			return getServerError(e);
		}

		   return message;
	    }
}
