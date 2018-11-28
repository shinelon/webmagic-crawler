/**
 *FileDownloadServiceTest.java 
 *
 * 2018年11月28日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.shinelon.credit.crawler.CreditCrawlerApplicationTests;
import com.shinelon.credit.crawler.api.bean.ActivityFile;
import com.shinelon.credit.crawler.service.impl.FileDownloadServiceImpl;

/**
 * FileDownloadServiceTest.java
 *
 * @author syq
 *
 */
public class FileDownloadServiceTest extends CreditCrawlerApplicationTests{
	
	@Autowired
	private FileDownloadServiceImpl FileDownloadService;
	
	@Test
	public void ListFileTest() {
		List<ActivityFile> listActivityFile = FileDownloadService.listActivityFile();
		logger.info("listActivityFile:{}",JSON.toJSONString(listActivityFile));
	}

}
