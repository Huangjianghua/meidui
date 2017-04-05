package com.first.pojo;

/**
 * 类名: TopAdBannerWidget
 * 描述: 顶部广告挂件
 * 编写者: pjl 
 * 版权: Copyright (C) 2016 first版权所有
 * 创建时间: 2016年7月12日
 */
public class TopAdBannerWidget {

	private String widgetsId;
	
	private String widgetsTheme;
	
	private String widgets;
	
	private String domid;
	
	private String adPic;
	
	private String adPicLink;
	
	private String adPicTxt;

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

	public String getDomid() {
		return domid;
	}

	public void setDomid(String domid) {
		this.domid = domid;
	}

	public String getAdPic() {
		return adPic;
	}

	public void setAdPic(String adPic) {
		this.adPic = adPic;
	}

	public String getAdPicLink() {
		return adPicLink;
	}

	public void setAdPicLink(String adPicLink) {
		this.adPicLink = adPicLink;
	}

	public String getAdPicTxt() {
		return adPicTxt;
	}

	public void setAdPicTxt(String adPicTxt) {
		this.adPicTxt = adPicTxt;
	}

	@Override
	public String toString() {
		return "TopAdBannerWidget [widgetsId=" + widgetsId + ", widgetsTheme="
				+ widgetsTheme + ", widgets=" + widgets + ", domid=" + domid
				+ ", adPic=" + adPic + ", adPicLink=" + adPicLink
				+ ", adPicTxt=" + adPicTxt + "]";
	}
}
