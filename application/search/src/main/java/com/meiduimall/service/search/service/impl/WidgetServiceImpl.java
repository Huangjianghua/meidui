package com.meiduimall.service.search.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.search.entity.Widgets;
import com.meiduimall.service.search.mapper.WidgetsMapper;
import com.meiduimall.service.search.result.NoticeWidget;
import com.meiduimall.service.search.result.SearchWidget;
import com.meiduimall.service.search.result.TopAdBannerWidget;
import com.meiduimall.service.search.result.TopAdWidget;
import com.meiduimall.service.search.result.WidgetResult;
import com.meiduimall.service.search.service.WidgetService;
import com.meiduimall.service.search.util.PHPUnSerializerUtil;

@Service
public class WidgetServiceImpl implements WidgetService {

	@Autowired
	private WidgetsMapper widgetsMapper;

	@Override
	public WidgetResult queryWidgets() throws Exception {
		WidgetResult wr = new WidgetResult();
		wr.setNoticeWidget(queryNoticeWidget());
		wr.setSearchWidget(querySearchWidget());
		wr.setTopAdBannerWidget(queryTopAdBannerWidget());
		wr.setTopAdWidget(queryTopAdWidget());
		return wr;
	}

	private TopAdWidget queryTopAdWidget() throws Exception {
		List<Widgets> widgets = widgetsMapper.queryWidgets("new_top_ad");
		if (widgets != null && widgets.size() > 0) {
			Widgets wd = widgets.get(0);
			String params = wd.getParams();

			Map<String, Object> map = PHPUnSerializerUtil.siteWidgetsInstanceParams(params);

			String adPic = (String) map.get("ad_pic");
			String adPicLink = (String) map.get("ad_pic_link");
			String adPicDesc = (String) map.get("ad_pic_desc");
			String domid = (String) map.get("domid");
			String theme = (String) map.get("theme");

			TopAdWidget ad = new TopAdWidget();
			ad.setAdPic(adPic);
			ad.setAdPicLink(adPicLink);
			ad.setAdPicDesc(adPicDesc);
			ad.setDomid(domid);
			ad.setWidgetsTheme(theme);
			ad.setWidgets(wd.getWidgetsType());
			ad.setWidgetsId(wd.getWidgetsId());
			return ad;
		} else {
			return null;
		}
	}

	private TopAdBannerWidget queryTopAdBannerWidget() throws Exception {
		List<Widgets> widgets = widgetsMapper.queryWidgets("ad_banner_top");
		if (widgets != null && widgets.size() > 0) {
			Widgets wd = widgets.get(0);
			String params = wd.getParams();
			Map<String, Object> map = PHPUnSerializerUtil.siteWidgetsInstanceParams(params);
			String adPic = (String) map.get("ad_pic");
			String adPicLink = (String) map.get("ad_pic_link");
			String adPicTxt = (String) map.get("ad_pic_txt");
			String domid = (String) map.get("domid");
			String theme = (String) map.get("theme");

			TopAdBannerWidget tbw = new TopAdBannerWidget();
			tbw.setAdPic(adPic);
			tbw.setAdPicLink(adPicLink);
			tbw.setAdPicTxt(adPicTxt);
			tbw.setDomid(domid);
			tbw.setWidgetsTheme(theme);
			tbw.setWidgets(wd.getWidgetsType());
			tbw.setWidgetsId(wd.getWidgetsId());

			return tbw;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private SearchWidget querySearchWidget() throws Exception {
		List<Widgets> widgets = widgetsMapper.queryWidgets("search");
		if (widgets != null && widgets.size() > 0) {
			Widgets wd = widgets.get(0);
			String params = wd.getParams();
			Map<String, Object> map = PHPUnSerializerUtil.siteWidgetsInstanceParams(params);
			String domain = (String) map.get("domain");
			String hotkey = (String) map.get("hotkey");
			List<String> topLinkTitle = (List<String>) map.get("top_link_title");
			String domid = (String) map.get("domid");
			String theme = (String) map.get("theme");

			SearchWidget sw = new SearchWidget();
			sw.setDomain(domain);
			sw.setHotkey(hotkey);
			sw.setDomid(domid);
			sw.setWidgetsTheme(theme);
			sw.setTopLinkTitle(topLinkTitle);
			sw.setWidgets(wd.getWidgetsType());
			sw.setWidgetsId(wd.getWidgetsId());

			return sw;
		}
		return null;
	}

	private NoticeWidget queryNoticeWidget() throws Exception {
		List<Widgets> widgets = widgetsMapper.queryWidgets("notice");
		if (widgets != null && widgets.size() > 0) {
			Widgets wd = widgets.get(0);
			String params = wd.getParams();
			Map<String, Object> map = PHPUnSerializerUtil.siteWidgetsInstanceParams(params);
			String noticeVisable = (String) map.get("notice_visable");
			String noticeTitle = (String) map.get("notice_title");
			String domid = (String) map.get("domid");
			String theme = (String) map.get("theme");

			NoticeWidget nw = new NoticeWidget();
			nw.setNoticeVisable(noticeVisable);
			nw.setNoticeTitle(noticeTitle);
			nw.setDomid(domid);
			nw.setWidgetsTheme(theme);
			nw.setWidgets(wd.getWidgetsType());
			nw.setWidgetsId(wd.getWidgetsId());

			return nw;
		}
		return null;
	}

}
