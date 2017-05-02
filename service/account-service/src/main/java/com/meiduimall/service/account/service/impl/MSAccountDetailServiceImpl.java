package com.meiduimall.service.account.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.meiduimall.service.account.constant.AccountReviseStatusEnum;
import com.meiduimall.service.account.constant.AccountReviseTypeEnum;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.ApplicationConstant;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.AccountReviseDetail;
import com.meiduimall.service.account.model.AddOrUpdateAccountReviseDetail;
import com.meiduimall.service.account.model.MSAccount;
import com.meiduimall.service.account.model.MSAccountDetail;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.MSAccountList;
import com.meiduimall.service.account.model.MSDict;
import com.meiduimall.service.account.model.ResBodyData;
import com.meiduimall.service.account.model.request.RequestAccountReviseDetail;
import com.meiduimall.service.account.model.request.RequestMSAccountList;
import com.meiduimall.service.account.service.MSAccountDetailService;
import com.meiduimall.service.account.util.DateUtil;
import com.meiduimall.service.account.util.DoubleCalculate;

@Transactional
@Component
public class MSAccountDetailServiceImpl implements MSAccountDetailService {

	private final static Logger logger=LoggerFactory.getLogger(MSAccountDetailServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public List<MSAccountDetail> listMSAccountDetail(MSAccountDetailGet mSAccountDetail) throws Exception {
		if(null != mSAccountDetail.getTradeType() && !"".equals(mSAccountDetail.getTradeType())){
			String[] split = mSAccountDetail.getTradeType().split(",");
			List<Object> arrayList = new ArrayList<Object>();
			for (String s : split) {
				arrayList.add(s);
			}
			mSAccountDetail.setTradeTypeList(arrayList);
		}

		List<MSAccountDetail> selectList = baseDao.selectList(mSAccountDetail, "listMSAccountDetail");
		return selectList;
	}
	
	@Override
	public List<MSAccountDetail> listMSAccountCondition(MSAccountDetailCondition mSAccountDetailCondition) {
		// TODO Auto-generated method stub
		List<MSAccountDetail> selectList = null;
		try {
			selectList = baseDao.selectList(mSAccountDetailCondition, "listMSAccountCondition");
		} catch (Exception e) {
			logger.error("查询余额流水出现错误，错误信息：%s", e.getMessage());
		}
		return selectList;
	}

	@Override
	public List<MSDict> listMSDict(String dicparentid) throws Exception {
		// TODO Auto-generated method stub
		List<MSDict>  selectList= baseDao.selectList(dicparentid, "MSDictMapper.queryMsDictByParentId");
		return selectList;
	}

	@Override
	public List<MSAccountList> listMSAccount(RequestMSAccountList msAccountListRequest) throws Exception {
		List<MSAccountList> selectList=null;
		try {
			selectList=baseDao.selectList(msAccountListRequest, "MSAccountMapper.queryListMSAccount");
			if(!CollectionUtils.isEmpty(selectList)) return selectList;
		}catch(Exception e){
			logger.error("查询会员列表出现错误，错误信息：%s", e.getMessage());
		}
		return selectList;
	}

	@Override
	public void addMSAccountReviseDetail(AddOrUpdateAccountReviseDetail dto) throws Exception {
		String reviseId = UUID.randomUUID().toString();
		dto.setId(reviseId);
		try {
			 baseDao.insert(dto, "AccountReviseDetailMapper.insertAccountReviseDetail");
		} catch (Exception e) {
			logger.error("添加调整余额操作出现错误，错误信息：%s", e.getMessage());
		}
	}

	@Override
	public Integer updateMSAccountReviseDetail(AddOrUpdateAccountReviseDetail dto) throws Exception {
		Integer result=0;
		try {
			result=baseDao.update(dto, "AccountReviseDetailMapper.updateAccountReviseDetail");
		} catch (Exception e) {
			logger.error("修改调整余额操作出现错误，错误信息：%s", e.getMessage());
		}
		return result;
	}

	@Override
	public AccountReviseDetail getMSAccountReviseDetail(String id) throws Exception {
		AccountReviseDetail detail=null;
		try {
			detail=baseDao.selectOne(id, "AccountReviseDetailMapper.getAccountReviseDetail");
			if(detail!=null) return detail;
		} catch (Exception e) {
			logger.error("查询会员余额明细操作出现错误，错误信息：%s", e.getMessage());
		}
		return detail;
	}

	@Override
	public List<AccountReviseDetail> queryMSAccountReviseDetailList(RequestAccountReviseDetail dto) throws Exception {
		List<AccountReviseDetail> list=null;
		try {
			list=baseDao.selectList(dto, "AccountReviseDetailMapper.queryAccountReviseDetailList");
			if(!CollectionUtils.isEmpty(list)) return list;
		} catch (Exception e) {
			logger.error("查询会员余额明细集合操作出现错误，错误信息：%s", e.getMessage());
		}
		return list;
	}

	@Override
	public ResBodyData examineMSAccountReviseDetail(AddOrUpdateAccountReviseDetail dto) throws Exception {
		//step1 查询余额调整记录
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
		AccountReviseDetail detail=this.getMSAccountReviseDetail(dto.getId());
		if(detail==null) return new ResBodyData(ApiStatusConst.ACCOUNT_REVISE_IS_NULL_ERROR,ApiStatusConst.ACCOUNT_REVISE_IS_NULL_ERROR_C);
		//审核同意操作
		if(dto.getOperate().equals(ApiStatusConst.OPERATE_AGREE)){
			//step2 修改余额调整记录状态为"已审核"
			dto.setStatus(AccountReviseStatusEnum.AUDITED_REVIEW.getCode());
			this.updateMSAccountReviseDetail(dto);
			//step3  查询用户余额 修改余额
			resBodyData=cutAccountMoney(detail);
		}else{
			//驳回操作  修改状态为"已拒绝"
			dto.setStatus(AccountReviseStatusEnum.REJECTED_REVIEW.getCode());
			this.updateMSAccountReviseDetail(dto);
		}
		return resBodyData;
	}
	
	public ResBodyData cutAccountMoney(AccountReviseDetail detail) throws Exception{
		//step1 查询账号
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", detail.getMemId());
		paramsMap.put("accountType", ApplicationConstant.ACCOUNT_TYPE_MONEY);
		MSAccount account=baseDao.selectOne(paramsMap, "MSAccountMapper.getAccountByMemId");
		if(account==null) return new ResBodyData(ApiStatusConst.ACCOUNT_IS_NULL_ERROR,ApiStatusConst.ACCOUNT_IS_NULL_ERROR_C);
		Double balance=0.0;
		//step2  判断调整类型   1-调增金额   2-调减金额  
		if(detail.getReviseType().equals(AccountReviseTypeEnum.CUT_DOWN.getName())){
			balance = DoubleCalculate.sub(Double.valueOf(account.getBalance()),detail.getReviseBalance().doubleValue());
			if(balance<0) return new ResBodyData(ApiStatusConst.ACCOUNT_REVISE_BALANCE_ERROR,ApiStatusConst.ACCOUNT_REVISE_BALANCE_ERROR_C);
		}else{
			balance = DoubleCalculate.add(Double.valueOf(account.getBalance()),detail.getReviseBalance().doubleValue());
		}
		//step3 修改会员账户余额
		updateAccountBalance(account.getId(), balance);
		//step4 记录调整金额流水记录
		saveAccountDetail(detail,account);
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	
	/**
	 * @Description: 修改会员余额
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午4:55:03
	 */
	private Integer updateAccountBalance(String id, Double balance){
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", id);
		paramsMap.put("accountType", ApplicationConstant.ACCOUNT_TYPE_MONEY);
		paramsMap.put("balance", String.valueOf(balance));
		Integer updateFlag=0;
		try {
			updateFlag = baseDao.update(paramsMap, "MSAccountMapper.updateAccountBalance");
		} catch (Exception e) {
			logger.error("修改会员账户余额出现错误，会员账户ID：%s，错误信息：%s", id, e.getMessage());
			return updateFlag;
		}
		return updateFlag;
	}
	
	/**
	 * @Description: 添加余额流水明细
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午4:49:13
	 */
	private void saveAccountDetail(AccountReviseDetail detail,MSAccount account) {
		//业务流水号  CWTZ+年月日时+6位随机数
		StringBuffer businesNo=new StringBuffer("CWTZ");
		businesNo.append(DateUtil.format(new Date(), DateUtil.YYYYMMDDHH));
		businesNo.append(100000+new Random().nextInt(900000));
		
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", detail.getMemId());
		paramsMap.put("orderId", businesNo.toString());
		paramsMap.put("accountId", account.getId());
		paramsMap.put("accountType", account.getType());
		paramsMap.put("tradeType", "CWTZ");
		paramsMap.put("tradeAmount", detail.getReviseBalance().toString());
		paramsMap.put("balance", account.getBalance());
		paramsMap.put("remark", detail.getReviseRemark());
		paramsMap.put("inOrOut", "-1");
		paramsMap.put("tradeDate", DateUtil.format(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS));
		try {
			baseDao.insert(paramsMap, "MSAccountDetailMapper.insertAccountDetail");
		} catch (Exception e) {
			logger.error("写入账户变动明细出现错误-1002，会员编号：%s，订单编号：%s，错误信息：%s", detail.getMemId(), detail.getId(), e.getMessage());
		}
	}

}
