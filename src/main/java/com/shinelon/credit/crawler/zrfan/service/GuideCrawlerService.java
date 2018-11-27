/**
 *GuideCrawlerService.java 
 *
 * 2018年11月27日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.zrfan.service;

import java.util.List;

/**
 * GuideCrawlerService.java
 *
 * @author syq
 *
 */
public interface GuideCrawlerService {
	/***
	 * 指南抓取入口
	 * @param urls
	 * @param dateStrs
	 */
	void execute(List<String> urls,List<String> dateStrs);
}
