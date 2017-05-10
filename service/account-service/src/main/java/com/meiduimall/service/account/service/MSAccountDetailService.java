package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.account.model.AccountReviseDetail;
import com.meiduimall.service.account.model.AddOrUpdateAccountReviseDetail;
import com.meiduimall.service.account.model.MSAccountDetail;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.MSAccountList;
import com.meiduimall.service.account.model.MSDict;
import com.meiduimall.service.account.model.request.RequestAccountReviseDetail;
import com.meiduimall.service.account.model.request.RequestMSAccountList;

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
	 * @Description: 查询根据条件查询会员列表 
	 * @param RequestMSAccountList
	 * @Author: jianhua.huang
	 * @Date:   2017年4月18日 下午12:18:16
	 */
	public List<MSAccountList> listMSAccount(RequestMSAccountList msAccountListRequest)throws Exception;
	
	/**
	 * @Description: 添加调整会员余额明细
     * @param RequestAccountReviseDetail
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午12:00:07
	 */
	public void addMSAccountReviseDetail(AddOrUpdateAccountReviseDetail detail)throws Exception;
	
	/**
	 * @Description: 修改调整会员余额明细
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午12:11:51
	 */
	public Integer updateMSAccountReviseDetail(AddOrUpdateAccountReviseDetail detail)throws Exception;
	
	/**
	 * 
	 * @Description:查看调整会员余额明细
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午12:17:38
	 */
	public AccountReviseDetail getMSAccountReviseDetail(String id)throws Exception;
	
	/**
	 * @Description: 查询调整会员余额集合
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午12:18:30
	 */
	public List<AccountReviseDetail> queryMSAccountReviseDetailList(RequestAccountReviseDetail reviseDetailRequest)throws Exception;
	
	/**
	 * @Description: 审核会员余额调整
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午3:51:09
	 */
	public ResBodyData examineMSAccountReviseDetail(AddOrUpdateAccountReviseDetail detail)throws Exception;
}
