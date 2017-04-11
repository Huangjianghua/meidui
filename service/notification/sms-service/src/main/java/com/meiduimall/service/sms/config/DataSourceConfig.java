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

	@Autowired
	private Environment env;

	@Bean
	public DataSource getDruidDataSource() throws SQLException {
		HikariConfig config = new HikariConfig();
		/*
		 * 数据库基本配置
		 */
		config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		config.setJdbcUrl(env.getProperty("spring.datasource.url"));
		config.setUsername(env.getProperty("spring.datasource.username"));
		config.setPassword(env.getProperty("spring.datasource.password"));
		/*
		 * 连接池配置
		 */
		config.setConnectionTimeout(Long.valueOf(env.getProperty("spring.datasource.connectionTimeout")));
		config.setMaximumPoolSize(Integer.valueOf(env.getProperty("spring.datasource.maximumPoolSize")));
		config.setMinimumIdle(Integer.valueOf(env.getProperty("spring.datasource.minimumIdle")));
		config.setMaxLifetime(Long.valueOf(env.getProperty("spring.datasource.maxLifetime")));
		config.setIdleTimeout(Long.valueOf(env.getProperty("spring.datasource.idleTimeout")));
		config.setAutoCommit(Boolean.valueOf(env.getProperty("spring.datasource.autoCommit")));
		config.setConnectionTestQuery(env.getProperty("spring.datasource.connectionTestQuery"));
		return new HikariDataSource(config);
	}
}