package com.meiduimall.service.settlement.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.meiduimall.service.settlement.dao.BaseMapper;

@Repository
public class BaseMapperImpl extends SqlSessionDaoSupport implements BaseMapper {
	
    @Resource  
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){  
        super.setSqlSessionFactory(sqlSessionFactory);  
    }
    
    @Override
	public <T, P> T selectOne(P params, String sqlTag) {
    	
		return getSqlSession().selectOne(sqlTag, params);
		
	}

	@Override
	public <T, P> List<T> selectList(P params, String sqlTag) {
		
		return getSqlSession().selectList(sqlTag, params);
		
	}
	
	@Override
	public <T> Integer insert(T t, String sqlTag) {
		
		return getSqlSession().insert(sqlTag, t);
		
	}

	@Override
	public <T> Integer insertBatch(List<T> ts, String sqlTag) {
		
		return getSqlSession().insert(sqlTag + "." + sqlTag, ts);
		
	}

	@Override
	public <P> Integer update(P params, String sqlTag) {
		
		return getSqlSession().update(sqlTag, params);
		
	}

	@Override
	public <P> Integer delete(P params, String sqlTag) {
		
		return getSqlSession().delete(sqlTag, params);
		
	}

	@Override
	public <P> Integer deleteBatch(List<P> params, String sqlTag) {
		
		return getSqlSession().delete(sqlTag, params);
		
	}

}