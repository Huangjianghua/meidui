package com.meiduimall.service.catalog.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meiduimall.exception.DaoException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.util.SqlHelper;

/**
 * 通用数据访问接口实现类
 * 
 * @author chencong
 *
 */
@Repository
public class BaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public <T, P> T selectOne(P params, String sqlTag) {
		T rt = null;
		try {
			rt = getSqlSession().selectOne(sqlTag, params);
		} catch (Exception e) {
			throw new DaoException(e, ServiceCatalogApiCode.DB_EXCEPTION,
					SqlHelper.getMapperSql(getSqlSession(), sqlTag, params));
		}
		return rt;
	}

	public <T, P> List<T> selectList(P params, String sqlTag) {
		List<T> rt = null;
		try {
			rt = getSqlSession().selectList(sqlTag, params);
		} catch (Exception e) {
			throw new DaoException(e, ServiceCatalogApiCode.DB_EXCEPTION,
					SqlHelper.getMapperSql(getSqlSession(), sqlTag, params));
		}
		return rt;
	}

	public <T> Integer insert(T t, String sqlTag) {
		Integer rt = null;
		try {
			rt = getSqlSession().insert(sqlTag, t);
		} catch (Exception e) {
			throw new DaoException(e, ServiceCatalogApiCode.DB_EXCEPTION,
					SqlHelper.getMapperSql(getSqlSession(), sqlTag, t));
		}
		return rt;
	}

	public <T> Integer insertBatch(List<T> ts, String sqlTag) {
		Integer rt = null;
		try {
			rt = getSqlSession().insert(sqlTag, ts);
		} catch (Exception e) {
			throw new DaoException(e, ServiceCatalogApiCode.DB_EXCEPTION,
					SqlHelper.getMapperSql(getSqlSession(), sqlTag, ts));
		}
		return rt;
	}

	public <P> Integer update(P params, String sqlTag) {
		Integer rt = null;
		try {
			rt = getSqlSession().update(sqlTag, params);
		} catch (Exception e) {
			throw new DaoException(e, ServiceCatalogApiCode.DB_EXCEPTION,
					SqlHelper.getMapperSql(getSqlSession(), sqlTag, params));
		}
		return rt;
	}

	public <P> Integer delete(P params, String sqlTag) {
		Integer rt = null;

		try {
			rt = getSqlSession().delete(sqlTag, params);
		} catch (Exception e) {
			throw new DaoException(e, ServiceCatalogApiCode.DB_EXCEPTION,
					SqlHelper.getMapperSql(getSqlSession(), sqlTag, params));
		}
		return rt;
	}

	public <P> Integer deleteBatch(List<P> params, String sqlTag) {
		Integer rt = null;
		try {
			rt = getSqlSession().delete(sqlTag, params);
		} catch (Exception e) {
			throw new DaoException(e, ServiceCatalogApiCode.DB_EXCEPTION,
					SqlHelper.getMapperSql(getSqlSession(), sqlTag, params));
		}
		return rt;
	}

}
