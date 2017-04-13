package com.meiduimall.service.catalog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.SysshopShop;
import com.meiduimall.service.catalog.entity.SysshopShopCat;
import com.meiduimall.service.catalog.entity.SysshopShopCatExample;
import com.meiduimall.service.catalog.entity.SysshopShopExample;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.entity.SysuserShopFav;
import com.meiduimall.service.catalog.entity.SysuserShopFavExample;
import com.meiduimall.service.catalog.request.ShopProductRequest;
import com.meiduimall.service.catalog.result.ChildShopCat;
import com.meiduimall.service.catalog.result.GoodsDetailResult;
import com.meiduimall.service.catalog.result.JsonItemDetailResult_ShopData;
import com.meiduimall.service.catalog.result.ParentShopCat;
import com.meiduimall.service.catalog.result.ShopCatResult;
import com.meiduimall.service.catalog.result.ShopProductList;
import com.meiduimall.service.catalog.service.ShopService;
import com.meiduimall.service.catalog.service.common.ShopCommonService;
import com.meiduimall.service.catalog.util.StringUtil;

@Service
public class ShopServiceImpl implements ShopService {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Override
	public ResBodyData getShopDetail(Integer shopId, String mem_id) {
		ResBodyData result = new ResBodyData();// 最终返回的数据对象
		try {
			JsonItemDetailResult_ShopData shopData = ShopCommonService.getJsonItemDetailResult_ShopData(baseDao, shopId,
					mem_id);

			if (shopData == null) {
				/** 没有这个店铺 */
				result.setStatus(BaseApiCode.NO_THIS_SHOP);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NO_THIS_SHOP));
				result.setData(new JSONObject());
				return result;
			}

			result.setData(shopData);
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

	@Override
	public ResBodyData collectOrCancelShop(Integer shopId, SysuserAccount sysuserAccount, int isCollect) {
		ResBodyData result = new ResBodyData();// 最终返回的数据对象
		try {
			// 获取user_id
			if (sysuserAccount == null) {
				result.setStatus(BaseApiCode.NO_LOGIN);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NO_LOGIN));
				result.setData(new JSONObject());
				return result;
			}
			Integer userId = sysuserAccount.getUserId();

