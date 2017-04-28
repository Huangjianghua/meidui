package com.meiduimall.service.account.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.account.dao.BaseDao;

/**
 * 通用数据访问接口实现类
 * @author chencong
 *
 */
@Repository
public class BaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){  
		super.setSqlSessionFactory(sqlSessionFactory);  
	}
	
	public <T, P> T selectOne(P params, String sqlTag) throws DaoException {
		return getSqlSession().selectOne(sqlTag, params);
	}

	public <T, P> List<T> selectList(P params, String sqlTag) throws DaoException {
		return getSqlSession().selectList(sqlTag, params);
	}
	
	public <T> Integer insert(T t, String sqlTag) throws DaoException {
		return getSqlSession().insert(sqlTag, t);
	}

	public <T> Integer insertBatch(List<T> ts, String sqlTag) throws DaoException {
		return getSqlSession().insert(sqlTag + "." + sqlTag, ts);
	}

	public <P> Integer update(P params, String sqlTag) throws DaoException {
		return  getSqlSession().update(sqlTag, params);
	}

	public <P> Integer delete(P params, String sqlTag) throws DaoException {
		return getSqlSession().delete(sqlTag, params);
	}

	public <P> Integer deleteBatch(List<P> params, String sqlTag) throws DaoException {
		return getSqlSession().delete(sqlTag, params);
	}

}
