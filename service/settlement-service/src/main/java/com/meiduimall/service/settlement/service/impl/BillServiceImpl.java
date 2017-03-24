package com.meiduimall.service.settlement.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.meiduimall.service.settlement.common.CodeRuleUtil;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmMzfBillWater;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.BeanSelfAware;
import com.meiduimall.service.settlement.service.BillService;
import com.meiduimall.service.settlement.util.DateUtil;
import com.meiduimall.service.settlement.vo.BilledWaterVO2Merge;
import com.meiduimall.service.settlement.vo.OrderToBilledVO;

/**
 * 生成账单，生成账单流水，更新账户
 * @author chencong
 *
 */
@Service
public class BillServiceImpl implements BillService,BeanSelfAware {
	
	protected final Logger log = LoggerFactory.getLogger(BillServiceImpl.class);
	
	@Autowired
	private BaseMapper baseMapper;
	
	@Autowired
	private AgentService agentService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	

	/**
	 * spring声明式事务 同一类内方法调用事务失效
	 * //http://blog.csdn.net/jiesa/article/details/53438342
	 */
	@Autowired
	private BillService proxySelf;

	@Override
	public void setSelf(Object proxyBean) {
		this.proxySelf=(BillService) proxyBean;
 
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Collection<String> createBills() throws Exception {
		
		Collection<String> orderSns=new ArrayList<String>();
		
		Long verifyTime=DateUtil.getLastDayEndBySecond();
		//查询账单
		List<EcmMzfBillWater> billList = baseMapper.selectList(verifyTime, "EcmBillMapper.queryReviewedShareOrder");
		List<OrderToBilledVO> orderToBilledList = baseMapper.selectList(verifyTime, "EcmBillMapper.queryOrderToBilled");
		
		Date billCreatedtime=sdf.parse(DateUtil.getCurrentDay()); //账单创建日期
		Date billtime=sdf.parse(DateUtil.getUpDAY()); //账单日期
		log.info("账单生成时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 账单日期："+billtime+" 需要插入到账单流水的条数："+billList.size());
		if(billList.size()>0)
		{
			
			Timestamp waterOpTime=new Timestamp(System.currentTimeMillis());
			//boolean billedSuccess=true;
			for (int j=0;j<billList.size();j++) {
				EcmMzfBillWater bill=billList.get(j);
				
				try {
					this.proxySelf.handleBill(bill,billCreatedtime,billtime,waterOpTime);
					this.proxySelf.createBillAndOrderMapping(bill,orderToBilledList);
				} catch (Exception e) {
					log.error("编号为："+bill.getCode()+ ",账单处理异常:"+e.toString());
					throw e;
				}
			}
			
			Set<String> orderNos=new HashSet<String>();
			for(OrderToBilledVO vo:orderToBilledList){
				orderNos.add(vo.getOrderSn());  //get the unique orderSn collection.
			}
			this.proxySelf.updateOrderBillStatus(orderNos);
			orderSns.addAll(orderNos);
		}
		
		return orderSns;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void handleBill(EcmMzfBillWater bill,Date billCreatedtime,Date billtime,Timestamp opTime) throws Exception
	{
		bill.setBillAddTime(billCreatedtime); //设置账单创建日期
		bill.setBillTime(billtime);//设置账单日期
		double amount=bill.getAmount(); //账单金额
		int type=bill.getType();//角色类型
		String code=bill.getCode();//角色编号
		//设置账单编号
		String billid=CodeRuleUtil.getBillid(bill.getType(),bill.getCode());
		bill.setBillId(billid);
		//开始生成账单流水数据
		if(StringUtils.isNotBlank(code))
		{
			int i=baseMapper.insert(bill,"EcmBillMapper.createBillWater");
			log.info("生成编号为"+code+"的账单数据："+i+"条 金额:"+amount+" 角色类型:"+type);
			
			//BUG #3029::金额为0的账单不需要生成流水
			if(!SettlementUtil.isZero(amount)){
				//账单数据插入到流水汇总表
				EcmMzfWater water=null;
				water=new EcmMzfWater();
				water.setWaterId(CodeRuleUtil.getBillFlowCode(type,code));
				water.setRemark(sdf.format(billtime)+"账单");
				water.setCode(code);
				water.setMoney(new BigDecimal(amount));
				water.setWaterType("2");
				water.setExtId(billid);
				water.setOpTime(opTime);
				int i2=agentService.insertWater(water);
				log.info("生成编号为"+code+"的流水汇总数据："+i2+"条  金额:"+amount+" 角色类型:"+type);	
				
				//更新账户
				String newtype=String.valueOf(CodeRuleUtil.getAccountRoleType(type));
				EcmMzfAccount ccount=null;
				ccount=new EcmMzfAccount();
				ccount.setAccountRoleType(newtype);
				ccount.setBalance(new BigDecimal(amount));//double改为BigDecimal
				ccount.setCode(code);
				int d=agentService.updateAccount(ccount);
				log.info("更新编号"+code+"的账户："+d+"条 金额:"+amount+" 角色类型:"+newtype);
			}
		}
	}
	
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void createBillAndOrderMapping(EcmMzfBillWater bill, List<OrderToBilledVO> orderToBilledList) throws Exception {
		if(bill!=null && StringUtils.isNotBlank(bill.getCode())){
			final String code=bill.getCode();
			Collection<OrderToBilledVO> orderToBilledVos=Collections2.filter(orderToBilledList, new Predicate<OrderToBilledVO>(){
				@Override
				public boolean apply(OrderToBilledVO vo) {
					return code.equalsIgnoreCase(vo.getCode());
				}
				
			});
			
			if(orderToBilledVos!=null && !orderToBilledVos.isEmpty()){
				for(OrderToBilledVO ob:orderToBilledVos){
					try {
						ob.setBillId(bill.getBillId());
						//baseMapper.update(ob, "EcmBillMapper.updateOrderBillIdByType");
						
						baseMapper.insert(ob, "EcmBillMapper.createBillAndOrderMapping");
						
						log.info("EcmBillMapper.createBillAndOrderMapping success: orderSn:" + ob.getOrderSn() + " | type:"+ob.getType() + " | billId:" + ob.getBillId());
					} catch (Exception e) {
						log.error("EcmBillMapper.createBillAndOrderMapping got error for orderSn:" + ob.getOrderSn() + " | type:"+ob.getType() + " | billId:" + ob.getBillId(), e);
						throw e;
					}
				}
				
			}
		}
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateOrderBillStatus(Collection<String> orderSnList) throws Exception {
		if(orderSnList!=null && !orderSnList.isEmpty()){
			for(String orderSn:orderSnList){	
				try {
					EcmMzfOrderStatus os=new EcmMzfOrderStatus(orderSn,3,"已结算");
					baseMapper.update(os, "EcmMzfOrderStatusMapper.updateBillStatus");
					log.info("EcmMzfOrderStatusMapper.updateBillStatus success: orderSn:" + orderSn);
				} catch (Exception e) {
					log.error("EcmMzfOrderStatusMapper.updateBillStatus got error for orderSn:" + orderSn, e);
					throw e;
				}
			}
		}
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public void mergeBilledWaters(List<BilledWaterVO2Merge> mergeWaterVOList) throws Exception{
		
		if(mergeWaterVOList!=null && !mergeWaterVOList.isEmpty()){
			final List<Integer> ids2Del=new ArrayList<Integer>();
			final List<Integer> ids2Keep=new ArrayList<Integer>();
			final Map<Integer,Integer> idRelations=new HashMap<Integer,Integer>();
			
			for(BilledWaterVO2Merge vo:mergeWaterVOList){
				Integer id2Keep=vo.getIdKeep();
				Integer id2Del=vo.getIdDel();
				ids2Del.add(id2Del);
				ids2Keep.add(id2Keep);
				idRelations.put(id2Keep, id2Del);
			}
			
			List<EcmMzfWater> EcmMzfWaterList2Del = baseMapper.selectList(ImmutableMap.of("ids",ids2Del), "EcmMzfWaterMapper.getWatersByIds");
			
			List<EcmMzfWater> EcmMzfWaterList2Keep = baseMapper.selectList(ImmutableMap.of("ids",ids2Keep), "EcmMzfWaterMapper.getWatersByIds");
			
			Map<Integer,EcmMzfWater> EcmMzfWaterMap2Del=SettlementUtil.convert2Map(EcmMzfWaterList2Del, "id");
			
			//合并同一用户重复流水money
			for(EcmMzfWater water:EcmMzfWaterList2Keep){
				Integer id2keep=water.getId();
				Integer id2Del=idRelations.get(id2keep);
				EcmMzfWater water2Del=EcmMzfWaterMap2Del.get(id2Del);
				water.setMoney(water.getMoney().add(water2Del.getMoney()));
			}
			//更新money
			for(EcmMzfWater water:EcmMzfWaterList2Keep){
				baseMapper.update(water, "EcmMzfWaterMapper.updateMoneybyId");
			}
			//删除流水
			baseMapper.delete(ImmutableMap.of("ids",ids2Del), "EcmMzfWaterMapper.delWatersByIds");
			
		}
		
	}

	

}