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
import com.meiduimall.service.account.model.AccountReviseDetail;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.request.RequestAccountReviseDetail;
import com.meiduimall.service.account.util.DateUtil;



public class MoneyV1ControllerTests extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(MoneyV1ControllerTests.class);
	
	private AccountReviseDetail dto;

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
	 * @Description: 涓嶅姞鏉′欢鏌ヨ
	 * @Author: jianhua.huang
	 * @Date:   2017骞�4鏈�18鏃� 涓嬪崍3:46:31
	 */
	@Test
	public void queryAccountListTest() throws Exception {
		 String url = "/member/account_service/v1/list_account";
		 String json = "{\"flg\":\"1\"}";
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * @Description: 娣诲姞娴嬭瘯
	 * @Author: jianhua.huang
	 * @Date:   2017骞�4鏈�20鏃� 涓嬪崍2:18:31
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
		 dto.setReviewRemark("鏄偗瀹氫細缃氭鍐冲畾浠樻鍚庝細璁″笀鐨勭柉鐙傜殑11");
		 dto.setReviseRemark("浣嗘槸绂诲紑娉涙捣寤鸿鍟︾湅瑙佸搱浣汭鏈潵閭ｅ潡鍦版柟22");
		 String json=JsonUtils.beanToJson(dto);
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * @Description: 淇敼娴嬭瘯
	 * @Author: jianhua.huang
	 * @Date:   2017骞�4鏈�20鏃� 涓嬪崍2:48:31
	 */
	@Test
	public void updateMSAccountRevisionDetailTest() throws Exception {
		 String url = "/member/account_service/v1/update_account_revision_detail";
		 dto=new AccountReviseDetail();
		 dto.setMemId("72063681-7408-435c-88fd-cd837c95c66e");
		 dto.setMemLoginName("寰坱est1");
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
		 dto.setReviewRemark("璐㈠姟澶勭悊");
		 dto.setReviseRemark("閫氳繃");
		 String json=JsonUtils.beanToJson(dto);
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * @Description: 鏌ョ湅浼氬憳浣欓鏄庣粏
	 * @Author: jianhua.huang
	 * @Date:   2017骞�4鏈�20鏃� 涓嬪崍3:02:44
	 */
	@Test
	public void getMSAccountRevisionDetailTest() throws Exception {
		 String url = "/member/account_service/v1/get_account_revision_detail";
		 String id="15cc8c8f-5e7c-4ade-b325-21b3a360115a";
		 
		 resultSystemOutPut(url,id);
	}
	
	/**
	 * @Description: 鏌ョ湅浼氬憳浣欓鏄庣粏闆嗗悎
	 * @Author: jianhua.huang
	 * @Date:   2017骞�4鏈�20鏃� 涓嬪崍3:22:44
	 */
	@Test
	public void queryMSAccountRevisionDetailListTest() throws Exception {
		 String url = "/member/account_service/v1/query_account_revision_detail_list";
		 RequestAccountReviseDetail request=new RequestAccountReviseDetail();
		 request.setFlg("1");
		 request.setMemId("72063681-7408-435c-88fd-cd837c95c66e");
		 request.setMemLoginName("wXyd8CZLYBIU1TE+FgtHrw==");
		 request.setStatus("WR");
		 request.setCreatedDateBegin("2017-04-17 00:00:00");
		 request.setCreatedDateEnd("2017-04-19 23:59:00");
		 
		 request.setUpdatedDateBegin("2017-04-17 00:00:00");
		 request.setUpdatedDateEnd("2017-04-21 23:59:00");
		 String json=JsonUtils.beanToJson(request);
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * 瀹℃牳鍚屾剰
	 * @Description: 
	 * @Author: jianhua.huang
	 * @Date:   2017骞�4鏈�21鏃� 涓婂崍10:17:38
	 */
	@Test
	@Rollback
	public void agreeExamineMSAccountReviseDetailTest() throws Exception {
		 String url = "/member/account_service/v1/examine_account_revision_detail";
		 AccountReviseDetail detail=new AccountReviseDetail();
		 detail.setId("ef8d4259-96d9-420e-b828-f550a7d152c0");
		 detail.setReviseRemark("璐㈠姟璋冩暣");
		 detail.setOperate("agree");//鍚屾剰
		 String json=JsonUtils.beanToJson(detail);
		 
		 resultSystemOutPut(url,json);
	}
	
	/**
	 * 瀹℃牳椹冲洖
	 * @Description: 
	 * @Author: jianhua.huang
	 * @Date:   2017骞�4鏈�21鏃� 涓婂崍10:17:47
	 */
	@Test
	public void examineMSAccountReviseDetailTest() throws Exception {
		 String url = "/member/account_service/v1/examine_account_revision_detail";
		 AccountReviseDetail detail=new AccountReviseDetail();
		 detail.setId("f619656e-8600-4870-9665-9496d3a56a2d");
		 detail.setReviseRemark("璐㈠姟璋冩暣");
		 detail.setOperate("reject");//椹冲洖
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
			System.out.println("寮傚父*********************"+e);
		}
	}
	

}

