package com.meiduimall.service.catalog.service.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.meiduimall.core.util.NumberFormatUtil;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.IdAndMemId;
import com.meiduimall.service.catalog.entity.SysrateDsrWithBLOBs;
import com.meiduimall.service.catalog.entity.SysshopShopWithBLOBs;
import com.meiduimall.service.catalog.result.JsonItemDetailResult_ShopData;
import com.meiduimall.service.catalog.util.ParseSysRateDsrInfo;

/**
 * 公共服务类--店铺相关
 * 
 * @author yangchang
 *
 */
public class ShopCommonService {

	/**
	 * 获取店铺详情
	 * 
	 * @param baseDao
	 * @param shopId
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static JsonItemDetailResult_ShopData getJsonItemDetailResult_ShopData(BaseDao baseDao, Integer shopId,
			String memId) {
		
		// 查询店铺信息
		SysshopShopWithBLOBs shopWithBLOBs = baseDao.selectOne(shopId, "SysshopShopMapper.selectByPrimaryKey");
		
		if (shopWithBLOBs == null) {
			return null;
		}
		
		JsonItemDetailResult_ShopData shopData = new JsonItemDetailResult_ShopData();

		SysrateDsrWithBLOBs rateDsrWithBLOBs = baseDao.selectOne(new Long(shopId.longValue()),
				"SysrateDsrMapper.selectByPrimaryKey");
		if (rateDsrWithBLOBs != null) {
			// 反序列化数据---解析店铺信息中的：描述相符、服务态度、发货速度的分值
			try {
				float fTallyDsr = ParseSysRateDsrInfo.getValue(rateDsrWithBLOBs.getTallyDsr());
				float fDeliverySpeedDsr = ParseSysRateDsrInfo.getValue(rateDsrWithBLOBs.getDeliverySpeedDsr());
				float fAttitudeDsr = ParseSysRateDsrInfo.getValue(rateDsrWithBLOBs.getAttitudeDsr());

				shopData.setTally_dsr(NumberFormatUtil.formatString(fTallyDsr, 1));
				shopData.setDelivery_speed_dsr(NumberFormatUtil.formatString(fDeliverySpeedDsr, 1));
				shopData.setAttitude_dsr(NumberFormatUtil.formatString(fAttitudeDsr, 1));
			} catch (Exception e) {
				throw new ServiceException(ServiceCatalogApiCode.SHOP_DATA_EXCEPTION);
			}
		} else {
			shopData.setAttitude_dsr("5.0");
			shopData.setDelivery_speed_dsr("5.0");
			shopData.setTally_dsr("5.0");
		}

		shopData.setShop_descript(shopWithBLOBs.getShopDescript());
		shopData.setShop_id(shopId.toString());
		shopData.setShop_logo(shopWithBLOBs.getShopLogo());
		shopData.setShop_name(shopWithBLOBs.getShopName());
		shopData.setShop_area(shopWithBLOBs.getShopArea());

		// 开店时间
		Integer openTime = shopWithBLOBs.getOpenTime();
		if (openTime != null) {
			shopData.setOpen_time(DateFormatUtils.format(openTime.intValue() * 1000l, "yyyy年MM月dd日"));
		} else {
			shopData.setOpen_time("");
		}

		String shopType = shopWithBLOBs.getShopType();
		shopData.setShop_type(shopType);
		// if ("brand".equals(shopType)) {
		// shopData.setShop_type("品牌专卖店");
		// } else if ("cat".equals(shopType)) {
		// shopData.setShop_type("类目专营店");
		// } else if ("flag".equals(shopType)) {
		// shopData.setShop_type("品牌旗舰店");
		// } else if ("self".equals(shopType)) {
		// shopData.setShop_type("运营商自营店铺");
		// } else {
		// shopData.setShop_type("未知");
		// }

		// 查询用户是否收藏了该店铺，查 表sysuser_shop_fav
		if (StringUtils.isEmpty(memId)) {
			// 没有token，不需要处理
			shopData.setIs_collect("0");
		} else {
			// 处理token
			IdAndMemId idAndMemId = new IdAndMemId();
			idAndMemId.setId(shopId.intValue());
			idAndMemId.setMem_id(memId);
			int count = baseDao.selectOne(idAndMemId, "SysuserShopFavMapper.selectCountByItemIdAndMemId");
			if (count > 0) {
				shopData.setIs_collect("1");
			} else {
				shopData.setIs_collect("0");
			}
		}

		return shopData;
	}
}
