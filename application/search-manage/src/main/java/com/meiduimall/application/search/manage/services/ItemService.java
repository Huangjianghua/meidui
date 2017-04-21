package com.meiduimall.application.search.manage.services;

import java.util.List;

import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.pojo.ItemModel;

public interface ItemService {

	public QueryResult queryItems(ItemModel itemModel) ;
	
	public long queryItemsCount(ItemModel itemModel) ;
	
	public ItemModel queryItemById(Integer id) ;

	public List<Integer> queryAllItemsId();
}
