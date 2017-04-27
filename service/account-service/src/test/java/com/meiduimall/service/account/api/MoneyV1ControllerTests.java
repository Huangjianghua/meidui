package com.meiduimall.service.account.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.account.model.AccountReviseDetail;
import com.meiduimall.service.account.model.request.AccountReviseDetailRequest;
import com.meiduimall.service.account.util.DateUtil;


/**
 * @Copyright (C), 2002-2017, 美兑壹购物
 * @FileName: MoneyV1ControllerTests.java
 * @Author:   jianhua.huang 
 * @Date:     2017年4月18日 下午3:39:45
 * @Description: 测试新会员列表接口
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value="dev")
@EnableTransactionManagement
public class MoneyV1ControllerTests  {

	@Autowired
	private WebApplicationContext webApplicationContext; 
	
	private MockMvc mockMvc;
	
	private AccountReviseDetail dto;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * @Description: 不加条件查询
	 * @Author: jianhua.huang
	 * @Date:   2017年4月18日 下午3:46:31
	 */
	@Test
	public void queryAccountListTest() throws Exception {
		 String url = "/member/account_service/v1/list_account";
		 String json = "{\"memPhone\":\"13418786965\",\"flg\":\"1\"}";
		 
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
		 dto=new AccountReviseDetail();
		 dto.setMemId("72063681-7408-435c-88fd-cd837c95c66e");
		 dto.setMemLoginName("aaaa");
		 dto.setMemPhone("123456");
		 dto.setReviseType("1");
		 dto.setStatus("WR");
		 dto.setIsDelete("N");
		 dto.setCreatedBy("huang");
		 dto.setUpdatedBy("huang2");
		 dto.setReviewRemark("是肯定会罚款决定付款后会计师的疯狂的11");
		 dto.setReviseRemark("但是离开泛海建设啦看见哈佛I未来那块地方22");
		 String json=JsonUtils.beanToJson(dto);
		 
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
		 dto=new AccountReviseDetail();
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
		 
		 resultSystemOutPut(url,id);
	}
	
	/**
	 * @Description: 查看会员余额明细集合
	 * @Author: jianhua.huang
	 * @Date:   2017年4月20日 下午3:22:44
	 */
	@Test
	public void queryMSAccountRevisionDetailListTest() throws Exception {
		 String url = "/member/account_service/v1/query_account_revision_detail_list";
		 AccountReviseDetailRequest request=new AccountReviseDetailRequest();
		 request.setFlg("1");
		/* request.setMemId("72063681-7408-435c-88fd-cd837c95c66e");
		 request.setMemLoginName("wXyd8CZLYBIU1TE+FgtHrw==");
		 request.setStatus("WR");
		 request.setCreatedDateBegin("2017-04-17 00:00:00");
		 request.setCreatedDateEnd("2017-04-19 23:59:00");
		 
		 request.setUpdatedDateBegin("2017-04-17 00:00:00");
		 request.setUpdatedDateEnd("2017-04-21 23:59:00");*/
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
		 AccountReviseDetail detail=new AccountReviseDetail();
		 detail.setId("ef8d4259-96d9-420e-b828-f550a7d152c0");
		 detail.setReviseRemark("财务调整");
		 detail.setOperate("agree");//同意
		 String json=JsonUtils.beanToJson(detail);
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * 审核驳回
	 * @Description: 
	 * @Author: jianhua.huang
	 * @Date:   2017年4月21日 上午10:17:47
	 */
	@Test
	public void examineMSAccountReviseDetailTest() throws Exception {
		 String url = "/member/account_service/v1/examine_account_revision_detail";
		 AccountReviseDetail detail=new AccountReviseDetail();
		 detail.setId("f619656e-8600-4870-9665-9496d3a56a2d");
		 detail.setReviseRemark("财务调整");
		 detail.setOperate("reject");//驳回
		 String json=JsonUtils.beanToJson(detail);
		 
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
	
	
}
