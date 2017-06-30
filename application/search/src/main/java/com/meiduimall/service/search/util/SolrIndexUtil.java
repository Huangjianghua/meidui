package com.meiduimall.service.search.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.meiduimall.service.search.entity.ItemModel;
import com.meiduimall.service.search.entity.ItemPropValue;
import com.meiduimall.service.search.entity.Props;
import com.meiduimall.service.search.util.ParseItemSpecDescBean.PropValueBean;

public class SolrIndexUtil {

	private static final Logger log = LoggerFactory.getLogger(SolrIndexUtil.class);

	/**
	 * 将ItemModel对象转换为SolrInputDocument对象
	 * 
	 * @param itemModel
	 * @param propMaps
	 * @return
	 */
	public static SolrInputDocument transform2Document(ItemModel itemModel, Map<Integer, Props> propMaps) {
		try {
			SolrInputDocument doc = new SolrInputDocument();
			double price = itemModel.getPrice();
			double point = itemModel.getPoint();
			double costPrice = itemModel.getCostPrice();
			// 商品的利润（销售价-成本价）(C)
			double profit = price - costPrice;

			doc.addField("id", itemModel.getId());
			doc.addField("itemId", itemModel.getItemId());
			doc.addField("title", itemModel.getTitle(), 20);
			doc.addField("image", itemModel.getImage());

			String brandId = itemModel.getBrandId();
			String brand = itemModel.getBrand();
			doc.addField("brandId", brandId);
			doc.addField("brand", brand);
			doc.addField("brandInfo", brandId + "_" + brand);

			// 类目信息
			String catId = itemModel.getCatId();
			String cat = itemModel.getCat().trim();
			String catParentId = itemModel.getCatParentId();

			StringBuilder catInfo = new StringBuilder();
			catInfo.append(catParentId).append("_");
			catInfo.append(catId).append("_");
			catInfo.append(cat);

			doc.addField("catId", catId);
			doc.addField("catParentId", catParentId);
			doc.addField("cat", cat);
			doc.addField("catInfo", catInfo);

			// 类目路径
			String catPath = itemModel.getCatPath();
			doc.addField("catPath", catPath + catId);

			// 店铺信息
			StringBuilder shopInfo = new StringBuilder();
			String shopId = itemModel.getShopId();
			String shopName = itemModel.getShopName();
			String shopDescript = itemModel.getShopDescript();
			String shopLogo = itemModel.getShopLogo();
			String tallyScore = itemModel.getTallyScore();
			String attitudeScore = itemModel.getAttitudeScore();
			String deliverySpeedScore = itemModel.getDeliverySpeedScore();

			shopInfo.append(shopId).append("_");
			shopInfo.append(shopName).append("_");
			shopInfo.append(Strings.isNullOrEmpty(shopDescript) ? "暂无简介~" : shopDescript).append("_");
			shopInfo.append(Strings.isNullOrEmpty(shopLogo) ? "default" : shopLogo).append("_");
			shopInfo.append(Strings.isNullOrEmpty(tallyScore) ? "5" : tallyScore).append("_");
			shopInfo.append(Strings.isNullOrEmpty(attitudeScore) ? "5" : attitudeScore).append("_");
			shopInfo.append(Strings.isNullOrEmpty(deliverySpeedScore) ? "5" : deliverySpeedScore);

			doc.addField("shopId", shopId);
			doc.addField("shopName", shopName);
			doc.addField("shopInfo", shopInfo);

			// 商品在若干天内的访问次数(A)
			Integer viewCount = itemModel.getViewCount();

			// 商品在若干天内的销量(B)
			Integer soldQuantity = itemModel.getSoldQuantity();

			// 离当前的上架天数(D)
			Integer listTime = itemModel.getListTime();
			if (listTime == null) {
				listTime = 0;
			}
			int now = (int) (System.currentTimeMillis() / 1000);
			int listDays = now - listTime;

			// 若干天内的好评率(E)
			Integer buyCount = itemModel.getBuyCount();
			Integer rateCount = itemModel.getRateCount();
			Integer rateGoodCount = itemModel.getRateGoodCount();
			double goodRate = (1 + rateGoodCount) / (1 + rateCount);

			// 若干天内的收藏率(F)
			Integer favCount = itemModel.getFavCount();
			if (favCount == null) {
				favCount = 0;
			}
			double favRate = (favCount + 1) / (viewCount + 1.0);

			// 商品的排序系数=[(1+B)/(1+A)]*(1+C)*1/D*(1+E)*[(1+F)/(1+A)]。
			double score = ((1 + soldQuantity) / (1 + viewCount)) * (1 + profit) * 1.0 / listDays * (1 + goodRate)
					* ((1 + favRate) / (1 + viewCount));

			doc.addField("score", score);

			doc.addField("sold_quantity", soldQuantity);
			doc.addField("rate_count", rateCount);
			doc.addField("buy_count", buyCount);
			doc.addField("view_count", viewCount);
			doc.addField("list_time", listTime);

			doc.addField("price", price);
			doc.addField("point", point);

			// 转换属性属性值
			String specDesc = itemModel.getSpecDesc();
			if (specDesc != null) {
				Map<Integer, ArrayList<ItemPropValue>> transformProp = transformProp(specDesc);
				Collection<ArrayList<ItemPropValue>> values = transformProp.values();
				Set<String> props = new HashSet<String>();
				for (ArrayList<ItemPropValue> itemPropValues : values) {
					ItemPropValue itemPropValue = itemPropValues.get(0);
					if (itemPropValue != null) {
						Integer pvid = itemPropValue.getPropValueId();
						Props prop = propMaps.get(pvid);
						if (prop == null) {
							continue;
						}
						Integer pid = itemPropValue.getPropId();
						String propName = prop.getPropName();
						String propValueName = prop.getPropValue();
						if (Strings.isNullOrEmpty(propName) || Strings.isNullOrEmpty(propValueName)) {
							continue;
						}
						// 添加动态属性 addField(prop_id_name, valueId_value)
						String field = "prop_" + pid + "_" + propName;
						String value = pvid + "_" + propValueName.trim();
						doc.addField(field, value);
						props.add(propValueName);
						// 动态查询属性
						doc.addField("attr_" + pid, pvid);
					}
				}
				doc.addField("props", props);
			}
			return doc;
		} catch (Exception e) {
			log.error("SolrDocument转换参数异常", e);
			return null;
		}
	}

	/**
	 * 转换属性-属性值
	 * 
	 * @param specDesc
	 * @return
	 * @throws Exception
	 */
	private static Map<Integer, ArrayList<ItemPropValue>> transformProp(String specDesc) throws Exception {

		List<ParseItemSpecDescBean> parseList = ParseItemSpecDesUtil.parse(specDesc);
		HashMap<Integer, ArrayList<ItemPropValue>> itemProp = new HashMap<Integer, ArrayList<ItemPropValue>>();
		if (parseList != null && !parseList.isEmpty()) {
			for (int i = 0; i < parseList.size(); i++) {
				ParseItemSpecDescBean descBean = parseList.get(i);
				Integer propId = descBean.getPropId();
				List<PropValueBean> beanList = descBean.getPropValueBeanList();
				ArrayList<ItemPropValue> propValueList = new ArrayList<ItemPropValue>();
				for (PropValueBean propValueBean : beanList) {
					ItemPropValue item = new ItemPropValue();
					item.setPropId(propId);
					item.setPropValueId(propValueBean.getSpecValueId());
					item.setPropValueName(propValueBean.getSpecValue());
					propValueList.add(item);
				}
				itemProp.put(propId, propValueList);
			}
		}
		return itemProp;
	}
}
