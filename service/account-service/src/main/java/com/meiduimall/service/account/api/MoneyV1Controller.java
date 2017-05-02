package com.meiduimall.service.account.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.model.AccountReviseDetail;
import com.meiduimall.service.account.model.AddOrUpdateAccountReviseDetail;
import com.meiduimall.service.account.model.MSAccountDetail;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.MSAccountList;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.MSDict;
import com.meiduimall.service.account.model.ResBodyData;
import com.meiduimall.service.account.model.request.RequestAccountReviseDetail;
import com.meiduimall.service.account.model.request.RequestMSAccountList;
import com.meiduimall.service.account.service.BankWithdrawDepositService;
import com.meiduimall.service.account.service.MSAccountDetailService;
import com.meiduimall.service.account.util.DESC;



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
	
	
	/**余额流水（分页）*/
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
		} catch (Exception e) {
			logger.error("服务器错误:{}",e.toString());
			return new ResBodyData(1,"服务器错误");
		}
		return new ResBodyData(ApiStatusConst.SUCCESS,"成功",new PageInfo<>(listMSAccountDetail));
	}
	
	
	/**
	 * 描述：根据条件查询余额流水
	 * 
	 */
	@PostMapping(value="/list_account_condition")
	public ResBodyData  listMSAccountCondition(@RequestBody MSAccountDetailCondition mSAccountDetailCondition){
		List<MSAccountDetail> listMSAccountDetail = null;
		try{
			
			
			//分页查询
			if(mSAccountDetailCondition.getFlg().equals("1")){
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
				if(null !=listMSAccountDetail.get(i).getPhone()){
					
					String phone= DESC.deyption(listMSAccountDetail.get(i).getPhone());
					listMSAccountDetail.get(i).setPhone(phone);
				}
				
				if(null !=listMSAccountDetail.get(i).getLoginName()){

					String loginName = DESC.deyption(listMSAccountDetail.get(i).getLoginName());
					listMSAccountDetail.get(i).setLoginName(loginName);
				}
				
			}
			
		}catch(Exception e){
			logger.info("服务器错误:{}", e.getMessage());
			return new ResBodyData(1,"服务器错误");
		}
		
		return new ResBodyData(0,"成功",new PageInfo<>(listMSAccountDetail));
	}
	
	
	/**
	 * 描述：查询交易类型
	 * @param dicparentid
	 * @return
	 */
	@PostMapping(value="/list_msdict")
	public ResBodyData  listMSDict(@RequestBody String dicparentid){
		List<MSDict> listMSDict= null;
		try{
			JSONObject jb= JSONObject.fromObject(dicparentid);
			listMSDict=mSAccountDetailService.listMSDict(jb.getString("dicparentid"));
		}catch(Exception e){
			logger.error("服务器错误:%s", e.getMessage());
			return new ResBodyData(1,"服务器错误");
		}
		
		return new ResBodyData(0,"成功",listMSDict);
	}
	
	/**
	 * @Description: 新需求  根据条件查询会员列表  
	 * @Author: jianhua.huang
	 * @Date:   2017年4月18日 上午11:44:54
	 */
	@RequestMapping(value="/list_account")
	public ResBodyData  listMSAccount(@RequestBody RequestMSAccountList msAccountListRequest){
		List<MSAccountList> msAccountLists = null;
		try{
			//分页查询
			if(msAccountListRequest.getFlg().equals("1")){
				//分页
				PageHelper.startPage(msAccountListRequest.getPageNum(), msAccountListRequest.getPageSize());
				PageHelper.orderBy("memRegTime DESC");
			}else{
				//不分页
				PageHelper.startPage(msAccountListRequest.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("memRegTime DESC");
			}
			msAccountLists=mSAccountDetailService.listMSAccount(msAccountListRequest);
		}catch(Exception e){
			logger.error("服务器错误:%s", e.getMessage());
			return new ResBodyData(1,"服务器错误");
		}
		return new ResBodyData(0,"成功",new PageInfo<>(msAccountLists));
	}
	
	
	/**
	 * 描述：提现记录查询接口实现
	 * @param mSAccountDetailCondition
	 * @return
	 */
	@PostMapping(value="/list_withdraw_condition")
	public ResBodyData  listWithDrawCondition(@RequestBody MSAccountDetailCondition mSAccountDetailCondition){
		List<MSBankWithdrawDeposit> listMSBankWithdrawDeposit = null;
		try{
			
			
			//分页查询
			if(mSAccountDetailCondition.getFlg().equals("1")){
				//分页
				PageHelper.startPage(mSAccountDetailCondition.getPageNum(), mSAccountDetailCondition.getPageSize());
				PageHelper.orderBy("apply_date DESC");
			}else{
				//不分页 
				PageHelper.startPage(mSAccountDetailCondition.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("apply_date DESC");
			}
			
			listMSBankWithdrawDeposit=bankWithdrawDepositService.getBankWithDrawConditon(mSAccountDetailCondition);
			
			
			for(int i=0;i<listMSBankWithdrawDeposit.size();i++){
				//手机号解密
				String phone= DESC.deyption(listMSBankWithdrawDeposit.get(i).getPhone());
				listMSBankWithdrawDeposit.get(i).setPhone(phone);
				//登录名解密
				String loginName = DESC.deyption(listMSBankWithdrawDeposit.get(i).getLoginName());
				listMSBankWithdrawDeposit.get(i).setLoginName(loginName);
			}
			
		}catch(Exception e){
			logger.error("服务器错误:%s", e.getMessage());
			return new ResBodyData(1,"服务器错误");
		}
		
		return new ResBodyData(0,"成功",new PageInfo<>(listMSBankWithdrawDeposit));
	}
	
	
	/**
	 * @Description: 添加会员余额调整明细
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 上午11:45:30
	 */
	@PostMapping(value="/add_account_revision_detail")
	public ResBodyData  addMSAccountRevisionDetail(@RequestBody AddOrUpdateAccountReviseDetail detail){
		try{
			mSAccountDetailService.addMSAccountReviseDetail(detail);
		}catch(Exception e){
			logger.error("服务器错误:%s", e.getMessage());
			return new ResBodyData(1,"服务器错误,添加失败");
		}
		return new ResBodyData(0,"成功");
	}
	
	/**
	 * @Description: 修改会员余额调整明细
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午2:45:30
	 */
	@PostMapping(value="/update_account_revision_detail")
	public ResBodyData  updateMSAccountRevisionDetail(@RequestBody AddOrUpdateAccountReviseDetail detail){
		try{
		  mSAccountDetailService.updateMSAccountReviseDetail(detail);
		}catch(Exception e){
			logger.error("服务器错误:%s", e.getMessage());
			return new ResBodyData(1,"服务器错误,修改失败");
		}
		return new ResBodyData(0,"成功");
	}
	
	/**
	 * @Description: 查看会员余额调整明细
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午2:45:30
	 */
	@PostMapping(value="/get_account_revision_detail")
	public ResBodyData  getMSAccountRevisionDetail(@RequestBody String id){
		AccountReviseDetail detail=null;
		try{
			detail=mSAccountDetailService.getMSAccountReviseDetail(id);
		}catch(Exception e){
			logger.error("服务器错误:%s", e.getMessage());
			return new ResBodyData(1,"服务器错误,查看失败");
		}
		return new ResBodyData(0,"成功",detail);
	}
	
	
	/**
	 * @Description: 查看会员余额调整明细集合  flg区别 分页
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午2:45:30
	 */
	@PostMapping(value="/query_account_revision_detail_list")
	public ResBodyData  queryMSAccountRevisionDetailList(@RequestBody RequestAccountReviseDetail detailRequest){
		List<AccountReviseDetail> list = null;
		try {
			//分页查询
			if(detailRequest.getFlg().equals("1")){
				//分页
				PageHelper.startPage(detailRequest.getPageNum(), detailRequest.getPageSize());
				PageHelper.orderBy("msard.created_date DESC");
			}else{
				//不分页
				PageHelper.startPage(detailRequest.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("msard.created_date DESC");
			}
			list = mSAccountDetailService.queryMSAccountReviseDetailList(detailRequest);
		} catch (Exception e) {
			logger.error("服务器错误:%s", e.getMessage());
			return new ResBodyData(1, "服务器错误,查看失败");
		}
		return new ResBodyData(0, "成功", new PageInfo<>(list));
	}

	/**
	 * @Description: 会员余额审核
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午3:48:48
	 */
	@PostMapping(value="/examine_account_revision_detail")
	public ResBodyData  examineMSAccountRevisionDetail(@RequestBody AddOrUpdateAccountReviseDetail detail){
		ResBodyData resBodyData=null;
		try {
			resBodyData=mSAccountDetailService.examineMSAccountReviseDetail(detail);
		} catch (Exception e) {
			logger.error("服务器错误:%s", e.getMessage());
			return new ResBodyData(1, "服务器错误,修改失败");
		}
		return resBodyData;
	}
}
