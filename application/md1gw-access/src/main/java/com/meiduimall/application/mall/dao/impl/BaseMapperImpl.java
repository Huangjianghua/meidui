package com.meiduimall.application.mall.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.meiduimall.exception.DaoException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.mall.dao.BaseMapper;
import com.meiduimall.application.mall.exception.MallApiCode;
import com.meiduimall.application.mall.util.Logger;

@Repository
public class BaseMapperImpl extends SqlSessionDaoSupport implements BaseMapper {
	
    @Resource  
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){  
        super.setSqlSessionFactory(sqlSessionFactory);  
    }
    
    
    @Override
	public <T, P> T selectOne(P params, String sqlTag){
		T rt = null;
		try {
			rt = getSqlSession().selectOne(sqlTag, params);
		} catch (RuntimeException e) {
			Logger.error(sqlTag + " error, params = " + params, e);
			throw new DaoException(MallApiCode.EXCEPTION_UNKNOWN,MallApiCode.getZhMsg(MallApiCode.EXCEPTION_UNKNOWN));
		} 
		return rt;
	}

	@Override
	public <T, P> List<T> selectList(P params, String sqlTag){
		List<T> rt = null;
		try {
			rt = getSqlSession().selectList(sqlTag, params);
		} catch (RuntimeException e) {
			Logger.error(sqlTag + " error, params = " + params, e);
			throw new DaoException(MallApiCode.EXCEPTION_UNKNOWN,MallApiCode.getZhMsg(MallApiCode.EXCEPTION_UNKNOWN));
		}
		return rt;
	}
	
	@Override
	public <T> Integer insert(T t, String sqlTag){
		Integer rt = null;
		try {
			rt = getSqlSession().insert(sqlTag, t);
		} catch (RuntimeException e) {
			Logger.error(sqlTag + " error, params = " + t, e);
			throw new DaoException(MallApiCode.EXCEPTION_UNKNOWN,MallApiCode.getZhMsg(MallApiCode.EXCEPTION_UNKNOWN));
		}
		return rt;
	}

	@Override
	public <T> Integer insertBatch(List<T> ts, String sqlTag){
		Integer rt = null;
		try {
			rt = getSqlSession().insert(sqlTag + "." + sqlTag, ts);
		} catch (RuntimeException e) {
			Logger.error(sqlTag + " error, params = " + ts, e);
			throw new DaoException(MallApiCode.EXCEPTION_UNKNOWN,MallApiCode.getZhMsg(MallApiCode.EXCEPTION_UNKNOWN));
		}
		return rt;
	}

	@Override
	public <P> Integer update(P params, String sqlTag){
		Integer rt = null;
		try {
			rt = getSqlSession().update(sqlTag, params);
		} catch (RuntimeException e) {
			Logger.error(sqlTag + " error, params = " + params, e);
			throw new DaoException(MallApiCode.EXCEPTION_UNKNOWN,MallApiCode.getZhMsg(MallApiCode.EXCEPTION_UNKNOWN));
		}
		return rt;
	}

	@Override
	public <P> Integer delete(P params, String sqlTag){
		Integer rt = null;

		try {
			rt = getSqlSession().delete(sqlTag, params);
		} catch (RuntimeException e) {
			Logger.error(sqlTag + " error, params = " + params, e);
			throw new DaoException(MallApiCode.EXCEPTION_UNKNOWN,MallApiCode.getZhMsg(MallApiCode.EXCEPTION_UNKNOWN));
		}
		return rt;
	}

	@Override
	public <P> Integer deleteBatch(List<P> params, String sqlTag)
			 {
		Integer rt = null;
		try {
			rt = getSqlSession().delete(sqlTag, params);
		} catch (RuntimeException e) {
			Logger.error(sqlTag + " error, params = " + params, e);
			throw new DaoException(MallApiCode.EXCEPTION_UNKNOWN,MallApiCode.getZhMsg(MallApiCode.EXCEPTION_UNKNOWN));
		}
		return rt;
	}

}