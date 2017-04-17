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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

/**
 *  解决jredis分片无法选择数据库的问题
 * http://blog.csdn.net/zhoujintangjob/article/details/42121451
 */
public class JedisDBShardInfo extends JedisShardInfo { 
	private static final Logger log=LoggerFactory.getLogger(JedisDBShardInfo.class);
    private int database;  
      
    public JedisDBShardInfo(String host, int database) {
        super(host, 6379);  
        this.database=database;  
    }  
  
    public JedisDBShardInfo(String host, String name, int database) {
        super(host, 6379, name);  
        this.database=database;  
    }  
  
    public JedisDBShardInfo(String host, int port, int database) {
        super(host, port, 2000);  
        this.database=database;  
    }  
  
    public JedisDBShardInfo(String host, int port, String name, int database) {
        super(host, port, 2000, name);  
        this.database=database;  
    }  
  
    public JedisDBShardInfo(String host, int port, int timeout, int database) {
        super(host, port, timeout,timeout, 1);  
        this.database=database;  
    }  
  
    public JedisDBShardInfo(String host, int port, int timeout, String name, int database) {
        super(host, port, timeout, name);  
        this.database=database;  
    }  
  
    @Override
    public Jedis createResource() {  
        Jedis jedis=new Jedis(this);  
            try {  
            if (this.database != 0&&jedis.ping().equals("PONG")) {  
                jedis.select(this.database);  
                return jedis;  
            }  
        } catch (Exception e) {  
            log.error("连接异常=>"+getHost()+":"+getPort()+":"+getDatabase() + ";error msg:{}",e); 
        }  
        return null;  
    }  
  
    public int getDatabase() {  
        return database;  
    }  
  
    public void setDatabase(int database) {  
        this.database = database;  
    }  
 
}
