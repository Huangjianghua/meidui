package com.meiduimall.service.catalog.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meiduimall.service.catalog.dao.BaseDao;

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
	
	public <T, P> T selectOne(P params, String sqlTag) throws Exception {
		T rt = null;
		try {
			rt = getSqlSession().selectOne(sqlTag, params);
		} catch (Exception e) {
			throw new Exception(sqlTag + " error, params = " + params, e);
		}
		return rt;
	}

	public <T, P> List<T> selectList(P params, String sqlTag) throws Exception {
		List<T> rt = null;
		try {
			rt = getSqlSession().selectList(sqlTag, params);
		} catch (Exception e) {
			throw new Exception(sqlTag + " error, params = " + params, e);
		}
		return rt;
	}
	
	public <T> Integer insert(T t, String sqlTag) throws Exception {
		Integer rt = null;
		try {
			rt = getSqlSession().insert(sqlTag, t);
		} catch (Exception e) {
			throw new Exception(sqlTag + " error, t = " + t, e);
		}
		return rt;
	}

	public <T> Integer insertBatch(List<T> ts, String sqlTag) throws Exception {
		Integer rt = null;
		try {
			rt = getSqlSession().insert(sqlTag, ts);
		} catch (Exception e) {
			throw new Exception(sqlTag + " error, ts = " + ts, e);
		}
		return rt;
	}

	public <P> Integer update(P params, String sqlTag) throws Exception {
		Integer rt = null;
		try {
			rt = getSqlSession().update(sqlTag, params);
		} catch (Exception e) {
			throw new Exception(sqlTag + " error, params = " + params, e);
		}
		return rt;
	}

	public <P> Integer delete(P params, String sqlTag) throws Exception {
		Integer rt = null;

		try {
			rt = getSqlSession().delete(sqlTag, params);
		} catch (Exception e) {
			throw new Exception(sqlTag + " error, params = " + params, e);
		}
		return rt;
	}

	public <P> Integer deleteBatch(List<P> params, String sqlTag)
			throws Exception {
		Integer rt = null;
		try {
			rt = getSqlSession().delete(sqlTag, params);
		} catch (Exception e) {
			throw new Exception(sqlTag + " error, params = " + params, e);
		}
		return rt;
	}

}
