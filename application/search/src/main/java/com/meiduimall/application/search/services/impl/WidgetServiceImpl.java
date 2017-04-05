package com.meiduimall.application.search.services.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.application.search.IDao.WidgetsMapper;
import com.meiduimall.application.search.pojo.NoticeWidget;
import com.meiduimall.application.search.pojo.SearchWidget;
import com.meiduimall.application.search.pojo.TopAdBannerWidget;
import com.meiduimall.application.search.pojo.TopAdWidget;
import com.meiduimall.application.search.pojo.WidgetResult;
import com.meiduimall.application.search.pojo.Widgets;
import com.meiduimall.application.search.services.WidgetService;
import com.meiduimall.application.search.utility.PHPSerializer;

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
			Object obj = PHPSerializer.unserialize(params.getBytes("utf-8"));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>)obj;
			String adPic = map.get("ad_pic");
			String adPicLink = map.get("ad_pic_link");
			String adPicDesc = map.get("ad_pic_desc");
			String domid = map.get("domid");
			String theme = map.get("theme");
			
			TopAdWidget ad = new TopAdWidget();
			ad.setAdPic(adPic);
			ad.setAdPicLink(adPicLink);
			ad.setAdPicDesc(adPicDesc);
			ad.setDomid(domid);
			ad.setWidgetsTheme(theme);
			ad.setWidgets(wd.getWidgetsType());
			ad.setWidgetsId(wd.getWidgetsId());
			
			return ad;
		}
		return null;
	}
	
	private TopAdBannerWidget queryTopAdBannerWidget() throws Exception {
		List<Widgets> widgets = widgetsMapper.queryWidgets("ad_banner_top");
		if (widgets != null && widgets.size() > 0) {
			Widgets wd = widgets.get(0);
			String params = wd.getParams();
			Object obj = PHPSerializer.unserialize(params.getBytes("utf-8"));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>)obj;
			String adPic = map.get("ad_pic");
			String adPicLink = map.get("ad_pic_link");
			String adPicTxt = map.get("ad_pic_txt");
			String domid = map.get("domid");
			String theme = map.get("theme");
			
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
			Object obj = PHPSerializer.unserialize(params.getBytes("utf-8"));
			HashMap<String, Object> map = (HashMap<String, Object>)obj;
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
			Object obj = PHPSerializer.unserialize(params.getBytes("utf-8"));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>)obj;
			String noticeVisable = map.get("notice_visable");
			String noticeTitle = map.get("notice_title");
			String domid = map.get("domid");
			String theme = map.get("theme");
			
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
