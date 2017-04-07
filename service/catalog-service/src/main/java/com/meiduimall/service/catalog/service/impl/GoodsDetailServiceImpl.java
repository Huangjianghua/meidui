package com.meiduimall.service.catalog.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.CheckGoodsResult;
import com.meiduimall.service.catalog.entity.IdAndToken;
import com.meiduimall.service.catalog.entity.JsonItemDetailResult;
import com.meiduimall.service.catalog.entity.JsonItemDetailResult_ItemData;
import com.meiduimall.service.catalog.entity.JsonItemDetailResult_Prop_Values;
import com.meiduimall.service.catalog.entity.JsonItemDetailResult_Props;
import com.meiduimall.service.catalog.entity.JsonItemDetailResult_ShopData;
import com.meiduimall.service.catalog.entity.JsonItemDetailResult_Sku;
import com.meiduimall.service.catalog.entity.SyscategoryProps;
import com.meiduimall.service.catalog.entity.SysitemItemCount;
import com.meiduimall.service.catalog.entity.SysitemItemDesc;
import com.meiduimall.service.catalog.entity.SysitemItemStatus;
import com.meiduimall.service.catalog.entity.SysitemItemStore;
import com.meiduimall.service.catalog.entity.SysitemItemWithBLOBs;
import com.meiduimall.service.catalog.entity.SysitemSkuExample;
import com.meiduimall.service.catalog.entity.SysitemSkuStore;
import com.meiduimall.service.catalog.entity.SysitemSkuWithBLOBs;
import com.meiduimall.service.catalog.service.GoodsDetailService;
import com.meiduimall.service.catalog.service.common.ShopCommonService;
import com.meiduimall.service.catalog.util.ParserItemSpecDescBean;
import com.meiduimall.service.catalog.util.ParserItemSpecDescBean.PropBean;
import com.meiduimall.service.catalog.util.ParserItemSpecDescUtil;
import com.meiduimall.service.catalog.util.ParserSkuSpecDescBean;
import com.meiduimall.service.catalog.util.ParserSkuSpecDescUtil;

@Service
public class GoodsDetailServiceImpl implements GoodsDetailService {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(GoodsDetailServiceImpl.class);

	@Autowired
	private Environment env;

	@Autowired
	private BaseDao baseDao;

