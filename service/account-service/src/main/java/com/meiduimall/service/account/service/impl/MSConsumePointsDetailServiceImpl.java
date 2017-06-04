package com.meiduimall.service.account.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.dto.InterfaceConsumePointsDetailsDTO;
import com.meiduimall.service.account.dto.ServiceToServiceDTO;
import com.meiduimall.service.account.model.MSConsumePointsDetail;
import com.meiduimall.service.account.model.MSConsumePointsDetailGet;
import com.meiduimall.service.account.service.AccountAdjustService;
import com.meiduimall.service.account.service.AccountDetailService;
import com.meiduimall.service.account.service.AccountService;
import com.meiduimall.service.account.service.MSConsumePointsDetailService;
import com.meiduimall.service.account.service.ConsumePointsFreezeInfoService;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DateUtil;
import com.meiduimall.service.account.util.DoubleCalculate;
import com.meiduimall.service.account.util.StringUtil;


@Component
public class MSConsumePointsDetailServiceImpl implements MSConsumePointsDetailService {

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private ConsumePointsFreezeInfoService   msConsumePointsFreezeService;
	
	@Autowired
	private AccountAdjustService   accountAdjustService;
	
	@Autowired
	private AccountDetailService   accountDetailService;
	
	@Autowired
	private AccountService   accountService;
	
