package com.first.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.first.IDao.NewestPriceMapper;
import com.first.common.JunitTests;
import com.first.pojo.NewestPrice;

public class NewestPriceServiceTest extends JunitTests {

	@Autowired
	private NewestPriceMapper newestPriceMapper;

	@Test
	public void testGetNewestPrices() throws Exception {
		List<Integer> ids = new ArrayList<>();
		ids.add(155);
		ids.add(156);
		ids.add(157);
		ids.add(158);
		LinkedList<NewestPrice> newestPrices = newestPriceMapper.getNewestPrices(ids);
		for (NewestPrice newestPrice : newestPrices) {
			System.out.println(newestPrice);
		}
	}

}
