package com.meiduimall.service.search.service;

import java.util.List;

import com.meiduimall.service.search.entity.Scanner;
import com.meiduimall.service.search.page.PageView;

public interface ScannerService {

	/**
	 * 查询索引扫描信息
	 * 
	 * @return
	 */
	public List<Scanner> queryScanners(PageView pageView) throws Exception;

	/**
	 * 查询总记录数
	 * 
	 * @return
	 */
	public long queryScannerCount() throws Exception;

	/**
	 * 根据ID查询索引扫描信息
	 * 
	 * @return
	 */
	public Scanner queryScannerById(Integer id) throws Exception;

	/**
	 * 删除已扫描记录
	 * 
	 * @param ids
	 *            已删除记录ID
	 * @return
	 */
	public int deleteScanned(List<Integer> ids);
}