			if (isCollect == 1) {
				// 收藏店铺
				// 1.首先判断该用户是否收藏了该店铺--如果已经收藏了，直接提示收藏成功
				SysuserShopFavExample shopFavExample = new SysuserShopFavExample();
				SysuserShopFavExample.Criteria criteria = shopFavExample.createCriteria();
				criteria.andShopIdEqualTo(shopId);
				criteria.andUserIdEqualTo(userId);
				int collectCount = baseDao.selectOne(shopFavExample, "SysuserShopFavMapper.countByExample");
				if (collectCount > 0) {
					result.setStatus(BaseApiCode.SUCCESS);
					result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.COLLECT_SUCCESS));
					result.setData(new JSONObject());
					return result;
				}

				// 2.查询店铺信息
				SysshopShopExample shopExample = new SysshopShopExample();
				SysshopShopExample.Criteria shopCriteria = shopExample.createCriteria();
				shopCriteria.andShopIdEqualTo(shopId);
				List<SysshopShop> shopList = baseDao.selectList(shopExample, "SysshopShopMapper.selectByExample");

				if (shopList == null || shopList.size() == 0) {
					// 没有这个店铺
					result.setStatus(BaseApiCode.NO_THIS_SHOP);
					result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NO_THIS_SHOP));
					result.setData(new JSONObject());
					return result;
				}
				SysshopShop sysshopShop = shopList.get(0);

				// 3.保存数据
				SysuserShopFav shopFav = new SysuserShopFav();

				// 时间只保存到秒
				shopFav.setCreateTime(new Long(System.currentTimeMillis() / 1000l).intValue());
				shopFav.setShopId(shopId);
				shopFav.setShopLogo(sysshopShop.getShopLogo());
				shopFav.setShopName(sysshopShop.getShopName());
				shopFav.setUserId(userId);
				Integer insertCount = baseDao.insert(shopFav, "SysuserShopFavMapper.insertSelective");
				if (insertCount > 0) {
					result.setStatus(BaseApiCode.SUCCESS);
					result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.COLLECT_SUCCESS));
					result.setData(new JSONObject());
				} else {
					result.setStatus(BaseApiCode.COLLECT_FAIL);
					result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.COLLECT_FAIL));
					result.setData(new JSONObject());
				}
			} else {
				// 取消收藏
				SysuserShopFavExample shopFavExample = new SysuserShopFavExample();
				SysuserShopFavExample.Criteria shopFavCriteria = shopFavExample.createCriteria();
				shopFavCriteria.andUserIdEqualTo(userId);
				shopFavCriteria.andShopIdEqualTo(shopId);
				Integer deleteCount = baseDao.delete(shopFavExample, "SysuserShopFavMapper.deleteByExample");
				if (deleteCount > 0) {
					result.setStatus(BaseApiCode.SUCCESS);
					result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.CANCEL_COLLECT_SUCCESS));
					result.setData(new JSONObject());
				} else {
					result.setStatus(BaseApiCode.CANCEL_COLLECT_FAIL);
					result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.CANCEL_COLLECT_FAIL));
					result.setData(new JSONObject());
				}
			}
		} catch (Exception e) {
			if (isCollect == 1) {
				logger.error("收藏藏店铺，service报异常：" + e);
			} else {
				logger.error("取消收藏店铺，service报异常：" + e);
			}
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
		}
		return result;
	}

	@Override
	public ResBodyData getShopProductCatalog(Integer shopId) {
		ResBodyData result = new ResBodyData();// 最终返回的数据对象
		try {
			logger.error("获取店铺自定义分类商品，shop_id： " + shopId);
			SysshopShopCatExample example = new SysshopShopCatExample();
			SysshopShopCatExample.Criteria criteria = example.createCriteria();
			criteria.andShopIdEqualTo(Integer.valueOf(shopId));
			List<SysshopShopCat> list = baseDao.selectList(example, "SysshopShopCatMapper.selectByExample");

			if (list != null && list.size() > 0) {
				ShopCatResult shopCatResult = new ShopCatResult();
				List<ParentShopCat> results = new ArrayList<ParentShopCat>();

				// 1.先得到所有的parent_id
				for (SysshopShopCat cat : list) {
					Integer parentId = cat.getParentId();
					if (parentId != null && parentId.intValue() == 0) {
						ParentShopCat parentShopCat = new ParentShopCat();
						parentShopCat.setCat_id(cat.getCatId());
						parentShopCat.setCat_name(cat.getCatName());
						parentShopCat.setChildShopCat(new ArrayList<ChildShopCat>());
						results.add(parentShopCat);
					}
				}

				// 2.将所有的cat_id放到对应的parent_id集合中
				out: for (SysshopShopCat cat : list) {
					Integer parentId = cat.getParentId();
					if (parentId != null && parentId.intValue() != 0) {
						for (ParentShopCat parent : results) {
							Integer cat_id = parent.getCat_id();
							if (cat_id != null && cat_id.intValue() == parentId.intValue()) {
								ChildShopCat childShopCat = new ChildShopCat();
								childShopCat.setCat_id(cat.getCatId());
								childShopCat.setCat_name(cat.getCatName());
								parent.getChildShopCat().add(childShopCat);
								continue out;
							}
						}
					}
				}
				shopCatResult.setResults(results);

				result.setData(shopCatResult);
				result.setStatus(BaseApiCode.SUCCESS);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));
			} else {
				result.setStatus(BaseApiCode.NONE_DATA);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.NONE_DATA));
				result.setData(new JSONObject());
			}
		} catch (Exception e) {
			logger.error("获取店铺分类商品，service报异常：" + e);
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
		}
		return result;
	}

	@Override
	public ResBodyData getShopProductList(ShopProductRequest param) {
		ResBodyData result = new ResBodyData();// 最终返回的数据对象
		try {
			ShopProductList data = new ShopProductList();

			// 设置排序字段
			if (StringUtil.isEmptyByString(param.getOrder_by())) {
				param.setOrder_by("store");
			}
			switch (param.getOrder_by()) {
			case "updateTime":// 按修改时间排序
				param.setOrder_by("modified_time");
				break;
			case "price":// 按价格排序
				param.setOrder_by("price");
				break;
			case "point":// 按积分排序
				param.setOrder_by("point");
				break;
			default:// 默认：按销量排序
				param.setOrder_by("sold_quantity");
				break;
			}

			// 设置排序规则--升序或者降序
			if (StringUtil.isEmptyByString(param.getColumn())) {
				param.setColumn("desc");
			}
			switch (param.getColumn()) {
			case "asc":// 升序
				param.setColumn("asc");
				break;
			default:// 默认：降序
				param.setColumn("desc");
				break;
			}

			// 处理分页
			if (param.getPageNo() == null || param.getPageNo().intValue() == 0) {
				param.setPageNo(1);
			}
			if (param.getPageSize() == null || param.getPageSize().intValue() == 0) {
				param.setPageSize(20);
			}
			param.setLimitBegin((param.getPageNo().intValue() - 1) * param.getPageSize().intValue());

			// 查询商品总数量
			int itemTotal = baseDao.selectOne(param, "SysitemItemMapper.selectItemCountByShopInfo");
			int totalPage = (itemTotal + param.getPageSize() - 1) / param.getPageSize();

			// 查询商品列表
			List<GoodsDetailResult> productList = baseDao.selectList(param, "SysitemItemMapper.selectItemByShopInfo");

			data.setPageNo(param.getPageNo());
			data.setPageSize(param.getPageSize());
			data.setTotalPage(totalPage);
			data.setProductList(productList);

			result.setData(data);
			result.setStatus(BaseApiCode.SUCCESS);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.SUCCESS));

		} catch (Exception e) {
			logger.error("获取店铺商品列表，service报异常：" + e);
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
		}
		return result;
	}

}
