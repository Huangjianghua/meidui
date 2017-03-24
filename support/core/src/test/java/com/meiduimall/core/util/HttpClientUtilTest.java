/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 

package com.meiduimall.core.util;

import static org.junit.Assert.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import io.netty.handler.codec.http.HttpUtil;

*//**
 * Created by hadoop on 2017/3/24.
 *//*
public class HttpClientUtilTest {
  private static Logger logger = LoggerFactory.getLogger(HttpClientUtilTest.class);


  private static HttpClientUtil utils = new HttpClientUtil();

  private static HttpUtil utils = new HttpUtil();

  @Rule
  public MockServerRule server = new MockServerRule(this, 5000);

  private String expected = "{ message: 'test ok' }";

  @Before
  public void setUp(){

    MockServerClient mockClient = new MockServerClient("localhost", 5000);
    mockClient.when(
        request()
            .withPath("/util/test")
            .withMethod("POST")
//                        .withHeader(new Header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN))
//                        .withQueryStringParameter(new Parameter("my-token", "12345"))
    ).respond(
        response()
            .withStatusCode(200)
            .withBody(expected)
    );

    mockClient.when(
        request()
            .withPath("/util/test")
            .withMethod("GET")
//                        .withHeader(new Header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN))
//                        .withQueryStringParameter(new Parameter("my-token", "12345"))
    ).respond(
        response()
            .withStatusCode(200)
            .withBody(expected)
    );
  }

  @After
  public void setDown(){

  }
  @Test
  public void getFewResultByPost1() throws Exception {

    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost("http://localhost:5000/util/test");
    Map<String, String> params = Maps.newHashMap();
    String result = utils.getFewResultByPost(httpclient, httpPost, params);

    logger.info(result);

    assertEquals(expected,result);
  }

  @Test
  public void getFewResultByPost2() throws Exception {

    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost("http://localhost:5000/util/test");
    String params = "";
    String result = utils.getFewResultByPost(httpclient, httpPost, params);
    logger.info(result);

    assertEquals(expected,result);
  }

  @Test
  public void doPost() throws Exception {
    Map<String, String> headers = Maps.newHashMap();
    String result = utils.doPost("http://localhost:5000/util/test", new StringEntity("", "UTF-8"), headers);
    logger.info(result);
    assertEquals(expected,result);
  }

  @Test
  public void doGet() throws Exception {

    Map<String, String> headers = Maps.newHashMap();
    String result = utils.doGet("http://localhost:5000/util/test", headers);
    logger.info(result);
    assertEquals(expected,result);
  }

}*/