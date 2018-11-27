/**
 *ZrfanCrawlerServiceTest.java 
 *
 * 2018年11月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.service;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.shinelon.credit.crawler.CreditCrawlerApplicationTests;
import com.shinelon.credit.crawler.zrfan.service.BankCrawlerService;
import com.shinelon.credit.crawler.zrfan.service.GuideCrawlerService;
import com.shinelon.credit.crawler.zrfan.utils.ZrfanUtils;

/**
 * ZrfanCrawlerServiceTest.java
 *
 * @author syq
 *
 */
public class ZrfanCrawlerServiceTest extends CreditCrawlerApplicationTests {

	@Autowired
	@Qualifier("bankCrawlerService")
	private BankCrawlerService crawlerService;

	@Autowired
	@Qualifier("guideCrawlerService")
	private GuideCrawlerService guidecrawlerService;

	@Test
	public void executeBanktest() {
		// ,"https://www.zrfan.com/category/bank/page/2/","https://www.zrfan.com/category/bank/page/3/"
		crawlerService.execute(Arrays.asList("https://www.zrfan.com/category/bank/"));
	}

	@Test
	public void executeBankUrlTest() {
		crawlerService.execute(Arrays.asList("https://www.zrfan.com/2431.html"));
	}

	@Test
	public void executeGuideTest() {
		guidecrawlerService.execute(Arrays.asList("https://www.zrfan.com/category/zhinan/"),
				Arrays.asList(ZrfanUtils.getTodayStr()));
	}

	@Test
	public void executeAll() {
		
		crawlerService.execute(Arrays.asList("https://www.zrfan.com/category/bank/",
				"https://www.zrfan.com/category/bank/page/2/", "https://www.zrfan.com/category/bank/page/3/"));
		guidecrawlerService.execute(Arrays.asList("https://www.zrfan.com/category/zhinan/"),
				Arrays.asList(ZrfanUtils.getTodayStr()));
	}

}
