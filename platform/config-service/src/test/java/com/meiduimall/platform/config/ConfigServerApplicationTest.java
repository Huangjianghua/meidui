package com.meiduimall.platform.config;
/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.platform.config.api.ConfigManageController;
import com.meiduimall.platform.config.model.ConfigerMsg;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ConfigServerApplicationTest {
	private final static Logger logger=LoggerFactory.getLogger(ConfigServerApplicationTest.class);
  @Test
  public void contextLoads() {
  }
  
  @Autowired
  ConfigManageController controller;
  
  /**
   * 查询配置信息
   * 
   * @author: jianhua.huang  2017年5月25日 下午5:54:50
   */
  @Test
  public void queryConfigList(){
	  Map<String, String> param =new HashMap<>();
	  param.put("type", "o2o");
	  try {
		  ResBodyData data=  controller.queryConfigMangeList(param); 
		  logger.info("单元测试>>查询配置管理API>>执行结果:{}",data.getData());
	} catch (Exception e) {
		logger.error("单元测试>>查询配置管理API>>执行异常");
	}
  }
  
  /**
   * 新增配置信息
   * 
   * @author: jianhua.huang  2017年5月25日 下午5:55:00
   */
  @Test
  public void addConfigManage(){
	  ConfigerMsg conf=new ConfigerMsg();
	  conf.setKey("huang1111add");
	  conf.setName("黄测试add");
	  conf.setType("com");
	  conf.setUpdateBy("huangadd");
	  conf.setUpdateDate("2017-05-27 17:25:25");
	  conf.setValue("huangValueadd");
	  conf.setStatus("Y");
	  try {
		  ResBodyData data=  controller.addConfigManage(conf); 
		  logger.info("单元测试>>新增配置管理API>>执行结果:{}",data.getData());
	} catch (Exception e) {
		logger.error("单元测试>>>新增配置管理API>>执行异常");
	}
  }
  
  /**
   * 修改配置信息
   * 
   * @author: jianhua.huang  2017年5月25日 下午5:55:00
   */
  @Test
  public void updateConfigManage(){
	  
	  ConfigerMsg conf=new ConfigerMsg();
	  conf.setKey("huang1111add");
	  conf.setName("黄测试update");
	  conf.setType("o2o");
	  conf.setUpdateBy("huangupdate");
	  conf.setUpdateDate("2017-05-27 17:22:22");
	  conf.setValue("huangValueupdate");
	  conf.setStatus("Y");
	  try {
		  ResBodyData data=  controller.updateConfigManage(conf); 
		  logger.info("单元测试>>查询配置管理API>>执行结果:{}",data.getData());
	} catch (Exception e) {
		logger.error("单元测试>>查询配置管理API>>执行异常");
	}
  }
}
