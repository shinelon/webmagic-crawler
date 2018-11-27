/**
 *CrawlerServiceImpl.java 
 *
 * 2018年11月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.zrfan.service.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shinelon.credit.crawler.config.SeleniumDownloader;
import com.shinelon.credit.crawler.zrfan.model.Activity;
import com.shinelon.credit.crawler.zrfan.page.BankPageProcessor;
import com.shinelon.credit.crawler.zrfan.pipeline.ZrfanActivityPipeline;
import com.shinelon.credit.crawler.zrfan.service.BankCrawlerService;
import com.shinelon.credit.crawler.zrfan.spider.ZrfanActivitySpider;
import com.shinelon.credit.crawler.zrfan.utils.ZrfanUtils;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * CrawlerServiceImpl.java
 *
 * @author syq
 *
 */
@Service("bankCrawlerService")
public class BankCrawlerServiceImpl implements BankCrawlerService {
	
	private static final Logger logger = LoggerFactory.getLogger(BankCrawlerServiceImpl.class);
	
	@Value("${activity.file.path}")
	private String filePath;
	@Value("${activity.file.name.bank}")
	private String fileName;
	@Value("${activity.file.suffix}")
	private String fileSuffix;
	
	@Autowired
	@Qualifier("spiderTaskExecutor")
	private ExecutorService executor;
	@Autowired
	@Qualifier("spiderHttpClientDownloader")
	private HttpClientDownloader httpClientDownloader;
//	@Autowired
//	@Qualifier("spiderSeleniumDownloader")
//	private SeleniumDownloader seleniumDownloader;

	@Override
	public void execute(List<String> urls) {
		if (CollectionUtils.isEmpty(urls)) {
            throw new RuntimeException("urls is empty");
        }
		
		ZrfanActivitySpider spider =new ZrfanActivitySpider(new BankPageProcessor());
		urls.forEach(e -> {
            spider.addUrl(e);
        });
		spider.thread(executor, 1);
        spider.setDownloader(httpClientDownloader);
//		spider.setDownloader(seleniumDownloader);
        spider.addPipeline(new ZrfanActivityPipeline());
        spider.run();

        ZrfanActivityPipeline sp = spider.getZrfanActivityPipeline();
        List<Activity> list = sp.getCollected();
        
        logger.info("list size:{}", list.size());
        writeCsv(list);
	}
	
	private void writeCsv(List<Activity> list) {
		List<String[]> csvList = csvList(list);
		CsvWriter writer = CsvUtil.getWriter(getFileFullName(), Charset.forName("GBK"));
		writer.write(csvList);
		writer.close();
	}
	
	private String getFileFullName() {
		return filePath+fileName+"-"+ZrfanUtils.getTodayStr()+fileSuffix;
	}
	
	private List<String[]> csvList(List<Activity> list) {
		List<String[]> ret = new ArrayList<>(list.size() + 1);
		ret.add(new String[] { "银行名称", "关键词", "标题", "内容","URL" });
		ret.addAll(
				list.stream().map(e -> new String[] { e.getBankName(), e.getKeywords(), e.getTitle(), e.getContent(),e.getUrl() })
						.collect(Collectors.toList()));
		return ret;

	}
}
