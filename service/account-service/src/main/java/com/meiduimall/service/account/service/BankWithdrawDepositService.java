package com.meiduimall.service.account.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;


/**
 * 类名:  BankWithdrawDepositService<br>
 * 描述: 银行提现服务接口类 <br>
 * 创建时间: 2016-12-18
 */
public interface BankWithdrawDepositService {
	 
	
	/**
	 * 方法名: addBankWithdrawDeposit<br>
	 * 描述: 添加一条银行提现信息，返回业务主键 <br>
	 * 创建时间: 2016-12-18
	 * @param dto
	 * @return
	 */
	public String addBankWithdrawDeposit(MSBankWithdrawDeposit dto);

	/**
	 * 方法名: updateDataStatus<br>
	 * 描述:  通过会员标识与业务订单号，修改订单状态 <br>
	 * 创建时间: 2016-12-18
	 * @param memId
	 * @param businessNo
	 * @param auditBy
	 * @param auditDate
	 * @param status
	 * @param auditState
	 * @return
	 */
	public boolean updateDataStatus(String memId, String businessNo, String auditBy,
			java.util.Date auditDate, String status, String auditState);
	
	/**
	 * 方法名: getBankWithdrawDeposit<br>
	 * 描述: 根据会员标识与业务订单号查询银行提现信息 <br>
	 * 创建时间: 2016-12-18
	 * @param memId
	 * @param businessNo
	 * @return
	 */
	public MSBankWithdrawDeposit getBankWithdrawDeposit(String memId,String businessNo);
	
	/**
	 * 方法名: getBankWithdrawDepositList<br>
	 * 描述: 条件查询银行提现记录，第四个参数返回总数据量 <br>
	 * @param memId
	 * @param ispage
	 * @param parasMap
	 * @return
	 */
	public List<MSBankWithdrawDeposit> getBankWithdrawDepositList(String memId, boolean ispage,
			Map<String, String> parasMap, Map<String, String> resultMap);
	
	
	/**
	 * 描述：根据条件查询提现记录
	 * @param mSAccountDetailCondition
	 * @return
	 * @throws Exception 
	 */
	public List<MSBankWithdrawDeposit> getBankWithDrawConditon(MSAccountDetailCondition mSAccountDetailCondition) throws Exception;
	
}
