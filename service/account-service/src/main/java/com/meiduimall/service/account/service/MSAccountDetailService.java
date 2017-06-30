package com.meiduimall.service.account.service;

import java.util.List;


import com.github.pagehelper.Page;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.account.model.AccountReviseDetail;
import com.meiduimall.service.account.model.AddOrUpdateAccountReviseDetail;
import com.meiduimall.service.account.model.MSAccountDetail;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.MSAccountList;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.MSDict;
import com.meiduimall.service.account.model.request.RequestAccountReviseDetail;
import com.meiduimall.service.account.model.request.RequestMSAccountList;
import com.meiduimall.service.account.model.request.RequestMSBankWithDrawDepostie;

public interface MSAccountDetailService {

	/**
	 * 新增余额流水接口 分页
	 * @param mSAccountDetail
	 * @return
	 * @throws Exception
	 */
	public List<MSAccountDetail> listMSAccountDetail(MSAccountDetailGet mSAccountDetail) throws Exception;
	
	/**
	 * 描述：根据条件查询余额流水
	 * @param mSAccountDetailCondition
	 * @return
	 * @throws Exception
	 */
	public List<MSAccountDetail> listMSAccountCondition(MSAccountDetailCondition mSAccountDetailCondition) throws Exception;
	
	
	public List<MSDict> listMSDict(String dicparentid) throws Exception;
	
	/**
	 * 查询根据条件查询会员列表 
	 * @param msAccountListRequest
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月5日 下午5:44:05
	 */
	public Page<MSAccountList> listMSAccount(RequestMSAccountList msAccountListRequest)throws MdBizException;
	
	/**
	 * 添加调整会员余额明细
	 * @param detail
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月5日 下午5:44:14
	 */
	public void addMSAccountReviseDetail(AddOrUpdateAccountReviseDetail detail)throws MdBizException;
	
	/**
	 * 修改调整会员余额明细
	 * @param detail
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月5日 下午5:44:23
	 */
	public Integer updateMSAccountReviseDetail(AddOrUpdateAccountReviseDetail detail)throws MdBizException;
	
	/**
	 * 查看调整会员余额明细
	 * @param id
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月5日 下午5:44:31
	 */
	public AccountReviseDetail getMSAccountReviseDetail(String id)throws MdBizException;
	
	/**
	 * 查询调整会员余额集合
	 * @param reviseDetailRequest
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月5日 下午5:44:40
	 */
	public List<AccountReviseDetail> queryMSAccountReviseDetailList(RequestAccountReviseDetail reviseDetailRequest)throws MdBizException;
	
	/**
	 * 审核会员余额调整
	 * @param detail
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月5日 下午5:44:48
	 */
	public ResBodyData examineMSAccountReviseDetail(AddOrUpdateAccountReviseDetail detail)throws MdBizException;

	/**
	 * 修改提现记录  并添加相应的操作记录
	 * @param deposit
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月5日 下午5:44:56
	 */
	public Integer updateWithDraw(RequestMSBankWithDrawDepostie deposit)throws MdBizException;
	/**
	 * 修改提现记录 驳回操作
	 * @param deposit
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月5日 下午5:45:05
	 */
	public void rejectWithDraw(RequestMSBankWithDrawDepostie deposit)throws MdBizException;
	/**
	 * 查看提现详情
	 * @param id
	 * @return
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月5日 下午5:45:13
	 */
	public MSBankWithdrawDeposit queryMSBankWithdrawDepositDetail(String id)throws MdBizException;
	
	/**
	 * 提现结算操作
	 * @param deposit
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年5月5日 下午5:45:21
	 */
	public void settlementWithDraw(RequestMSBankWithDrawDepostie deposit)throws MdBizException;
	
	/**
	 * 余额提现申请
	 * @param deposit
	 * @throws MdBizException
	 * @author: jianhua.huang  2017年4月28日 上午10:48:37
	 */
	public String saveBankWithdrawDeposit(RequestMSBankWithDrawDepostie deposit)throws MdBizException;
}
