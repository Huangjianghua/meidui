package com.meiduimall.service.member.api;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.request.RequestLoginUnlock;
import com.meiduimall.service.member.model.request.RequestSetPaypwdStatus;
import com.meiduimall.service.member.model.response.MemberLockDTO;
import com.meiduimall.service.member.service.SecurityService;

/**
 * 账号安全相关接口
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class SecurityV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(SecurityV1Controller.class);
	
	@Autowired
	private  SecurityService  securityService;
	
	/**设置支付密码开关*/
	@PostMapping(value = "/set_paypwd_status")
	ResBodyData setPaypwdStatus(@RequestBody @Valid RequestSetPaypwdStatus requestSetPaypwdStatus) {
		logger.info("收到设置支付密码开关API请求：",requestSetPaypwdStatus.toString());
		try {
			MSMembersGet msMembersGet=new MSMembersGet();
			BeanUtils.copyProperties(requestSetPaypwdStatus,msMembersGet);
			ResBodyData resBodyData = securityService.setPaypwdStatus(msMembersGet);
			logger.info("设置支付密码开关API请求结果  ：{}",resBodyData.toString());
			return resBodyData;
		}
		catch (MdBizException e) {
			logger.error("设置支付密码开关API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.SET_PAYPWD_STATUS_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.SET_PAYPWD_STATUS_EXCEPTION));
		}
	}

	
	/**
	 * 手机找回登录密码 http://IP:PORT/Authorized/PhoneFindPwd
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getbackpwdbyphone/{phone}",method=RequestMethod.PUT)
	String getbackpwdbyphone(@PathVariable(name="phone")String phone) throws Exception {
	
		/*
		JSONObject  result  =  new  JSONObject();
		
		//String phone = jsonObject.getString("phone");//手机号码
		
		String one_pass_word = jsonObject.getString("one_pass_word");
		String two_pass_word = jsonObject.getString("two_pass_word");
		String  message="";
	     JedisUtil  jedisUtil=JedisUtil.getJedisInstance();
		try {
			if (!two_pass_word.equals(one_pass_word)) {
				result.put(SysParamsConst.STATUS, "1011");
				result.put(SysParamsConst.MSG, "两次密码输入不一致");
				message = result.toString();
				logger.info("外部当前请求手机找回登录密码IP地址=" + ip + "结束,两次密码输入不一致");
				return message;
			}
			if (one_pass_word.length() != 32 && two_pass_word.length() != 32) {
				result.put(SysParamsConst.STATUS, "1009");
				result.put(SysParamsConst.MSG, "密码输入错误");
				message = result.toString();
				logger.info("外部当前请求手机"+phone+"找回登录密码IP地址=" + ip + "结束,密码输入错误,非MD5的32位数加密");
				return message;
			}
			if (!StringUtil.isPhoneToRegex(phone)) {
				result.put(SysParamsConst.STATUS, "1013");
				result.put(SysParamsConst.MSG, "手机号码错误");
				logger.info("外部当前请求手机"+phone+"找回登录密码IP地址=" + ip + "结束,手机号码输入错误");
				message = result.toString();
				return message;
			}
			if((jedisUtil.execGetFromCache(phone + "app_code_zhaohui").isEmpty()))
			{
				result.put(SysParamsConst.STATUS, "2044");
				result.put(SysParamsConst.MSG, "操作超时,请重新按照步骤操作");
				logger.info("外部当前请求手机"+phone+"找回登录密码IP地址=" + ip + "结束,操作超时请重新按照步骤操作");
				message = result.toString();
				return message;
			}
            message = securityService.updateLoginPwdByPhone(jsonObject);
		} catch (Exception e) {
			
		}

		   return message;*/
		return null;
	    }
	
	/**
	 * 修改手机号码(会获取验证码)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatememberphone/{token}",method=RequestMethod.PUT)
    public void updatememberphone(@PathVariable String token) throws Exception {
		/*JSONObject json = new JSONObject();
		PrintWriter out = null;
		try {
			JSONObject j=HttpClientUtil.readStreamToJsonObject(request);
			String newPhone=j.getString("newPhone");
			String verify_code=j.getString("verify_code");
			String password=j.getString("password");
			json = securityService.updateMemberPhone(token, newPhone, verify_code, password);
		} catch (Exception e) {
			json.put(SysParamsConst.STATUS, "9999");
			json.put(SysParamsConst.MSG, "服务器错误!");
			logger.error("服务器错误:%s", e.getMessage());
		}
		out.print(json.toString());*/
	}
	
	/**
	 * 账号禁用 
	 * @param map
	 * @throws Exception
	 * @author: jianhua.huang  2017年5月2日 下午3:20:34
	 */
	@RequestMapping(value = "/disableAccount",method=RequestMethod.POST)
    public ResBodyData disabledAccount(@RequestBody Map<String, Object> param) throws Exception {
		try {
			checkParam(param);
			securityService.disabledAccount(param.get("memId").toString());
		} catch (MdBizException e) {
			logger.info("调用账号禁用API接口异常 :{},id:{}",e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		 return new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
	}
	
	/**
	 * 账号解禁 
	 * @param map
	 * @throws Exception
	 * @author: jianhua.huang  2017年5月2日 下午3:20:34
	 */
	@RequestMapping(value = "/undisableAccount",method=RequestMethod.POST)
    public ResBodyData unDisabledAccount(@RequestBody Map<String, Object> param) throws Exception {
		try {
			checkParam(param);
			securityService.unDisabledAccount(param.get("memId").toString());
		} catch (MdBizException e) {
			logger.info("调用账号解禁API接口异常 :{},id:{}",e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
	}
	
	
	/**
	 * 账号重置密码
	 * @param param
	 * @throws Exception
	 * @author: jianhua.huang  2017年5月2日 下午3:20:34
	 */
	@RequestMapping(value = "/resetAccountPwd",method=RequestMethod.POST)
    public ResBodyData resetAccountPwd(@RequestBody Map<String, Object> param) throws Exception {
		try {
			checkParam(param);
			securityService.resetAccountPwd(param);
		} catch (MdBizException e) {
			logger.info("调用账号重置密码API接口异常 :{},id:{}",e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
	}
	
	/**
	 * 登陆解锁列表
	 * @param loginUnlock
	 * @return
	 * @throws Exception
	 * @author: jianhua.huang  2017年5月3日 上午11:46:13
	 */
	@RequestMapping(value = "/loginUnlockList",method=RequestMethod.POST)
    public ResBodyData loginUnlockList(@RequestBody RequestLoginUnlock loginUnlock) throws Exception {
		List<MemberLockDTO> list=null;
		try {
			//分页查询
			if(loginUnlock.getFlag().equals("1")){
				//分页
				PageHelper.startPage(loginUnlock.getPageNum(), loginUnlock.getPageSize());
				PageHelper.orderBy("mem_login_time DESC");
			}else{
				//不分页
				PageHelper.startPage(loginUnlock.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("mem_login_time DESC");
			}
			list=securityService.loginUnlockList(loginUnlock);
		} catch (MdBizException e) {
			logger.info("调用登陆解锁列表API接口异常 :{},id:{}",e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS),new PageInfo<>(list));
	}
	
	/**
	 * 解锁用户
	 * @param param
	 * @return
	 * @throws Exception
	 * @author: jianhua.huang  2017年5月3日 上午11:46:52
	 */
	@RequestMapping(value = "/unlock",method=RequestMethod.POST)
    public ResBodyData unlock(@RequestBody Map<String, Object> param) throws Exception {
		try {
			checkParam(param);
			securityService.unlockMember(param);
		} catch (MdBizException e) {
			logger.info("调用解锁用户API接口异常 :{},id:{}",e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
	}
	/**
	 * 参数校验
	 * @param param
	 * @throws ApiException
	 * @author: jianhua.huang  2017年5月8日 上午11:41:05
	 */
	private void checkParam(Map<String, Object> param) throws ApiException{
		if(param==null){
			throw new ApiException(ApiStatusConst.REQUIRED_PARAM_EMPTY);
		}
		if(param.containsKey("memId")){
			if(StringUtils.isBlank(param.get("memId").toString())){
				throw new ApiException(ApiStatusConst.ACCOUNT_MEMBER_ID_NULL);
			}
		}
	}
}
