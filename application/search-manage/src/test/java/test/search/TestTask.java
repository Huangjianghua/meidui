package test.search;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.first.pojo.Scanner;
import com.first.services.IndexService;
import com.first.services.ProductIndexService;
import com.first.services.ScannerService;
import com.first.task.SyncIndexTask;

public class TestTask extends BaseTest {

	private Logger log = Logger.getLogger(SyncIndexTask.class);
	
	@Autowired
	private IndexService indexService;
	
	@Autowired
	private ScannerService scannerService;
	
	@Autowired
	private ProductIndexService productIndexService;
	
	@Test
	public void startTask() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("开始数据库和索引库信息同步检查，开始时间： " + sdf.format(new Date()));
		int flagId = -1;
		String table = "";
		try {
			// 查询所有数据库商品ID
			List<Scanner> scanners = scannerService.queryScanners(null);
			for (Scanner scanner : scanners) {
				System.out.println(scanner);
				flagId = scanner.getScanId();
				// 更新表：1、item，2、brand，3、cat，4、shop
				table = scanner.getTableName();
				Integer updateId = scanner.getUpdateId();
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
				// 根据条件查询符合的索引信息
				List<String> idList = productIndexService.queryIndexByQuery(field + ":" + updateId);
				System.out.println(idList.size());
				for (String itemId : idList) {
					if (itemId != null) {
						indexService.addProductIndexById(Integer.parseInt(itemId));
						// 如果是类目同时修改类目索引
						if (table.equals("cat")) {
							indexService.addCatlogIndexById(updateId);
						}
					}
				}
			}
			if (flagId == -1) {
				return;
			}
			// 同步结束 删除已扫描记录
//			int deleteNum = scannerService.deleteScanned(flagId);
			int deleteNum = 1;
			if (deleteNum > 0) {
				log.info("成功删除已扫描表，结束时间：" + sdf.format(new Date()));
			}
		} catch (Exception e) {
			log.error("数据库和索引库信息同步检查出现异常，" + table +"_id： " + flagId, e);
		}
		log.info("完成数据库和索引库信息同步检查，结束时间：" + sdf.format(new Date()));
	}
}
