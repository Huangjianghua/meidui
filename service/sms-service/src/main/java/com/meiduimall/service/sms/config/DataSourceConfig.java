/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Title : DataSourceConfig 
 * Description : 数据源配置
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月16日 下午2:46:11 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
@Configuration
public class DataSourceConfig {

	private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	@Autowired
	private Environment env;

	@Bean
	public DataSource initDataSource() throws SQLException {
		String driverClass = env.getProperty("spring.datasource.driver-class-name");
		String url=env.getProperty("spring.datasource.url");
		String username =env.getProperty("spring.datasource.username");
		String passwd=env.getProperty("spring.datasource.password");

    //不能小于250
		long timeout=500;
		try{
			Long.valueOf(env.getProperty("spring.datasource.connectionTimeout"));
		}catch (NumberFormatException e){
			logger.warn("default spring.datasource.connectionTimeout {}", timeout);
		}

    int maxPoolSize=3;
    try{
      maxPoolSize = Integer.valueOf(env.getProperty("spring.datasource.maximumPoolSize"));
    }catch (NumberFormatException e){
      logger.warn("default spring.datasource.maximumPoolSize {}", maxPoolSize);
    }

    int minIdle=3;
    try{
      minIdle = Integer.valueOf(env.getProperty("spring.datasource.minimumIdle"));
    }catch (NumberFormatException e){
      logger.warn("default spring.datasource.minimumIdle {}", minIdle);
    }

    long maxLifetime=3;
    try{
      maxLifetime = Long.valueOf(env.getProperty("spring.datasource.maxLifetime"));
    }catch (NumberFormatException e){
      logger.warn("default spring.datasource.maxLifetime {}", maxLifetime);
    }

    long idleTimeout=3;
    try{
      idleTimeout = Long.valueOf(env.getProperty("spring.datasource.idleTimeout"));
    }catch (NumberFormatException e){
      logger.warn("default spring.datasource.idleTimeout {}", idleTimeout);
    }

    boolean autoCommit=false;
//    try{
      autoCommit = Boolean.valueOf(env.getProperty("spring.datasource.autoCommit"));
//    }catch (NumberFormatException e){
//      logger.warn("default spring.datasource.autoCommit {}", autoCommit);
//    }

    String connectionTestQuery="select 1";
//    try{
      connectionTestQuery = env.getProperty("spring.datasource.connectionTestQuery");
//    }catch (NumberFormatException e){
//      logger.warn("default spring.datasource.connectionTestQuery {}", connectionTestQuery);
//    }




		HikariConfig config = new HikariConfig();
		/*
		 * 数据库基本配置
		 */
		config.setDriverClassName(driverClass);
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(passwd);
		/*
		 * 连接池配置
		 */
		config.setConnectionTimeout(timeout);
		config.setMaximumPoolSize(maxPoolSize);
		config.setMinimumIdle(minIdle);
		config.setMaxLifetime(maxLifetime);
		config.setIdleTimeout(idleTimeout);
		config.setAutoCommit(autoCommit);
		config.setConnectionTestQuery(connectionTestQuery);
		return new HikariDataSource(config);
	}
}