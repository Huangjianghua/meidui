package com.meiduimall.service.account.api;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.MSConsumePointsDetail;
import com.meiduimall.service.account.model.MSConsumePointsDetailGet;
import com.meiduimall.service.account.model.MemberTransferHistory;
import com.meiduimall.service.account.model.request.RequestPointTransfer;
import com.meiduimall.service.account.service.ConsumePointsDetailService;
import com.meiduimall.service.account.service.MembersPointsOpService;
import com.meiduimall.service.account.service.PointsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(value="会员积分相关API接口")
@RestController
@RequestMapping("/member/account_service/v1")
public class PointsV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(PointsV1Controller.class);

	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private MembersPointsOpService membersScoreOpService;
	
	@Autowired
	private ConsumePointsDetailService mSConsumePointsDetailService;
	
	@Autowired
	private PointsService pointsService;
	

	@ApiOperation(value="积分 流水接口  分页 ")
	@ApiImplicitParam(name = "mSConsumePointsDetail", value = "积分 流水接口  分页API请求实体", required = true, dataType = "MSConsumePointsDetailGet")
	@PostMapping(value="/list_consume_points_detail")
	public ResBodyData listConsumePointsDetail(@RequestBody MSConsumePointsDetailGet mSConsumePointsDetail) throws Exception{
		List<MSConsumePointsDetail> listMSConsumePointsDetail = null;
		try {
			if(mSConsumePointsDetail.getMemId() == null){
				return new ResBodyData(1,"memId为空");
			}else if("".equals(mSConsumePointsDetail.getMemId())){
				return new ResBodyData(1,"memId为空");
			}else{
				PageHelper.startPage(mSConsumePointsDetail.getPageNum(), mSConsumePointsDetail.getPageSize());
				listMSConsumePointsDetail = mSConsumePointsDetailService.listMSConsumePointsDetail(mSConsumePointsDetail);
			}
		} catch (Exception e) {
			logger.error("服务器错误:%s", e.getMessage());
			return new ResBodyData(1,"服务器错误");
		}
		return new ResBodyData(ConstApiStatus.SUCCESS,"成功",new PageInfo<>(listMSConsumePointsDetail));
	}
	
	
	@ApiOperation(value="积分汇总接口，汇总某种类型的积分总额 ")
	@ApiImplicitParams({
         @ApiImplicitParam(name = "memId", value = "会员ID", required = true, dataType = "String"),
         @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "String")
 })
	@RequestMapping(value = "/gathrescoresumbytype",method=RequestMethod.GET)
	public void gathrescoresumbytype(String memId, String type) throws Exception {
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = membersScoreOpService.getMemberScoreByMemId(memId, type);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put("status_code", "9999");
				json.put("result_msg", "服务器错误!");
				logger.error("服务器错误:%s", e.getMessage());
			} catch (IOException e1) {
				logger.error("服务器错误:%s", e1.getMessage());
			}
		}
		out.print(json.toString());
	}
	

	@ApiOperation(value="查询积分转账列表")
	@ApiImplicitParam(name = "transfer", value = "查询积分转账列表API请求实体", required = true, dataType = "RequestPointTransfer")
	@RequestMapping(value = "/queryPointsTransferList")
	public ResBodyData queryPointsTransferList(@RequestBody RequestPointTransfer transfer) throws Exception {
		List<MemberTransferHistory> list=null;
		try {
			//分页查询
			if(transfer.getFlag().equals(Constants.CONSTANT_STR_ONE)){
				//分页
				PageHelper.startPage(transfer.getPageNum(), transfer.getPageSize());
				PageHelper.orderBy("mth_created_date DESC");
			}else{
				//不分页
				PageHelper.startPage(transfer.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("mth_created_date DESC");
			}
			list = pointsService.queryPointsTransferList(transfer);
		} catch (MdBizException e) {
			logger.error("查询积分转账列表queryPointsTransferList服务器错误:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,new PageInfo<>(list));
	}
	
}
