package com.meiduimall.application.search.manage.services;

import java.util.List;

import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.pojo.ItemModel;

public interface ItemService {

	public QueryResult queryItems(ItemModel itemModel) throws Exception;
	
	public long queryItemsCount(ItemModel itemModel) throws Exception;
	
	public ItemModel queryItemById(Integer id) throws Exception;

	public List<Integer> queryAllItemsId();
}
