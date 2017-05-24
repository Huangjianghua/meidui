package com.meiduimall.service.account.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.model.MSMembersPaypwd;
import com.meiduimall.service.account.model.request.RequestRetrievePaypwd;
import com.meiduimall.service.account.model.request.RequestUpdatePaypwd;
import com.meiduimall.service.account.service.PaypwdService;

@RestController
@RequestMapping("/member/account_service/v1")
public class PayPwdV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(PayPwdV1Controller.class);
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private PaypwdService paypwdService;
	
	
	/**验证支付密码*/
	@RequestMapping(value = "/valide_pay_pwd")
	public ResBodyData validePaypwd(@Valid MSMembersPaypwd msMembersPaypwd) throws Exception {
		logger.info("收到验证支付密码API请求  ：{}",msMembersPaypwd.toString());
		try {
			ResBodyData resBodyData = paypwdService.validePaypwd(msMembersPaypwd);
			logger.info("验证支付密码API请求结果  ：{}",resBodyData.toString());
			return resBodyData;
		} catch (Exception e) {
			logger.error("验证支付密码API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.VALIDATE_PAYPWD_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.VALIDATE_PAYPWD_EXCEPTION));
		}
	}
	
	/**设置支付密码*/
	@RequestMapping(value = "/set_pay_pwd")
	public ResBodyData setPaypwd(@Valid MSMembersPaypwd msMembersPaypwd) {
		logger.info("收到设置支付密码API请求  ：{}",msMembersPaypwd.toString());
		try {
			ResBodyData resBodyData = paypwdService.setPaypwd(msMembersPaypwd);
			logger.info("设置支付密码API请求结果  ：{}",resBodyData.toString());
			return resBodyData;
		} catch (Exception e) {
			logger.error("设置支付密码API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.SET_PAYPWD_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.SET_PAYPWD_EXCEPTION));
		}
	}
	
	
	/**根据memId查询是否存在支付密码*/
	@GetMapping(value = "/is_exist_paypwd")
	ResBodyData  isExistPaypwd(String memId) {
		logger.info("收到根据memId查询是否存在支付密码API请求:{}",request.getQueryString());
		try {
			ResBodyData resBodyData = paypwdService.isExistPaypwd(memId);
			logger.info("根据memId查询是否存在支付密码API请求结果  ：{}",resBodyData.toString());
			return resBodyData;
		} catch (MdBizException e) {
			logger.error("根据memId查询是否存在支付密码API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.SET_PAYPWD_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.SET_PAYPWD_EXCEPTION));
		}
	}
	
	/**修改支付密码*/
	@PostMapping(value = "/update_pay_pwd")
	public ResBodyData updatePaypwd(@RequestBody @Valid RequestUpdatePaypwd requestUpdatePaypwd) {
		logger.info("收到修改支付密码API请求  ：{}",requestUpdatePaypwd.toString());
		try {
			ResBodyData resBodyData = paypwdService.updatePaypwd(requestUpdatePaypwd);
			logger.info("修改支付密码API请求结果  ：{}",resBodyData.toString());
			return resBodyData;
		} 
		catch (Exception e) {
			logger.error("修改支付密码API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.UPDATE_PAYPWD_EXCEPTION);
		}
	}
	
	/**找回支付密码*/
	@PostMapping(value = "/retrieve_pay_pwd")
	public ResBodyData retrievePaypwd(@RequestBody @Valid RequestRetrievePaypwd requestRetrievePaypwd) {
		logger.info("收到找回支付密码API请求  ：{}",requestRetrievePaypwd.toString());
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		try {
			paypwdService.retrievePaypwd(requestRetrievePaypwd);
		}catch (MdSysException e) {
			logger.info("找回支付密码API请求异常  ：{}",e.toString());
			throw new ApiException(ApiStatusConst.RETRIEVE_PAYPWD_EXCEPTION);
		}
		return resBodyData;
	}
	
}
