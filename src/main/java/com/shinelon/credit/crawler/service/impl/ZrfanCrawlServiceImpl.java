/**
 *ZrfanCrawlServiceImpl.java 
 *
 * 2018年11月28日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shinelon.credit.crawler.service.ZrfanCrawlService;
import com.shinelon.credit.crawler.zrfan.service.BankCrawlerService;
import com.shinelon.credit.crawler.zrfan.service.GuideCrawlerService;

/**
 * ZrfanCrawlServiceImpl.java
 *
 * @author syq
 *
 */
@Service
public class ZrfanCrawlServiceImpl implements ZrfanCrawlService{
	
	private static final Logger logger = LoggerFactory.getLogger(ZrfanCrawlServiceImpl.class);
	
    private static final String BANK_URL="https://www.zrfan.com/category/bank/";
    
    private static final String GUIDE_URL="https://www.zrfan.com/category/zhinan/";
	
    @Autowired
	@Qualifier("bankCrawlerService")
	private BankCrawlerService bankCrawlerService;

	@Autowired
	@Qualifier("guideCrawlerService")
	private GuideCrawlerService guidecrawlerService;
    
	/***
	 * 抓取几页
	 * @param pageSum
	 */
	@Override
	public void crawlBank(Integer pageSum) {
		List<String> urls=new ArrayList<>();
		urls.add(BANK_URL);
		if(pageSum>1) {
			for (int i = 1; i < pageSum; i++) {
				int pageNo=i+1;
				urls.add(BANK_URL+"page/"+pageNo+"/");
			}
		}
		logger.info("bank urls:{}",urls);
		bankCrawlerService.execute(urls);
	}
	
	/***
	 * 抓取指南
	 * @param dateStr
	 */
	@Override
	public void crawlGuide(String dateStr) {
		logger.info("guide urls:{} date:{}",GUIDE_URL,dateStr);
		guidecrawlerService.execute(Arrays.asList(GUIDE_URL), Arrays.asList(dateStr));
	}
	

}
