package com.meiduimall.service.account.dao;

import java.util.List;

import com.meiduimall.exception.DaoException;


/**
 * 通用数据访问接口
 * @author chencong
 *
 */
public interface BaseDao {
	/**
	 * @param params
	 *            参数
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 根据primaryKey查询T
	 * @throws DaoException
	 */
	public <T, P> T selectOne(P params, String sqlTag) throws DaoException;

	/**
	 * @param params
	 *            普通查询参数, 分页查询参数(position=开始位置,offset=偏移量)
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 根据params查询T,List.size =0
	 * @throws DaoException
	 */
	public <T, P> List<T> selectList(P params, String sqlTag) throws DaoException;

	/**
	 * @param t
	 *            插入对象
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 操作结果
	 * @throws DaoException
	 */
	public <T> Integer insert(T t, String sqlTag) throws DaoException;

	/**
	 * @param ts
	 *            批量插入对象
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 操作结果
	 * @throws DaoException
	 */
	public <T> Integer insertBatch(List<T> ts, String sqlTag) throws DaoException;

	/**
	 * @param params
	 *            参数包含两种类型,更新参数与条件参数(键以p_名称开头)
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 操作结果
	 * @throws DaoException
	 */
	public <P> Integer update(P params, String sqlTag) throws DaoException;

	/**
	 * @param params
	 *            参数
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 删除结果
	 * @throws DaoException
	 */
	public <P> Integer delete(P params, String sqlTag) throws DaoException;

	/**
	 * @param params
	 *            参数
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 删除结果
	 * @throws DaoException
	 */
	public <P> Integer deleteBatch(List<P> params, String sqlTag) throws DaoException;
}
