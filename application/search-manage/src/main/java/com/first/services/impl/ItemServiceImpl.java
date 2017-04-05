package com.first.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.first.mapper.ItemMapper;
import com.first.page.QueryResult;
import com.first.pojo.ItemModel;
import com.first.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Resource
	private ItemMapper itemMapper;

	@Override
	public QueryResult queryItems(ItemModel itemModel) throws Exception {
		List<ItemModel> items = itemMapper.queryItems(itemModel);
		QueryResult qr = new QueryResult();
		qr.setDateList(items);
		qr.setTotalCount((int)queryItemsCount(itemModel));
		return qr;
	}

	@Override
	public long queryItemsCount(ItemModel itemModel) throws Exception {
		return itemMapper.queryItemsCount(itemModel);
	}

	@Override
	public ItemModel queryItemById(Integer id) throws Exception {
		return itemMapper.queryItemById(id);
	}

	@Override
	public List<Integer> queryAllItemsId() {
		return itemMapper.queryAllItemsId();
	}

}