	@Override
	public ResBodyData checkItemIsExistById(int item_id) {
		ResBodyData result = new ResBodyData();
		try {

			/** TODO --------查询这个商品ID是否存在-------- */
			int count = baseDao.selectOne(item_id, "SysitemItemMapper.getItemCountByItemId");
			if (count > 0) {
				// 返回访问这个商品的详情页的地址
				CheckGoodsResult bean = new CheckGoodsResult();
				String base_url = env.getProperty("estore.base-url");
				String url = base_url + "/item.html?item_id=" + item_id;
				bean.setUrl(url);

				result.setData(bean);
				result.setStatus(BaseApiCode.SUCCESS);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));
			} else {
				result.setData(new JSONObject());
				result.setStatus(BaseApiCode.NONE_DATA);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NONE_DATA));
			}

			/** TODO ----------查询该商品状态------- */
			/*
			 * SysitemItemStatus itemStatus = baseDao.selectOne(item_id,
			 * "SysitemItemStatusMapper.selectByPrimaryKey"); String
			 * approveStatus = itemStatus.getApproveStatus(); if
			 * ("onsale".equals(approveStatus)) { // 返回访问这个商品的详情页的地址
			 * CheckGoodsResult bean = new CheckGoodsResult(); String base_url =
			 * env.getProperty("estore.base-url"); String url = base_url +
			 * "/item.html?item_id=" + item_id; bean.setUrl(url);
			 * 
			 * result.setData(bean); result.setStatus(BaseApiCode.SUCCESS);
			 * result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.SUCCESS)); } else
			 * { result.setData(new JSONObject());
			 * result.setStatus(BaseApiCode.NONE_DATA);
			 * result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NONE_DATA)); }
			 */

		} catch (Exception e) {
			result.setData(new JSONObject());
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			logger.error("查询商品信息，service报异常：" + e);
		}
		return result;
	}

	@Override
	public ResBodyData getItemDetailById(String token, Integer item_id) {
		/**
		 * <table schema="" tableName="sysitem_item">
		 * <table schema="" tableName="sysitem_item_desc">
		 * <table schema="" tableName="sysitem_item_status">
		 * <table schema="" tableName="sysitem_item_count">
		 * <table schema="" tableName="sysitem_item_store">
		 * 
		 * <table schema="" tableName="sysitem_sku">
		 * <table schema="" tableName="sysitem_sku_store">
		 * 
		 * <table schema="" tableName="sysshop_shop">
		 * <table schema="" tableName="sysrate_dsr">
		 * 
		 * <table schema="" tableName="syscategory_props">
		 * <table schema="" tableName="syscategory_prop_values">
		 * 
		 * <table schema="" tableName="sysuser_account">
		 * <table schema="" tableName="sysuser_user_fav">
		 */
		ResBodyData result = new ResBodyData();// 最终返回的数据对象

		try {
			// 根据item_id查找sysitem_item表中对应的商品记录信息
			SysitemItemWithBLOBs itemWithBLOBs = baseDao.selectOne(item_id, "SysitemItemMapper.selectByPrimaryKey");
			if (itemWithBLOBs == null) {// 查询不到该商品
				result.setStatus(BaseApiCode.NONE_DATA);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NONE_DATA));
				result.setData(new JSONObject());
				return result;
			}

			JsonItemDetailResult jsonResult = new JsonItemDetailResult();
			// ----------1、开始拼接商品规格数据-----------
			List<JsonItemDetailResult_Props> itemPropsList = new ArrayList<JsonItemDetailResult_Props>();

			// 反序列化数据---解析商品的规格参数---读取sysitem_item表的spec_desc字段
			// 最终得到每一个规格，以及规格对应的规格属性。比如：[{4颜色：43黑色，44咖啡色，51军绿色},{},{}]
			List<ParserItemSpecDescBean> parseList = ParserItemSpecDescUtil.parser(itemWithBLOBs.getSpecDesc());
			if (parseList != null && parseList.size() > 0) {
				JsonItemDetailResult_Props itemProps = null;
				for (int i = 0; i < parseList.size(); i++) {

					// 获取每一组规格。比如：[{4颜色：43黑色，44咖啡色，51军绿色},{},{}]
					ParserItemSpecDescBean parserItemSpecDescBean = parseList.get(i);

					if (parserItemSpecDescBean != null) {
						// 规格
						itemProps = new JsonItemDetailResult_Props();

						// 根据规格ID查找规格名称。查找表syscategory_props
						// 比如上面只得到了编号4，并没有得到4对应的名称颜色
						// TODO 待优化
						SyscategoryProps categoryProps = baseDao.selectOne(parserItemSpecDescBean.getProp_id(),
								"SyscategoryPropsMapper.selectByPrimaryKey");

						itemProps.setProp_id(parserItemSpecDescBean.getProp_id().toString());
						itemProps.setProp_name(categoryProps.getPropName());

						// 遍历该规格下的每一种规格属性
						// 获取规格编号4颜色，对应的属性值：43黑色，44咖啡色，51军绿色，整理数据
						List<PropBean> propBeanList = parserItemSpecDescBean.getPropBeanList();
						if (propBeanList != null && propBeanList.size() > 0) {
							List<JsonItemDetailResult_Prop_Values> prop_list = new ArrayList<JsonItemDetailResult_Prop_Values>();
							JsonItemDetailResult_Prop_Values propValues = null;
							for (int j = 0; j < propBeanList.size(); j++) {
								PropBean propBean = propBeanList.get(j);
								if (propBean != null) {
									propValues = new JsonItemDetailResult_Prop_Values();
									Integer spec_value_id = propBean.getPropValueBean().getSpec_value_id();
									String spec_value = propBean.getPropValueBean().getSpec_value();
									if (spec_value_id != null) {
										propValues.setProp_value_id(spec_value_id.toString());
									} else {
										propValues.setProp_value_id("0");
									}
									propValues.setProp_value(spec_value);
									prop_list.add(propValues);
								} else {
									// 数据异常
								}
								propValues = null;
							}
							// 给该规格(比如4颜色)添加对应的规格属性列表
							itemProps.setProp_list(prop_list);
						} else {
							// 只有规格名称，没有规格对应的规格属性
							continue;
						}
						itemPropsList.add(itemProps);
					} else {
						// 数据异常
					}
					itemProps = null;
				} // 循环结束
			} else {
				// 查找不到规格参数
			}
			jsonResult.setItemPropsList(itemPropsList);

			// --------2、开始拼接商品信息数据-----------
			JsonItemDetailResult_ItemData itemData = new JsonItemDetailResult_ItemData();

			// 获取商品详情的HTML页面地址，查找sysitem_item_desc表
			SysitemItemDesc itemDesc = baseDao.selectOne(item_id, "SysitemItemDescMapper.selectByPrimaryKey");
			String html_detail_url = "";
			String wapDesc = itemDesc.getWapDesc();
			String pcDesc = itemDesc.getPcDesc();
			if (!StringUtils.isEmpty(wapDesc)) {
				html_detail_url = wapDesc;
			} else {
				html_detail_url = pcDesc;
			}
			itemData.setHtml_detail_url(html_detail_url);

			// 获取商品销量和商品评论信息，查找表sysitem_item_count
			SysitemItemCount itemCount = baseDao.selectOne(item_id, "SysitemItemCountMapper.selectByPrimaryKey");
			// 评论数量
			Integer rateCount = itemCount.getRateCount();
			if (rateCount == null) {
				itemData.setRate_count("0");
			} else {
				itemData.setRate_count(rateCount.toString());
			}

			// 商品销量=虚拟销量+实际销量
			Integer soldQuantity = itemCount.getSoldQuantity();
			Integer vituralQuantity = itemCount.getVituralQuantity();
			int sold = 0;
			int vitural = 0;
			if (soldQuantity != null) {
				sold = soldQuantity;
			}
			if (vituralQuantity != null) {
				vitural = vituralQuantity;
			}
			int rate_count = sold + vitural;
			itemData.setSales_volume(rate_count + "");

			// 查询商品状态信息，表sysitem_item_status
			SysitemItemStatus itemStatus = baseDao.selectOne(item_id, "SysitemItemStatusMapper.selectByPrimaryKey");
			// 商品状态
			String approveStatus = itemStatus.getApproveStatus();
			itemData.setApprove_status(approveStatus);
			// if ("onsale".equals(approveStatus)) {
			// itemData.setApprove_status("出售中");
			// } else {
			// itemData.setApprove_status("库中");
			// }

			// 商品上架时间
			Integer listTime = itemStatus.getListTime();
			if (listTime != null) {
				itemData.setList_time(DateFormatUtils.format(listTime.intValue(), "yyyy-MM-dd HH:mm:ss"));
			} else {
				itemData.setList_time("");
			}

			itemData.setBn(itemWithBLOBs.getBn());
			itemData.setImage_default_id(itemWithBLOBs.getImageDefaultId());
			itemData.setItme_id(item_id + "");
			itemData.setList_image(itemWithBLOBs.getListImage());
			itemData.setPoint(itemWithBLOBs.getPoint().toString());
			itemData.setPrice(itemWithBLOBs.getPrice().toString());

			String subTitle = itemWithBLOBs.getSubTitle();
			String title = itemWithBLOBs.getTitle();
			if (StringUtils.isEmpty(subTitle)) {
				subTitle = title;
			}
			itemData.setSub_title(subTitle);
			itemData.setTitle(title);
			BigDecimal weight = itemWithBLOBs.getWeight();
			if (weight != null) {
				itemData.setWeight(weight.toString());
			} else {
				itemData.setWeight("");
			}

			if (itemWithBLOBs.getIsShowWeight() != null) {
				itemData.setIs_show_weight(itemWithBLOBs.getIsShowWeight().toString());
			} else {
				itemData.setIs_show_weight("0");
			}

			// 查询商品的库存，表sysitem_item_store
			SysitemItemStore itemStore = baseDao.selectOne(item_id, "SysitemItemStoreMapper.selectByPrimaryKey");
			Integer store = itemStore.getStore();
			Integer freez = itemStore.getFreez();
			if (store != null) {
				if (freez != null) {
					int item_store = store.intValue() - freez.intValue();
					itemData.setItem_store(item_store + "");
				} else {
					itemData.setItem_store(store.toString());
				}
			} else {
				itemData.setItem_store("0");
			}

			// 检查用户是否收藏了该商品
			if (StringUtils.isEmpty(token)) {
				// 没有token，不需要处理
				itemData.setIs_collect("0");
			} else {
				// 处理token
				IdAndToken idAndToken = new IdAndToken();
				idAndToken.setId(item_id.intValue());
				idAndToken.setToken(token);
				int count = baseDao.selectOne(idAndToken, "SysuserUserFavMapper.selectCountByItemIdAndToken");
				if (count > 0) {
					itemData.setIs_collect("1");
				} else {
					itemData.setIs_collect("0");
				}
			}
			jsonResult.setItemData(itemData);

			// -------------3、开始拼接商品SKU数据-----------
			List<JsonItemDetailResult_Sku> skuList = new ArrayList<JsonItemDetailResult_Sku>();

			// 查sysitem_sku表，根据item_id查找该商品对应的SKU列表
			SysitemSkuExample skuExample = new SysitemSkuExample();
			SysitemSkuExample.Criteria criteria = skuExample.createCriteria();
			criteria.andItemIdEqualTo(item_id);
			List<SysitemSkuWithBLOBs> itemSkuWithBLOBsList = baseDao.selectList(skuExample,
					"SysitemSkuMapper.selectByExampleWithBLOBs");

			if (itemSkuWithBLOBsList != null && itemSkuWithBLOBsList.size() > 0) {
				JsonItemDetailResult_Sku result_sku = null;
				for (int i = 0; i < itemSkuWithBLOBsList.size(); i++) {
					SysitemSkuWithBLOBs sysitemSkuWithBLOBs = itemSkuWithBLOBsList.get(i);
					if (sysitemSkuWithBLOBs == null) {
						continue;// can not reach
					}
					result_sku = new JsonItemDetailResult_Sku();

					result_sku.setPoint(sysitemSkuWithBLOBs.getPoint().toString());
					result_sku.setPrice(sysitemSkuWithBLOBs.getPrice().toString());
					result_sku.setSku_id(sysitemSkuWithBLOBs.getSkuId().toString());

					// result_sku.setSpec_info(sysitemSkuWithBLOBs.getSpecInfo());前端不需要该字段

					String sku_status = sysitemSkuWithBLOBs.getStatus();
					result_sku.setStatus(sku_status);
					// if ("normal".equals(sku_status)) {
					// result_sku.setStatus("正常");
					// } else {
					// result_sku.setStatus("删除");
					// }

					if (sysitemSkuWithBLOBs.getWeight() != null) {
						result_sku.setWeight(sysitemSkuWithBLOBs.getWeight().toString());
					} else {
						result_sku.setWeight("0");
					}

					// 查sysitem_sku_store表，获取每一个SKU对应的库存信息
					SysitemSkuStore skuStore = baseDao.selectOne(sysitemSkuWithBLOBs.getSkuId(),
							"SysitemSkuStoreMapper.selectByPrimaryKey");
					int sku_store = skuStore.getStore().intValue() - skuStore.getFreez().intValue();
					result_sku.setSku_store(sku_store + "");

					// 反序列化数据---解析每一个商品对应的SKU数据
					List<ParserSkuSpecDescBean> skuSpecDescBeanList = ParserSkuSpecDescUtil
							.parser(sysitemSkuWithBLOBs.getSpecDesc());

					if (skuSpecDescBeanList != null && skuSpecDescBeanList.size() > 0) {
						StringBuilder sb = new StringBuilder();
						for (int j = 0; j < skuSpecDescBeanList.size(); j++) {
							Integer prop_value_id = skuSpecDescBeanList.get(j).getProp_value_id();
							sb.append(prop_value_id + "_");
						}
						if (sb.length() > 1) {
							String prop_value_ids = sb.substring(0, sb.length() - 1);
							result_sku.setProp_value_ids(prop_value_ids);
						} else {
							// 数据异常，没有找到prop_value_id(一般不会发生)
						}
					} else {
						// 数据异常，反序列化失败
					}
					skuList.add(result_sku);
					result_sku = null;
				}
			} else {
				// 没有SKU，不需要处理
			}
			jsonResult.setSkuList(skuList);

			// -------------4、开始拼接商家数据-----------
			Integer shopId = itemWithBLOBs.getShopId();
			JsonItemDetailResult_ShopData shopData = ShopCommonService.getJsonItemDetailResult_ShopData(baseDao, shopId,
					token);
			
			jsonResult.setShopData(shopData);

			result.setData(jsonResult);
			result.setStatus(BaseApiCode.SUCCESS);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));

		} catch (Exception e) {
			logger.error("根据商品编号，获取商品详情，service报异常：" + e);
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
		}
		return result;
	}

}
