package com.meiduimall.service.settlement.config;


import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.io.VFS;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.github.pagehelper.PageHelper;


/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: MyBatisConfig.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: MyBatis启动配置类(参考:http://blog.csdn.net/u012706811/article/details/52198677) 
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer{
	
	private static final Logger logger=LoggerFactory.getLogger("MyBatisConfig.class");
	
    @Resource(name = "dataSource")
    DataSource dataSource;

    /**
     * 可以通过这个类,详细配置mybatis
     * @return
     */
    @Bean
    public org.apache.ibatis.session.Configuration mybatisSetting(){
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(false);
        configuration.setLazyLoadingEnabled(false);
        configuration.setAggressiveLazyLoading(true);
        return configuration;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
    	
    	logger.info("--enter sqlSessionFactoryBean() in MyBatisConfig.class---");
    	
    	VFS.addImplClass(SpringBootVFS.class);

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.meiduimall.service.settlement.model");
        //bean.setTypeHandlersPackage("com.meiduimall.settlement.typehandler");   //un-comment it if needed.
        
/*      remove optimistic lock ...  
 *      OptimisticLocker optLocker=new OptimisticLocker();
        Properties optLockerprop=new Properties();
        optLockerprop.setProperty("versionField", "version");
        optLockerprop.setProperty("versionColumn", "version");
        optLocker.setProperties(optLockerprop);
        
        */

        //分页插件,插件无非是设置mybatis的拦截器
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});
       // bean.setPlugins(new Interceptor[]{pageHelper,optLocker});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //设置xml扫描路径
            bean.setMapperLocations(resolver.getResources("classpath:com/meiduimall/service/settlement/mapper/*Mapper.xml"));
            return bean.getObject();
        } catch (Exception e) {
        	logger.error(e.getMessage());
            throw new RuntimeException("sqlSessionFactory init fail",e);
        }
    }

    /**
     * 用于实际查询的sql工具,传统dao开发形式可以使用这个,基于mapper代理则不需要注入
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    /**
     * 事务管理,具体使用在service层加入@Transactional注解
     */
    @Bean(name = "transactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
