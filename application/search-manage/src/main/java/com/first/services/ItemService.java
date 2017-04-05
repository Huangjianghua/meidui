package com.first.services;

import java.util.List;

import com.first.page.QueryResult;
import com.first.pojo.ItemModel;

public interface ItemService {

	public QueryResult queryItems(ItemModel itemModel) throws Exception;
	
	public long queryItemsCount(ItemModel itemModel) throws Exception;
	
	public ItemModel queryItemById(Integer id) throws Exception;

	public List<Integer> queryAllItemsId();
}
