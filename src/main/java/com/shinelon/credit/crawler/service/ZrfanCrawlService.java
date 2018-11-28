/**
 *ZrfanCrawlService.java 
 *
 * 2018年11月28日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.service;

/**
 * ZrfanCrawlService.java
 *
 * @author syq
 *
 */
public interface ZrfanCrawlService {
	
	/***
	 * crawlBank
	 * @param pageSum
	 */
	void crawlBank(Integer pageSum);
	
	/***
	 * crawlGuide
	 * @param dateStr
	 */
	void crawlGuide(String dateStr);
}
