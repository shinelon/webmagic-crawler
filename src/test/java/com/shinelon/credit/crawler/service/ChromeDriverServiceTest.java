/**
 *ChromeDriverServiceTest.java 
 *
 * 2018年11月29日
 * 
 * @author shinelon
 */
package com.shinelon.credit.crawler.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.shinelon.credit.crawler.CreditCrawlerApplicationTests;
import com.shinelon.credit.crawler.chrome.ChromeDriverDownloader;
import com.shinelon.credit.crawler.zrfan.model.Activity;
import com.shinelon.credit.crawler.zrfan.page.GuidePageProcessor;
import com.shinelon.credit.crawler.zrfan.pipeline.ZrfanActivityPipeline;
import com.shinelon.credit.crawler.zrfan.spider.ZrfanActivitySpider;
import com.shinelon.credit.crawler.zrfan.utils.ZrfanUtils;

import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * ChromeDriverServiceTest.java
 *
 * @author syq
 *
 */
public class ChromeDriverServiceTest extends CreditCrawlerApplicationTests{
	
	private static final Logger logger = LoggerFactory.getLogger(ChromeDriverServiceTest.class);
	
	@Autowired
	@Qualifier("spiderTaskExecutor")
	private ExecutorService executor;
	@Autowired
	@Qualifier("spiderChromeDriverDownloader")
	private ChromeDriverDownloader chromeDriverDownloader;
	
	@Test
	public void chromeTest() {
		List<String> urls=Arrays.asList("https://www.zrfan.com/category/zhinan/");
		if (CollectionUtils.isEmpty(urls)) {
            throw new RuntimeException("urls is empty");
        }
		
		ZrfanActivitySpider spider =new ZrfanActivitySpider(new GuidePageProcessor(Arrays.asList(ZrfanUtils.getTodayStr())));
		urls.forEach(e -> {
            spider.addUrl(e);
        });
		spider.thread(executor, 1);
        spider.setDownloader(chromeDriverDownloader);
        spider.addPipeline(new ZrfanActivityPipeline());
        spider.run();

         ZrfanActivityPipeline sp = spider.getZrfanActivityPipeline();
         List<Activity> list = sp.getCollected();
         
         logger.info("list size:{}",JSON.toJSONString(list));
	}
	
	
}
