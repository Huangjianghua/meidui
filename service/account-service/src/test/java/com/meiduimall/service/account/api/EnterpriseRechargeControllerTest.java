package com.meiduimall.service.account.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.account.model.AccountFlowEntity;
import com.meiduimall.service.account.model.BusinessManagementEntity;
import com.meiduimall.service.account.model.MSRechargeApply;
import com.meiduimall.service.account.model.RefundRequestEntity;

import net.sf.json.JSONObject;


public class EnterpriseRechargeControllerTest extends BaseControllerTest {
	private final static Logger logger=LoggerFactory.getLogger(EnterpriseRechargeControllerTest.class);
	

	private void resultSystemOutPut(String url,String json){
		try{
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
				.post(url)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("*********" + result.getResponse().getContentAsString());
			}
		});
		}catch(Exception e){
			logger.info("*********"+e);
		}
	}

	/**
	 * 外部充值申请
	 * @throws Exception
	 */
	@Test
	public void rechargeApply()throws Exception{
		 String url = "/member/account_service/v1/rechargeApply";
		 MSRechargeApply deposit=new MSRechargeApply();
		 
		 deposit.setOrderId("CZKFJT170511000001");
		 deposit.setExtorderId("KF1234");
		 deposit.setExtCompanyCode("KFJT");
		 deposit.setAccountId("18898447755");
		 deposit.setRechargeAmout("188.08");
		 deposit.setAccountType("KFCZ1");
		 deposit.setCallbackUrl("file:///C:/Users/Administrator/Desktop");
		 deposit.setMemId("1111");
		 deposit.setPhone("11222211");
		 JSONObject object=JSONObject.fromObject(deposit);
		 String json=object.toString();
		 
		 resultSystemOutPut(url,json);
	}
	/**
	 * 外部充值申请列表
	 * @throws Exception
	 */
	@Test
	public void queryExternalList() throws Exception {
		 String url = "/member/account_service/v1/findExternalRechargeList";
		 String json = "{\"flg\":\"1\"}";
		 
		 resultSystemOutPut(url,json);
	}
	/**
	 * 更新充值状态
	 * @throws Exception
	 */
	@Test
	public void updateRechargeStatus()throws Exception{
		 String url = "/member/account_service/v1/updateRechargeStatus";
		 MSRechargeApply deposit=new MSRechargeApply();
		 
		 deposit.setRecId("429e7324-40ab-48f8-9e4f-65cea41a6b72");
		 deposit.setStatus("2");
		 JSONObject object=JSONObject.fromObject(deposit);
		 String json=object.toString();
		 
		 resultSystemOutPut(url,json);
	}
	/**
	 * 调整授信,帐户充值
	 * @throws Exception
	 */
	@Test
	public void updateEnterpriseAccount()throws Exception{
		 String url = "/member/account_service/v1/updateEnterpriseAccount";
		 BusinessManagementEntity deposit=new BusinessManagementEntity();
		 
		 deposit.setDetId("8622d1f7-84fe-4ae6-ba07-134c63037c2b");
		 //deposit.setLineOfCredit("100");
		 //deposit.setCashAccount("100");
		 deposit.setCashRecharge("1500");
		 JSONObject object=JSONObject.fromObject(deposit);
		 String json=object.toString();
		 
		 resultSystemOutPut(url,json);
	}
	/**
	 * 帐户最大的充值上限（现金余额+授信）
	 * @throws Exception
	 */
	@Test
	public void rechargeCeiling()throws Exception{
		 String url = "/member/account_service/v1/rechargeCeiling";
		 BusinessManagementEntity deposit=new BusinessManagementEntity();
		 
		 deposit.setDetId("8622d1f7-84fe-4ae6-ba07-134c63037c2b");
		 JSONObject object=JSONObject.fromObject(deposit);
		 String json=object.toString();
		 
		 resultSystemOutPut(url,json);
	}
	/**
	 * 企业管理插入数据
	 * @throws Exception
	 */
	@Test
	public void insertBusinessManagement()throws Exception{
		 String url = "/member/account_service/v1/insertBusinessManagement";
		 BusinessManagementEntity businessManagementEntity=new BusinessManagementEntity();
		 businessManagementEntity.setEnterpriseIdentity("KFJT");
		 businessManagementEntity.setEnterpriseName("凯富集团");
		 businessManagementEntity.setEnterpriseKey("test123");
		 JSONObject object=JSONObject.fromObject(businessManagementEntity);
		 String json=object.toString();
		 resultSystemOutPut(url,json);
	}
	/**
	 * 企业管理插入数据
	 * @throws Exception
	 */
	@Test
	public void insertTripartiteEnterpriseDetail()throws Exception{
		 String url = "/member/account_service/v1/insertTripartiteEnterpriseDetail";
		 BusinessManagementEntity businessManagementEntity=new BusinessManagementEntity();
		 businessManagementEntity.setEntId("6e5462f0-0ded-4c7c-9eb9-1211da59bea5");
		 businessManagementEntity.setEnterpriseAccountName("K兑");
		 businessManagementEntity.setPersonalAccountType("KFCZ1");
		 businessManagementEntity.setLineOfCredit("100000.00");
		 businessManagementEntity.setCashAccount("100000.00");
		 JSONObject object=JSONObject.fromObject(businessManagementEntity);
		 String json=object.toString();
		 resultSystemOutPut(url,json);
	}
	/**
	 * 企业管理查询列表
	 * @throws Exception
	 */
	@Test
	public void findBusinessManagementList() throws Exception {
		 String url = "/member/account_service/v1/findBusinessManagementList";
		 String json = "{\"flg\":\"1\"}";
		 resultSystemOutPut(url,json);
	}
	/**
	 * 账户名称查询列表
	 * @throws Exception
	 */
	@Test
	public void findAccountNameList() throws Exception {
		 String url = "/member/account_service/v1/findAccountNameList";
		 String json = "{\"flg\":\"1\"}";
		 resultSystemOutPut(url,json);
	}
	/**
	 * 企业管理详情查询列表
	 * @throws Exception
	 */
	@Test
	public void findTripartiteEnterpriseDetailList() throws Exception {
		 String url = "/member/account_service/v1/findTripartiteEnterpriseDetailList";
		 String json = "{\"flg\":\"1\"}";
		 resultSystemOutPut(url,json);
	}
	/**
	 * 帐户流水插入数据
	 * @throws Exception
	 */
