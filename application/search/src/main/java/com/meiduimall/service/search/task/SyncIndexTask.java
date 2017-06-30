package com.meiduimall.service.search.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.meiduimall.service.search.entity.Scanner;
import com.meiduimall.service.search.service.IndexService;
import com.meiduimall.service.search.service.ProductIndexService;
import com.meiduimall.service.search.service.ScannerService;

@Configuration
@EnableScheduling
public class SyncIndexTask {

	private static Logger logger = LoggerFactory.getLogger(SyncIndexTask.class);

	@Autowired
	private IndexService indexService;

	@Autowired
	private ScannerService scannerService;

	@Autowired
	private ProductIndexService productIndexService;

	/**
	 * 功能描述: 检查商品操作任务，早上七点到晚上十一点内每半小时
	 */
	@Scheduled(cron = "0 0/30 7-23 * * *")
	public void startTask() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("开始数据库和索引库信息同步检查，开始时间： " + sdf.format(new Date()));
		int flagId = -1;
		int count = 0;
		String table = "";
		try {
			// 查询数据库--sysitem_scanner表的所有数据
			List<Scanner> scanners = scannerService.queryScanners(null);
			// 已删除ID
			List<Integer> deletedIds = new ArrayList<Integer>();
			for (Scanner scanner : scanners) {
				flagId = scanner.getScanId();
				// 更新表：1、item，2、brand，3、cat，4、shop
				table = scanner.getTableName();
				Integer updateId = scanner.getUpdateId();
				String operateType = scanner.getOperateType();
				String field = "";
				switch (table) {
				case "item":
					field = "itemId";
					break;
				case "brand":
					field = "brandId";
					break;
				case "cat":
					field = "catId";
					break;
				case "shop":
					field = "shopId";
					break;

				default:
					break;
				}
				// 操作类型
				if ("delete".equals(operateType)) {
					deleteIndex(field, updateId);
				} else {
					count = updateIndex(field, updateId, table);
				}
				// 将已更新内容ID添加到已删除集合中
				deletedIds.add(flagId);
			}
			if (flagId == -1 || count == 0) {
				return;
			}
			// 同步结束 删除已扫描记录
			int deleteNum = scannerService.deleteScanned(deletedIds);
			if (deleteNum > 0) {
				logger.info("成功删除已扫描表，共操作" + count + "条记录,结束时间：" + sdf.format(new Date()));
			}
		} catch (Exception e) {
			logger.error("数据库和索引库信息同步检查出现异常，" + table + "_id： " + flagId, e);
		} finally {
			logger.info("完成数据库和索引库信息同步检查，结束时间：" + sdf.format(new Date()));
		}
	}

	private int updateIndex(String field, Integer updateId, String table) throws Exception {
		// 根据条件查询符合的索引信息
		List<String> idList = productIndexService.queryIndexByQuery(field + ":" + updateId);
		for (String itemId : idList) {
			if (itemId != null) {
				indexService.addProductIndexById(Integer.parseInt(itemId));
				// 如果是类目同时修改类目索引
				if ("cat".equals(table)) {
					indexService.addCatlogIndexById(updateId);
				}
				logger.info("更新索引 [" + field + ":" + updateId + "] 成功！");
			}
		}
		return idList.size();
	}

	private void deleteIndex(String field, Integer updateId) {
		Map<String, Object> result = indexService.deleteProductIndexByQuery(field + ":" + updateId);
		if ("0".equals(result.get("status_code"))) {
			logger.info("删除[" + field + ":" + updateId + "]索引信息成功");
		} else {
			logger.info("删除[" + field + ":" + updateId + "]索引信息失败");
		}
	}
}
