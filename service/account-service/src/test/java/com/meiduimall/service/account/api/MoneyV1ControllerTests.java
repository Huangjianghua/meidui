package com.meiduimall.service.account.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.core.util.JsonUtils;

import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.AddOrUpdateAccountReviseDetail;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;

import com.meiduimall.service.account.model.request.RequestAccountReviseDetail;
import com.meiduimall.service.account.util.DESC;

import net.sf.json.JSONObject;



/**

 * @FileName: MoneyV1ControllerTests.java
 * @Author:   jianhua.huang 

 * @Date:     2017年4月18日 下午3:39:45
 * @Description: 测试新会员列表接口	

 */
public class MoneyV1ControllerTests extends BaseControllerTest {

	private final static Logger logger=LoggerFactory.getLogger(MoneyV1ControllerTests.class);
	/**
	 * 余额流水
	 * @throws Exception
	 */
    @Test
    public void listMSAccountDetail() throws Exception{
    	MSAccountDetailGet model=new MSAccountDetailGet();
    	model.setMemId(memId);
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/list_account_detail")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(model)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>余额流水分页API>>执行结果:{}",result.getResponse().getContentAsString());;


			}
		});
    }

	
	/**
	 * @Author: jianhua.huang
	 * @Date:   2017年4月18日 下午3:46:31
	 */
	@Test
	public void queryAccountListTest() throws Exception {
		 String url = "/member/account_service/v1/list_account";
		 String json = "{\"memParentIdPhone\":\"13418786965\",\"flg\":\"1\"}";
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * @Description: 添加测试
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午2:18:31
	 */
	@Test
	public void addMSAccountRevisionDetailTest() throws Exception {
		 String url = "/member/account_service/v1/add_account_revision_detail";
		 AddOrUpdateAccountReviseDetail dto=new AddOrUpdateAccountReviseDetail();
		 dto.setMemId("72063681-7408-435c-88fd-cd837c95c66e");
		 dto.setReviseType("1");
		 dto.setStatus("WR");
		 dto.setIsDelete("N");
		 dto.setCreateUser("huang");
		 dto.setUpdateUser("huang2");
		 dto.setReviewRemark("是肯定会罚款决定付款后会计师的疯狂的11");
		 dto.setReviseRemark("但是离开泛海建设啦看见哈佛I未来那块地方22");
		 String object=JsonUtils.beanToJson(dto);
		 String json=object.toString();
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * @Description: 修改测试
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午2:48:31
	 */
	@Test
	public void updateMSAccountRevisionDetailTest() throws Exception {
		 String url = "/member/account_service/v1/update_account_revision_detail";
		 AddOrUpdateAccountReviseDetail dto=new AddOrUpdateAccountReviseDetail();
		 dto.setMemId("72063681-7408-435c-88fd-cd837c95c66e");
		 dto.setMemLoginName("111");
		 dto.setMemPhone("15112347555");
		 dto.setReviseType("1");
		 dto.setStatus("AR");
		 dto.setIsDelete("N");
		 dto.setBeforeBalance(BigDecimal.TEN);
		 dto.setReviseBalance(BigDecimal.ONE);
		 dto.setCreateUser("huangTestUpdate1");
		 dto.setCreateDate(new Date());
		 dto.setUpdateDate(new Date());
		 dto.setUpdateUser("huangTestUpdate2");
		 dto.setReviewRemark("财务处理");
		 dto.setReviseRemark("通过");
		 String json=JsonUtils.beanToJson(dto);
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * @Description: 查看会员余额明细
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午3:02:44
	 */
	@Test
	public void getMSAccountRevisionDetailTest() throws Exception {
		 String url = "/member/account_service/v1/get_account_revision_detail";
		 String id="15cc8c8f-5e7c-4ade-b325-21b3a360115a";
		 String json=JsonUtils.beanToJson(id);
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * @Description: 查看会员余额明细集合
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午3:22:44
	 */
	@Test
	public void queryMSAccountRevisionDetailListTest() throws Exception {
		 String url = "/member/account_service/v1/query_account_revision_detail_list";
		 RequestAccountReviseDetail request=new RequestAccountReviseDetail();
		 request.setFlg("1");
		 String json=JsonUtils.beanToJson(request);
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * 审核同意
	 * @Description: 
	 * @Author: jianhua.huang
	 * @Date:   2017年4月21日 上午10:17:38
	 */
	@Test
	@Rollback
	public void agreeExamineMSAccountReviseDetailTest() throws Exception {
		 String url = "/member/account_service/v1/examine_account_revision_detail";

		 AddOrUpdateAccountReviseDetail detail=new AddOrUpdateAccountReviseDetail();
		 detail.setId("f320c1aa-bce2-4cd2-9c3a-1e605761d242");
		 detail.setReviseRemark("财务调整");
		 detail.setOperate("agree");//同意

		 String json=JsonUtils.beanToJson(detail);
		 
		 resultSystemOutPut(url,json);
	}
	


	/**
	 * @Description: 修改提现记录 测试

	 * @Author: jianhua.huang


	 */
	@Test

	public void updateWidthDrawDepositTest()throws Exception{
		 String url = "/member/account_service/v1/update_withdraw";
		 MSBankWithdrawDeposit deposit=new MSBankWithdrawDeposit();
		 deposit.setId("d52e04eb-815c-443c-832d-5b4380b833d8");
		 deposit.setStatus("3");
		 deposit.setActualTransferAmount(101.00);
		 deposit.setOperate("客服审核");
		 deposit.setRemark("查明无误");
		 deposit.setAuditBy("huang");
		 String object=JsonUtils.beanToJson(deposit);
		 String json=object.toString();
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * @Description: 测试 查看提现记录详情
	 * @Author: jianhua.huang
	 * @Date:   2017年4月25日 下午3:50:39
	 */
	@Test
	public void queryWidthDrawDeposiDetailTest()throws Exception{
		 String url = "/member/account_service/v1/query_withdraw_detail";
		 MSAccountDetailCondition condition=new MSAccountDetailCondition();
		 condition.setId("df99c423-ae02-4ffa-80b3-c64e8dfcd0f3");
		 String object=JsonUtils.beanToJson(condition);
		 String json=object.toString();
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * 客服提现驳回
	 * @throws Exception
	 * @author: jianhua.huang  2017年4月27日 下午12:05:58
	 */
	@Test
	public void rejectWithDrawTest()throws Exception{
		 String url = "/member/account_service/v1/reject_withdraw";
		 MSBankWithdrawDeposit deposit=new MSBankWithdrawDeposit();
		 deposit.setId("354b255e-7dde-42fc-8535-9438524a4536");
		 deposit.setOperate("customer");
		 deposit.setRemark("客服驳回操作");
		 String object=JsonUtils.beanToJson(deposit);
		 String json=object.toString();
		 
		 resultSystemOutPut(url,json);
	}
	/**
	 * 提现结算（财务点击结算）
	 * @throws Exception
	 * @author: jianhua.huang  2017年4月27日 下午12:06:58
	 */
	@Test
	public void settlementWithDrawTest()throws Exception{
		 String url = "/member/account_service/v1/settlement_withdraw";
		 MSBankWithdrawDeposit deposit=new MSBankWithdrawDeposit();
		 deposit.setId("7744988c-d71e-44d4-8ceb-00fc08ab9070");
		 deposit.setOperate("财务结算");
		 deposit.setRemark("财务已经打款完成");
		 String object=JsonUtils.beanToJson(deposit);
		 String json=object.toString();
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * 新增提现记录
	 * @throws Exception
	 * @author: jianhua.huang  2017年4月28日 下午6:18:19
	 */
	@Test
	public void saveBankWithDraw()throws Exception{
		 String url = "/member/account_service/v1/save_withdraw";
		 MSBankWithdrawDeposit deposit=new MSBankWithdrawDeposit();
		 
		 deposit.setBankCardNo("65555555555556666");
		 deposit.setMemId("ffc391ae-4e97-4830-8547-a2d537dc6d5d");
		 deposit.setApplyWithdrawAmount(10.5);
		 deposit.setAuditBy("huangTest");
		 deposit.setRemark("huangjianhuaTestDate");
		 
		 JSONObject object=JSONObject.fromObject(deposit);
		 String json=object.toString();

		 
		 resultSystemOutPut(url,json);
	}
	
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
			System.out.println("寮傚父*********************"+e);
		}
	}

	public static void main(String[] args) throws MdSysException {
		String s="100";
		System.out.println(DESC.encryption(s, "b9d78165-1483-42f7-a48c-fbfcc3b06431"));
	}

}


