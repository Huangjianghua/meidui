package com.first.mapper;

import java.util.List;

import com.first.pojo.ItemModel;

public interface ItemMapper {
	
    /**
     * 查询产品信息
     * @return
     */
	public List<ItemModel> queryItems(ItemModel itemModel) throws Exception;
	
	/**
	 * 查询总记录数
	 * @return
	 */
	public long queryItemsCount(ItemModel itemModel) throws Exception;
	
	/**
	 * 根据ID查询产品信息
	 * @return
	 */
	public ItemModel queryItemById(Integer id) throws Exception;

	/**
	 * 查询所有产品ID
	 * @return
	 */
	public List<Integer> queryAllItemsId();

}
