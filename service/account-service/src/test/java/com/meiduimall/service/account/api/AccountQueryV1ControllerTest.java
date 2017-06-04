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

import com.meiduimall.core.Constants;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.AddOrUpdateAccountReviseDetail;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.request.RequestAccountReviseDetail;
import com.meiduimall.service.account.model.request.RequestMSBankWithDrawDepostie;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DateUtil;

import net.sf.json.JSONObject;

/**
 * 账户信息查询API{@link=AccountQueryV1Controller}单元测试
 * @author chencong
 *
 */
public class AccountQueryV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(AccountQueryV1ControllerTest.class);
	
	/**
	 * 查询当前会员可用余额
	 * @author chencong
	 * @throws Exception
	 */
    @Test
    public void getAvailableBalance() throws Exception{
    	//当前会员账户存在
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/v1/get_available_balance?memId="+memId))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(Constants.CONSTANT_INT_ZERO)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>查询当前会员可用余额API>>账户存在>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    	//账户不存在
    	postResultAction=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/v1/get_available_balance?memId=123456789"))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(ConstApiStatus.ACCOUNT_NOT_EXIST)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>查询当前会员可用余额API>>账户不存在>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});
    }
	
	/**余额流水*/
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
				logger.info("单元测试>>余额流水分页API>>执行结果:{}",result.getResponse().getContentAsString());

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
		 dto.setCreatedBy("huang");
		 dto.setUpdatedBy("huang2");
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
		 dto.setMemLoginName("很test1");
		 dto.setMemPhone("15112347555");
		 dto.setReviseType("1");
		 dto.setStatus("AR");
		 dto.setIsDelete("N");
		 dto.setBeforeBalance(BigDecimal.TEN);
		 dto.setReviseBalance(BigDecimal.ONE);
		 dto.setCreatedBy("huangTestUpdate1");
		 dto.setCreatedDate(DateUtil.formatDateTime(new Date()));
		 dto.setUpdatedDate(DateUtil.formatDateTime(new Date()));
		 dto.setUpdatedBy("huangTestUpdate2");
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
		 detail.setId("1");
		 detail.setReviseRemark("财务调整");
		 detail.setOperate("agree");//同意
		 String json=JsonUtils.beanToJson(detail);
		 
		 resultSystemOutPut(url,json);
	}
	

	/**
	 * @Description: 修改提现记录 测试
	 * @Author: jianhua.huang
	 * @Date:   2017年4月25日 下午3:01:55
	 */
	@Test
	public void updateWidthDrawDepositTest()throws Exception{
		 String url = "/member/account_service/v1/update_withdraw";
		 MSBankWithdrawDeposit deposit=new MSBankWithdrawDeposit();
		 deposit.setId("d52e04eb-815c-443c-832d-5b4380b833d8");
		 deposit.setStatus("3");
		/* deposit.setActualTransferCash("101");
		 deposit.setOperate("客服审核");*/
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
		 deposit.setId("e422b739-7e62-4a2e-8933-860828a39347");
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
		 deposit.setId("0bbe582b-6454-471f-839e-021f15df15cb");
		 /*deposit.setOperate("财务结算");*/
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
		 RequestMSBankWithDrawDepostie deposit=new RequestMSBankWithDrawDepostie();
		 
		 deposit.setAccountNo("123456");
		 deposit.setMemId("a0db1419-f44a-48e8-9394-a49620e47940");
		 deposit.setApplyCarryCash("100.05");
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
			System.out.println("异常*********************"+e);
		}
	}
	public static void main(String[] args) throws MdSysException {
		String s="100";
		System.out.println(DESC.encryption(s, "b9d78165-1483-42f7-a48c-fbfcc3b06431"));
	}

	/**
     * 查询个人消费管理信息接口
     * @throws Exception
     */
    @Test
	public void personalConsumptionPoints_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/account_service/v1/personalConsumptionPoints")
				.param("memId", "a0db1419-f44a-48e8-9394-a49620e47940"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("personalConsumptionPoints_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
    
    /**
     * 查询个人消费管理信息接口---memId不存在
     * @throws Exception
     */
    @Test
	public void personalConsumptionPoints_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/account_service/v1/personalConsumptionPoints")
				.param("memId", "a0db1419"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("personalConsumptionPoints_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
    
    /**
     * 根据会员memId，获取会员账户余额和积分余额---按新接口规范
     * @throws Exception
     */
    @Test
	public void getAccountBalanceForApp_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/account_service/v1/getAccountBalanceForApp")
				.param("memId", "a0db1419-f44a-48e8-9394-a49620e47940"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getAccountBalanceForApp_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
    
    /**
     * 根据会员memId，获取会员账户余额和积分余额---兼容旧版
     * @throws Exception
     */
    @Test
	public void getAccountBalanceForApp_old_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/account_service/v1/getAccountBalanceForApp_old")
				.param("memId", "a0db1419-f44a-48e8-9394-a49620e47940"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getAccountBalanceForApp_old_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
