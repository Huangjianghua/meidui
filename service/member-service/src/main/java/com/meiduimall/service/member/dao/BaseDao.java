package com.meiduimall.service.member.dao;

import java.util.List;

/**
 * 通用数据访问接口
 * @author chencong
 *
 */
public interface BaseDao {
	/**
	 * @param params
	 *           
	 * @param sqlTag
	 *           
	 * @return 根据primaryKey查询T
	 */
	public <T, P> T selectOne(P params, String sqlTag);

	/**
	 * @param params
	 *            
	 * @param sqlTag
	 *           
	 * @return 根据params查询T
	 */
	public <T, P> List<T> selectList(P params, String sqlTag);

	/**
	 * @param t
	 *            插入对象
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 操作结果
	 */
	public <T> Integer insert(T t, String sqlTag);

	/**
	 * @param ts
	 *            批量插入对象
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 操作结果
	 */
	public <T> Integer insertBatch(List<T> ts, String sqlTag);

	/**
	 * @param params
	 *            参数包含两种类型,更新参数与条件参数(键以p_名称开头)
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 操作结果
	 */
	public <P> Integer update(P params, String sqlTag);

	/**
	 * @param params
	 *            参数
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 */
	public <P> Integer delete(P params, String sqlTag) ;

	/**
	 * @param params
	 *            参数
	 * @param sqlTag
	 *            mapper.xml文件中的tag的ID
	 * @return 删除结果
	 */
	public <P> Integer deleteBatch(List<P> params, String sqlTag) ;
}
