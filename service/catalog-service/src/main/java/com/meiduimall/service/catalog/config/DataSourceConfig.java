package com.meiduimall.service.catalog.config;

import java.sql.SQLException;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * mysql数据源配置
 * @author chencong
 *
 */
@Configuration
public class DataSourceConfig {

	@Autowired
	private Environment env;
	
	private HikariDataSource pool;

	@Bean(destroyMethod = "close")
	public DataSource getHikariDataSource() throws SQLException {
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
		return pool=new HikariDataSource(config);
	}
	
	@PreDestroy
	public void close() {
		if (this.pool != null) {
			this.pool.close();
		}
	}
}