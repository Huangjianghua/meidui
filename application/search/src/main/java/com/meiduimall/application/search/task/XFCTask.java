package com.meiduimall.application.search.task;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.meiduimall.application.search.utility.XFCUtil;

@Component("xfcTask")
public class XFCTask {

	private Logger log = Logger.getLogger(XFCTask.class);

	public void startTask() {
		log.info("=========================获取xfc定时任务开始========================");
		XFCUtil.getXfcPrice();
		log.info("=========================获取xfc定时任务结束========================");
	}
}
