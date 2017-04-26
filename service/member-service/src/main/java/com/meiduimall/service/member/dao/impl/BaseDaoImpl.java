package com.meiduimall.service.member.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.dao.BaseDao;


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
	
	public <T, P> T selectOne(P params, String sqlTag)  {
		T result=null;
		try {
			result=getSqlSession().selectOne(sqlTag, params);
		} catch (Exception e) {
			throw new DaoException(ApiStatusConst.DB_SELECT_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.DB_SELECT_EXCEPTION));
		}
		return result;
	}

	public <T, P> List<T> selectList(P params, String sqlTag) {
		List<T> result=null;
		try {
			result=getSqlSession().selectList(sqlTag, params);
		} catch (Exception e) {
			throw new DaoException(ApiStatusConst.DB_SELECT_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.DB_SELECT_EXCEPTION));
		}
		return result;
	}
	
	public <T> Integer insert(T t, String sqlTag) {
		int result=0;
		try {
			result=getSqlSession().insert(sqlTag, t);	
		} catch (Exception e) {
			throw new DaoException(ApiStatusConst.DB_SELECT_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.DB_SELECT_EXCEPTION));
		} 
		return result;
	}

	public <T> Integer insertBatch(List<T> ts, String sqlTag)  {
		int result=0;
		try {
			result=getSqlSession().insert(sqlTag + "." + sqlTag, ts);
		} catch (Exception e) {
			throw new DaoException(ApiStatusConst.DB_SELECT_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.DB_SELECT_EXCEPTION));
		} 
		return result;
	}

	public <P> Integer update(P params, String sqlTag){
		int result=0;
		try {
			result=getSqlSession().update(sqlTag, params);
		} catch (Exception e) {
			throw new DaoException(ApiStatusConst.DB_SELECT_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.DB_SELECT_EXCEPTION));
		} 
		return result;  
	}

	public <P> Integer delete(P params, String sqlTag) {
		int result=0;
		try {
			result=getSqlSession().delete(sqlTag, params);
		} catch (Exception e) {
			throw new DaoException(ApiStatusConst.DB_SELECT_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.DB_SELECT_EXCEPTION));
		} 
		return result;   
	}

	public <P> Integer deleteBatch(List<P> params, String sqlTag) {
		int result=0;
		try {
			result=getSqlSession().delete(sqlTag, params);
		} catch (Exception e) {
			throw new DaoException(ApiStatusConst.DB_SELECT_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.DB_SELECT_EXCEPTION));
		} 
		return result; 
	}

}