	@Override
	public void saveAddConsumePoints(String memId, String orderId, String orderSource, String consumePoints,
			String operatorType, String operator, String remark) throws Exception {
		double realPoints = msConsumePointsFreezeService.getConsumePoints(memId);
		String balancePoints = String.valueOf(DoubleCalculate.add(realPoints,Double.valueOf(consumePoints)));

		insertConsumePointsDetail(memId, orderId, orderSource, consumePoints,
				"0", balancePoints, operatorType, operator, remark);

	}
	/**
	 * 方法名: insertConsumePointsDetail<br>
	 * 描述:  写入会员积分消费明细<br>
	 * 创建时间: 2016-11-18
	 * @param memId
	 * @param orderId
	 * @param orderSource
	 * @param inConsumePoints
	 * @param outConsumePoints
	 * @param balancePoints
	 * @param operatorType
	 * @param operator
	 * @param remark
	 * @throws Exception 
	 */
	private void insertConsumePointsDetail(String memId, String orderId,
			String orderSource, String inConsumePoints,
			String outConsumePoints, String balancePoints, String operatorType,
			String operator, String remark) throws Exception {
		
		MSConsumePointsDetail entity = new MSConsumePointsDetail();
		Date nowDate = new Date(System.currentTimeMillis());
		entity.setMemId(memId);
		entity.setMcpOrderId(orderId);
		entity.setMcpOperatorType(operatorType);
		entity.setMcpOrderSource(orderSource);
		entity.setMcpIncome(inConsumePoints);
		entity.setMcpExpenditure(outConsumePoints);
		entity.setMcpBalance(balancePoints);
		entity.setMcpCreatedBy(operator);
		entity.setMcpUpdatedBy(operator);
		entity.setMcpCreatedDate(nowDate);
		entity.setMcpUpdatedDate(nowDate);
		entity.setMcpRemark(remark);
		baseDao.insert(entity,"MSConsumePointsDetailMapper.saveConsumePointsDetails");
	}
	@Override
	public void saveConsumePoints(String memId, String orderId, String orderSource, String inConsumePoints,
			String outConsumePoints, String balancePoints, String operatorType, String operator, String remark) {
		// TODO Auto-generated method stub
		try {
			insertConsumePointsDetail(memId, orderId, orderSource,
					inConsumePoints, outConsumePoints, balancePoints, operatorType, operator, remark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void getConsumePointsDetail(ServiceToServiceDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		Map<String,String> reqMap = dto.getReqMap();
		param.put("memId",dto.getBsId());
		int currentPage =Integer.parseInt(reqMap.get("current_page"));
		int pageSize = Integer.parseInt(reqMap.get("page_size"));
		
		param.put("before_page", (currentPage-1)*pageSize);
		param.put("after_page",pageSize);
		if(reqMap.containsKey("dict_operator_type"))
		{
			param.put("mcpOperatorType", reqMap.get("dict_operator_type"));
			
		}
		if(reqMap.containsKey("many_dict_operator_type"))
		{
			/*param.put("mcpOperatorTypes", SqlConvertUtil.convertManyByStringArray(reqMap.get("order_type"),SysConstant.MANY_DATA_SPLIT_STR));*/
			
		}
		List<MSConsumePointsDetailGet> list = baseDao.selectList(param,"MSConsumePointsDetailMapper.queryListByMember");
		
		int totalRecords = baseDao.selectOne(param,"MSConsumePointsDetailMapper.getConsumePointsTotalRecords");//获取总的记录数
		
		int totalPage= totalRecords%pageSize==1?(totalRecords/pageSize)+1:totalRecords/pageSize;//获取总的页数
		if(totalPage==0)
		{
			totalPage=1;
		}
		List<InterfaceConsumePointsDetailsDTO> dtoList = new ArrayList<InterfaceConsumePointsDetailsDTO>();
		if(list == null || list.size() == 0){
			dto.setExecFlag(ServiceToServiceDTO.EXEC_SUCCESS_NO_DATA);
		}else{
			InterfaceConsumePointsDetailsDTO icpdDto = null;
			for (MSConsumePointsDetailGet tmpDto : list) {
				icpdDto = new InterfaceConsumePointsDetailsDTO();
				
				icpdDto.setOrderId(tmpDto.getMcpOrderId());
				icpdDto.setMcpId(tmpDto.getMcpId());
				icpdDto.setOrderType(tmpDto.getMcpOperatorType());
				icpdDto.setMemId(tmpDto.getMemId());
				icpdDto.setIncomePoints(tmpDto.getMcpIncome());
				icpdDto.setExpenditurePoints(tmpDto.getMcpExpenditure());
				icpdDto.setBalancePoints(tmpDto.getMcpBalance());
				icpdDto.setCreatedBy(tmpDto.getMcpCreatedBy());
				icpdDto.setCreatedDate(DateUtil.format(tmpDto.getMcpCreatedDate(),DateUtil.YYYY_MM_DD_HH_MM_SS));
				icpdDto.setUpdatedBy(tmpDto.getMcpUpdatedBy());
				icpdDto.setUpdatedDate(DateUtil.format(tmpDto.getMcpUpdatedDate(),DateUtil.YYYY_MM_DD_HH_MM_SS));
				
				//手机号码隐藏中间四位
				String remark = tmpDto.getMcpRemark();
				if(!StringUtil.isEmptyByString(remark)){
					try{
						icpdDto.setRemark(remark.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
					}catch (Exception e) {
						icpdDto.setRemark(remark);
					}
				}
				
				dtoList.add(icpdDto);
			}
			dto.setExecFlag(ServiceToServiceDTO.EXEC_SUCCESS_RESULT_DATA);
			dto.setResultList((List)dtoList);
			dto.setResultStr(JSONArray.toJSON(dtoList).toString());
			dto.setResultInt(totalPage);
		}
		
		
	}
	
	
	@Override
	public List<MSConsumePointsDetail> listMSConsumePointsDetail(MSConsumePointsDetailGet mSConsumePointsDetail) throws Exception {
		List<MSConsumePointsDetail> selectList = baseDao.selectList(mSConsumePointsDetail, "MSConsumePointsDetailMapper.listMSConsumePointsDetail");
		if(selectList != null){
			selectList.forEach(mscpoint->{
				try {
					mscpoint.setMcpIncome(DESC.deyption(mscpoint.getMcpIncome(), mscpoint.getMemId()));
				} catch (MdSysException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					mscpoint.setMcpExpenditure(DESC.deyption(mscpoint.getMcpExpenditure(), mscpoint.getMemId()));
				} catch (MdSysException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		return selectList;
	}

	@Override
	public boolean addMDConsumePointsAndDetail(String memId,
			String consumePoints, String orderId, String orderSource,
			String operatorType, String operator, String remark) throws MdSysException {
		//增加基本账户总额
		double addAtq = DoubleCalculate.add(accountService.getTotalConsumePoints(memId),
				Double.valueOf(consumePoints));
		//调用增加积分方法
		boolean flag = accountAdjustService.addMDConsumePoints(memId, consumePoints, false);
		if(flag){
			//写入积分明细
			accountDetailService.saveConsumePoints(memId,
					orderId, orderSource, consumePoints, "0", String.valueOf(addAtq),
					operatorType, operator, remark);
		}
		return flag;
	}

}
