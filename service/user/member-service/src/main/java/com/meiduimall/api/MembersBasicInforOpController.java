package com.meiduimall.api;


import java.io.IOException;
import java.io.PrintWriter;
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
import com.meiduimall.constant.OauthConst;
import com.meiduimall.constant.SysParaNameConst;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.service.MembersBasicInforOpService;
import com.meiduimall.util.HttpClientUtil;
import com.meiduimall.util.Logger;

/**
 * 获取用户基本信息
 * @author chencong
 *
 */
@RestController
@RequestMapping("/membersbasicinforop")
public class MembersBasicInforOpController extends BaseController{
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private MembersBasicInforOpService mbis;
	
	/**
	 * 获取当前会员基本信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getmemberbasicinfo",method=RequestMethod.GET)
	String getmemberbasicinfo() throws Exception {	
		response.setCharacterEncoding("UTF-8");
		Logger.info("请求字符串：%s",request.getQueryString());
		try {
			JSONObject jsonObject=HttpClientUtil.readGetStringToJsonObject(request);
			//获取token对应的memid
			String mem_id=JedisUtil.getJedisInstance().execGetFromCache(jsonObject.getString(OauthConst.TOKEN));
			jsonObject.put("mem_id",mem_id);
			Map<String, Object> map=mbis.getmemberbasicinfo(jsonObject);
			Logger.info("用户"+mem_id+"请求结果:"+JSON.toJSON(map));
			return JSON.toJSONString(map);
		} catch (Exception e) {
			Logger.error("服务器错误:%s",e.getMessage());
		}
		return null;
	    }
	
	
	/**
	 * 保存当前会员基本信息 http://IP:PORT/Authorized/SaveMemberInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/savememberbasicinfo",method=RequestMethod.POST)
	public void register() throws Exception {
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONObject param = HttpClientUtil.readStreamToJsonObject(request);
			//从流中取参数
			String token = param.getString("token");
			String mem_sex = param.getString("mem_sex");
			String mem_birthday = param.getString("mem_birthday");
			String mem_address_area = param.getString("mem_address_area");
			String mem_address = param.getString("mem_address");
			String mem_pic = param.getString("mem_pic");
			String nick_name = param.getString("nick_name");
			json = mbis.saveMemberBasicInfo(token, mem_sex, mem_birthday, mem_address_area, mem_address, mem_pic, nick_name);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParaNameConst.STATUS_CODE, "9999");
				json.put(SysParaNameConst.RESULT_MSG, "服务器错误!");
				Logger.error("服务器错误:%s", e.getMessage());
			} catch (IOException e1) {
				Logger.error("服务器错误:%s", e1.getMessage());
			}
		}
		out.print(json.toString());
	}
	
	
	/**
	 * 修改手机号码(会获取验证码)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatememberphone/{token}",method=RequestMethod.PUT)
	public void updatememberphone(@PathVariable String token) throws Exception {
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONObject j=HttpClientUtil.readStreamToJsonObject(request);
			String newPhone=j.getString("newPhone");
			String verify_code=j.getString("verify_code");
			String password=j.getString("password");
			json = mbis.updateMemberPhone(token, newPhone, verify_code, password);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParaNameConst.STATUS_CODE, "9999");
				json.put(SysParaNameConst.RESULT_MSG, "服务器错误!");
				Logger.error("服务器错误:%s", e.getMessage());
			} catch (IOException e1) {
				Logger.error("服务器错误:%s", e1.getMessage());
			}
		}
		out.print(json.toString());
	}
	
	
	/**
	 * 根据手机号获取对方userid
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getuseridbyphone",method=RequestMethod.GET)
	public void getuseridbyphone(String phone) throws Exception {
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = mbis.getMemberInfoByPhone(phone);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParaNameConst.STATUS_CODE, "9999");
				json.put(SysParaNameConst.RESULT_MSG, "服务器错误!");
				Logger.error("服务器错误:%s", e.getMessage());
			} catch (IOException e1) {
				Logger.error("服务器错误:%s", e1.getMessage());
			}
		}
		out.print(json.toString());
	}
	
	
}
