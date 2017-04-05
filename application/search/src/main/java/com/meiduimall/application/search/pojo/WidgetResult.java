package com.meiduimall.application.search.pojo;

/**
 * 类名: WidgetResult
 * 描述: 挂件结果
 * 编写者: pjl 
 * 版权: Copyright (C) 2016 first版权所有
 * 创建时间: 2016年7月12日
 */
public class WidgetResult {

	private TopAdWidget topAdWidget;
	
	private SearchWidget searchWidget;
	
	private NoticeWidget noticeWidget;
	
	private TopAdBannerWidget topAdBannerWidget;

	public TopAdWidget getTopAdWidget() {
		return topAdWidget;
	}

	public void setTopAdWidget(TopAdWidget topAdWidget) {
		this.topAdWidget = topAdWidget;
	}

	public SearchWidget getSearchWidget() {
		return searchWidget;
	}

	public void setSearchWidget(SearchWidget searchWidget) {
		this.searchWidget = searchWidget;
	}

	public NoticeWidget getNoticeWidget() {
		return noticeWidget;
	}

	public void setNoticeWidget(NoticeWidget noticeWidget) {
		this.noticeWidget = noticeWidget;
	}

	public TopAdBannerWidget getTopAdBannerWidget() {
		return topAdBannerWidget;
	}

	public void setTopAdBannerWidget(TopAdBannerWidget topAdBannerWidget) {
		this.topAdBannerWidget = topAdBannerWidget;
	}

	@Override
	public String toString() {
		return "WidgetResult [topAdWidget=" + topAdWidget + ", searchWidget="
				+ searchWidget + ", noticeWidget=" + noticeWidget
				+ ", topAdBannerWidget=" + topAdBannerWidget + "]";
	}
}
