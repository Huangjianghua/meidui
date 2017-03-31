/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.core.util;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by hadoop on 2017/3/23.
 */
public class ExceptionUtilsTest {

  @Test
  public void testGetFullStackTrace() throws Exception {
    String msg = ExceptionUtils.getFullStackTrace(new NullPointerException());
    Assert.assertNotNull(msg);
  }

}