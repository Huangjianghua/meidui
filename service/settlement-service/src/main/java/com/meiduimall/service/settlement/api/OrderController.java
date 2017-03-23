package com.meiduimall.service.settlement.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.StringUtil;
import com.google.common.collect.ImmutableMap;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.EcmOrder;
import com.meiduimall.service.settlement.service.OrderService;
import com.meiduimall.service.settlement.task.AsyncTaskService;
import com.meiduimall.service.settlement.vo.ShareProfitVO;

@RestController
@RequestMapping("/settlementservice/orderservice/v1")
public class OrderController {
	
	private static final Logger log=LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AsyncTaskService asyncTaskService;

	/**
	 * 订单分润接口
	 * @param request
	 * @param response
	 * @param ecmOrder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/shareprofit",method=RequestMethod.POST)
	public ResBodyData shareProfit(@Validated EcmOrder ecmOrder)throws Exception{
		
		long start=System.currentTimeMillis();
		log.info("share profit for order start:{}",start);
		
		Integer statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS;
		boolean isSuccess=true;
		//shareStatus:0:未分润;1已分润
		Integer shareStatus=1;
		String resultMsg="订单分润成功!";
		final List<String> errors=new ArrayList<String>();
		
		EcmMzfShareProfit shareProfit=null;
		try {
			boolean isExisted=orderService.checkShareProfitExisted(ecmOrder.getOrderSn());
			if(isExisted){
				resultMsg="该订单已经分润过啦！不能再重复分润!";
				statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE;
				return SettlementUtil.buildReponseData(ImmutableMap.of("orderSn", ecmOrder.getOrderSn(),"shareStatus",shareStatus), statusCode, resultMsg);
			}
			
			shareProfit=orderService.buildShareProfit(ecmOrder,errors);
		} catch (Exception e) {
			resultMsg="订单分润失败!";
			shareStatus=0;
			log.error("shareprofit() for orderSn:{} in OrderController got error:{}",ecmOrder.getOrderSn(),e.getMessage());
			return SettlementUtil.buildReponseData(ImmutableMap.of("orderSn", ecmOrder.getOrderSn(),"shareStatus",shareStatus), statusCode, resultMsg);
		}
		
		if(errors!=null && errors.size()>0){
			log.info("orderSn:{},分润数据有错误:{}",ecmOrder.getOrderSn(),errors.toString());
			resultMsg=errors.toString();
			statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE;
			shareStatus=0;
			return SettlementUtil.buildReponseData(ImmutableMap.of("orderSn", ecmOrder.getOrderSn(),"shareStatus",shareStatus), statusCode, resultMsg);
		}
		
		//2.保存分润数据到DB。。。
		if(shareProfit!=null){
			try{
				orderService.saveShareProfit(shareProfit);
			}catch(Exception e){
				isSuccess=false;
				log.error("saveShareProfit() for orderSn:{} got error:{}",shareProfit.getOrderSn(),e.getMessage());
			}
		}else{
			isSuccess=false;
		}

		if(!isSuccess){
			statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE;
			resultMsg="订单分润失败!";
			shareStatus=0;
		}else{
			//异步同步积分到会员系统...
			//orderService.updateScore2MemberSystem(shareProfit,ShareProfitConstants.SHARE_PROFIT_SOURCE_O2O,null);
			asyncTaskService.updateScore2MemberSystem(shareProfit,ShareProfitConstants.SHARE_PROFIT_SOURCE_O2O,null);

		}
		
		long end=System.currentTimeMillis();
		log.info("share profit for order end:{}",end);
		log.info("total time(second) for shareprofit:{}", (end-start)/1000);
		return SettlementUtil.buildReponseData(ImmutableMap.of("orderSn", ecmOrder.getOrderSn(),"shareStatus",shareStatus), statusCode, resultMsg);
	}

    /**
     * 根据订单号列表查询订单状态接口
     * @param input
     * @return
     * @throws Exception
     * @author wujun 
     */
	@PostMapping(value="/queryorderstatus")
	public ResBodyData queryOrderStatus(String[] orderSns) throws Exception{
		
		List<EcmMzfOrderStatus> queryorderstatus=new ArrayList<EcmMzfOrderStatus>();
		
		if(orderSns!=null && orderSns.length>0){
			queryorderstatus = orderService.queryOrderStatus(Arrays.asList(orderSns));
		}
		
		return SettlementUtil.buildReponseData(queryorderstatus, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "查询订单状态成功!");
		
	}
	
	/**
	 * 同步订单审核状态接口
	 * bill_status状态改为1, status_desc改为待结算
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/syncverifystatus")
	public ResBodyData syncVerifyStatus(@Validated EcmMzfOrderStatus orderStatus) throws Exception{
		log.info("syncVerifyStatus() in the OrderController start--");
		
		int statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS;

		Boolean isSuccess=true;
		String msg="同步订单审核状态数据到结算系统成功!";
		try {
			isSuccess = orderService.syncVerifyStatus(orderStatus);
		} catch (Exception e) {
			isSuccess=false;
			log.error("同步订单审核状态接口 出错，orderService.syncVerifyStatus() for orderSn:{} got error:{}",orderStatus.getOrderSn(),e.getMessage());
		}
		
		if(!isSuccess){
			msg="同步订单审核状态数据到结算系统失败!";
			statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE;
			log.info(msg);
		}
			
		return SettlementUtil.buildReponseData("", statusCode, msg);
		
	}
	
	
	/**
	 * 根据订单号列表查询订单状态接口
	 * @param orderSns
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryshareprofit",method=RequestMethod.POST)
	@ResponseBody
	public ResBodyData queryShareProfit(String[] orderSns) throws Exception{
		
		List<EcmMzfShareProfit> shareProfits=new ArrayList<EcmMzfShareProfit>();
		
		if(orderSns!=null && orderSns.length>0){
			shareProfits = orderService.queryShareProfit(Arrays.asList(orderSns));
		}

		return SettlementUtil.buildReponseData(shareProfits, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "成功!");

	}
	
	
	/**
	 * 根据区代/个代查询今日订单佣金和待结算金额接口
	 * @param code
	 * @param accountRoleType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryprofitbyrole",method=RequestMethod.POST)
	@ResponseBody
	public ResBodyData queryProfitByRole(String code,Integer accountRoleType) throws Exception{
		
		ShareProfitVO shareProfitVO=orderService.queryProfitByRole(code,accountRoleType);
	
		return SettlementUtil.buildReponseData(shareProfitVO, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "成功!");

	}
	
	/**
	 * 根据流水编号查询分润数据接口 (已经合并到查询账单流水详情接口WaterController.querywaterbyid())
	 * @param waterId
	 * @param loginType
	 * @param code
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryprofitbywaterbytype",method=RequestMethod.POST)
	@ResponseBody
	public ResBodyData queryProfitByWaterByType(String waterId,Integer loginType,String code,Integer pageNumber,Integer pageSize) throws Exception{
		
		Integer count = orderService.queryProfitCountByWaterId(waterId);
		
		List<EcmMzfShareProfit> shareProfitList=orderService.queryProfitByWaterByType(waterId,loginType,code,pageNumber,pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shareProfitList", shareProfitList);
		map.put("total", count);
		
		return SettlementUtil.buildReponseData(map, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "查询账单流水详情数据成功!");

	}


	
	
	
}
