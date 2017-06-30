package com.meiduimall.service.search.mapper;

import java.util.List;

import com.meiduimall.service.search.entity.Scanner;
import com.meiduimall.service.search.page.PageView;

public interface ScannerMapper {

	/**
	 * 查询索引扫描信息
	 * 
	 * @return
	 */
	public List<Scanner> queryScanners(PageView pageView);

	/**
	 * 查询总记录数
	 * 
	 * @return
	 */
	public long queryScannerCount();

	/**
	 * 根据ID查询索引扫描信息
	 * 
	 * @return
	 */
	public Scanner queryScannerById(Integer id);

	/**
	 * 删除已扫描记录
	 * 
	 * @param ids
	 *            已删除记录ID
	 * @return
	 */
	public int deleteScanned(List<Integer> ids);
}
