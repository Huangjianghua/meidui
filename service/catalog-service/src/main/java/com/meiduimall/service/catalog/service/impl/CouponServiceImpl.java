package com.meiduimall.service.catalog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.catalog.constant.ServiceCatalogApiCode;
import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.SyspromotionSendcouponrule;
import com.meiduimall.service.catalog.result.CouponRuleResult;
import com.meiduimall.service.catalog.result.ResultList;
import com.meiduimall.service.catalog.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private BaseDao baseDao;

	@Override
	public ResBodyData selectAllCouponRule() {
		List<SyspromotionSendcouponrule> list = baseDao.selectList(null, "SyspromotionSendcouponruleMapper.selectAll");
		List<CouponRuleResult> resultList = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (SyspromotionSendcouponrule rule : list) {
				CouponRuleResult bean = new CouponRuleResult();
				bean.setMinprice(rule.getMinprice());
				bean.setMaxprice(rule.getMaxprice());
				bean.setDeductMoney(rule.getDeductMoney());
				resultList.add(bean);
			}
			ResultList<CouponRuleResult> data = new ResultList<CouponRuleResult>();
			data.setResults(resultList);

			// 返回结果
			ResBodyData result = new ResBodyData();
			result.setStatus(ServiceCatalogApiCode.SUCCESS);
			result.setMsg(ServiceCatalogApiCode.getZhMsg(ServiceCatalogApiCode.REQUEST_SUCCESS));
			result.setData(data);
			return result;
		} else {
			throw new ServiceException(ServiceCatalogApiCode.NO_COUPON_RULE);
		}
	}

}
