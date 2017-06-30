package com.meiduimall.service.search.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meiduimall.service.search.entity.ItemModel;
import com.meiduimall.service.search.mapper.ItemMapper;
import com.meiduimall.service.search.page.QueryResult;
import com.meiduimall.service.search.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Resource
	private ItemMapper itemMapper;

	@Override
	public QueryResult queryItems(ItemModel itemModel) throws Exception {
		List<ItemModel> items = itemMapper.queryItems(itemModel);
		QueryResult qr = new QueryResult();
		qr.setDateList(items);
		qr.setTotalCount((int) queryItemsCount(itemModel));
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
