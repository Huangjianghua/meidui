/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.route;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by hadoop on 2017/3/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RouteServiceApplicationTest.class)
@WebAppConfiguration
public class RouteServiceApplicationTest {

  private TestRestTemplate template = new TestRestTemplate();
  @Value("${server.port}")// 注入端口号
  private int port=-1;

  @Test
  public void test1(){
    String url = "http://localhost:"+port+"/routes";
    String result = template.getForObject(url,  String.class);
    System.out.println(result);
    assertNotNull(result);
    assertThat(result, Matchers.containsString("{\"/mall/mall_user_service/v1/**\":\"mall-user-service\",\"/mall/mall_commodity_service/v1/**\":\"mall-commodity-service\",\"/mall/mall_shoppingcart_service/v1/**\":\"mall-shoppingcart-service\",\"/mall/mall_order_service/v1/**\":\"mall-order-service\",\"/bus/refresh/**\":\"service-config\",\"/member/member_service/v1/**\":\"member-service\",\"/member/account_service/v1/**\":\"account-service\",\"/notify/short_msg_service/**\":\"short_msg_service\",\"/push/push-service/v1/**\":\"service-push\"}"));
  }
}
