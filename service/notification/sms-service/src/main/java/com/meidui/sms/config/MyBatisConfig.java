package com.meidui.sms.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.github.pagehelper.PageHelper;

/**
 * Title : MyBatisConfig 
 * Description : mybatis配置
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月16日 下午2:46:31 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer{
	
    @Autowired
    private DataSource dataSource;
    
    @Autowired
	private Environment env;
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.meidui.shortmsg.mapper");
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
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //设置xml扫描路径
            bean.setMapperLocations(resolver.getResources(env.getProperty("mybatis.mapper-locations")));
            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException("sqlSessionFactory init fail",e);
        }
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean(name = "transactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