//	@Test
//	public void insertAccountFlow()throws Exception{
//		 String url = "/member/account_service/v1/insertAccountFlow";
//		 AccountFlowEntity accountFlowEntity=new AccountFlowEntity();
//		 accountFlowEntity.setFlowType("KFJT");
//		 accountFlowEntity.setExtcompanyCode("凯富集团");
//		 accountFlowEntity.setAccountType("K分");
//		 accountFlowEntity.setBeforeChangeAmout("100.2");
//		 accountFlowEntity.setAfterChangeAmout("152.24");
//		 accountFlowEntity.setIncome("10.00");
//		 accountFlowEntity.setExpenses("11.00");
//		 accountFlowEntity.setOrderId("CZKFJT170511000001");
//		 accountFlowEntity.setCreatedDate(new Date());
//		 accountFlowEntity.setRemark("无可奈何花落去村");
//		 accountFlowEntity.setCreatedBy("test1");
//		 JSONObject object=JSONObject.fromObject(accountFlowEntity);
//		 String json=object.toString();
//		 resultSystemOutPut(url,json);
//	}
	/**
	 * 帐户流水查询列表
	 * @throws Exception
	 */
	@Test
	public void findAccountFlowList() throws Exception {
		 String url = "/member/account_service/v1/findAccountFlowList";
		 String json = "{\"flg\":\"1\"}";
		 resultSystemOutPut(url,json);
	}
	/**
	 * 帐户流水插入数据
	 * @throws Exception
	 */
	@Test
	public void insertRefundRequest()throws Exception{
		 String url = "/member/account_service/v1/insertRefundRequest";
		 RefundRequestEntity refundRequestEntity=new RefundRequestEntity();
		 refundRequestEntity.setOrderId("TLKFJT170511000001");
		 refundRequestEntity.setExtorderId("KF1234");
		 refundRequestEntity.setExtcompanyName("凯富集团");
		 refundRequestEntity.setAccountId("18898447755");
		 refundRequestEntity.setRefundAmout("152.24");
		 refundRequestEntity.setAccountType("KFCZ1");
		 refundRequestEntity.setRequestDate("2017-1-1 10:00:00");
		 refundRequestEntity.setStatus("AR");
		 JSONObject object=JSONObject.fromObject(refundRequestEntity);
		 String json=object.toString();
		 resultSystemOutPut(url,json);
	}
	/**
	 * 帐户流水查询列表
	 * @throws Exception
	 */
	@Test
	public void findRefundRequestList() throws Exception {
		 String url = "/member/account_service/v1/findRefundRequestList";
		 String json = "{\"flg\":\"1\"}";
		 resultSystemOutPut(url,json);
	}
}

