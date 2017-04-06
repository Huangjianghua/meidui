package com.meiduimall.application.search.services.impl;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meiduimall.application.search.IDao.NewestPriceMapper;
import com.meiduimall.application.search.pojo.NewestPrice;
import com.meiduimall.application.search.services.NewestPriceService;

@Service
public class NewestPriceServiceImpl implements NewestPriceService {

	@Autowired
	private NewestPriceMapper newestPriceMapper;
	
	@Override
	public LinkedList<NewestPrice> getNewestPrices(List<Integer> ids) throws Exception {
		return newestPriceMapper.getNewestPrices(ids);
	}

}
