package com.meiduimall.service.catalog.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.SysitemItemRecommend;
import com.meiduimall.service.catalog.result.CheckGoodsResult;
import com.meiduimall.service.catalog.result.GoodsDetailResult;
import com.meiduimall.service.catalog.result.JsonCheckGoodsResult;
import com.meiduimall.service.catalog.result.ListGoodsDetailResult;
import com.meiduimall.service.catalog.service.GoodsRecommendService;

@Service
public class GoodsRecommendServiceImpl implements GoodsRecommendService {

	private static Logger logger = LoggerFactory.getLogger(GoodsRecommendServiceImpl.class);

	@Autowired
	private Environment env;

	@Autowired
	private BaseDao baseDao;

	@Transactional
	@Override
	public ResBodyData insertBatchItems(int[] item_ids, int type, String opt_user, String ip, int reco_level) {
		logger.info("批量插入推荐商品ID: " + Arrays.toString(item_ids) + " ; type: " + type);
		List<SysitemItemRecommend> list = new ArrayList<SysitemItemRecommend>();
		SysitemItemRecommend bean = null;
		for (int i = 0; i < item_ids.length; i++) {
			/** ----------验证这个item_id是否存在-------- */
			int count = baseDao.selectOne(item_ids[i], "SysitemItemMapper.getItemCountByItemId");
			if (count < 1) {
				continue;
			}
			/** TODO ----------查询该商品状态------- */
			/*
			 * SysitemItemStatus itemStatus = baseDao.selectOne(item_ids[i],
			 * "SysitemItemStatusMapper.selectByPrimaryKey"); String
			 * approveStatus = itemStatus.getApproveStatus(); if
			 * (!"onsale".equals(approveStatus)) { continue; }
			 */
			bean = new SysitemItemRecommend();
			bean.setItemId(item_ids[i]);
			bean.setRecoType(type);
			bean.setOptUser(opt_user);
			bean.setIp(ip);
			bean.setRecoTime(new Date());
			bean.setRecoLevel(reco_level);
			list.add(bean);
			bean = null;
		}

		if (list.size() > 0) {
			/** TODO 是否需要删除就数据?? */
			// baseDao.delete(null,
			// "SysitemItemRecommendMapper.deleteByExample");
			Integer rows = baseDao.insertBatch(list, "SysitemItemRecommendMapper.insertBatch");
			if (rows > 0 && rows == list.size()) {
				// 批量插入成功
				ResBodyData result = new ResBodyData();
				result.setStatus(ServiceCatalogApiCode.SUCCESS);
				result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.OPERAT_SUCCESS));
				result.setData(JsonUtils.getInstance().createObjectNode());
				return result;
			} else {
				throw new ServiceException(ServiceCatalogApiCode.OPERAT_FAIL);
			}
		} else {
			throw new ServiceException(ServiceCatalogApiCode.REQUEST_PARAMS_ERROR);
		}
	}

	@Override
	public ResBodyData getFirstRecommendItems(int type, int count, int sourceId) {
		logger.info("获取指定类型的推荐商品： " + type);

		ResBodyData result = new ResBodyData();
		String base_url = env.getProperty("estore.base-url");
		// 先查询出最后推荐的商品item_id(不需要过滤重复数据)
		List<Integer> list = baseDao.selectList(type, "SysitemItemRecommendMapper.selectLastRecordByType");
		// 集合反转
		Collections.reverse(list);
		List<GoodsDetailResult> results = new ArrayList<GoodsDetailResult>();
		if (list != null && list.size() > 0) {
			// 根据商品id查询商品详情
			List<GoodsDetailResult> queryResults = baseDao.selectList(list, "SysitemItemMapper.getItemsInItemId");
			// 重新排序:查询商品详情获取到的顺序 与 查询商品item_id的顺序不一致，所以需要重新排序
			for (int i = 0; i < list.size(); i++) {
				int item_id = list.get(i);
				inner: for (int k = 0; k < queryResults.size(); k++) {
					String temp_id = queryResults.get(k).getItemId();
					if (item_id == Integer.parseInt(temp_id)) {
						results.add(queryResults.get(k));
						break inner;
					}
				}
			}
			if (results != null && results.size() > 0) {
				// 分别给每一个商品详情查询结果添加访问地址
				for (GoodsDetailResult detail : results) {
					if (sourceId == 1) {
						detail.setUrl(base_url + "/wap/item.html?item_id=" + detail.getItemId());
					} else if (sourceId == 2) {
						detail.setUrl(base_url + "/item.html?item_id=" + detail.getItemId());
					} else {
						// can not reach
						detail.setUrl(base_url + "/wap/item.html?item_id=" + detail.getItemId());
					}
				}
				ListGoodsDetailResult data = new ListGoodsDetailResult();
				data.setResults(results);
				result.setData(data);
				result.setStatus(ServiceCatalogApiCode.SUCCESS);
				result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_SUCCESS));
			} else {
				throw new ServiceException(ServiceCatalogApiCode.NONE_DATA);
			}
		} else {
			throw new ServiceException(ServiceCatalogApiCode.NONE_DATA);
		}
		return result;
	}

	@Override
	public ResBodyData getFirstRecommendItemsAllType(int count) {
		logger.info("获取各个类型的推荐商品,count=" + count);

		ResBodyData result = new ResBodyData();

		String base_url = env.getProperty("estore.base-url");
		List<Integer> list1 = baseDao.selectList(1, "SysitemItemRecommendMapper.selectLastRecordByType");
		List<Integer> list2 = baseDao.selectList(2, "SysitemItemRecommendMapper.selectLastRecordByType");

		List<CheckGoodsResult> results1 = new ArrayList<CheckGoodsResult>();
		List<CheckGoodsResult> results2 = new ArrayList<CheckGoodsResult>();

		if (list1 != null && list1.size() > 0) {
			CheckGoodsResult detail = null;
			for (Integer item_id : list1) {
				detail = new CheckGoodsResult();
				detail.setItemId(item_id.toString());
				detail.setUrl(base_url + "/item.html?item_id=" + item_id.intValue());
				results1.add(detail);
				detail = null;
			}
		}

		if (list2 != null && list2.size() > 0) {
			CheckGoodsResult detail = null;
			for (Integer item_id : list2) {
				detail = new CheckGoodsResult();
				detail.setItemId(item_id.toString());
				detail.setUrl(base_url + "/item.html?item_id=" + item_id.intValue());
				results2.add(detail);
				detail = null;
			}
		}

		JsonCheckGoodsResult data = new JsonCheckGoodsResult();
		data.setResults1(results1);
		data.setResults2(results2);

		result.setData(data);
		result.setStatus(ServiceCatalogApiCode.SUCCESS);
		result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_SUCCESS));
		return result;
	}

	@Override
	public ResBodyData getAllRecommendItems(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResBodyData getRecommendItemsByType(int type, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResBodyData deleteRecommendItems(int[] item_id, String opt_user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResBodyData updateRecommendItemLevel(int item_id, String opt_user) {
		// TODO Auto-generated method stub
		return null;
	}

}
