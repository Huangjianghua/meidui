package com.meiduimall.application.search.manage.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meiduimall.application.search.manage.mapper.ItemMapper;
import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.pojo.ItemModel;
import com.meiduimall.application.search.manage.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Resource
	private ItemMapper itemMapper;

	@Override
	public QueryResult queryItems(ItemModel itemModel)  {
		List<ItemModel> items = itemMapper.queryItems(itemModel);
		QueryResult qr = new QueryResult();
		qr.setDateList(items);
		qr.setTotalCount((int)queryItemsCount(itemModel));
		return qr;
	}

	@Override
	public long queryItemsCount(ItemModel itemModel)  {
		return itemMapper.queryItemsCount(itemModel);
	}

	@Override
	public ItemModel queryItemById(Integer id) {
		return itemMapper.queryItemById(id);
	}

	@Override
	public List<Integer> queryAllItemsId() {
		return itemMapper.queryAllItemsId();
	}

}
