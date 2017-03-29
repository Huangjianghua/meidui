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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by hadoop on 2017/3/23.
 */
public class ExceptionUtilsTest {

  private static Logger logger = LoggerFactory.getLogger(ExceptionUtilsTest.class);

  @Test
  public void getFullStackTrace() throws Exception {
    String msg = ExceptionUtils.getFullStackTrace(new NullPointerException());
    logger.info(msg);
    assertEquals("[[\"com.meiduimall.core.util.ExceptionUtilsTest\",\"ExceptionUtilsTest.java\",\"28\",\"getFullStackTrace\"],[\"sun.reflect.NativeMethodAccessorImpl\",\"NativeMethodAccessorImpl.java\",\"-2\",\"invoke0\"],[\"sun.reflect.NativeMethodAccessorImpl\",\"NativeMethodAccessorImpl.java\",\"62\",\"invoke\"],[\"sun.reflect.DelegatingMethodAccessorImpl\",\"DelegatingMethodAccessorImpl.java\",\"43\",\"invoke\"],[\"java.lang.reflect.Method\",\"Method.java\",\"498\",\"invoke\"],[\"org.junit.runners.model.FrameworkMethod$1\",\"FrameworkMethod.java\",\"50\",\"runReflectiveCall\"],[\"org.junit.internal.runners.model.ReflectiveCallable\",\"ReflectiveCallable.java\",\"12\",\"run\"],[\"org.junit.runners.model.FrameworkMethod\",\"FrameworkMethod.java\",\"47\",\"invokeExplosively\"],[\"org.junit.internal.runners.statements.InvokeMethod\",\"InvokeMethod.java\",\"17\",\"evaluate\"],[\"org.junit.runners.ParentRunner\",\"ParentRunner.java\",\"325\",\"runLeaf\"],[\"org.junit.runners.BlockJUnit4ClassRunner\",\"BlockJUnit4ClassRunner.java\",\"78\",\"runChild\"],[\"org.junit.runners.BlockJUnit4ClassRunner\",\"BlockJUnit4ClassRunner.java\",\"57\",\"runChild\"],[\"org.junit.runners.ParentRunner$3\",\"ParentRunner.java\",\"290\",\"run\"],[\"org.junit.runners.ParentRunner$1\",\"ParentRunner.java\",\"71\",\"schedule\"],[\"org.junit.runners.ParentRunner\",\"ParentRunner.java\",\"288\",\"runChildren\"],[\"org.junit.runners.ParentRunner\",\"ParentRunner.java\",\"58\",\"access$000\"],[\"org.junit.runners.ParentRunner$2\",\"ParentRunner.java\",\"268\",\"evaluate\"],[\"org.junit.runners.ParentRunner\",\"ParentRunner.java\",\"363\",\"run\"],[\"org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference\",\"JUnit4TestReference.java\",\"86\",\"run\"],[\"org.eclipse.jdt.internal.junit.runner.TestExecution\",\"TestExecution.java\",\"38\",\"run\"],[\"org.eclipse.jdt.internal.junit.runner.RemoteTestRunner\",\"RemoteTestRunner.java\",\"459\",\"runTests\"],[\"org.eclipse.jdt.internal.junit.runner.RemoteTestRunner\",\"RemoteTestRunner.java\",\"675\",\"runTests\"],[\"org.eclipse.jdt.internal.junit.runner.RemoteTestRunner\",\"RemoteTestRunner.java\",\"382\",\"run\"],[\"org.eclipse.jdt.internal.junit.runner.RemoteTestRunner\",\"RemoteTestRunner.java\",\"192\",\"main\"]]", msg);
  }

}