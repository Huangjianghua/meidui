package com.meiduimall.service.account.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.Constant;
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
import com.meiduimall.service.account.service.BankWithdrawDepositService;
import com.meiduimall.service.account.service.MSAccountDetailService;
import com.meiduimall.service.account.util.DESC;

import net.sf.json.JSONObject;


 
/**
 * 余额相关操作
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class MoneyV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(GciV1Controller.class);
	
	@Autowired
	private MSAccountDetailService mSAccountDetailService;
	
	@Autowired
	private BankWithdrawDepositService bankWithdrawDepositService;
	
	
	/**
	 * 余额流水（分页）
	 * @param mSAccountDetail
	 * @return
	 * @throws Exception
	 * @author: jianhua.huang  2017年5月5日 下午5:30:15
	 */
	@PostMapping(value="/list_account_detail")
	public ResBodyData listMSAccountDetail(@RequestBody MSAccountDetailGet mSAccountDetail) throws Exception{
		List<MSAccountDetail> listMSAccountDetail = null;
		try {
			if(mSAccountDetail.getMemId() == null){
				return new ResBodyData(1,"memId为空");
			}else if("".equals(mSAccountDetail.getMemId())){
				return new ResBodyData(1,"memId为空"); 
			}else{
				PageHelper.startPage(mSAccountDetail.getPageNum(), mSAccountDetail.getPageSize());
				PageHelper.orderBy("create_date DESC");
				listMSAccountDetail = mSAccountDetailService.listMSAccountDetail(mSAccountDetail);
			}
		}catch (Exception e) {
			logger.error("查询余额流水listMSAccountDetail服务器错误:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS,"成功",new PageInfo<>(listMSAccountDetail));
	}
	
	/**
	 *  描述：根据条件查询余额流水
	 * @param mSAccountDetailCondition
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:30:32
	 */
	@PostMapping(value="/list_account_condition")
	public ResBodyData  listMSAccountCondition(@RequestBody MSAccountDetailCondition mSAccountDetailCondition){
		List<MSAccountDetail> listMSAccountDetail = null;
		try{
			//分页查询
			if(mSAccountDetailCondition.getFlg().equals(Constant.ONE)){
				//分页
				PageHelper.startPage(mSAccountDetailCondition.getPageNum(), mSAccountDetailCondition.getPageSize());
				PageHelper.orderBy("create_date DESC");
			}else{
				//不分页
				PageHelper.startPage(mSAccountDetailCondition.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("create_date DESC");
			}
			listMSAccountDetail=mSAccountDetailService.listMSAccountCondition(mSAccountDetailCondition);
			for(int i=0;i<listMSAccountDetail.size();i++){
				String phone= DESC.deyption(listMSAccountDetail.get(i).getPhone());
				listMSAccountDetail.get(i).setPhone(phone);
				
				String loginName = DESC.deyption(listMSAccountDetail.get(i).getLoginName());
				listMSAccountDetail.get(i).setLoginName(loginName);
			}
		}catch(Exception e){
			logger.error("根据条件查询余额流水listMSAccountCondition错误:{}", e);
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,new PageInfo<>(listMSAccountDetail));
	}
	
	/**
	 * 描述：查询交易类型
	 * @param dicparentid
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:30:43
	 */
	@PostMapping(value="/list_msdict")
	public ResBodyData  listMSDict(@RequestBody String dicparentid){
		List<MSDict> listMSDict= null;
		try{
			JSONObject jb= JSONObject.fromObject(dicparentid);
			listMSDict=mSAccountDetailService.listMSDict(jb.getString("dicparentid"));
		}catch(Exception e){
			logger.error("查询交易类型listMSDict错误:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,listMSDict);
	}
	
	/**
	 *  新需求  根据条件查询会员列表  
	 * @param msAccountListRequest
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:31:18
	 */
	@RequestMapping(value="/list_account")
	public ResBodyData  listMSAccount(@RequestBody RequestMSAccountList msAccountListRequest){
		List<MSAccountList> msAccountLists = null;
		try{
			//分页查询
			if(msAccountListRequest.getFlg().equals(Constant.ONE)){
				//分页
				PageHelper.startPage(msAccountListRequest.getPageNum(), msAccountListRequest.getPageSize());
				PageHelper.orderBy("memRegTime DESC");
			}else{
				//不分页
				PageHelper.startPage(msAccountListRequest.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("memRegTime DESC");
			}
			msAccountLists=mSAccountDetailService.listMSAccount(msAccountListRequest);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("查询会员列表Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,new PageInfo<>(msAccountLists));
	}
	
	/**
	 * 描述：提现记录查询接口实现
	 * @param mSAccountDetailCondition
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:31:38
	 */
	@PostMapping(value="/list_withdraw_condition")
	public ResBodyData  listWithDrawCondition(@RequestBody MSAccountDetailCondition mSAccountDetailCondition){
		List<MSBankWithdrawDeposit> listMSBankWithdrawDeposit = null;
		try{
			//分页查询
			if(mSAccountDetailCondition.getFlg().equals(Constant.ONE)){
				//分页
				PageHelper.startPage(mSAccountDetailCondition.getPageNum(), mSAccountDetailCondition.getPageSize());
				PageHelper.orderBy("apply_date DESC");
			}else{
				//不分页 
				PageHelper.startPage(mSAccountDetailCondition.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("apply_date DESC");
			}
			listMSBankWithdrawDeposit=bankWithdrawDepositService.getBankWithDrawConditon(mSAccountDetailCondition);
		}catch(Exception e){
			logger.error("查询提现记录列表Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,new PageInfo<>(listMSBankWithdrawDeposit));
	}
	
	/**
	 * 添加会员余额调整明细
	 * @param detail
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:31:48
	 */
	@PostMapping(value="/add_account_revision_detail")
	public ResBodyData  addMSAccountRevisionDetail(@RequestBody AddOrUpdateAccountReviseDetail detail){
		try{
			mSAccountDetailService.addMSAccountReviseDetail(detail);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("添加会员余额调整明细Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	
	/**
	 * 修改会员余额调整明细
	 * @param detail
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:31:57
	 */
	@PostMapping(value="/update_account_revision_detail")
	public ResBodyData  updateMSAccountRevisionDetail(@RequestBody AddOrUpdateAccountReviseDetail detail){
		try{
		  mSAccountDetailService.updateMSAccountReviseDetail(detail);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("修改会员余额调整明细Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	
	/**
	 * 查看会员余额调整明细
	 * @param id
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:32:18
	 */
	@PostMapping(value="/get_account_revision_detail")
	public ResBodyData  getMSAccountRevisionDetail(@RequestBody String id){
		AccountReviseDetail detail=null;
		try{
			detail=mSAccountDetailService.getMSAccountReviseDetail(id);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("查看会员余额调整明细Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,detail);
	}
	
	/**
	 * 查看会员余额调整明细集合  flg区别 分页
	 * @param detailRequest
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:32:28
	 */
	@PostMapping(value="/query_account_revision_detail_list")
	public ResBodyData  queryMSAccountRevisionDetailList(@RequestBody RequestAccountReviseDetail detailRequest){
		List<AccountReviseDetail> list = null;
		try {
			//分页查询
			if(detailRequest.getFlg().equals(Constant.ONE)){
				//分页
				PageHelper.startPage(detailRequest.getPageNum(), detailRequest.getPageSize());
				PageHelper.orderBy("msard.created_date DESC");
			}else{
				//不分页
				PageHelper.startPage(detailRequest.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("msard.created_date DESC");
			}
			list = mSAccountDetailService.queryMSAccountReviseDetailList(detailRequest);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch (Exception e) {
			logger.error("查看会员余额调整列表Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M, new PageInfo<>(list));
	}

	/**
	 *  会员余额审核
	 * @param detail
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:32:37
	 */
	@PostMapping(value="/examine_account_revision_detail")
	public ResBodyData  examineMSAccountRevisionDetail(@RequestBody AddOrUpdateAccountReviseDetail detail){
		ResBodyData resBodyData=null;
		try {
			resBodyData=mSAccountDetailService.examineMSAccountReviseDetail(detail);
		} catch (MdBizException e) {
			logger.error("会员余额审核Controller异常:{}", e.getMessage());
			throw new ApiException(e.getCode(), e.getMessage());
		}
		return resBodyData;
	}
	
	/**
	 * 修改提现记录(审核同意)
	 * @param deposit
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:32:47
	 */
	@PostMapping(value="/update_withdraw")
	public ResBodyData  updateWithDraw(@RequestBody RequestMSBankWithDrawDepostie deposit){
		try {
			mSAccountDetailService.updateWithDraw(deposit);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch (Exception e) {
			logger.error("审核同意操作修改提现记录Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	/**
	 * 修改提现记录(审核驳回)
	 * @param deposit
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:32:55
	 */
	@PostMapping(value="/reject_withdraw")
	public ResBodyData  rejectWithDraw(@RequestBody RequestMSBankWithDrawDepostie deposit){
		try {
			mSAccountDetailService.rejectWithDraw(deposit);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch (Exception e) {
			logger.error("审核驳回操作修改提现记录Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	
	/**
	 * 查看提现明细
	 * @param mSAccountDetailCondition
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:33:05
	 */
	@PostMapping(value="/query_withdraw_detail")
	public ResBodyData  queryWithDrawDetail(@RequestBody  MSAccountDetailCondition mSAccountDetailCondition){
		ResBodyData resultData=new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
		try {
			MSBankWithdrawDeposit deposit=mSAccountDetailService.queryMSBankWithdrawDepositDetail(mSAccountDetailCondition.getId());
			resultData.setData(deposit);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch (Exception e) {
			logger.error("查看提现明细Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		return resultData;
	}
	/**
	 * 提现结算
	 * @param deposit
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:33:13
	 */
	@PostMapping(value="/settlement_withdraw")
	public ResBodyData  settlementWithDraw(@RequestBody RequestMSBankWithDrawDepostie deposit){
		try {
			mSAccountDetailService.settlementWithDraw(deposit);
		} catch (MdBizException e) {
			logger.error("提现结算操作Controller异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	
	/**
	 * 余额提现申请
	 * @param deposit
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:33:20
	 */
	@PostMapping(value="/save_withdraw")
	public ResBodyData  saveBankWithDraw(@RequestBody RequestMSBankWithDrawDepostie deposit){
		//step1 检查参数
		this.checkSaveBankWithDrawParam(deposit);
		try {
			//stpe2 执行提现申请
			mSAccountDetailService.saveBankWithdrawDeposit(deposit);
		} catch (MdBizException e) {
			logger.error("余额提现申请操作Controller异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	
	/**
	 * 检查提现参数
	 * @param deposit
	 * @throws ApiException
	 * @author: jianhua.huang  2017年5月2日 上午10:37:09
	 */
	private void checkSaveBankWithDrawParam(RequestMSBankWithDrawDepostie deposit) throws ApiException{
		if(deposit==null){
			throw new ApiException(ApiStatusConst.PARAMETER_IS_NULL);
		}else if(StringUtils.isBlank(deposit.getMemId())){
			throw new ApiException(ApiStatusConst.PARAMETER_MEMID_IS_NULL);
		}else if(StringUtils.isBlank(deposit.getAccountNo())){
			throw new ApiException(ApiStatusConst.PARAMETER_ACCOUNTNO_IS_NULL);
		}else if(StringUtils.isBlank(deposit.getApplyCarryCash())){
			throw new ApiException(ApiStatusConst.PARAMETER_APPLYCARRYCASH_IS_NULL);
		}
	}
}
