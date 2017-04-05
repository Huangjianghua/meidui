package com.first.pojo;

import java.util.List;

/**
 * 类名: SearchWidget
 * 描述: 搜索挂件
 * 编写者: pjl 
 * 版权: Copyright (C) 2016 first版权所有
 * 创建时间: 2016年7月12日
 */
public class SearchWidget {

	private String widgetsId;
	
	private String widgetsTheme;
	
	private String widgets;
	
	private String domain;
	
	private String domid;
	
	private List<String> topLinkTitle;
	
	private String hotkey;

	public String getWidgetsId() {
		return widgetsId;
	}

	public void setWidgetsId(String widgetsId) {
		this.widgetsId = widgetsId;
	}

	public String getWidgetsTheme() {
		return widgetsTheme;
	}

	public void setWidgetsTheme(String widgetsTheme) {
		this.widgetsTheme = widgetsTheme;
	}

	public String getWidgets() {
		return widgets;
	}

	public void setWidgets(String widgets) {
		this.widgets = widgets;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomid() {
		return domid;
	}

	public void setDomid(String domid) {
		this.domid = domid;
	}

	public List<String> getTopLinkTitle() {
		return topLinkTitle;
	}

	public void setTopLinkTitle(List<String> topLinkTitle) {
		this.topLinkTitle = topLinkTitle;
	}

	public String getHotkey() {
		return hotkey;
	}

	public void setHotkey(String hotkey) {
		this.hotkey = hotkey;
	}

	@Override
	public String toString() {
		return "SearchWidget [widgetsId=" + widgetsId + ", widgetsTheme="
				+ widgetsTheme + ", widgets=" + widgets + ", domain=" + domain
				+ ", domid=" + domid + ", topLinkTitle=" + topLinkTitle
				+ ", hotkey=" + hotkey + "]";
	}
}
