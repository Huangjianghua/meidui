package com.meiduimall.service.search.task;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.meiduimall.service.search.service.IndexService;
import com.meiduimall.service.search.service.ItemService;
import com.meiduimall.service.search.service.ProductIndexService;

@Configuration
@EnableScheduling
public class CheckIndexTask {

	private static Logger logger = LoggerFactory.getLogger(CheckIndexTask.class);

	@Autowired
	private IndexService indexService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ProductIndexService productIndexService;

	/**
	 * 功能描述: 增量检查数据库索引库匹配任务，每天早上5点
	 */
	@Scheduled(cron = "0 0 5 * * *")
	public void startTask() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("==============数据库和索引库信息同步检查开始==============" + sdf.format(new Date()));
		try {
			// 查询数据库所有符合条件的商品ID
			List<Integer> dbIds = itemService.queryAllItemsId();
			// 索引库所有商品ID
			List<Integer> indexIds = productIndexService.queryIds();
			// 比较ID，不同ID集合
			Collection<Integer> diffents = getDiffent(dbIds, indexIds);
			for (Integer id : diffents) {
				if (dbIds.contains(id) && !indexIds.contains(id)) {
					// 数据库存在，索引库不存在则添加
					indexService.addProductIndexById(id);
					logger.info("进行数据库和索引库信息同步检查，更新了itemId[" + id + "]信息到索引库");
				} else if (!dbIds.contains(id) && indexIds.contains(id)) {
					// 索引库存在，数据库不存在则删除
					indexService.deleteProductIndexByItemId(String.valueOf(id));
					logger.info("进行索引库和数据库信息同步检查，删除了索引库itemId[" + id + "]信息");
				}
			}
		} catch (Exception e) {
			logger.error("数据库和索引库信息同步检查出现异常", e);
		}
		logger.info("=============数据库和索引库信息同步检查结束============" + sdf.format(new Date()));
	}

	/**
	 * 找出两个集合不同元素
	 * 
	 * @param collmax
	 * @param collmin
	 * @return
	 * @throws Exception
	 */
	private Collection<Integer> getDiffent(Collection<Integer> collmax, Collection<Integer> collmin) throws Exception {

		// 使用LinkeList防止差异过大时,元素拷贝
		Collection<Integer> csReturn = new LinkedList<>();
		Collection<Integer> max = collmax;
		Collection<Integer> min = collmin;
		// 先比较大小,这样会减少后续map的if判断次数
		if (collmax.size() < collmin.size()) {
			max = collmin;
			min = collmax;
		}
		// 直接指定大小,防止再散列
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(max.size());
		for (Integer object : max) {
			map.put(object, 1);
		}
		for (Integer object : min) {
			if (map.get(object) == null) {
				csReturn.add(object);
			} else {
				map.put(object, 2);
			}
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				csReturn.add(entry.getKey());
			}
		}
		return csReturn;
	}
}
