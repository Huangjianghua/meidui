package com.meiduimall.service.account.api;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.core.Constants;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.model.MSAccountReport;
import com.meiduimall.service.account.model.request.RequestAccountAdjustAmount;
import com.meiduimall.service.account.service.AccountReportService;

/**
 * 账户调整相关API{@link=AccountAdjustV1Controller}单元测试
 * @author chencong
 *
 */
public class AccountAdjustV1ControllerTest extends BaseControllerTest {
	
	@Autowired
	private AccountReportService accountReportService;
	
	
	/**
	 * 账户调增调减
	 * @author chencong
	 * @throws Exception
	 */
	 @Test
	 public void testAccount_Adjust_Amount_01() throws Exception{
		 
		 //查询当前会员账户报表的总余额
		 MSAccountReport accountReport=accountReportService.getTotalAndFreezeBalanceByMemId(memId);
		 
		 //生成随机的订单号
		 String orderId=UUID.randomUUID().toString();
		 
		 //拼接请求的实体类
		 RequestAccountAdjustAmount model=new RequestAccountAdjustAmount();
		 model.setMemId(memId);
		 model.setSource("O2O");
		 model.setTradeType("GDYE");
		 model.setOrderId(orderId);
		 model.setTradeTime(String.valueOf(System.currentTimeMillis()));
		 model.setRemark("");
		 
		 //调增100
		 model.setDirection(ConstSysParamsDefination.CAPITAL_IN);
		 model.setTrade_amount(100.00);
		 mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/account_adjust_amount")
	    			.contentType(MediaType.APPLICATION_JSON_UTF8)
	    			.content(JsonUtils.beanToJson(model)))
	    			.andExpect(status().isOk())
	    			.andExpect(jsonPath("$.status",is(Constants.CONSTANT_INT_ZERO)));
		 
		//校验：调增后余额=调增前余额+100 
		MSAccountReport addAccountReport=accountReportService.getTotalAndFreezeBalanceByMemId(memId);
		assertTrue(accountReport.getBalance()+model.getTrade_amount()==addAccountReport.getBalance());	
		
		//重复提交的订单
		mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/account_adjust_amount")
	    			.contentType(MediaType.APPLICATION_JSON_UTF8)
	    			.content(JsonUtils.beanToJson(model)))
	    			.andExpect(status().isOk())
	    			.andExpect(jsonPath("$.status",is(ConstApiStatus.REPEAT_ORDER)));
		 
		 //调减100
		orderId=UUID.randomUUID().toString();
		model.setOrderId(orderId);
	    model.setDirection(ConstSysParamsDefination.CAPITAL_OUT);
		mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/account_adjust_amount")
	    			.contentType(MediaType.APPLICATION_JSON_UTF8)
	    			.content(JsonUtils.beanToJson(model)))
	    			.andExpect(status().isOk())
	    			.andExpect(jsonPath("$.status",is(Constants.CONSTANT_INT_ZERO)));
		//校验：调减后余额=调减前余额-100 
		MSAccountReport subAccountReport=accountReportService.getTotalAndFreezeBalanceByMemId(memId);
		assertTrue(addAccountReport.getBalance()-model.getTrade_amount()==subAccountReport.getBalance());
		
		
		//调减金额大于当前金额
		orderId=UUID.randomUUID().toString();
		model.setOrderId(orderId);
	    model.setDirection(ConstSysParamsDefination.CAPITAL_OUT);
	    model.setTrade_amount(subAccountReport.getBalance()+100000);
		mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/account_adjust_amount")
	    			.contentType(MediaType.APPLICATION_JSON_UTF8)
	    			.content(JsonUtils.beanToJson(model)))
	    			.andExpect(status().isOk())
	    			.andExpect(jsonPath("$.status",is(ConstApiStatus.BALANCE_CANNOT_AFFORD)));
	    
	    }
}
