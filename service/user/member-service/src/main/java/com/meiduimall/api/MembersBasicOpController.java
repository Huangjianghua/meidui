package com.meiduimall.api;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.api.BaseController;
import com.meiduimall.service.MembersBasicOpService;
import com.meiduimall.util.HttpClientUtil;
import com.meiduimall.util.Logger;
import com.meiduimall.util.checkparameter.AuthorizationCheckParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户基本操作
 * @author chencong
 *
 */
@RestController
@RequestMapping("/baseop")
public class MembersBasicOpController extends BaseController{
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private MembersBasicOpService mbo;
	
	/**旧会员系统获取token或创建token(临时接口)
	 * 因为APP可能没升级，还会请求旧会员系统的登录和注册，所以这两个接口的put token要走这个接口
	 * 其他的接口get token也是走这个接口，两种操作用type区分，1是get，2是put
	 * */
	@RequestMapping(value = "/getput",method=RequestMethod.GET)
	void getput() { 
		PrintWriter pw=null;
		try {
			response.setCharacterEncoding("utf-8");
			pw=response.getWriter();
			response.setCharacterEncoding("utf-8");
			//从本地变量获取已解析过的json
			JSONObject j=HttpClientUtil.readGetStringToJsonObject(request);
			Logger.info("旧会员系统请求接口的JSON："+j.toString());
			//处理请求
			Map<String, Object> map=mbo.getput(j);
			String result=JSON.toJSONString(map);
			pw.print(result);
		} catch (Exception e) {
			pw.println(getServerError(e));
		}
	    }
	
	/**
	 * 旧会员系统登出接口调用新会员系统的接口
	 * */
	@RequestMapping(value = "/handlesignout",method=RequestMethod.GET)
	void handlesignout() { 
		PrintWriter pw=null;
		try {
			response.setCharacterEncoding("utf-8");
			pw=response.getWriter();
			response.setCharacterEncoding("utf-8");
			//从本地变量获取已解析过的json
			JSONObject j=HttpClientUtil.readGetStringToJsonObject(request);
			Logger.info("旧会员系统请求接口的JSON："+j.toString());
			//处理请求
			Map<String, Object> map=mbo.handlesignout(j);
			String result=JSON.toJSONString(map);
			pw.print(result);
		} catch (Exception e) {
			pw.println(getServerError(e));
		}
	    }
	
	/**用户登录*/
	@RequestMapping(value = "/login",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
	String login(){ 
		try {
			response.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			//从本地变量获取已解析过的json
			JSONObject j=HttpClientUtil.readStreamToJsonObject(request);
			//得到IP地址
			j.put("ip",HttpClientUtil.getIpAddr(request));
			//处理登录请求
			Map<String, Object> map=mbo.login(j);
			String result=JSON.toJSONString(map);
			Logger.info("用户"+j.getString("user_id")+"请求结果"+result);
			return result;
		} catch (Exception e) {
			return getServerError(e);
		}
	    }
	
	
	/**用户注册*/
	@RequestMapping(value = "/register",method=RequestMethod.POST)
	void register() { 
		//得到IP地址
		String ip=HttpClientUtil.getIpAddr(request);
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
			response.setCharacterEncoding("utf-8");
			//从本地变量获取已解析过的json
			JSONObject j=HttpClientUtil.readGetStringToJsonObject(request);
			Logger.info("接收IP为："+ip+"的注册请求："+j.toString());
			/*校验参数*/
			JSONObject check=AuthorizationCheckParameter.vilidetePar(j);
			if((check.keySet().size()>0)&&check.getString(AuthorizationCheckParameter.RESULTKEYNAME).equals("false"))
			{
				check.remove(AuthorizationCheckParameter.RESULTKEYNAME);
				pw.print(check.toString());
				return;
			}
			//处理注册请求
			j.put("ip",ip);
			Map<String, Object> map=mbo.register(j);
			String result=JSON.toJSONString(map);
			pw.print(result);
		} catch (Exception e) {
			pw.println(getServerError(e));
		}
	    }
	
	
	/**token校验*/
	@RequestMapping(value = "/checktoken",method=RequestMethod.POST)
	void checktoken() {	
		PrintWriter pw=null;
		try {
			response.setCharacterEncoding("utf-8");
			pw=response.getWriter();
			//到拦截器那里从本地变量获取解析过的json

			JSONObject j=HttpClientUtil.readStreamToJsonObject(request);
			//处理打开APP时检查token的逻辑
			Map<String, Object> map=mbo.checktoken(j);
			String result=JSON.toJSONString(map);
			Logger.info("用户"+j.getString("user_id")+"请求结果"+result);
			pw.print(result);
		} catch (Exception e) {
			pw.println(getServerError(e));
		}
	
}
	
	/**生成短信验证码*/
	@RequestMapping(value = "/createvalidatecode",method=RequestMethod.POST)
	void createValidateCode() { 
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
			//从本地变量获取已解析过的json
			JSONObject j=HttpClientUtil.readGetStringToJsonObject(request);
			//得到IP地址
			String ip=HttpClientUtil.getIpAddr(request);
			j.put("ip",ip);
			Logger.info("接收IP为："+ip+"的获取短信验证码请求："+j.toString());
			/*校验参数*/
			JSONObject check=AuthorizationCheckParameter.vilidetePar(j);
			if((check.keySet().size()>0)&&check.getString(AuthorizationCheckParameter.RESULTKEYNAME).equals("false"))
			{
				check.remove(AuthorizationCheckParameter.RESULTKEYNAME);
				pw.print(check.toString());
				return;
			}
			//处理注册请求
			Map<String, Object> map=mbo.createValidateCode(j);
			String result=JSON.toJSONString(map);
			pw.print(result);
		} catch (Exception e) {
			pw.println(getServerError(e));
		}
	    }
	
	

}
