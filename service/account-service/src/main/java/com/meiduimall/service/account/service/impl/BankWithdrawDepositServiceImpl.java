package com.meiduimall.service.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.service.BankWithdrawDepositService;
import com.meiduimall.service.account.util.DateUtil;

/**
 * 类名:  BankWithdrawDepositService<br>
 * 描述: 银行提现信息业务服务层，与银行提现信息相关的都定义在此  <br>
 * 创建时间: 2016-12-18
 */
@Transactional
@Component
public class BankWithdrawDepositServiceImpl implements BankWithdrawDepositService{

	private final static Logger logger=LoggerFactory.getLogger(BankWithdrawDepositServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public String addBankWithdrawDeposit(MSBankWithdrawDeposit dto) {
		String id = UUID.randomUUID().toString();
		dto.setId(id);
		try {
			Integer insertFlag = baseDao.insert(dto, "MSBankWithdrawDepositMapper.insertBankWithdrawDeposit");
			if(insertFlag <= 0){
				return null;
			}
			return id;
		} catch (Exception e) {
			logger.error("新增银行提现信息出现错误，会员编号：%s，错误信息：%s", dto.getMemId(), e.getMessage());
			return null;
		}
	}

	@Override
	public boolean updateDataStatus(String memId, String businessNo, String auditBy, Date auditDate, String status,
			String auditState) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		paramsMap.put("businessNo", businessNo);
		paramsMap.put("auditBy", auditBy);
		paramsMap.put("auditDate", DateUtil.format(auditDate,DateUtil.YYYY_MM_DD_HH_MM_SS));
		paramsMap.put("status", status);
		paramsMap.put("auditState", auditState);
		try {
			MSBankWithdrawDeposit dto = this.getBankWithdrawDeposit(memId, businessNo);
			if(dto != null){
				paramsMap.put("id", dto.getId());
				Integer updateFlag = baseDao.delete(paramsMap, "MSBankWithdrawDepositMapper.updateBankWithdrawDeposit");
				if(updateFlag <= 0){
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			logger.error("修改银行提现申请状态出现错误，申请单号：%s，错误信息：%s", businessNo, e.getMessage());
		}
		return false;
	}


	@Override
	public MSBankWithdrawDeposit getBankWithdrawDeposit(String memId, String businessNo) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		paramsMap.put("businessNo", businessNo);
		try {
			MSBankWithdrawDeposit dto = baseDao.selectOne(paramsMap, "MSBankWithdrawDepositMapper.selectBankWithdrawDeposit");
			return dto;
		} catch (Exception e) {
			logger.error("查询单条银行提现申请信息出现错误，申请单号：%s，错误信息：%s", businessNo, e.getMessage());
		}
		return null;
	}

	@Override
	public List<MSBankWithdrawDeposit> getBankWithdrawDepositList(String memId, boolean ispage,
			Map<String, String> paraMap, Map<String, String> resultMap) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("memId", memId);
		try {
			List<MSBankWithdrawDeposit> list = baseDao.selectList(paramsMap, "MSBankWithdrawDepositMapper.selectBankWithdrawDeposit");
			if(list != null && list.size() > 0){
				
				List<MSBankWithdrawDeposit> resultList = null;
				/** 程序分页 */  
				if(ispage){
					int current_page = Integer.parseInt(paraMap.get("current_page"));
					int page_size = Integer.parseInt(paraMap.get("page_size"));
					
					int fromIndex = (current_page-1) * page_size;
					int toIndex = current_page * page_size;
					
					if(fromIndex > list.size()){
						fromIndex = list.size();
					}
					if(toIndex >= list.size()){
						toIndex = list.size();
					}
					if(fromIndex > 0){
						fromIndex = fromIndex - 1;
					}
					resultList = list.subList(fromIndex, toIndex);
				}else{
					resultList = list;
				}
				resultMap.put("pageTotal", String.valueOf(list.size()));
				return resultList;
			}
		} catch (Exception e) {
			logger.error("查询银行提现申请列表出现错误，会员编号：%s，错误信息：%s", memId, e.getMessage());
		}
		return null;
	}

	@Override
	public List<MSBankWithdrawDeposit> getBankWithDrawConditon(MSAccountDetailCondition mSAccountDetailCondition) throws Exception {

		List<MSBankWithdrawDeposit> list =baseDao.selectList(mSAccountDetailCondition, "MSBankWithdrawDepositMapper.listwidthdrawCondition");
		return list;
	}

}
