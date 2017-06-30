package com.meiduimall.service.search.mapper;

import java.util.List;

import com.meiduimall.service.search.entity.ItemModel;

public interface ItemMapper {

	/**
	 * 查询产品信息
	 * 
	 * @return
	 */
	public List<ItemModel> queryItems(ItemModel itemModel);

	/**
	 * 查询总记录数
	 * 
	 * @return
	 */
	public long queryItemsCount(ItemModel itemModel);

	/**
	 * 根据ID查询产品信息
	 * 
	 * @return
	 */
	public ItemModel queryItemById(Integer id);

	/**
	 * 查询所有产品ID
	 * 
	 * @return
	 */
	public List<Integer> queryAllItemsId();

}
