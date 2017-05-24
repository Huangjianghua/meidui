package com.meiduimall.application.mall.dao;

import java.util.List;

/**
 * 数据访问基础接口
 * @author guidl
 *
 */
public interface BaseMapper {
	/**
	 * @param params  参数
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return T
	 */
	public <T, P> T selectOne(P params, String sqlTag);

	/**
	 * @param params  普通查询参数, 分页查询参数(position=开始位置,offset=偏移量)
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return List
	 */
	public <T, P> List<T> selectList(P params, String sqlTag);

	/**
	 * @param t 插入对象
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return Integer
	 */
	public <T> Integer insert(T t, String sqlTag);

	/**
	 * @param ts 批量插入对象
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return  Integer
	 */
	public <T> Integer insertBatch(List<T> ts, String sqlTag);

	/**
	 * @param params 参数包含两种类型,更新参数与条件参数(键以p_名称开头)
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return Integer
	 */
	public <P> Integer update(P params, String sqlTag);

	/**
	 * @param params 参数
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return Integer
	 */
	public <P> Integer delete(P params, String sqlTag);

	/**
	 * @param params 参数
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return Integer
	 */
	public <P> Integer deleteBatch(List<P> params, String sqlTag);
}