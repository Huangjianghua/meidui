package com.meiduimall.service.search.service;

import java.util.List;

import com.meiduimall.service.search.entity.ItemModel;
import com.meiduimall.service.search.page.QueryResult;

public interface ItemService {

	public QueryResult queryItems(ItemModel itemModel) throws Exception;

	public long queryItemsCount(ItemModel itemModel) throws Exception;

	public ItemModel queryItemById(Integer id) throws Exception;

	public List<Integer> queryAllItemsId();
}
