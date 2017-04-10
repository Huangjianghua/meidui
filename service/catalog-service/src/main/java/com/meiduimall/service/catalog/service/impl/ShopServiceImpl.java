package com.meiduimall.service.catalog.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.JsonItemDetailResult_ShopData;
import com.meiduimall.service.catalog.entity.SysshopShop;
import com.meiduimall.service.catalog.entity.SysshopShopExample;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.entity.SysuserShopFav;
import com.meiduimall.service.catalog.entity.SysuserShopFavExample;
import com.meiduimall.service.catalog.request.ShopProductRequest;
import com.meiduimall.service.catalog.service.ShopService;
import com.meiduimall.service.catalog.service.common.ShopCommonService;

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
				// 1.首先判断该用户是否收藏了该店铺
				SysuserShopFavExample shopFavExample = new SysuserShopFavExample();
				SysuserShopFavExample.Criteria criteria = shopFavExample.createCriteria();
				criteria.andShopIdEqualTo(shopId);
				criteria.andUserIdEqualTo(userId);
				int collectCount = baseDao.selectOne(shopFavExample, "SysuserShopFavMapper.countByExample");
				if (collectCount > 0) {
					result.setStatus(BaseApiCode.ALREADY_COLLECT);
					result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.ALREADY_COLLECT));
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

				// TODO 时间如何取值
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

		} catch (Exception e) {
			logger.error("获取店铺分类商品，service报异常：" + e);
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
		}
		return result;
	}

	@Override
	public ResBodyData getShopProductList(ShopProductRequest params) {
		// TODO Auto-generated method stub
		return null;
	}

}