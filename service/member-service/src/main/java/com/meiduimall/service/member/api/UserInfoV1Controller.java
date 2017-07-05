package com.meiduimall.service.member.api;

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
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.model.MSMemberMobileArea;
import com.meiduimall.service.member.model.request.RequestGetMemberBasicInfo;
import com.meiduimall.service.member.model.request.RequestUpdateMemberBasicInfo;
import com.meiduimall.service.member.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 会员信息相关API
 * @author chencong
 *
 */
@Api(value = "会员信息相关", description = "会员信息相关接口")  
@RestController
@RequestMapping("/member/member_service/v1")
public class UserInfoV1Controller{
	
	private final static Logger logger=LoggerFactory.getLogger(UserInfoV1Controller.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**根据memId获取会员基本信息
	 * @throws MdSysException */
	@ApiOperation(value="根据memId获取会员基本信息", notes="根据memId获取会员基本信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "requestGetMemberBasicInfo", value = "根据memId获取会员基本信息实体", required = true, dataType = "RequestGetMemberBasicInfo"),
	})
	@GetMapping(value = "/get_member_basic_info")
	ResBodyData getmemberbasicinfo(@Valid RequestGetMemberBasicInfo requestGetMemberBasicInfo) throws MdSysException{
		String memId=requestGetMemberBasicInfo.getMemId();
		logger.info("收到会员：{}获取基本信息API请求",memId);
		ResBodyData resBodyData = userInfoService.getBasicInfoByMemId(memId);
		logger.info("获取会员：{}基本信息API请求结果  ：{}",memId,resBodyData.toString());
		return resBodyData;
	}
	
	/**
	 * 根据会员memId获取会员简单的信息
	 * @param memId 会员ID
	 * @return 数据对象
	 */
	@ApiOperation(value="根据会员memId获取会员简单的信息", notes="根据会员memId获取会员简单的信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "memId", value = "根据会员memId获取会员简单的信息实体", required = true, dataType = "String"),
	})
	@RequestMapping(value = "/get_member_simple_info")
	public ResBodyData getMemberSimpleInfo(String memId){
		return userInfoService.getSimpleInfoByMemId(memId);
	}
	
	
	/**
	 * 保存当前会员基本信息 http://IP:PORT/Authorized/SaveMemberInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/savememberbasicinfo",method=RequestMethod.POST)
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
			json = userInfoService.saveMemberBasicInfo(token, mem_sex, mem_birthday, mem_address_area, mem_address, mem_pic, nick_name);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParamsConst.STATUS, "9999");
				json.put(SysParamsConst.MSG, "服务器错误!");
			} catch (IOException e1) {
				logger.error("服务器错误:{}", e1.getMessage());
			}
		}
		out.print(json.toString());
	}*/
	
	/**校验手机号或登录名是否存在*/
	
	
	
	/**注册时记录会员手机对应的区域*/
	@ApiOperation(value="注册时记录会员手机对应的区域", notes="注册时记录会员手机对应的区域")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "mSMemberMobileArea", value = "注册时记录会员手机对应的区域实体", required = true, dataType = "MSMemberMobileArea"),
	})
	@PostMapping(value = "/record_area")
	ResBodyData recordArea(@RequestBody MSMemberMobileArea mSMemberMobileArea) throws MdSysException{
		logger.info("接收到数据：memId={}, phone={}", mSMemberMobileArea.getMemId(), mSMemberMobileArea.getPhone());
		ResBodyData recordArea = userInfoService.recordArea(mSMemberMobileArea.getMemId(), mSMemberMobileArea.getPhone());
		logger.info("注册时记录会员手机对应的区域请求结果：{}",recordArea.toString());
		return recordArea;
    }
	
	
	/**更新注册时记录会员手机对应的区域*/
	@ApiOperation(value="更新注册时记录会员手机对应的区域", notes="更新注册时记录会员手机对应的区域")
	@GetMapping(value = "/update_member_area")
	ResBodyData updateMemberArea() throws MdSysException{
		ResBodyData recordArea = userInfoService.updateMemberArea();
		return recordArea;
    }
	
	/**
	 * 更新会员基本信息 
	 * @param model 会员信息封装参数
	 * @return 数据对象 
	 */
	@ApiOperation(value="更新会员基本信息", notes="更新会员基本信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "model", value = "更新会员基本信息实体", required = true, dataType = "RequestUpdateMemberBasicInfo"),
	})
	@PostMapping(value = "/update_member_basic_info")
	public ResBodyData updateMemberBasicInfo(@Valid RequestUpdateMemberBasicInfo model){
		return userInfoService.updateMemberBasicInfo(model);
	}
}
