package com.meiduimall.payment.api.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.meiduimall.payment.api.dao.DaoTemplate;



@Component
public class DaoTemplateImpl implements DaoTemplate {
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insert(String id) throws Exception {
		try {
			return sqlSessionTemplate.insert(id);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public <T> int insert(String id, T clasz) throws Exception {
		try {
			return sqlSessionTemplate.insert(id, clasz);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public  <T> List<T> findAll(String id) throws Exception {
		try {
			return sqlSessionTemplate.selectList(id);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public <T, P> List<T> findAll(String id, P clasz) throws Exception {
		try {
			return sqlSessionTemplate.selectList(id, clasz);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public <T> T find(String id) throws Exception {
		try {
			return sqlSessionTemplate.selectOne(id);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public <T, P> T find(String id, P clasz) throws Exception {
		try {
			return sqlSessionTemplate.selectOne(id, clasz);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public int update(String id) throws Exception {
		try {
			return sqlSessionTemplate.update(id);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public <T> int update(String id, T clasz) throws Exception {
		try {
			return sqlSessionTemplate.update(id, clasz);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public int delete(String id) throws Exception {
		try {
			return sqlSessionTemplate.delete(id);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public <T> int delete(String id, T clasz) throws Exception {
		try {
			return sqlSessionTemplate.delete(id, clasz);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	
}
